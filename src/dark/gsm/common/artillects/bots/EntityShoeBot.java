package dark.gsm.common.artillects.bots;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;

public class EntityShoeBot extends EntityRobot
{

	EntityItem targetItem = null;

	public EntityShoeBot(World par1World)
	{
		super(par1World);
		this.setSize(0.6F, 0.2F);
		this.moveSpeed = 0.23F;
		this.texture = "/mobs/char.png";
	}

	@Override
	public int getMaxHealth()
	{
		// TODO Auto-generated method stub
		return 5;
	}

	public String getRenderedName()
	{
		return "Harvester Bot";
	}

	public String getTaskType()
	{
		return "harvest";

	}

	@Override
	public int getRunningWatts()
	{
		return 0;
	}

	@Override
	public void runningUpdate()
	{
		// TODO Auto-generated method stub

	}
}
