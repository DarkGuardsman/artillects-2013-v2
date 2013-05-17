package dark.gsm.common.artillects.bots;

import universalelectricity.prefab.implement.IDisableable;
import icbm.api.explosion.IExplosive;
import icbm.api.explosion.IExplosiveContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityElecticCreeper extends EntityRobot implements IExplosiveContainer
{
	/**
	 * Time when this creeper was last in an active state (Messed up code here, probably causes
	 * creeper animation to go weird)
	 */
	private int lastActiveTime;

	/**
	 * The amount of time since the creeper was close enough to the player to ignite
	 */
	private int timeSinceIgnited;
	private int fuseTime = 30;

	public EntityElecticCreeper(World par1World)
	{
		super(par1World);
		this.texture = "/mob/creeper.png";
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 0.25F, false));
		this.tasks.addTask(5, new EntityAIWander(this, 0.2F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public int func_82143_as()
	{
		return this.getAttackTarget() == null ? 3 : 3 + (this.health - 1);
	}

	@Override
	protected void fall(float par1)
	{
		super.fall(par1);
		this.timeSinceIgnited = (int) ((float) this.timeSinceIgnited + par1 * 1.5F);

		if (this.timeSinceIgnited > this.fuseTime - 5)
		{
			this.timeSinceIgnited = this.fuseTime - 5;
		}
	}

	@Override
	public int getMaxHealth()
	{
		return 20;
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
		super.onDeath(par1DamageSource);

		if (par1DamageSource.getEntity() instanceof EntitySkeleton)
		{
			int i = Item.record13.itemID + this.rand.nextInt(Item.recordWait.itemID - Item.record13.itemID + 1);
			this.dropItem(i, 1);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		return true;
	}

	/**
	 * Returns true if the creeper is powered by a lightning bolt.
	 */
	public boolean getPowered()
	{
		return this.dataWatcher.getWatchableObjectByte(17) == 1;
	}

	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float par1)
	{
		return ((float) this.lastActiveTime + (float) (this.timeSinceIgnited - this.lastActiveTime) * par1) / (float) (this.fuseTime - 2);
	}

	@Override
	protected int getDropItemId()
	{
		return Item.gunpowder.itemID;
	}

	/**
	 * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
	 */
	public int getCreeperState()
	{
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	/**
	 * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
	 */
	public void setCreeperState(int par1)
	{
		this.dataWatcher.updateObject(16, Byte.valueOf((byte) par1));
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt)
	{
		super.onStruckByLightning(par1EntityLightningBolt);
		this.dataWatcher.updateObject(17, Byte.valueOf((byte) 1));
	}

	@Override
	public IExplosive getExplosiveType()
	{
		// TODO Auto-generated method stub
		return null;
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
}