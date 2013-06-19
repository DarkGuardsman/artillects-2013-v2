package dark.gsm.common.artillects.blocks;

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
		this.setBlockBounds(0.4f, 0, 0.6f, 0, .1f, 0);
	}

	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityWireFrame();
	}

}
