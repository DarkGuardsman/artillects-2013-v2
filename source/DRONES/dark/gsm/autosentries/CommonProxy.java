package dark.gsm.autosentries;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.core.prefab.machine.TileEntityMulti;
import dark.gsm.autosentries.container.ContainerTurretPlatform;
import dark.gsm.autosentries.platform.TileEntityTurretPlatform;
import dark.gsm.autosentries.turret.mount.TileEntityRailTurret;
import dark.gsm.autosentries.turret.sentries.TileEntityAATurret;
import dark.gsm.autosentries.turret.sentries.TileEntityGunTurret;

public class CommonProxy implements IGuiHandler
{
    /** GUI IDs */
    public static final int GUI_PLATFORM_ID = 0;
    public static final int GUI_PLATFORM_TERMINAL_ID = 1;
    public static final int GUI_PLATFORM_ACCESS_ID = 2;

    public void init()
    {
        GameRegistry.registerTileEntity(TileEntityGunTurret.class, "ICBMGunTurret");
        GameRegistry.registerTileEntity(TileEntityAATurret.class, "ICBMAATurret");
        GameRegistry.registerTileEntity(TileEntityRailTurret.class, "ICBMRailgun");
        GameRegistry.registerTileEntity(TileEntityTurretPlatform.class, "ICBMPlatform");
        GameRegistry.registerTileEntity(TileEntityMulti.class, "ICBMMultiblock");
    }

    public void preInit()
    {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null)
        {
            switch (ID)
            {
                case GUI_PLATFORM_ID:
                    return new ContainerTurretPlatform(player.inventory, ((TileEntityTurretPlatform) tileEntity));
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    /** Renders a bullet tracer from one spot to another will later be replaced with start and degree */
    public void renderTracer(World world, Vector3 position, Vector3 target)
    {

    }
}
