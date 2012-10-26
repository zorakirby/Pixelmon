package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelVenomoth extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer forehead;
    ModelRenderer nose;
    ModelRenderer eyeR;
    ModelRenderer eyeL;
    ModelRenderer pupilR;
    ModelRenderer pupilL;
    ModelRenderer stickthing1;
    ModelRenderer stickthing2;
    ModelRenderer stickthing3;
    ModelRenderer middle1;
    ModelRenderer middle2;
    ModelRenderer middle3;
    ModelRenderer back1;
    ModelRenderer back2;
    ModelRenderer back3;
    ModelRenderer back4;
    ModelRenderer armR1;
    ModelRenderer armR2;
    ModelRenderer armR3;
    ModelRenderer armL1;
    ModelRenderer armL2;
    ModelRenderer armL3;
    ModelRenderer wingL1;
    ModelRenderer wingL2;
    ModelRenderer wingL3;
    ModelRenderer wingL4;
    ModelRenderer wingR1;
    ModelRenderer wingR2;
    ModelRenderer wingR3;
    ModelRenderer wingR4;
    ModelRenderer VenomothBase;
	ModelRenderer HeadBase;
	ModelRenderer LeftWing;
	ModelRenderer RightWing;
  
  public ModelVenomoth()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    VenomothBase = new ModelRenderer(this, "VenomothBase");
    VenomothBase.setRotationPoint(0F, 5F, 0F);
    setRotation(VenomothBase, 0F, 0F, 0F);
    VenomothBase.mirror = true;
      middle1 = new ModelRenderer(this, 48, 26);
      middle1.addBox(-2.5F, -5F, 4.5F, 4, 4, 2);
      middle1.setRotationPoint(0F, 0F, 0F);
      middle1.setTextureSize(64, 32);
      middle1.mirror = true;
      setRotation(middle1, 0F, 0F, 0F);
      middle2 = new ModelRenderer(this, 46, 25);
      middle2.addBox(-3F, -6F, 3F, 5, 5, 2);
      middle2.setRotationPoint(0F, 0F, 0F);
      middle2.setTextureSize(64, 32);
      middle2.mirror = true;
      setRotation(middle2, 0F, 0F, 0F);
      middle3 = new ModelRenderer(this, 48, 26);
      middle3.addBox(-2.5F, -5F, 1.5F, 4, 4, 2);
      middle3.setRotationPoint(0F, 0F, 0F);
      middle3.setTextureSize(64, 32);
      middle3.mirror = true;
      setRotation(middle3, 0F, 0F, 0F);
      back1 = new ModelRenderer(this, 40, 10);
      back1.addBox(-3F, -3F, -4F, 5, 2, 5);
      back1.setRotationPoint(0F, 0F, 0F);
      back1.setTextureSize(64, 32);
      back1.mirror = true;
      setRotation(back1, -1.047198F, 0F, 0F);
      back2 = new ModelRenderer(this, 36, 0);
      back2.addBox(-3.5F, -1.7F, -4.5F, 6, 3, 6);
      back2.setRotationPoint(0F, 0F, 0F);
      back2.setTextureSize(64, 32);
      back2.mirror = true;
      setRotation(back2, -1.047198F, 0F, 0F);
      back3 = new ModelRenderer(this, 40, 10);
      back3.addBox(-3F, 0F, -4F, 5, 3, 5);
      back3.setRotationPoint(0F, 0F, 0F);
      back3.setTextureSize(64, 32);
      back3.mirror = true;
      setRotation(back3, -1.047198F, 0F, 0F);
      back4 = new ModelRenderer(this, 48, 19);
      back4.addBox(-2F, 2.4F, -3F, 3, 2, 3);
      back4.setRotationPoint(0F, 0F, 0F);
      back4.setTextureSize(64, 32);
      back4.mirror = true;
      setRotation(back4, -1.047198F, 0F, 0F);
      armR1 = new ModelRenderer(this, 60, 30);
      armR1.addBox(0F, -1.5F, 5F, 1, 1, 1);
      armR1.setRotationPoint(0F, 0F, 0F);
      armR1.setTextureSize(64, 32);
      armR1.mirror = true;
      setRotation(armR1, 0F, 0F, 0F);
      armR2 = new ModelRenderer(this, 60, 30);
      armR2.addBox(0F, -1.5F, 3.5F, 1, 1, 1);
      armR2.setRotationPoint(0F, 0F, 0F);
      armR2.setTextureSize(64, 32);
      armR2.mirror = true;
      setRotation(armR2, 0F, 0F, 0F);
      armR3 = new ModelRenderer(this, 60, 30);
      armR3.addBox(0F, -2.7F, 0.5F, 1, 1, 1);
      armR3.setRotationPoint(0F, 0F, 0F);
      armR3.setTextureSize(64, 32);
      armR3.mirror = true;
      setRotation(armR3, -1.047198F, 0F, 0F);
      armL1 = new ModelRenderer(this, 60, 30);
      armL1.addBox(-2F, -1.5F, 5F, 1, 1, 1);
      armL1.setRotationPoint(0F, 0F, 0F);
      armL1.setTextureSize(64, 32);
      armL1.mirror = true;
      setRotation(armL1, 0F, 0F, 0F);
      armL2 = new ModelRenderer(this, 60, 30);
      armL2.addBox(-2F, -1.5F, 3.5F, 1, 1, 1);
      armL2.setRotationPoint(0F, 0F, 0F);
      armL2.setTextureSize(64, 32);
      armL2.mirror = true;
      setRotation(armL2, 0F, 0F, 0F);
      armL3 = new ModelRenderer(this, 60, 30);
      armL3.addBox(-2F, -2.7F, 0.5F, 1, 1, 1);
      armL3.setRotationPoint(0F, 0F, 0F);
      armL3.setTextureSize(64, 32);
      armL3.mirror = true;
      setRotation(armL3, -1.047198F, 0F, 0F);
      
      VenomothBase.addChild(middle1);
      VenomothBase.addChild(middle2);
      VenomothBase.addChild(middle3);
      VenomothBase.addChild(back1);
      VenomothBase.addChild(back2);
      VenomothBase.addChild(back3);
      VenomothBase.addChild(back4);
      VenomothBase.addChild(armR1);
      VenomothBase.addChild(armR2);
      VenomothBase.addChild(armR3);
      VenomothBase.addChild(armL1);
      VenomothBase.addChild(armL2);
      VenomothBase.addChild(armL3);
      
    RightWing = new ModelRenderer(this, "RightWing");
    RightWing.setRotationPoint(-2.5F, -5F, 2.5F);
    setRotation(RightWing, 0F, 0F, 0F);
    RightWing.mirror = true;
      wingR1 = new ModelRenderer(this, 0, 23);
      wingR1.addBox(-11F, -0.8F, -4F, 13, 0, 9);
      wingR1.setRotationPoint(0F, 0F, 0F);
      wingR1.setTextureSize(64, 32);
      wingR1.mirror = true;
      setRotation(wingR1, 0.2617994F, 0F, 0.5235988F);
      wingR2 = new ModelRenderer(this, 0, 18);
      wingR2.addBox(-13F, -0.8F, -1F, 14, 0, 5);
      wingR2.setRotationPoint(0F, 0F, 0F);
      wingR2.setTextureSize(64, 32);
      wingR2.mirror = true;
      setRotation(wingR2, 0.2617994F, 0F, 0.5235988F);
      wingR3 = new ModelRenderer(this, 0, 13);
      wingR3.addBox(-8F, -1.3F, -7.5F, 9, 0, 5);
      wingR3.setRotationPoint(0F, 0F, 0F);
      wingR3.setTextureSize(64, 32);
      wingR3.mirror = true;
      setRotation(wingR3, 0.5235988F, -0.0872665F, 0.5235988F);
      wingR4 = new ModelRenderer(this, 20, 10);
      wingR4.addBox(-6F, -1.3F, -10.5F, 7, 0, 3);
      wingR4.setRotationPoint(0F, 0F, 0F);
      wingR4.setTextureSize(64, 32);
      wingR4.mirror = true;
      setRotation(wingR4, 0.5235988F, -0.0872665F, 0.5235988F);
      
      RightWing.addChild(wingR1);
      RightWing.addChild(wingR2);
      RightWing.addChild(wingR3);
      RightWing.addChild(wingR4);
      
    LeftWing = new ModelRenderer(this, "LeftWing");
    LeftWing.setRotationPoint(1.5F, -5F, 2.5F);
    setRotation(LeftWing, 0F, 0F, 0F);
    LeftWing.mirror = true;
      wingL1 = new ModelRenderer(this, 0, 23);
      wingL1.addBox(-2F, -0.8F, -4F, 12, 0, 9);
      wingL1.setRotationPoint(0F, 0F, 0F);
      wingL1.setTextureSize(64, 32);
      wingL1.mirror = true;
      setRotation(wingL1, 0.2617994F, 0F, -0.5235988F);
      wingL2 = new ModelRenderer(this, 0, 18);
      wingL2.addBox(-2F, -0.8F, -1F, 14, 0, 5);
      wingL2.setRotationPoint(0F, 0F, 0F);
      wingL2.setTextureSize(64, 32);
      wingL2.mirror = true;
      setRotation(wingL2, 0.2617994F, 0F, -0.5235988F);
      wingL3 = new ModelRenderer(this, 0, 13);
      wingL3.addBox(-1.5F, -1.7F, -7.5F, 9, 0, 5);
      wingL3.setRotationPoint(0F, 0F, 0F);
      wingL3.setTextureSize(64, 32);
      wingL3.mirror = true;
      setRotation(wingL3, 0.5235988F, -0.0872665F, -0.5235988F);
      wingL4 = new ModelRenderer(this, 20, 10);
      wingL4.addBox(-1.5F, -1.7F, -10.5F, 7, 0, 3);
      wingL4.setRotationPoint(0F, 0F, 0F);
      wingL4.setTextureSize(64, 32);
      wingL4.mirror = true;
      setRotation(wingL4, 0.5235988F, -0.0872665F, -0.5235988F);
      
      LeftWing.addChild(wingL1);
      LeftWing.addChild(wingL2);
      LeftWing.addChild(wingL3);
      LeftWing.addChild(wingL4);
      
    HeadBase = new ModelRenderer(this, "HeadBase");
    HeadBase.setRotationPoint(-0.5F, -4.5F, 6.5F);
    setRotation(HeadBase, 0F, 0F, 0F);
    HeadBase.mirror = true;
      head = new ModelRenderer(this, 24, 0);
      head.addBox(-1.5F, -3F, 0F, 3, 4, 3);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      forehead = new ModelRenderer(this, 20, 0);
      forehead.addBox(-0.5F, -3F, 2.7F, 1, 3, 1);
      forehead.setRotationPoint(0F, 0F, 0F);
      forehead.setTextureSize(64, 32);
      forehead.mirror = true;
      setRotation(forehead, 0F, 0F, 0F);
      nose = new ModelRenderer(this, 12, 0);
      nose.addBox(-1F, -0.5F, 1.2F, 2, 2, 2);
      nose.setRotationPoint(0F, 0F, 0F);
      nose.setTextureSize(64, 32);
      nose.mirror = true;
      setRotation(nose, 0F, 0F, 0F);
      eyeR = new ModelRenderer(this, 30, 7);
      eyeR.addBox(-2F, -2F, 2.4F, 2, 2, 1);
      eyeR.setRotationPoint(0F, 0F, 0F);
      eyeR.setTextureSize(64, 32);
      eyeR.mirror = true;
      setRotation(eyeR, 0F, 0.7853982F, 0F);
      eyeL = new ModelRenderer(this, 24, 7);
      eyeL.addBox(0F, -2F, 2.4F, 2, 2, 1);
      eyeL.setRotationPoint(0F, 0F, 0F);
      eyeL.setTextureSize(64, 32);
      eyeL.mirror = true;
      setRotation(eyeL, 0F, -0.7853982F, 0F);
      pupilR = new ModelRenderer(this, 20, 4);
      pupilR.addBox(-1.5F, -1.5F, 2.5F, 1, 1, 1);
      pupilR.setRotationPoint(0F, 0F, 0F);
      pupilR.setTextureSize(64, 32);
      pupilR.mirror = true;
      setRotation(pupilR, 0F, 0.7853982F, 0F);
      pupilL = new ModelRenderer(this, 20, 6);
      pupilL.addBox(0.5F, -1.5F, 2.5F, 1, 1, 1);
      pupilL.setRotationPoint(0F, 0F, 0F);
      pupilL.setTextureSize(64, 32);
      pupilL.mirror = true;
      setRotation(pupilL, 0F, -0.7853982F, 0F);
      stickthing1 = new ModelRenderer(this, 4, 0);
      stickthing1.addBox(-0.1F, -9F, 2.1F, 1, 9, 1);
      stickthing1.setRotationPoint(0F, 0F, 0F);
      stickthing1.setTextureSize(64, 32);
      stickthing1.mirror = true;
      setRotation(stickthing1, 0.0872665F, 0F, 0.2617994F);
      stickthing2 = new ModelRenderer(this, 0, 0);
      stickthing2.addBox(-0.5F, -10F, 2.5F, 1, 11, 1);
      stickthing2.setRotationPoint(0F, 0F, 0F);
      stickthing2.setTextureSize(64, 32);
      stickthing2.mirror = true;
      setRotation(stickthing2, 0.0872665F, 0F, 0F);
      stickthing3 = new ModelRenderer(this, 4, 0);
      stickthing3.addBox(-0.9F, -9F, 2.1F, 1, 9, 1);
      stickthing3.setRotationPoint(0F, 0F, 0F);
      stickthing3.setTextureSize(64, 32);
      stickthing3.mirror = true;
      setRotation(stickthing3, 0.0872665F, 0F, -0.2617994F);
      
      HeadBase.addChild(head);
      HeadBase.addChild(forehead);
      HeadBase.addChild(nose);
      HeadBase.addChild(eyeR);
      HeadBase.addChild(eyeL);
      HeadBase.addChild(pupilR);
      HeadBase.addChild(pupilL);
      HeadBase.addChild(stickthing1);
      HeadBase.addChild(stickthing2);
      HeadBase.addChild(stickthing3);
      
      VenomothBase.addChild(HeadBase);
      VenomothBase.addChild(LeftWing);
      VenomothBase.addChild(RightWing);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    VenomothBase.render(f5);
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
    VenomothBase.rotateAngleY= 3.14159265F;
    LeftWing.rotateAngleZ = MathHelper.cos(f2)*.5F - .5F;
    RightWing.rotateAngleZ = MathHelper.cos(f2)*-.5F - 5.5F;
    HeadBase.rotateAngleX = f4 / 57.29578F;
    HeadBase.rotateAngleY = f3 / 57.29578F;
    VenomothBase.rotationPointY = MathHelper.cos(.5F*f2)*3F;
  }

}
