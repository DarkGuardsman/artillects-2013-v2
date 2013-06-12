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
	}
	
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityWireFrame();
	}

}
