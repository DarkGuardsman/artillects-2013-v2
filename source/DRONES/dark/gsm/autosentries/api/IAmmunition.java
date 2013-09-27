package dark.gsm.autosentries.api;

import net.minecraft.item.ItemStack;

/** Applied to all TileEntities that can hold ammunition. * */
public interface IAmmunition
{
    /** Checks if the platform has the specified ammunition.
     * 
     * @param itemStack
     * @return */
    public AmmoPair<IAmmo, ItemStack> hasAmmunition(ProjectileTypes ammunitionStack);

    /** Uses a specific type ammunition from the ammunition pool.
     * 
     * @param ammunitionStack - The type of ammunition to use.
     * @return True if used successfully. */
    public boolean useAmmunition(AmmoPair<IAmmo, ItemStack> ammo);

}
