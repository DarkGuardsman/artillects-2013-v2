package dark.gsm.common.artillects.bots;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;
import dark.gsm.common.artillects.GSMMachines;
import dark.gsm.common.artillects.ai.combat.EnumRange;
import dark.gsm.common.artillects.ai.combat.IAttacker;
import dark.gsm.common.artillects.ai.combat.LaserHelper;
import dark.gsm.common.artillects.ai.combat.SearchHelper;
import dark.library.access.AccessLevel;
import dark.library.access.UserAccess;
import dark.library.access.interfaces.ISpecialAccess;

public class EntityEyeBot extends EntityFlying implements IAttacker, ISpecialAccess
{
	SearchHelper targetFinder;
	LaserHelper laserHelp;
	public boolean targetPlayers = true;
	public boolean targetAir = true;
	public boolean targetHostile = true;
	public boolean targetFriendly = true;

	public int courseChangeCooldown = 0;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	private Entity targetedEntity = null;

	/** Cooldown time between target loss and new target aquirement. */
	private int aggroCooldown = 0;
	public int prevAttackCounter = 0;
	public int attackCounter = 0;

	/** The explosion radius of spawned fireballs. */
	private int explosionStrength = 1;

	public EntityEyeBot(World par1World)
	{
		super(par1World);
		this.texture = GSMMachines.TEXTURE_DIRECTORY + "/entity/bots/CubeEyeBot.png";
		this.setSize(1.0F, 1.0F);
		this.isImmuneToFire = true;
		this.experienceValue = 15;
	}

	public SearchHelper getTargetHelper()
	{
		if (this.targetFinder == null)
		{
			this.targetFinder = new SearchHelper(this.worldObj, new Vector3(this), this);
		}
		return this.targetFinder;
	}

	public LaserHelper getLaser()
	{
		if (this.laserHelp == null)
		{
			this.laserHelp = new LaserHelper(this);
		}

		return this.laserHelp;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	/**
	 * Called when this takes damage from any damage source
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else
		{
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	@Override
	public int getMaxHealth()
	{
		return 30;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
	}

	@Override
	protected void updateEntityActionState()
	{
		this.prevAttackCounter = this.attackCounter;

		/* START PATH FINDING OR MAKING A NEW PATH */
		double d0 = this.waypointX - this.posX;
		double d1 = this.waypointY - this.posY;
		double d2 = this.waypointZ - this.posZ;

		double d3 = d0 * d0 + d1 * d1 + d2 * d2;

		if (d3 < 1.0D || d3 > 3600.0D)
		{
			this.waypointX = this.posX + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.waypointY = this.posY + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.waypointZ = this.posZ + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
		}

		if (this.courseChangeCooldown-- <= 0)
		{
			this.courseChangeCooldown += this.rand.nextInt(5) + 2;
			d3 = (double) MathHelper.sqrt_double(d3);

			if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
			{
				this.motionX += d0 / d3 * 0.1D;
				this.motionY += d1 / d3 * 0.1D;
				this.motionZ += d2 / d3 * 0.1D;
			}
			else
			{
				this.waypointX = this.posX;
				this.waypointY = this.posY;
				this.waypointZ = this.posZ;
			}
		}

		/* TARGETING / TARGET FINDING */
		if (!this.isValidTarget(this.targetedEntity) || this.aggroCooldown-- <= 0)
		{
			this.targetedEntity = null;
			this.getTargetHelper().findTarget();

			if (this.targetedEntity != null)
			{
				this.aggroCooldown = 20;
			}
		}

		/* ATTACK TARGET */
		if (this.isValidTarget(this.targetedEntity))
		{
			double x = this.targetedEntity.posX - this.posX;
			double y = this.targetedEntity.boundingBox.minY + (double) (this.targetedEntity.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
			double z = this.targetedEntity.posZ - this.posZ;
			this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(x, z)) * 180.0F / (float) Math.PI;

			if (++this.attackCounter == this.getFireRate())
			{
				this.fireAtTarget(x, y, z);
				this.attackCounter = 0;
			}
		}
		else
		{
			this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;

			if (this.attackCounter > 0)
			{
				--this.attackCounter;
			}
		}

		/* UPDATE CLIENT */
		if (!this.worldObj.isRemote)
		{
			byte b0 = this.dataWatcher.getWatchableObjectByte(16);
			byte b1 = (byte) (this.attackCounter > 10 ? 1 : 0);

			if (b0 != b1)
			{
				this.dataWatcher.updateObject(16, Byte.valueOf(b1));
			}
		}
	}

	public int getFireRate()
	{
		return 40;
	}

	/**
	 * Fire the built in weapon at the target
	 * 
	 * @param x y z - taget location for arrows, fireballs, or dummy fire weapons
	 */
	public void fireAtTarget(double x, double y, double z)
	{
		if (this.targetedEntity != null)
		{
			try
			{
				System.out.println("EyeBot>>>Weapon Activation>>>At Target>>>" + this.targetedEntity.toString());
				this.getLaser().generateLaser(this.worldObj, this.targetedEntity, Color.red, 10, 5);
				// ProjectileFiringMethods.fireGhastBalls(this, this.targetedEntity,
				// this.explosionStrength, 4D, true);
			}
			catch (Exception e)
			{
				System.out.println("EyeBot >>> Weapon activation >>> critical error");
				e.printStackTrace();
			}
		}
	}

	/**
	 * True if the ghast has an unobstructed line of travel to the waypoint.
	 */
	private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
	{
		double d4 = (this.waypointX - this.posX) / par7;
		double d5 = (this.waypointY - this.posY) / par7;
		double d6 = (this.waypointZ - this.posZ) / par7;
		AxisAlignedBB axisalignedbb = this.boundingBox.copy();

		for (int i = 1; (double) i < par7; ++i)
		{
			axisalignedbb.offset(d4, d5, d6);

			if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
			{
				return false;
			}
		}

		return true;
	}

	@Override
	protected int getDropItemId()
	{
		return Block.glass.blockID;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{

	}

	@Override
	protected float getSoundVolume()
	{
		return 10.0F;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 5;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("ExplosionPower", this.explosionStrength);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("ExplosionPower"))
		{
			this.explosionStrength = par1NBTTagCompound.getInteger("ExplosionPower");
		}
	}

	@Override
	public AxisAlignedBB getTargetingBox()
	{
		return AxisAlignedBB.getBoundingBox(this.posX - this.getRange(EnumRange.MAX), this.posY - 5, this.posZ - this.getRange(EnumRange.MAX), this.posX + this.getRange(EnumRange.MAX), this.posY + 5, this.posZ + this.getRange(EnumRange.MAX));
	}

	@Override
	public Entity getTarget()
	{
		return this.targetedEntity;
	}

	@Override
	public boolean setTarget(Entity target, boolean override)
	{
		if (!this.isValidTarget(this.targetedEntity) || override)
		{
			this.targetedEntity = target;
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidTarget(Entity entity)
	{
		if (entity != null)
		{
			if (!entity.isDead && !entity.isEntityInvulnerable() && entity instanceof EntityLiving)
			{
				if (this.canEntityBeSeen(entity))
				{
					if (entity instanceof EntityPlayer || entity.riddenByEntity instanceof EntityPlayer)
					{
						EntityPlayer player;

						if (entity.riddenByEntity instanceof EntityPlayer)
						{
							player = (EntityPlayer) entity.riddenByEntity;
						}
						else
						{
							player = ((EntityPlayer) entity);
						}

						if (!player.capabilities.isCreativeMode)
						{
							if (this.getUserAccess(player.username).ordinal() >= AccessLevel.BASIC.ordinal())
							{
								return true;
							}
						}
					}
					else if (this.getUsers().size() <= 0 && !(entity instanceof EntityEyeBot))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public double getRange(EnumRange rangeType)
	{
		if (rangeType == EnumRange.MIN || rangeType == EnumRange.AIR_MIN || rangeType == EnumRange.GOUND_MIN)
		{
			return 10D;
		}
		return 100D;
	}

	@Override
	public AccessLevel getUserAccess(String username)
	{
		return AccessLevel.NONE;
	}

	@Override
	public List<UserAccess> getUsers()
	{
		return new ArrayList<UserAccess>();
	}

	@Override
	public boolean addUserAccess(String username, AccessLevel level, boolean save)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUserAccess(String username)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserAccess> getUsersWithAcess(AccessLevel level)
	{
		// TODO Auto-generated method stub
		return new ArrayList<UserAccess>();
	}

}
