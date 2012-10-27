package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelChansey extends ModelBase
{
  //fields
    ModelRenderer face;
    ModelRenderer ear1;
    ModelRenderer ear5;
    ModelRenderer ear3;
    ModelRenderer ear6;
    ModelRenderer ear4;
    ModelRenderer ear2;
    ModelRenderer headtop;
    ModelRenderer head;
    ModelRenderer Egg;
    ModelRenderer eggPouch;
    ModelRenderer LeftLeg;
    ModelRenderer RightLeg;
    ModelRenderer Shape1;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape16;
    ModelRenderer Shape23;
    ModelRenderer Shape20;
    ModelRenderer Shape19;
    ModelRenderer Shape15;
    ModelRenderer LeftArm;
    ModelRenderer RightArm;
    ModelRenderer tailTip;
    ModelRenderer tailBase;
  
  public ModelChansey()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      face = new ModelRenderer(this, 28, 27);
      face.addBox(0F, 0F, 0F, 5, 3, 1);
      face.setRotationPoint(-2F, 14.5F, 0F);
      face.setTextureSize(64, 32);
      face.mirror = true;
      setRotation(face, 0F, 0F, 0F);
      ear1 = new ModelRenderer(this, 0, 20);
      ear1.addBox(0F, 0F, 0F, 1, 4, 1);
      ear1.setRotationPoint(-2F, 14F, 1.5F);
      ear1.setTextureSize(64, 32);
      ear1.mirror = true;
      setRotation(ear1, -0.4461433F, 0F, 0.8922867F);
      ear5 = new ModelRenderer(this, 0, 20);
      ear5.addBox(0F, 0F, 0F, 1, 4, 1);
      ear5.setRotationPoint(-2.5F, 14.5F, 5F);
      ear5.setTextureSize(64, 32);
      ear5.mirror = true;
      setRotation(ear5, 0.5576792F, 0F, 1.07818F);
      ear3 = new ModelRenderer(this, 0, 20);
      ear3.addBox(0F, 0F, 0F, 1, 4, 1);
      ear3.setRotationPoint(-2F, 14F, 3F);
      ear3.setTextureSize(64, 32);
      ear3.mirror = true;
      setRotation(ear3, 0F, 0F, 1.07818F);
      ear6 = new ModelRenderer(this, 5, 22);
      ear6.addBox(0F, 0F, 0F, 4, 1, 1);
      ear6.setRotationPoint(3F, 14F, 5F);
      ear6.setTextureSize(64, 32);
      ear6.mirror = true;
      setRotation(ear6, 0F, -0.4833219F, 0.4833219F);
      ear4 = new ModelRenderer(this, 5, 22);
      ear4.addBox(0F, 0F, 0F, 4, 1, 1);
      ear4.setRotationPoint(3F, 14F, 3F);
      ear4.setTextureSize(64, 32);
      ear4.mirror = true;
      setRotation(ear4, 0F, 0F, 0.4461433F);
      ear2 = new ModelRenderer(this, 5, 22);
      ear2.addBox(0F, 0F, 0F, 4, 1, 1);
      ear2.setRotationPoint(3F, 14F, 1F);
      ear2.setTextureSize(64, 32);
      ear2.mirror = true;
      setRotation(ear2, 0F, 0.5205006F, 0.5948578F);
      headtop = new ModelRenderer(this, 12, 0);
      headtop.addBox(0F, 0F, 0F, 4, 1, 5);
      headtop.setRotationPoint(-1.5F, 13.5F, 1F);
      headtop.setTextureSize(64, 32);
      headtop.mirror = true;
      setRotation(headtop, 0F, 0F, 0F);
      head = new ModelRenderer(this, 0, 0);
      head.addBox(0F, 0F, 0F, 6, 3, 6);
      head.setRotationPoint(-2.5F, 14F, 0.5F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      Egg = new ModelRenderer(this, 36, 0);
      Egg.addBox(0F, 0F, 0F, 3, 4, 1);
      Egg.setRotationPoint(-1F, 18F, -1F);
      Egg.setTextureSize(64, 32);
      Egg.mirror = true;
      setRotation(Egg, 0F, 0F, 0F);
      eggPouch = new ModelRenderer(this, 34, 5);
      eggPouch.addBox(0F, 0F, 0F, 4, 2, 2);
      eggPouch.setRotationPoint(-1.5F, 21F, -1.3F);
      eggPouch.setTextureSize(64, 32);
      eggPouch.mirror = true;
      setRotation(eggPouch, 0F, 0F, 0F);
      LeftLeg = new ModelRenderer(this, 25, 14);
      LeftLeg.addBox(0F, 0F, 0F, 1, 1, 7);
      LeftLeg.setRotationPoint(3.5F, 23F, -1.5F);
      LeftLeg.setTextureSize(64, 32);
      LeftLeg.mirror = true;
      setRotation(LeftLeg, 0F, -0.2602503F, 0F);
      RightLeg = new ModelRenderer(this, 25, 14);
      RightLeg.addBox(0F, 0F, 0F, 1, 1, 7);
      RightLeg.setRotationPoint(-3.5F, 23F, -1.3F);
      RightLeg.setTextureSize(64, 32);
      RightLeg.mirror = true;
      setRotation(RightLeg, 0F, 0.2230717F, 0F);
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 8, 7, 7);
      Shape1.setRotationPoint(-3.5F, 16.5F, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 48, 17);
      Shape13.addBox(0F, 0F, 0F, 1, 6, 6);
      Shape13.setRotationPoint(-4F, 17F, 0.5F);
      Shape13.setTextureSize(64, 32);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 50, 0);
      Shape14.addBox(0F, 0F, 0F, 1, 5, 5);
      Shape14.setRotationPoint(-4.5F, 18F, 1F);
      Shape14.setTextureSize(64, 32);
      Shape14.mirror = true;
      setRotation(Shape14, 0F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 48, 17);
      Shape16.addBox(0F, 0F, 0F, 1, 6, 6);
      Shape16.setRotationPoint(4F, 17F, 0.5F);
      Shape16.setTextureSize(64, 32);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape23 = new ModelRenderer(this, 0, 0);
      Shape23.addBox(0F, 0F, 0F, 7, 6, 1);
      Shape23.setRotationPoint(-3F, 17F, -0.5F);
      Shape23.setTextureSize(64, 32);
      Shape23.mirror = true;
      setRotation(Shape23, 0F, 0F, 0F);
      Shape20 = new ModelRenderer(this, 47, 11);
      Shape20.addBox(0F, 0F, 0F, 5, 5, 1);
      Shape20.setRotationPoint(-2F, 18F, 7F);
      Shape20.setTextureSize(64, 32);
      Shape20.mirror = true;
      setRotation(Shape20, 0F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 11, 14);
      Shape19.addBox(0F, 0F, 0F, 6, 6, 1);
      Shape19.setRotationPoint(-2.5F, 17F, 6.5F);
      Shape19.setTextureSize(64, 32);
      Shape19.mirror = true;
      setRotation(Shape19, 0F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 50, 0);
      Shape15.addBox(0F, 0F, 0F, 1, 5, 5);
      Shape15.setRotationPoint(4.5F, 18F, 1F);
      Shape15.setTextureSize(64, 32);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, 0F);
      LeftArm = new ModelRenderer(this, 36, 10);
      LeftArm.addBox(0F, 0F, 0F, 1, 3, 1);
      LeftArm.setRotationPoint(2.8F, 18F, 0.2F);
      LeftArm.setTextureSize(64, 32);
      LeftArm.mirror = true;
      setRotation(LeftArm, -0.669215F, 0.5948578F, 0.0174533F);
      RightArm = new ModelRenderer(this, 36, 10);
      RightArm.addBox(0F, 0F, 0F, 1, 3, 1);
      RightArm.setRotationPoint(-1.9F, 18.5F, 0.7F);
      RightArm.setTextureSize(64, 32);
      RightArm.mirror = true;
      setRotation(RightArm, 0.5948578F, 2.918521F, 0.2602503F);
      tailTip = new ModelRenderer(this, 0, 0);
      tailTip.addBox(0F, 0F, 0F, 1, 1, 1);
      tailTip.setRotationPoint(1.7F, 20.5F, 9.8F);
      tailTip.setTextureSize(64, 32);
      tailTip.mirror = true;
      setRotation(tailTip, 2.120575F, 0.8203047F, 0F);
      tailBase = new ModelRenderer(this, 0, 0);
      tailBase.addBox(0F, 0F, 0F, 2, 4, 2);
      tailBase.setRotationPoint(-1F, 23F, 8F);
      tailBase.setTextureSize(64, 32);
      tailBase.mirror = true;
      setRotation(tailBase, 2.120575F, 0.8203047F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    face.render(f5);
    ear1.render(f5);
    ear5.render(f5);
    ear3.render(f5);
    ear6.render(f5);
    ear4.render(f5);
    ear2.render(f5);
    headtop.render(f5);
    head.render(f5);
    Egg.render(f5);
    eggPouch.render(f5);
    LeftLeg.render(f5);
    RightLeg.render(f5);
    Shape1.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape16.render(f5);
    Shape23.render(f5);
    Shape20.render(f5);
    Shape19.render(f5);
    Shape15.render(f5);
    LeftArm.render(f5);
    RightArm.render(f5);
    tailTip.render(f5);
    tailBase.render(f5);
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
  }

}
