package dark.gsm.common.artillects.ai.combat;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public interface IAttacker
{
	/**
	 * Gets the bounding box by which the sentry focuses its weapon on
	 */
	public AxisAlignedBB getTargetingBox();

	/**
	 * Get the current target of the Sentry
	 */
	public Entity getTarget();

	/**
	 * Sets the target of the sentry if it doesn't have one or was overridden
	 */
	public boolean setTarget(Entity target, boolean override);

	/**
	 * Used by the AI Action classes to check if the target its trying to find is valid for it to
	 * track
	 */
	public boolean isValidTarget(Entity entity);

	/**
	 * Gets the detection range for the range type
	 */
	public double getRange(EnumRange rangeType);
}
