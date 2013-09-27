package dark.gsm.npc.village;

import java.util.List;

import net.minecraft.item.ItemStack;

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
    /** Data base location inside the config folder */
    public static final String configDir = "/dark/GSM/ModManager";

    public void init()
    {
        this.updateConfigDatabase();
        this.parseConfigForBuildOptions();
        this.parseModsForBuildOptions();
    }

    /** Used later to download a master list from a server of how to interact with all mods */
    public void updateConfigDatabase()
    {

        //TODO get version of mods
        //Request from server xml files for that version of the mod
        //Turn item names to blockIDs
        //Setup crafting lists
        //Report any unknown mods to server
        //Add option to let smp server override master server database

    }

    /** Reads a config root file for how to handle content from other mods */
    public void parseConfigForBuildOptions()
    {
        //TODO only load configs for versions of mods running
        //This means the config xml will need to contain the modID, and version

    }

    public void parseModsForBuildOptions()
    {
        //TODO this will be more conplex than the config file
        // since mods will contain version-less xmls
    }

    /** Enum used to track the tech progress of an NPC empire. Normal npcs start at stone or nomatic.
     * Drones start at industrial automatically. */
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
