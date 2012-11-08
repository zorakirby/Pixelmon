package pixelmon.models.trainers;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelFisherman2 extends ModelBase
{
  //fields
    ModelRenderer Mouth;
    ModelRenderer Mouth_2;
    ModelRenderer Fishing_Box_Handle_Top;
    ModelRenderer Handle_Front;
    ModelRenderer Handle_Back;
    ModelRenderer Lid;
    ModelRenderer Box;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer HeadBase;
    ModelRenderer head;
    ModelRenderer Hat;
    ModelRenderer hatTop;
    ModelRenderer hatTip;
  
  public ModelFisherman2()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Mouth = new ModelRenderer(this, 20, 0);
      Mouth.addBox(-2F, 0F, 0F, 1, 1, 0);
      Mouth.setRotationPoint(1F, -3F, -3.1F);
      Mouth.setTextureSize(64, 64);
      Mouth.mirror = true;
      setRotation(Mouth, 0F, 0F, -0.6320361F);
      Mouth_2 = new ModelRenderer(this, 20, 0);
      Mouth_2.addBox(-1F, 0F, 1F, 2, 1, 0);
      Mouth_2.setRotationPoint(-1F, -2F, -4.1F);
      Mouth_2.setTextureSize(64, 64);
      Mouth_2.mirror = true;
      setRotation(Mouth_2, 0F, 0F, 0F);
      Fishing_Box_Handle_Top = new ModelRenderer(this, 50, 25);
      Fishing_Box_Handle_Top.addBox(0F, 0F, -3F, 1, 1, 6);
      Fishing_Box_Handle_Top.setRotationPoint(5F, 10F, -1F);
      Fishing_Box_Handle_Top.setTextureSize(64, 64);
      Fishing_Box_Handle_Top.mirror = true;
      setRotation(Fishing_Box_Handle_Top, 0F, 0F, 0F);
      Handle_Front = new ModelRenderer(this, 0, 12);
      Handle_Front.addBox(0F, 0F, 0F, 1, 2, 1);
      Handle_Front.setRotationPoint(5F, 10F, -4F);
      Handle_Front.setTextureSize(64, 64);
      Handle_Front.mirror = true;
      setRotation(Handle_Front, 0F, 0F, 0F);
      Handle_Back = new ModelRenderer(this, 0, 12);
      Handle_Back.addBox(0F, 0F, 0F, 1, 2, 1);
      Handle_Back.setRotationPoint(5F, 10F, 1F);
      Handle_Back.setTextureSize(64, 64);
      Handle_Back.mirror = true;
      setRotation(Handle_Back, 0F, 0F, 0F);
      Lid = new ModelRenderer(this, 17, 4);
      Lid.addBox(-1F, 0F, -5F, 3, 1, 10);
      Lid.setRotationPoint(5F, 12F, -1F);
      Lid.setTextureSize(64, 64);
      Lid.mirror = true;
      setRotation(Lid, 0F, 0F, 0F);
      Box = new ModelRenderer(this, 0, 32);
      Box.addBox(-1F, 0F, -5F, 3, 3, 10);
      Box.setRotationPoint(5F, 13F, -1F);
      Box.setTextureSize(64, 64);
      Box.mirror = true;
      setRotation(Box, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, -3F, -2F, 7, 12, 3);
      body.setRotationPoint(0F, 3F, 0F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm.mirror = true;
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-2F, -2F, -1F, 2, 11, 2);
      rightarm.setRotationPoint(-4F, 2F, -0.5F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0.074351F);
      rightarm.mirror = false;
      leftarm = new ModelRenderer(this, 40, 16);
      leftarm.addBox(0F, -2F, -1F, 2, 11, 2);
      leftarm.setRotationPoint(3F, 2F, -1F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, -0.1487144F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-2F, 0F, -2F, 3, 12, 3);
      rightleg.setRotationPoint(-2F, 12F, 0F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg.mirror = true;
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, 0F, -2F, 3, 12, 3);
      leftleg.setRotationPoint(2F, 12F, 0F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      leftleg.mirror = false;
    HeadBase = new ModelRenderer(this, "HeadBase");
    HeadBase.setRotationPoint(-0.5F, 0F, 0F);
    setRotation(HeadBase, 0F, 0F, 0F);
    HeadBase.mirror = true;
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-2.5F, -5F, -3F, 5, 5, 5);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      Hat = new ModelRenderer(this, 40, 0);
      Hat.addBox(-2F, 0F, -4F, 7, 0, 7);
      Hat.setRotationPoint(-1.5F, -5F, 0F);
      Hat.setTextureSize(64, 64);
      Hat.mirror = true;
      setRotation(Hat, 0F, 0F, 0F);
      hatTop = new ModelRenderer(this, 44, 8);
      hatTop.addBox(-2F, -1F, -2F, 5, 2, 5);
      hatTop.setRotationPoint(-0.5F, -6F, -1F);
      hatTop.setTextureSize(64, 64);
      hatTop.mirror = true;
      setRotation(hatTop, 0F, 0F, 0F);
      hatTip = new ModelRenderer(this, 26, 0);
      hatTip.addBox(-1F, 0F, 0F, 3, 1, 3);
      hatTip.setRotationPoint(-0.5F, -8F, -2F);
      hatTip.setTextureSize(64, 64);
      hatTip.mirror = true;
      setRotation(hatTip, 0F, 0F, 0F);
      
      HeadBase.addChild(head);
      HeadBase.addChild(Hat);
      HeadBase.addChild(hatTop);
      HeadBase.addChild(hatTip);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Mouth.render(f5);
    Mouth_2.render(f5);
    Fishing_Box_Handle_Top.render(f5);
    Handle_Front.render(f5);
    Handle_Back.render(f5);
    Lid.render(f5);
    Box.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    HeadBase.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5){
  }

}
