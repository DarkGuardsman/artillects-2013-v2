package dark.gsm.autosentries.render;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.gsm.autosentries.model.ModelRailgun;
import dark.gsm.autosentries.turret.mount.TileEntityRailTurret;
import dark.gsm.client.renders.RenderTaggedTile;
import dark.gsm.core.common.GSMCore;

@SideOnly(Side.CLIENT)
public class RenderRailgun extends RenderTaggedTile
{
    public static final String TEXTURE_FILE = "railgun.png";
    public static final ModelRailgun MODEL = new ModelRailgun();

    @Override
    public void renderTileEntityAt(TileEntity t, double x, double y, double z, float f)
    {
        super.renderTileEntityAt(t, x, y, z, f);

        if (t instanceof TileEntityRailTurret)
        {
            TileEntityRailTurret tileEntity = (TileEntityRailTurret) t;

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 2.2F, (float) z + 0.5F);
            GL11.glScalef(1.5f, 1.5f, 1.5f);
            this.bindTextureByName(GSMCore.DOMAIN, GSMCore.MODEL_DIRECTORY + TEXTURE_FILE);
            GL11.glRotatef(180F, 0F, 0F, 1F);
            GL11.glRotatef(180F, 0F, 1F, 0F);
            MODEL.render(tileEntity.currentRotationYaw, tileEntity.currentRotationPitch, 0.0625F);
            GL11.glPopMatrix();
        }
    }

    @Override
    public ResourceLocation getTexture(int block, int meta)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void renderModel(TileEntity tileEntity, double x, double y, double z, float size)
    {
        // TODO Auto-generated method stub

    }
}