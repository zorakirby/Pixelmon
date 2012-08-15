package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelFlareon extends ModelBase
{
  //fields
    ModelRenderer FluffBase;
    ModelRenderer FluffSeg1;
    ModelRenderer FluffSeg2;
    ModelRenderer FluffTip;
    ModelRenderer ManeRightAngle;
    ModelRenderer ManeLeftAngle;
    ModelRenderer Body;
    ModelRenderer TailBase;
    ModelRenderer TailSeg1;
    ModelRenderer TailSeg2;
    ModelRenderer TailSeg3;
    ModelRenderer TailSeg4;
    ModelRenderer TailSeg5;
    ModelRenderer TailSeg6;
    ModelRenderer HEADBASE;
    ModelRenderer FRONTRIGHTLEG;
    ModelRenderer FRONTLEFETLEG;
    ModelRenderer REARRIGHTLEG;
    ModelRenderer REARLEFTLEG;
  
  public ModelFlareon()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      FluffBase = new ModelRenderer(this, 52, 29);
      FluffBase.addBox(-1F, -3.7F, -2.5F, 2, 1, 2);
      FluffBase.setRotationPoint(0F, 16.5F, -4.4F);
      FluffBase.setTextureSize(64, 32);
      FluffBase.mirror = true;
      setRotation(FluffBase, 0F, 0F, 0F);
      FluffSeg1 = new ModelRenderer(this, 52, 29);
      FluffSeg1.addBox(-1F, -4.3F, -2F, 2, 1, 2);
      FluffSeg1.setRotationPoint(0F, 16.5F, -4.4F);
      FluffSeg1.setTextureSize(64, 32);
      FluffSeg1.mirror = true;
      setRotation(FluffSeg1, 0F, 0F, 0F);
      FluffSeg2 = new ModelRenderer(this, 52, 29);
      FluffSeg2.addBox(-1F, -4.7F, -0.8F, 2, 1, 1);
      FluffSeg2.setRotationPoint(0F, 16.5F, -4.4F);
      FluffSeg2.setTextureSize(64, 32);
      FluffSeg2.mirror = true;
      setRotation(FluffSeg2, 0F, 0F, 0F);
      FluffTip = new ModelRenderer(this, 52, 29);
      FluffTip.addBox(-0.5F, -5.1F, -0.3F, 1, 1, 1);
      FluffTip.setRotationPoint(0F, 16.5F, -4.4F);
      FluffTip.setTextureSize(64, 32);
      FluffTip.mirror = true;
      setRotation(FluffTip, 0F, 0F, 0F);
      ManeRightAngle = new ModelRenderer(this, 0, 10);
      ManeRightAngle.addBox(-3.5F, -3.5F, -4F, 7, 6, 4);
      ManeRightAngle.setRotationPoint(0F, 18F, -1.2F);
      ManeRightAngle.setTextureSize(64, 32);
      ManeRightAngle.mirror = true;
      setRotation(ManeRightAngle, -0.122173F, -0.0174533F, -0.1745329F);
      ManeRightAngle.mirror = false;
      ManeLeftAngle = new ModelRenderer(this, 0, 10);
      ManeLeftAngle.addBox(-3.5F, -3.5F, -4F, 7, 6, 4);
      ManeLeftAngle.setRotationPoint(0F, 18F, -1.2F);
      ManeLeftAngle.setTextureSize(64, 32);
      ManeLeftAngle.mirror = true;
      setRotation(ManeLeftAngle, -0.122173F, 0.0174533F, 0.1745329F);
      Body = new ModelRenderer(this, 0, 20);
      Body.addBox(-2.5F, -3F, -4.4F, 5, 4, 8);
      Body.setRotationPoint(0F, 18.5F, 2F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      TailBase = new ModelRenderer(this, 52, 28);
      TailBase.addBox(-1.5F, -1.5F, 0F, 3, 3, 1);
      TailBase.setRotationPoint(0F, 17.1F, 5F);
      TailBase.setTextureSize(64, 32);
      TailBase.mirror = true;
      setRotation(TailBase, 0F, 0F, 0F);
      TailSeg1 = new ModelRenderer(this, 51, 26);
      TailSeg1.addBox(-2F, -1.7F, 0.7F, 4, 4, 2);
      TailSeg1.setRotationPoint(0F, 17.1F, 5F);
      TailSeg1.setTextureSize(64, 32);
      TailSeg1.mirror = true;
      setRotation(TailSeg1, 0.1570796F, 0F, 0F);
      TailSeg2 = new ModelRenderer(this, 50, 25);
      TailSeg2.addBox(-2.5F, -1.8F, 1.5F, 5, 5, 2);
      TailSeg2.setRotationPoint(0F, 17.1F, 5F);
      TailSeg2.setTextureSize(64, 32);
      TailSeg2.mirror = true;
      setRotation(TailSeg2, 0.3665191F, 0F, 0F);
      TailSeg3 = new ModelRenderer(this, 50, 26);
      TailSeg3.addBox(-2F, -0.7F, 2.4F, 4, 4, 2);
      TailSeg3.setRotationPoint(0F, 17.1F, 5F);
      TailSeg3.setTextureSize(64, 32);
      TailSeg3.mirror = true;
      setRotation(TailSeg3, 0.5759587F, 0F, 0F);
      TailSeg4 = new ModelRenderer(this, 51, 27);
      TailSeg4.addBox(-1.5F, 0.5F, 3.5F, 3, 3, 2);
      TailSeg4.setRotationPoint(0F, 17.1F, 5F);
      TailSeg4.setTextureSize(64, 32);
      TailSeg4.mirror = true;
      setRotation(TailSeg4, 0.7853982F, 0F, 0F);
      TailSeg5 = new ModelRenderer(this, 51, 28);
      TailSeg5.addBox(-1F, 2F, 4.3F, 2, 2, 2);
      TailSeg5.setRotationPoint(0F, 17.1F, 5F);
      TailSeg5.setTextureSize(64, 32);
      TailSeg5.mirror = true;
      setRotation(TailSeg5, 1.012291F, 0F, 0F);
      TailSeg6 = new ModelRenderer(this, 51, 28);
      TailSeg6.addBox(-0.5F, 3.9F, 4.5F, 1, 1, 2);
      TailSeg6.setRotationPoint(0F, 17.1F, 5F);
      TailSeg6.setTextureSize(64, 32);
      TailSeg6.mirror = true;
      setRotation(TailSeg6, 1.27409F, 0F, 0F);
    HEADBASE = new ModelRenderer(this, "HEADBASE");
    HEADBASE.setRotationPoint(0F, 16.5F, -4.4F);
    setRotation(HEADBASE, 0F, 0F, 0F);
    HEADBASE.mirror = true;
      ModelRenderer  EarTipRight = new ModelRenderer(this, 17, 4);
      EarTipRight.addBox(-1F, -6F, -1.4F, 1, 1, 1);
      EarTipRight.setRotationPoint(0F, 0F, 0F);
      EarTipRight.setTextureSize(64, 32);
      EarTipRight.mirror = true;
      setRotation(EarTipRight, -0.0523599F, -0.0872665F, 0.6981317F);
      ModelRenderer  EarRight = new ModelRenderer(this, 16, 6);
      EarRight.addBox(-0.5F, -5F, -1.4F, 2, 3, 1);
      EarRight.setRotationPoint(0F, 0F, 0F);
      EarRight.setTextureSize(64, 32);
      EarRight.mirror = true;
      setRotation(EarRight, -0.0523599F, 0.0872665F, -0.6981317F);
      ModelRenderer   EarTipLeft = new ModelRenderer(this, 17, 4);
      EarTipLeft.addBox(0F, -6F, -1.4F, 1, 1, 1);
      EarTipLeft.setRotationPoint(0F, 0F, 0F);
      EarTipLeft.setTextureSize(64, 32);
      EarTipLeft.mirror = true;
      setRotation(EarTipLeft, -0.0523599F, 0.0872665F, -0.6981317F);
      ModelRenderer  EarLeft = new ModelRenderer(this, 16, 6);
      EarLeft.addBox(-1.5F, -5F, -1.4F, 2, 3, 1);
      EarLeft.setRotationPoint(0F, 0F, 0F);
      EarLeft.setTextureSize(64, 32);
      EarLeft.mirror = true;
      setRotation(EarLeft, -0.0523599F, -0.0872665F, 0.6981317F);
      EarLeft.mirror = false;
      ModelRenderer  Nose = new ModelRenderer(this, 0, 0);
      Nose.addBox(-1.5F, -1.3F, -3.5F, 3, 2, 1);
      Nose.setRotationPoint(0F, 0F, 0F);
      Nose.setTextureSize(64, 32);
      Nose.mirror = true;
      setRotation(Nose, 0.2094395F, 0F, 0F);
      ModelRenderer  Head = new ModelRenderer(this, 0, 3);
      Head.addBox(-2F, -3F, -3F, 4, 4, 3);
      Head.setRotationPoint(0F, 0F, 0F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0.0872665F, 0F, 0F);
      
      HEADBASE.addChild(Head);
      HEADBASE.addChild(Nose);
      HEADBASE.addChild(EarRight);
      HEADBASE.addChild(EarLeft);
      HEADBASE.addChild(EarTipRight);
      HEADBASE.addChild(EarTipLeft);
      
    FRONTRIGHTLEG = new ModelRenderer(this, "FRONTRIGHTLEG");
    FRONTRIGHTLEG.setRotationPoint(-2F, 16.46667F, -3F);
    setRotation(FRONTRIGHTLEG, 0F, 0F, 0F);
    FRONTRIGHTLEG.mirror = true;
      ModelRenderer  FrontToesRight = new ModelRenderer(this, 8, 1);
      FrontToesRight.addBox(-1F, 3F, -1.5F, 2, 1, 1);
      FrontToesRight.setRotationPoint(0F, 3.5F, 0F);
      FrontToesRight.setTextureSize(64, 32);
      FrontToesRight.mirror = true;
      setRotation(FrontToesRight, 0F, 0F, 0F);
      ModelRenderer  FrontRightLeg = new ModelRenderer(this, 52, 0);
      FrontRightLeg.addBox(-1F, 0F, -1F, 2, 4, 2);
      FrontRightLeg.setRotationPoint(0F, 3.5F, 0F);
      FrontRightLeg.setTextureSize(64, 32);
      FrontRightLeg.mirror = true;
      setRotation(FrontRightLeg, 0F, 0F, 0F);

      FRONTRIGHTLEG.addChild(FrontRightLeg);
      FRONTRIGHTLEG.addChild(FrontToesRight);
      
    FRONTLEFETLEG = new ModelRenderer(this, "FRONTLEFETLEG");
    FRONTLEFETLEG.setRotationPoint(2F, 16.5F, -3F);
    setRotation(FRONTLEFETLEG, 0F, 0F, 0F);
    FRONTLEFETLEG.mirror = true;
      ModelRenderer FrontToesLeft = new ModelRenderer(this, 8, 1);
      FrontToesLeft.addBox(-1F, 3F, -1.5F, 2, 1, 1);
      FrontToesLeft.setRotationPoint(0F, 3.5F, 0F);
      FrontToesLeft.setTextureSize(64, 32);
      FrontToesLeft.mirror = true;
      setRotation(FrontToesLeft, 0F, 0F, 0F);
      FrontToesLeft.mirror = false;
      ModelRenderer FrontLeftLeg = new ModelRenderer(this, 52, 0);
      FrontLeftLeg.addBox(-1F, 0F, -1F, 2, 4, 2);
      FrontLeftLeg.setRotationPoint(0F, 3.5F, 0F);
      FrontLeftLeg.setTextureSize(64, 32);
      FrontLeftLeg.mirror = true;
      setRotation(FrontLeftLeg, 0F, 0F, 0F);
      FrontLeftLeg.mirror = false;

      FRONTLEFETLEG.addChild(FrontLeftLeg);
      FRONTLEFETLEG.addChild(FrontToesLeft);
      
    REARRIGHTLEG = new ModelRenderer(this, "REARRIGHTLEG");
    REARRIGHTLEG.setRotationPoint(-2F, 18F, 4F);
    setRotation(REARRIGHTLEG, 0F, 0F, 0F);
    REARRIGHTLEG.mirror = true;
      ModelRenderer  RearUpperRightLeg = new ModelRenderer(this, 52, 0);
      RearUpperRightLeg.addBox(-2F, -0.5F, -1.5F, 2, 4, 3);
      RearUpperRightLeg.setRotationPoint(1.4F, -1F, 0F);
      RearUpperRightLeg.setTextureSize(64, 32);
      RearUpperRightLeg.mirror = true;
      setRotation(RearUpperRightLeg, 0.0872665F, 0F, 0F);
      ModelRenderer RearKneeRight = new ModelRenderer(this, 8, 1);
      RearKneeRight.addBox(-2F, 1.7F, -3.3F, 2, 1, 1);
      RearKneeRight.setRotationPoint(1.4F, -1F, 0F);
      RearKneeRight.setTextureSize(64, 32);
      RearKneeRight.mirror = true;
      setRotation(RearKneeRight, 0.7853982F, 0F, 0F);
      ModelRenderer  RearToesRight = new ModelRenderer(this, 8, 1);
      RearToesRight.addBox(-2F, 6F, -1.3F, 2, 1, 1);
      RearToesRight.setRotationPoint(1.4F, -1F, 0F);
      RearToesRight.setTextureSize(64, 32);
      RearToesRight.mirror = true;
      setRotation(RearToesRight, 0F, 0F, 0F);
      ModelRenderer  RearlowerRightLeg = new ModelRenderer(this, 52, 0);
      RearlowerRightLeg.addBox(-2F, 2.9F, 0.4F, 2, 4, 2);
      RearlowerRightLeg.setRotationPoint(1.4F, -1F, 0F);
      RearlowerRightLeg.setTextureSize(64, 32);
      RearlowerRightLeg.mirror = true;
      setRotation(RearlowerRightLeg, -0.2094395F, 0F, 0F);

      REARRIGHTLEG.addChild(RearUpperRightLeg);
      REARRIGHTLEG.addChild(RearlowerRightLeg);
      REARRIGHTLEG.addChild(RearKneeRight);
      REARRIGHTLEG.addChild(RearToesRight);
      
    REARLEFTLEG = new ModelRenderer(this, "REARLEFTLEG");
    REARLEFTLEG.setRotationPoint(2F, 18F, 4F);
    setRotation(REARLEFTLEG, 0F, 0F, 0F);
    REARLEFTLEG.mirror = true;
    ModelRenderer RearUpperLeftLeg = new ModelRenderer(this, 52, 0);
      RearUpperLeftLeg.addBox(0F, -0.5F, -1.5F, 2, 4, 3);
      RearUpperLeftLeg.setRotationPoint(-1.4F, -1F, 0F);
      RearUpperLeftLeg.setTextureSize(64, 32);
      RearUpperLeftLeg.mirror = true;
      setRotation(RearUpperLeftLeg, 0.0872665F, 0F, 0F);
      RearUpperLeftLeg.mirror = false;
      ModelRenderer    RearKneeLeft = new ModelRenderer(this, 8, 1);
      RearKneeLeft.addBox(0F, 1.7F, -3.3F, 2, 1, 1);
      RearKneeLeft.setRotationPoint(-1.4F, -1F, 0F);
      RearKneeLeft.setTextureSize(64, 32);
      RearKneeLeft.mirror = true;
      setRotation(RearKneeLeft, 0.7853982F, 0F, 0F);
      RearKneeLeft.mirror = false;
      ModelRenderer  RearlowerLeftLeg = new ModelRenderer(this, 52, 0);
      RearlowerLeftLeg.addBox(0F, 2.9F, 0.4F, 2, 4, 2);
      RearlowerLeftLeg.setRotationPoint(-1.4F, -1F, 0F);
      RearlowerLeftLeg.setTextureSize(64, 32);
      RearlowerLeftLeg.mirror = true;
      setRotation(RearlowerLeftLeg, -0.1919862F, 0F, 0F);
      RearlowerLeftLeg.mirror = false;
      ModelRenderer RearToesLeft = new ModelRenderer(this, 8, 1);
      RearToesLeft.addBox(0F, 6F, -1.3F, 2, 1, 1);
      RearToesLeft.setRotationPoint(-1.4F, -1F, 0F);
      RearToesLeft.setTextureSize(64, 32);
      RearToesLeft.mirror = true;
      setRotation(RearToesLeft, 0F, 0F, 0F);
      RearToesLeft.mirror = false;
  
      REARLEFTLEG.addChild(RearUpperLeftLeg);
      REARLEFTLEG.addChild(RearlowerLeftLeg);
      REARLEFTLEG.addChild(RearKneeLeft);
      REARLEFTLEG.addChild(RearToesLeft);    
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    FluffBase.render(f5);
    FluffSeg1.render(f5);
    FluffSeg2.render(f5);
    FluffTip.render(f5);
    ManeRightAngle.render(f5);
    ManeLeftAngle.render(f5);
    Body.render(f5);
    TailBase.render(f5);
    TailSeg1.render(f5);
    TailSeg2.render(f5);
    TailSeg3.render(f5);
    TailSeg4.render(f5);
    TailSeg5.render(f5);
    TailSeg6.render(f5);
    HEADBASE.render(f5);
    FRONTRIGHTLEG.render(f5);
    FRONTLEFETLEG.render(f5);
    REARRIGHTLEG.render(f5);
    REARLEFTLEG.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
	HEADBASE.rotateAngleX = f4 / 57.29578F;
    HEADBASE.rotateAngleY = f3 / 57.29578F;
    REARRIGHTLEG.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
    REARLEFTLEG.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
    FRONTRIGHTLEG.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
    FRONTLEFETLEG.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
  }

}
