package dark.gsm.common.artillects.ai.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;
import cpw.mods.fml.client.FMLClientHandler;
import dark.core.DarkMain;
import dark.gsm.common.artillects.GSMMachines;
import dark.gsm.common.artillects.PacketHandler;
import dark.library.effects.FXBeam;

public class LaserHelper
{
	IAttacker attacker;

	public LaserHelper(IAttacker attacker)
	{
		this.attacker = attacker;
	}

	public Vector3 getLoc()
	{
		if (attacker instanceof TileEntity)
		{
			return new Vector3((TileEntity) attacker);
		}
		if (attacker instanceof Entity)
		{
			return new Vector3((Entity) attacker);
		}
		return null;
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
		if (!world.isRemote)
		{
			Packet packet = PacketHandler.getPacketWithID(GSMMachines.CHANNEL, PacketHandler.PacketType.EFFECTS.ordinal(), 0, start.x, start.y, start.z, end.x, end.y, end.z, color.getRed(), color.getBlue(), color.getGreen(), time);
			PacketHandler.sendPacketToClients(packet, world, start, 64);
			if (damage > 0)
			{
				this.generateDamageField(world, start, end, damage);
			}
		}
		else
		{
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FXBeam(FMLClientHandler.instance().getClient().thePlayer.worldObj, start, end, color, DarkMain.TEXTURE_DIRECTORY + "", time));

		}
	}

	public void generateLaser(World world, Entity entity, Color color, int damage, int time)
	{
		if (damage > 0)
		{
			entity.attackEntityFrom(DamageSource.inFire, damage);
			entity.setFire(5);
		}
		this.generateLaser(world, this.getLoc(), new Vector3(entity).add(new Vector3(0, entity.getEyeHeight() / 2, 0)), color, damage, time);
	}

	public void generateDamageField(World world, Vector3 start, Vector3 end, int damage)
	{
		if (!world.isRemote)
		{
			// System.out.println("LaserHelper>>>Doing Damage to Path");
			List<Entity> effectedTargets = this.getEntitiesInPath(world, start, end);
			for (Entity entity : effectedTargets)
			{
				// System.out.println("LaserHelper>>>Doing Damage>>To Entity>>>" +
				// entity.toString());
				entity.attackEntityFrom(DamageSource.inFire, (int) (damage - (damage * Math.max(this.isPointOnLine(start, end, new Vector3(entity)), 0))));
				entity.setFire(5);
			}
		}
	}

	public List<Entity> getEntitiesInPath(World world, Vector3 start, Vector3 end)
	{
		List<Entity> entities = new ArrayList<Entity>();
		if (start != null && end != null)
		{
			AxisAlignedBB bound = AxisAlignedBB.getBoundingBox(start.x > end.x ? end.x - 1 : start.x - 1, start.y > end.y ? end.y - 1 : start.y - 1, start.z > end.z ? end.z - 1 : start.z - 1, start.x < end.x ? end.x + 1 : start.x + 1, start.y < end.y ? end.y + 1 : start.y + 1, start.z < end.z ? end.z + 1 : start.z + 1);

			List<Entity> list = world.getEntitiesWithinAABB(Entity.class, bound);
			Iterator<Entity> it = list.iterator();
			if (it.hasNext())
			{
				Entity entity = (Entity) it.next();
				if ((this.attacker instanceof Entity && entity == this.attacker) || (this.isPointOnLine(start, end, new Vector3(entity)) > (entity.width > 0.2 ? entity.width : 0.2)) || !this.isValidTarget(entity))
				{
					it.remove();
				}
			}

		}

		return entities;
	}

	/**
	 * Check to make sure that the entity is valid for being damaged by the laser
	 * 
	 * @param entity - any entity that extends the Entity.class
	 * @return true if it can apply damage
	 */
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
