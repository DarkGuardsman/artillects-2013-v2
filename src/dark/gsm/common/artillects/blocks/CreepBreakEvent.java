package dark.gsm.common.artillects.blocks;

import net.minecraft.world.World;
import net.minecraftforge.event.Event;
import universalelectricity.core.vector.Vector3;

public class CreepBreakEvent extends Event
{
	public World world;
	public Vector3 location;

	public CreepBreakEvent(World world, Vector3 loc)
	{
		this.world = world;
		this.location = loc;
	}
}
