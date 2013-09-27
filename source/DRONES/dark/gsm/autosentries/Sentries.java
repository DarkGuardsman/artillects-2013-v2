package dark.gsm.autosentries;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import dark.core.prefab.entity.EntityTileDamage;
import dark.core.prefab.terminal.CommandHelp;
import dark.core.prefab.terminal.CommandRegistry;
import dark.core.prefab.terminal.CommandUser;
import dark.drone.PacketManager;
import dark.gsm.autosentries.platform.BlockTurretPlatform;
import dark.gsm.autosentries.terminal.command.CommandAccess;
import dark.gsm.autosentries.terminal.command.CommandDestroy;
import dark.gsm.autosentries.terminal.command.CommandGet;
import dark.gsm.autosentries.terminal.command.CommandTarget;
import dark.gsm.autosentries.turret.BlockTurret;
import dark.gsm.autosentries.turret.ItemAmmo;
import dark.gsm.autosentries.turret.ItemBlockTurret;
import dark.gsm.autosentries.turret.mount.EntityFakeMountable;
import dark.gsm.autosentries.turret.upgrades.ItemTurretUpgrades;
import dark.gsm.core.common.GSMCore;

@Mod(modid = Sentries.NAME, name = Sentries.NAME, version = GSMCore.VERSION, useMetadata = true)
@NetworkMod(channels = { Sentries.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketManager.class)
public class Sentries extends GSMCore
{
    public static final String NAME = GSMCore.NAME + "|AutoSentries";
    public static final String CHANNEL = NAME;

    @SidedProxy(clientSide = "dark.gsm.autosentries.ClientProxy", serverSide = "dark.gsm.autosentries.CommonProxy")
    public static CommonProxy proxy;

    @Instance(NAME)
    public static Sentries instance;

    @Metadata(NAME)
    public static ModMetadata metadata;

    public static Block blockTurret, blockPlatform;
    public static Item itemAmmo, itemUpgrades;

    /** ItemStack helpers. Do not modify theses. */
    public static ItemStack conventionalBullet, railgunBullet, antimatterBullet, bulletShell;

    private static Configuration sentryConfig = new Configuration(new File(Loader.instance().getConfigDir(), "dark/AutoSentries.cfg"));

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        NetworkRegistry.instance().registerGuiHandler(this, Sentries.proxy);
        MinecraftForge.EVENT_BUS.register(this);

        sentryConfig.load();

        blockTurret = new BlockTurret(++BLOCK_ID_PREFIX, sentryConfig);
        blockPlatform = new BlockTurretPlatform(++BLOCK_ID_PREFIX, sentryConfig);

        itemAmmo = new ItemAmmo(++ITEM_ID_PREFIX, sentryConfig);
        itemUpgrades = new ItemTurretUpgrades(++ITEM_ID_PREFIX, sentryConfig);
        if (sentryConfig.hasChanged())
        {
            sentryConfig.save();
        }

        bulletShell = new ItemStack(itemAmmo, 1, 0);
        conventionalBullet = new ItemStack(itemAmmo, 1, 1);
        railgunBullet = new ItemStack(itemAmmo, 1, 2);
        antimatterBullet = new ItemStack(itemAmmo, 1, 3);

        GameRegistry.registerBlock(blockTurret, ItemBlockTurret.class, "ICBMTurret");
        GameRegistry.registerBlock(blockPlatform, "ICBMPlatform");

        EntityRegistry.registerGlobalEntityID(EntityFakeMountable.class, "ICBMFake", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityFakeMountable.class, "ICBMFake", ENTITY_ID_PREFIX + 7, this, 50, 5, true);
        EntityRegistry.registerGlobalEntityID(EntityTileDamage.class, "ICBMFakeTile", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityTileDamage.class, "ICBMFakeTile", ENTITY_ID_PREFIX + 8, this, 50, 5, true);

        //ICBMTab.itemStack = new ItemStack(blockTurret);

        proxy.preInit();
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        //ZhuYaoBase.setModMetadata(NAME, metadata);
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);

        // Shell
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemAmmo, 16, 0), new Object[] { "T", "T", 'T', "ingotTin" }));
        // Bullets
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemAmmo, 16, 1), new Object[] { "SBS", "SGS", "SSS", 'B', Item.ingotIron, 'G', Item.gunpowder, 'S', bulletShell.copy() }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemAmmo, 2, 2), new Object[] { "D", "B", "B", 'D', Item.diamond, 'B', conventionalBullet }));
        GameRegistry.addRecipe(new ShapedOreRecipe(antimatterBullet, new Object[] { "A", "B", 'A', "antimatterGram", 'B', railgunBullet }));

        if (OreDictionary.getOres("battery").size() > 0)
        {
            // Turret Platform
            //GameRegistry.addRecipe(new ShapedOreRecipe(blockPlatform, new Object[] { "SPS", "CBC", "SAS", 'P', Block.pistonBase, 'A', UniversalRecipes.BATTERY, 'S', UniversalRecipes.PRIMARY_PLATE, 'C', Block.chest, 'B', UniversalRecipes.CIRCUIT_T1 }));
        }

        // Gun Turret
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTurret, 1, 0), new Object[] { "SSS", "CS ", 'C', UniversalRecipes.CIRCUIT_T1, 'S', UniversalRecipes.PRIMARY_METAL }));
        // Railgun
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTurret, 1, 1), new Object[] { "DDD", "CS ", "GS ", 'D', Item.diamond, 'S', UniversalRecipes.PRIMARY_PLATE, 'C', UniversalRecipes.CIRCUIT_T3, 'G', new ItemStack(blockTurret, 1, 0) }));
        // AA Turret
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTurret, 1, 2), new Object[] { "DDD", "CS ", "GS ", 'D', UniversalRecipes.SECONDARY_PLATE, 'S', UniversalRecipes.PRIMARY_PLATE, 'C', UniversalRecipes.CIRCUIT_T2, 'G', new ItemStack(blockTurret, 1, 0) }));

        CommandRegistry.register(new CommandAccess());
        CommandRegistry.register(new CommandDestroy());
        CommandRegistry.register(new CommandUser());
        CommandRegistry.register(new CommandHelp());
        CommandRegistry.register(new CommandGet());
        CommandRegistry.register(new CommandTarget());
        proxy.init();
    }

    @Override
    public String getChannel()
    {
        return CHANNEL;
    }
}