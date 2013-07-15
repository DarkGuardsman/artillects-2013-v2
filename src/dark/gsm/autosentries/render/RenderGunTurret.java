package dark.gsm.autosentries.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.gsm.autosentries.model.ModelSentryCannon;
import dark.gsm.autosentries.turret.TileEntityTurretBase;
import dark.gsm.client.renders.RenderTaggedTile;
import dark.gsm.core.common.GSMCore;
import dark.library.access.AccessLevel;

@SideOnly(Side.CLIENT)
public class RenderGunTurret extends RenderTaggedTile
{
	public static final String TEXTURE_FILE = "gun_turret_neutral.png";
	public static final String TEXTURE_FILE_FRIENDLY = "gun_turret_friendly.png";
	public static final String TEXTURE_FILE_HOSTILE = "gun_turret_hostile.png";
	public static final ModelSentryCannon MODEL = new ModelSentryCannon();

	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y, double z, float f)
	{
		super.renderTileEntityAt(t, x, y, z, f);

		if (t instanceof TileEntityTurretBase)
		{
			TileEntityTurretBase tileEntity = (TileEntityTurretBase) t;
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);

			this.setTextureBaseOnState(tileEntity);
			RenderGunTurret.render(tileEntity.currentRotationYaw, tileEntity.currentRotationPitch);

			GL11.glPopMatrix();
		}
	}

	public static void render(float renderYaw, float renderPitch)
	{
		GL11.glRotatef(180F, 0F, 0F, 1F);
		GL11.glRotatef(180F, 0F, 1F, 0F);
		// Render base yaw rotation
		GL11.glRotatef(renderYaw, 0F, 1F, 0F);
		MODEL.renderYaw(0.0625F);
		// Render gun pitch rotation
		GL11.glRotatef(renderPitch, 1F, 0F, 0F);
		MODEL.renderYawPitch(0.0625F);
	}

	public void setTextureBaseOnState(TileEntityTurretBase tileEntity)
	{
		EntityPlayer player = this.getPlayer();

		if (tileEntity.getPlatform() != null)
		{
			AccessLevel level = tileEntity.getPlatform().getUserAccess(player.username);

			if (level == AccessLevel.ADMIN)
			{
				this.bindTextureByName(GSMCore.DOMAIN, GSMCore.MODEL_DIRECTORY + TEXTURE_FILE);
				return;
			}
			else if (level.ordinal() >= AccessLevel.USER.ordinal())
			{
				this.bindTextureByName(GSMCore.DOMAIN, GSMCore.MODEL_DIRECTORY + TEXTURE_FILE_FRIENDLY);
				return;
			}
		}

		this.bindTextureByName(GSMCore.DOMAIN, GSMCore.MODEL_DIRECTORY + TEXTURE_FILE_HOSTILE);

	}

	@Override
	public ResourceLocation getTexture(int block, int meta)
	{
		return null;
	}
}