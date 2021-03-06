package dark.gsm.artillects.common.bots;

import icbm.api.ICBM;
import icbm.api.explosion.IExplosive;
import icbm.api.explosion.IExplosiveContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityElecticCreeper extends EntityRobot implements IExplosiveContainer
{
    private int lastActiveTime;
    /* Time since the entity started to blow up */
    private int timeSinceIgnited;
    /* Length of time it takes for entity to blow up */
    private int fuseTime = 30;
    /* Name of the explosive to be used */
    private String explosiveName = "emp";
    private String[] validTNT = new String[] { "tnt", "emp", "shrapnel", "incendiary", "chemical" };

    public EntityElecticCreeper(World par1World)
    {
        super(par1World);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 0.25F, false));
        this.tasks.addTask(5, new EntityAIWander(this, 0.2F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    public EntityElecticCreeper(World world, String explosive)
    {
        this(world);
        if (!Loader.isModLoaded("ICBM|Explosion"))
        {
            this.explosiveName = "tnt";
        }
        else if (explosive != null && !explosive.isEmpty())
        {
            for (int i = 0; i < validTNT.length; i++)
            {
                if (explosive.equalsIgnoreCase(validTNT[i]))
                {
                    this.explosiveName = explosive;
                }
            }
        }

    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6000000238418582D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(100.0D);
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected void fall(float par1)
    {
        super.fall(par1);
        this.timeSinceIgnited = (int) (this.timeSinceIgnited + par1 * 1.5F);

        if (this.timeSinceIgnited > this.fuseTime - 5)
        {
            this.timeSinceIgnited = this.fuseTime - 5;
        }
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte) -1));
        this.dataWatcher.addObject(17, Byte.valueOf((byte) 0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);

        if (this.dataWatcher.getWatchableObjectByte(17) == 1)
        {
            par1NBTTagCompound.setBoolean("powered", true);
        }

        par1NBTTagCompound.setShort("Fuse", (short) this.fuseTime);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.dataWatcher.updateObject(17, Byte.valueOf((byte) (par1NBTTagCompound.getBoolean("powered") ? 1 : 0)));

        if (par1NBTTagCompound.hasKey("Fuse"))
        {
            this.fuseTime = par1NBTTagCompound.getShort("Fuse");
        }
    }

    @Override
    public void onUpdate()
    {
        if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;
            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound("random.fuse", 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.timeSinceIgnited = this.fuseTime;
                this.boom();

            }
        }

        super.onUpdate();
    }

    public void boom()
    {
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
            if (!this.explosiveName.equalsIgnoreCase("tnt") && this.getExplosiveType() != null)
            {
                this.getExplosiveType().createExplosion(this.worldObj, this.posX, this.posY, this.posZ, this);
            }
            else
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 30, flag);
            }
            this.setDead();
        }
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.creeper.say";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.creeper.death";
    }

    @Override
    public void onDeath(DamageSource par1DamageSource)
    {
        if (this.isDisabled())
        {
            // ItemStack stack = ICBM.getExplosive(this.explosiveName).getExplosiveName()
        }
        super.onDeath(par1DamageSource);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        return true;
    }

    /** Returns true if the creeper is powered by a lightning bolt. */
    public boolean getPowered()
    {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    @SideOnly(Side.CLIENT)
    public float getCreeperFlashIntensity(float par1)
    {
        return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * par1) / (this.fuseTime - 2);
    }

    @Override
    protected int getDropItemId()
    {
        return Item.gunpowder.itemID;
    }

    /** Returns the current state of creeper, -1 is idle, 1 is 'in fuse' */
    public int getCreeperState()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    /** Sets the state of creeper, -1 to idle and 1 to be 'in fuse' */
    public void setCreeperState(int par1)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte) par1));
    }

    @Override
    public IExplosive getExplosiveType()
    {
        return ICBM.getExplosive(this.explosiveName);
    }

    @Override
    public void runningUpdate()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRunningWatts()
    {
        return 0;
    }

    @Override
    public NBTTagCompound getTagCompound()
    {
        return this.getEntityData();
    }
}