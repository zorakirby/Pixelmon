package pixelmon.client.models.plates;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPlateStage2 extends ModelBase {
	// fields
	ModelRenderer Ingot1;
	ModelRenderer Ingot2;
	ModelRenderer Ingot3;
	ModelRenderer Ingot4;

	public ModelPlateStage2() {
		textureWidth = 32;
		textureHeight = 32;

		Ingot1 = new ModelRenderer(this, 0, 0);
		Ingot1.addBox(-13.9F, 0.7F, -1.5F, 5, 1, 3);
		Ingot1.setRotationPoint(6.5F, 12F, 0F);
		Ingot1.setTextureSize(32, 32);
		Ingot1.mirror = true;
		setRotation(Ingot1, 0F, 0F, 0F);
		Ingot2 = new ModelRenderer(this, 0, 9);
		Ingot2.addBox(-9.4F, 0F, -2F, 1, 1, 4);
		Ingot2.setRotationPoint(6.5F, 13F, 0F);
		Ingot2.setTextureSize(32, 32);
		Ingot2.mirror = true;
		setRotation(Ingot2, 0F, 0F, 0F);
		Ingot3 = new ModelRenderer(this, 0, 4);
		Ingot3.addBox(-13.9F, 0F, -2.5F, 5, 1, 5);
		Ingot3.setRotationPoint(6.5F, 13F, 0F);
		Ingot3.setTextureSize(32, 32);
		Ingot3.mirror = true;
		setRotation(Ingot3, 0F, 0F, 0F);
		Ingot4 = new ModelRenderer(this, 0, 9);
		Ingot4.addBox(-14.4F, 0F, -2F, 1, 1, 4);
		Ingot4.setRotationPoint(6.5F, 13F, 0F);
		Ingot4.setTextureSize(32, 32);
		Ingot4.mirror = true;
		setRotation(Ingot4, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Ingot1.render(f5);
		Ingot2.render(f5);
		Ingot3.render(f5);
		Ingot4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderModel(float f5) {
		Ingot1.render(f5);
		Ingot2.render(f5);
		Ingot3.render(f5);
		Ingot4.render(f5);
	}
}
