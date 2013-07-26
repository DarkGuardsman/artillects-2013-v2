// Date: 10/4/2012 7:35:56 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package dark.gsm.autosentries.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTurretMed extends ModelBase
{
    // fields

    ModelRenderer Center;
    ModelRenderer FRBrace;
    ModelRenderer BRBrace;
    ModelRenderer FLBrace;
    ModelRenderer BLBrace;
    ModelRenderer FLBrace2;
    ModelRenderer FLBrace3;
    ModelRenderer FRBrace2;
    ModelRenderer FRBrace3;
    ModelRenderer BRBrace2;
    ModelRenderer BRBrace3;
    ModelRenderer BLBrace3;
    ModelRenderer BLBrace2;
    ModelRenderer neck;
    ModelRenderer LeftFace;
    ModelRenderer RightFace;
    ModelRenderer Head;
    ModelRenderer Barrel;
    ModelRenderer Center2;
    ModelRenderer BarrelBrace;
    ModelRenderer BarrelCap;

    public ModelTurretMed()
    {
        textureWidth = 128;
        textureHeight = 128;

        Center = new ModelRenderer(this, 0, 0);
        Center.addBox(-2F, 0F, -2F, 4, 5, 4);
        Center.setRotationPoint(0F, 14F, 0F);
        Center.setTextureSize(128, 128);
        Center.mirror = true;
        setRotation(Center, 0F, 0.7853982F, 0F);
        FRBrace = new ModelRenderer(this, 0, 20);
        FRBrace.addBox(-1F, 14.5F, -8.5F, 2, 3, 6);
        FRBrace.setRotationPoint(0F, 0F, 0F);
        FRBrace.setTextureSize(128, 128);
        FRBrace.mirror = true;
        setRotation(FRBrace, 0.1745329F, 0.7853982F, 0F);
        BRBrace = new ModelRenderer(this, 0, 20);
        BRBrace.addBox(-1F, 14.5F, -8.5F, 2, 3, 6);
        BRBrace.setRotationPoint(0F, 0F, 0F);
        BRBrace.setTextureSize(128, 128);
        BRBrace.mirror = true;
        setRotation(BRBrace, 0.1745329F, -3.926991F, 0F);
        FLBrace = new ModelRenderer(this, 0, 20);
        FLBrace.addBox(-1F, 14.5F, -8.5F, 2, 3, 6);
        FLBrace.setRotationPoint(0F, 0F, 0F);
        FLBrace.setTextureSize(128, 128);
        FLBrace.mirror = true;
        setRotation(FLBrace, 0.1745329F, -0.7853982F, 0F);
        BLBrace = new ModelRenderer(this, 0, 20);
        BLBrace.addBox(-1F, 14.5F, -8.5F, 2, 3, 6);
        BLBrace.setRotationPoint(0F, 0F, 0F);
        BLBrace.setTextureSize(128, 128);
        BLBrace.mirror = true;
        setRotation(BLBrace, 0.1745329F, -2.356194F, 0F);
        FLBrace2 = new ModelRenderer(this, 0, 20);
        FLBrace2.addBox(-1F, 7F, -21F, 2, 3, 6);
        FLBrace2.setRotationPoint(0F, 0F, 0F);
        FLBrace2.setTextureSize(128, 128);
        FLBrace2.mirror = true;
        setRotation(FLBrace2, 0.7853982F, -0.7853982F, 0F);
        FLBrace3 = new ModelRenderer(this, 20, 20);
        FLBrace3.addBox(-2F, -10F, -24F, 4, 3, 4);
        FLBrace3.setRotationPoint(0F, 0F, 0F);
        FLBrace3.setTextureSize(128, 128);
        FLBrace3.mirror = true;
        setRotation(FLBrace3, 1.570796F, -0.7853982F, 0F);
        FRBrace2 = new ModelRenderer(this, 0, 20);
        FRBrace2.addBox(-1F, 7F, -21F, 2, 3, 6);
        FRBrace2.setRotationPoint(0F, 0F, 0F);
        FRBrace2.setTextureSize(128, 128);
        FRBrace2.mirror = true;
        setRotation(FRBrace2, 0.7853982F, 0.7853982F, 0F);
        FRBrace3 = new ModelRenderer(this, 20, 20);
        FRBrace3.addBox(-2F, -10.03333F, -24F, 4, 3, 4);
        FRBrace3.setRotationPoint(0F, 0F, 0F);
        FRBrace3.setTextureSize(128, 128);
        FRBrace3.mirror = true;
        setRotation(FRBrace3, 1.570796F, 0.7853982F, 0F);
        BRBrace2 = new ModelRenderer(this, 0, 20);
        BRBrace2.addBox(-1F, 7F, -21F, 2, 3, 6);
        BRBrace2.setRotationPoint(0F, 0F, 0F);
        BRBrace2.setTextureSize(128, 128);
        BRBrace2.mirror = true;
        setRotation(BRBrace2, 0.7853982F, -3.926991F, 0F);
        BRBrace3 = new ModelRenderer(this, 20, 20);
        BRBrace3.addBox(-2F, -10F, -24F, 4, 3, 4);
        BRBrace3.setRotationPoint(0F, 0F, 0F);
        BRBrace3.setTextureSize(128, 128);
        BRBrace3.mirror = true;
        setRotation(BRBrace3, 1.570796F, -3.926991F, 0F);
        BLBrace3 = new ModelRenderer(this, 20, 20);
        BLBrace3.addBox(-2F, -10F, -24F, 4, 3, 4);
        BLBrace3.setRotationPoint(0F, 0F, 0F);
        BLBrace3.setTextureSize(128, 128);
        BLBrace3.mirror = true;
        setRotation(BLBrace3, 1.570796F, -2.356194F, 0F);
        BLBrace2 = new ModelRenderer(this, 0, 20);
        BLBrace2.addBox(-1F, 7F, -21F, 2, 3, 6);
        BLBrace2.setRotationPoint(0F, 0F, 0F);
        BLBrace2.setTextureSize(128, 128);
        BLBrace2.mirror = true;
        setRotation(BLBrace2, 0.7853982F, -2.356194F, 0F);
        neck = new ModelRenderer(this, 19, 0);
        neck.addBox(-1.5F, 0F, -1.5F, 3, 5, 3);
        neck.setRotationPoint(0F, 11F, 0F);
        neck.setTextureSize(128, 128);
        neck.mirror = true;
        setRotation(neck, 0F, 0F, 0F);
        LeftFace = new ModelRenderer(this, 17, 69);
        LeftFace.addBox(3F, -1F, -1F, 2, 2, 6);
        LeftFace.setRotationPoint(0F, 11F, 0F);
        LeftFace.setTextureSize(128, 128);
        LeftFace.mirror = true;
        setRotation(LeftFace, 0F, 0F, 0F);
        RightFace = new ModelRenderer(this, 0, 69);
        RightFace.addBox(-5F, -1F, -1F, 2, 2, 6);
        RightFace.setRotationPoint(0F, 11F, 0F);
        RightFace.setTextureSize(128, 128);
        RightFace.mirror = true;
        setRotation(RightFace, 0F, 0F, 0F);
        Head = new ModelRenderer(this, 0, 55);
        Head.addBox(-3F, -2F, -2F, 6, 4, 8);
        Head.setRotationPoint(0F, 11F, 0F);
        Head.setTextureSize(128, 128);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Barrel = new ModelRenderer(this, 0, 42);
        Barrel.addBox(-1F, -1F, -6F, 2, 2, 2);
        Barrel.setRotationPoint(0F, 11F, 0F);
        Barrel.setTextureSize(128, 128);
        Barrel.mirror = true;
        setRotation(Barrel, 0F, 0F, 0F);
        Center2 = new ModelRenderer(this, 0, 0);
        Center2.addBox(-2F, 0F, -2F, 4, 5, 4);
        Center2.setRotationPoint(0F, 14F, 0F);
        Center2.setTextureSize(128, 128);
        Center2.mirror = true;
        setRotation(Center2, 0F, 0F, 0F);
        BarrelBrace = new ModelRenderer(this, 0, 36);
        BarrelBrace.addBox(-2F, -1.5F, -4F, 4, 3, 2);
        BarrelBrace.setRotationPoint(0F, 11F, 0F);
        BarrelBrace.setTextureSize(128, 128);
        BarrelBrace.mirror = true;
        setRotation(BarrelBrace, 0F, 0F, 0F);
        BarrelCap = new ModelRenderer(this, 0, 47);
        BarrelCap.addBox(-1.5F, -1.5F, -7F, 3, 3, 2);
        BarrelCap.setRotationPoint(0F, 11F, 0F);
        BarrelCap.setTextureSize(128, 128);
        BarrelCap.mirror = true;
        setRotation(BarrelCap, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity par1Entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);

        // Turret
        LeftFace.render(f5);
        RightFace.render(f5);
        Head.render(f5);
        Barrel.render(f5);
        BarrelBrace.render(f5);
        BarrelCap.render(f5);
    }

    public void renderLegs(float f5)
    {
        // turret stand
        FRBrace.render(f5);
        BRBrace.render(f5);
        FLBrace.render(f5);
        BLBrace.render(f5);
        FLBrace2.render(f5);
        FLBrace3.render(f5);
        FRBrace2.render(f5);
        FRBrace3.render(f5);
        BRBrace2.render(f5);
        BRBrace3.render(f5);
        BLBrace3.render(f5);
        BLBrace2.render(f5);
        neck.render(f5);
        Center.render(f5);
        Center2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
    {
        ModelRenderer[] Body = { Center, FRBrace, BRBrace, FLBrace, BLBrace, FLBrace2, FLBrace3, FRBrace2, FRBrace3, BRBrace2, BRBrace3, BLBrace3, BLBrace2, neck };
        for (int i = 0; i < Body.length; i++)
        {
            // Body[i].rotateAngleY -= par4 / (180F / (float)Math.PI);
            // TODO correct rotation to not move
        }
        this.LeftFace.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.LeftFace.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.RightFace.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.RightFace.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.Barrel.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.Barrel.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.BarrelBrace.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.BarrelBrace.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.BarrelCap.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.BarrelCap.rotateAngleX = par5 / (180F / (float) Math.PI);
    }

}
