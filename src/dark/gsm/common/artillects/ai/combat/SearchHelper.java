package dark.gsm.common.artillects.ai.combat;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import dark.library.access.AccessLevel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class SearchHelper
{
	private IAttacker attacker;
	private World world;
	private Vector3 loct;

	public SearchHelper(World world, Vector3 location, IAttacker attack)
	{
		this.attacker = attack;
		this.world = world;
		this.loct = location;
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
		return loct;
	}

	/**
	 * Find a target for the attacking entity TODO add var for getting ground or air separate
	 */
	public void findTarget()
	{
		if (!this.attacker.isValidTarget(this.attacker.getTarget()))
		{
			if (this.attacker.getTargetingBox() != null)
			{
				List<Entity> entities = this.refineList(world, getLoc(), attacker.getTargetingBox(), attacker.getRange(EnumRange.MIN), attacker.getRange(EnumRange.MAX));
				for (Entity entity : entities)
				{
					if (this.isValidTarget(entity))
					{
						this.attacker.setTarget(entity, true);
						break;
					}
				}
			}
		}
	}

	/**
	 * Refine the list of all targets with in range to a smaller group
	 * 
	 * @param world - world searching
	 * @param location - location to do distance calculations from
	 * @param bounds - search area
	 * @param min - min range
	 * @param max - max range
	 * @return list of valid entities for targeting or more refinement
	 */
	public List<Entity> refineList(World world, Vector3 location, AxisAlignedBB bounds, double min, double max)
	{
		List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bounds);
		SearchHelper.sortClosest(entities, location);
		Iterator<Entity> iterator = entities.iterator();

		while (iterator.hasNext())
		{
			Entity entity = iterator.next();
			double distance = entity.getDistance(location.intX(), location.intY(), location.intZ());

			if (!this.isValidTarget(entity) || distance < min)
			{
				iterator.remove();
			}
		}
		return entities;
	}

	public static void sortClosest(final List<Entity> entities, final Vector3 vec)
	{
		Collections.sort(entities, new Comparator()
		{
			@Override
			public int compare(Object o1, Object o2)
			{
				if (o1 == o2)
				{
					return 0;
				}
				Vector3 a = new Vector3((Entity) o1);
				Vector3 b = new Vector3((Entity) o2);
				double da = Vector3.distance(a, vec);
				double db = Vector3.distance(b, vec);
				return Double.compare(da, db);
			}
		});
	}

	public boolean isValidTarget(Entity entity)
	{
		if (entity == null || entity.isDead || !entity.isEntityAlive() || entity.isEntityInvulnerable())
		{
			return false;
		}

		if (entity instanceof EntityPlayer)
		{
			if (((EntityPlayer) entity).capabilities.isCreativeMode)
			{
				return false;
			}
		}
		return this.attacker.isValidTarget(entity);
	}

}
