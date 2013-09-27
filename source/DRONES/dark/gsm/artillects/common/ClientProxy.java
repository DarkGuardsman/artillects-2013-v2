package dark.gsm.artillects.common;

import cpw.mods.fml.client.registry.RenderingRegistry;
import dark.gsm.artillects.common.bots.EntityEyeBot;
import dark.gsm.client.renders.RenderEyeBot;
import dark.gsm.client.renders.models.ModelCubeEyeBot;

public class ClientProxy extends dark.gsm.artillects.common.CommonProxy
{

    @Override
    public void preInit()
    {
        // TODO Auto-generated method stub
        RenderingRegistry.registerEntityRenderingHandler(EntityEyeBot.class, new RenderEyeBot(new ModelCubeEyeBot(), 1));
    }

    @Override
    public void Init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void postInit()
    {
        // TODO Auto-generated method stub

    }

}
