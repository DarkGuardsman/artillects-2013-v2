package icbm.gangshao.render;

import icbm.gangshao.turret.BlockTurret.TurretType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.gsm.common.core.GSMCore;

/** Renders a block as an item.
 * 
 * @author Calclavia */
@SideOnly(Side.CLIENT)
public class BlockRenderingHandler implements ISimpleBlockRenderingHandler
{
	public static final int ID = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == ID)
		{
			GL11.glPushMatrix();

			if (metadata == TurretType.GUN.ordinal())
			{
				/** Render the gun turret. */
				GL11.glTranslatef(0.1f, 1f, 0f);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, FMLClientHandler.instance().getClient().renderEngine.getTexture(GSMCore.MODEL_PATH + RenderGunTurret.TEXTURE_FILE));
				RenderGunTurret.render(0, 0);
			}
			else if (metadata == TurretType.AA.ordinal())
			{
				/** Render the gun turret. */
				GL11.glTranslatef(0.2f, 0.3f, 0);
				GL11.glScalef(0.45f, 0.45f, 0.45f);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, FMLClientHandler.instance().getClient().renderEngine.getTexture(GSMCore.MODEL_PATH + RenderAATurret.TEXTURE_FILE));
				RenderAATurret.render(0, 0);
			}
			else if (metadata == TurretType.RAILGUN.ordinal())
			{
				/** Renders the railgun. */
				GL11.glTranslatef(0f, 0.9f, 0f);
				GL11.glRotatef(180f, 0f, 0f, 1f);

				GL11.glBindTexture(GL11.GL_TEXTURE_2D, FMLClientHandler.instance().getClient().renderEngine.getTexture(GSMCore.MODEL_PATH + RenderRailgun.TEXTURE_FILE));
				RenderRailgun.MODEL.render(90, 0, 0.0625F);
			}

			GL11.glPopMatrix();
		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess iBlockAccess, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return ID;
	}

}