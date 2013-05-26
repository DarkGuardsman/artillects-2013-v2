package dark.gsm.common.artillects;

import cpw.mods.fml.client.registry.RenderingRegistry;
import dark.gsm.client.renders.RenderEyeBot;
import dark.gsm.client.renders.models.ModelCubeEyeBot;
import dark.gsm.common.artillects.bots.EntityEyeBot;

public class ClientProxy extends dark.gsm.common.artillects.CommonProxy
{

	public void preInit()
	{
		// TODO Auto-generated method stub
		RenderingRegistry.registerEntityRenderingHandler(EntityEyeBot.class, new RenderEyeBot(new ModelCubeEyeBot(), 1));
	}

	public void Init()
	{
		// TODO Auto-generated method stub

	}

	public void postInit()
	{
		// TODO Auto-generated method stub

	}

}
