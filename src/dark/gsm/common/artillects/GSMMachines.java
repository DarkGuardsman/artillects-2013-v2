package dark.gsm.common.artillects;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;

import org.modstats.ModstatInfo;

import universalelectricity.prefab.TranslationHelper;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.gsm.common.artillects.blocks.BlockCreep;
import dark.gsm.common.artillects.blocks.BlockWireRotation;
import dark.gsm.common.artillects.blocks.TileEntityWireFrame;
import dark.gsm.common.artillects.bots.EntityEyeBot;
import dark.gsm.common.core.GSMCore;
import dark.gsm.common.core.IMod;

@ModstatInfo(prefix = "GSMMachines")
@Mod(modid = GSMMachines.MOD_ID, name = GSMMachines.MOD_NAME, version = GSMMachines.VERSION, dependencies = "after:ICBM|Sentry;after:ICBM|Explosion;after:AtomicScience", useMetadata = true)
@NetworkMod(channels = { GSMMachines.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class GSMMachines extends DummyModContainer implements IMod
{

	// @Mod Prerequisites
	public static final String MAJOR_VERSION = "@MAJOR@";
	public static final String MINOR_VERSION = "@MINOR@";
	public static final String REVIS_VERSION = "@REVIS@";
	public static final String BUILD_VERSION = "@BUILD@";

	// @Mod
	public static final String MOD_ID = "GSM_Machines";
	public static final String MOD_NAME = "GSM-Artillects";
	public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVIS_VERSION + "." + BUILD_VERSION;

	// @NetworkMod
	public static final String CHANNEL = "GSMMech";

	@Metadata(GSMMachines.MOD_ID)
	public static ModMetadata meta;

	/* RESOURCE FILE PATHS */
	public static final String RESOURCE_PATH = "/mods/gsm/";
	public static final String TEXTURE_DIRECTORY = RESOURCE_PATH + "textures/";
	public static final String GUI_DIRECTORY = TEXTURE_DIRECTORY + "gui/";
	public static final String BLOCK_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "blocks/";
	public static final String ITEM_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "items/";
	public static final String MODEL_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "models/";
	public static final String TEXTURE_NAME_PREFIX = "gsm:";
	public static final String LANGUAGE_PATH = RESOURCE_PATH + "languages/";

	/* SUPPORTED LANGS */
	private static final String[] LANGUAGES_SUPPORTED = new String[] { "en_US" };

	/* CONFIG FILE */
	public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir() + "/GSM/", GSMMachines.MOD_NAME + ".cfg"));

	/* START IDS */
	public final static int BLOCK_ID_PREFIX = 3150;
	public final static int ITEM_ID_PREFIX = 13250;

	Block creepBlock;
	Block wireFrameBlock;

	Item botSpawner;

	@SidedProxy(clientSide = "dark.gsm.common.artillects.ClientProxy", serverSide = "dark.gsm.common.artillects.CommonProxy")
	public static dark.gsm.common.artillects.CommonProxy proxy;

	@Instance(GSMMachines.MOD_NAME)
	public static GSMMachines instance;

	/* LOGGER - EXTENDS FORGE'S LOG SYSTEM */
	public static Logger FMLog = Logger.getLogger(GSMMachines.MOD_NAME);

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		/* LOGGER SETUP */
		FMLog.setParent(FMLLog.getLogger());
		FMLog.fine("Initializing...");

		instance = this;

		/* UPDATE NOTIFIER */
		// Modstats.instance().getReporter().registerMod(this);

		/* CONFIGS */
		CONFIGURATION.load();
		creepBlock = new BlockCreep((this.CONFIGURATION.getBlock("creep", GSMMachines.BLOCK_ID_PREFIX).getInt()));
		wireFrameBlock = new BlockWireRotation((this.CONFIGURATION.getBlock("wireFrame", GSMMachines.BLOCK_ID_PREFIX + 1).getInt()));
		botSpawner = new ItemRobot((this.CONFIGURATION.getItem("spawnTool", GSMMachines.ITEM_ID_PREFIX).getInt()));
		if (CONFIGURATION.hasChanged())
		{
			CONFIGURATION.save();
		}
		// RenderingRegistry.registerEntityRenderingHandler(EntityCollector.class, new
		// RenderSpidBot());
		// EyeBot
		EntityRegistry.registerGlobalEntityID(EntityEyeBot.class, "GSMEyeBot", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityEyeBot.class, "GSMEyeBot", EntityRegistry.findGlobalUniqueEntityId(), instance, 60, 1, true);
		EntityRegistry.addSpawn(EntityEyeBot.class, 100, 10, 100, EnumCreatureType.creature, BiomeGenBase.forest, BiomeGenBase.plains);

		/* CONFIG END */
		GameRegistry.registerBlock(creepBlock, "blockCreep");
		GameRegistry.registerBlock(wireFrameBlock, "blockWireFrame");
		this.proxy.preInit();

	}

	@Init
	public void Init(FMLInitializationEvent event)
	{
		/* MCMOD.INFO FILE BUILDER? */
		meta.modId = GSMMachines.MOD_ID;
		meta.name = GSMMachines.MOD_NAME;
		meta.description = "Creation of hostile robotic entities designed to challanged and make the player's life harder. These entities in this mod will act on there own consuming the world till they are stopped. However, the off some unique advancements and loot threw capture & destruction";
		meta.url = "http://www.universalelectricity.com";

		meta.logoFile = GSMMachines.TEXTURE_DIRECTORY + "Art_Banner.png";
		meta.version = GSMMachines.VERSION;
		meta.authorList = Arrays.asList(new String[] { "DarkGuardsman AKA DarkCow" });
		meta.credits = "Please see the website.";
		meta.autogenerated = false;

		/* LOGGER */
		FMLog.info("Loading...");
		proxy.Init();

		GameRegistry.registerTileEntity(TileEntityWireFrame.class, "WireFrameTest");

		/* LANG LOADING */
		FMLog.info(" Loaded: " + TranslationHelper.loadLanguages(LANGUAGE_PATH, LANGUAGES_SUPPORTED) + " Languages.");

	}

	@PostInit
	public void PostInit(FMLPostInitializationEvent event)
	{
		/* LOGGER */
		FMLog.info("Finalizing...");
		proxy.postInit();
		GSMCore.registerMod(this);

		FMLog.info("Done Loading");
	}

	@Override
	public String getChannel()
	{
		return GSMMachines.CHANNEL;
	}
}
