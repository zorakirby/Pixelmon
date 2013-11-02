package pixelmon.client.models.pokemon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelMagikarp extends ModelBase {
public class ModelMagikarp extends PixelmonModelBase {
	// fields
	ModelRenderer Face;
	ModelRenderer rightwhisker;
	ModelRenderer leftwhisker;
	ModelRenderer Body;
	ModelRenderer BodyFront;
	ModelRenderer BodyBack;
	ModelRenderer rightfin;
	ModelRenderer leftfin;
	ModelRenderer topfin;
	ModelRenderer bottomfin;
	ModelRenderer Rear;
	ModelRenderer Rearfin;
	ModelRenderer BodyBase;
	ModelRenderer FacePiece;
	ModelRenderer BackPiece;

	PixelmonModelRenderer Body;

	public ModelMagikarp() {
		textureWidth = 64;
		textureHeight = 32;


		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 22, 0);
		Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/magikarp/Body.obj")));

		BodyBase = new ModelRenderer(this, "BodyBase");
		BodyBase.setRotationPoint(0F, 17.5F, -0.5F);
		setRotation(BodyBase, 0F, 0F, 0F);
		BodyBase.mirror = true;
		Body = new ModelRenderer(this, 34, 18);
		Body.addBox(-1.5F, -3F, -2F, 3, 7, 3);
		Body.setRotationPoint(0F, -0.5F, 0.5F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		topfin = new ModelRenderer(this, 32, -5);
		topfin.addBox(0F, -6F, -2.5F, 0, 3, 5);
		topfin.setRotationPoint(0F, -0.5F, 0F);
		topfin.setTextureSize(64, 32);
		topfin.mirror = true;
		setRotation(topfin, 0F, 0F, 0F);
		bottomfin = new ModelRenderer(this, 32, -2);
		bottomfin.addBox(0F, 4F, -2.5F, 0, 3, 5);
		bottomfin.setRotationPoint(0F, -0.5F, 0F);
		bottomfin.setTextureSize(64, 32);
		bottomfin.mirror = true;
		setRotation(bottomfin, 0F, 0F, 0F);

		BodyBase.addChild(Body);
		BodyBase.addChild(topfin);
		BodyBase.addChild(bottomfin);

		FacePiece = new ModelRenderer(this, "FacePiece");
		FacePiece.setRotationPoint(0F, 0F, -1.5F);
		setRotation(FacePiece, 0F, 0F, 0F);
		FacePiece.mirror = true;
		BodyFront = new ModelRenderer(this, 22, 18);
		BodyFront.addBox(-1.5F, -3F, -2F, 3, 7, 3);
		BodyFront.setRotationPoint(0F, -0.5F, -1F);
		BodyFront.setTextureSize(64, 32);
		BodyFront.mirror = true;
		setRotation(BodyFront, 0F, 0F, 0F);
		Face = new ModelRenderer(this, 0, 15);
		Face.addBox(-1F, -2F, -3F, 2, 5, 1);
		Face.setRotationPoint(0F, -0.5F, -1F);
		Face.setTextureSize(64, 32);
		Face.mirror = true;
		setRotation(Face, 0F, 0F, 0F);
		rightwhisker = new ModelRenderer(this, 0, 22);
		rightwhisker.addBox(-4.5F, 2F, -2.5F, 4, 5, 0);
		rightwhisker.setRotationPoint(0F, -0.5F, -1F);
		rightwhisker.setTextureSize(64, 32);
		rightwhisker.mirror = true;
		setRotation(rightwhisker, 0F, 0.2094395F, 0F);
		leftwhisker = new ModelRenderer(this, 9, 22);
		leftwhisker.addBox(0.5F, 2F, -2.5F, 4, 5, 0);
		leftwhisker.setRotationPoint(0F, -0.5F, -1F);
		leftwhisker.setTextureSize(64, 32);
		leftwhisker.mirror = true;
		setRotation(leftwhisker, 0F, -0.2094395F, 0F);
		leftfin = new ModelRenderer(this, 22, 3);
		leftfin.addBox(0F, -3F, 0F, 5, 3, 0);
		leftfin.setRotationPoint(-1.5F, 2.5F, -2F);
		leftfin.setTextureSize(64, 32);
		leftfin.mirror = true;
		setRotation(leftfin, -0.1919862F, -2.356194F, 0.2268928F);
		rightfin = new ModelRenderer(this, 22, 0);
		rightfin.addBox(0F, -3F, 0F, 5, 3, 0);
		rightfin.setRotationPoint(1.5F, 2.5F, -2F);
		rightfin.setTextureSize(64, 32);
		rightfin.mirror = true;
		setRotation(rightfin, 0.1919862F, -0.7853982F, 0.2268928F);

		FacePiece.addChild(BodyFront);
		FacePiece.addChild(Face);
		FacePiece.addChild(rightwhisker);
		FacePiece.addChild(leftwhisker);
		FacePiece.addChild(rightfin);
		FacePiece.addChild(leftfin);
		BodyBase.addChild(FacePiece);

		BackPiece = new ModelRenderer(this, "BackPiece");
		BackPiece.setRotationPoint(0F, 0F, 1.5F);
		setRotation(BackPiece, 0F, 0F, 0F);
		BackPiece.mirror = true;
		BodyBack = new ModelRenderer(this, 46, 19);
		BodyBack.addBox(-1.5F, -3F, -2F, 3, 7, 2);
		BodyBack.setRotationPoint(0F, -0.5F, 2F);
		BodyBack.setTextureSize(64, 32);
		BodyBack.mirror = true;
		setRotation(BodyBack, 0F, 0F, 0F);
		Rear = new ModelRenderer(this, 6, 15);
		Rear.addBox(-1F, -1.5F, 0F, 2, 4, 1);
		Rear.setRotationPoint(0F, -0.5F, 2F);
		Rear.setTextureSize(64, 32);
		Rear.mirror = true;
		setRotation(Rear, 0F, 0F, 0F);
		Rearfin = new ModelRenderer(this, 22, 1);
		Rearfin.addBox(0F, -4F, 0F, 0, 8, 5);
		Rearfin.setRotationPoint(0F, 0F, 3F);
		Rearfin.setTextureSize(64, 32);
		Rearfin.mirror = true;
		setRotation(Rearfin, 0F, 0F, 0F);

		BackPiece.addChild(BodyBack);
		BackPiece.addChild(Rear);
		BackPiece.addChild(Rearfin);
		BodyBase.addChild(BackPiece);
		skeleton = new SkeletonBase(Body);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		if (entity.isInWater())
			setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		else
			setRotationAnglesOutOfWater(f, f1, f2, f3, f4, f5);
		BodyBase.render(f5);
		Body.render(f5);
	}

	private void setRotationAnglesOutOfWater(float f, float f1, float f2, float f3, float f4, float f5) {
		BodyBase.rotateAngleZ = 1.5F;
		BodyBase.rotationPointY = 22F;
		Rearfin.rotateAngleY = 0F;
		Body.rotateAngleZ = 1.5F;
		Body.rotationPointY = 22F;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
	private void setRotation(PixelmonModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		Rearfin.rotateAngleY = MathHelper.cos(f * 0.785398163F) * 2F * f1;
		BodyBase.rotateAngleZ = 0F;
		BodyBase.rotationPointY = 17.5F;
		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);
		setRotation(Body, radians, 0, 0);
		Body.rotateAngleZ = 0F;
		Body.rotationPointY = 17.5F;
	}

}
