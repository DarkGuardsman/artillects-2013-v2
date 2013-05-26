package dark.gsm.common.artillects.blocks;

import universalelectricity.core.vector.Vector3;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;

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
