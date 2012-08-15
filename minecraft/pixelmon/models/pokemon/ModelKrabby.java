package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelKrabby extends ModelBase
{
  //fields
    ModelRenderer Left_Eye;
    ModelRenderer Bottem_Head;
    ModelRenderer Top_Head;
    ModelRenderer Right_Eye;
    ModelRenderer Left_Antenna;
    ModelRenderer Right_Antenna;
    ModelRenderer RIGHT_LEG_2;
    ModelRenderer RIGHT_LEG_1;
    ModelRenderer LEFT_LEG_1;
    ModelRenderer LEFT_LEG_2;
    ModelRenderer LEFT_ARM;
    ModelRenderer RIGHT_ARM;
  
  public ModelKrabby()
  {
    textureWidth = 80;
    textureHeight = 80;
    
      Left_Eye = new ModelRenderer(this, 0, 75);
      Left_Eye.addBox(0F, -1F, -1F, 2, 2, 2);
      Left_Eye.setRotationPoint(1F, 15F, 2F);
      Left_Eye.setTextureSize(80, 80);
      Left_Eye.mirror = true;
      setRotation(Left_Eye, 0F, 0F, -0.185895F);
      Bottem_Head = new ModelRenderer(this, 0, 44);
      Bottem_Head.addBox(-3.5F, -3F, -4.5F, 4, 4, 9);
      Bottem_Head.setRotationPoint(2F, 20F, 0F);
      Bottem_Head.setTextureSize(80, 80);
      Bottem_Head.mirror = true;
      setRotation(Bottem_Head, 0F, 0F, 0.4833219F);
      Top_Head = new ModelRenderer(this, 0, 58);
      Top_Head.addBox(-3F, -3F, -4F, 7, 5, 8);
      Top_Head.setRotationPoint(-1F, 17F, 0F);
      Top_Head.setTextureSize(80, 80);
      Top_Head.mirror = true;
      setRotation(Top_Head, 0F, 0F, -0.2974289F);
      Right_Eye = new ModelRenderer(this, 8, 75);
      Right_Eye.addBox(0F, -1F, -1F, 2, 2, 2);
      Right_Eye.setRotationPoint(1F, 15F, -2F);
      Right_Eye.setTextureSize(80, 80);
      Right_Eye.mirror = true;
      setRotation(Right_Eye, 0F, 0F, -0.1858931F);
      Left_Antenna = new ModelRenderer(this, 0, 70);
      Left_Antenna.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
      Left_Antenna.setRotationPoint(0F, 14F, 1F);
      Left_Antenna.setTextureSize(80, 80);
      Left_Antenna.mirror = true;
      setRotation(Left_Antenna, -0.3346145F, 0F, 0F);
      Right_Antenna = new ModelRenderer(this, 0, 70);
      Right_Antenna.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
      Right_Antenna.setRotationPoint(0F, 14F, -1F);
      Right_Antenna.setTextureSize(80, 80);
      Right_Antenna.mirror = true;
      setRotation(Right_Antenna, 0.3346075F, 0F, 0F);
      
    RIGHT_LEG_2 = new ModelRenderer(this, "RIGHT_LEG_2");
    RIGHT_LEG_2.setRotationPoint(-2.5F, 19F, -3.5F);
    setRotation(RIGHT_LEG_2, 0F, 0F, 0F);
    RIGHT_LEG_2.mirror = true;
      ModelRenderer  Right_Foot_2 = new ModelRenderer(this, 0, 0);
      Right_Foot_2.addBox(-1F, 0F, -2.5F, 2, 1, 3);
      Right_Foot_2.setRotationPoint(0F, 4F, -3F);
      Right_Foot_2.setTextureSize(80, 80);
      Right_Foot_2.mirror = true;
      setRotation(Right_Foot_2, 0F, 0F, 0F);
      ModelRenderer  Right_Bottem_leg_2 = new ModelRenderer(this, 0, 26);
      Right_Bottem_leg_2.addBox(-0.5F, 0F, -0.5F, 1, 5, 1);
      Right_Bottem_leg_2.setRotationPoint(0F, 0F, -3.5F);
      Right_Bottem_leg_2.setTextureSize(80, 80);
      Right_Bottem_leg_2.mirror = true;
      setRotation(Right_Bottem_leg_2, 0.1115285F, 0F, 0F);
      ModelRenderer   Right_Leg_2 = new ModelRenderer(this, 0, 20);
      Right_Leg_2.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
      Right_Leg_2.setRotationPoint(0F, 1F, 0F);
      Right_Leg_2.setTextureSize(80, 80);
      Right_Leg_2.mirror = true;
      setRotation(Right_Leg_2, 1.245063F, 0F, 0F);
      ModelRenderer   Right_Shoulder2 = new ModelRenderer(this, 5, 38);
      Right_Shoulder2.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
      Right_Shoulder2.setRotationPoint(0F, 0F, -0.5F);
      Right_Shoulder2.setTextureSize(80, 80);
      Right_Shoulder2.mirror = true;
      setRotation(Right_Shoulder2, -0.6494863F, 0F, 0F);
      
      RIGHT_LEG_2.addChild(Right_Shoulder2);
      RIGHT_LEG_2.addChild(Right_Leg_2);
      RIGHT_LEG_2.addChild(Right_Bottem_leg_2);
      RIGHT_LEG_2.addChild(Right_Foot_2);
      
    RIGHT_LEG_1 = new ModelRenderer(this, "RIGHT_LEG_1");
    RIGHT_LEG_1.setRotationPoint(2.5F, 19F, -3.5F);
    setRotation(RIGHT_LEG_1, 0F, 0F, 0F);
    RIGHT_LEG_1.mirror = true;
      ModelRenderer   Right_Leg_1 = new ModelRenderer(this, 0, 20);
      Right_Leg_1.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
      Right_Leg_1.setRotationPoint(0F, 1F, 0F);
      Right_Leg_1.setTextureSize(80, 80);
      Right_Leg_1.mirror = true;
      setRotation(Right_Leg_1, 1.245063F, 0F, 0F);
      ModelRenderer Right_Bottem_leg_1 = new ModelRenderer(this, 0, 26);
      Right_Bottem_leg_1.addBox(-0.5F, 0F, -0.5F, 1, 5, 1);
      Right_Bottem_leg_1.setRotationPoint(0F, 0F, -3.5F);
      Right_Bottem_leg_1.setTextureSize(80, 80);
      Right_Bottem_leg_1.mirror = true;
      setRotation(Right_Bottem_leg_1, 0.1115285F, 0F, 0F);
      ModelRenderer   Right_Foot_1 = new ModelRenderer(this, 0, 0);
      Right_Foot_1.addBox(-1F, 0F, -2.5F, 2, 1, 3);
      Right_Foot_1.setRotationPoint(0F, 4F, -3F);
      Right_Foot_1.setTextureSize(80, 80);
      Right_Foot_1.mirror = true;
      setRotation(Right_Foot_1, 0F, 0F, 0F);
      ModelRenderer  Right_Shoulder1 = new ModelRenderer(this, 5, 38);
      Right_Shoulder1.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
      Right_Shoulder1.setRotationPoint(0F, 0F, -0.5F);
      Right_Shoulder1.setTextureSize(80, 80);
      Right_Shoulder1.mirror = true;
      setRotation(Right_Shoulder1, -0.6494863F, 0F, 0F);
      
      RIGHT_LEG_1.addChild(Right_Shoulder1);
      RIGHT_LEG_1.addChild(Right_Leg_1);
      RIGHT_LEG_1.addChild(Right_Bottem_leg_1);
      RIGHT_LEG_1.addChild(Right_Foot_1);
      
    LEFT_LEG_1 = new ModelRenderer(this, "LEFT_LEG_1");
    LEFT_LEG_1.setRotationPoint(2.5F, 19F, 3.5F);
    setRotation(LEFT_LEG_1, 0F, 0F, 0F);
    LEFT_LEG_1.mirror = true;
      ModelRenderer  Left_Foot_1 = new ModelRenderer(this, 0, 0);
      Left_Foot_1.addBox(-1F, 0F, -0.5F, 2, 1, 3);
      Left_Foot_1.setRotationPoint(0F, 4F, 3F);
      Left_Foot_1.setTextureSize(80, 80);
      Left_Foot_1.mirror = true;
      setRotation(Left_Foot_1, 0F, 0F, 0F);
      ModelRenderer  Left_Bottem_leg_1 = new ModelRenderer(this, 0, 26);
      Left_Bottem_leg_1.addBox(-0.5F, 0F, -0.5F, 1, 5, 1);
      Left_Bottem_leg_1.setRotationPoint(0F, 0F, 3.5F);
      Left_Bottem_leg_1.setTextureSize(80, 80);
      Left_Bottem_leg_1.mirror = true;
      setRotation(Left_Bottem_leg_1, -0.1115265F, 0F, 0F);
      ModelRenderer  Left_Leg_1 = new ModelRenderer(this, 0, 20);
      Left_Leg_1.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
      Left_Leg_1.setRotationPoint(0F, 1F, 0F);
      Left_Leg_1.setTextureSize(80, 80);
      Left_Leg_1.mirror = true;
      setRotation(Left_Leg_1, -1.245065F, 0F, 0F);
      ModelRenderer  Left_Shoulder1 = new ModelRenderer(this, 5, 38);
      Left_Shoulder1.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
      Left_Shoulder1.setRotationPoint(0F, 0F, 0.5F);
      Left_Shoulder1.setTextureSize(80, 80);
      Left_Shoulder1.mirror = true;
      setRotation(Left_Shoulder1, 0.6494894F, 0F, 0F);
      
      LEFT_LEG_1.addChild(Left_Shoulder1);
      LEFT_LEG_1.addChild(Left_Leg_1);
      LEFT_LEG_1.addChild(Left_Bottem_leg_1);
      LEFT_LEG_1.addChild(Left_Foot_1);
      
    LEFT_LEG_2 = new ModelRenderer(this, "LEFT_LEG_2");
    LEFT_LEG_2.setRotationPoint(-2.5F, 19F, 3.5F);
    setRotation(LEFT_LEG_2, 0F, 0F, 0F);
    LEFT_LEG_2.mirror = true;
      ModelRenderer  Left_Bottem_leg_2 = new ModelRenderer(this, 0, 26);
      Left_Bottem_leg_2.addBox(-0.5F, 0F, -0.5F, 1, 5, 1);
      Left_Bottem_leg_2.setRotationPoint(0F, 0F, 3.5F);
      Left_Bottem_leg_2.setTextureSize(80, 80);
      Left_Bottem_leg_2.mirror = true;
      setRotation(Left_Bottem_leg_2, -0.1115265F, 0F, 0F);
      ModelRenderer  Left_Foot_2 = new ModelRenderer(this, 0, 0);
      Left_Foot_2.addBox(-1F, 0F, -0.5F, 2, 1, 3);
      Left_Foot_2.setRotationPoint(0F, 4F, 3F);
      Left_Foot_2.setTextureSize(80, 80);
      Left_Foot_2.mirror = true;
      setRotation(Left_Foot_2, 0F, 0F, 0F);
      ModelRenderer  Left_Leg_2 = new ModelRenderer(this, 0, 20);
      Left_Leg_2.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
      Left_Leg_2.setRotationPoint(0F, 1F, 0F);
      Left_Leg_2.setTextureSize(80, 80);
      Left_Leg_2.mirror = true;
      setRotation(Left_Leg_2, -1.245065F, 0F, 0F);
      ModelRenderer  Left_Shoulder2 = new ModelRenderer(this, 5, 38);
      Left_Shoulder2.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
      Left_Shoulder2.setRotationPoint(0F, 0F, 0.5F);
      Left_Shoulder2.setTextureSize(80, 80);
      Left_Shoulder2.mirror = true;
      setRotation(Left_Shoulder2, 0.6494894F, 0F, 0F);
      
      LEFT_LEG_2.addChild(Left_Shoulder2);
      LEFT_LEG_2.addChild(Left_Leg_2);
      LEFT_LEG_2.addChild(Left_Bottem_leg_2);
      LEFT_LEG_2.addChild(Left_Foot_2);
      
    LEFT_ARM = new ModelRenderer(this, "LEFT_ARM");
    LEFT_ARM.setRotationPoint(-1F, 16.5F, 4F);
    setRotation(LEFT_ARM, 0F, 0F, 0F);
    LEFT_ARM.mirror = true;
      ModelRenderer Left_top_arm = new ModelRenderer(this, 0, 38);
      Left_top_arm.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
      Left_top_arm.setRotationPoint(0F, -0.3F, 2.5F);
      Left_top_arm.setTextureSize(80, 80);
      Left_top_arm.mirror = true;
      setRotation(Left_top_arm, -0.2974216F, 0F, 0F);
      ModelRenderer  Left_Arm = new ModelRenderer(this, 0, 33);
      Left_Arm.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
      Left_Arm.setRotationPoint(0F, 0.5F, 0F);
      Left_Arm.setTextureSize(80, 80);
      Left_Arm.mirror = true;
      setRotation(Left_Arm, -1.189721F, 0F, 0F);
      ModelRenderer  Left_top_Claw = new ModelRenderer(this, 0, 12);
      Left_top_Claw.addBox(-1.5F, -5F, -1F, 3, 5, 2);
      Left_top_Claw.setRotationPoint(0F, -3.5F, 4F);
      Left_top_Claw.setTextureSize(80, 80);
      Left_top_Claw.mirror = true;
      setRotation(Left_top_Claw, 0.3346075F, 0F, 0F);
      ModelRenderer   Left_bottom_Claw = new ModelRenderer(this, 0, 7);
      Left_bottom_Claw.addBox(-1F, -3F, -0.5F, 2, 3, 1);
      Left_bottom_Claw.setRotationPoint(0F, -3.5F, 3F);
      Left_bottom_Claw.setTextureSize(80, 80);
      Left_bottom_Claw.mirror = true;
      setRotation(Left_bottom_Claw, 1.041002F, 0F, 0F);
      
      LEFT_ARM.addChild(Left_top_arm);
      LEFT_ARM.addChild(Left_Arm);
      LEFT_ARM.addChild(Left_top_Claw);
      LEFT_ARM.addChild(Left_bottom_Claw);
      
    RIGHT_ARM = new ModelRenderer(this, "RIGHT_ARM");
    RIGHT_ARM.setRotationPoint(-1F, 16.5F, -4F);
    setRotation(RIGHT_ARM, 0F, 0F, 0F);
    RIGHT_ARM.mirror = true;
      ModelRenderer  Right_Arm = new ModelRenderer(this, 0, 33);
      Right_Arm.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
      Right_Arm.setRotationPoint(0F, 0.5F, 0F);
      Right_Arm.setTextureSize(80, 80);
      Right_Arm.mirror = true;
      setRotation(Right_Arm, 1.189721F, 0F, 0F);
      ModelRenderer  Right_top_Claw = new ModelRenderer(this, 11, 12);
      Right_top_Claw.addBox(-1.5F, -5F, -1F, 3, 5, 2);
      Right_top_Claw.setRotationPoint(0F, -3.5F, -4F);
      Right_top_Claw.setTextureSize(80, 80);
      Right_top_Claw.mirror = true;
      setRotation(Right_top_Claw, -0.3346145F, 0F, 0F);
      ModelRenderer  Right_bottom_Claw = new ModelRenderer(this, 0, 7);
      Right_bottom_Claw.addBox(-1F, -3F, -0.5F, 2, 3, 1);
      Right_bottom_Claw.setRotationPoint(0F, -3.5F, -3F);
      Right_bottom_Claw.setTextureSize(80, 80);
      Right_bottom_Claw.mirror = true;
      setRotation(Right_bottom_Claw, -1.040998F, 0F, 0F);
      ModelRenderer  Right_top_arm = new ModelRenderer(this, 0, 38);
      Right_top_arm.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
      Right_top_arm.setRotationPoint(0F, -0.3F, -2.5F);
      Right_top_arm.setTextureSize(80, 80);
      Right_top_arm.mirror = true;
      setRotation(Right_top_arm, 0.2974216F, 0F, 0F);
      
      RIGHT_ARM.addChild(Right_top_arm);
      RIGHT_ARM.addChild(Right_Arm);
      RIGHT_ARM.addChild(Right_top_Claw);
      RIGHT_ARM.addChild(Right_bottom_Claw);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Left_Eye.render(f5);
    Bottem_Head.render(f5);
    Top_Head.render(f5);
    Right_Eye.render(f5);
    Left_Antenna.render(f5);
    Right_Antenna.render(f5);
    RIGHT_LEG_2.render(f5);
    RIGHT_LEG_1.render(f5);
    LEFT_LEG_1.render(f5);
    LEFT_LEG_2.render(f5);
    LEFT_ARM.render(f5);
    RIGHT_ARM.render(f5);
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
  	RIGHT_LEG_1.rotateAngleX = MathHelper.cos(f * 1.4F) * 1F * f1;
  	LEFT_LEG_1.rotateAngleX = MathHelper.cos(f * 1.4F + 3.141593F) * 1F * f1;
  	RIGHT_LEG_2.rotateAngleX = MathHelper.cos(f * 1.4F + 3.141593F) * 1F * f1;
  	LEFT_LEG_2.rotateAngleX = MathHelper.cos(f * 1.4F) * 1F * f1;
  }

}
