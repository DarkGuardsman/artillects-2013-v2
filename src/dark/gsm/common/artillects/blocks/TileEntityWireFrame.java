package dark.gsm.common.artillects.blocks;

import icbm.api.IMissile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.tile.TileEntityAdvanced;
import dark.gsm.common.artillects.ai.combat.EnumRange;
import dark.gsm.common.artillects.ai.combat.IAttacker;
import dark.gsm.common.artillects.ai.combat.LaserHelper;
import dark.gsm.common.artillects.ai.combat.SearchHelper;
import dark.gsm.common.artillects.bots.EntityEyeBot;
import dark.library.access.AccessLevel;
import dark.library.orbit.NetworkOrbit;

public class TileEntityWireFrame extends TileEntityAdvanced implements IAttacker, IBotController
{
	Vector3 rotation = new Vector3(0, 0, 0);
	LaserHelper laser = new LaserHelper(this);
	List<EntityEyeBot> botList = new ArrayList<EntityEyeBot>();

	private Entity targetedEntity = null;
	SearchHelper targetFinder;

	/** Cooldown time between target loss and new target. */
	private int aggroCooldown = 0;
	public int prevAttackCounter = 0;
	public int attackCounter = 0;

	@Override
	public void invalidate()
	{
		for (EntityEyeBot bot : botList)
		{
			bot.setControler(null);
		}
		super.invalidate();
	}

	public SearchHelper getTargetHelper()
	{
		if (this.targetFinder == null)
		{
			this.targetFinder = new SearchHelper(this.worldObj, new Vector3(this), this);
		}
		return this.targetFinder;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.ticks % 1 == 0)
		{
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
				double x = this.targetedEntity.posX - this.xCoord;
				double y = this.targetedEntity.boundingBox.minY + (double) (this.targetedEntity.height / 2.0F) - (this.yCoord + 0.5);
				double z = this.targetedEntity.posZ - this.zCoord;
				this.rotation.y = this.getYaw(new Vector3(this), new Vector3(this.targetedEntity));
				this.rotation.z = -this.getPitch(new Vector3(this), new Vector3(this.targetedEntity));
				if (++this.attackCounter == 20)
				{
					if (this.targetedEntity != null)
					{
						this.laser.generateLaser(this.worldObj, this.targetedEntity, Color.red, 10, 5);
					}
					this.attackCounter = 0;
				}
			}
			else
			{
				this.rotation.z = 0;
				this.rotation.y = 0;
			}

			AxisAlignedBB bound = AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1);
			bound = bound.copy().expand(100, 100, 100);
			List<EntityEyeBot> botListNew = this.worldObj.getEntitiesWithinAABB(EntityEyeBot.class, bound);
			for (EntityEyeBot bot : botListNew)
			{
				if (!botList.contains(bot) && this.botList.size() < 10)
				{
					botList.add(bot);
				}
			}
			/*
			 * This is the code used to test the rotation by generating a circle with the radius
			 * given
			 */

			Iterator<EntityEyeBot> it = this.botList.iterator();
			while (it.hasNext())
			{
				EntityEyeBot bot = it.next();
				if (bot == null || bot.isDead)
				{
					it.remove();
				}
				else
				{
					bot.setControler(this);
				}

			}

			double r = (2 * botList.size()) / (2 * Math.PI);
			double s = (2 * Math.PI) / this.botList.size();
			Vector3 vec = new Vector3(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D);

			Vector3 prevPoint = null;
			for (int i = 0; i < botList.size(); i++)
			{
				Vector3 point = vec.clone().add(NetworkOrbit.getOrbitOffset(r, s * i, this.rotation));
				this.botList.get(i).setPosition(point.x, point.y, point.z);
				if (this.worldObj.isRemote)
				{
					this.laser.generateLaser(this.worldObj, vec, new Vector3(this.botList.get(i)), Color.red, 0, 1);
				}
				if (i != 0)
				{
					int a = i + 1 == this.botList.size() ? 0 : i - 1;
					int b = i;
					if (this.worldObj.isRemote)
					{
						this.laser.generateLaser(this.worldObj, prevPoint, point, Color.cyan, 0, 1);
					}
				}
				prevPoint = point.clone();

			}
			if (this.worldObj.isRemote)
			{
				this.laser.generateLaser(this.worldObj, vec, vec.clone().add(new Vector3(0, -2, 0)), Color.darkGray, 0, 1);
			}
		}

	}

	@Override
	public AxisAlignedBB getTargetingBox()
	{
		return AxisAlignedBB.getBoundingBox(this.xCoord - 30, this.yCoord - 30, this.zCoord - 30, this.xCoord + 30, this.yCoord + 30, this.zCoord + 30);
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
			if (!entity.isDead && !entity.isEntityInvulnerable() && (entity instanceof EntityLiving || entity instanceof IMissile))
			{
				if (this.canEntityBeSeen(new Vector3(entity)))
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
							return true;
						}
					}
					else if (!(entity instanceof EntityEyeBot))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean canEntityBeSeen(Vector3 target)
	{
		return this.worldObj.rayTraceBlocks(new Vector3(this).toVec3(), target.toVec3()) == null;
	}

	public static float getPitch(Vector3 position, Vector3 target)
	{
		Vector3 difference = Vector3.subtract(target, position);
		double verticleDistance = MathHelper.sqrt_double(difference.x * difference.x + difference.z * difference.z);
		return -MathHelper.wrapAngleTo180_float((float) (Math.atan2(difference.y, verticleDistance) * 180.0D / Math.PI));
	}

	/**
	 * Gets the rotation yaw between the two points in angles
	 */
	public static float getYaw(Vector3 position, Vector3 target)
	{
		Vector3 difference = Vector3.subtract(target, position);
		return MathHelper.wrapAngleTo180_float((float) (Math.atan2(difference.z, difference.x) * 180.0D / Math.PI) - 90.0F);
	}

	@Override
	public double getRange(EnumRange rangeType)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isConnected(Object bot)
	{
		return bot instanceof EntityEyeBot && this.botList.contains(bot);
	}
}
