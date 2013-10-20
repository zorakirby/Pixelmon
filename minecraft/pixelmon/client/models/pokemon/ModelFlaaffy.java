// Date: 2/25/2013 10:22:42 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelFlaaffy extends PixelmonModelBase {
	// fields

	PixelmonModelRenderer Body;

	public ModelFlaaffy() {
		textureWidth = 128;
		textureHeight = 64;

		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 16, -1);
		PixelmonModelRenderer body_main = new PixelmonModelRenderer(this, 0, 54);
		body_main.addBox(-2.5F, -1F, -1F, 5, 6, 4);
		body_main.setTextureSize(128, 64);
		body_main.mirror = true;
		setRotation(body_main, 0.1047198F, 0F, 0F);
		PixelmonModelRenderer neck = new PixelmonModelRenderer(this, 0, 45);
		neck.addBox(-1.533333F, -5.6F, -0.4666667F, 3, 5, 3);
		neck.setTextureSize(128, 64);
		neck.mirror = true;
		setRotation(neck, 0.0174533F, 0F, 0F);
		PixelmonModelRenderer body_bottom = new PixelmonModelRenderer(this, 0,
				39);
		body_bottom.addBox(-2F, 3.533333F, -0.3333333F, 4, 2, 3);
		body_bottom.setTextureSize(128, 64);
		body_bottom.mirror = true;
		setRotation(body_bottom, 0.1047198F, 0F, 0F);
		PixelmonModelRenderer body_front = new PixelmonModelRenderer(this, 0, 0);
		body_front.addBox(-2F, -0.2666667F, -1.466667F, 4, 5, 2);
		body_front.setTextureSize(128, 64);
		body_front.mirror = true;
		setRotation(body_front, 0.0698132F, 0F, 0F);
		PixelmonModelRenderer body_wool_front = new PixelmonModelRenderer(this,
				20, 0);
		body_wool_front.addBox(-3F, -2.6F, -1.6F, 6, 3, 3);
		body_wool_front.setTextureSize(128, 64);
		body_wool_front.mirror = true;
		setRotation(body_wool_front, 0.2094395F, 0F, 0F);
		PixelmonModelRenderer body_wool_front_2 = new PixelmonModelRenderer(
				this, 23, 1);
		body_wool_front_2.addBox(-1.5F, -1.6F, -1.733333F, 3, 2, 2);
		body_wool_front_2.setTextureSize(128, 64);
		body_wool_front_2.mirror = true;
		setRotation(body_wool_front_2, 0.3665191F, 0F, 0F);
		PixelmonModelRenderer body_wool_front_L = new PixelmonModelRenderer(
				this, 46, 16);
		body_wool_front_L.addBox(0.03333334F, -2.6F, -0.4666667F, 3, 3, 4);
		body_wool_front_L.setTextureSize(128, 64);
		body_wool_front_L.mirror = true;
		setRotation(body_wool_front_L, 0F, 0F, 0.3490659F);
		PixelmonModelRenderer body_wool_front_R = new PixelmonModelRenderer(
				this, 46, 16);
		body_wool_front_R.addBox(-2.966667F, -2.6F, -0.4666667F, 3, 3, 4);
		body_wool_front_R.setTextureSize(128, 64);
		body_wool_front_R.mirror = true;
		setRotation(body_wool_front_R, 0.0174533F, 0F, -0.3490659F);
		PixelmonModelRenderer body_wool_back = new PixelmonModelRenderer(this,
				112, 0);
		body_wool_back.addBox(-2.5F, -2.6F, 1.2F, 5, 4, 3);
		body_wool_back.setTextureSize(128, 64);
		body_wool_back.mirror = true;
		setRotation(body_wool_back, 0.1396263F, 0F, 0F);
		PixelmonModelRenderer body_wool_back_2 = new PixelmonModelRenderer(
				this, 118, 13);
		body_wool_back_2.addBox(-2F, -3.466667F, 3.466667F, 4, 3, 1);
		body_wool_back_2.setTextureSize(128, 64);
		body_wool_back_2.mirror = true;
		setRotation(body_wool_back_2, 0.0349066F, 0F, 0F);
		PixelmonModelRenderer body_wool_top = new PixelmonModelRenderer(this,
				52, 0);
		body_wool_top.addBox(-2F, -3.4F, -1.066667F, 4, 2, 5);
		body_wool_top.setTextureSize(128, 64);
		body_wool_top.mirror = true;
		setRotation(body_wool_top, 0.1570796F, 0F, 0F);
		PixelmonModelRenderer body_wool_bottom = new PixelmonModelRenderer(
				this, 118, 8);
		body_wool_bottom.addBox(-1.5F, -0.2666667F, 1.666667F, 3, 2, 2);
		body_wool_bottom.setTextureSize(128, 64);
		body_wool_bottom.mirror = true;
		setRotation(body_wool_bottom, 0.1396263F, 0F, 0F);
		PixelmonModelRenderer body_wool_2_L = new PixelmonModelRenderer(this,
				37, 0);
		body_wool_2_L.addBox(0.5666667F, -3F, -1.066667F, 3, 2, 4);
		body_wool_2_L.setTextureSize(128, 64);
		body_wool_2_L.mirror = true;
		setRotation(body_wool_2_L, 0F, 0.0174533F, -0.2268928F);
		PixelmonModelRenderer body_wool_2_R = new PixelmonModelRenderer(this,
				37, 0);
		body_wool_2_R.addBox(-3.6F, -3F, -1.066667F, 3, 2, 4);
		body_wool_2_R.setTextureSize(128, 64);
		body_wool_2_R.mirror = true;
		setRotation(body_wool_2_R, 0F, -0.0174533F, 0.2268928F);

		PixelmonModelRenderer LeftLeg = new PixelmonModelRenderer(this,
				"Left Leg");
		LeftLeg.setRotationPoint(2, 4, 2);
		PixelmonModelRenderer L_leg_1 = new PixelmonModelRenderer(this, 0, 9);
		L_leg_1.addBox(-0.8F, -1.733333F, -2.533333F, 2, 4, 4);
		L_leg_1.setTextureSize(128, 64);
		L_leg_1.mirror = true;
		setRotation(L_leg_1, 0.1396263F, -0.1919862F, 0F);
		PixelmonModelRenderer L_leg_2 = new PixelmonModelRenderer(this, 13, 10);
		L_leg_2.addBox(0F, 1.333333F, -0.4666667F, 1, 2, 2);
		L_leg_2.setTextureSize(128, 64);
		L_leg_2.mirror = true;
		setRotation(L_leg_2, -0.2792527F, -0.2792527F, 0F);
		PixelmonModelRenderer L_foot = new PixelmonModelRenderer(this, 12, 15);
		L_foot.addBox(-0.5333334F, 3F, -2.333333F, 2, 1, 3);
		L_foot.setTextureSize(128, 64);
		L_foot.mirror = true;
		setRotation(L_foot, 0F, -0.3141593F, 0F);
		PixelmonModelRenderer L_leg_3 = new PixelmonModelRenderer(this, 0, 18);
		L_leg_3.addBox(-0.4F, -1.266667F, -2.066667F, 2, 3, 3);
		L_leg_3.setTextureSize(128, 64);
		L_leg_3.mirror = true;
		setRotation(L_leg_3, 0.1396263F, -0.1919862F, 0F);

		PixelmonModelRenderer RightLeg = new PixelmonModelRenderer(this,
				"Right Leg");
		RightLeg.setRotationPoint(-2, 4, 2);
		PixelmonModelRenderer R_leg_1 = new PixelmonModelRenderer(this, 0, 9);
		R_leg_1.addBox(-1.2F, -1.733333F, -2.533333F, 2, 4, 4);
		R_leg_1.setTextureSize(128, 64);
		R_leg_1.mirror = true;
		setRotation(R_leg_1, 0.1396263F, 0.1919862F, 0F);
		PixelmonModelRenderer R_leg_3 = new PixelmonModelRenderer(this, 0, 18);
		R_leg_3.addBox(-1.6F, -1.266667F, -2.066667F, 2, 3, 3);
		R_leg_3.setTextureSize(128, 64);
		R_leg_3.mirror = true;
		setRotation(R_leg_3, 0.1396263F, 0.1919862F, 0F);
		PixelmonModelRenderer R_leg_2 = new PixelmonModelRenderer(this, 13, 10);
		R_leg_2.addBox(-1F, 1.333333F, -0.4666667F, 1, 2, 2);
		R_leg_2.setTextureSize(128, 64);
		R_leg_2.mirror = true;
		setRotation(R_leg_2, -0.2792527F, 0.2792527F, 0F);
		PixelmonModelRenderer R_foot = new PixelmonModelRenderer(this, 12, 15);
		R_foot.addBox(-1.533333F, 3F, -2.333333F, 2, 1, 3);
		R_foot.setTextureSize(128, 64);
		R_foot.mirror = true;
		setRotation(R_foot, 0F, 0.3141593F, 0F);

		PixelmonModelRenderer Tail = new PixelmonModelRenderer(this, "Tail");
		Tail.setRotationPoint(0, 4, 3);
		PixelmonModelRenderer tail_1 = new PixelmonModelRenderer(this, 19, 59);
		tail_1.addBox(-2F, -1.133333F, -0.3333333F, 4, 3, 2);
		tail_1.setTextureSize(128, 64);
		tail_1.mirror = true;
		setRotation(tail_1, -0.4537856F, 0F, 0F);
		PixelmonModelRenderer tail_2 = new PixelmonModelRenderer(this, 19, 52);
		tail_2.addBox(-1.5F, -1.2F, -1.266667F, 3, 3, 3);
		tail_2.setRotationPoint(0F, 1F, 2F);
		tail_2.setTextureSize(128, 64);
		tail_2.mirror = true;
		setRotation(tail_2, -0.2094395F, 0F, 0F);
		PixelmonModelRenderer tail_3 = new PixelmonModelRenderer(this, 19, 46);
		tail_3.addBox(-1F, -1.6F, -0.8666667F, 2, 2, 3);
		tail_3.setRotationPoint(0F, 2F, 4F);
		tail_3.setTextureSize(128, 64);
		tail_3.mirror = true;
		setRotation(tail_3, 0.0872665F, 0F, 0F);
		PixelmonModelRenderer tail_4 = new PixelmonModelRenderer(this, 19, 40);
		tail_4.addBox(-0.5F, -0.6F, -0.8666667F, 1, 1, 4);
		tail_4.setRotationPoint(0F, 1F, 4F);
		tail_4.setTextureSize(128, 64);
		tail_4.mirror = true;
		setRotation(tail_4, 0.296706F, 0F, 0F);
		PixelmonModelRenderer tail_ball = new PixelmonModelRenderer(this, 20,
				35);
		tail_ball.addBox(-1F, -1F, 2.733333F, 2, 2, 2);
		tail_ball.setRotationPoint(0F, 1F, 4F);
		tail_ball.setTextureSize(128, 64);
		tail_ball.mirror = true;
		setRotation(tail_ball, 0.3490659F, 0F, 0F);

		PixelmonModelRenderer LeftArm = new PixelmonModelRenderer(this,
				"Left Arm");
		LeftArm.setRotationPoint(2, 0, 1);
		PixelmonModelRenderer arm_L = new PixelmonModelRenderer(this, 11, 20);
		arm_L.addBox(-1.133333F, 0.06666667F, -1.066667F, 2, 3, 2);
		arm_L.setTextureSize(128, 64);
		arm_L.mirror = true;
		setRotation(arm_L, 0F, 0.1745329F, -1.117011F);

		PixelmonModelRenderer RightArm = new PixelmonModelRenderer(this,
				"Right Arm");
		RightArm.setRotationPoint(-2, 0, 1);
		PixelmonModelRenderer arm_R = new PixelmonModelRenderer(this, 11, 20);
		arm_R.addBox(-0.9F, 0.06666667F, -1.066667F, 2, 3, 2);
		arm_R.setTextureSize(128, 64);
		arm_R.mirror = true;
		setRotation(arm_R, 0F, -0.1745329F, 1.117011F);

		PixelmonModelRenderer Head = new PixelmonModelRenderer(this, "Head");
		Head.setRotationPoint(0, -5, 0);
		PixelmonModelRenderer head_2 = new PixelmonModelRenderer(this, 21, 25);
		head_2.addBox(-2F, -1.533333F, -2.266667F, 4, 3, 5);
		head_2.setTextureSize(128, 64);
		head_2.mirror = true;
		setRotation(head_2, 1.047198F, 0F, 0F);
		PixelmonModelRenderer horn_1_L = new PixelmonModelRenderer(this, 44, 32);
		horn_1_L.addBox(0.6F, -2.533333F, -0.2666667F, 2, 3, 3);
		horn_1_L.setTextureSize(128, 64);
		horn_1_L.mirror = true;
		setRotation(horn_1_L, 0.0698132F, 0F, 0F);
		PixelmonModelRenderer horn_2_L = new PixelmonModelRenderer(this, 44, 27);
		horn_2_L.addBox(2.4F, -2.466667F, 0.06666667F, 1, 2, 2);
		horn_2_L.setTextureSize(128, 64);
		horn_2_L.mirror = true;
		setRotation(horn_2_L, 0.0698132F, 0F, 0.1047198F);
		PixelmonModelRenderer horn_3_L = new PixelmonModelRenderer(this, 44, 24);
		horn_3_L.addBox(2.933333F, -2.2F, 0.4666667F, 1, 1, 1);
		horn_3_L.setTextureSize(128, 64);
		horn_3_L.mirror = true;
		setRotation(horn_3_L, 0.0698132F, 0F, 0.1396263F);
		PixelmonModelRenderer horn_1_R = new PixelmonModelRenderer(this, 44, 32);
		horn_1_R.addBox(-2.6F, -2.533333F, -0.2666667F, 2, 3, 3);
		horn_1_R.setTextureSize(128, 64);
		horn_1_R.mirror = true;
		setRotation(horn_1_R, 0.0698132F, 0F, 0F);
		PixelmonModelRenderer horn_2_R = new PixelmonModelRenderer(this, 44, 27);
		horn_2_R.addBox(-3.4F, -2.466667F, 0.06666667F, 1, 2, 2);
		horn_2_R.setTextureSize(128, 64);
		horn_2_R.mirror = true;
		setRotation(horn_2_R, 0.0698132F, 0F, -0.1047198F);
		PixelmonModelRenderer horn_3_R = new PixelmonModelRenderer(this, 44, 24);
		horn_3_R.addBox(-4.066667F, -2.2F, 0.4666667F, 1, 1, 1);
		horn_3_R.setTextureSize(128, 64);
		horn_3_R.mirror = true;
		setRotation(horn_3_R, 0.0698132F, 0F, -0.1396263F);
		PixelmonModelRenderer head__wool_top__2 = new PixelmonModelRenderer(
				this, 71, 0);
		head__wool_top__2.addBox(-2F, -2.2F, 1.4F, 4, 5, 3);
		head__wool_top__2.setTextureSize(128, 64);
		head__wool_top__2.mirror = true;
		setRotation(head__wool_top__2, 0.2792527F, 0F, 0F);
		PixelmonModelRenderer head__wool_curl_L = new PixelmonModelRenderer(
				this, 34, 18);
		head__wool_curl_L.addBox(-1.2F, -4.6F, -2.4F, 2, 3, 3);
		head__wool_curl_L.setTextureSize(128, 64);
		head__wool_curl_L.mirror = true;
		setRotation(head__wool_curl_L, -0.7330383F, 1.151917F, 0.1047198F);
		PixelmonModelRenderer head__wool_curl_R = new PixelmonModelRenderer(
				this, 23, 18);
		head__wool_curl_R.addBox(-0.8F, -4.6F, -2.4F, 2, 3, 3);
		head__wool_curl_R.setTextureSize(128, 64);
		head__wool_curl_R.mirror = true;
		setRotation(head__wool_curl_R, -0.7330383F, -1.151917F, -0.1047198F);
		PixelmonModelRenderer head__wool_top = new PixelmonModelRenderer(this,
				21, 7);
		head__wool_top.addBox(-1.5F, -4.133333F, 0.06666667F, 3, 3, 3);
		head__wool_top.setTextureSize(128, 64);
		head__wool_top.mirror = true;
		setRotation(head__wool_top, -0.0523599F, 0F, 0F);
		PixelmonModelRenderer head__wool_back_ = new PixelmonModelRenderer(
				this, 36, 7);
		head__wool_back_.addBox(-2F, -2.2F, 1.4F, 4, 5, 3);
		head__wool_back_.setTextureSize(128, 64);
		head__wool_back_.mirror = true;
		setRotation(head__wool_back_, 0.2792527F, 0F, 0F);
		PixelmonModelRenderer head__wool_back_2 = new PixelmonModelRenderer(
				this, 52, 8);
		head__wool_back_2.addBox(-1.5F, -1.933333F, 1.866667F, 3, 4, 3);
		head__wool_back_2.setTextureSize(128, 64);
		head__wool_back_2.mirror = true;
		setRotation(head__wool_back_2, 0.2792527F, 0F, 0F);
		PixelmonModelRenderer head__wool_back_L = new PixelmonModelRenderer(
				this, 40, 10);
		head__wool_back_L.addBox(-2.533333F, -1.733333F, 2.266667F, 2, 3, 2);
		head__wool_back_L.setTextureSize(128, 64);
		head__wool_back_L.mirror = true;
		setRotation(head__wool_back_L, 0.2792527F, 0.9773844F, 0.3141593F);
		PixelmonModelRenderer head__wool_back_R = new PixelmonModelRenderer(
				this, 40, 10);
		head__wool_back_R.addBox(0.4666667F, -1.733333F, 2.266667F, 2, 3, 2);
		head__wool_back_R.setTextureSize(128, 64);
		head__wool_back_R.mirror = true;
		setRotation(head__wool_back_R, 0.2792527F, -0.9773844F, -0.3141593F);

		Body.addChild(body_main);
		Body.addChild(neck);
		LeftLeg.addChild(L_leg_1);
		LeftLeg.addChild(L_leg_2);
		LeftLeg.addChild(L_foot);
		LeftLeg.addChild(L_leg_3);
		RightLeg.addChild(R_leg_1);
		RightLeg.addChild(R_leg_3);
		RightLeg.addChild(R_leg_2);
		RightLeg.addChild(R_foot);
		Body.addChild(body_bottom);
		Body.addChild(body_front);
		Tail.addChild(tail_1);
		Tail.addChild(tail_2);
		Tail.addChild(tail_3);
		Tail.addChild(tail_4);
		Tail.addChild(tail_ball);
		LeftArm.addChild(arm_L);
		RightArm.addChild(arm_R);
		Head.addChild(head_2);
		Head.addChild(horn_1_L);
		Head.addChild(horn_2_L);
		Head.addChild(horn_3_L);
		Head.addChild(horn_1_R);
		Head.addChild(horn_2_R);
		Head.addChild(horn_3_R);
		Head.addChild(head__wool_top__2);
		Head.addChild(head__wool_curl_L);
		Head.addChild(head__wool_curl_R);
		Head.addChild(head__wool_top);
		Head.addChild(head__wool_back_);
		Head.addChild(head__wool_back_2);
		Head.addChild(head__wool_back_L);
		Body.addChild(body_wool_front);
		Body.addChild(body_wool_front_2);
		Body.addChild(body_wool_front_L);
		Body.addChild(body_wool_front_R);
		Body.addChild(body_wool_back);
		Body.addChild(body_wool_back_2);
		Body.addChild(body_wool_top);
		Head.addChild(head__wool_back_R);
		Body.addChild(body_wool_bottom);
		Body.addChild(body_wool_2_L);
		Body.addChild(body_wool_2_R);
		Body.addChild(LeftLeg);
		Body.addChild(RightLeg);
		Body.addChild(LeftArm);
		Body.addChild(RightArm);
		Body.addChild(Tail);
		Body.addChild(Head);

		ModuleHead headModule = new ModuleHead(Head);

		ModuleArm leftArmModule = new ModuleArm(LeftArm, EnumArm.Left, EnumRotation.x, 1, 0.5F);
		ModuleArm rightArmModule = new ModuleArm(RightArm, EnumArm.Right, EnumRotation.x, 1, 0.5F);

		float legspeed = 0.65F;
		float legRotationLimit = 1.4F;

		ModuleLeg leftLegModule = new ModuleLeg(LeftLeg, EnumLeg.FrontLeft,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(RightLeg, EnumLeg.FrontRight,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleTailBasic tailModule = new ModuleTailBasic(Tail, .2F, .05F, legspeed);

		skeleton = new SkeletonBiped(Body, headModule, leftArmModule,
				rightArmModule, leftLegModule, rightLegModule, null);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		Body.render(f5);
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y,
			float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
