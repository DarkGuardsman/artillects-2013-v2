// Date: 10/4/2012 4:12:19 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package dark.gsm.artillects.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBasicSpidBot extends ModelBase
{
    // fields
    ModelRenderer MAIN_BODY;
    ModelRenderer LEG1la;
    ModelRenderer LEG1lb;
    ModelRenderer LEG2lb;
    ModelRenderer LEG2La;
    ModelRenderer LEG3Lb;
    ModelRenderer LEG3la;
    ModelRenderer LEG3rb;
    ModelRenderer LEG3ra;
    ModelRenderer LEG2ra;
    ModelRenderer LEG2rb;
    ModelRenderer LEG1ra;
    ModelRenderer LEG1rb;
    ModelRenderer MAINBACKSIDE;
    ModelRenderer MAINFRONTSIDE;
    ModelRenderer PINCERL1;
    ModelRenderer PINCERL2;
    ModelRenderer PINCERR1;
    ModelRenderer PINCERR2;
    ModelRenderer PINCERR3;
    ModelRenderer PINCERL3;
    ModelRenderer MAINCAM;
    ModelRenderer CAMLENS;
    ModelRenderer ANTENNA;
    ModelRenderer EXHAUST1;
    ModelRenderer EXHAUST2;

    public ModelBasicSpidBot()
    {
        textureWidth = 128;
        textureHeight = 128;

        MAIN_BODY = new ModelRenderer(this, 0, 0);
        MAIN_BODY.addBox(0F, 0F, 0F, 6, 4, 8);
        MAIN_BODY.setRotationPoint(-3F, 17F, -2F);
        MAIN_BODY.setTextureSize(128, 128);
        MAIN_BODY.mirror = true;
        setRotation(MAIN_BODY, 0F, 0F, 0F);
        LEG1la = new ModelRenderer(this, 0, 14);
        LEG1la.addBox(0F, -1F, -1F, 4, 2, 2);
        LEG1la.setRotationPoint(2F, 19F, -1F);
        LEG1la.setTextureSize(128, 128);
        LEG1la.mirror = true;
        setRotation(LEG1la, 0F, 0.1745329F, 0F);
        LEG1lb = new ModelRenderer(this, 0, 20);
        LEG1lb.addBox(3F, -1F, -1F, 2, 6, 2);
        LEG1lb.setRotationPoint(2F, 19F, -1F);
        LEG1lb.setTextureSize(128, 128);
        LEG1lb.mirror = true;
        setRotation(LEG1lb, 0F, 0.1745329F, 0F);
        LEG2lb = new ModelRenderer(this, 0, 20);
        LEG2lb.addBox(3F, -1F, -1F, 2, 6, 2);
        LEG2lb.setRotationPoint(3F, 19F, 2F);
        LEG2lb.setTextureSize(128, 128);
        LEG2lb.mirror = true;
        setRotation(LEG2lb, 0F, 0F, 0F);
        LEG2La = new ModelRenderer(this, 0, 14);
        LEG2La.addBox(0F, -1F, -1F, 4, 2, 2);
        LEG2La.setRotationPoint(3F, 19F, 2F);
        LEG2La.setTextureSize(128, 128);
        LEG2La.mirror = true;
        setRotation(LEG2La, 0F, 0F, 0F);
        LEG3Lb = new ModelRenderer(this, 0, 20);
        LEG3Lb.addBox(3F, -1F, -1F, 2, 6, 2);
        LEG3Lb.setRotationPoint(2F, 19F, 5F);
        LEG3Lb.setTextureSize(128, 128);
        LEG3Lb.mirror = true;
        setRotation(LEG3Lb, 0F, -0.1745329F, 0F);
        LEG3la = new ModelRenderer(this, 0, 14);
        LEG3la.addBox(0F, -1F, -1F, 4, 2, 2);
        LEG3la.setRotationPoint(2F, 19F, 5F);
        LEG3la.setTextureSize(128, 128);
        LEG3la.mirror = true;
        setRotation(LEG3la, 0F, -0.1745329F, 0F);
        LEG3rb = new ModelRenderer(this, 0, 20);
        LEG3rb.addBox(-5F, -1F, -1F, 2, 6, 2);
        LEG3rb.setRotationPoint(-2F, 19F, 5F);
        LEG3rb.setTextureSize(128, 128);
        LEG3rb.mirror = true;
        setRotation(LEG3rb, 0F, 0.1745329F, 0F);
        LEG3ra = new ModelRenderer(this, 0, 14);
        LEG3ra.addBox(-4F, -1F, -1F, 4, 2, 2);
        LEG3ra.setRotationPoint(-2F, 19F, 5F);
        LEG3ra.setTextureSize(128, 128);
        LEG3ra.mirror = true;
        setRotation(LEG3ra, 0F, 0.1745329F, 0F);
        LEG2ra = new ModelRenderer(this, 0, 14);
        LEG2ra.addBox(-4F, -1F, -1F, 4, 2, 2);
        LEG2ra.setRotationPoint(-3F, 19F, 2F);
        LEG2ra.setTextureSize(128, 128);
        LEG2ra.mirror = true;
        setRotation(LEG2ra, 0F, 0F, 0F);
        LEG2rb = new ModelRenderer(this, 0, 20);
        LEG2rb.addBox(-5F, -1F, -1F, 2, 6, 2);
        LEG2rb.setRotationPoint(-3F, 19F, 2F);
        LEG2rb.setTextureSize(128, 128);
        LEG2rb.mirror = true;
        setRotation(LEG2rb, 0F, 0F, 0F);
        LEG1ra = new ModelRenderer(this, 0, 14);
        LEG1ra.addBox(-4F, -1F, -1F, 4, 2, 2);
        LEG1ra.setRotationPoint(-2F, 19F, -1F);
        LEG1ra.setTextureSize(128, 128);
        LEG1ra.mirror = true;
        setRotation(LEG1ra, 0F, -0.1745329F, 0F);
        LEG1rb = new ModelRenderer(this, 0, 20);
        LEG1rb.addBox(-5F, -1F, -1F, 2, 6, 2);
        LEG1rb.setRotationPoint(-2F, 19F, -1F);
        LEG1rb.setTextureSize(128, 128);
        LEG1rb.mirror = true;
        setRotation(LEG1rb, 0F, -0.1745329F, 0F);
        MAINBACKSIDE = new ModelRenderer(this, 29, 7);
        MAINBACKSIDE.addBox(0F, 0F, 0F, 4, 4, 6);
        MAINBACKSIDE.setRotationPoint(-2F, 15F, 3F);
        MAINBACKSIDE.setTextureSize(128, 128);
        MAINBACKSIDE.mirror = true;
        setRotation(MAINBACKSIDE, -0.3839724F, 0F, 0F);
        MAINFRONTSIDE = new ModelRenderer(this, 29, 18);
        MAINFRONTSIDE.addBox(0F, 0F, 0F, 4, 4, 9);
        MAINFRONTSIDE.setRotationPoint(-2F, 18F, -5F);
        MAINFRONTSIDE.setTextureSize(128, 128);
        MAINFRONTSIDE.mirror = true;
        setRotation(MAINFRONTSIDE, 0.4363323F, 0F, 0F);
        PINCERL1 = new ModelRenderer(this, 60, 0);
        PINCERL1.addBox(0F, -1F, -1F, 4, 2, 2);
        PINCERL1.setRotationPoint(1F, 19F, -3F);
        PINCERL1.setTextureSize(128, 128);
        PINCERL1.mirror = true;
        setRotation(PINCERL1, 0F, 0.2617994F, 0F);
        PINCERL2 = new ModelRenderer(this, 60, 6);
        PINCERL2.addBox(4F, -1F, -3F, 2, 2, 4);
        PINCERL2.setRotationPoint(1F, 19F, -3F);
        PINCERL2.setTextureSize(128, 128);
        PINCERL2.mirror = true;
        setRotation(PINCERL2, 0F, 0.2617994F, 0F);
        PINCERR1 = new ModelRenderer(this, 60, 0);
        PINCERR1.addBox(-4F, -1F, -1F, 4, 2, 2);
        PINCERR1.setRotationPoint(-1F, 19F, -3F);
        PINCERR1.setTextureSize(128, 128);
        PINCERR1.mirror = true;
        setRotation(PINCERR1, 0F, -0.2617994F, 0F);
        PINCERR2 = new ModelRenderer(this, 60, 6);
        PINCERR2.addBox(-6F, -1F, -3F, 2, 2, 4);
        PINCERR2.setRotationPoint(-1F, 19F, -3F);
        PINCERR2.setTextureSize(128, 128);
        PINCERR2.mirror = true;
        setRotation(PINCERR2, 0F, -0.2617994F, 0F);
        PINCERR3 = new ModelRenderer(this, 60, 14);
        PINCERR3.addBox(-2F, -1F, -7F, 3, 2, 2);
        PINCERR3.setRotationPoint(-1F, 19F, -3F);
        PINCERR3.setTextureSize(128, 128);
        PINCERR3.mirror = true;
        setRotation(PINCERR3, 0F, 0.6283185F, 0F);
        PINCERL3 = new ModelRenderer(this, 60, 14);
        PINCERL3.addBox(-2F, -1F, -7F, 3, 2, 2);
        PINCERL3.setRotationPoint(1F, 19F, -3F);
        PINCERL3.setTextureSize(128, 128);
        PINCERL3.mirror = true;
        setRotation(PINCERL3, 0F, -0.8028515F, 0F);
        MAINCAM = new ModelRenderer(this, 0, 30);
        MAINCAM.addBox(-2F, -2F, -3.5F, 4, 4, 7);
        MAINCAM.setRotationPoint(1F, 15F, -1F);
        MAINCAM.setTextureSize(128, 128);
        MAINCAM.mirror = true;
        setRotation(MAINCAM, 0F, 0F, 0F);
        CAMLENS = new ModelRenderer(this, 0, 43);
        CAMLENS.addBox(-1F, -1F, -5F, 2, 2, 2);
        CAMLENS.setRotationPoint(1F, 15F, -1F);
        CAMLENS.setTextureSize(128, 128);
        CAMLENS.mirror = true;
        setRotation(CAMLENS, 0F, 0F, 0F);
        ANTENNA = new ModelRenderer(this, 0, 49);
        ANTENNA.addBox(0F, 0F, 0F, 1, 6, 1);
        ANTENNA.setRotationPoint(1F, 10F, 6F);
        ANTENNA.setTextureSize(128, 128);
        ANTENNA.mirror = true;
        setRotation(ANTENNA, -0.2443461F, 0F, 0F);
        EXHAUST1 = new ModelRenderer(this, 29, 0);
        EXHAUST1.addBox(0F, 0F, 0F, 2, 4, 2);
        EXHAUST1.setRotationPoint(-2F, 14F, 4F);
        EXHAUST1.setTextureSize(128, 128);
        EXHAUST1.mirror = true;
        setRotation(EXHAUST1, -0.2443461F, 0F, 0F);
        EXHAUST2 = new ModelRenderer(this, 29, 0);
        EXHAUST2.addBox(0F, 0F, 0F, 2, 4, 2);
        EXHAUST2.setRotationPoint(-2F, 14F, 7F);
        EXHAUST2.setTextureSize(128, 128);
        EXHAUST2.mirror = true;
        setRotation(EXHAUST2, -0.2443461F, 0F, 0F);
    }

    @Override
    public void render(Entity par1Entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        MAIN_BODY.render(f5);
        MAINBACKSIDE.render(f5);
        MAINFRONTSIDE.render(f5);
        this.grab(0, f5);
        MAINCAM.render(f5);
        CAMLENS.render(f5);
        ANTENNA.render(f5);
        EXHAUST1.render(f5);
        EXHAUST2.render(f5);
        // legs
        LEG1la.render(f5);
        LEG1lb.render(f5);
        LEG2lb.render(f5);
        LEG2La.render(f5);
        LEG3Lb.render(f5);
        LEG3la.render(f5);
        LEG3rb.render(f5);
        LEG3ra.render(f5);
        LEG2ra.render(f5);
        LEG2rb.render(f5);
        LEG1ra.render(f5);
        LEG1rb.render(f5);
    }

    public void grab(int pos, float f5)
    {
        PINCERL1.render(f5);
        PINCERL2.render(f5);
        PINCERR1.render(f5);
        PINCERR2.render(f5);
        PINCERR3.render(f5);
        PINCERL3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
    {
        this.MAINCAM.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.MAINCAM.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.CAMLENS.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.CAMLENS.rotateAngleX = par5 / (180F / (float) Math.PI);
        /** float var7 = ((float)Math.PI / 4F); this.LEG1la.rotateAngleZ = -var7;
         * this.LEG1lb.rotateAngleZ = -var7; this.LEG2La.rotateAngleZ = var7;
         * this.LEG2lb.rotateAngleZ = var7; this.LEG3la.rotateAngleZ = -var7 * 0.74F;
         * this.LEG3Lb.rotateAngleZ = -var7 * 0.74F;
         * 
         * this.LEG1ra.rotateAngleZ = var7 * 0.74F; this.LEG1rb.rotateAngleZ = var7 * 0.74F;
         * this.LEG2ra.rotateAngleZ = -var7 * 0.74F; this.LEG2rb.rotateAngleZ = -var7 * 0.74F;
         * this.LEG3ra.rotateAngleZ = var7 * 0.74F; this.LEG3rb.rotateAngleZ = var7 * 0.74F; */
        float var8 = -0.0F;
        float var9 = 0.3926991F;
        this.LEG1la.rotateAngleY = 0.1745329F;
        this.LEG1lb.rotateAngleY = 0.1745329F;
        this.LEG2La.rotateAngleY = 0;
        this.LEG2lb.rotateAngleY = 0;
        this.LEG3la.rotateAngleY = -0.1745329F;
        this.LEG3Lb.rotateAngleY = -0.1745329F;

        this.LEG1ra.rotateAngleY = -0.1745329F;
        this.LEG1rb.rotateAngleY = -0.1745329F;
        this.LEG2ra.rotateAngleY = 0;
        this.LEG2rb.rotateAngleY = 0;
        this.LEG3ra.rotateAngleY = 0.1745329F;
        this.LEG3rb.rotateAngleY = 0.1745329F;

        float var10 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
        float var11 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * par2;
        float var12 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * par2;
        float var14 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
        float var15 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float) Math.PI) * 0.4F) * par2;
        float var16 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * par2;
        this.LEG1la.rotateAngleY += var10 + var14;
        this.LEG1lb.rotateAngleY += var10 + var14;
        this.LEG2La.rotateAngleY += -var10 - var14;
        this.LEG2lb.rotateAngleY += -var10 - var14;
        this.LEG3la.rotateAngleY += var11 + var15;
        this.LEG3Lb.rotateAngleY += var11 + var15;

        this.LEG1ra.rotateAngleY += -var11 - var15;
        this.LEG1rb.rotateAngleY += -var11 - var15;
        this.LEG2ra.rotateAngleY += var12 + var16;
        this.LEG2rb.rotateAngleY += var12 + var16;
        this.LEG3ra.rotateAngleY += -var12 - var16;
        this.LEG3rb.rotateAngleY += -var12 - var16;
    }

}