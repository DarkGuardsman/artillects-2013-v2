package dark.gsm.common.artillects.blocks;

import java.awt.Color;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import dark.gsm.common.artillects.ai.combat.EnumRange;
import dark.gsm.common.artillects.ai.combat.IAttacker;
import dark.gsm.common.artillects.ai.combat.LaserHelper;
import dark.library.orbit.NetworkOrbit;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.tile.TileEntityAdvanced;

public class TileEntityWireFrame extends TileEntityAdvanced implements IAttacker
{
	Vector3[] points = new Vector3[20];
	Vector3 rotation = new Vector3(0, 0, 0);
	LaserHelper laser = new LaserHelper(this);

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.worldObj.isRemote && this.ticks % 5 == 0)
		{
			/*
			 * This is the code used to test the rotation by generating a circle with the radius
			 * given
			 */
			double r = 3;
			double s = (2 * Math.PI) / this.points.length;
			Vector3 vec = new Vector3(this.xCoord + 0.5D, this.yCoord + 2D, this.zCoord + 0.5D);
			boolean flip = false;
			/*
			 * Rotation speed is set here though it could be control by anything. As well the
			 * rotation change is stored in a vector(x,y,z) and doesn't only have to be in the z axis
			 */
			this.rotation.z += 5;
			for (int i = 0; i < points.length; i++)
			{
				Color color = flip ? Color.red : Color.blue;
				Vector3 point = vec.clone().add(NetworkOrbit.getOrbitOffset(r, s * i, this.rotation));
				points[i] = point.clone();
				if (i != 0)
				{
					int a = i + 1 == this.points.length ? 0 : i - 1;
					int b = i;
					/*
					 * generatelaser creates a laser line from one point to another and is used for
					 * visual debug
					 */
					this.laser.generateLaser(this.worldObj, points[a], points[b], color, 10, 5);
				}
				flip = !flip;
			}
			this.laser.generateLaser(this.worldObj, points[this.points.length - 1], points[this.points.length - 2], flip ? Color.red : Color.blue, 10, 5);
			//Markers to show me the center point and then a  line to point 0
			this.laser.generateLaser(this.worldObj, vec, vec.clone().add(new Vector3(0, -2, 0)), Color.darkGray, 10, 5);
			this.laser.generateLaser(this.worldObj, points[0], vec, Color.darkGray, 10, 5);
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
}
