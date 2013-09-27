package dark.gsm.npc.village;

import java.util.List;

import net.minecraft.item.ItemStack;

public class TechAgeManager
{

    public static boolean controlModContent = false;
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
        NOMATIC,
        /** First village goes up and workers start building */
        STONE,
        /** First point empire starts building bronze tools, and weapons */
        BRONZE,
        /** Iron tools, weapons, basic machines */
        IRON,
        /** Advanced iron tools, weapons, steel tools & weapons, more advanced machines */
        STEEL,
        /** First point steam is used to power machines */
        STEAM,
        /** Early electricity, and more complex machines */
        INDUSTRIAL,
        /** Late industrial age when nuclear power and weapons are created */
        NUCLEAR,
        /** Space starts to become and option to explore and exploit */
        SPACE;
    }

    /** Used on a mod class or manager to help this mod understand what content is for what age. Too
     * start with this will only restrict what the NPCs can create */
    public static interface ITechRestrictedContent
    {
        public List<ItemStack> getItemsForTechLevel(TechAge age);

    }
}
