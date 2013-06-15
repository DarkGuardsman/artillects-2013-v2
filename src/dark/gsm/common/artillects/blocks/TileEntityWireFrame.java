package dark.gsm.common.artillects.blocks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.tile.TileEntityAdvanced;
import dark.gsm.common.artillects.ai.combat.EnumRange;
import dark.gsm.common.artillects.ai.combat.IAttacker;
import dark.gsm.common.artillects.ai.combat.LaserHelper;
import dark.gsm.common.artillects.bots.EntityEyeBot;
import dark.library.orbit.NetworkOrbit;

public class TileEntityWireFrame extends TileEntityAdvanced implements IAttacker, IBotController
{
	Vector3 rotation = new Vector3(0, 0, 0);
	LaserHelper laser = new LaserHelper(this);
	List<EntityEyeBot> botList = new ArrayList<EntityEyeBot>();

	@Override
	public void invalidate()
	{
		for (EntityEyeBot bot : botList)
		{
			bot.setControler(null);
		}
		super.invalidate();
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.ticks % 1 == 0)
		{
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

			double r = (3 * botList.size()) / (2 * Math.PI);
			double s = (2 * Math.PI) / this.botList.size();
			Vector3 vec = new Vector3(this.xCoord + 0.5D, this.yCoord + 2D, this.zCoord + 0.5D);

			this.rotation.z += 5;
			this.rotation.x += 5;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getTarget()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setTarget(Entity target, boolean override)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidTarget(Entity entity)
	{
		// TODO Auto-generated method stub
		return false;
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
