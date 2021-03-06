package dark.gsm.artillects.hive.spire;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import dark.gsm.artillects.client.renders.RenderCore;
import dark.gsm.artillects.prefab.BlockMain;
import dark.gsm.artillects.prefab.Pos;
import dark.gsm.artillects.tiles.BlockDecor;

public class BlockSpireCore extends BlockMain
{

    public BlockSpireCore(int id)
    {
        super(id, "SpireCore", Material.iron);
        this.setHardness(1000);
        this.setResistance(100000);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockBounds(0, 0, 0, 1, 0.2f, 1);

    }

    @Override
    public Icon getIcon(int side, int meta)
    {
        if (BlockDecor.icons[meta] != null)
        {
            return BlockDecor.icons[meta];
        }
        return this.machineSide;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!player.isSneaking())
        {
            float change = 0f;
            ItemStack stack = player.getHeldItem();
            if (stack != null)
            {
                if (stack.getItem().itemID == Item.stick.itemID)
                {
                    change = 0.01f;
                }
                else if (stack.getItem().itemID == Item.arrow.itemID)
                {
                    change = 0.1f;
                }
                else if (stack.getItem().itemID == Block.dirt.blockID)
                {
                    TileEntity entity = world.getBlockTileEntity(x, y, z);
                    if (entity instanceof TileEntitySpireCore)
                    {
                        ((TileEntitySpireCore) entity).getSpire().scanArea();
                    }
                }
            }
            if (change != 0f)
            {
                Pos pos = new Pos();
                pos.modifyBy(ForgeDirection.getOrientation(side));
                RenderCore.xChange += (float) pos.xx * change;
                RenderCore.yChange += (float) pos.yy * change;
                RenderCore.zChange += (float) pos.zz * change;
                System.out.println(RenderCore.xChange + "x " + RenderCore.yChange + "y " + RenderCore.zChange + "z ");
            }
            else
            {
                //TODO send chat to the player saying what the core is
            }
        }
        else
        {
            RenderCore.xChange = 0;
            RenderCore.yChange = 0;
            RenderCore.zChange = 0;
        }
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        TileEntity entity = world.getBlockTileEntity(x, y, z);
        if (entity instanceof TileEntitySpireCore)
        {

        }
        super.breakBlock(world, x, y, z, par5, par6);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        if (metadata == 0)
        {
            return new TileEntitySpireCore();
        }
        else
        {
            return null;
        }
    }

}
