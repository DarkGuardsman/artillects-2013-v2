package dark.gsm.artillects.common.bots;

import icbm.api.RadarRegistry;

import java.util.Random;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.CustomDamageSource;
import dark.gsm.artillects.common.blocks.IBotController;
import dark.interfaces.IDisableable;
import dark.prefab.helpers.FluidHelper;

public abstract class EntityRobot extends EntityCreature implements IDisableable
{
    Random random = new Random();
    /* Energy stored in the internal battery */
    private double wattsStored = 0;

    /* Temp value for the robots group */
    private String faction = "world";
    private String displayName = "Robot";
    /* Data watch values */
    private static final int powerWatcher = 17;

    private int disableTime = 0;

    protected IBotController controler;

    public EntityRobot(World par1World)
    {
        super(par1World);
        this.isImmuneToFire = true;
        this.experienceValue = 10;

    }

    public void setControler(IBotController con)
    {
        this.controler = con;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(powerWatcher, new Byte((byte) 0));
        RadarRegistry.register(this);
    }

    @Override
    public void setDead()
    {
        super.setDead();
        RadarRegistry.unregister(this);
    }

    @Override
    public void onLivingUpdate()
    {
        this.updateArmSwingProgress();
        /* Disable timer update */
        if (this.disableTime > 0)
        {
            this.disableTime--;
        }
        if (this.controler != null)
        {
            if (!this.controler.isConnected(this))
            {
                this.controler = null;
            }
        }
        /* Generates smoke particles if the bot is bellow 10% health */
        if (this.func_110143_aJ() < ((int) this.getMaxHealth() / 10))
        {
            for (int i = 0; i < 2; ++i)
            {
                this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D);
            }
        }

        /* Power update */
        if (!this.worldObj.isRemote)
        {
            /* Solor power handler */// TODO check for sight of sky
            double solorPower = this.getSolorPower(this.getBrightness(1.0F));
            if (solorPower > 0 && this.worldObj.canBlockSeeTheSky((int) this.posX, (int) this.posY, (int) this.posZ))
            {
                this.wattsStored += solorPower;
            }

            /* Data Watcher setter for power updates */
            this.setPowerWatcher(this.wattsStored);
        }
        else
        {
            this.wattsStored = this.getPower();
        }

        /* Running update */
        if (!this.isDisabled() && this.wattsStored >= this.getRunningWatts())
        {
            this.wattsStored -= this.getRunningWatts();
            super.onLivingUpdate();
            this.runningUpdate();
        }
    }

    public int getMaxHealth()
    {
        return 50;
    }

    /** Logic or running operation of the bot after power checks */
    public abstract void runningUpdate();

    /** watts needed to just keep the bot running. */
    public abstract int getRunningWatts();

    @Override
    public boolean getCanSpawnHere()
    {
        return false;
    }

    /** Value of solar power generated by this robot
     * 
     * @param brightness - above 0.6 is consider sunlight bright
     * 
     * @return amount of watts generated per tick of sunlight */
    public double getSolorPower(float brightness)
    {
        return 0;
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setDouble("watts", this.wattsStored);
        nbt.setString("factionName", this.faction);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.wattsStored = nbt.getDouble("watts");
        this.faction = nbt.getString("factionName");
    }

    /** Current power level of the bot */
    public double getPower()
    {
        return (double) this.dataWatcher.getWatchableObjectByte(powerWatcher);
    }

    /** updates the dataWatcher's power level of the bot */
    public void setPowerWatcher(double power)
    {
        this.dataWatcher.updateObject(powerWatcher, Byte.valueOf((byte) power));
    }

    @Override
    public float getBlockPathWeight(int x, int y, int z)
    {
        Vector3 vec = new Vector3(x, y, z);
        int blockID = vec.getBlockID(this.worldObj);
        int meta = vec.getBlockMetadata(this.worldObj);
        if (FluidHelper.drainBlock(this.worldObj, new Vector3(x, y, z), false) != null)
        {
            return -10.0F;
        }
        return 0.0F;
    }

    @Override
    public void onDisable(int duration)
    {
        this.disableTime += duration;
    }

    @Override
    public boolean isDisabled()
    {
        return this.disableTime > 0;
    }

    @Override
    public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt)
    {
        this.onDisable(random.nextInt(360));
        this.wattsStored = 0;
        this.damageEntity(CustomDamageSource.electrocution, 30);
    }
}
