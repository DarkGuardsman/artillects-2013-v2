package dark.gsm.core.common;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.Configuration;
import universalelectricity.prefab.block.BlockTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicBlock extends BlockTile
{
	protected Icon iconTop, iconSide, iconBottom;
	protected boolean requireSidedTextures = false;

	public BasicBlock(int id, String name, Material material, Configuration config)
	{
		super(config.getBlock(name, id).getInt(id), material);
		this.setUnlocalizedName(GSMCore.PREFIX + name);
		this.setCreativeTab(GSMCore.tabGSMGeneral);
	}

	@Override
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		super.registerIcons(iconRegister);

		if (this.requireSidedTextures)
		{
			this.iconTop = iconRegister.registerIcon(this.getUnlocalizedName() + "_top");
			this.iconSide = iconRegister.registerIcon(this.getUnlocalizedName() + "_side");
			this.iconBottom = iconRegister.registerIcon(this.getUnlocalizedName() + "_bottom");
		}
	}
}
