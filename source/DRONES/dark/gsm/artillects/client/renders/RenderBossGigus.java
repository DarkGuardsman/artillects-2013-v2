package dark.gsm.artillects.client.renders;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import dark.gsm.artillects.DarkBotMain;

public class RenderBossGigus extends Render
{
    private IModelCustom modelBody;
    private IModelCustom modelShoulders;
    //private IModelCustom modelLeg;
    private IModelCustom modelPack;

    private ResourceLocation body = new ResourceLocation(DarkBotMain.DOMAIN, "textures/uv/body.png");
    private ResourceLocation shoulders = new ResourceLocation(DarkBotMain.DOMAIN, "textures/uv/shoulders.png");
    private ResourceLocation pack = new ResourceLocation(DarkBotMain.DOMAIN, "textures/uv/missilePack.png");

    //private ResourceLocation leg = new ResourceLocation(DarkBotMain.DOMAIN, "textures/uv/BossLeg.png");

    public RenderBossGigus()
    {
        modelBody = AdvancedModelLoader.loadModel("/assets/drones/models/Drone.gigus/body.obj");
        modelShoulders = AdvancedModelLoader.loadModel("/assets/drones/models/Drone.gigus/shoulders.obj");
        //modelLeg = AdvancedModelLoader.loadModel("/assets/dark/models/Drone.gigus/leg.obj");
        modelPack = AdvancedModelLoader.loadModel("/assets/drones/models/Drone.gigus/missilePack.obj");
    }

    @Override
    public void doRender(Entity entity, double xx, double yy, double zz, float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(xx, yy + 0.5, zz);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glScalef(.35f, .35f, .35f);
        //GL11.glRotatef(-90, 0F, 1F, 0F);
        GL11.glRotatef(-entity.rotationYaw, 0F, 1F, 0F);
        /*Render Body */
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(pack);
        modelPack.renderAll();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(body);
        modelBody.renderAll();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(shoulders);
        modelShoulders.renderAll();
        /*Render Legs */

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();

    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation(DarkBotMain.DOMAIN, "textures/uv/Drone.Wheel.png");
    }
}
