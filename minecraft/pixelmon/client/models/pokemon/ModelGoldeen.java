package pixelmon.client.models.pokemon;

import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.fish.ModuleFin;
import pixelmon.client.models.animations.fish.ModuleTailFish;
import pixelmon.client.models.animations.fish.SkeletonFish;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelGoldeen extends PixelmonModelBase {
	// fields
	PixelmonModelRenderer Body;
	
	public ModelGoldeen() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0F, 18F, 3F);
		setRotation(Body, 0F, 0F, 0F);
		Body.mirror = true;

		PixelmonModelRenderer body = new PixelmonModelRenderer(this, 0, 11);
		body.addBox(-4F, 0F, -3F, 9, 6, 11);
		body.setRotationPoint(-0.5F, -3F, -2.5F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		PixelmonModelRenderer Lips = new PixelmonModelRenderer(this, 58, 10);
		Lips.addBox(0F, 0F, 0F, 4, 1, 1);
		Lips.setRotationPoint(-2F, 1F, -7F);
		Lips.setTextureSize(64, 64);
		Lips.mirror = true;
		setRotation(Lips, 0F, 0F, 0F);
		PixelmonModelRenderer Tail_fin = new PixelmonModelRenderer(this, -15,
				30);
		Tail_fin.addBox(-10.5F, 0F, 0F, 22, 0, 15);
		Tail_fin.setRotationPoint(0F, 0F, 6.5F);
		Tail_fin.setTextureSize(64, 64);
		Tail_fin.mirror = true;
		setRotation(Tail_fin, 0.1858931F, 0F, 0F);
		PixelmonModelRenderer Right_fin = new PixelmonModelRenderer(this, 43,
				58);
		Right_fin.addBox(-7F, 0F, -3F, 7, 0, 6);
		Right_fin.setRotationPoint(-4F, 1F, 0F);
		Right_fin.setTextureSize(64, 64);
		Right_fin.mirror = true;
		setRotation(Right_fin, 0.2230717F, 0F, -0.1858931F);
		PixelmonModelRenderer Left_fin = new PixelmonModelRenderer(this, 43, 58);
		Left_fin.addBox(0F, 0F, -3F, 7, 0, 6);
		Left_fin.setRotationPoint(4F, 1F, 0F);
		Left_fin.setTextureSize(64, 64);
		Left_fin.mirror = true;
		setRotation(Left_fin, 0.2974289F, 0F, 0.2230717F);
		PixelmonModelRenderer Top_Fin = new PixelmonModelRenderer(this, 0, -4);
		Top_Fin.addBox(0F, -1F, -3F, 0, 2, 5);
		Top_Fin.setRotationPoint(0F, -1.5F, -2F);
		Top_Fin.setTextureSize(64, 64);
		Top_Fin.mirror = true;
		setRotation(Top_Fin, 0F, 0F, 3.141593F);
		PixelmonModelRenderer Body1 = new PixelmonModelRenderer(this, 0, 45);
		Body1.addBox(-4F, 0F, -3F, 7, 8, 11);
		Body1.setRotationPoint(0.5F, -4F, -2.5F);
		Body1.setTextureSize(64, 64);
		Body1.mirror = true;
		setRotation(Body1, 0F, 0F, 0F);
		PixelmonModelRenderer Body2 = new PixelmonModelRenderer(this, 0, 11);
		Body2.addBox(-4F, 0F, -3F, 7, 6, 13);
		Body2.setRotationPoint(0.5F, -3F, -2.5F);
		Body2.setTextureSize(64, 64);
		Body2.mirror = true;
		setRotation(Body2, 0F, 0F, 0F);
		PixelmonModelRenderer Body3 = new PixelmonModelRenderer(this, 0, 11);
		Body3.addBox(-4F, 0F, -3F, 8, 7, 12);
		Body3.setRotationPoint(0F, -3.5F, -3F);
		Body3.setTextureSize(64, 64);
		Body3.mirror = true;
		setRotation(Body3, 0F, 0F, 0F);
		PixelmonModelRenderer Shape2 = new PixelmonModelRenderer(this, 44, 43);
		Shape2.addBox(0F, -1.5F, -5F, 0, 2, 10);
		Shape2.setRotationPoint(0F, -4F, 0.5F);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 3.141593F, 0F);
		PixelmonModelRenderer Lips1 = new PixelmonModelRenderer(this, 60, 9);
		Lips1.addBox(0F, 0F, 0F, 1, 2, 1);
		Lips1.setRotationPoint(1F, -0.2F, -7F);
		Lips1.setTextureSize(64, 64);
		Lips1.mirror = true;
		setRotation(Lips1, 0F, 0F, 0F);
		PixelmonModelRenderer Black = new PixelmonModelRenderer(this, 48, 9);
		Black.addBox(0F, 0F, 0F, 3, 1, 1);
		Black.setRotationPoint(-1.5F, 0.8F, -6.9F);
		Black.setTextureSize(64, 64);
		Black.mirror = true;
		setRotation(Black, 0F, 0F, 0F);
		PixelmonModelRenderer Lips2 = new PixelmonModelRenderer(this, 60, 9);
		Lips2.addBox(0F, 0F, 0F, 1, 2, 1);
		Lips2.setRotationPoint(-2F, -0.2F, -7F);
		Lips2.setTextureSize(64, 64);
		Lips2.mirror = true;
		setRotation(Lips2, 0F, 0F, 0F);
		PixelmonModelRenderer Lips3 = new PixelmonModelRenderer(this, 57, 9);
		Lips3.addBox(0F, 0F, 0F, 4, 1, 1);
		Lips3.setRotationPoint(-2F, -0.2F, -7F);
		Lips3.setTextureSize(64, 64);
		Lips3.mirror = true;
		setRotation(Lips3, 0F, 0F, 0F);
		PixelmonModelRenderer Eyelid = new PixelmonModelRenderer(this, 25, 0);
		Eyelid.addBox(-1F, 0F, -1F, 1, 1, 2);
		Eyelid.setRotationPoint(-3.8F, -1.5F, -3.5F);
		Eyelid.setTextureSize(64, 64);
		Eyelid.mirror = true;
		setRotation(Eyelid, 0F, 0F, 0.1495747F);
		PixelmonModelRenderer Eyelid1 = new PixelmonModelRenderer(this, 25, 0);
		Eyelid1.addBox(0F, 0F, -1F, 1, 1, 2);
		Eyelid1.setRotationPoint(3.8F, -1.5F, -3.5F);
		Eyelid1.setTextureSize(64, 64);
		Eyelid1.mirror = true;
		setRotation(Eyelid1, 0F, 0F, -0.1869996F);
		PixelmonModelRenderer black = new PixelmonModelRenderer(this, 50, 9);
		black.addBox(0F, 0F, 0F, 1, 1, 1);
		black.setRotationPoint(3.8F, -0.8F, -4F);
		black.setTextureSize(64, 64);
		black.mirror = true;
		setRotation(black, 0F, 0F, 0F);
		PixelmonModelRenderer Black1 = new PixelmonModelRenderer(this, 51, 9);
		Black1.addBox(0F, 0F, 0F, 1, 1, 1);
		Black1.setRotationPoint(-4.8F, -0.8F, -4F);
		Black1.setTextureSize(64, 64);
		Black1.mirror = true;
		setRotation(Black1, 0F, 0F, 0F);
		PixelmonModelRenderer White = new PixelmonModelRenderer(this, 39, 9);
		White.addBox(0F, 0F, 0F, 1, 1, 2);
		White.setRotationPoint(-4.7F, -0.7F, -4.5F);
		White.setTextureSize(64, 64);
		White.mirror = true;
		setRotation(White, 3.141593F, 3.141593F, 3.141593F);
		PixelmonModelRenderer Black2 = new PixelmonModelRenderer(this, 49, 9);
		Black2.addBox(0F, 0F, 0F, 1, 1, 2);
		Black2.setRotationPoint(-4.55F, -0.7F, -4.47F);
		Black2.setTextureSize(64, 64);
		Black2.mirror = true;
		setRotation(Black2, 3.141593F, 3.141593F, 3.141593F);
		PixelmonModelRenderer Black3 = new PixelmonModelRenderer(this, 49, 9);
		Black3.addBox(0F, 0F, 0F, 1, 1, 2);
		Black3.setRotationPoint(-4.55F, -0.7F, -4.55F);
		Black3.setTextureSize(64, 64);
		Black3.mirror = true;
		setRotation(Black3, 3.141593F, 3.141593F, 3.141593F);
		PixelmonModelRenderer Black4 = new PixelmonModelRenderer(this, 40, 9);
		Black4.addBox(0F, 0F, 0F, 1, 1, 2);
		Black4.setRotationPoint(3.7F, -0.7F, -4.5F);
		Black4.setTextureSize(64, 64);
		Black4.mirror = true;
		setRotation(Black4, 3.141593F, 3.141593F, 3.141593F);
		PixelmonModelRenderer White2 = new PixelmonModelRenderer(this, 49, 9);
		White2.addBox(0F, 0F, 0F, 1, 1, 2);
		White2.setRotationPoint(-4.51F, -0.75F, -4.5F);
		White2.setTextureSize(64, 64);
		White2.mirror = true;
		setRotation(White2, 3.141593F, 3.141593F, 3.141593F);
		PixelmonModelRenderer Black5 = new PixelmonModelRenderer(this, 49, 9);
		Black5.addBox(0F, 0F, 0F, 1, 1, 2);
		Black5.setRotationPoint(3.61F, -0.7F, -4.47F);
		Black5.setTextureSize(64, 64);
		Black5.mirror = true;
		setRotation(Black5, 3.141593F, 3.141593F, 3.141593F);
		PixelmonModelRenderer Black6 = new PixelmonModelRenderer(this, 49, 9);
		Black6.addBox(0F, 0F, 0F, 1, 1, 2);
		Black6.setRotationPoint(3.61F, -0.7F, -4.55F);
		Black6.setTextureSize(64, 64);
		Black6.mirror = true;
		setRotation(Black6, 3.141593F, 3.141593F, 3.141593F);
		PixelmonModelRenderer White3 = new PixelmonModelRenderer(this, 49, 9);
		White3.addBox(0F, 0F, 0F, 1, 1, 2);
		White3.setRotationPoint(3.65F, -0.75F, -4.5F);
		White3.setTextureSize(64, 64);
		White3.mirror = true;
		setRotation(White3, 3.141593F, 3.141593F, 3.141593F);
		PixelmonModelRenderer Horn = new PixelmonModelRenderer(this, 36, 0);
		Horn.addBox(-1F, -3F, -1F, 2, 5, 2);
		Horn.setRotationPoint(0F, -3F, -5.5F);
		Horn.setTextureSize(64, 64);
		Horn.mirror = true;
		setRotation(Horn, 0.4136121F, 0.7482196F, 0.4136121F);
		PixelmonModelRenderer Horn1 = new PixelmonModelRenderer(this, 36, 0);
		Horn1.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
		Horn1.setRotationPoint(0F, -5F, -6.7F);
		Horn1.setTextureSize(64, 64);
		Horn1.mirror = true;
		setRotation(Horn1, 0.4136121F, 0.7482196F, 0.4136121F);

		Body.addChild(body);
		Body.addChild(Lips);
		Body.addChild(Tail_fin);
		Body.addChild(Right_fin);
		Body.addChild(Left_fin);
		Body.addChild(Top_Fin);
		Body.addChild(Body1);
		Body.addChild(Body2);
		Body.addChild(Body3);
		Body.addChild(Shape2);
		Body.addChild(Lips1);
		Body.addChild(Black);
		Body.addChild(Lips2);
		Body.addChild(Lips3);
		Body.addChild(Eyelid);
		Body.addChild(Eyelid1);
		Body.addChild(black);
		Body.addChild(Black1);
		Body.addChild(White);
		Body.addChild(Black2);
		Body.addChild(Black3);
		Body.addChild(Black4);
		Body.addChild(White2);
		Body.addChild(Black5);
		Body.addChild(Black6);
		Body.addChild(White3);
		Body.addChild(Horn);
		Body.addChild(Horn1);

		ModuleFin LeftFinModule = new ModuleFin(Left_fin, EnumArm.Left, 90,
				(float) Math.PI / 4, 0.23F);
		ModuleFin RightFinModule = new ModuleFin(Right_fin, EnumArm.Right, 90,
				(float) Math.PI / 4, 0.23F);
		ModuleTailFish tailModule = new ModuleTailFish(Tail_fin, 90,(float) Math.PI/11,
				0.3F);

		skeleton = new SkeletonFish(Body, null, LeftFinModule, RightFinModule, null, null,
				tailModule);
	}
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}