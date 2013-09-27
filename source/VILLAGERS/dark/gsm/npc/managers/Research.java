package dark.gsm.npc.managers;

public abstract class Research
{
    /** Means nothing is before this */
    boolean isBaseResearch = false;
    /** Is unlocked or researched already */
    boolean isUnlocked = false;
    /** can research */
    boolean canResearch = false;
    /** All research before this that is directly linked to this */
    Research[] prevResearch;
    /** All research after this that is directly linked to this */
    Research[] nextResearch;

    public Research(Research[] nextResearch)
    {
        this.isBaseResearch = true;
        this.nextResearch = nextResearch;
    }

    public Research(Research[] prevResearch, Research[] nextResearch)
    {
    }

    /** Have all events, and reqs been meet to complete the research. @Note: this doesn't have to be
     * research itself but can be a farmer finding a new plant, or a metal work creating a new
     * Technique
     *
     * @return true for yes */
    public abstract boolean canComplete();

    /** After this tech is researched use this to create events. or unlocked unlicked tech */
    public abstract void onResearched();

    public boolean isUnlocked()
    {
        return this.isUnlocked;
    }

    public boolean canResearch()
    {
        return this.canResearch;
    }
}
