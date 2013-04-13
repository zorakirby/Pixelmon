package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.Biped.SkeletonBiped;

public class ModelBellsprout extends PixelmonModelBase {
	PixelmonModelRenderer Body;

	// fields

	public ModelBellsprout() {
		textureWidth = 128;
		textureHeight = 128;

		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(1, -26, -3);
		PixelmonModelRenderer Stem_1 = new PixelmonModelRenderer(this, 49, 64);
		Stem_1.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_1.setTextureSize(128, 128);
		Stem_1.mirror = true;
		setRotation(Stem_1, 0F, -1.58825F, 0.1919862F);
		Body.addChild(Stem_1);
		PixelmonModelRenderer Stem_2 = new PixelmonModelRenderer(this, 49, 59);
		Stem_2.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_2.setRotationPoint(0, 0.7F, 4.4F);
		Stem_2.setTextureSize(128, 128);
		Stem_2.mirror = true;
		setRotation(Stem_2, 0F, -1.58825F, 0.8901179F);
		Body.addChild(Stem_2);
		PixelmonModelRenderer Stem_3 = new PixelmonModelRenderer(this, 49, 54);
		Stem_3.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_3.setRotationPoint(0, 4.2F, 7.4F);
		Stem_3.setTextureSize(128, 128);
		Stem_3.mirror = true;
		setRotation(Stem_3, 0F, -1.58825F, 1.27409F);
		Body.addChild(Stem_3);
		PixelmonModelRenderer Stem_4 = new PixelmonModelRenderer(this, 49, 48);
		Stem_4.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_4.setRotationPoint(0, 8.4F, 8.7F);
		Stem_4.setTextureSize(128, 128);
		Stem_4.mirror = true;
		setRotation(Stem_4, 0F, -1.58825F, 1.37881F);
		Body.addChild(Stem_4);
		PixelmonModelRenderer Stem_5 = new PixelmonModelRenderer(this, 49, 42);
		Stem_5.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_5.setRotationPoint(0, 12.30667F, 9.5F);
		Stem_5.setTextureSize(128, 128);
		Stem_5.mirror = true;
		setRotation(Stem_5, 0F, -1.58825F, 1.53589F);
		Body.addChild(Stem_5);
		PixelmonModelRenderer Stem_6 = new PixelmonModelRenderer(this, 49, 36);
		Stem_6.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_6.setRotationPoint(0, 16.8F, 9.7F);
		Stem_6.setTextureSize(128, 128);
		Stem_6.mirror = true;
		setRotation(Stem_6, 0F, -1.58825F, 1.623156F);
		Body.addChild(Stem_6);
		PixelmonModelRenderer Stem_7 = new PixelmonModelRenderer(this, 49, 29);
		Stem_7.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_7.setRotationPoint(0, 21F, 9.6F);
		Stem_7.setTextureSize(128, 128);
		Stem_7.mirror = true;
		setRotation(Stem_7, 0F, -1.58825F, 1.780236F);
		Body.addChild(Stem_7);
		PixelmonModelRenderer Stem_8 = new PixelmonModelRenderer(this, 49, 23);
		Stem_8.addBox(0F, -1F, -1F, 6, 2, 2);
		Stem_8.setRotationPoint(0, 25F, 8.9F);
		Stem_8.setTextureSize(128, 128);
		Stem_8.mirror = true;
		setRotation(Stem_8, 0F, -1.58825F, 1.867502F);
		Body.addChild(Stem_8);
		PixelmonModelRenderer stem_9 = new PixelmonModelRenderer(this, 49, 17);
		stem_9.addBox(-4F, -1F, -1F, 5, 2, 2);
		stem_9.setRotationPoint(0, 34F, 6.3F);
		stem_9.setTextureSize(128, 128);
		stem_9.mirror = true;
		setRotation(stem_9, 0F, -1.58825F, 1.867502F);
		Body.addChild(stem_9);
		PixelmonModelRenderer Stem_10 = new PixelmonModelRenderer(this, 49, 11);
		Stem_10.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_10.setRotationPoint(0, 32.7F, 6.7F);
		Stem_10.setTextureSize(128, 128);
		Stem_10.mirror = true;
		setRotation(Stem_10, 0F, -1.58825F, 1.867502F);
		Body.addChild(Stem_10);
		PixelmonModelRenderer Stem_11 = new PixelmonModelRenderer(this, 49, 6);
		Stem_11.addBox(0F, -1F, -1F, 5, 2, 2);
		Stem_11.setRotationPoint(0, 37.2F, 5.4F);
		Stem_11.setTextureSize(128, 128);
		Stem_11.mirror = true;
		setRotation(Stem_11, 0F, -1.58825F, 1.675516F);
		Body.addChild(Stem_11);
		PixelmonModelRenderer Leaf_L = new PixelmonModelRenderer(this, 66, 57);
		Leaf_L.addBox(-7F, 0F, -16F, 14, 0, 18);
		Leaf_L.setRotationPoint(2F, 19F, 9.6F);
		Leaf_L.setTextureSize(128, 128);
		Leaf_L.mirror = true;
		setRotation(Leaf_L, 0F, -1.383618F, 1.58825F);
		Body.addChild(Leaf_L);
		PixelmonModelRenderer Leaf_R = new PixelmonModelRenderer(this, 65, 77);
		Leaf_R.addBox(-5F, 0F, 0F, 14, 0, 18);
		Leaf_R.setRotationPoint(0, 16F, 9.6F);
		Leaf_R.setTextureSize(128, 128);
		Leaf_R.mirror = true;
		setRotation(Leaf_R, 0F, -1.62441F, 1.58825F);
		Body.addChild(Leaf_R);

		PixelmonModelRenderer Head = new PixelmonModelRenderer(this, "Head");
		Head.setRotationPoint(1, -3, 6);
		// Head.setRotationPoint(2, -29, 3);
		PixelmonModelRenderer EyeL = new PixelmonModelRenderer(this, 41, 32);
		EyeL.addBox(0F, -1F, -1F, 1, 1, 1);
		EyeL.setRotationPoint(1, 0.3F, -10.1F);
		EyeL.setTextureSize(128, 128);
		EyeL.mirror = true;
		setRotation(EyeL, 0.0349066F, 0F, 0F);
		Head.addChild(EyeL);
		PixelmonModelRenderer EyeR = new PixelmonModelRenderer(this, 40, 36);
		EyeR.addBox(0F, -1F, -1F, 1, 1, 1);
		EyeR.setRotationPoint(-4, 0.3f, -10.1F);
		EyeR.setTextureSize(128, 128);
		EyeR.mirror = true;
		setRotation(EyeR, 0.0349066F, 0F, 0F);
		Head.addChild(EyeR);
		PixelmonModelRenderer Head_Tip = new PixelmonModelRenderer(this, 3, 2);
		Head_Tip.addBox(-3F, -4F, 0F, 6, 6, 2);
		Head_Tip.setRotationPoint(-1F, -0.9F, -4);
		Head_Tip.setTextureSize(128, 128);
		Head_Tip.mirror = true;
		setRotation(Head_Tip, 1.832596F, 0F, 0F);
		Head.addChild(Head_Tip);
		PixelmonModelRenderer Head_Top = new PixelmonModelRenderer(this, 3, 2);
		Head_Top.addBox(-3F, -3F, 0F, 7, 8, 2);
		Head_Top.setRotationPoint(-1.5F, 0.1F, -6F);
		Head_Top.setTextureSize(128, 128);
		Head_Top.mirror = true;
		setRotation(Head_Top, 1.832596F, 0F, 0F);
		Head.addChild(Head_Top);
		PixelmonModelRenderer Head_2 = new PixelmonModelRenderer(this, 1, 54);
		Head_2.addBox(-3F, -3F, 0F, 6, 5, 2);
		Head_2.setRotationPoint(-1F, 1F, 0);
		Head_2.setTextureSize(128, 128);
		Head_2.mirror = true;
		setRotation(Head_2, 0.2443461F, 0F, 0F);
		Head.addChild(Head_2);
		PixelmonModelRenderer Head_1 = new PixelmonModelRenderer(this, 18, 53);
		Head_1.addBox(-5F, -4F, 0F, 8, 6, 2);
		Head_1.setRotationPoint(0, 1.7F, -1F);
		Head_1.setTextureSize(128, 128);
		Head_1.mirror = true;
		setRotation(Head_1, 0.2443461F, 0F, 0F);
		Head.addChild(Head_1);
		PixelmonModelRenderer Head_main = new PixelmonModelRenderer(this, 0, 91);
		Head_main.addBox(-5F, -3F, -10F, 8, 7, 10);
		Head_main.setTextureSize(128, 128);
		Head_main.mirror = true;
		setRotation(Head_main, 0.2443461F, 0F, 0F);
		Head.addChild(Head_main);
		PixelmonModelRenderer Mouth_Upper = new PixelmonModelRenderer(this, 1, 110);
		Mouth_Upper.addBox(-3F, -2F, -6F, 6, 5, 6);
		Mouth_Upper.setRotationPoint(-1F, 3F, -8F);
		Mouth_Upper.setTextureSize(128, 128);
		Mouth_Upper.mirror = true;
		setRotation(Mouth_Upper, 0.3346075F, 0F, 0F);
		Head.addChild(Mouth_Upper);
		PixelmonModelRenderer Lip_D = new PixelmonModelRenderer(this, 44, 94);
		Lip_D.addBox(-3F, -0.5F, 0F, 7, 1, 1);
		Lip_D.setRotationPoint(-1.5F, 7F, -13F);
		Lip_D.setTextureSize(128, 128);
		Lip_D.mirror = true;
		setRotation(Lip_D, -1.222541F, 0F, 0F);
		Head.addChild(Lip_D);
		PixelmonModelRenderer Lip_U = new PixelmonModelRenderer(this, 43, 113);
		Lip_U.addBox(-4F, 0F, -0.5F, 7, 1, 1);
		Lip_U.setRotationPoint(-0.4F, 3.1F, -13.8F);
		Lip_U.setTextureSize(128, 128);
		Lip_U.mirror = true;
		setRotation(Lip_U, -1.222541F, 0F, 0F);
		Head.addChild(Lip_U);
		PixelmonModelRenderer Lip_L = new PixelmonModelRenderer(this, 48, 100);
		Lip_L.addBox(-2F, 0F, -1F, 5, 1, 1);
		Lip_L.setRotationPoint(1.5F, 4.8F, -13.2F);
		Lip_L.setTextureSize(128, 128);
		Lip_L.mirror = true;
		setRotation(Lip_L, 0F, -1.58825F, 1.239184F);
		Head.addChild(Lip_L);
		PixelmonModelRenderer Lip_R = new PixelmonModelRenderer(this, 48, 107);
		Lip_R.addBox(-2F, 0F, -1F, 5, 1, 1);
		Lip_R.setRotationPoint(-4.373333F, 4.8F, -13.2F);
		Lip_R.setTextureSize(128, 128);
		Lip_R.mirror = true;
		setRotation(Lip_R, 0F, -1.58825F, 1.239184F);
		Head.addChild(Lip_R);
		Body.addChild(Head);

		PixelmonModelRenderer RightLeg = new PixelmonModelRenderer(this, "Right Leg");
		RightLeg.setRotationPoint(0, 41F, 5.1F);
		PixelmonModelRenderer RootR1 = new PixelmonModelRenderer(this, 49, 69);
		RootR1.addBox(0F, -1F, -1F, 5, 2, 2);
		// RootR1.setRotationPoint(1F, 15F, 2.1F);
		RootR1.setTextureSize(128, 128);
		RootR1.mirror = true;
		setRotation(RootR1, 0.1745329F, -0.2268928F, 2.216568F);
		RightLeg.addChild(RootR1);
		PixelmonModelRenderer RootR2 = new PixelmonModelRenderer(this, 50, 74);
		RootR2.addBox(0F, -1F, -1F, 4, 2, 2);
		RootR2.setRotationPoint(-2.8F, 3.8F, -0.7F);
		RootR2.setTextureSize(128, 128);
		RootR2.mirror = true;
		setRotation(RootR2, 0.1745329F, -0.1745329F, 1.972222F);
		RightLeg.addChild(RootR2);
		PixelmonModelRenderer AnkleR = new PixelmonModelRenderer(this, 53, 85);
		AnkleR.addBox(-1F, 0F, -1F, 2, 2, 2);
		AnkleR.setRotationPoint(-4.2F, 7F, -0.8F);
		AnkleR.setTextureSize(128, 128);
		AnkleR.mirror = true;
		setRotation(AnkleR, 0F, 0F, 0F);
		RightLeg.addChild(AnkleR);
		PixelmonModelRenderer Foot_Right = new PixelmonModelRenderer(this, 0, 33);
		Foot_Right.addBox(-4.5F, 0F, -6F, 8, 0, 11);
		Foot_Right.setRotationPoint(-4F, 9F, -1.1F);
		Foot_Right.setTextureSize(128, 128);
		Foot_Right.mirror = true;
		setRotation(Foot_Right, 0F, 0F, 0F);
		RightLeg.addChild(Foot_Right);
		Body.addChild(RightLeg);

		PixelmonModelRenderer LeftLeg = new PixelmonModelRenderer(this, "Left Leg");
		LeftLeg.setRotationPoint(0.1F, 41F, 5.1F);
		PixelmonModelRenderer RootL1 = new PixelmonModelRenderer(this, 49, 0);
		RootL1.addBox(0F, -1F, -1F, 5, 2, 2);
		// RootL1.setRotationPoint(1.1F, 15F, 2.1F);
		RootL1.setTextureSize(128, 128);
		RootL1.mirror = true;
		setRotation(RootL1, 0F, 0.0872665F, 0.9424778F);
		LeftLeg.addChild(RootL1);
		PixelmonModelRenderer RootL2 = new PixelmonModelRenderer(this, 50, 79);
		RootL2.addBox(0F, -1F, -1F, 4, 2, 2);
		RootL2.setRotationPoint(2.8F, 3.8F, -0.3F);
		RootL2.setTextureSize(128, 128);
		RootL2.mirror = true;
		setRotation(RootL2, 0F, 0.0872665F, 1.204277F);
		LeftLeg.addChild(RootL2);
		PixelmonModelRenderer Foot_left = new PixelmonModelRenderer(this, 0, 20);
		Foot_left.addBox(-4F, 0F, -5F, 8, 0, 11);
		Foot_left.setRotationPoint(3.9F, 9F, -1.1F);
		Foot_left.setTextureSize(128, 128);
		Foot_left.mirror = true;
		setRotation(Foot_left, 0F, 0F, 0F);
		LeftLeg.addChild(Foot_left);
		PixelmonModelRenderer AnkleL = new PixelmonModelRenderer(this, 44, 85);
		AnkleL.addBox(-1F, 0F, -1F, 2, 2, 2);
		AnkleL.setRotationPoint(4.2F, 7F, -0.4F);
		AnkleL.setTextureSize(128, 128);
		AnkleL.mirror = true;
		setRotation(AnkleL, 0F, 0.1047198F, 0F);
		LeftLeg.addChild(AnkleL);
		Body.addChild(LeftLeg);

		ModuleHead headModule = new ModuleHead(Head);
		
		float legspeed = 0.65F;
		float legRotationLimit = 1.4F;

		ModuleLeg leftLegModule = new ModuleLeg(LeftLeg, EnumLeg.FrontLeft,
				EnumPhase.InPhase, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(RightLeg, EnumLeg.FrontRight,
				EnumPhase.InPhase, legRotationLimit, legspeed);
		
		skeleton = new SkeletonBiped(Body, headModule, null, null, leftLegModule, rightLegModule);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Body.render(f5);
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
	}

}
