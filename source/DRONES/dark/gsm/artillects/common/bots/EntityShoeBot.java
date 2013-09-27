package dark.gsm.artillects.common.bots;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;

public class EntityShoeBot extends EntityRobot
{

    EntityItem targetItem = null;

    public EntityShoeBot(World par1World)
    {
        super(par1World);
        this.setSize(0.6F, 0.2F);
    }
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.8000000238418582D);
    }

    public String getRenderedName()
    {
        return "Harvester Bot";
    }

    public String getTaskType()
    {
        return "harvest";

    }

    @Override
    public int getRunningWatts()
    {
        return 0;
    }

    @Override
    public void runningUpdate()
    {
        // TODO Auto-generated method stub

    }
}
