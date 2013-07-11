package dark.gsm.artillects.common.ai.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.Vec3;

public class ProjectileFiringMethods
{
	/** Creates and fires a Ghast size fire ball at a target entity
	 * 
	 * @param entity - Entity firing the fire ball
	 * @param targetedEntity - entity being fired at
	 * @param strength - explosion size of the ball
	 * @param spawnOffSet - how far to offset the spawn of the ball from the firing entity
	 * @param audio - should it play the default audio, if not you need to supply the audio */
	public static void fireGhastBalls(EntityLiving entity, Entity targetedEntity, int strength, double spawnOffSet, boolean audio)
	{
		if (entity == null || targetedEntity == null)
		{
			return;
		}
		double x = targetedEntity.posX - entity.posX;
		double y = targetedEntity.boundingBox.minY + (double) (targetedEntity.height / 2.0F) - (entity.posY + (double) (entity.height / 2.0F));
		double z = targetedEntity.posZ - entity.posZ;

		if (entity.worldObj.isRemote && audio)
		{
			entity.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1007, (int) entity.posX, (int) entity.posY, (int) entity.posZ, 0);
		}

		entity.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1008, (int) entity.posX, (int) entity.posY, (int) entity.posZ, 0);
		EntityLargeFireball entitylargefireball = new EntityLargeFireball(entity.worldObj, entity, x, y, z);
		entitylargefireball.field_92057_e = strength;

		Vec3 ballSpawnLocation = ((EntityLiving) entity).getLook(1.0F);
		entitylargefireball.posX = entity.posX + ballSpawnLocation.xCoord * spawnOffSet;
		entitylargefireball.posY = entity.posY + (double) (entity.height / 2.0F) + 0.5D;
		entitylargefireball.posZ = entity.posZ + ballSpawnLocation.zCoord * spawnOffSet;

		entity.worldObj.spawnEntityInWorld(entitylargefireball);

	}
}
