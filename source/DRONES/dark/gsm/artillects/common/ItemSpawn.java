package dark.gsm.artillects.common;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import dark.gsm.artillects.common.bots.EntityShoeBot;

public class ItemSpawn extends Item
{

    public ItemSpawn(int id)
    {
        super(id);
        maxStackSize = 1;
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public void addCreativeItems(ArrayList itemList)
    {

        itemList.add(new ItemStack(this, 1, 1));

    }

    public String getItemNameIS(ItemStack itemstack)
    {
        switch (itemstack.getItemDamage())
        {
            case 1:
                return "Bot";
        }

        return "Blank";
    }

    public boolean tryPlaceIntoWorld(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10)
    {

        if (!world.isRemote)
        {

            i += Facing.offsetsXForSide[l];
            j += Facing.offsetsYForSide[l];
            k += Facing.offsetsZForSide[l];

            EntityShoeBot Guard = new EntityShoeBot(world);
            Guard.setLocationAndAngles(i + 0.5D, j + 1.0D, k + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(Guard);

            entityplayer.swingItem();
            --itemstack.stackSize;
        }

        return true;
    }
}
