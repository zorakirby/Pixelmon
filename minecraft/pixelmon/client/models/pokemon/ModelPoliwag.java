package pixelmon.client.models.pokemon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPoliwag extends ModelBase
{
  //fields
    ModelRenderer RightEye;
    ModelRenderer LeftEye;
    ModelRenderer RightLeg;
    ModelRenderer RFoot1;
    ModelRenderer LFoot1;
    ModelRenderer LeftLeg;
    ModelRenderer LFoot2;
    ModelRenderer LFoot3;
    ModelRenderer RFoot2;
    ModelRenderer RFoot3;
    ModelRenderer TailEnd;
    ModelRenderer TailBase;
    ModelRenderer Mouth;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Belly;
    ModelRenderer Body5;
    ModelRenderer Body6;
    ModelRenderer Body7;
    ModelRenderer Body8;
    ModelRenderer BodyMain;
  
  public ModelPoliwag()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      RightEye = new ModelRenderer(this, 17, 9);
      RightEye.addBox(0F, 0F, 0F, 1, 1, 1);
      RightEye.setRotationPoint(-1F, 17.5F, -0.5F);
      RightEye.setTextureSize(64, 32);
      RightEye.mirror = true;
      setRotation(RightEye, 0F, 0F, 0F);
      LeftEye = new ModelRenderer(this, 17, 7);
      LeftEye.addBox(0F, 0F, 0F, 1, 1, 1);
      LeftEye.setRotationPoint(1F, 17.5F, -0.5F);
      LeftEye.setTextureSize(64, 32);
      LeftEye.mirror = true;
      setRotation(LeftEye, 0F, 0F, 0F);
      RightLeg = new ModelRenderer(this, 0, 0);
      RightLeg.addBox(0F, 0F, 0F, 1, 2, 1);
      RightLeg.setRotationPoint(-1.5F, 22F, 1F);
      RightLeg.setTextureSize(64, 32);
      RightLeg.mirror = true;
      setRotation(RightLeg, 0F, 0F, 0F);
      RFoot1 = new ModelRenderer(this, 0, 0);
      RFoot1.addBox(0F, 0F, 0F, 1, 1, 3);
      RFoot1.setRotationPoint(-1.5F, 23F, -1F);
      RFoot1.setTextureSize(64, 32);
      RFoot1.mirror = true;
      setRotation(RFoot1, 0F, 0F, 0F);
      LFoot1 = new ModelRenderer(this, 0, 0);
      LFoot1.addBox(0F, 0F, 0F, 1, 1, 3);
      LFoot1.setRotationPoint(1.5F, 23F, -1F);
      LFoot1.setTextureSize(64, 32);
      LFoot1.mirror = true;
      setRotation(LFoot1, 0F, 0F, 0F);
      LeftLeg = new ModelRenderer(this, 0, 0);
      LeftLeg.addBox(0F, 0F, 0F, 1, 2, 1);
      LeftLeg.setRotationPoint(1.5F, 22F, 1F);
      LeftLeg.setTextureSize(64, 32);
      LeftLeg.mirror = true;
      setRotation(LeftLeg, 0F, 0F, 0F);
      LFoot2 = new ModelRenderer(this, 0, 0);
      LFoot2.addBox(0F, 0F, 0F, 1, 1, 3);
      LFoot2.setRotationPoint(2.5F, 23F, 2F);
      LFoot2.setTextureSize(64, 32);
      LFoot2.mirror = true;
      setRotation(LFoot2, 0F, -3.030057F, 0F);
      LFoot3 = new ModelRenderer(this, 0, 0);
      LFoot3.addBox(0F, 0F, 0F, 1, 1, 3);
      LFoot3.setRotationPoint(2.5F, 23F, 2.2F);
      LFoot3.setTextureSize(64, 32);
      LFoot3.mirror = true;
      setRotation(LFoot3, 0F, 3.028146F, 0F);
      RFoot2 = new ModelRenderer(this, 0, 0);
      RFoot2.addBox(0F, 0F, 0F, 1, 1, 3);
      RFoot2.setRotationPoint(-0.5F, 23F, 2F);
      RFoot2.setTextureSize(64, 32);
      RFoot2.mirror = true;
      setRotation(RFoot2, 0F, -3.030057F, 0F);
      RFoot3 = new ModelRenderer(this, 0, 0);
      RFoot3.addBox(0F, 0F, 0F, 1, 1, 3);
      RFoot3.setRotationPoint(-0.5F, 23F, 2.2F);
      RFoot3.setTextureSize(64, 32);
      RFoot3.mirror = true;
      setRotation(RFoot3, 0F, 3.028146F, 0F);
      TailEnd = new ModelRenderer(this, 15, 15);
      TailEnd.addBox(-2.5F, 0F, 0F, 5, 1, 8);
      TailEnd.setRotationPoint(0.6F, 21.7F, 4F);
      TailEnd.setTextureSize(64, 32);
      TailEnd.mirror = true;
      setRotation(TailEnd, 0.2617994F, 0F, 0F);
      TailBase = new ModelRenderer(this, 0, 0);
      TailBase.addBox(0F, 0F, 0F, 1, 1, 2);
      TailBase.setRotationPoint(0F, 21.5F, 3F);
      TailBase.setTextureSize(64, 32);
      TailBase.mirror = true;
      setRotation(TailBase, 0.2617994F, 0F, 0F);
      Mouth = new ModelRenderer(this, 17, 11);
      Mouth.addBox(0F, 0F, 0F, 1, 1, 1);
      Mouth.setRotationPoint(0F, 18.5F, -0.8F);
      Mouth.setTextureSize(64, 32);
      Mouth.mirror = true;
      setRotation(Mouth, 0F, 0F, 0F);
      Body1 = new ModelRenderer(this, 30, 0);
      Body1.addBox(0F, 0F, 0F, 1, 4, 2);
      Body1.setRotationPoint(2.5F, 18F, 0.5F);
      Body1.setTextureSize(64, 32);
      Body1.mirror = true;
      setRotation(Body1, 0F, 0F, 0F);
      Body2 = new ModelRenderer(this, 0, 0);
      Body2.addBox(0F, 0F, 0F, 4, 1, 2);
      Body2.setRotationPoint(-1.5F, 22F, 0.5F);
      Body2.setTextureSize(64, 32);
      Body2.mirror = true;
      setRotation(Body2, 0F, 0F, 0F);
      Body3 = new ModelRenderer(this, 0, 0);
      Body3.addBox(0F, 0F, 0F, 3, 3, 1);
      Body3.setRotationPoint(-1F, 19F, 3F);
      Body3.setTextureSize(64, 32);
      Body3.mirror = true;
      setRotation(Body3, 0F, 0F, 0F);
      Body4 = new ModelRenderer(this, 30, 0);
      Body4.addBox(0F, 0F, 0F, 1, 4, 2);
      Body4.setRotationPoint(-2.5F, 18F, 0.5F);
      Body4.setTextureSize(64, 32);
      Body4.mirror = true;
      setRotation(Body4, 0F, 0F, 0F);
      Belly = new ModelRenderer(this, 19, 0);
      Belly.addBox(0F, 0F, 0F, 3, 3, 1);
      Belly.setRotationPoint(-1F, 19F, -0.7F);
      Belly.setTextureSize(64, 32);
      Belly.mirror = true;
      setRotation(Belly, 0F, 0F, 0F);
      Body5 = new ModelRenderer(this, 0, 0);
      Body5.addBox(0F, 0F, 0F, 3, 1, 2);
      Body5.setRotationPoint(-1F, 16.7F, 0.5F);
      Body5.setTextureSize(64, 32);
      Body5.mirror = true;
      setRotation(Body5, 0F, 0F, 0F);
      Body6 = new ModelRenderer(this, 0, 0);
      Body6.addBox(0F, 0F, 0F, 4, 1, 3);
      Body6.setRotationPoint(-1.5F, 17F, 0F);
      Body6.setTextureSize(64, 32);
      Body6.mirror = true;
      setRotation(Body6, 0F, 0F, 0F);
      Body7 = new ModelRenderer(this, 0, 20);
      Body7.addBox(0F, 0F, 0F, 4, 4, 1);
      Body7.setRotationPoint(-1.5F, 18F, -0.5F);
      Body7.setTextureSize(64, 32);
      Body7.mirror = true;
      setRotation(Body7, 0F, 0F, 0F);
      Body8 = new ModelRenderer(this, 0, 10);
      Body8.addBox(0F, 0F, 0F, 4, 4, 1);
      Body8.setRotationPoint(-1.5F, 18F, 2.5F);
      Body8.setTextureSize(64, 32);
      Body8.mirror = true;
      setRotation(Body8, 0F, 0F, 0F);
      BodyMain = new ModelRenderer(this, 0, 0);
      BodyMain.addBox(0F, 0F, 0F, 5, 5, 3);
      BodyMain.setRotationPoint(-2F, 17.5F, 0F);
      BodyMain.setTextureSize(64, 32);
      BodyMain.mirror = true;
      setRotation(BodyMain, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    RightEye.render(f5);
    LeftEye.render(f5);
    RightLeg.render(f5);
    RFoot1.render(f5);
    LFoot1.render(f5);
    LeftLeg.render(f5);
    LFoot2.render(f5);
    LFoot3.render(f5);
    RFoot2.render(f5);
    RFoot3.render(f5);
    TailEnd.render(f5);
    TailBase.render(f5);
    Mouth.render(f5);
    Body1.render(f5);
    Body2.render(f5);
    Body3.render(f5);
    Body4.render(f5);
    Belly.render(f5);
    Body5.render(f5);
    Body6.render(f5);
    Body7.render(f5);
    Body8.render(f5);
    BodyMain.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
  }

}
