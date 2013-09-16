package dark.gsm.artillects.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockAdvanced;

public class BlockWireRotation extends BlockAdvanced
{

    public BlockWireRotation(int id)
    {
        super(id, Material.iron);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setUnlocalizedName("DroneController");
        this.setBlockBounds(0, 0, 0, 1, 1, 1);
    }

    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityWireFrame();
    }

}
