package dark.gsm.core.prefab;

/** Some generic body & mind information used by NPC to influence how they act, what they can do, and
 * how well they do things.
 * 
 * @author Darkguardsman */
public enum CharStats
{
    /** Melee impact damage, carrying strength */
    STR("Strength"),
    /** how long can the character keep at it */
    END("endurance"),
    /** How hard the body, or hit points */
    CON("constitution"),
    /** Reflex of the character */
    DEX("dexterity"),
    /** Ability to think, and process information */
    INT("intelligence"),
    /** Ability to act fast on information given */
    WIT("wits"),
    /** Ability to recall information */
    MEM("memory"),
    /** Power to overcome challenges of the mind, and body */
    WIL("Will"),
    /** How well can the character use their senses to read the surround area */
    PER("perception"),
    /** How lucky is the character */
    luck("luck"),
    /** Social standing of character without character history, based on looks, and interaction
     * skills */
    SOC("standings");

    /** Name of the stat */
    public String name;

    private CharStats(String name)
    {
        this.name = name;
    }
}
