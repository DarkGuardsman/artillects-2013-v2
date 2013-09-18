// Date: 5/26/2013 12:31:13 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package dark.gsm.client.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelCubeEyeBot extends ModelBase
{
    // fields
    ModelRenderer core;
    ModelRenderer fShieldTR;
    ModelRenderer fShieldBL;
    ModelRenderer fShieldTL;
    ModelRenderer fShieldBR;
    ModelRenderer bShieldTL;
    ModelRenderer bShieldTR;
    ModelRenderer bShieldBR;
    ModelRenderer bShieldBL;
    ModelRenderer lShieldTL;
    ModelRenderer lShieldBL;
    ModelRenderer lShieldBR;
    ModelRenderer lShieldTR;
    ModelRenderer rShieldTL;
    ModelRenderer rShieldTR;
    ModelRenderer rShieldBR;
    ModelRenderer rShieldBL;
    ModelRenderer tShieldRF;
    ModelRenderer tShieldLF;
    ModelRenderer tShieldLB;
    ModelRenderer tShieldRB;
    ModelRenderer bShieldLF;
    ModelRenderer bShieldRF;
    ModelRenderer bShieldRB;
    ModelRenderer bShieldLB;

    public ModelCubeEyeBot()
    {
        textureWidth = 256;
        textureHeight = 256;

        core = new ModelRenderer(this, 90, 64);
        core.addBox(-4F, -4F, -4F, 8, 8, 8);
        core.setRotationPoint(0F, 16F, 0F);
        core.setTextureSize(256, 256);
        core.mirror = true;
        setRotation(core, 0F, 0F, 0F);
        fShieldTR = new ModelRenderer(this, 40, 111);
        fShieldTR.addBox(-8F, 1F, -8F, 7, 7, 2);
        fShieldTR.setRotationPoint(0F, 16F, 0F);
        fShieldTR.setTextureSize(256, 256);
        fShieldTR.mirror = true;
        setRotation(fShieldTR, 0F, 0F, 0F);
        fShieldBL = new ModelRenderer(this, 61, 111);
        fShieldBL.addBox(1F, 1F, -8F, 7, 7, 2);
        fShieldBL.setRotationPoint(0F, 16F, 0F);
        fShieldBL.setTextureSize(256, 256);
        fShieldBL.mirror = true;
        setRotation(fShieldBL, 0F, 0F, 0F);
        fShieldTL = new ModelRenderer(this, 61, 100);
        fShieldTL.addBox(1F, -8F, -8F, 7, 7, 2);
        fShieldTL.setRotationPoint(0F, 16F, 0F);
        fShieldTL.setTextureSize(256, 256);
        fShieldTL.mirror = true;
        setRotation(fShieldTL, 0F, 0F, 0F);
        fShieldBR = new ModelRenderer(this, 40, 100);
        fShieldBR.addBox(-8F, -8F, -8F, 7, 7, 2);
        fShieldBR.setRotationPoint(0F, 16F, 0F);
        fShieldBR.setTextureSize(256, 256);
        fShieldBR.mirror = true;
        setRotation(fShieldBR, 0F, 0F, 0F);
        bShieldTL = new ModelRenderer(this, 40, 74);
        bShieldTL.addBox(1F, -8F, 6F, 7, 7, 2);
        bShieldTL.setRotationPoint(0F, 16F, 0F);
        bShieldTL.setTextureSize(256, 256);
        bShieldTL.mirror = true;
        setRotation(bShieldTL, 0F, 0F, 0F);
        bShieldTR = new ModelRenderer(this, 61, 74);
        bShieldTR.addBox(-8F, -8F, 6F, 7, 7, 2);
        bShieldTR.setRotationPoint(0F, 16F, 0F);
        bShieldTR.setTextureSize(256, 256);
        bShieldTR.mirror = true;
        setRotation(bShieldTR, 0F, 0F, 0F);
        bShieldBR = new ModelRenderer(this, 61, 86);
        bShieldBR.addBox(-8F, 1F, 6F, 7, 7, 2);
        bShieldBR.setRotationPoint(0F, 16F, 0F);
        bShieldBR.setTextureSize(256, 256);
        bShieldBR.mirror = true;
        setRotation(bShieldBR, 0F, 0F, 0F);
        bShieldBL = new ModelRenderer(this, 40, 86);
        bShieldBL.addBox(1F, 1F, 6F, 7, 7, 2);
        bShieldBL.setRotationPoint(0F, 16F, 0F);
        bShieldBL.setTextureSize(256, 256);
        bShieldBL.mirror = true;
        setRotation(bShieldBL, 0F, 0F, 0F);
        lShieldTL = new ModelRenderer(this, 82, 97);
        lShieldTL.addBox(6F, -8F, -6F, 2, 7, 5);
        lShieldTL.setRotationPoint(0F, 16F, 0F);
        lShieldTL.setTextureSize(256, 256);
        lShieldTL.mirror = true;
        setRotation(lShieldTL, 0F, 0F, 0F);
        lShieldBL = new ModelRenderer(this, 82, 111);
        lShieldBL.addBox(6F, 1F, -6F, 2, 7, 5);
        lShieldBL.setRotationPoint(0F, 16F, 0F);
        lShieldBL.setTextureSize(256, 256);
        lShieldBL.mirror = true;
        setRotation(lShieldBL, 0F, 0F, 0F);
        lShieldBR = new ModelRenderer(this, 99, 111);
        lShieldBR.addBox(6F, 1F, 1F, 2, 7, 5);
        lShieldBR.setRotationPoint(0F, 16F, 0F);
        lShieldBR.setTextureSize(256, 256);
        lShieldBR.mirror = true;
        setRotation(lShieldBR, 0F, 0F, 0F);
        lShieldTR = new ModelRenderer(this, 99, 97);
        lShieldTR.addBox(6F, -8F, 1F, 2, 7, 5);
        lShieldTR.setRotationPoint(0F, 16F, 0F);
        lShieldTR.setTextureSize(256, 256);
        lShieldTR.mirror = true;
        setRotation(lShieldTR, 0F, 0F, 0F);
        rShieldTL = new ModelRenderer(this, 8, 97);
        rShieldTL.addBox(-8F, -8F, 1F, 2, 7, 5);
        rShieldTL.setRotationPoint(0F, 16F, 0F);
        rShieldTL.setTextureSize(256, 256);
        rShieldTL.mirror = true;
        setRotation(rShieldTL, 0F, 0F, 0F);
        rShieldTR = new ModelRenderer(this, 24, 97);
        rShieldTR.addBox(-8F, -8F, -6F, 2, 7, 5);
        rShieldTR.setRotationPoint(0F, 16F, 0F);
        rShieldTR.setTextureSize(256, 256);
        rShieldTR.mirror = true;
        setRotation(rShieldTR, 0F, 0F, 0F);
        rShieldBR = new ModelRenderer(this, 24, 111);
        rShieldBR.addBox(-8F, 1F, -6F, 2, 7, 5);
        rShieldBR.setRotationPoint(0F, 16F, 0F);
        rShieldBR.setTextureSize(256, 256);
        rShieldBR.mirror = true;
        setRotation(rShieldBR, 0F, 0F, 0F);
        rShieldBL = new ModelRenderer(this, 8, 111);
        rShieldBL.addBox(-8F, 1F, 1F, 2, 7, 5);
        rShieldBL.setRotationPoint(0F, 16F, 0F);
        rShieldBL.setTextureSize(256, 256);
        rShieldBL.mirror = true;
        setRotation(rShieldBL, 0F, 0F, 0F);
        tShieldRF = new ModelRenderer(this, 61, 51);
        tShieldRF.addBox(-6F, -8F, -6F, 5, 2, 5);
        tShieldRF.setRotationPoint(0F, 16F, 0F);
        tShieldRF.setTextureSize(256, 256);
        tShieldRF.mirror = true;
        setRotation(tShieldRF, 0F, 0F, 0F);
        tShieldLF = new ModelRenderer(this, 38, 51);
        tShieldLF.addBox(1F, -8F, -6F, 5, 2, 5);
        tShieldLF.setRotationPoint(0F, 16F, 0F);
        tShieldLF.setTextureSize(256, 256);
        tShieldLF.mirror = true;
        setRotation(tShieldLF, 0F, 0F, 0F);
        tShieldLB = new ModelRenderer(this, 38, 61);
        tShieldLB.addBox(1F, -8F, 1F, 5, 2, 5);
        tShieldLB.setRotationPoint(0F, 16F, 0F);
        tShieldLB.setTextureSize(256, 256);
        tShieldLB.mirror = true;
        setRotation(tShieldLB, 0F, 0F, 0F);
        tShieldRB = new ModelRenderer(this, 61, 61);
        tShieldRB.addBox(-6F, -8F, 1F, 5, 2, 5);
        tShieldRB.setRotationPoint(0F, 16F, 0F);
        tShieldRB.setTextureSize(256, 256);
        tShieldRB.mirror = true;
        setRotation(tShieldRB, 0F, 0F, 0F);
        bShieldLF = new ModelRenderer(this, 61, 134);
        bShieldLF.addBox(1F, 6F, -6F, 5, 2, 5);
        bShieldLF.setRotationPoint(0F, 16F, 0F);
        bShieldLF.setTextureSize(256, 256);
        bShieldLF.mirror = true;
        setRotation(bShieldLF, 0F, 0F, 0F);
        bShieldRF = new ModelRenderer(this, 61, 124);
        bShieldRF.addBox(-7F, 6F, -6F, 5, 2, 5);
        bShieldRF.setRotationPoint(0F, 16F, 0F);
        bShieldRF.setTextureSize(256, 256);
        bShieldRF.mirror = true;
        setRotation(bShieldRF, 0F, 0F, 0F);
        bShieldRB = new ModelRenderer(this, 38, 124);
        bShieldRB.addBox(-6F, 6F, 1F, 5, 2, 5);
        bShieldRB.setRotationPoint(0F, 16F, 0F);
        bShieldRB.setTextureSize(256, 256);
        bShieldRB.mirror = true;
        setRotation(bShieldRB, 0F, 0F, 0F);
        bShieldLB = new ModelRenderer(this, 38, 134);
        bShieldLB.addBox(1F, 6F, 1F, 5, 2, 5);
        bShieldLB.setRotationPoint(0F, 16F, 0F);
        bShieldLB.setTextureSize(256, 256);
        bShieldLB.mirror = true;
        setRotation(bShieldLB, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.6F, 0.0F);
        core.render(f5);
        fShieldTR.render(f5);
        fShieldBL.render(f5);
        fShieldTL.render(f5);
        fShieldBR.render(f5);
        bShieldTL.render(f5);
        bShieldTR.render(f5);
        bShieldBR.render(f5);
        bShieldBL.render(f5);
        lShieldTL.render(f5);
        lShieldBL.render(f5);
        lShieldBR.render(f5);
        lShieldTR.render(f5);
        rShieldTL.render(f5);
        rShieldTR.render(f5);
        rShieldBR.render(f5);
        rShieldBL.render(f5);
        tShieldRF.render(f5);
        tShieldLF.render(f5);
        tShieldLB.render(f5);
        tShieldRB.render(f5);
        bShieldLF.render(f5);
        bShieldRF.render(f5);
        bShieldRB.render(f5);
        bShieldLB.render(f5);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float swingTime, float swingDistance, float par3, float headY, float headX, float par6, Entity par7Entity)
    {
        this.core.rotateAngleX = headX / (180F / (float) Math.PI);
        this.core.rotateAngleY = headY / (180F / (float) Math.PI);
        ModelRenderer[] body = new ModelRenderer[] { fShieldTR, fShieldBL, fShieldTL, fShieldBR, bShieldTL, bShieldTR, bShieldBR, bShieldBL, lShieldTL, lShieldBL, lShieldBR, lShieldTR, rShieldTL, rShieldTR, rShieldBR, rShieldBL, tShieldRF, tShieldLF, tShieldLB, tShieldRB, bShieldLF, bShieldRF, bShieldRB, bShieldLB };
        for (int i = 0; i < body.length; i++)
        {
            body[i].rotateAngleX = ((float) Math.PI / 2F);
        }
    }

}
