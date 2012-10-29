package pixelmon.models.apricornTrees;

import pixelmon.blocks.apricornTrees.TileEntityApricornTree;
import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelApricornTreeStage1 extends ModelApricornTreeBase {
	// fields
	ModelRenderer Base;
	ModelRenderer RootFront;
	ModelRenderer RootBack;
	ModelRenderer RootRight;
	ModelRenderer RootLeft;
	ModelRenderer BushMiddle;
	ModelRenderer BushInner;
	ModelRenderer BushInner2;
	ModelRenderer BushOuter2;
	ModelRenderer BushOuter;

	public ModelApricornTreeStage1() {
		textureWidth = 256;
		textureHeight = 128;

		Base = new ModelRenderer(this, 66, 34);
		Base.addBox(-1F, 0F, -1F, 2, 3, 2);
		Base.setRotationPoint(0F, 21F, 0F);
		Base.setTextureSize(256, 128);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		RootFront = new ModelRenderer(this, 64, 0);
		RootFront.addBox(-1F, 0F, -2F, 2, 2, 2);
		RootFront.setRotationPoint(0F, 23F, -1F);
		RootFront.setTextureSize(256, 128);
		RootFront.mirror = true;
		setRotation(RootFront, 0.5235988F, 0F, 0F);
		RootBack = new ModelRenderer(this, 64, 0);
		RootBack.addBox(-1F, 0F, 0F, 2, 2, 2);
		RootBack.setRotationPoint(0F, 23F, 1F);
		RootBack.setTextureSize(256, 128);
		RootBack.mirror = true;
		setRotation(RootBack, -0.5235988F, 0F, 0F);
		RootRight = new ModelRenderer(this, 64, 0);
		RootRight.addBox(-2F, 0F, -1F, 2, 2, 2);
		RootRight.setRotationPoint(-1F, 23F, 0F);
		RootRight.setTextureSize(256, 128);
		RootRight.mirror = true;
		setRotation(RootRight, 0F, 0F, -0.5235988F);
		RootLeft = new ModelRenderer(this, 64, 0);
		RootLeft.addBox(0F, 0F, -1F, 2, 2, 2);
		RootLeft.setRotationPoint(1F, 23F, 0F);
		RootLeft.setTextureSize(256, 128);
		RootLeft.mirror = true;
		setRotation(RootLeft, 0F, 0F, 0.5235988F);
		BushMiddle = new ModelRenderer(this, 0, 0);
		BushMiddle.addBox(-1.5F, 0F, -1.5F, 3, 9, 3);
		BushMiddle.setRotationPoint(0F, 12.5F, 0F);
		BushMiddle.setTextureSize(256, 128);
		BushMiddle.mirror = true;
		setRotation(BushMiddle, 0F, 0F, 0F);
		BushInner = new ModelRenderer(this, 0, 0);
		BushInner.addBox(-2F, 0F, -2F, 4, 8, 4);
		BushInner.setRotationPoint(0F, 13F, 0F);
		BushInner.setTextureSize(256, 128);
		BushInner.mirror = true;
		setRotation(BushInner, 0F, 0F, 0F);
		BushInner2 = new ModelRenderer(this, 0, 0);
		BushInner2.addBox(-3F, 0F, -3F, 6, 7, 6);
		BushInner2.setRotationPoint(0F, 13.5F, 0F);
		BushInner2.setTextureSize(256, 128);
		BushInner2.mirror = true;
		setRotation(BushInner2, 0F, 0F, 0F);
		BushOuter2 = new ModelRenderer(this, 0, 0);
		BushOuter2.addBox(-3.5F, 0F, -3.5F, 7, 6, 7);
		BushOuter2.setRotationPoint(0F, 14F, 0F);
		BushOuter2.setTextureSize(256, 128);
		BushOuter2.mirror = true;
		setRotation(BushOuter2, 0F, 0F, 0F);
		BushOuter = new ModelRenderer(this, 0, 0);
		BushOuter.addBox(-4F, 0F, -4F, 8, 4, 8);
		BushOuter.setRotationPoint(0F, 15F, 0F);
		BushOuter.setTextureSize(256, 128);
		BushOuter.mirror = true;
		setRotation(BushOuter, 0F, 0F, 0F);
	}

	public void renderModel(TileEntityApricornTree entity, float f5) {
		Base.render(f5);
		RootFront.render(f5);
		RootBack.render(f5);
		RootRight.render(f5);
		RootLeft.render(f5);
		BushMiddle.render(f5);
		BushInner.render(f5);
		BushInner2.render(f5);
		BushOuter2.render(f5);
		BushOuter.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
