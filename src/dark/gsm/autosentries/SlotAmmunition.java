package dark.gsm.autosentries;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import universalelectricity.prefab.SlotSpecific;
import dark.interfaces.ITerminal;
import dark.prefab.access.AccessLevel;

public class SlotAmmunition extends SlotSpecific
{
    public SlotAmmunition(IInventory par1iInventory, int par2, int par3, int par4)
    {
        super(par1iInventory, par2, par3, par4, Sentries.conventionalBullet.copy());
    }

    @Override
    public boolean canTakeStack(EntityPlayer entityPlayer)
    {
        if (this.inventory instanceof ITerminal)
        {
            return ((ITerminal) this.inventory).getUserAccess(entityPlayer.username).ordinal() > AccessLevel.NONE.ordinal();
        }

        return false;
    }
}
