package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelPikachu extends ModelBase {
	// fields
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	ModelRenderer Back;
	ModelRenderer LeftSide;
	ModelRenderer RightSide;
	ModelRenderer Front;
	ModelRenderer RightFoot;
	ModelRenderer LeftFoot;
	ModelRenderer Tail;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer Tail3;
	ModelRenderer Tail4;
	ModelRenderer Tail5;
	ModelRenderer HeadBase;
	ModelRenderer Head;
	ModelRenderer RightCheek;
	ModelRenderer LeftCheek;
	ModelRenderer RightEar;
	ModelRenderer RightEarTip;
	ModelRenderer LeftEar;
	ModelRenderer LeftEarTip;

	public ModelPikachu() {
		textureWidth = 128;
		textureHeight = 64;

		RightArm = new ModelRenderer(this, 0, 54);
		RightArm.addBox(0F, 0F, -7F, 3, 3, 7);
		RightArm.setRotationPoint(-6.7F, 5.4F, -3F);
		RightArm.setTextureSize(128, 64);
		RightArm.mirror = true;
		setRotation(RightArm, 0.5585054F, -0.3665191F, 0.0349066F);
		LeftArm = new ModelRenderer(this, 0, 54);
		LeftArm.addBox(-3F, 0F, -7F, 3, 3, 7);
		LeftArm.setRotationPoint(7.55F, 5.4F, -3F);
		LeftArm.setTextureSize(128, 64);
		LeftArm.mirror = true;
		setRotation(LeftArm, 0.6981317F, 0.3665191F, 0.0174533F);
		Back = new ModelRenderer(this, 59, 46);
		Back.addBox(-5.5F, -0.06666667F, -2F, 11, 16, 2);
		Back.setRotationPoint(0.4F, 5.4F, 3.7F);
		Back.setTextureSize(128, 64);
		Back.mirror = true;
		setRotation(Back, 0.0698132F, 0F, 0F);
		LeftSide = new ModelRenderer(this, 0, 39);
		LeftSide.addBox(-5.2F, 0F, -2F, 11, 17, 8);
		LeftSide.setRotationPoint(0.7333333F, 5.4F, -2.5F);
		LeftSide.setTextureSize(128, 64);
		LeftSide.mirror = true;
		setRotation(LeftSide, 0F, 0F, -0.0698132F);
		RightSide = new ModelRenderer(this, 0, 39);
		RightSide.addBox(-5.2F, 0F, -2F, 11, 17, 8);
		RightSide.setRotationPoint(-0.7F, 5.37F, -2.5F);
		RightSide.setTextureSize(128, 64);
		RightSide.mirror = true;
		setRotation(RightSide, 0F, 0F, 0.0698132F);
		Front = new ModelRenderer(this, 31, 13);
		Front.addBox(-4.5F, 1F, -3F, 11, 15, 1);
		Front.setRotationPoint(-0.7F, 5.4F, -1.5F);
		Front.setTextureSize(128, 64);
		Front.mirror = true;
		setRotation(Front, -0.0523599F, 0F, 0F);
		RightFoot = new ModelRenderer(this, 105, 2);
		RightFoot.addBox(-2F, 0F, -5F, 3, 2, 8);
		RightFoot.setRotationPoint(-4.5F, 22F, -1.166667F);
		RightFoot.setTextureSize(128, 64);
		RightFoot.mirror = true;
		setRotation(RightFoot, 0F, 0.1745329F, 0F);
		LeftFoot = new ModelRenderer(this, 105, 2);
		LeftFoot.addBox(-1F, 0F, -5F, 3, 2, 8);
		LeftFoot.setRotationPoint(5.5F, 22F, -1.2F);
		LeftFoot.setTextureSize(128, 64);
		LeftFoot.mirror = true;
		setRotation(LeftFoot, 0F, -0.2094395F, 0F);
		Tail = new ModelRenderer(this, "Tail");
		Tail.setRotationPoint(0.5F, 18.4F, 3.7F);
		setRotation(Tail, 0F, 0F, 0F);
		Tail.mirror = true;
		Tail1 = new ModelRenderer(this, 94, 54);
		Tail1.addBox(-0.5F, 0F, 0F, 1, 2, 5);
		Tail1.setRotationPoint(0F, 0F, 0F);
		Tail1.setTextureSize(128, 64);
		Tail1.mirror = true;
		setRotation(Tail1, 0.296706F, 0F, 0F);
		Tail2 = new ModelRenderer(this, 94, 46);
		Tail2.addBox(-0.5F, -3.1F, -0.2F, 1, 6, 2);
		Tail2.setRotationPoint(0F, -2F, 3.6F);
		Tail2.setTextureSize(128, 64);
		Tail2.mirror = true;
		setRotation(Tail2, 0.296706F, 0F, 0F);
		Tail3 = new ModelRenderer(this, 101, 39);
		Tail3.addBox(-0.5F, 0.1F, -1.866667F, 1, 3, 7);
		Tail3.setRotationPoint(0F, -5.6F, 4.3F);
		Tail3.setTextureSize(128, 64);
		Tail3.mirror = true;
		setRotation(Tail3, 0.296706F, 0F, 0F);
		Tail4 = new ModelRenderer(this, 97, 9);
		Tail4.addBox(-0.5F, -1F, 1.85F, 1, 4, 3);
		Tail4.setRotationPoint(0F, -6.6F, 4.3F);
		Tail4.setTextureSize(128, 64);
		Tail4.mirror = true;
		setRotation(Tail4, 0.296706F, 0F, 0F);
		Tail5 = new ModelRenderer(this, 0, 53);
		Tail5.addBox(-0.5F, 0.3333333F, -0.56F, 1, 4, 7);
		Tail5.setRotationPoint(0F, -11.6F, 5.3F);
		Tail5.setTextureSize(128, 64);
		Tail5.mirror = true;
		setRotation(Tail5, 0.296706F, 0F, 0F);

		Tail.addChild(Tail1);
		Tail.addChild(Tail2);
		Tail.addChild(Tail3);
		Tail.addChild(Tail4);
		Tail.addChild(Tail5);

		HeadBase = new ModelRenderer(this, "HeadBase");
		HeadBase.setRotationPoint(0F, 5.5F, -0.6F);
		setRotation(HeadBase, 0F, 0F, 0F);
		HeadBase.mirror = true;
		Head = new ModelRenderer(this, 63, 19);
		Head.addBox(-6.7F, -11F, -5F, 14, 11, 10);
		Head.setRotationPoint(0.03333334F, 0F, 0F);
		Head.setTextureSize(128, 64);
		Head.mirror = true;
		setRotation(Head, -0.0174533F, 0F, 0F);
		RightCheek = new ModelRenderer(this, 50, 37);
		RightCheek.addBox(0F, 0F, 0F, 2, 2, 1);
		RightCheek.setRotationPoint(-5.67F, -3.1F, -5.2F);
		RightCheek.setTextureSize(128, 64);
		RightCheek.mirror = true;
		setRotation(RightCheek, 0F, 0F, 0F);
		LeftCheek = new ModelRenderer(this, 50, 37);
		LeftCheek.addBox(0F, 0F, 0F, 2, 2, 1);
		LeftCheek.setRotationPoint(4.33F, -3.1F, -5.2F);
		LeftCheek.setTextureSize(128, 64);
		LeftCheek.mirror = true;
		setRotation(LeftCheek, 0F, 0F, 0F);
		RightEar = new ModelRenderer(this, 65, 0);
		RightEar.addBox(-3F, -10F, 0F, 3, 10, 3);
		RightEar.setRotationPoint(-3F, -9.5F, 1F);
		RightEar.setTextureSize(128, 64);
		RightEar.mirror = true;
		setRotation(RightEar, 0F, 0F, -0.5585054F);
		RightEarTip = new ModelRenderer(this, 84, 7);
		RightEarTip.addBox(0.45F, 1F, 0.8F, 2, 2, 2);
		RightEarTip.setRotationPoint(-12F, -18F, 0.4F);
		RightEarTip.setTextureSize(128, 64);
		RightEarTip.mirror = true;
		setRotation(RightEarTip, 0F, 0F, -0.5585054F);
		LeftEar = new ModelRenderer(this, 74, 0);
		LeftEar.addBox(0F, -3F, 0F, 10, 3, 3);
		LeftEar.setRotationPoint(5F, -8.1F, 0.1F);
		LeftEar.setTextureSize(128, 64);
		LeftEar.mirror = true;
		setRotation(LeftEar, 0F, 0.0174533F, -0.2617994F);
		LeftEarTip = new ModelRenderer(this, 84, 7);
		LeftEarTip.addBox(-1.4F, 0.1F, 1.7F, 2, 2, 2);
		LeftEarTip.setRotationPoint(15F, -13F, -1.4F);
		LeftEarTip.setTextureSize(128, 64);
		LeftEarTip.mirror = true;
		setRotation(LeftEarTip, 0F, 0F, -0.2617994F);

		HeadBase.addChild(Head);
		HeadBase.addChild(RightCheek);
		HeadBase.addChild(LeftCheek);
		HeadBase.addChild(RightEar);
		HeadBase.addChild(RightEarTip);
		HeadBase.addChild(LeftEar);
		HeadBase.addChild(LeftEarTip);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		RightArm.render(f5);
		LeftArm.render(f5);
		Back.render(f5);
		LeftSide.render(f5);
		RightSide.render(f5);
		Front.render(f5);
		RightFoot.render(f5);
		LeftFoot.render(f5);
		Tail.render(f5);
		HeadBase.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		HeadBase.rotateAngleY = f3 / (180F / (float) Math.PI);
		HeadBase.rotateAngleX = f4 / (180F / (float) Math.PI);
		RightFoot.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		LeftFoot.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		RightFoot.rotateAngleY = 0.0F;
		LeftFoot.rotateAngleY = 0.0F;
		Tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * .8F * f1;
	}

}
