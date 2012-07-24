package pixelmon.models;

import net.minecraft.src.*;

public class ModelPokeball extends ModelBase
{
  //fields
    ModelRenderer WhteTip;
    ModelRenderer WhiteTop;
    ModelRenderer WhiteBottom;
    ModelRenderer WhiteFront;
    ModelRenderer WhiteRight;
    ModelRenderer WhiteLeft;
    ModelRenderer WhiteBack;
    ModelRenderer Hinge;
  
  public ModelPokeball()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      WhteTip = new ModelRenderer(this, 18, 12);
      WhteTip.addBox(-1F, 0.8F, -1F, 2, 1, 2);
      WhteTip.setRotationPoint(0F, 22F, 0F);
      WhteTip.setTextureSize(32, 32);
      WhteTip.mirror = true;
      setRotation(WhteTip, 0F, 0F, 0F);
      WhiteTop = new ModelRenderer(this, 0, 12);
      WhiteTop.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
      WhiteTop.setRotationPoint(0F, 22F, 0F);
      WhiteTop.setTextureSize(32, 32);
      WhiteTop.mirror = true;
      setRotation(WhiteTop, 0F, 0F, 0F);
      WhiteBottom = new ModelRenderer(this, 0, 16);
      WhiteBottom.addBox(-1.5F, 0.4F, -1.5F, 3, 1, 3);
      WhiteBottom.setRotationPoint(0F, 22F, 0F);
      WhiteBottom.setTextureSize(32, 32);
      WhiteBottom.mirror = true;
      setRotation(WhiteBottom, 0F, 0F, 0F);
      WhiteFront = new ModelRenderer(this, 12, 12);
      WhiteFront.addBox(-1F, 0F, -1.9F, 2, 1, 1);
      WhiteFront.setRotationPoint(0F, 22F, 0F);
      WhiteFront.setTextureSize(32, 32);
      WhiteFront.mirror = true;
      setRotation(WhiteFront, 0F, 0F, 0F);
      WhiteRight = new ModelRenderer(this, 0, 20);
      WhiteRight.addBox(-1.9F, 0F, -1F, 1, 1, 2);
      WhiteRight.setRotationPoint(0F, 22F, 0F);
      WhiteRight.setTextureSize(32, 32);
      WhiteRight.mirror = true;
      setRotation(WhiteRight, 0F, 0F, 0F);
      WhiteLeft = new ModelRenderer(this, 6, 20);
      WhiteLeft.addBox(0.9F, 0F, -1F, 1, 1, 2);
      WhiteLeft.setRotationPoint(0F, 22F, 0F);
      WhiteLeft.setTextureSize(32, 32);
      WhiteLeft.mirror = true;
      setRotation(WhiteLeft, 0F, 0F, 0F);
      WhiteBack = new ModelRenderer(this, 12, 14);
      WhiteBack.addBox(-1F, 0F, 0.9F, 2, 1, 1);
      WhiteBack.setRotationPoint(0F, 22F, 0F);
      WhiteBack.setTextureSize(32, 32);
      WhiteBack.mirror = true;
      setRotation(WhiteBack, 0F, 0F, 0F);
      
    Hinge = new ModelRenderer(this, "Hinge");
    Hinge.setRotationPoint(0F, 22F, 1.5F);
    setRotation(Hinge, 0F, 0F, 0F);
    Hinge.mirror = true;
      ModelRenderer  Black9 = new ModelRenderer(this, 0, 25);
      Black9.addBox(0.8F, -0.5F, -0.9F, 1, 1, 1);
      Black9.setRotationPoint(0F, -0.5F, -1.5F);
      Black9.setTextureSize(32, 32);
      Black9.mirror = true;
      setRotation(Black9, 0F, 0F, 0F);
      ModelRenderer  Black10 = new ModelRenderer(this, 0, 25);
      Black10.addBox(0.4F, -0.5F, -1.4F, 1, 1, 1);
      Black10.setRotationPoint(0F, -0.5F, -1.5F);
      Black10.setTextureSize(32, 32);
      Black10.mirror = true;
      setRotation(Black10, 0F, 0F, 0F);
      ModelRenderer  Black11 = new ModelRenderer(this, 0, 25);
      Black11.addBox(-0.1F, -0.5F, -1.8F, 1, 1, 1);
      Black11.setRotationPoint(0F, -0.5F, -1.5F);
      Black11.setTextureSize(32, 32);
      Black11.mirror = true;
      setRotation(Black11, 0F, 0F, 0F);
      ModelRenderer  Black12 = new ModelRenderer(this, 0, 25);
      Black12.addBox(-0.9F, -0.5F, -1.8F, 1, 1, 1);
      Black12.setRotationPoint(0F, -0.5F, -1.5F);
      Black12.setTextureSize(32, 32);
      Black12.mirror = true;
      setRotation(Black12, 0F, 0F, 0F);
      ModelRenderer   Black1 = new ModelRenderer(this, 0, 25);
      Black1.addBox(-1.4F, -0.5F, -1.4F, 1, 1, 1);
      Black1.setRotationPoint(0F, -0.5F, -1.5F);
      Black1.setTextureSize(32, 32);
      Black1.mirror = true;
      setRotation(Black1, 0F, 0F, 0F);
      ModelRenderer  Black2 = new ModelRenderer(this, 0, 25);
      Black2.addBox(-1.8F, -0.5F, -0.9F, 1, 1, 1);
      Black2.setRotationPoint(0F, -0.5F, -1.5F);
      Black2.setTextureSize(32, 32);
      Black2.mirror = true;
      setRotation(Black2, 0F, 0F, 0F);
      ModelRenderer   Black3 = new ModelRenderer(this, 0, 25);
      Black3.addBox(-1.8F, -0.5F, -0.1F, 1, 1, 1);
      Black3.setRotationPoint(0F, -0.5F, -1.5F);
      Black3.setTextureSize(32, 32);
      Black3.mirror = true;
      setRotation(Black3, 0F, 0F, 0F);
      ModelRenderer  Black4 = new ModelRenderer(this, 0, 25);
      Black4.addBox(-1.4F, -0.5F, 0.4F, 1, 1, 1);
      Black4.setRotationPoint(0F, -0.5F, -1.5F);
      Black4.setTextureSize(32, 32);
      Black4.mirror = true;
      setRotation(Black4, 0F, 0F, 0F);
      ModelRenderer   Black5 = new ModelRenderer(this, 0, 25);
      Black5.addBox(-0.9F, -0.5F, 0.8F, 1, 1, 1);
      Black5.setRotationPoint(0F, -0.5F, -1.5F);
      Black5.setTextureSize(32, 32);
      Black5.mirror = true;
      setRotation(Black5, 0F, 0F, 0F);
      ModelRenderer    Black6 = new ModelRenderer(this, 0, 25);
      Black6.addBox(-0.1F, -0.5F, 0.8F, 1, 1, 1);
      Black6.setRotationPoint(0F, -0.5F, -1.5F);
      Black6.setTextureSize(32, 32);
      Black6.mirror = true;
      setRotation(Black6, 0F, 0F, 0F);
      ModelRenderer Black7 = new ModelRenderer(this, 0, 25);
      Black7.addBox(0.4F, -0.5F, 0.4F, 1, 1, 1);
      Black7.setRotationPoint(0F, -0.5F, -1.5F);
      Black7.setTextureSize(32, 32);
      Black7.mirror = true;
      setRotation(Black7, 0F, 0F, 0F);
      ModelRenderer  Black8 = new ModelRenderer(this, 0, 25);
      Black8.addBox(0.8F, -0.5F, -0.1F, 1, 1, 1);
      Black8.setRotationPoint(0F, -0.5F, -1.5F);
      Black8.setTextureSize(32, 32);
      Black8.mirror = true;
      setRotation(Black8, 0F, 0F, 0F);
      
      Hinge.addChild(Black1);
      Hinge.addChild(Black2);
      Hinge.addChild(Black3);
      Hinge.addChild(Black4);
      Hinge.addChild(Black5);
      Hinge.addChild(Black6);
      Hinge.addChild(Black7);
      Hinge.addChild(Black8);
      Hinge.addChild(Black9);
      Hinge.addChild(Black10);
      Hinge.addChild(Black11);
      Hinge.addChild(Black12);
      
      ModelRenderer  RedTip = new ModelRenderer(this, 18, 0);
      RedTip.addBox(-1F, -1.6F, -1F, 2, 1, 2);
      RedTip.setRotationPoint(0F, -0.5F, -1.5F);
      RedTip.setTextureSize(32, 32);
      RedTip.mirror = true;
      setRotation(RedTip, 0F, 0F, 0F);
      ModelRenderer  RedTop = new ModelRenderer(this, 0, 0);
      RedTop.addBox(-1.5F, -1.2F, -1.5F, 3, 1, 3);
      RedTop.setRotationPoint(0F, -0.5F, -1.5F);
      RedTop.setTextureSize(32, 32);
      RedTop.mirror = true;
      setRotation(RedTop, 0F, 0F, 0F);
      ModelRenderer  RedFront = new ModelRenderer(this, 12, 0);
      RedFront.addBox(-1F, -0.8F, -1.9F, 2, 1, 1);
      RedFront.setRotationPoint(0F, -0.5F, -1.5F);
      RedFront.setTextureSize(32, 32);
      RedFront.mirror = true;
      setRotation(RedFront, 0F, 0F, 0F);
      ModelRenderer  RedBottom = new ModelRenderer(this, 0, 4);
      RedBottom.addBox(-1.5F, -0.8F, -1.5F, 3, 1, 3);
      RedBottom.setRotationPoint(0F, -0.5F, -1.5F);
      RedBottom.setTextureSize(32, 32);
      RedBottom.mirror = true;
      setRotation(RedBottom, 0F, 0F, 0F);
      ModelRenderer RedRight = new ModelRenderer(this, 0, 8);
      RedRight.addBox(-1.9F, -0.8F, -1F, 1, 1, 2);
      RedRight.setRotationPoint(0F, -0.5F, -1.5F);
      RedRight.setTextureSize(32, 32);
      RedRight.mirror = true;
      setRotation(RedRight, 0F, 0F, 0F);
      ModelRenderer  RedLeft = new ModelRenderer(this, 6, 8);
      RedLeft.addBox(0.9F, -0.8F, -1F, 1, 1, 2);
      RedLeft.setRotationPoint(0F, -0.5F, -1.5F);
      RedLeft.setTextureSize(32, 32);
      RedLeft.mirror = true;
      setRotation(RedLeft, 0F, 0F, 0F);
      ModelRenderer  RedBack = new ModelRenderer(this, 12, 2);
      RedBack.addBox(-1F, -0.8F, 0.9F, 2, 1, 1);
      RedBack.setRotationPoint(0F, -0.5F, -1.5F);
      RedBack.setTextureSize(32, 32);
      RedBack.mirror = true;
      setRotation(RedBack, 0F, 0F, 0F);
      
      Hinge.addChild(RedTip);
      Hinge.addChild(RedTop);
      Hinge.addChild(RedBottom);
      Hinge.addChild(RedFront);
      Hinge.addChild(RedBack);
      Hinge.addChild(RedRight);
      Hinge.addChild(RedLeft);
      
      ModelRenderer  Button = new ModelRenderer(this, 12, 25);
      Button.addBox(-0.5F, -0.1F, -2.1F, 1, 1, 1);
      Button.setRotationPoint(0F, -0.5F, -1.5F);
      Button.setTextureSize(32, 32);
      Button.mirror = true;
      setRotation(Button, 0F, 0F, 0F);
      
      Hinge.addChild(Button);
      
      ModelRenderer  MRight = new ModelRenderer(this, 6, 26);
      MRight.addBox(-1.7F, -1.1F, -3.1F, 1, 1, 0);
      MRight.setRotationPoint(0F, 0F, 0F);
      MRight.setTextureSize(32, 32);
      MRight.mirror = true;
      setRotation(MRight, 0F, 0F, 0.6283185F);
      ModelRenderer  MLeft = new ModelRenderer(this, 6, 26);
      MLeft.addBox(-1.1F, -1.7F, -3.1F, 1, 1, 0);
      MLeft.setRotationPoint(0F, 0F, 0F);
      MLeft.setTextureSize(32, 32);
      MLeft.mirror = true;
      setRotation(MLeft, 0F, 0F, 0.9424778F);
      MLeft.mirror = false;
      
      Hinge.addChild(MRight);
      Hinge.addChild(MLeft);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    WhteTip.render(f5);
    WhiteTop.render(f5);
    WhiteBottom.render(f5);
    WhiteFront.render(f5);
    WhiteRight.render(f5);
    WhiteLeft.render(f5);
    WhiteBack.render(f5);
    Hinge.render(f5);
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
