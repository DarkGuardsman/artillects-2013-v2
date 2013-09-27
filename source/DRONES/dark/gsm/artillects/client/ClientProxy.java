package dark.gsm.artillects.client;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dark.gsm.artillects.CommonProxy;
import dark.gsm.artillects.client.renders.RenderBossGigus;
import dark.gsm.artillects.client.renders.RenderCore;
import dark.gsm.artillects.client.renders.RenderDefenderTwo;
import dark.gsm.artillects.client.renders.RenderMissile;
import dark.gsm.artillects.entity.EntityBombMissile;
import dark.gsm.artillects.entity.EntityBossGigus;
import dark.gsm.artillects.entity.EntityDefender;
import dark.gsm.artillects.hive.spire.TileEntitySpireCore;

public class ClientProxy extends CommonProxy
{

    @Override
    public void init()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpireCore.class, new RenderCore());

        RenderingRegistry.registerEntityRenderingHandler(EntityDefender.class, new RenderDefenderTwo());
        RenderingRegistry.registerEntityRenderingHandler(EntityBombMissile.class, new RenderMissile());
        RenderingRegistry.registerEntityRenderingHandler(EntityBossGigus.class, new RenderBossGigus());

        MinecraftForge.EVENT_BUS.register(SoundHandler.INSTANCE);
    }
}
