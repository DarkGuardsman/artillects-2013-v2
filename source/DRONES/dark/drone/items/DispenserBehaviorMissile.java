package dark.drone.items;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;
import dark.drone.entity.EntityBombMissile;

public final class DispenserBehaviorMissile extends BehaviorProjectileDispense
{
    /** Return the projectile entity spawned by this dispense behavior. */
    @Override
    protected IProjectile getProjectileEntity(World par1World, IPosition par2IPosition)
    {
        return new EntityBombMissile(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ());
    }
}
