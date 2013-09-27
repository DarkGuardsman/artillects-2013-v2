package dark.gsm.artillects.renders.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import dark.gsm.artillects.common.bots.EntityShoeBot;

public class RenderShoeBot extends Render
{
    ModelShoeBot model = new ModelShoeBot();

    public RenderShoeBot()
    {
        super();
    }

    protected void renderName(Entity par1Entity, double par2, double par4, double par6)
    {
        if (Minecraft.isGuiEnabled())
        {
            float var8 = 1.6F;
            float var9 = 0.016666668F * var8;
            float var10 = par1Entity.getDistanceToEntity(this.renderManager.livingPlayer);
            float var11 = par1Entity.isSneaking() ? 4.0F : 32.0F;

            if (var10 < var11)
            {
                String var12 = "Bot";
                if (par1Entity instanceof EntityShoeBot)
                {
                    var12 = ((EntityShoeBot) par1Entity).getRenderedName();
                }

                FontRenderer var13 = this.getFontRendererFromRenderManager();
                GL11.glPushMatrix();
                GL11.glTranslatef((float) par2 + 0.0F, (float) par4 + 2.3F, (float) par6);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                GL11.glScalef(-var9, -var9, var9);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glTranslatef(0.0F, 0.25F / var9, 0.0F);
                GL11.glDepthMask(false);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                Tessellator var14 = Tessellator.instance;
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                var14.startDrawingQuads();
                int var15 = var13.getStringWidth(var12) / 2;
                var14.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                var14.addVertex((-var15 - 1), -1.0D, 0.0D);
                var14.addVertex((-var15 - 1), 8.0D, 0.0D);
                var14.addVertex((var15 + 1), 8.0D, 0.0D);
                var14.addVertex((var15 + 1), -1.0D, 0.0D);
                var14.draw();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDepthMask(true);
                var13.drawString(var12, -var13.getStringWidth(var12) / 2, 0, 553648127);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            }
        }
    }

    public void renderBot(Entity par1Entity, double x, double y, double z, float par8, float par9)
    {

        GL11.glPushMatrix();
        GL11.glTranslated(x, y + .2, z);
        GL11.glRotatef(0, 0, 1, 0);
        //this.loadTexture("");
        model.render(par1Entity, 0, 0, 0, 0, 0, .0625f);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBot(par1Entity, par2, par4, par6, par8, par9);
        this.renderName(par1Entity, par2, par4, par6);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        // TODO Auto-generated method stub
        return null;
    }

}