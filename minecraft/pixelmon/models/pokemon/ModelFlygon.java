package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelFlygon extends ModelBase
{
  //fields
    ModelRenderer wing1;
    ModelRenderer wing2;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer tail5;
    ModelRenderer tail6;
    ModelRenderer tail7;
    ModelRenderer tail8;
    ModelRenderer tail9;
    ModelRenderer tail10;
    ModelRenderer tail11;
    ModelRenderer LegR;
    ModelRenderer LegL;
    ModelRenderer BodyBase;
    ModelRenderer ArmR;
    ModelRenderer ArmL;
    ModelRenderer NeckBase;
    ModelRenderer Neck1;
    ModelRenderer Neck2;
    ModelRenderer HeadBase;
  
  public ModelFlygon()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      wing1 = new ModelRenderer(this, 68, 13);
      wing1.addBox(0F, 0F, -15F, 15, 0, 15);
      wing1.setRotationPoint(1F, 1.5F, 3F);
      wing1.setTextureSize(128, 128);
      wing1.mirror = true;
      setRotation(wing1, -1.012911F, -1.047817F, 0.3346075F);
      wing2 = new ModelRenderer(this, 68, 13);
      wing2.addBox(-15F, 0F, -15F, 15, 0, 15);
      wing2.setRotationPoint(-1F, 1.5F, 3F);
      wing2.setTextureSize(128, 128);
      wing2.mirror = true;
      setRotation(wing2, -1.012919F, 1.047826F, -0.3346145F);
      tail1 = new ModelRenderer(this, 0, 46);
      tail1.addBox(0F, 1F, 0F, 0, 3, 4);
      tail1.setRotationPoint(0F, 4F, 34.5F);
      tail1.setTextureSize(128, 128);
      tail1.mirror = true;
      setRotation(tail1, 0.5576792F, -0.5948578F, 0F);
      tail2 = new ModelRenderer(this, 0, 46);
      tail2.addBox(0F, 1F, 0F, 0, 3, 4);
      tail2.setRotationPoint(0F, 4F, 34.5F);
      tail2.setTextureSize(128, 128);
      tail2.mirror = true;
      setRotation(tail2, 0.5576792F, 0.5948606F, 0F);
      tail3 = new ModelRenderer(this, 0, 60);
      tail3.addBox(-1F, 0F, -1F, 2, 1, 4);
      tail3.setRotationPoint(0F, 3.4F, 33.5F);
      tail3.setTextureSize(128, 128);
      tail3.mirror = true;
      setRotation(tail3, -0.5205006F, 0F, 0F);
      tail4 = new ModelRenderer(this, 0, 75);
      tail4.addBox(-2.5F, 0F, -3F, 5, 4, 5);
      tail4.setRotationPoint(0F, 3F, 25F);
      tail4.setTextureSize(128, 128);
      tail4.mirror = true;
      setRotation(tail4, 0.4461433F, 0F, 0F);
      tail5 = new ModelRenderer(this, 0, 75);
      tail5.addBox(-3.5F, 0F, -3F, 7, 6, 7);
      tail5.setRotationPoint(0F, 7F, 17F);
      tail5.setTextureSize(128, 128);
      tail5.mirror = true;
      setRotation(tail5, 0.4089647F, 0F, 0F);
      tail6 = new ModelRenderer(this, 0, 60);
      tail6.addBox(-3F, 0F, -3F, 6, 5, 7);
      tail6.setRotationPoint(0F, 6F, 20F);
      tail6.setTextureSize(128, 128);
      tail6.mirror = true;
      setRotation(tail6, 0.669215F, 0F, 0F);
      tail7 = new ModelRenderer(this, 0, 75);
      tail7.addBox(-4.5F, 0F, -3F, 9, 8, 8);
      tail7.setRotationPoint(0F, 6.5F, 10F);
      tail7.setTextureSize(128, 128);
      tail7.mirror = true;
      setRotation(tail7, -0.0743572F, 0F, 0F);
      tail8 = new ModelRenderer(this, 0, 46);
      tail8.addBox(0F, 1F, 0F, 0, 3, 4);
      tail8.setRotationPoint(0F, 4F, 34.5F);
      tail8.setTextureSize(128, 128);
      tail8.mirror = true;
      setRotation(tail8, 0.5576792F, 0F, 0F);
      tail9 = new ModelRenderer(this, 0, 60);
      tail9.addBox(-4F, 0F, -3F, 8, 7, 7);
      tail9.setRotationPoint(0F, 7.33F, 14F);
      tail9.setTextureSize(128, 128);
      tail9.mirror = true;
      setRotation(tail9, 0.2230717F, 0F, 0F);
      tail10 = new ModelRenderer(this, 0, 60);
      tail10.addBox(-2F, 0F, -3F, 4, 3, 4);
      tail10.setRotationPoint(0F, 2.2F, 29.5F);
      tail10.setTextureSize(128, 128);
      tail10.mirror = true;
      setRotation(tail10, 0.0743572F, 0F, 0F);
      tail11 = new ModelRenderer(this, 0, 75);
      tail11.addBox(-1.5F, 0F, -2F, 3, 2, 4);
      tail11.setRotationPoint(0F, 2.7F, 31.5F);
      tail11.setTextureSize(128, 128);
      tail11.mirror = true;
      setRotation(tail11, -0.1115358F, 0F, 0F);
    LegR = new ModelRenderer(this, "LegR");
    LegR.setRotationPoint(-5F, 12F, 6F);
    setRotation(LegR, 0F, 0F, 0F);
    LegR.mirror = true;
      ModelRenderer legright1 = new ModelRenderer(this, 0, 24);
      legright1.addBox(-4.5F, -2F, -3F, 5, 5, 5);
      legright1.setRotationPoint(0F, 0F, 0F);
      legright1.setTextureSize(128, 128);
      legright1.mirror = true;
      setRotation(legright1, -0.4833219F, 0F, 0F);
      ModelRenderer legright2 = new ModelRenderer(this, 0, 100);
      legright2.addBox(-4F, -2F, -3F, 4, 4, 4);
      legright2.setRotationPoint(0F, -1F, 1F);
      legright2.setTextureSize(128, 128);
      legright2.mirror = true;
      setRotation(legright2, -0.4833219F, 0F, 0F);
      ModelRenderer rightfoot = new ModelRenderer(this, 0, 37);
      rightfoot.addBox(-1.5F, 0F, -5F, 3, 2, 6);
      rightfoot.setRotationPoint(-2F, 3F, 0F);
      rightfoot.setTextureSize(128, 128);
      rightfoot.mirror = true;
      setRotation(rightfoot, 0.7063936F, 0F, 0F);
      
      LegR.addChild(legright1);
      LegR.addChild(legright2);
      LegR.addChild(rightfoot);
      
    LegL = new ModelRenderer(this, "LegL");
    LegL.setRotationPoint(5F, 12F, 6F);
    setRotation(LegL, 0F, 0F, 0F);
    LegL.mirror = true;
      ModelRenderer leftleg1 = new ModelRenderer(this, 0, 24);
      leftleg1.addBox(-0.5F, -2F, -3F, 5, 5, 5);
      leftleg1.setRotationPoint(0F, 0F, 0F);
      leftleg1.setTextureSize(128, 128);
      leftleg1.mirror = true;
      setRotation(leftleg1, -0.4833219F, 0F, 0F);
      ModelRenderer leftleg2 = new ModelRenderer(this, 0, 100);
      leftleg2.addBox(0F, -2F, -3F, 4, 4, 4);
      leftleg2.setRotationPoint(0F, -1F, 1F);
      leftleg2.setTextureSize(128, 128);
      leftleg2.mirror = true;
      setRotation(leftleg2, -0.4833219F, 0F, 0F);
      ModelRenderer  leftfoot = new ModelRenderer(this, 0, 37);
      leftfoot.addBox(-1.5F, 0F, -5F, 3, 2, 6);
      leftfoot.setRotationPoint(2F, 3F, 0F);
      leftfoot.setTextureSize(128, 128);
      leftfoot.mirror = true;
      setRotation(leftfoot, 0.7063936F, 0F, 0F);
      
      LegL.addChild(leftleg1);
      LegL.addChild(leftleg2);
      LegL.addChild(leftfoot);
      
    BodyBase = new ModelRenderer(this, "BodyBase");
    BodyBase.setRotationPoint(0F, 5F, 6F);
    setRotation(BodyBase, 0F, 0F, 0F);
    BodyBase.mirror = true;
      ModelRenderer Body_Bottom = new ModelRenderer(this, 0, 0);
      Body_Bottom.addBox(-5F, 0F, -3F, 10, 9, 9);
      Body_Bottom.setRotationPoint(0F, 0F, 0F);
      Body_Bottom.setTextureSize(128, 128);
      Body_Bottom.mirror = true;
      setRotation(Body_Bottom, -0.2230717F, 0F, 0F);
      ModelRenderer Chest = new ModelRenderer(this, 0, 0);
      Chest.addBox(-4F, 0F, -4F, 8, 8, 9);
      Chest.setRotationPoint(0F, -1.5F, -1F);
      Chest.setTextureSize(128, 128);
      Chest.mirror = true;
      setRotation(Chest, -0.8922867F, 0F, 0F);
      
      BodyBase.addChild(Chest);
      BodyBase.addChild(Body_Bottom);
      
    ArmL = new ModelRenderer(this, "ArmL");
    ArmL.setRotationPoint(3F, 0.6F, -8.5F);
    setRotation(ArmL, 0F, 0F, 0F);
    ArmL.mirror = true;
      ModelRenderer armleft3 = new ModelRenderer(this, 0, 0);
      armleft3.addBox(-1F, 0F, -1F, 2, 4, 2);
      armleft3.setRotationPoint(0F, 0F, 0F);
      armleft3.setTextureSize(128, 128);
      armleft3.mirror = true;
      setRotation(armleft3, -0.3665191F, 0F, 0F);
      ModelRenderer  armleft2 = new ModelRenderer(this, 0, 0);
      armleft2.addBox(-1F, 0F, -1F, 2, 4, 2);
      armleft2.setRotationPoint(0F, 3F, -0.5F);
      armleft2.setTextureSize(128, 128);
      armleft2.mirror = true;
      setRotation(armleft2, -1.779306F, 0F, 0F);
      ModelRenderer armleft1 = new ModelRenderer(this, 0, 0);
      armleft1.addBox(-1F, 0F, -1F, 2, 3, 1);
      armleft1.setRotationPoint(0F, 1.3F, -3.5F);
      armleft1.setTextureSize(128, 128);
      armleft1.mirror = true;
      setRotation(armleft1, -0.1487144F, 0F, 0F);
      
      ArmL.addChild(armleft3);
      ArmL.addChild(armleft2);
      ArmL.addChild(armleft1);
      BodyBase.addChild(ArmL);
      
    ArmR = new ModelRenderer(this, "ArmR");
    ArmR.setRotationPoint(-3F, 0.6F, -8.5F);
    setRotation(ArmR, 0F, 0F, 0F);
    ArmR.mirror = true;
      ModelRenderer armright3 = new ModelRenderer(this, 0, 0);
      armright3.addBox(-1F, 0F, -1F, 2, 4, 2);
      armright3.setRotationPoint(0F, 0F, 0F);
      armright3.setTextureSize(128, 128);
      armright3.mirror = true;
      setRotation(armright3, -0.3665191F, 0F, 0F);
      ModelRenderer armright2 = new ModelRenderer(this, 0, 0);
      armright2.addBox(-1F, 0F, -1F, 2, 4, 2);
      armright2.setRotationPoint(0F, 3F, -0.5F);
      armright2.setTextureSize(128, 128);
      armright2.mirror = true;
      setRotation(armright2, -1.779306F, 0F, 0F);
      ModelRenderer  armright1 = new ModelRenderer(this, 0, 0);
      armright1.addBox(-1F, 0F, -1F, 2, 3, 1);
      armright1.setRotationPoint(0F, 1.3F, -3.5F);
      armright1.setTextureSize(128, 128);
      armright1.mirror = true;
      setRotation(armright1, -0.1487144F, 0F, 0F);
      
      ArmR.addChild(armright3);
      ArmR.addChild(armright2);
      ArmR.addChild(armright1);
      BodyBase.addChild(ArmR);
      
    NeckBase = new ModelRenderer(this, "NeckBase");
    NeckBase.setRotationPoint(0F, 1F, -6F);
    setRotation(NeckBase, 0F, 0F, 0F);
    NeckBase.mirror = true;
      ModelRenderer Neck_lower_1 = new ModelRenderer(this, 0, 0);
      Neck_lower_1.addBox(-3.5F, 0F, -4F, 7, 5, 7);
      Neck_lower_1.setRotationPoint(0F, -3.5F, 2F);
      Neck_lower_1.setTextureSize(128, 128);
      Neck_lower_1.mirror = true;
      setRotation(Neck_lower_1, -1.413717F, 0F, 0F);
      
      NeckBase.addChild(Neck_lower_1);
      BodyBase.addChild(NeckBase);
      
    Neck1 = new ModelRenderer(this, "Neck1");
    Neck1.setRotationPoint(0F, -7F, -1.2F);
    setRotation(Neck1, 0F, 0F, 0F);
    Neck1.mirror = true;
      ModelRenderer  Neck_upper_2 = new ModelRenderer(this, 0, 0);
      Neck_upper_2.addBox(-2.5F, -2F, -4F, 5, 4, 5);
      Neck_upper_2.setRotationPoint(0F, 0F, 0F);
      Neck_upper_2.setTextureSize(128, 128);
      Neck_upper_2.mirror = true;
      setRotation(Neck_upper_2, -1.32645F, 0F, 0F);
      
      Neck1.addChild(Neck_upper_2);
      NeckBase.addChild(Neck1);
      
    Neck2 = new ModelRenderer(this, "Neck2");
    Neck2.setRotationPoint(0F, -3.8F, -0.8F);
    setRotation(Neck2, 0F, 0F, 0F);
    Neck2.mirror = true;
      ModelRenderer  Neck_upper_1 = new ModelRenderer(this, 0, 0);
      Neck_upper_1.addBox(-2F, -1.5F, -4F, 4, 3, 5);
      Neck_upper_1.setRotationPoint(0F, 0F, 0F);
      Neck_upper_1.setTextureSize(128, 128);
      Neck_upper_1.mirror = true;
      setRotation(Neck_upper_1, -1.117011F, 0F, 0F);
      
      Neck2.addChild(Neck_upper_1);
      Neck1.addChild(Neck2);
      
    HeadBase = new ModelRenderer(this, "HeadBase");
    HeadBase.setRotationPoint(0F, -3F, -1.5F);
    setRotation(HeadBase, 0F, 0F, 0F);
    HeadBase.mirror = true;
      ModelRenderer   Horn_part2 = new ModelRenderer(this, 0, 98);
      Horn_part2.addBox(-0.5F, -1F, 0F, 1, 2, 9);
      Horn_part2.setRotationPoint(2.4F, -3.7F, 0F);
      Horn_part2.setTextureSize(128, 128);
      Horn_part2.mirror = true;
      setRotation(Horn_part2, 0.296706F, -0.1047198F, 0F);
      ModelRenderer Horn_part4 = new ModelRenderer(this, 0, 98);
      Horn_part4.addBox(-0.5F, -1F, 0F, 1, 2, 9);
      Horn_part4.setRotationPoint(1.4F, -3.7F, 0F);
      Horn_part4.setTextureSize(128, 128);
      Horn_part4.mirror = true;
      setRotation(Horn_part4, 0.296706F, 0F, 0F);
      ModelRenderer  Head = new ModelRenderer(this, 0, 109);
      Head.addBox(-3F, 0F, -5F, 6, 4, 7);
      Head.setRotationPoint(0F, -3.7F, -0.7F);
      Head.setTextureSize(128, 128);
      Head.mirror = true;
      setRotation(Head, 0.296706F, 0F, 0F);
      ModelRenderer  Head_top = new ModelRenderer(this, 0, 98);
      Head_top.addBox(-2.5F, 0F, -5F, 5, 2, 6);
      Head_top.setRotationPoint(0F, -4.9F, 0F);
      Head_top.setTextureSize(128, 128);
      Head_top.mirror = true;
      setRotation(Head_top, 0.4014257F, 0F, 0F);
      ModelRenderer  Eye_cover1 = new ModelRenderer(this, 0, 120);
      Eye_cover1.addBox(-4F, 0F, -2.5F, 2, 3, 3);
      Eye_cover1.setRotationPoint(0F, -3.7F, -2F);
      Eye_cover1.setTextureSize(128, 128);
      Eye_cover1.mirror = true;
      setRotation(Eye_cover1, 0.296706F, 0F, 0F);
      ModelRenderer  Eye_cover2 = new ModelRenderer(this, 0, 120);
      Eye_cover2.addBox(2F, 0F, -2.5F, 2, 3, 3);
      Eye_cover2.setRotationPoint(0F, -3.7F, -2F);
      Eye_cover2.setTextureSize(128, 128);
      Eye_cover2.mirror = true;
      setRotation(Eye_cover2, 0.2974289F, 0F, 0F);
      ModelRenderer   Eye2 = new ModelRenderer(this, 91, 105);
      Eye2.addBox(2.5F, 0F, -2.5F, 1, 1, 1);
      Eye2.setRotationPoint(0F, -2.7F, -1F);
      Eye2.setTextureSize(128, 128);
      Eye2.mirror = true;
      setRotation(Eye2, 0.2974289F, 0F, 0F);
      ModelRenderer  Eye1 = new ModelRenderer(this, 90, 103);
      Eye1.addBox(-3.5F, 0F, -2.5F, 1, 1, 1);
      Eye1.setRotationPoint(0F, -2.7F, -1F);
      Eye1.setTextureSize(128, 128);
      Eye1.mirror = true;
      setRotation(Eye1, 0.2974289F, 0F, 0F);
      ModelRenderer Horn_part1 = new ModelRenderer(this, 0, 98);
      Horn_part1.addBox(-0.5F, -1F, 0F, 1, 2, 9);
      Horn_part1.setRotationPoint(-2.4F, -3.7F, 0F);
      Horn_part1.setTextureSize(128, 128);
      Horn_part1.mirror = true;
      setRotation(Horn_part1, 0.296706F, 0.1047198F, 0F);
      ModelRenderer  Horn_part3 = new ModelRenderer(this, 0, 98);
      Horn_part3.addBox(-0.5F, -1F, 0F, 1, 2, 9);
      Horn_part3.setRotationPoint(-1.4F, -3.7F, 0F);
      Horn_part3.setTextureSize(128, 128);
      Horn_part3.mirror = true;
      setRotation(Horn_part3, 0.296706F, 0F, 0F);
      
      HeadBase.addChild(Horn_part1);
      HeadBase.addChild(Horn_part2);
      HeadBase.addChild(Horn_part3);
      HeadBase.addChild(Horn_part4);
      HeadBase.addChild(Eye_cover1);
      HeadBase.addChild(Eye_cover2);
      HeadBase.addChild(Eye1);
      HeadBase.addChild(Eye2);
      HeadBase.addChild(Head);
      HeadBase.addChild(Head_top);
      Neck2.addChild(HeadBase);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    wing1.render(f5);
    wing2.render(f5);
    tail1.render(f5);
    tail2.render(f5);
    tail3.render(f5);
    tail4.render(f5);
    tail5.render(f5);
    tail6.render(f5);
    tail7.render(f5);
    tail8.render(f5);
    tail9.render(f5);
    tail10.render(f5);
    tail11.render(f5);
    LegR.render(f5);
    LegL.render(f5);
    BodyBase.render(f5);
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
