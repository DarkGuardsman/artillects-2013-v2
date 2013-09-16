package dark.gsm.core.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.world.WorldEvent.Save;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class GSMCore
{
    public static boolean loaded = false;

    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";

    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION;
    public static final String DOMAIN = "dark";
    public static final String PREFIX = DOMAIN + ":";

    /* RESOURCE FILE PATHS */
    public static final String DIRECTORY_NO_SLASH = "assets/" + DOMAIN + "/";
    public static final String DIRECTORY = "/" + DIRECTORY_NO_SLASH;
    public static final String LANGUAGE_PATH = DIRECTORY + "languages/";
    public static final String SOUND_PATH = DIRECTORY + "audio/";

    public static final String TEXTURE_DIRECTORY = "textures/";
    public static final String BLOCK_DIRECTORY = TEXTURE_DIRECTORY + "blocks/";
    public static final String ITEM_DIRECTORY = TEXTURE_DIRECTORY + "items/";
    public static final String MODEL_DIRECTORY = TEXTURE_DIRECTORY + "models/";
    public static final String GUI_DIRECTORY = TEXTURE_DIRECTORY + "gui/";

    public static final Configuration gsmCoreConfig = new Configuration(new File(Loader.instance().getConfigDir(), "dark/DarkMain.cfg"));

    public static final String NAME = "GSM";

    public static boolean preInit = false;
    public static boolean init = false;
    public static boolean postInit = false;

    /* START IDS */
    public static int BLOCK_ID_PREFIX = 3323;
    public static int ITEM_ID_PREFIX = 15673;
    public static int ENTITY_ID_PREFIX = 56;

    public static Block multiBlock;

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

    public String getVersion()
    {
        return this.VERSION;
    }

    public String getChannel()
    {
        return "GSMMain";
    }

    public String getName()
    {
        return NAME;
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
