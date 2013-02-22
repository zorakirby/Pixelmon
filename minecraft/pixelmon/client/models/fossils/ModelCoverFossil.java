package pixelmon.client.models.fossils;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import pixelmon.blocks.TileEntityFossilMachine;

public class ModelCoverFossil extends ModelFossil {
	// fields
	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer base3;
	ModelRenderer base4;
	ModelRenderer base5;
	ModelRenderer base6;
	ModelRenderer base7;
	ModelRenderer base8;
	ModelRenderer base9;
	ModelRenderer base10;

	public ModelCoverFossil() {
		setFossilName("CoverFossil");
		textureWidth = 32;
		textureHeight = 32;

		base1 = new ModelRenderer(this, 0, 0);
		base1.addBox(-4F, 0F, -3F, 5, 3, 5);
		base1.setRotationPoint(0F, -2F, 3.4F);
		base1.setTextureSize(32, 32);
		base1.mirror = true;
		setRotation(base1, 0F, -2.945372F, 0F);
		base2 = new ModelRenderer(this, 0, 0);
		base2.addBox(-6F, 0F, -1F, 6, 3, 2);
		base2.setRotationPoint(0.3F, -2F, 5.6F);
		base2.setTextureSize(32, 32);
		base2.mirror = true;
		setRotation(base2, 0F, -0.6283185F, 0F);
		base3 = new ModelRenderer(this, 0, 0);
		base3.addBox(-4F, 0F, -4.6F, 6, 3, 3);
		base3.setRotationPoint(1F, -2F, 1F);
		base3.setTextureSize(32, 32);
		base3.mirror = true;
		setRotation(base3, 0F, 0.1570796F, 0F);
		base4 = new ModelRenderer(this, 0, 0);
		base4.addBox(-5.5F, 0F, -2.6F, 2, 3, 6);
		base4.setRotationPoint(1F, -2F, 1F);
		base4.setTextureSize(32, 32);
		base4.mirror = true;
		setRotation(base4, 0F, -0.2617994F, 0F);
		base5 = new ModelRenderer(this, 0, 0);
		base5.addBox(-4F, 0F, -2.5F, 7, 1, 5);
		base5.setRotationPoint(1F, -2.2F, 1F);
		base5.setTextureSize(32, 32);
		base5.mirror = true;
		setRotation(base5, 0F, -0.4255918F, 0F);
		base6 = new ModelRenderer(this, 0, 0);
		base6.addBox(-2F, 0F, -1.5F, 4, 1, 3);
		base6.setRotationPoint(1F, 1F, 1F);
		base6.setTextureSize(32, 32);
		base6.mirror = true;
		setRotation(base6, 0F, -0.0750801F, 0F);
		base7 = new ModelRenderer(this, 0, 0);
		base7.addBox(-4F, 0F, -2.5F, 7, 1, 5);
		base7.setRotationPoint(1F, 0.5F, 1F);
		base7.setTextureSize(32, 32);
		base7.mirror = true;
		setRotation(base7, 0F, 0.0949087F, 0F);
		base8 = new ModelRenderer(this, 0, 0);
		base8.addBox(-4F, 0F, -3F, 8, 3, 7);
		base8.setRotationPoint(1F, -2F, 1F);
		base8.setTextureSize(32, 32);
		base8.mirror = true;
		setRotation(base8, 0F, 0.6806784F, 0F);
		base9 = new ModelRenderer(this, 0, 0);
		base9.addBox(-4F, 0F, -3F, 4, 3, 5);
		base9.setRotationPoint(2F, -2F, 3.5F);
		base9.setTextureSize(32, 32);
		base9.mirror = true;
		setRotation(base9, 0F, -2.144896F, 0F);
		base10 = new ModelRenderer(this, 0, 0);
		base10.addBox(-4F, 0F, -2.5F, 7, 1, 5);
		base10.setRotationPoint(1F, -2.2F, 1F);
		base10.setTextureSize(32, 32);
		base10.mirror = true;
		setRotation(base10, 0F, 0.0205515F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base1.render(f5);
		base2.render(f5);
		base3.render(f5);
		base4.render(f5);
		base5.render(f5);
		base6.render(f5);
		base7.render(f5);
		base8.render(f5);
		base9.render(f5);
		base10.render(f5);
	}

	public void renderModel(float f5) {
		base1.render(f5);
		base2.render(f5);
		base3.render(f5);
		base4.render(f5);
		base5.render(f5);
		base6.render(f5);
		base7.render(f5);
		base8.render(f5);
		base9.render(f5);
		base10.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
