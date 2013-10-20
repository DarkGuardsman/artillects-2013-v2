package dark.gsm.core.prefab;

import java.util.Random;

import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import dark.core.prefab.helpers.MathHelper;

/** NPC that simulate being players in an empire controlled environment
 *
 * @author Darkguardsman */
public class EntityNpc extends EntityCreature
{
    protected Random random = new Random();

    protected String factionName = "NEUTRIAL";

    /* CHARACTER STUFF */
    protected String humanName = "NPC";
    protected boolean female;
    protected int[] stats;

    public EntityNpc(World world)
    {
        super(world);
        this.stats = MathHelper.generateRandomIntArray(random, 100, 8);
        this.female = random.nextBoolean();
    }

    /** @param stats - Strength, endurance, dexterity, Intelligence, wit, memory, willpower,
     * perception, luck http://en.wikipedia.org/wiki/Attribute_(role-playing_games) */
    public EntityNpc(World world, int... stats)
    {
        this(world);
        this.stats = MathHelper.generateRandomIntArray(random, 100, 8);
        for (int i = 0; i < stats.length && i < stats.length; i++)
        {
            if (stats[i] != -1)
            {
                this.stats[i] = stats[i];
            }
        }
    }

    /** Gets the value of the stat */
    public int getStat(CharStats stat)
    {
        if (this.stats == null)
        {
            this.stats = MathHelper.generateRandomIntArray(random, 100, 8);
        }
        return stat.ordinal() < this.stats.length ? this.stats[stat.ordinal()] : 10;

    }

    public String getName()
    {
        return humanName;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setString("humanName", this.humanName);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.humanName = nbt.getString("humanName");
        this.factionName = nbt.getString("factionName");
    }
}
