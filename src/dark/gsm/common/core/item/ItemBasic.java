package dark.gsm.common.core.item;

import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraftforge.common.Configuration;
import dark.gsm.common.core.GSMCore;

public class ItemBasic extends Item
{
	public static final Icon[] ICONS = new Icon[256];

	public ItemBasic(int itemID, String name, Configuration config)
	{
		super(config.getItem(name, itemID).getInt());
		this.setUnlocalizedName(GSMCore.PREFIX + name);
	}

}
