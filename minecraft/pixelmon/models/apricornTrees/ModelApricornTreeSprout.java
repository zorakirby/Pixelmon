package pixelmon.models.apricornTrees;

import pixelmon.blocks.apricornTrees.TileEntityApricornTree;
import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelApricornTreeSprout extends ModelApricornTreeBase {
	// fields
	ModelRenderer Base;
	ModelRenderer BaseLeft;
	ModelRenderer BaseBack;
	ModelRenderer BaseRight;
	ModelRenderer BaseFront;
	ModelRenderer TopBack;
	ModelRenderer TopFront;
	ModelRenderer Leaf1;
	ModelRenderer BaseTop;
	ModelRenderer Leaf2;

	public ModelApricornTreeSprout() {
		textureWidth = 64;
		textureHeight = 32;

		Base = new ModelRenderer(this, 8, 7);
		Base.addBox(-1F, 0F, -1F, 2, 2, 2);
		Base.setRotationPoint(0F, 22F, 0F);
		Base.setTextureSize(64, 32);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		BaseLeft = new ModelRenderer(this, 0, 0);
		BaseLeft.addBox(0.5F, 0F, -1F, 1, 1, 2);
		BaseLeft.setRotationPoint(0F, 23.5F, 0F);
		BaseLeft.setTextureSize(64, 32);
		BaseLeft.mirror = true;
		setRotation(BaseLeft, 0F, 0F, 0.3490659F);
		BaseBack = new ModelRenderer(this, 0, 0);
		BaseBack.addBox(-1F, 0F, 0.5F, 2, 1, 1);
		BaseBack.setRotationPoint(0F, 23.5F, 0F);
		BaseBack.setTextureSize(64, 32);
		BaseBack.mirror = true;
		setRotation(BaseBack, -0.3490659F, 0F, 0F);
		BaseRight = new ModelRenderer(this, 0, 0);
		BaseRight.addBox(-1.5F, 0F, -1F, 1, 1, 2);
		BaseRight.setRotationPoint(0F, 23.5F, 0F);
		BaseRight.setTextureSize(64, 32);
		BaseRight.mirror = true;
		setRotation(BaseRight, 0F, 0F, -0.3490659F);
		BaseFront = new ModelRenderer(this, 0, 0);
		BaseFront.addBox(-1F, 0F, -1.5F, 2, 1, 1);
		BaseFront.setRotationPoint(0F, 23.5F, 0F);
		BaseFront.setTextureSize(64, 32);
		BaseFront.mirror = true;
		setRotation(BaseFront, 0.3490659F, 0F, 0F);
		TopBack = new ModelRenderer(this, 15, 4);
		TopBack.addBox(-0.5F, 0F, 0.5F, 1, 1, 1);
		TopBack.setRotationPoint(0F, 21.5F, -0.5F);
		TopBack.setTextureSize(64, 32);
		TopBack.mirror = true;
		setRotation(TopBack, -0.3490659F, 0F, 0F);
		TopFront = new ModelRenderer(this, 5, 4);
		TopFront.addBox(-0.5F, 0F, -1.5F, 1, 1, 1);
		TopFront.setRotationPoint(0F, 21.5F, 0.5F);
		TopFront.setTextureSize(64, 32);
		TopFront.mirror = true;
		setRotation(TopFront, 0.3490659F, 0F, 0F);
		Leaf1 = new ModelRenderer(this, 7, 0);
		Leaf1.addBox(0.5F, 0F, -1F, 2, 0, 2);
		Leaf1.setRotationPoint(0F, 21.8F, 0F);
		Leaf1.setTextureSize(64, 32);
		Leaf1.mirror = true;
		setRotation(Leaf1, -0.3490659F, 0F, 0F);
		BaseTop = new ModelRenderer(this, 10, 3);
		BaseTop.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		BaseTop.setRotationPoint(0F, 21.6F, 0F);
		BaseTop.setTextureSize(64, 32);
		BaseTop.mirror = true;
		setRotation(BaseTop, 0F, 0F, 0F);
		Leaf2 = new ModelRenderer(this, 7, 0);
		Leaf2.addBox(-2.5F, 0F, -1.5F, 2, 0, 2);
		Leaf2.setRotationPoint(0F, 21.8F, 0.5F);
		Leaf2.setTextureSize(64, 32);
		Leaf2.mirror = true;
		setRotation(Leaf2, 0F, 0F, 0.3490659F);
	}

	public void renderModel(TileEntityApricornTree entity, float f5) {
		Base.render(f5);
		BaseLeft.render(f5);
		BaseBack.render(f5);
		BaseRight.render(f5);
		BaseFront.render(f5);
		TopBack.render(f5);
		TopFront.render(f5);
		Leaf1.render(f5);
		BaseTop.render(f5);
		Leaf2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}