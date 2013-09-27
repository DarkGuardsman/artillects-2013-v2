package dark.drone.client;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dark.drone.CommonProxy;
import dark.drone.client.renders.RenderBossGigus;
import dark.drone.client.renders.RenderCore;
import dark.drone.client.renders.RenderDefenderTwo;
import dark.drone.client.renders.RenderMissile;
import dark.drone.entity.EntityBombMissile;
import dark.drone.entity.EntityBossGigus;
import dark.drone.entity.EntityDefender;
import dark.drone.hive.spire.TileEntitySpireCore;

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
