package pixelmon.client.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelMeowth extends ModelBase {
	// fields
	ModelRenderer Ltoe;
	ModelRenderer RToe;
	ModelRenderer Lfoot;
	ModelRenderer Rfoot;
	ModelRenderer Body;
	ModelRenderer Head;
	ModelRenderer LEar;
	ModelRenderer REar;
	ModelRenderer Coin1;
	ModelRenderer Ltoe1;
	ModelRenderer Ltoe2;
	ModelRenderer RToe1;
	ModelRenderer RToe2;
	ModelRenderer LeftPaw2;
	ModelRenderer LeftA;
	ModelRenderer TailEnd;
	ModelRenderer TailBase;
	ModelRenderer RArm;
	ModelRenderer RightPaw2;
	ModelRenderer RightPaw1;
	ModelRenderer LeftPaw1;
	ModelRenderer Whisker1;
	ModelRenderer Whisker2;
	ModelRenderer Whisker3;
	ModelRenderer Whisker4;
	ModelRenderer Whisker5;
	ModelRenderer Whisker6;
	ModelRenderer HeadPiece;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	ModelRenderer Tail;
	ModelRenderer LeftLeg;
	ModelRenderer RightLeg;

	public ModelMeowth() {
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("HeadPiece.Folder1", 0, 0);
		setTextureOffset("RightArm.Folder2", 0, 0);
		setTextureOffset("LeftArm.Folder3", 0, 0);
		setTextureOffset("Tail.Folder4", 0, 0);
		setTextureOffset("LeftLeg.Folder5", 0, 0);
		setTextureOffset("RightLeg.Folder6", 0, 0);

		Body = new ModelRenderer(this, 13, 11);
		Body.addBox(-2F, 0F, 0F, 4, 4, 2);
		Body.setRotationPoint(0F, 18F, -0.5F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		HeadPiece = new ModelRenderer(this, "HeadPiece");
		HeadPiece.setRotationPoint(0F, 18F, 0.5F);
		setRotation(HeadPiece, 0F, 0F, 0F);
		HeadPiece.mirror = true;
		Head = new ModelRenderer(this, 12, 4);
		Head.addBox(-2F, -3F, -1F, 4, 3, 3);
		Head.setRotationPoint(0F, 0F, -0.5F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Coin1 = new ModelRenderer(this, 18, 0);
		Coin1.addBox(0F, 0F, 0F, 1, 2, 0);
		Coin1.setRotationPoint(-0.5F, -4F, -1.7F);
		Coin1.setTextureSize(64, 32);
		Coin1.mirror = true;
		setRotation(Coin1, 0F, 0F, 0F);
		LEar = new ModelRenderer(this, 21, 0);
		LEar.addBox(0F, 0F, 0F, 2, 2, 1);
		LEar.setRotationPoint(0.8F, -3.8F, -0.5F);
		LEar.setTextureSize(64, 32);
		LEar.mirror = true;
		setRotation(LEar, 0F, 0F, 0F);
		REar = new ModelRenderer(this, 11, 0);
		REar.addBox(-2F, 0F, 0F, 2, 2, 1);
		REar.setRotationPoint(-0.8F, -3.8F, -0.5F);
		REar.setTextureSize(64, 32);
		REar.mirror = true;
		setRotation(REar, 0F, 0F, 0F);
		Whisker1 = new ModelRenderer(this, 0, 3);
		Whisker1.addBox(1F, 0F, 0F, 1, 1, 0);
		Whisker1.setRotationPoint(0.5F, -1.3F, -1.6F);
		Whisker1.setTextureSize(64, 32);
		Whisker1.mirror = true;
		setRotation(Whisker1, 0F, 0F, -0.1396263F);
		Whisker2 = new ModelRenderer(this, 0, 3);
		Whisker2.addBox(1F, 0F, 0F, 1, 1, 0);
		Whisker2.setRotationPoint(1.5F, -1.8F, -1.6F);
		Whisker2.setTextureSize(64, 32);
		Whisker2.mirror = true;
		setRotation(Whisker2, 0F, 0F, 0.2443461F);
		Whisker3 = new ModelRenderer(this, 0, 3);
		Whisker3.addBox(-2F, 0F, 0F, 1, 1, 0);
		Whisker3.setRotationPoint(-0.5F, -1.3F, -1.6F);
		Whisker3.setTextureSize(64, 32);
		Whisker3.mirror = true;
		setRotation(Whisker3, 0F, 0F, 0.1396263F);
		Whisker4 = new ModelRenderer(this, 0, 3);
		Whisker4.addBox(-2F, 0F, 0F, 1, 1, 0);
		Whisker4.setRotationPoint(-1.5F, -1.8F, -1.6F);
		Whisker4.setTextureSize(64, 32);
		Whisker4.mirror = true;
		setRotation(Whisker4, 0F, 0F, -0.2443461F);
		Whisker5 = new ModelRenderer(this, 0, 4);
		Whisker5.addBox(0F, 0F, -0.4F, 2, 1, 0);
		Whisker5.setRotationPoint(-1F, -4.8F, -1.6F);
		Whisker5.setTextureSize(64, 32);
		Whisker5.mirror = true;
		setRotation(Whisker5, 0.3717861F, 0F, 0F);
		Whisker6 = new ModelRenderer(this, 0, 4);
		Whisker6.addBox(0F, 0F, 0F, 2, 1, 0);
		Whisker6.setRotationPoint(-1F, -3.8F, -1.6F);
		Whisker6.setTextureSize(64, 32);
		Whisker6.mirror = true;
		setRotation(Whisker6, 0F, 0F, 0F);

		HeadPiece.addChild(Head);
		HeadPiece.addChild(Coin1);
		HeadPiece.addChild(LEar);
		HeadPiece.addChild(REar);
		HeadPiece.addChild(Whisker1);
		HeadPiece.addChild(Whisker2);
		HeadPiece.addChild(Whisker3);
		HeadPiece.addChild(Whisker4);
		HeadPiece.addChild(Whisker5);
		HeadPiece.addChild(Whisker6);

		RightArm = new ModelRenderer(this, "RightArm");
		RightArm.setRotationPoint(-2F, 18.5F, 0.5F);
		setRotation(RightArm, 0F, 0F, 0F);
		RightArm.mirror = true;
		RArm = new ModelRenderer(this, 8, 12);
		RArm.addBox(-1F, 0F, 0F, 1, 3, 1);
		RArm.setRotationPoint(0F, -0.5F, -0.5F);
		RArm.setTextureSize(64, 32);
		RArm.mirror = true;
		setRotation(RArm, -0.7504916F, 0F, 0F);
		RightPaw2 = new ModelRenderer(this, 8, 17);
		RightPaw2.addBox(-1F, 0F, 0F, 1, 1, 1);
		RightPaw2.setRotationPoint(0.1F, 1F, -1.8F);
		RightPaw2.setTextureSize(64, 32);
		RightPaw2.mirror = true;
		setRotation(RightPaw2, -0.7504916F, -0.0698132F, 0F);
		RightPaw1 = new ModelRenderer(this, 8, 17);
		RightPaw1.addBox(-1F, 0F, 0F, 1, 1, 1);
		RightPaw1.setRotationPoint(-0.1F, 1F, -1.8F);
		RightPaw1.setTextureSize(64, 32);
		RightPaw1.mirror = true;
		setRotation(RightPaw1, -0.7504916F, 0.0698132F, 0F);

		RightArm.addChild(RArm);
		RightArm.addChild(RightPaw1);
		RightArm.addChild(RightPaw2);

		LeftArm = new ModelRenderer(this, "LeftArm");
		LeftArm.setRotationPoint(2F, 18.5F, 0.5F);
		setRotation(LeftArm, 0F, 0F, 0F);
		LeftArm.mirror = true;
		LeftA = new ModelRenderer(this, 26, 12);
		LeftA.addBox(0F, 0F, 0F, 1, 3, 1);
		LeftA.setRotationPoint(0F, -0.5F, -0.5F);
		LeftA.setTextureSize(64, 32);
		LeftA.mirror = true;
		setRotation(LeftA, -0.7504916F, 0F, 0F);
		LeftPaw1 = new ModelRenderer(this, 8, 17);
		LeftPaw1.addBox(-1F, 0F, 0F, 1, 1, 1);
		LeftPaw1.setRotationPoint(0.9F, 1F, -0.8F);
		LeftPaw1.setTextureSize(64, 32);
		LeftPaw1.mirror = true;
		setRotation(LeftPaw1, -0.7504916F, 0.0698132F, 0F);
		LeftPaw2 = new ModelRenderer(this, 8, 17);
		LeftPaw2.addBox(-1F, 0F, 0F, 1, 1, 1);
		LeftPaw2.setRotationPoint(1.1F, 1F, -1.8F);
		LeftPaw2.setTextureSize(64, 32);
		LeftPaw2.mirror = true;
		setRotation(LeftPaw2, -0.7504916F, -0.0698132F, 0F);

		LeftArm.addChild(LeftA);
		LeftArm.addChild(LeftPaw1);
		LeftArm.addChild(LeftPaw2);

		Tail = new ModelRenderer(this, "Tail");
		Tail.setRotationPoint(0F, 21F, 1.5F);
		setRotation(Tail, 0F, 0F, 0F);
		Tail.mirror = true;
		TailBase = new ModelRenderer(this, 27, 7);
		TailBase.addBox(0F, 0F, 0F, 1, 1, 2);
		TailBase.setRotationPoint(-0.5F, -1F, -0.5F);
		TailBase.setTextureSize(64, 32);
		TailBase.mirror = true;
		setRotation(TailBase, -0.2617994F, 0F, 0F);
		TailEnd = new ModelRenderer(this, 30, 2);
		TailEnd.addBox(0F, 0F, 0F, 1, 2, 2);
		TailEnd.setRotationPoint(-0.5F, -1.5F, 0.5F);
		TailEnd.setTextureSize(64, 32);
		TailEnd.mirror = true;
		setRotation(TailEnd, 0.3316126F, 0F, 0F);

		Tail.addChild(TailBase);
		Tail.addChild(TailEnd);

		LeftLeg = new ModelRenderer(this, "LeftLeg");
		LeftLeg.setRotationPoint(1.5F, 22F, 0.5F);
		setRotation(LeftLeg, 0F, 0F, 0F);
		LeftLeg.mirror = true;
		Lfoot = new ModelRenderer(this, 20, 18);
		Lfoot.addBox(0F, 0F, 0F, 1, 1, 1);
		Lfoot.setRotationPoint(-0.5F, 0F, -0.5F);
		Lfoot.setTextureSize(64, 32);
		Lfoot.mirror = true;
		setRotation(Lfoot, 0F, 0F, 0F);
		Ltoe = new ModelRenderer(this, 0, 0);
		Ltoe.addBox(0F, 0F, -2.1F, 1, 1, 2);
		Ltoe.setRotationPoint(-0.5F, 1F, 0.5F);
		Ltoe.setTextureSize(64, 32);
		Ltoe.mirror = true;
		setRotation(Ltoe, 0F, 0F, 0F);
		Ltoe1 = new ModelRenderer(this, 0, 0);
		Ltoe1.addBox(0F, 0F, -1.9F, 1, 1, 2);
		Ltoe1.setRotationPoint(-0.5F, 1F, 0.5F);
		Ltoe1.setTextureSize(64, 32);
		Ltoe1.mirror = true;
		setRotation(Ltoe1, 0F, 0.1745329F, 0F);
		Ltoe2 = new ModelRenderer(this, 0, 0);
		Ltoe2.addBox(0F, 0F, -2F, 1, 1, 2);
		Ltoe2.setRotationPoint(-0.5F, 1F, 0.5F);
		Ltoe2.setTextureSize(64, 32);
		Ltoe2.mirror = true;
		setRotation(Ltoe2, 0F, -0.1745329F, 0F);

		LeftLeg.addChild(Lfoot);
		LeftLeg.addChild(Ltoe);
		LeftLeg.addChild(Ltoe1);
		LeftLeg.addChild(Ltoe2);

		RightLeg = new ModelRenderer(this, "RightLeg");
		RightLeg.setRotationPoint(-1.5F, 22F, 0.5F);
		setRotation(RightLeg, 0F, 0F, 0F);
		RightLeg.mirror = true;
		Rfoot = new ModelRenderer(this, 14, 18);
		Rfoot.addBox(-1F, 0F, 0F, 1, 1, 1);
		Rfoot.setRotationPoint(0.5F, 0F, -0.5F);
		Rfoot.setTextureSize(64, 32);
		Rfoot.mirror = true;
		setRotation(Rfoot, 0F, 0F, 0F);
		RToe = new ModelRenderer(this, 0, 0);
		RToe.addBox(-1F, 0F, -2.1F, 1, 1, 2);
		RToe.setRotationPoint(0.5F, 1F, 0.5F);
		RToe.setTextureSize(64, 32);
		RToe.mirror = true;
		setRotation(RToe, 0F, 0F, 0F);
		RToe1 = new ModelRenderer(this, 0, 0);
		RToe1.addBox(-1F, 0F, -2F, 1, 1, 2);
		RToe1.setRotationPoint(0.5F, 1F, 0.5F);
		RToe1.setTextureSize(64, 32);
		RToe1.mirror = true;
		setRotation(RToe1, 0F, 0.1745329F, 0F);
		RToe2 = new ModelRenderer(this, 0, 0);
		RToe2.addBox(-1F, 0F, -1.9F, 1, 1, 2);
		RToe2.setRotationPoint(0.5F, 1F, 0.5F);
		RToe2.setTextureSize(64, 32);
		RToe2.mirror = true;
		setRotation(RToe2, 0F, -0.1745329F, 0F);

		RightLeg.addChild(Rfoot);
		RightLeg.addChild(RToe);
		RightLeg.addChild(RToe1);
		RightLeg.addChild(RToe2);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		HeadPiece.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		Tail.render(f5);
		LeftLeg.render(f5);
		RightLeg.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		HeadPiece.rotateAngleY = f3 / (180F / (float) Math.PI);
		HeadPiece.rotateAngleX = f4 / (180F / (float) Math.PI);
		RightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		LeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		RightLeg.rotateAngleY = 0.0F;
		LeftLeg.rotateAngleY = 0.0F;
		Tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * .8F * f1;
		RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * .5F * f1;
		LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * .5F * f1;
	}

}
