package dark.gsm.common.core;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.WorldEvent.Save;
import dark.core.DarkMain;

public class GSMCore implements IMod
{
	public static boolean loaded = false;

	public static final String MAJOR_VERSION = "@MAJOR@";
	public static final String MINOR_VERSION = "@MINOR@";
	public static final String REVISION_VERSION = "@REVIS@";
	public static final String BUILD_VERSION = "@BUILD@";
	public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION;

	/* RESOURCE FILE PATHS */
	public static final String RESOURCE_PATH = "/mods/dark/";
	public static final String TEXTURE_DIRECTORY = RESOURCE_PATH + "textures/";
	public static final String GUI_PATH = TEXTURE_DIRECTORY + "gui/";
	public static final String BLOCK_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "blocks/";
	public static final String ITEM_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "items/";
	public static final String MODEL_PATH = TEXTURE_DIRECTORY + "models/";
	public static final String PREFIX = "dark:";
	public static final String LANGUAGE_PATH = RESOURCE_PATH + "languages/";

	public static final String CONFIGURATION = null;

	public static void registerMod(IMod mod)
	{
		if (!loaded)
		{
			GSMCore.loaded = true;
		}
	}

	// CreativeTab GreaterSecurity.tabGreaterSecurity 
	public static CreativeTabs tabGSMGeneral = new CreativeTabs("GSM General")
	{

		public ItemStack getIconItemStack()
		{
			return new ItemStack(Item.ingotIron, 1, 0);
		}
	};
	public static CreativeTabs tabGSMIndustrial = new CreativeTabs("GSM Machines")
	{

		public ItemStack getIconItemStack()
		{
			return new ItemStack(Item.ingotIron, 1, 0);
		}
	};
	public static CreativeTabs tabGSMCastle = new CreativeTabs("GSM Fortress")
	{

		public ItemStack getIconItemStack()
		{
			return new ItemStack(Item.ingotIron, 1, 0);
		}
	};

	public void preInit(FMLPreInitializationEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	public void init(FMLInitializationEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getVersion()
	{
		return this.VERSION;
	}

	@Override
	public String getChannel()
	{
		return "GSMMain";
	}

	@Override
	public String getName()
	{
		return "GSM";
	}

	public void worldSave(Save evt)
	{
		// TODO Auto-generated method stub
		
	}

	public void serverStarting(FMLServerStartingEvent event)
	{
		// TODO Auto-generated method stub
		
	}
}
