package dark.gsm.autosentries.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.gsm.autosentries.turret.BlockTurret.TurretType;
import dark.gsm.core.common.GSMCore;

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
				FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation(GSMCore.DOMAIN,GSMCore.MODEL_DIRECTORY + RenderGunTurret.TEXTURE_FILE));
				RenderGunTurret.render(0, 0);
			}
			else if (metadata == TurretType.AA.ordinal())
			{
				/** Render the gun turret. */
				GL11.glTranslatef(0.2f, 0.3f, 0);
				GL11.glScalef(0.45f, 0.45f, 0.45f);
				FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation(GSMCore.DOMAIN,GSMCore.MODEL_DIRECTORY + RenderAATurret.TEXTURE_FILE));
				RenderAATurret.render(0, 0);
			}
			else if (metadata == TurretType.RAILGUN.ordinal())
			{
				/** Renders the railgun. */
				GL11.glTranslatef(0f, 0.9f, 0f);
				GL11.glRotatef(180f, 0f, 0f, 1f);

				FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation(GSMCore.DOMAIN,GSMCore.MODEL_DIRECTORY + RenderRailgun.TEXTURE_FILE));
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