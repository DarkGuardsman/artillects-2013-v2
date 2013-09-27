package dark.gsm.npc.managers;

import dark.gsm.npc.managers.TechAgeManager.TechAge;
import net.minecraft.nbt.NBTTagCompound;

/** Used to track info about the progression of an empires technology
 *
 * @author DarkGuardsman */
public class TechAgeInfo
{
    public TechAge ageLevel = TechAge.NOMATIC;

    public TechAgeInfo()
    {

    }

    /** Save data to save file for the tech age info */
    public NBTTagCompound save(NBTTagCompound tag)
    {

        return tag;
    }

    /** Load data from save file for the tech age info */
    public TechAgeInfo load(NBTTagCompound tag)
    {

        return this;
    }

    /** Creates then loads data for the tech age info */
    public static TechAgeInfo createFromNBT(NBTTagCompound tag)
    {
        TechAgeInfo age = new TechAgeInfo();
        age.load(tag);
        return age;
    }
}
