package dark.gsm.common.core;

import icbm.api.ICBM;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.world.WorldEvent.Save;
import universalelectricity.prefab.multiblock.BlockMulti;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

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

	public static final Configuration gsmCoreConfig = new Configuration(new File(Loader.instance().getConfigDir(), "dark/DarkMain.cfg"));

	public static boolean preInit = false;
	public static boolean init = false;
	public static boolean postInit = false;

	public static Block multiBlock;

	public static void registerMod(IMod mod)
	{
		if (!loaded)
		{
			GSMCore.loaded = true;
		}
	}

	/** Creative tab for generic items and block that don't belong in any other tab */
	public static CreativeTabs tabGSMGeneral = new CreativeTabs("GSM General")
	{

		public ItemStack getIconItemStack()
		{
			return new ItemStack(Item.ingotIron, 1, 0);
		}
	};
	/** Creative tab for High tech and industrial based items and blocks that don't belong in any
	 * other tab */
	public static CreativeTabs tabGSMIndustrial = new CreativeTabs("GSM Machines")
	{

		public ItemStack getIconItemStack()
		{
			return new ItemStack(Item.ingotIron, 1, 0);
		}
	};
	/** Creative tab for general castle and fortress related items and blocks that don't belong in
	 * any other tab */
	public static CreativeTabs tabGSMCastle = new CreativeTabs("GSM Fortress")
	{

		public ItemStack getIconItemStack()
		{
			return new ItemStack(Item.ingotIron, 1, 0);
		}
	};

	public void preInit(FMLPreInitializationEvent event)
	{
		if (!preInit)
		{
			gsmCoreConfig.load();
			multiBlock = new BlockMulti(gsmCoreConfig.getBlock("Multiblock", ICBM.BLOCK_ID_PREFIX + 6).getInt()).setTextureName(PREFIX + "machine").setChannel(this.getChannel());
			if (gsmCoreConfig.hasChanged())
			{
				gsmCoreConfig.save();
			}
			preInit = true;
		}

	}

	public void init(FMLInitializationEvent event)
	{
		if (!init)
		{
			init = true;
		}

	}

	public void postInit(FMLPostInitializationEvent event)
	{
		if (!postInit)
		{
			postInit = true;
		}

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
