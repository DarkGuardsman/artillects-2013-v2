package dark.gsm.autosentries.actions;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import dark.gsm.autosentries.api.IAutoSentry;

public class ActionSearchTarget extends Action
{
    @Override
    protected boolean onUpdateTask()
    {
        super.onUpdateTask();

        if (this.tileEntity instanceof IAutoSentry)
        {
            IAutoSentry sentry = (IAutoSentry) this.tileEntity;

            if (sentry.getTarget() == null || !sentry.isValidTarget(sentry.getTarget()))
            {
                AxisAlignedBB bounds = sentry.getTargetingBox();

                List<Entity> entities = this.tileEntity.worldObj.getEntitiesWithinAABB(Entity.class, bounds);
                Entity currentTarget = null;

                if (currentTarget == null)
                {
                    double smallestDis = sentry.getDetectRange();

                    for (Entity entity : entities)
                    {
                        double distance = entity.getDistance(this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord);

                        if (sentry.isValidTarget(entity) && distance < smallestDis)
                        {
                            currentTarget = entity;
                            smallestDis = distance;
                        }
                    }
                }

                sentry.setTarget(currentTarget, true);
            }
        }

        return false;
    }
}
