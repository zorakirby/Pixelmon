// Date: 5/10/2013 10:05:01 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;

public class ModelGrowlithe extends PixelmonModelBase
{
  //fields
    PixelmonModelRenderer Body;
  
  public ModelGrowlithe()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    
      Body = new PixelmonModelRenderer(this, "Body");
      Body.setRotationPoint(0, 17, 0);
      PixelmonModelRenderer body_1 = new PixelmonModelRenderer(this, 0, 21);
      body_1.addBox(-3F, -2.266667F, -5F, 6, 6, 5);
      body_1.setTextureSize(64, 32);
      body_1.mirror = true;
      setRotation(body_1, -0.0174533F, 0F, 0F);
      PixelmonModelRenderer body_2 = new PixelmonModelRenderer(this, 22, 20);
      body_2.addBox(-2.5F, -2F, -3F, 5, 5, 7);
      body_2.setTextureSize(64, 32);
      body_2.mirror = true;
      setRotation(body_2, -0.0698132F, 0F, 0F);
      PixelmonModelRenderer belly = new PixelmonModelRenderer(this, 46, 2);
      belly.addBox(-2F, 2.8F, -0.4F, 4, 1, 3);
      belly.setTextureSize(64, 32);
      belly.mirror = true;
      setRotation(belly, 0.0698132F, 0F, 0F);
      PixelmonModelRenderer neck = new PixelmonModelRenderer(this, 24, 22);
      neck.addBox(-2F, 2.066667F, -5F, 4, 4, 6);
      neck.setTextureSize(64, 32);
      neck.mirror = true;
      setRotation(neck, -1.099557F, 0F, 0F);
      PixelmonModelRenderer mane_ = new PixelmonModelRenderer(this, 40, 0);
      mane_.addBox(-2.5F, 4.4F, -4.8F, 5, 2, 7);
      mane_.setTextureSize(64, 32);
      mane_.mirror = true;
      setRotation(mane_, -1.099557F, 0F, 0F);
      PixelmonModelRenderer mane__L = new PixelmonModelRenderer(this, 26, 10);
      mane__L.addBox(-2.5F, -6.1F, -5F, 5, 1, 6);
      mane__L.setTextureSize(64, 32);
      mane__L.mirror = true;
      setRotation(mane__L, -2.024582F, 2.495821F, 0.296706F);
      PixelmonModelRenderer mane__R = new PixelmonModelRenderer(this, 26, 10);
      mane__R.addBox(-2.5F, 6.133333F, -4.133333F, 5, 1, 6);
      mane__R.setTextureSize(64, 32);
      mane__R.mirror = true;
      setRotation(mane__R, -1.27409F, 0.715585F, 0.296706F);
      Body.addChild(body_1);
      Body.addChild(body_2);
      Body.addChild(belly);
      Body.addChild(neck);
      Body.addChild(mane_);
      Body.addChild(mane__L);
      Body.addChild(mane__R);
      
      
      PixelmonModelRenderer BLLeg = new PixelmonModelRenderer(this, "Back Left Leg");
      BLLeg.setRotationPoint(2, 1, 3);
      PixelmonModelRenderer back_leg_1_L = new PixelmonModelRenderer(this, 0, 14);
      back_leg_1_L.addBox(-1F, -1F, -2F, 2, 4, 3);
      back_leg_1_L.setTextureSize(64, 32);
      back_leg_1_L.mirror = true;
      setRotation(back_leg_1_L, -0.0349066F, 0F, 0F);
      PixelmonModelRenderer back_leg_2_L = new PixelmonModelRenderer(this, 31, 26);
      back_leg_2_L.addBox(-1.2F, 2F, -1.533333F, 2, 4, 2);
      back_leg_2_L.setTextureSize(64, 32);
      back_leg_2_L.mirror = true;
      setRotation(back_leg_2_L, 0.0698132F, 0F, 0F);
      PixelmonModelRenderer paw_L = new PixelmonModelRenderer(this, 55, 11);
      paw_L.addBox(-1.2F, 5F, -1.6F, 2, 1, 2);
      paw_L.setTextureSize(64, 32);
      paw_L.mirror = true;
      setRotation(paw_L, 0F, 0F, 0F);
      BLLeg.addChild(back_leg_1_L);
      BLLeg.addChild(back_leg_2_L);
      BLLeg.addChild(paw_L);
      Body.addChild(BLLeg);
      
      
      PixelmonModelRenderer BRLeg = new PixelmonModelRenderer(this, "Back Right Leg");
      BRLeg.setRotationPoint(-2, 1, 3);
      PixelmonModelRenderer back_leg_1_R = new PixelmonModelRenderer(this, 0, 14);
      back_leg_1_R.addBox(-1F, -1F, -2F, 2, 4, 3);
      back_leg_1_R.setTextureSize(64, 32);
      back_leg_1_R.mirror = true;
      setRotation(back_leg_1_R, -0.0349066F, 0F, 0F);
      PixelmonModelRenderer back_leg_2_R = new PixelmonModelRenderer(this, 32, 26);
      back_leg_2_R.addBox(-0.8F, 2F, -1.533333F, 2, 4, 2);
      back_leg_2_R.setTextureSize(64, 32);
      back_leg_2_R.mirror = true;
      setRotation(back_leg_2_R, 0.0698132F, 0F, 0F);
      PixelmonModelRenderer paw_R = new PixelmonModelRenderer(this, 55, 11);
      paw_R.addBox(-0.8F, 5F, -1.6F, 2, 1, 2);
      paw_R.setTextureSize(64, 32);
      paw_R.mirror = true;
      setRotation(paw_R, 0F, 0F, 0F);
      BRLeg.addChild(back_leg_1_R);
      BRLeg.addChild(back_leg_2_R);
      BRLeg.addChild(paw_R);
      Body.addChild(BRLeg);
      
      
      PixelmonModelRenderer FLLeg = new PixelmonModelRenderer(this, "Front Left Leg");
      FLLeg.setRotationPoint(3, 1, -4);
      PixelmonModelRenderer front_leg_L = new PixelmonModelRenderer(this, 17, 18);
      front_leg_L.addBox(-1.5F, 0F, -1F, 2, 6, 2);
      front_leg_L.setTextureSize(64, 32);
      front_leg_L.mirror = true;
      setRotation(front_leg_L, 0F, 0F, 0F);
      PixelmonModelRenderer claw_1_L = new PixelmonModelRenderer(this, 15, 0);
      claw_1_L.addBox(-1.5F, 5F, -4.466667F, 1, 1, 2);
      claw_1_L.setTextureSize(64, 32);
      claw_1_L.mirror = true;
      setRotation(claw_1_L, 0.3490659F, 0F, 0F);
      PixelmonModelRenderer claw_2_L = new PixelmonModelRenderer(this, 15, 0);
      claw_2_L.addBox(-0.5F, 5F, -4.333333F, 1, 1, 2);
      claw_2_L.setTextureSize(64, 32);
      claw_2_L.mirror = true;
      setRotation(claw_2_L, 0.3490659F, 0F, 0F);
      FLLeg.addChild(front_leg_L);
      FLLeg.addChild(claw_1_L);
      FLLeg.addChild(claw_2_L);
      Body.addChild(FLLeg);
      
      
      
      PixelmonModelRenderer FRLeg = new PixelmonModelRenderer(this, "FRLeg");
      FRLeg.setRotationPoint(-3, 1, -4);
      PixelmonModelRenderer front_leg_R = new PixelmonModelRenderer(this, 17, 18);
      front_leg_R.addBox(-0.5F, 0F, -1F, 2, 6, 2);
      front_leg_R.setTextureSize(64, 32);
      front_leg_R.mirror = true;
      setRotation(front_leg_R, 0F, 0F, 0F);
      PixelmonModelRenderer claw_1_R = new PixelmonModelRenderer(this, 15, 0);
      claw_1_R.addBox(-0.5F, 5F, -4.3F, 1, 1, 2);
      claw_1_R.setTextureSize(64, 32);
      claw_1_R.mirror = true;
      setRotation(claw_1_R, 0.3490659F, 0F, 0F);
      PixelmonModelRenderer claw_2_R = new PixelmonModelRenderer(this, 15, 0);
      claw_2_R.addBox(0.5F, 5F, -4.5F, 1, 1, 2);
      claw_2_R.setTextureSize(64, 32);
      claw_2_R.mirror = true;
      setRotation(claw_2_R, 0.3490659F, 0F, 0F);
      FRLeg.addChild(front_leg_R);
      FRLeg.addChild(claw_1_R);
      FRLeg.addChild(claw_2_R);
      Body.addChild(FRLeg);
      
      
      
      PixelmonModelRenderer Head = new PixelmonModelRenderer(this, "Head");
      Head.setRotationPoint(0, -2, -6);
      PixelmonModelRenderer head_main = new PixelmonModelRenderer(this, 27, 23);
      head_main.addBox(-1F, -4F, -3.666667F, 2, 5, 4);
      head_main.setTextureSize(64, 32);
      head_main.mirror = true;
      setRotation(head_main, 0F, 0F, 0F);
      PixelmonModelRenderer head_L = new PixelmonModelRenderer(this, 10, 10);
      head_L.addBox(0.3333333F, -4F, -1.733333F, 3, 5, 4);
      head_L.setTextureSize(64, 32);
      head_L.mirror = true;
      setRotation(head_L, 0F, 0.8028515F, 0F);
      PixelmonModelRenderer head_R = new PixelmonModelRenderer(this, 10, 10);
      head_R.addBox(-3.3F, -4F, -1.733333F, 3, 5, 4);
      head_R.setTextureSize(64, 32);
      head_R.mirror = true;
      setRotation(head_R, 0F, -0.8028515F, 0F);
      PixelmonModelRenderer head_back = new PixelmonModelRenderer(this, 25, 23);
      head_back.addBox(-2.5F, -4F, -1.6F, 5, 5, 4);
      head_back.setTextureSize(64, 32);
      head_back.mirror = true;
      setRotation(head_back, 0F, 0F, 0F);
      PixelmonModelRenderer head_L_back = new PixelmonModelRenderer(this, 26, 24);
      head_L_back.addBox(0.3333333F, -4F, -2.2F, 3, 5, 3);
      head_L_back.setTextureSize(64, 32);
      head_L_back.mirror = true;
      setRotation(head_L_back, 0F, -0.4014257F, 0F);
      PixelmonModelRenderer head_R_back = new PixelmonModelRenderer(this, 28, 24);
      head_R_back.addBox(-3.3F, -4F, -2.2F, 3, 5, 3);
      head_R_back.setTextureSize(64, 32);
      head_R_back.mirror = true;
      setRotation(head_R_back, 0F, 0.4014257F, 0F);
      PixelmonModelRenderer snout_bottom = new PixelmonModelRenderer(this, 48, 0);
      snout_bottom.addBox(-1.5F, -0.06666667F, -3.9F, 3, 2, 4);
      snout_bottom.setTextureSize(64, 32);
      snout_bottom.mirror = true;
      setRotation(snout_bottom, -0.0872665F, 0F, 0F);
      PixelmonModelRenderer snout = new PixelmonModelRenderer(this, 46, 26);
      snout.addBox(-1.5F, -2F, -4.333333F, 3, 2, 3);
      snout.setTextureSize(64, 32);
      snout.mirror = true;
      setRotation(snout, 0.3665191F, 0F, 0F);
      PixelmonModelRenderer cheek_top_L = new PixelmonModelRenderer(this, 47, 0);
      cheek_top_L.addBox(1.766667F, -1.733333F, -2.666667F, 2, 2, 4);
      cheek_top_L.setTextureSize(64, 32);
      cheek_top_L.mirror = true;
      setRotation(cheek_top_L, 0.2443461F, 0.6108652F, 0.1745329F);
      PixelmonModelRenderer cheek_bottom_L = new PixelmonModelRenderer(this, 48, 0);
      cheek_bottom_L.addBox(1.766667F, -0.2666667F, -2.666667F, 2, 1, 4);
      cheek_bottom_L.setTextureSize(64, 32);
      cheek_bottom_L.mirror = true;
      setRotation(cheek_bottom_L, 0.0349066F, 0.6108652F, 0.1745329F);
      PixelmonModelRenderer snout_top_R = new PixelmonModelRenderer(this, 48, 0);
      snout_top_R.addBox(-3.8F, -1.733333F, -2.666667F, 2, 2, 4);
      snout_top_R.setTextureSize(64, 32);
      snout_top_R.mirror = true;
      setRotation(snout_top_R, 0.2443461F, -0.6108652F, -0.1570796F);
      PixelmonModelRenderer cheek_bottom_R = new PixelmonModelRenderer(this, 48, 0);
      cheek_bottom_R.addBox(-3.8F, -0.2666667F, -2.666667F, 2, 1, 4);
      cheek_bottom_R.setTextureSize(64, 32);
      cheek_bottom_R.mirror = true;
      setRotation(cheek_bottom_R, 0.0349066F, -0.6108652F, -0.1919862F);
      PixelmonModelRenderer head_top = new PixelmonModelRenderer(this, 23, 26);
      head_top.addBox(-2F, -4.8F, -2.333333F, 4, 2, 4);
      head_top.setTextureSize(64, 32);
      head_top.mirror = true;
      setRotation(head_top, 0.0698132F, 0F, 0F);
      PixelmonModelRenderer head_fluff_ = new PixelmonModelRenderer(this, 21, 0);
      head_fluff_.addBox(0.4666667F, -9F, -3.6F, 1, 6, 6);
      head_fluff_.setTextureSize(64, 32);
      head_fluff_.mirror = true;
      setRotation(head_fluff_, -0.1745329F, 0.715585F, -0.2094395F);
      PixelmonModelRenderer ear_L = new PixelmonModelRenderer(this, 0, 0);
      ear_L.addBox(2.666667F, -4F, -1.4F, 6, 6, 1);
      ear_L.setTextureSize(64, 32);
      ear_L.mirror = true;
      setRotation(ear_L, 0F, -0.3490659F, -0.3665191F);
      PixelmonModelRenderer head_fluff_2 = new PixelmonModelRenderer(this, 50, 0);
      head_fluff_2.addBox(-1.533333F, -6.8F, -2F, 3, 3, 4);
      head_fluff_2.setTextureSize(64, 32);
      head_fluff_2.mirror = true;
      setRotation(head_fluff_2, 0.0174533F, 0.715585F, -0.0349066F);
      PixelmonModelRenderer ear_R = new PixelmonModelRenderer(this, 0, 7);
      ear_R.addBox(-8.7F, -4F, -1.4F, 6, 6, 1);
      ear_R.setTextureSize(64, 32);
      ear_R.mirror = true;
      setRotation(ear_R, 0F, 0.3490659F, 0.3665191F);
      Head.addChild(head_main);
      Head.addChild(head_L);
      Head.addChild(head_R);
      Head.addChild(head_back);
      Head.addChild(head_L_back);
      Head.addChild(head_R_back);
      Head.addChild(snout_bottom);
      Head.addChild(snout);
      Head.addChild(cheek_top_L);
      Head.addChild(cheek_bottom_L);
      Head.addChild(snout_top_R);
      Head.addChild(cheek_bottom_R);
      Head.addChild(head_top);
      Head.addChild(head_fluff_);
      Head.addChild(ear_L);
      Head.addChild(head_fluff_2);
      Head.addChild(ear_R);
      Body.addChild(Head);
      
      
      
      PixelmonModelRenderer Tail = new PixelmonModelRenderer(this, "Tail");
      Tail.setRotationPoint(0, -1, 4);
      PixelmonModelRenderer tail_main = new PixelmonModelRenderer(this, 46, 0);
      tail_main.addBox(-2F, -2F, -1F, 4, 4, 5);
      tail_main.setTextureSize(64, 32);
      tail_main.mirror = true;
      setRotation(tail_main, 0.5759587F, 0F, 0F);
      PixelmonModelRenderer tail_bottom = new PixelmonModelRenderer(this, 50, 0);
      tail_bottom.addBox(-1.5F, -2.2F, -0.5333334F, 3, 5, 4);
      tail_bottom.setTextureSize(64, 32);
      tail_bottom.mirror = true;
      setRotation(tail_bottom, 0.5759587F, 0F, 0F);
      PixelmonModelRenderer tail_top = new PixelmonModelRenderer(this, 54, 0);
      tail_top.addBox(-1.533333F, -1.466667F, 2.666667F, 3, 3, 2);
      tail_top.setTextureSize(64, 32);
      tail_top.mirror = true;
      setRotation(tail_top, 0.5759587F, 0F, 0F);
      PixelmonModelRenderer tail_fluff = new PixelmonModelRenderer(this, 41, 10);
      tail_fluff.addBox(-1.733333F, -3.733333F, 2F, 1, 7, 7);
      tail_fluff.setTextureSize(64, 32);
      tail_fluff.mirror = true;
      setRotation(tail_fluff, 0.1396263F, 0.2792527F, 0F);
      Tail.addChild(tail_main);
      Tail.addChild(tail_bottom);
      Tail.addChild(tail_top);
      Tail.addChild(tail_fluff);
      Body.addChild(Tail);
      
      
      
      ModuleHead headModule = new ModuleHead(Head);

		float legspeed = 0.8F;
		float legRotationLimit = 1.1F;

		ModuleLeg frontlegLModule = new ModuleLeg(FLLeg, EnumLeg.FrontLeft,
				EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg frontlegRModule = new ModuleLeg(FRLeg, EnumLeg.FrontRight,
				EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backlegLModule = new ModuleLeg(BLLeg, EnumLeg.BackLeft,
				EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backlegRModule = new ModuleLeg(BRLeg, EnumLeg.BackRight,
				EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleTailBasic tailModule = new ModuleTailBasic(Tail, .2F, .05F, legspeed);

		skeleton = new SkeletonQuadruped(Body, headModule, frontlegLModule,
				frontlegRModule, backlegLModule, backlegRModule, tailModule);
      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Body.render(f5);
  }
  
  private void setRotation(PixelmonModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
  }

}
