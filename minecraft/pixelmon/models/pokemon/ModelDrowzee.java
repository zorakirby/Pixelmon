package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelDrowzee extends ModelBase {
	// fields
	ModelRenderer HeadBase;
	ModelRenderer BodyBase;
	ModelRenderer RightArmBase;
	ModelRenderer LeftArmBase;
	ModelRenderer LeftLegBase;
	ModelRenderer RightLegBase;

	public ModelDrowzee() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this, "HeadBase");
		HeadBase.setRotationPoint(0F, 6F, 0F);
		setRotation(HeadBase, 0F, 0F, 0F);
		HeadBase.mirror = true;
		ModelRenderer Head = new ModelRenderer(this, 0, 42);
		Head.addBox(-3.5F, -5F, -3F, 7, 6, 6);
		Head.setRotationPoint(0F, 0F, 0.5F);
		Head.setTextureSize(128, 128);
		Head.mirror = true;
		setRotation(Head, 0.1115358F, 0F, 0F);
		ModelRenderer R_ear = new ModelRenderer(this, 10, 18);
		R_ear.addBox(-3.5F, -5F, -3F, 3, 3, 1);
		R_ear.setRotationPoint(0F, -2F, 2F);
		R_ear.setTextureSize(128, 128);
		R_ear.mirror = true;
		setRotation(R_ear, 0F, 0F, -0.3717861F);
		ModelRenderer Neck = new ModelRenderer(this, 0, 30);
		Neck.addBox(-3.5F, -5F, -3F, 7, 6, 5);
		Neck.setRotationPoint(0F, 0F, 2.6F);
		Neck.setTextureSize(128, 128);
		Neck.mirror = true;
		setRotation(Neck, 0.4089647F, 0F, 0F);
		ModelRenderer L_ear = new ModelRenderer(this, 0, 18);
		L_ear.addBox(0.5F, -5F, -3F, 3, 3, 1);
		L_ear.setRotationPoint(0F, -2F, 2F);
		L_ear.setTextureSize(128, 128);
		L_ear.mirror = true;
		setRotation(L_ear, 0F, 0F, 0.37179F);
		ModelRenderer nose = new ModelRenderer(this, 0, 23);
		nose.addBox(-1.5F, -4F, -3F, 3, 3, 2);
		nose.setRotationPoint(0F, -1.6F, -0.1F);
		nose.setTextureSize(128, 128);
		nose.mirror = true;
		setRotation(nose, 0.5205006F, 0F, 0F);
		ModelRenderer nose_1 = new ModelRenderer(this, 11, 23);
		nose_1.addBox(-1.5F, -4F, -3F, 3, 3, 2);
		nose_1.setRotationPoint(0F, -1.6F, -1.1F);
		nose_1.setTextureSize(128, 128);
		nose_1.mirror = true;
		setRotation(nose_1, 0.8179294F, 0F, 0F);
		ModelRenderer nose_2 = new ModelRenderer(this, 11, 23);
		nose_2.addBox(-1.5F, -4F, -3F, 3, 3, 2);
		nose_2.setRotationPoint(0F, -1.6F, -1.9F);
		nose_2.setTextureSize(128, 128);
		nose_2.mirror = true;
		setRotation(nose_2, 1.189716F, 0F, 0F);

		HeadBase.addChild(Head);
		HeadBase.addChild(R_ear);
		HeadBase.addChild(L_ear);
		HeadBase.addChild(Neck);
		HeadBase.addChild(nose);
		HeadBase.addChild(nose_1);
		HeadBase.addChild(nose_2);

		BodyBase = new ModelRenderer(this, "BodyBase");
		BodyBase.setRotationPoint(0F, 6F, 0F);
		setRotation(BodyBase, 0F, 0F, 0F);
		BodyBase.mirror = true;
		ModelRenderer B_Body = new ModelRenderer(this, 0, 88);
		B_Body.addBox(-5F, 0F, 0F, 10, 6, 7);
		B_Body.setRotationPoint(0F, 5.5F, -1.5F);
		B_Body.setTextureSize(128, 128);
		B_Body.mirror = true;
		setRotation(B_Body, -0.1001757F, 0F, 0F);
		ModelRenderer Body = new ModelRenderer(this, 35, 88);
		Body.addBox(-4.5F, 0F, 0F, 9, 6, 6);
		Body.setRotationPoint(0F, 0F, -1.3F);
		Body.setTextureSize(128, 128);
		Body.mirror = true;
		setRotation(Body, 0.0371786F, 0F, 0F);

		BodyBase.addChild(Body);
		BodyBase.addChild(B_Body);

		RightArmBase = new ModelRenderer(this, "RightArmBase");
		RightArmBase.setRotationPoint(-4F, 8F, 2F);
		setRotation(RightArmBase, 0F, 0F, 0F);
		RightArmBase.mirror = true;
		ModelRenderer R_T_arm = new ModelRenderer(this, 19, 77);
		R_T_arm.addBox(-3.5F, -1F, -2F, 3, 6, 4);
		R_T_arm.setRotationPoint(0F, 0F, 0F);
		R_T_arm.setTextureSize(128, 128);
		R_T_arm.mirror = true;
		setRotation(R_T_arm, -0.7435722F, 0F, 0F);
		ModelRenderer R_B_arm = new ModelRenderer(this, 15, 59);
		R_B_arm.addBox(-3.5F, -1F, -2F, 3, 6, 3);
		R_B_arm.setRotationPoint(0F, 4F, -3F);
		R_B_arm.setTextureSize(128, 128);
		R_B_arm.mirror = true;
		setRotation(R_B_arm, -1.412787F, 0F, 0F);
		ModelRenderer RFinger1 = new ModelRenderer(this, 15, 66);
		RFinger1.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		RFinger1.setRotationPoint(-1F, 3.3F, -7.7F);
		RFinger1.setTextureSize(128, 128);
		RFinger1.mirror = true;
		setRotation(RFinger1, -1.412787F, 0F, 0F);
		ModelRenderer RFinger2 = new ModelRenderer(this, 15, 66);
		RFinger2.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		RFinger2.setRotationPoint(-2F, 5.3F, -7.5F);
		RFinger2.setTextureSize(128, 128);
		RFinger2.mirror = true;
		setRotation(RFinger2, -1.412787F, 0F, 0F);
		ModelRenderer RFinger3 = new ModelRenderer(this, 15, 66);
		RFinger3.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		RFinger3.setRotationPoint(-3F, 3.3F, -7.7F);
		RFinger3.setTextureSize(128, 128);
		RFinger3.mirror = true;
		setRotation(RFinger3, -1.412787F, 0F, 0F);

		RightArmBase.addChild(R_T_arm);
		RightArmBase.addChild(R_B_arm);
		RightArmBase.addChild(RFinger1);
		RightArmBase.addChild(RFinger2);
		RightArmBase.addChild(RFinger3);

		LeftArmBase = new ModelRenderer(this, "LeftArmBase");
		LeftArmBase.setRotationPoint(4F, 8F, 2F);
		setRotation(LeftArmBase, 0F, 0F, 0F);
		LeftArmBase.mirror = true;
		ModelRenderer L_T_arm = new ModelRenderer(this, 19, 77);
		L_T_arm.addBox(0.5F, -1F, -2F, 3, 6, 4);
		L_T_arm.setRotationPoint(0F, 0F, 0F);
		L_T_arm.setTextureSize(128, 128);
		L_T_arm.mirror = true;
		setRotation(L_T_arm, -0.7435722F, 0F, 0F);
		ModelRenderer L_B_arm = new ModelRenderer(this, 15, 59);
		L_B_arm.addBox(0.5F, -1F, -2F, 3, 6, 3);
		L_B_arm.setRotationPoint(0F, 4F, -3F);
		L_B_arm.setTextureSize(128, 128);
		L_B_arm.mirror = true;
		setRotation(L_B_arm, -1.412787F, 0F, 0F);
		ModelRenderer LFinger1 = new ModelRenderer(this, 15, 66);
		LFinger1.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		LFinger1.setRotationPoint(1F, 3.3F, -7.7F);
		LFinger1.setTextureSize(128, 128);
		LFinger1.mirror = true;
		setRotation(LFinger1, -1.412787F, 0F, 0F);
		ModelRenderer LFinger2 = new ModelRenderer(this, 15, 66);
		LFinger2.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		LFinger2.setRotationPoint(2F, 5.3F, -7.5F);
		LFinger2.setTextureSize(128, 128);
		LFinger2.mirror = true;
		setRotation(LFinger2, -1.412787F, 0F, 0F);
		ModelRenderer LFinger3 = new ModelRenderer(this, 15, 66);
		LFinger3.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		LFinger3.setRotationPoint(3F, 3.3F, -7.7F);
		LFinger3.setTextureSize(128, 128);
		LFinger3.mirror = true;
		setRotation(LFinger3, -1.412787F, 0F, 0F);

		LeftArmBase.addChild(L_T_arm);
		LeftArmBase.addChild(L_B_arm);
		LeftArmBase.addChild(LFinger1);
		LeftArmBase.addChild(LFinger2);
		LeftArmBase.addChild(LFinger3);

		LeftLegBase = new ModelRenderer(this, "LeftLegBase");
		LeftLegBase.setRotationPoint(3F, 17F, 1F);
		setRotation(LeftLegBase, 0F, 0F, 0F);
		LeftLegBase.mirror = true;
		ModelRenderer L_leg = new ModelRenderer(this, 0, 69);
		L_leg.addBox(-0.5F, -2F, -2.5F, 4, 6, 5);
		L_leg.setRotationPoint(0F, 0F, 0F);
		L_leg.setTextureSize(128, 128);
		L_leg.mirror = true;
		setRotation(L_leg, -0.2974289F, 0F, 0F);
		ModelRenderer L_B_leg = new ModelRenderer(this, 0, 61);
		L_B_leg.addBox(0F, -1.5F, -1F, 3, 3, 4);
		L_B_leg.setRotationPoint(0F, 3F, 0F);
		L_B_leg.setTextureSize(128, 128);
		L_B_leg.mirror = true;
		setRotation(L_B_leg, -1.449967F, 0F, 0F);
		ModelRenderer L_foot = new ModelRenderer(this, 0, 80);
		L_foot.addBox(-1.5F, -0.5F, -4F, 3, 2, 5);
		L_foot.setRotationPoint(1.5F, 6F, 1F);
		L_foot.setTextureSize(128, 128);
		L_foot.mirror = true;
		setRotation(L_foot, 0F, 0F, 0F);

		LeftLegBase.addChild(L_leg);
		LeftLegBase.addChild(L_B_leg);
		LeftLegBase.addChild(L_foot);

		RightLegBase = new ModelRenderer(this, "RightLegBase");
		RightLegBase.setRotationPoint(-3F, 17F, 1F);
		setRotation(RightLegBase, 0F, 0F, 0F);
		RightLegBase.mirror = true;
		ModelRenderer R_B_leg = new ModelRenderer(this, 0, 61);
		R_B_leg.addBox(-3F, -1.5F, -1F, 3, 3, 4);
		R_B_leg.setRotationPoint(0F, 3F, 0F);
		R_B_leg.setTextureSize(128, 128);
		R_B_leg.mirror = true;
		setRotation(R_B_leg, -1.449966F, 0F, 0F);
		ModelRenderer R_leg = new ModelRenderer(this, 0, 69);
		R_leg.addBox(-3.5F, -2F, -2.5F, 4, 6, 5);
		R_leg.setRotationPoint(0F, 0F, 0F);
		R_leg.setTextureSize(128, 128);
		R_leg.mirror = true;
		setRotation(R_leg, -0.2974289F, 0F, 0F);
		ModelRenderer R_foot = new ModelRenderer(this, 0, 80);
		R_foot.addBox(-1.5F, -0.5F, -4F, 3, 2, 5);
		R_foot.setRotationPoint(-1.5F, 6F, 1F);
		R_foot.setTextureSize(128, 128);
		R_foot.mirror = true;
		setRotation(R_foot, 0F, 0F, 0F);

		RightLegBase.addChild(R_leg);
		RightLegBase.addChild(R_B_leg);
		RightLegBase.addChild(R_foot);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		HeadBase.render(f5);
		BodyBase.render(f5);
		RightArmBase.render(f5);
		LeftArmBase.render(f5);
		LeftLegBase.render(f5);
		RightLegBase.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		HeadBase.rotateAngleY = f3 / (180F / (float) Math.PI);
		HeadBase.rotateAngleX = f4 / (180F / (float) Math.PI);
		RightLegBase.rotateAngleX = MathHelper.cos(f * 1F) * 1.1F * f1;
		LeftLegBase.rotateAngleX = MathHelper.cos(f * 1F + (float) Math.PI) * 1.1F * f1;
		RightArmBase.rotateAngleX = MathHelper.cos(f2 * .15F + (float) Math.PI) * .5F * .5F - .5F;
		LeftArmBase.rotateAngleX = MathHelper.cos(f2 * .15F) * .5F * 0.5F - .5F;
		RightArmBase.rotateAngleY = MathHelper.sin(f2 * .15F) * .5F * 0.5F;
		LeftArmBase.rotateAngleY = MathHelper.sin(f2 * .15F + (float) Math.PI) * .5F * 0.5F;
	}

}