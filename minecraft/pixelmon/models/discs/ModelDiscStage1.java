package pixelmon.models.discs;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelDiscStage1 extends ModelBase {
	// fields
	ModelRenderer RedTip;
	ModelRenderer RedTop;
	ModelRenderer RedFront;
	ModelRenderer RedBottom;
	ModelRenderer RedRight;
	ModelRenderer RedLeft;
	ModelRenderer RedBack;

	public ModelDiscStage1() {
		textureWidth = 32;
		textureHeight = 32;

		RedTip = new ModelRenderer(this, 18, 0);
		RedTip.addBox(-1F, -1.2F, -1F, 2, 1, 2);
		RedTip.setRotationPoint(6.5F, 13F, 0F);
		RedTip.setTextureSize(32, 32);
		RedTip.mirror = true;
		setRotation(RedTip, 0F, 0F, 0F);
		RedTop = new ModelRenderer(this, 0, 0);
		RedTop.addBox(-1.5F, -0.8F, -1.5F, 3, 1, 3);
		RedTop.setRotationPoint(6.5F, 13F, 0F);
		RedTop.setTextureSize(32, 32);
		RedTop.mirror = true;
		setRotation(RedTop, 0F, 0F, 0F);
		RedFront = new ModelRenderer(this, 12, 0);
		RedFront.addBox(-1F, -0.8F, -1.9F, 2, 1, 1);
		RedFront.setRotationPoint(6.5F, 13F, 0F);
		RedFront.setTextureSize(32, 32);
		RedFront.mirror = true;
		setRotation(RedFront, 0F, 0F, 0F);
		RedBottom = new ModelRenderer(this, 0, 4);
		RedBottom.addBox(-1.5F, -0.8F, -1.5F, 3, 1, 3);
		RedBottom.setRotationPoint(6.5F, 13F, 0F);
		RedBottom.setTextureSize(32, 32);
		RedBottom.mirror = true;
		setRotation(RedBottom, 0F, 0F, 0F);
		RedRight = new ModelRenderer(this, 0, 8);
		RedRight.addBox(-1.9F, -0.8F, -1F, 1, 1, 2);
		RedRight.setRotationPoint(6.5F, 13F, 0F);
		RedRight.setTextureSize(32, 32);
		RedRight.mirror = true;
		setRotation(RedRight, 0F, 0F, 0F);
		RedLeft = new ModelRenderer(this, 6, 8);
		RedLeft.addBox(0.9F, -0.8F, -1F, 1, 1, 2);
		RedLeft.setRotationPoint(6.5F, 13F, 0F);
		RedLeft.setTextureSize(32, 32);
		RedLeft.mirror = true;
		setRotation(RedLeft, 0F, 0F, 0F);
		RedBack = new ModelRenderer(this, 12, 2);
		RedBack.addBox(-1F, -0.8F, 0.9F, 2, 1, 1);
		RedBack.setRotationPoint(6.5F, 13F, 0F);
		RedBack.setTextureSize(32, 32);
		RedBack.mirror = true;
		setRotation(RedBack, 0F, 0F, 0F);
	}

	public void renderModel(float f5) {
		RedTip.render(f5);
		RedTop.render(f5);
		RedFront.render(f5);
		RedBottom.render(f5);
		RedRight.render(f5);
		RedLeft.render(f5);
		RedBack.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
