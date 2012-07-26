package pixelmon.models;

import pixelmon.blocks.TileEntityPC;
import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelHealer extends ModelBase
{
  //fields
    ModelRenderer HealerBase;
    ModelRenderer HealerLeg1;
    ModelRenderer HealerLeg2;
    ModelRenderer HealerLeg3;
    ModelRenderer HealerLeg4;
    ModelRenderer Mainplate;
    ModelRenderer HealerRightSide;
    ModelRenderer HealerLeftSide;
    ModelRenderer BackplateBase;
    ModelRenderer BackplateTop;
    ModelRenderer FrontCurveBase;
    ModelRenderer FrontCurveRight1;
    ModelRenderer FrontCurveRight2;
    ModelRenderer FrontCurveLeft1;
    ModelRenderer FrontCurveLeft2;
    ModelRenderer BackCurveBase;
    ModelRenderer BackCurveRight1;
    ModelRenderer BackCurveRight2;
    ModelRenderer BackCurveLeft1;
    ModelRenderer BackCurveLeft2;
  
  public ModelHealer()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      HealerBase = new ModelRenderer(this, 0, 0);
      HealerBase.addBox(0F, 0F, 0F, 14, 10, 14);
      HealerBase.setRotationPoint(-7F, 14F, -7F);
      HealerBase.setTextureSize(128, 128);
      HealerBase.mirror = true;
      setRotation(HealerBase, 0F, 0F, 0F);
      HealerLeg1 = new ModelRenderer(this, 57, 0);
      HealerLeg1.addBox(0F, 0F, 0F, 2, 10, 1);
      HealerLeg1.setRotationPoint(-7F, 14F, -8F);
      HealerLeg1.setTextureSize(128, 128);
      HealerLeg1.mirror = true;
      setRotation(HealerLeg1, 0F, 0F, 0F);
      HealerLeg2 = new ModelRenderer(this, 64, 0);
      HealerLeg2.addBox(0F, 0F, 0F, 2, 10, 1);
      HealerLeg2.setRotationPoint(5F, 14F, -8F);
      HealerLeg2.setTextureSize(128, 128);
      HealerLeg2.mirror = true;
      setRotation(HealerLeg2, 0F, 0F, 0F);
      HealerLeg3 = new ModelRenderer(this, 57, 12);
      HealerLeg3.addBox(0F, 0F, 0F, 2, 10, 1);
      HealerLeg3.setRotationPoint(5F, 14F, 7F);
      HealerLeg3.setTextureSize(128, 128);
      HealerLeg3.mirror = true;
      setRotation(HealerLeg3, 0F, 0F, 0F);
      HealerLeg4 = new ModelRenderer(this, 64, 12);
      HealerLeg4.addBox(0F, 0F, 0F, 2, 10, 1);
      HealerLeg4.setRotationPoint(-7F, 14F, 7F);
      HealerLeg4.setTextureSize(128, 128);
      HealerLeg4.mirror = true;
      setRotation(HealerLeg4, 0F, 0F, 0F);
      Mainplate = new ModelRenderer(this, 72, 3);
      Mainplate.addBox(0F, 0F, 0F, 10, 2, 14);
      Mainplate.setRotationPoint(-5F, 12.5F, -7F);
      Mainplate.setTextureSize(128, 128);
      Mainplate.mirror = true;
      setRotation(Mainplate, 0F, 0F, 0F);
      HealerRightSide = new ModelRenderer(this, 0, 25);
      HealerRightSide.addBox(0F, 0F, 0F, 3, 3, 16);
      HealerRightSide.setRotationPoint(-7F, 14F, -8F);
      HealerRightSide.setTextureSize(128, 128);
      HealerRightSide.mirror = true;
      setRotation(HealerRightSide, 0F, 0F, -0.7853982F);
      HealerLeftSide = new ModelRenderer(this, 0, 45);
      HealerLeftSide.addBox(0F, 0F, 0F, 3, 3, 16);
      HealerLeftSide.setRotationPoint(7F, 14F, -8F);
      HealerLeftSide.setTextureSize(128, 128);
      HealerLeftSide.mirror = true;
      setRotation(HealerLeftSide, 0F, 0F, 2.356194F);
      BackplateBase = new ModelRenderer(this, 72, 21);
      BackplateBase.addBox(0F, 0F, 0F, 10, 3, 2);
      BackplateBase.setRotationPoint(-5F, 8F, 6F);
      BackplateBase.setTextureSize(128, 128);
      BackplateBase.mirror = true;
      setRotation(BackplateBase, 0F, 0F, 0F);
      BackplateTop = new ModelRenderer(this, 97, 21);
      BackplateTop.addBox(0F, 0F, 0F, 8, 1, 2);
      BackplateTop.setRotationPoint(-4F, 7F, 6F);
      BackplateTop.setTextureSize(128, 128);
      BackplateTop.mirror = true;
      setRotation(BackplateTop, 0F, 0F, 0F);
      FrontCurveBase = new ModelRenderer(this, 50, 32);
      FrontCurveBase.addBox(0F, 0F, 0F, 12, 3, 2);
      FrontCurveBase.setRotationPoint(-6F, 11F, -8F);
      FrontCurveBase.setTextureSize(128, 128);
      FrontCurveBase.mirror = true;
      setRotation(FrontCurveBase, 0F, 0F, 0F);
      FrontCurveRight1 = new ModelRenderer(this, 56, 38);
      FrontCurveRight1.addBox(0F, 0F, 0F, 2, 2, 2);
      FrontCurveRight1.setRotationPoint(-7F, 12F, -8F);
      FrontCurveRight1.setTextureSize(128, 128);
      FrontCurveRight1.mirror = true;
      setRotation(FrontCurveRight1, 0F, 0F, -0.4712389F);
      FrontCurveRight2 = new ModelRenderer(this, 46, 38);
      FrontCurveRight2.addBox(0F, 0F, 0F, 2, 2, 2);
      FrontCurveRight2.setRotationPoint(-7F, 14F, -8F);
      FrontCurveRight2.setTextureSize(128, 128);
      FrontCurveRight2.mirror = true;
      setRotation(FrontCurveRight2, 0F, 0F, -1.570796F);
      FrontCurveLeft1 = new ModelRenderer(this, 66, 38);
      FrontCurveLeft1.addBox(0F, -2F, 0F, 2, 2, 2);
      FrontCurveLeft1.setRotationPoint(7F, 12F, -8F);
      FrontCurveLeft1.setTextureSize(128, 128);
      FrontCurveLeft1.mirror = true;
      setRotation(FrontCurveLeft1, 0F, 0F, -2.6529F);
      FrontCurveLeft2 = new ModelRenderer(this, 76, 38);
      FrontCurveLeft2.addBox(0F, -2F, 0F, 2, 2, 2);
      FrontCurveLeft2.setRotationPoint(7F, 14F, -8F);
      FrontCurveLeft2.setTextureSize(128, 128);
      FrontCurveLeft2.mirror = true;
      setRotation(FrontCurveLeft2, 0F, 0F, -1.570796F);
      BackCurveBase = new ModelRenderer(this, 50, 44);
      BackCurveBase.addBox(0F, 0F, 0F, 12, 3, 2);
      BackCurveBase.setRotationPoint(-6F, 11F, 6F);
      BackCurveBase.setTextureSize(128, 128);
      BackCurveBase.mirror = true;
      setRotation(BackCurveBase, 0F, 0F, 0F);
      BackCurveRight1 = new ModelRenderer(this, 56, 50);
      BackCurveRight1.addBox(0F, 0F, 0F, 2, 2, 2);
      BackCurveRight1.setRotationPoint(-7F, 12F, 6F);
      BackCurveRight1.setTextureSize(128, 128);
      BackCurveRight1.mirror = true;
      setRotation(BackCurveRight1, 0F, 0F, -0.4712389F);
      BackCurveRight2 = new ModelRenderer(this, 46, 50);
      BackCurveRight2.addBox(0F, 0F, 0F, 2, 2, 2);
      BackCurveRight2.setRotationPoint(-7F, 14F, 6F);
      BackCurveRight2.setTextureSize(128, 128);
      BackCurveRight2.mirror = true;
      setRotation(BackCurveRight2, 0F, 0F, -1.570796F);
      BackCurveLeft1 = new ModelRenderer(this, 66, 50);
      BackCurveLeft1.addBox(0F, -2F, 0F, 2, 2, 2);
      BackCurveLeft1.setRotationPoint(7F, 12F, 6F);
      BackCurveLeft1.setTextureSize(128, 128);
      BackCurveLeft1.mirror = true;
      setRotation(BackCurveLeft1, 0F, 0F, -2.6529F);
      BackCurveLeft2 = new ModelRenderer(this, 76, 50);
      BackCurveLeft2.addBox(0F, -2F, 0F, 2, 2, 2);
      BackCurveLeft2.setRotationPoint(7F, 14F, 6F);
      BackCurveLeft2.setTextureSize(128, 128);
      BackCurveLeft2.mirror = true;
      setRotation(BackCurveLeft2, 0F, 0F, -1.570796F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    HealerBase.render(f5);
    HealerLeg1.render(f5);
    HealerLeg2.render(f5);
    HealerLeg3.render(f5);
    HealerLeg4.render(f5);
    Mainplate.render(f5);
    HealerRightSide.render(f5);
    HealerLeftSide.render(f5);
    BackplateBase.render(f5);
    BackplateTop.render(f5);
    FrontCurveBase.render(f5);
    FrontCurveRight1.render(f5);
    FrontCurveRight2.render(f5);
    FrontCurveLeft1.render(f5);
    FrontCurveLeft2.render(f5);
    BackCurveBase.render(f5);
    BackCurveRight1.render(f5);
    BackCurveRight2.render(f5);
    BackCurveLeft1.render(f5);
    BackCurveLeft2.render(f5);
  }
  public void renderModel(TileEntityPC entity, float f5) {
	  HealerBase.render(f5);
	    HealerLeg1.render(f5);
	    HealerLeg2.render(f5);
	    HealerLeg3.render(f5);
	    HealerLeg4.render(f5);
	    Mainplate.render(f5);
	    HealerRightSide.render(f5);
	    HealerLeftSide.render(f5);
	    BackplateBase.render(f5);
	    BackplateTop.render(f5);
	    FrontCurveBase.render(f5);
	    FrontCurveRight1.render(f5);
	    FrontCurveRight2.render(f5);
	    FrontCurveLeft1.render(f5);
	    FrontCurveLeft2.render(f5);
	    BackCurveBase.render(f5);
	    BackCurveRight1.render(f5);
	    BackCurveRight2.render(f5);
	    BackCurveLeft1.render(f5);
	    BackCurveLeft2.render(f5);
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
