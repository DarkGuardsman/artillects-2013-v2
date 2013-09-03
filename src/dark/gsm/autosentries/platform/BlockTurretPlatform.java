package dark.gsm.autosentries.platform;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import dark.core.interfaces.ISpecialAccess;
import dark.core.prefab.access.AccessLevel;
import dark.gsm.autosentries.CommonProxy;
import dark.gsm.autosentries.Sentries;
import dark.gsm.autosentries.api.IAmmo;
import dark.gsm.core.common.BasicBlock;
import dark.gsm.core.common.GSMCore;

public class BlockTurretPlatform extends BasicBlock
{
    public BlockTurretPlatform(int id, Configuration config)
    {
        super(id, "turretPlatform", Material.iron, config);
        this.setHardness(100f);
        this.setResistance(50f);
        this.setCreativeTab(GSMCore.tabGSMIndustrial);
        this.requireSidedTextures = true;
    }

    @Override
    public Icon getIcon(int side, int metadata)
    {
        return side == 0 ? this.iconBottom : (side == 1 ? this.iconTop : this.iconSide);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        if (entity instanceof EntityPlayer && !world.isRemote)
        {
            TileEntity ent = world.getBlockTileEntity(x, y, z);

            if (ent instanceof ISpecialAccess)
            {
                ((ISpecialAccess) ent).addUserAccess(((EntityPlayer) entity).username, AccessLevel.OWNER, true);
            }
        }
    }

    @Override
    public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        /** Only allow the platform to be open if there is a turret installed with it. */
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityTurretPlatform)
        {
            if (((TileEntityTurretPlatform) tileEntity).onPlayerActivated(player))
            {
                return true;
            }
            if (player.getCurrentEquippedItem() != null)
            {
                if (side == ((TileEntityTurretPlatform) tileEntity).deployDirection.ordinal() && player.getCurrentEquippedItem().itemID == Sentries.blockTurret.blockID)
                {
                    return false;
                }

            }

            if (((TileEntityTurretPlatform) tileEntity).getTurret(false) != null)
            {
                if (!world.isRemote)
                {
                    player.openGui(Sentries.instance, CommonProxy.GUI_PLATFORM_ID, world, x, y, z);
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityTurretPlatform();
    }

    @Override
    public void dropEntireInventory(World world, int x, int y, int z, int par5, int par6)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null)
        {
            if (tileEntity instanceof IInventory)
            {
                IInventory inventory = (IInventory) tileEntity;

                for (int slot = 0; slot < inventory.getSizeInventory(); ++slot)
                {
                    ItemStack itemStack = inventory.getStackInSlot(slot);

                    if (itemStack != null)
                    {
                        boolean flag = true;
                        Item item = itemStack.getItem();
                        if (item instanceof IAmmo)
                        {
                            if (((IAmmo) item).canDrop(itemStack.getItemDamage()))
                            {
                                flag = true;
                                itemStack = ((IAmmo) item).onDroppedIntoWorld(itemStack.copy());
                            }
                            else
                            {
                                flag = false;
                            }
                        }
                        if (flag)
                        {
                            Random random = new Random();
                            float var8 = random.nextFloat() * 0.8F + 0.1F;
                            float var9 = random.nextFloat() * 0.8F + 0.1F;
                            float var10 = random.nextFloat() * 0.8F + 0.1F;

                            while (itemStack.stackSize > 0)
                            {
                                int var11 = random.nextInt(21) + 10;

                                if (var11 > itemStack.stackSize)
                                {
                                    var11 = itemStack.stackSize;
                                }

                                itemStack.stackSize -= var11;
                                EntityItem var12 = new EntityItem(world, (x + var8), (y + var9), (z + var10), new ItemStack(itemStack.itemID, var11, itemStack.getItemDamage()));

                                if (itemStack.hasTagCompound())
                                {
                                    var12.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                                }

                                float var13 = 0.05F;
                                var12.motionX = ((float) random.nextGaussian() * var13);
                                var12.motionY = ((float) random.nextGaussian() * var13 + 0.2F);
                                var12.motionZ = ((float) random.nextGaussian() * var13);
                                world.spawnEntityInWorld(var12);
                            }
                        }
                    }
                }
            }
        }
    }
}
