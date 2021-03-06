package dark.gsm.fortress;

import java.awt.Color;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dark.core.client.FXBeam;
import dark.core.prefab.ModPrefab;
import dark.gsm.fortress.gui.GuiPlatformAccess;
import dark.gsm.fortress.gui.GuiPlatformSlots;
import dark.gsm.fortress.gui.GuiPlatformTerminal;
import dark.gsm.fortress.platform.TileEntityTurretPlatform;
import dark.gsm.fortress.render.BlockRenderingHandler;
import dark.gsm.fortress.render.RenderAATurret;
import dark.gsm.fortress.render.RenderGunTurret;
import dark.gsm.fortress.render.RenderRailgun;
import dark.gsm.fortress.turret.mount.TileEntityRailTurret;
import dark.gsm.fortress.turret.sentries.TileEntityAATurret;
import dark.gsm.fortress.turret.sentries.TileEntityGunTurret;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        super.preInit();
    }

    @Override
    public void init()
    {
        super.init();

        /** TileEntities */
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGunTurret.class, new RenderGunTurret());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAATurret.class, new RenderAATurret());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRailTurret.class, new RenderRailgun());

        RenderingRegistry.registerBlockHandler(new BlockRenderingHandler());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null)
        {
            switch (ID)
            {
                case GUI_PLATFORM_ID:
                    return new GuiPlatformSlots(player.inventory, ((TileEntityTurretPlatform) tileEntity));
                case GUI_PLATFORM_TERMINAL_ID:
                    return new GuiPlatformTerminal(player, ((TileEntityTurretPlatform) tileEntity));
                case GUI_PLATFORM_ACCESS_ID:
                    return new GuiPlatformAccess(player, ((TileEntityTurretPlatform) tileEntity));
            }
        }

        return null;
    }

    @Override
    public void renderTracer(World world, Vector3 position, Vector3 target)
    {
        if (target != null && position != null)
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FXBeam(world, position, target, Color.DARK_GRAY, ModPrefab.TEXTURE_DIRECTORY + "traceStream.png", 5, true));
    }
}
