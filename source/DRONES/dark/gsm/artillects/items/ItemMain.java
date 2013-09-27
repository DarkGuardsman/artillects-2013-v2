package dark.gsm.artillects.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import dark.gsm.artillects.DarkBotMain;

public class ItemMain extends Item
{
    public ItemMain(int par, String name, CreativeTabs tab)
    {
        super(DarkBotMain.config.getItem(name, par).getInt());
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
        this.setTextureName(DarkBotMain.PREFIX + name);
    }

}
