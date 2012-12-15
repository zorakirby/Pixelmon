package pixelmon.client.models.pokemon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelHaunter extends ModelBase {
	// fields
	ModelRenderer BODYBASE;
	ModelRenderer HandR;
	ModelRenderer HandL;

	public ModelHaunter() {
		textureWidth = 64;
		textureHeight = 64;
		setTextureOffset("BODYBASE.DeleteMe1", 0, 0);

		BODYBASE = new ModelRenderer(this, "BODYBASE");
		BODYBASE.setRotationPoint(0F, 4F, 3F);
		setRotation(BODYBASE, 0F, 0F, 0F);
		BODYBASE.mirror = true;
		ModelRenderer Body = new ModelRenderer(this, 34, 49);
		Body.addBox(0F, 0F, 0F, 8, 8, 7);
		Body.setRotationPoint(-3.5F, -4F, -3F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		ModelRenderer Head = new ModelRenderer(this, 0, 48);
		Head.addBox(0F, 0F, 0F, 6, 1, 5);
		Head.setRotationPoint(-2.5F, -4.4F, -2F);
		Head.setTextureSize(64, 64);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		ModelRenderer Face = new ModelRenderer(this, 0, 56);
		Face.addBox(0F, 0F, 0F, 7, 7, 1);
		Face.setRotationPoint(-3F, -3.5F, -3.7F);
		Face.setTextureSize(64, 64);
		Face.mirror = true;
		setRotation(Face, 0F, 0F, 0F);
		ModelRenderer EyebrowL = new ModelRenderer(this, -1, 56);
		EyebrowL.addBox(-1F, -1F, 0F, 1, 3, 1);
		EyebrowL.setRotationPoint(1.7F, -2.5F, -3.8F);
		EyebrowL.setTextureSize(64, 64);
		EyebrowL.mirror = true;
		setRotation(EyebrowL, 0F, 0F, -1.821757F);
		ModelRenderer EyebrowR = new ModelRenderer(this, -1, 56);
		EyebrowR.addBox(0F, -1F, 0F, 1, 3, 1);
		EyebrowR.setRotationPoint(-0.7F, -2.5F, -3.8F);
		EyebrowR.setTextureSize(64, 64);
		EyebrowR.mirror = true;
		setRotation(EyebrowR, 0F, 0F, 1.821752F);
		ModelRenderer Tail1 = new ModelRenderer(this, 38, 51);
		Tail1.addBox(-2F, 0F, 0F, 5, 5, 5);
		Tail1.setRotationPoint(0F, 3.5F, 0F);
		Tail1.setTextureSize(64, 64);
		Tail1.mirror = true;
		setRotation(Tail1, 0.715585F, 0F, 0F);
		ModelRenderer Tail2 = new ModelRenderer(this, 37, 52);
		Tail2.addBox(-3.5F, 0F, 0F, 7, 7, 5);
		Tail2.setRotationPoint(0.5F, 0F, -2F);
		Tail2.setTextureSize(64, 64);
		Tail2.mirror = true;
		setRotation(Tail2, 0.5759587F, 0F, 0F);
		ModelRenderer Tail3 = new ModelRenderer(this, 40, 55);
		Tail3.addBox(-2F, 0F, 0F, 4, 4, 4);
		Tail3.setRotationPoint(0.5F, 6F, 3F);
		Tail3.setTextureSize(64, 64);
		Tail3.mirror = true;
		setRotation(Tail3, 1.117011F, 0F, 0F);
		ModelRenderer Spike1 = new ModelRenderer(this, 29, 0);
		Spike1.addBox(0F, 0F, 0F, 2, 2, 3);
		Spike1.setRotationPoint(2F, -4F, 3F);
		Spike1.setTextureSize(64, 64);
		Spike1.mirror = true;
		setRotation(Spike1, 0.3490659F, 0.4712389F, 0F);
		ModelRenderer Spike2 = new ModelRenderer(this, 29, 0);
		Spike2.addBox(0F, 0F, 0F, 2, 2, 3);
		Spike2.setRotationPoint(-2.7F, -4F, 2.3F);
		Spike2.setTextureSize(64, 64);
		Spike2.mirror = true;
		setRotation(Spike2, 0.3490659F, -0.4712389F, 0F);
		ModelRenderer Spike3 = new ModelRenderer(this, 29, 6);
		Spike3.addBox(0F, 0F, 0F, 1, 1, 4);
		Spike3.setRotationPoint(3.7F, -4.5F, 5.1F);
		Spike3.setTextureSize(64, 64);
		Spike3.mirror = true;
		setRotation(Spike3, 0.3490659F, 0.4712389F, 0F);
		ModelRenderer Spike4 = new ModelRenderer(this, 29, 6);
		Spike4.addBox(0F, 0F, 0F, 1, 1, 4);
		Spike4.setRotationPoint(-3.5F, -4.5F, 5.1F);
		Spike4.setTextureSize(64, 64);
		Spike4.mirror = true;
		setRotation(Spike4, 0.3490659F, -0.4712389F, 0F);
		ModelRenderer Spike5 = new ModelRenderer(this, 52, 12);
		Spike5.addBox(0F, 0F, 0F, 1, 1, 5);
		Spike5.setRotationPoint(3F, -2F, 3F);
		Spike5.setTextureSize(64, 64);
		Spike5.mirror = true;
		setRotation(Spike5, 0.1047198F, 0.4537856F, 0F);
		ModelRenderer Spike6 = new ModelRenderer(this, 41, 12);
		Spike6.addBox(0F, 0F, 0F, 1, 1, 4);
		Spike6.setRotationPoint(3F, 0F, 3F);
		Spike6.setTextureSize(64, 64);
		Spike6.mirror = true;
		setRotation(Spike6, 0.0523599F, 0.4014257F, 0F);
		ModelRenderer Spike7 = new ModelRenderer(this, 52, 12);
		Spike7.addBox(-1F, 0F, 0F, 1, 1, 5);
		Spike7.setRotationPoint(-2F, -2F, 3F);
		Spike7.setTextureSize(64, 64);
		Spike7.mirror = true;
		setRotation(Spike7, 0.1047198F, -0.4537856F, 0F);
		ModelRenderer Spike8 = new ModelRenderer(this, 41, 12);
		Spike8.addBox(-1F, 0F, 0F, 1, 1, 4);
		Spike8.setRotationPoint(-2F, 0F, 3F);
		Spike8.setTextureSize(64, 64);
		Spike8.mirror = true;
		setRotation(Spike8, 0.0523599F, -0.4014257F, 0F);

		BODYBASE.addChild(Body);
		BODYBASE.addChild(Head);
		BODYBASE.addChild(Face);
		BODYBASE.addChild(EyebrowR);
		BODYBASE.addChild(EyebrowL);
		BODYBASE.addChild(Tail1);
		BODYBASE.addChild(Tail2);
		BODYBASE.addChild(Tail3);
		BODYBASE.addChild(Spike1);
		BODYBASE.addChild(Spike2);
		BODYBASE.addChild(Spike3);
		BODYBASE.addChild(Spike4);
		BODYBASE.addChild(Spike5);
		BODYBASE.addChild(Spike6);
		BODYBASE.addChild(Spike7);
		BODYBASE.addChild(Spike8);

		HandR = new ModelRenderer(this, "HandR");
		HandR.setRotationPoint(-6.5F, 3.4F, -1F);
		setRotation(HandR, 0F, 0F, 0F);
		HandR.mirror = true;
		ModelRenderer RightFingerM = new ModelRenderer(this, 0, 0);
		RightFingerM.addBox(-0.5F, -0.5F, -3F, 1, 1, 3);
		RightFingerM.setRotationPoint(0F, -0.9F, -0.5F);
		RightFingerM.setTextureSize(64, 64);
		RightFingerM.mirror = true;
		setRotation(RightFingerM, -0.296706F, 0F, 0F);
		ModelRenderer RightFingerR = new ModelRenderer(this, 0, 0);
		RightFingerR.addBox(-0.5F, -0.5F, -3F, 1, 1, 3);
		RightFingerR.setRotationPoint(-1F, -0.9F, -0.5F);
		RightFingerR.setTextureSize(64, 64);
		RightFingerR.mirror = true;
		setRotation(RightFingerR, -0.296706F, 0.2268928F, 0F);
		ModelRenderer RightHand1 = new ModelRenderer(this, 0, 7);
		RightHand1.addBox(-1.5F, 0F, 0F, 3, 2, 1);
		RightHand1.setRotationPoint(0F, -1F, -1.2F);
		RightHand1.setTextureSize(64, 64);
		RightHand1.mirror = true;
		setRotation(RightHand1, 0.6806784F, 0F, 0F);
		ModelRenderer RightFingerL = new ModelRenderer(this, 0, 0);
		RightFingerL.addBox(-0.5F, -0.5F, -3F, 1, 1, 3);
		RightFingerL.setRotationPoint(1F, -0.9F, -0.5F);
		RightFingerL.setTextureSize(64, 64);
		RightFingerL.mirror = true;
		setRotation(RightFingerL, -0.296706F, -0.2268928F, 0F);

		HandR.addChild(RightFingerR);
		HandR.addChild(RightFingerM);
		HandR.addChild(RightFingerL);
		HandR.addChild(RightHand1);
		BODYBASE.addChild(HandR);

		HandL = new ModelRenderer(this, "HandL");
		HandL.setRotationPoint(7.5F, 2.4F, -2.2F);
		setRotation(HandL, 0F, 0F, 0F);
		HandL.mirror = true;
		ModelRenderer LeftHand1 = new ModelRenderer(this, 0, 7);
		LeftHand1.addBox(-1.5F, 0F, 0F, 3, 2, 1);
		LeftHand1.setRotationPoint(0F, 0F, 0F);
		LeftHand1.setTextureSize(64, 64);
		LeftHand1.mirror = true;
		setRotation(LeftHand1, 0.6806784F, 0F, 0F);
		ModelRenderer LeftFingerL = new ModelRenderer(this, 0, 0);
		LeftFingerL.addBox(-0.5F, -0.5F, -3F, 1, 1, 3);
		LeftFingerL.setRotationPoint(1F, -0.1F, 0.7F);
		LeftFingerL.setTextureSize(64, 64);
		LeftFingerL.mirror = true;
		setRotation(LeftFingerL, -0.296706F, -0.2268928F, 0F);
		ModelRenderer LeftFingerM = new ModelRenderer(this, 0, 0);
		LeftFingerM.addBox(-0.5F, -0.5F, -3F, 1, 1, 3);
		LeftFingerM.setRotationPoint(0F, 0F, 0.7F);
		LeftFingerM.setTextureSize(64, 64);
		LeftFingerM.mirror = true;
		setRotation(LeftFingerM, -0.296706F, 0F, 0F);
		ModelRenderer LeftFingerR = new ModelRenderer(this, 0, 0);
		LeftFingerR.addBox(-0.5F, -0.5F, -3F, 1, 1, 3);
		LeftFingerR.setRotationPoint(-1F, 0F, 0.7F);
		LeftFingerR.setTextureSize(64, 64);
		LeftFingerR.mirror = true;
		setRotation(LeftFingerR, -0.296706F, 0.2268928F, 0F);

		HandL.addChild(LeftFingerR);
		HandL.addChild(LeftFingerM);
		HandL.addChild(LeftFingerL);
		HandL.addChild(LeftHand1);
		BODYBASE.addChild(HandL);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		BODYBASE.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		BODYBASE.rotationPointY = MathHelper.cos(.2F * f2) * 1.8F;
		HandR.rotationPointY = MathHelper.cos(.2F * f2 - .5F) * .7F + .7F;
		HandL.rotationPointY = MathHelper.cos(.2F * f2 + .4F) * .7F + .7F;
	}

}
