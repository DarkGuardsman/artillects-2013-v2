package dark.gsm.fortress.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGunTurret extends ModelBase
{
    // fields
    ModelRenderer BasePlate;
    ModelRenderer neck;
    ModelRenderer neck2;
    ModelRenderer neck3;
    ModelRenderer neck4;
    ModelRenderer Base;
    ModelRenderer LeftSide;
    ModelRenderer LeftSideB;
    ModelRenderer RightSide;
    ModelRenderer RightSideB;
    ModelRenderer CannonBackStoper;
    ModelRenderer CannonBarrelBrace;
    ModelRenderer CannonS1;
    ModelRenderer CannonS2;
    ModelRenderer CannonS3;
    ModelRenderer CannonS4;
    ModelRenderer CannonBarrel;
    ModelRenderer CannonBrace;
    ModelRenderer CannonLBeam;
    ModelRenderer CannonRBeam;
    ModelRenderer CannonBody;
    ModelRenderer AmmoCase;
    ModelRenderer AmmoB1;
    ModelRenderer AmmoB2;
    ModelRenderer AmmoB3;
    ModelRenderer AmmoB3B;
    ModelRenderer AmmoB2B;
    ModelRenderer AmmoB1B;
    ModelRenderer Shell1Tip;
    ModelRenderer Shell1Body;
    ModelRenderer Shell1Tip2;
    ModelRenderer Shell1Body2;
    ModelRenderer Shell1Tip3;
    ModelRenderer Shell1Body3;
    ModelRenderer Shell1Tip4;
    ModelRenderer Shell1Body4;
    ModelRenderer Shell1Tip5;
    ModelRenderer Shell1Body5;
    ModelRenderer AmmoCaseT;

    public ModelGunTurret()
    {
        textureWidth = 128;
        textureHeight = 128;

        BasePlate = new ModelRenderer(this, 0, 113);
        BasePlate.addBox(-7.5F, 3F, -6.5F, 15, 2, 13);
        BasePlate.setRotationPoint(0F, 20F, 0F);
        BasePlate.setTextureSize(128, 128);
        BasePlate.mirror = true;
        setRotation(BasePlate, 0F, 0F, 0F);
        neck = new ModelRenderer(this, 19, 0);
        neck.addBox(-4F, 0F, -4F, 8, 5, 8);
        neck.setRotationPoint(0F, 19F, 0F);
        neck.setTextureSize(128, 128);
        neck.mirror = true;
        setRotation(neck, 0F, 0.3926991F, 0F);
        neck2 = new ModelRenderer(this, 19, 0);
        neck2.addBox(-4F, 0F, -4F, 8, 5, 8);
        neck2.setRotationPoint(0F, 19F, 0F);
        neck2.setTextureSize(128, 128);
        neck2.mirror = true;
        setRotation(neck2, 0F, 0F, 0F);
        neck3 = new ModelRenderer(this, 19, 0);
        neck3.addBox(-4F, 0F, -4F, 8, 5, 8);
        neck3.setRotationPoint(0F, 19F, 0F);
        neck3.setTextureSize(128, 128);
        neck3.mirror = true;
        setRotation(neck3, 0F, 1.178097F, 0F);
        neck4 = new ModelRenderer(this, 19, 0);
        neck4.addBox(-4F, 0F, -4F, 8, 5, 8);
        neck4.setRotationPoint(0F, 19F, 0F);
        neck4.setTextureSize(128, 128);
        neck4.mirror = true;
        setRotation(neck4, 0F, 0.7853982F, 0F);
        Base = new ModelRenderer(this, 0, 97);
        Base.addBox(-7.5F, -2F, -6.5F, 15, 2, 13);
        Base.setRotationPoint(0F, 19F, 0F);
        Base.setTextureSize(128, 128);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        LeftSide = new ModelRenderer(this, 0, 59);
        LeftSide.addBox(5.5F, -13F, -5.5F, 2, 2, 11);
        LeftSide.setRotationPoint(0F, 19F, 0F);
        LeftSide.setTextureSize(128, 128);
        LeftSide.mirror = true;
        setRotation(LeftSide, 0F, 0F, 0F);
        LeftSideB = new ModelRenderer(this, 0, 73);
        LeftSideB.addBox(5.5F, -11F, -6.5F, 2, 9, 13);
        LeftSideB.setRotationPoint(0F, 19F, 0F);
        LeftSideB.setTextureSize(128, 128);
        LeftSideB.mirror = true;
        setRotation(LeftSideB, 0F, 0F, 0F);
        RightSide = new ModelRenderer(this, 26, 59);
        RightSide.addBox(-5.5F, -13F, -5.5F, 2, 2, 11);
        RightSide.setRotationPoint(0F, 19F, 0F);
        RightSide.setTextureSize(128, 128);
        RightSide.mirror = true;
        setRotation(RightSide, 0F, 0F, 0F);
        RightSideB = new ModelRenderer(this, 31, 73);
        RightSideB.addBox(-5.5F, -11F, -6.5F, 2, 9, 13);
        RightSideB.setRotationPoint(0F, 19F, 0F);
        RightSideB.setTextureSize(128, 128);
        RightSideB.mirror = true;
        setRotation(RightSideB, 0F, 0F, 0F);
        CannonBackStoper = new ModelRenderer(this, 0, 22);
        CannonBackStoper.addBox(-2.5F, -3F, 7.5F, 7, 7, 4);
        CannonBackStoper.setRotationPoint(0F, 10F, 0F);
        CannonBackStoper.setTextureSize(128, 128);
        CannonBackStoper.mirror = true;
        setRotation(CannonBackStoper, -0.1745329F, 0F, 0F);
        CannonBarrelBrace = new ModelRenderer(this, 63, 61);
        CannonBarrelBrace.addBox(0F, 2.5F, -16.5F, 2, 2, 13);
        CannonBarrelBrace.setRotationPoint(0F, 10F, 0F);
        CannonBarrelBrace.setTextureSize(128, 128);
        CannonBarrelBrace.mirror = true;
        setRotation(CannonBarrelBrace, -0.1745329F, 0F, 0F);
        CannonS1 = new ModelRenderer(this, 101, 44);
        CannonS1.addBox(2.5F, -2.5F, -19.5F, 2, 5, 3);
        CannonS1.setRotationPoint(0F, 10F, 0F);
        CannonS1.setTextureSize(128, 128);
        CannonS1.mirror = true;
        setRotation(CannonS1, -0.1745329F, 0F, 0F);
        CannonS2 = new ModelRenderer(this, 92, 53);
        CannonS2.addBox(-1.5F, 1.5F, -19.5F, 5, 2, 3);
        CannonS2.setRotationPoint(0F, 10F, 0F);
        CannonS2.setTextureSize(128, 128);
        CannonS2.mirror = true;
        setRotation(CannonS2, -0.1745329F, 0F, 0F);
        CannonS3 = new ModelRenderer(this, 89, 44);
        CannonS3.addBox(-2.5F, -2.5F, -19.5F, 2, 5, 3);
        CannonS3.setRotationPoint(0F, 10F, 0F);
        CannonS3.setTextureSize(128, 128);
        CannonS3.mirror = true;
        setRotation(CannonS3, -0.1745329F, 0F, 0F);
        CannonS4 = new ModelRenderer(this, 92, 38);
        CannonS4.addBox(-1.5F, -3.5F, -19.5F, 5, 2, 3);
        CannonS4.setRotationPoint(0F, 10F, 0F);
        CannonS4.setTextureSize(128, 128);
        CannonS4.mirror = true;
        setRotation(CannonS4, -0.1745329F, 0F, 0F);
        CannonBarrel = new ModelRenderer(this, 53, 45);
        CannonBarrel.addBox(-1.5F, -2.5F, -16.5F, 5, 5, 8);
        CannonBarrel.setRotationPoint(0F, 10F, 0F);
        CannonBarrel.setTextureSize(128, 128);
        CannonBarrel.mirror = true;
        setRotation(CannonBarrel, -0.1745329F, 0F, 0F);
        CannonBrace = new ModelRenderer(this, 43, 34);
        CannonBrace.addBox(-2F, -3F, -8.5F, 6, 6, 4);
        CannonBrace.setRotationPoint(0F, 10F, 0F);
        CannonBrace.setTextureSize(128, 128);
        CannonBrace.mirror = true;
        setRotation(CannonBrace, -0.1745329F, 0F, 0F);
        CannonLBeam = new ModelRenderer(this, 66, 34);
        CannonLBeam.addBox(5F, -2F, -2F, 2, 4, 4);
        CannonLBeam.setRotationPoint(0F, 10F, 0F);
        CannonLBeam.setTextureSize(128, 128);
        CannonLBeam.mirror = true;
        setRotation(CannonLBeam, -0.1745329F, 0F, 0F);
        CannonRBeam = new ModelRenderer(this, 66, 34);
        CannonRBeam.addBox(-4F, -2F, -2F, 2, 4, 4);
        CannonRBeam.setRotationPoint(0F, 10F, 0F);
        CannonRBeam.setTextureSize(128, 128);
        CannonRBeam.mirror = true;
        setRotation(CannonRBeam, -0.1745329F, 0F, 0F);
        CannonBody = new ModelRenderer(this, 0, 34);
        CannonBody.addBox(-3F, -4F, -4.5F, 8, 9, 12);
        CannonBody.setRotationPoint(0F, 10F, 0F);
        CannonBody.setTextureSize(128, 128);
        CannonBody.mirror = true;
        setRotation(CannonBody, -0.1745329F, 0F, 0F);
        AmmoCase = new ModelRenderer(this, 91, 105);
        AmmoCase.addBox(-10.5F, -5F, -6.5F, 5, 9, 14);
        AmmoCase.setRotationPoint(0F, 20F, 0F);
        AmmoCase.setTextureSize(128, 128);
        AmmoCase.mirror = true;
        setRotation(AmmoCase, 0F, 0F, 0F);
        AmmoB1 = new ModelRenderer(this, 114, 98);
        AmmoB1.addBox(-0.5F, -7F, -3.5F, 3, 5, 1);
        AmmoB1.setRotationPoint(0F, 9F, 0F);
        AmmoB1.setTextureSize(128, 128);
        AmmoB1.mirror = true;
        setRotation(AmmoB1, -0.1745329F, 0F, 0F);
        AmmoB2 = new ModelRenderer(this, 96, 93);
        AmmoB2.addBox(-8.5F, -9F, -3.5F, 10, 3, 1);
        AmmoB2.setRotationPoint(0F, 9F, 0F);
        AmmoB2.setTextureSize(128, 128);
        AmmoB2.mirror = true;
        setRotation(AmmoB2, -0.1745329F, 0F, 0F);
        AmmoB3 = new ModelRenderer(this, 91, 98);
        AmmoB3.addBox(-9.5F, -7F, -3.5F, 3, 5, 1);
        AmmoB3.setRotationPoint(0F, 9F, 0F);
        AmmoB3.setTextureSize(128, 128);
        AmmoB3.mirror = true;
        setRotation(AmmoB3, -0.1745329F, 0F, 0F);
        AmmoB3B = new ModelRenderer(this, 91, 98);
        AmmoB3B.addBox(-9.5F, -7F, 6.5F, 3, 5, 1);
        AmmoB3B.setRotationPoint(0F, 9F, 0F);
        AmmoB3B.setTextureSize(128, 128);
        AmmoB3B.mirror = true;
        setRotation(AmmoB3B, -0.1745329F, 0F, 0F);
        AmmoB2B = new ModelRenderer(this, 96, 93);
        AmmoB2B.addBox(-8.5F, -9F, 6.5F, 10, 3, 1);
        AmmoB2B.setRotationPoint(0F, 9F, 0F);
        AmmoB2B.setTextureSize(128, 128);
        AmmoB2B.mirror = true;
        setRotation(AmmoB2B, -0.1745329F, 0F, 0F);
        AmmoB1B = new ModelRenderer(this, 114, 98);
        AmmoB1B.addBox(-0.5F, -7F, 6.5F, 3, 5, 1);
        AmmoB1B.setRotationPoint(0F, 9F, 0F);
        AmmoB1B.setTextureSize(128, 128);
        AmmoB1B.mirror = true;
        setRotation(AmmoB1B, -0.1745329F, 0F, 0F);
        Shell1Tip = new ModelRenderer(this, 112, 82);
        Shell1Tip.addBox(-9F, -6.5F, -2.5F, 2, 2, 2);
        Shell1Tip.setRotationPoint(0F, 10F, 0F);
        Shell1Tip.setTextureSize(128, 128);
        Shell1Tip.mirror = true;
        setRotation(Shell1Tip, -0.1745329F, 0F, 0F);
        Shell1Body = new ModelRenderer(this, 91, 82);
        Shell1Body.addBox(-9.5F, -7F, -0.5F, 3, 3, 7);
        Shell1Body.setRotationPoint(0F, 10F, 0F);
        Shell1Body.setTextureSize(128, 128);
        Shell1Body.mirror = true;
        setRotation(Shell1Body, -0.1745329F, 0F, 0F);
        Shell1Tip2 = new ModelRenderer(this, 112, 82);
        Shell1Tip2.addBox(-8F, -9.5F, -2.5F, 2, 2, 2);
        Shell1Tip2.setRotationPoint(0F, 10F, 0F);
        Shell1Tip2.setTextureSize(128, 128);
        Shell1Tip2.mirror = true;
        setRotation(Shell1Tip2, -0.1745329F, 0F, 0F);
        Shell1Body2 = new ModelRenderer(this, 91, 82);
        Shell1Body2.addBox(-8.5F, -10F, -0.5F, 3, 3, 7);
        Shell1Body2.setRotationPoint(0F, 10F, 0F);
        Shell1Body2.setTextureSize(128, 128);
        Shell1Body2.mirror = true;
        setRotation(Shell1Body2, -0.1745329F, 0F, 0F);
        Shell1Tip3 = new ModelRenderer(this, 112, 82);
        Shell1Tip3.addBox(-4.5F, -9.5F, -2.5F, 2, 2, 2);
        Shell1Tip3.setRotationPoint(0F, 10F, 0F);
        Shell1Tip3.setTextureSize(128, 128);
        Shell1Tip3.mirror = true;
        setRotation(Shell1Tip3, -0.1745329F, 0F, 0F);
        Shell1Body3 = new ModelRenderer(this, 91, 82);
        Shell1Body3.addBox(-5F, -10F, -0.5F, 3, 3, 7);
        Shell1Body3.setRotationPoint(0F, 10F, 0F);
        Shell1Body3.setTextureSize(128, 128);
        Shell1Body3.mirror = true;
        setRotation(Shell1Body3, -0.1745329F, 0F, 0F);
        Shell1Tip4 = new ModelRenderer(this, 112, 82);
        Shell1Tip4.addBox(-1F, -9.5F, -2.5F, 2, 2, 2);
        Shell1Tip4.setRotationPoint(0F, 10F, 0F);
        Shell1Tip4.setTextureSize(128, 128);
        Shell1Tip4.mirror = true;
        setRotation(Shell1Tip4, -0.1745329F, 0F, 0F);
        Shell1Body4 = new ModelRenderer(this, 91, 82);
        Shell1Body4.addBox(-1.5F, -10F, -0.5F, 3, 3, 7);
        Shell1Body4.setRotationPoint(0F, 10F, 0F);
        Shell1Body4.setTextureSize(128, 128);
        Shell1Body4.mirror = true;
        setRotation(Shell1Body4, -0.1745329F, 0F, 0F);
        Shell1Tip5 = new ModelRenderer(this, 112, 82);
        Shell1Tip5.addBox(0F, -6.5F, -2.5F, 2, 2, 2);
        Shell1Tip5.setRotationPoint(0F, 10F, 0F);
        Shell1Tip5.setTextureSize(128, 128);
        Shell1Tip5.mirror = true;
        setRotation(Shell1Tip5, -0.1745329F, 0F, 0F);
        Shell1Body5 = new ModelRenderer(this, 91, 82);
        Shell1Body5.addBox(-0.5F, -7F, -0.5F, 3, 3, 7);
        Shell1Body5.setRotationPoint(0F, 10F, 0F);
        Shell1Body5.setTextureSize(128, 128);
        Shell1Body5.mirror = true;
        setRotation(Shell1Body5, -0.1745329F, 0F, 0F);
        AmmoCaseT = new ModelRenderer(this, 55, 0);
        AmmoCaseT.addBox(-10.5F, -2F, -4.5F, 5, 9, 13);
        AmmoCaseT.setRotationPoint(0F, 9F, 0F);
        AmmoCaseT.setTextureSize(128, 128);
        AmmoCaseT.mirror = true;
        setRotation(AmmoCaseT, -0.1745329F, 0F, 0F);
    }

    /** renders the stationary parts of the model */
    public void render(float f5)
    {
        BasePlate.render(f5);
    }

    /** renders the motion body of the model
     * 
     * @param f5 */
    public void renderBody(float f5)
    {
        neck.render(f5);
        neck2.render(f5);
        neck3.render(f5);
        neck4.render(f5);
        Base.render(f5);
        LeftSide.render(f5);
        LeftSideB.render(f5);
        RightSide.render(f5);
        RightSideB.render(f5);
        AmmoCase.render(f5);
    }

    /** renders the cannon part of the model as well as other parts that move with it
     * 
     * @param f5 */
    public void renderCannon(float f5)
    {
        CannonBackStoper.render(f5);
        CannonBarrelBrace.render(f5);
        CannonS1.render(f5);
        CannonS2.render(f5);
        CannonS3.render(f5);
        CannonS4.render(f5);
        CannonBarrel.render(f5);
        CannonBrace.render(f5);
        CannonLBeam.render(f5);
        CannonRBeam.render(f5);
        CannonBody.render(f5);
        AmmoB1.render(f5);
        AmmoB2.render(f5);
        AmmoB3.render(f5);
        AmmoB3B.render(f5);
        AmmoB2B.render(f5);
        AmmoB1B.render(f5);
        Shell1Tip.render(f5);
        Shell1Body.render(f5);
        Shell1Tip2.render(f5);
        Shell1Body2.render(f5);
        Shell1Tip3.render(f5);
        Shell1Body3.render(f5);
        Shell1Tip4.render(f5);
        Shell1Body4.render(f5);
        Shell1Tip5.render(f5);
        Shell1Body5.render(f5);
        AmmoCaseT.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
