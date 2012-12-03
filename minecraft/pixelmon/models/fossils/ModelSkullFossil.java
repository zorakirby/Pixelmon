package pixelmon.models.fossils;

import pixelmon.blocks.TileEntityFossilMachine;
import net.minecraft.src.*;

public class ModelSkullFossil extends ModelFossil {
	// fields
	ModelRenderer base1;
	ModelRenderer base_2;
	ModelRenderer base_3;
	ModelRenderer base_4;
	ModelRenderer base_5;
	ModelRenderer base_6;
	ModelRenderer Shape1;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape25;
	ModelRenderer Shape26;
	ModelRenderer Shape27;
	ModelRenderer Shape28;

	public ModelSkullFossil() {
		setFossilName("SkullFossil");
		textureWidth = 32;
		textureHeight = 32;

		base1 = new ModelRenderer(this, 0, 0);
		base1.addBox(-4F, 0F, -3F, 8, 1, 7);
		base1.setRotationPoint(1F, 0F, 1F);
		base1.setTextureSize(32, 32);
		base1.mirror = true;
		setRotation(base1, 0F, 0.6806784F, 0F);
		base_2 = new ModelRenderer(this, 0, 0);
		base_2.addBox(-6F, 0F, -1F, 6, 1, 2);
		base_2.setRotationPoint(0.3F, 0F, 5.6F);
		base_2.setTextureSize(32, 32);
		base_2.mirror = true;
		setRotation(base_2, 0F, -0.6283185F, 0F);
		base_3 = new ModelRenderer(this, 0, 0);
		base_3.addBox(-4F, 0F, -4.6F, 6, 1, 3);
		base_3.setRotationPoint(1F, 0F, 1F);
		base_3.setTextureSize(32, 32);
		base_3.mirror = true;
		setRotation(base_3, 0F, 0.1570796F, 0F);
		base_4 = new ModelRenderer(this, 0, 0);
		base_4.addBox(-5.5F, 0F, -2.6F, 2, 1, 6);
		base_4.setRotationPoint(1F, 0F, 1F);
		base_4.setTextureSize(32, 32);
		base_4.mirror = true;
		setRotation(base_4, 0F, -0.2617994F, 0F);
		base_5 = new ModelRenderer(this, 0, 0);
		base_5.addBox(-4F, 0F, -2.5F, 7, 1, 5);
		base_5.setRotationPoint(1F, 0.5F, 1F);
		base_5.setTextureSize(32, 32);
		base_5.mirror = true;
		setRotation(base_5, 0F, 0.5410521F, 0F);
		base_6 = new ModelRenderer(this, 0, 0);
		base_6.addBox(-2F, 0F, -1.5F, 4, 1, 3);
		base_6.setRotationPoint(1F, 1F, 1F);
		base_6.setTextureSize(32, 32);
		base_6.mirror = true;
		setRotation(base_6, 0F, 0.296706F, 0F);
		Shape1 = new ModelRenderer(this, 0, 12);
		Shape1.addBox(-1.5F, 0F, -2.5F, 3, 1, 5);
		Shape1.setRotationPoint(0F, -0.4F, 1.5F);
		Shape1.setTextureSize(32, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0.7330383F, 0F);
		Shape12 = new ModelRenderer(this, 0, 8);
		Shape12.addBox(-2.5F, 0F, -1.5F, 5, 1, 3);
		Shape12.setRotationPoint(0F, -0.4F, 1.5F);
		Shape12.setTextureSize(32, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0.7330383F, 0F);
		Shape13 = new ModelRenderer(this, 0, 23);
		Shape13.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
		Shape13.setRotationPoint(0F, -0.8F, 1.5F);
		Shape13.setTextureSize(32, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0.7330383F, 0F);
		Shape14 = new ModelRenderer(this, 0, 18);
		Shape14.addBox(-2F, 0F, -2F, 4, 1, 4);
		Shape14.setRotationPoint(0F, -0.4F, 1.5F);
		Shape14.setTextureSize(32, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0.7330383F, 0F);
		Shape25 = new ModelRenderer(this, 0, 27);
		Shape25.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape25.setRotationPoint(-2F, -0.3F, -3F);
		Shape25.setTextureSize(32, 32);
		Shape25.mirror = true;
		setRotation(Shape25, 0F, -0.3839724F, 0F);
		Shape26 = new ModelRenderer(this, 0, 27);
		Shape26.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape26.setRotationPoint(1F, -0.3F, -3F);
		Shape26.setTextureSize(32, 32);
		Shape26.mirror = true;
		setRotation(Shape26, 0F, 0.1919862F, 0F);
		Shape27 = new ModelRenderer(this, 0, 27);
		Shape27.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape27.setRotationPoint(-4.3F, -0.3F, 2.5F);
		Shape27.setTextureSize(32, 32);
		Shape27.mirror = true;
		setRotation(Shape27, 0F, 0.6806784F, 0F);
		Shape28 = new ModelRenderer(this, 0, 27);
		Shape28.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape28.setRotationPoint(3.8F, -0.3F, 2F);
		Shape28.setTextureSize(32, 32);
		Shape28.mirror = true;
		setRotation(Shape28, 0F, 1.710423F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base1.render(f5);
		base_2.render(f5);
		base_3.render(f5);
		base_4.render(f5);
		base_5.render(f5);
		base_6.render(f5);
		Shape1.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape25.render(f5);
		Shape26.render(f5);
		Shape27.render(f5);
		Shape28.render(f5);
	}

	public void renderModel(TileEntityFossilMachine tile, float f5) {
		base1.render(f5);
		base_2.render(f5);
		base_3.render(f5);
		base_4.render(f5);
		base_5.render(f5);
		base_6.render(f5);
		Shape1.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape25.render(f5);
		Shape26.render(f5);
		Shape27.render(f5);
		Shape28.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
