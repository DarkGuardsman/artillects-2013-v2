package dark.gsm.artillects.gen;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import dark.gsm.artillects.prefab.Pair;
import dark.gsm.artillects.prefab.Pos;
import dark.gsm.artillects.prefab.Trap;

public class TrapFall extends Trap
{
    Pair<Integer, Integer> resetBlock = null;

    public TrapFall(Pos pos)
    {
        super(pos, "fall", 10);
    }

    @Override
    public boolean canTrigger(Entity entity, Pos pos)
    {
        if (entity != null && pos != null)
        {
            return new Pos(entity).getDistanceFrom(this.pos) < 2;
        }
        return false;
    }

    @Override
    public boolean triggerTrap(World world)
    {
        System.out.println("Trap triggered " + type);
        resetBlock = pos.getBlockPair(world);
        pos.setBlock(world, 0);

        return true;
    }

    @Override
    public void reset(World world)
    {
        if (this.resetBlock != null)
        {
            this.pos.setBlock(world, resetBlock.getOne(), resetBlock.getTwo());
        }
    }
}
