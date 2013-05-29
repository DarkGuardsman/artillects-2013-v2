package dark.gsm.common.artillects.ai.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.library.DarkMain;
import dark.library.effects.FXBeam;

public class LaserHelper
{
	IAttacker attacker;

	public LaserHelper(IAttacker attacker)
	{
		this.attacker = attacker;
	}

	/**
	 * Creates a laser that generates client side and damages server side
	 * 
	 * @param world - world
	 * @param start - starting point of the laser
	 * @param end - ending point of the laser
	 * @param color - color of the laser
	 * @param damage - primary damage of the laser 25% damage lose from center per pixel
	 * @param time - time the laser lasts, loses damage near end of life span
	 */
	public void generateLaser(World world, Vector3 start, Vector3 end, Color color, int damage, int time)
	{
		if (world.isRemote)
		{
			this.renderLaser(world, start, end, color, time);
		}
		else
		{
			this.generateDamageField(world, start, end, damage, time);
		}
	}

	public void generateDamageField(World world, Vector3 start, Vector3 end, int damage, int time)
	{
		if (!world.isRemote)
		{
			List<Entity> effectedTargets = this.getEntitiesInPath(world, start, end);
			for (Entity entity : effectedTargets)
			{
				entity.attackEntityFrom(DamageSource.inFire, (int) (15 - (15 * Math.max(this.isPointOnLine(start, end, new Vector3(entity)), 0))));
				entity.setFire(5);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void renderLaser(World world, Vector3 start, Vector3 end, Color color, int time)
	{
		if (world.isRemote)
		{
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FXBeam(world, start, end, color, DarkMain.TEXTURE_DIRECTORY + "", time));
		}
	}

	public List<Entity> getEntitiesInPath(World world, Vector3 start, Vector3 end)
	{
		List<Entity> entities = new ArrayList<Entity>();
		if (start != null && end != null)
		{
			AxisAlignedBB bound = AxisAlignedBB.getBoundingBox(start.x > end.x ? end.x : start.x, start.y > end.y ? end.y : start.y, start.z > end.z ? end.z : start.z, start.x < end.x ? end.x : start.x, start.y < end.y ? end.y : start.y, start.z < end.z ? end.z : start.z);

			List<Entity> list = world.getEntitiesWithinAABB(Entity.class, bound);
			Iterator it = list.iterator();
			if (it.hasNext())
			{
				Entity entity = (Entity) it.next();
				if ((this.isPointOnLine(start, end, new Vector3(entity)) > (entity.width > 0.2 ? entity.width : 0.2)) || !this.isValidTarget(entity))
				{
					it.remove();
				}
			}

		}

		return entities;
	}

	private boolean isValidTarget(Entity entity)
	{
		if (entity.isDead || !entity.isEntityAlive() || entity.isEntityInvulnerable())
		{
			return false;
		}
		return true;
	}

	public double isPointOnLine(Vector3 start, Vector3 end, Vector3 point)
	{
		double a = (end.y - start.y) / (end.x - start.x);
		double b = start.y - a * start.x;
		return Math.abs(point.y - (a * point.x + b));
	}
}
