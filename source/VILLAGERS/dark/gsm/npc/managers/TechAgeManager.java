package dark.gsm.npc.managers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.builtbroken.common.Pair;

public class TechAgeManager
{
    /** Controls the content of other mods */
    public static boolean controlModContent = false;
    /** Tells the NPCs to ignore tech level restrictions */
    public static boolean ignoreTechLevel = false;
    /** Tells teh NPCs to ignore crafting restrictions */
    public static boolean ignoreCraftingRestrictions = false;
    /** Forces the player to the same tech restriction as NPCs */
    public static boolean forcePlayerCrafting = false;

    public static boolean init = false, postInit = false;
    /** Data base location inside the config folder */
    public static final String configDir = "/dark/GSM/ModManager";

    public static List<Pair<Integer, Integer>> unlockedCrafting = new ArrayList();

    static
    {
        //unlock all wood items
        unlockCrafting(Block.planks);
        unlockCrafting(Item.axeWood);
        unlockCrafting(Item.pickaxeWood);
        unlockCrafting(Item.swordWood);
        unlockCrafting(Block.workbench);
        unlockCrafting(Block.woodSingleSlab);
        unlockCrafting(Item.doorWood);
        unlockCrafting(Block.woodenButton);
        unlockCrafting(Item.stick);
        unlockCrafting(Block.torchWood);
    }

    public static void init()
    {
        if (!init)
        {
            updateConfigDatabase();
            parseConfigForBuildOptions();
            parseModsForBuildOptions();
            init = true;
        }
    }

    public static void postInit()
    {
        if (!postInit)
        {
            //TODO parse all mods for content, and attempt to auto category based on ore names, and reverse crafting
            postInit = true;
        }
    }

    /** Used later to download a master list from a server of how to interact with all mods */
    public static void updateConfigDatabase()
    {

        //TODO get version of mods
        //Request from server xml files for that version of the mod
        //Turn item names to blockIDs
        //Setup crafting lists
        //Report any unknown mods to server
        //Add option to let smp server override master server database

        //TODO copy files stored in the mod into the config folder if they don't download or don't already exist

    }

    /** Reads a config root file for how to handle content from other mods */
    public static void parseConfigForBuildOptions()
    {
        //TODO only load configs for versions of mods running
        //This means the config xml will need to contain the modID, and version

    }

    public static void parseModsForBuildOptions()
    {
        //TODO this will be more complex than the config file
        // since mods will contain version-less xmls
    }

    /** unlocks an item, block, or itemstack for crafting */
    public static void unlockCrafting(Object object)
    {
        ItemStack stack = null;
        if (object instanceof ItemStack)
        {
            stack = (ItemStack) object;
        }
        else if (object instanceof Item)
        {
            stack = new ItemStack((Item) object, 1, -1);
        }
        else if (object instanceof Block)
        {
            stack = new ItemStack((Block) object, 1, -1);
        }
        if (stack != null)
        {
            synchronized (unlockedCrafting)
            {
                TechAgeManager.unlockedCrafting.add(new Pair<Integer, Integer>(stack.itemID, stack.getItemDamage()));
            }
        }
    }

    /** Enum used to track the tech progress of an NPC empire. Normal npcs start at stone or nomatic.
     * Drones start at industrial automatically. Ages don't include any cultural periods as this is
     * technology only based */
    public static enum TechAge
    {
        /** Empire starting age. Before village creation */
        NOMATIC("Nomatic Period", "Early age of an empire in which citizens have yet to create static homes."),
        /** First village goes up and workers start building */
        STONE("Stone Age", "Begining point after citizens have created tools and shelters for their new town."),
        /** First point empire starts building bronze tools, and weapons */
        BRONZE("Bronze Age", "People have learned to start working basic metals to create tools, armor, and weapons."),
        /** Iron tools, weapons, basic machines */
        IRON("Iron Age", "Metal workers have learned to work iron into useful tools, and machines."),
        /** Advanced iron tools, weapons, steel tools & weapons, more advanced machines */
        STEEL("Steel Age", "Point between iron and steam when people learn to create steel, and use it."),
        /** First point steam is used to power machines */
        STEAM("Steam Age", "With a few shovals full of coal life has been brought to metal, and earth."),
        /** Early electricity, and more complex machines */
        INDUSTRIAL("Industiral Age", "Man no longer has to labor by hand to move heaven, and earth."),
        /** Late industrial age when nuclear power and weapons are created */
        NUCLEAR("Nuclear Age", "Fires from hell are brought forth in a tin can"),
        /** Space starts to become and option to explore and exploit */
        SPACE("Space", "Space, the final frontier, or a war zone were no one can hear you scream.");

        public String ageName, ageDesc;
        /** Crafting that by default is unlocked with the age progression */
        public List<Pair<Integer, Integer>> techCrafting = new ArrayList();

        private TechAge(String name, String description)
        {
            this.ageName = name;
            this.ageDesc = description;
        }
    }

    /** Used on a mod class or manager to help this mod understand what content is for what age. Too
     * start with this will only restrict what the NPCs can create */
    public static interface ITechRestrictedContent
    {
        public List<ItemStack> getItemsForTechLevel(TechAge age);
    }
}
