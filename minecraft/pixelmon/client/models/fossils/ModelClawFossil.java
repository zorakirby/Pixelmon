package pixelmon.client.models.fossils;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import pixelmon.blocks.TileEntityFossilMachine;

public class ModelClawFossil extends ModelFossil {
	// fields
	ModelRenderer claw_1_1;
	ModelRenderer claw_1_2;
	ModelRenderer claw_2_1;
	ModelRenderer xlaw_2_2;
	ModelRenderer claw_3_1;
	ModelRenderer claw_3_2;
	ModelRenderer claw_base_1_1;
	ModelRenderer claw_base_1_2;
	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer base3;
	ModelRenderer base4;
	ModelRenderer base5;
	ModelRenderer base6;
	ModelRenderer base;

	public ModelClawFossil() {
		setFossilName("ClawFossil");
		textureWidth = 32;
		textureHeight = 32;

		claw_1_1 = new ModelRenderer(this, 0, 5);
		claw_1_1.addBox(-0.05F, -1F, 0.9F, 2, 1, 2);
		claw_1_1.setRotationPoint(-0.2F, 0F, 0.5F);
		claw_1_1.setTextureSize(32, 32);
		claw_1_1.mirror = true;
		setRotation(claw_1_1, 0F, -0.4886922F, 0F);
		claw_1_2 = new ModelRenderer(this, 0, 8);
		claw_1_2.addBox(-0.2F, -1F, -0.2F, 2, 1, 2);
		claw_1_2.setRotationPoint(-0.2F, 0F, 0.5F);
		claw_1_2.setTextureSize(32, 32);
		claw_1_2.mirror = true;
		setRotation(claw_1_2, 0F, -0.3490659F, 0F);
		claw_2_1 = new ModelRenderer(this, 0, 11);
		claw_2_1.addBox(0F, -1F, 1F, 2, 1, 2);
		claw_2_1.setRotationPoint(-1F, 0.1F, -0.5F);
		claw_2_1.setTextureSize(32, 32);
		claw_2_1.mirror = true;
		setRotation(claw_2_1, 0F, -0.6981317F, 0F);
		xlaw_2_2 = new ModelRenderer(this, 8, 10);
		xlaw_2_2.addBox(-0.2F, -1F, 0.5F, 2, 1, 1);
		xlaw_2_2.setRotationPoint(-1F, 0.1F, -0.5F);
		xlaw_2_2.setTextureSize(32, 32);
		xlaw_2_2.mirror = true;
		setRotation(xlaw_2_2, 0F, -0.5235988F, 0F);
		claw_3_1 = new ModelRenderer(this, 0, 14);
		claw_3_1.addBox(0F, -1F, 0F, 3, 1, 1);
		claw_3_1.setRotationPoint(-3F, 0.2F, -0.9F);
		claw_3_1.setTextureSize(32, 32);
		claw_3_1.mirror = true;
		setRotation(claw_3_1, 0F, -1.047198F, 0F);
		claw_3_2 = new ModelRenderer(this, 0, 16);
		claw_3_2.addBox(0F, -1F, 0F, 3, 1, 1);
		claw_3_2.setRotationPoint(-3F, 0.2F, -0.9F);
		claw_3_2.setTextureSize(32, 32);
		claw_3_2.mirror = true;
		setRotation(claw_3_2, 0F, -0.5235988F, 0F);
		claw_base_1_1 = new ModelRenderer(this, 8, 7);
		claw_base_1_1.addBox(0.6F, -1F, -0.6F, 2, 1, 2);
		claw_base_1_1.setRotationPoint(0.7F, 0.1F, 0.8F);
		claw_base_1_1.setTextureSize(32, 32);
		claw_base_1_1.mirror = true;
		setRotation(claw_base_1_1, 0F, -1.22173F, 0F);
		claw_base_1_2 = new ModelRenderer(this, 8, 5);
		claw_base_1_2.addBox(0.6F, -0.2F, -0.5F, 2, 1, 1);
		claw_base_1_2.setRotationPoint(0.7F, -0.8F, 2.6F);
		claw_base_1_2.setTextureSize(32, 32);
		claw_base_1_2.mirror = true;
		setRotation(claw_base_1_2, 0F, -0.2792527F, 0.1919862F);
		base1 = new ModelRenderer(this, 0, 0);
		base1.addBox(-2F, -1F, 0F, 6, 1, 4);
		base1.setRotationPoint(0F, 0.8F, 0F);
		base1.setTextureSize(32, 32);
		base1.mirror = true;
		setRotation(base1, 0F, -0.4712389F, 0F);
		base2 = new ModelRenderer(this, 0, 0);
		base2.addBox(-2F, -1F, 1.4F, 3, 1, 3);
		base2.setRotationPoint(0F, 0.8F, 0F);
		base2.setTextureSize(32, 32);
		base2.mirror = true;
		setRotation(base2, 0F, -1.169371F, 0F);
		base3 = new ModelRenderer(this, 0, 0);
		base3.addBox(-4.8F, -1F, -0.6F, 5, 1, 1);
		base3.setRotationPoint(0F, 0.8F, 0F);
		base3.setTextureSize(32, 32);
		base3.mirror = true;
		setRotation(base3, 0F, -0.1047198F, 0F);
		base4 = new ModelRenderer(this, 0, 0);
		base4.addBox(-0.1F, -1F, -0.6F, 5, 1, 2);
		base4.setRotationPoint(0F, 0.8F, 0F);
		base4.setTextureSize(32, 32);
		base4.mirror = true;
		setRotation(base4, 0F, -0.5934119F, 0F);
		base5 = new ModelRenderer(this, 0, 0);
		base5.addBox(3F, -1F, -0.8F, 2, 1, 3);
		base5.setRotationPoint(1F, 0.8F, 0F);
		base5.setTextureSize(32, 32);
		base5.mirror = true;
		setRotation(base5, 0F, -0.9424778F, 0F);
		base6 = new ModelRenderer(this, 0, 0);
		base6.addBox(-3F, -1F, -1F, 6, 1, 3);
		base6.setRotationPoint(0F, 1.2F, 1.5F);
		base6.setTextureSize(32, 32);
		base6.mirror = true;
		setRotation(base6, 0F, -0.3665191F, 0F);
		base = new ModelRenderer(this, 0, 0);
		base.addBox(-2F, -1F, -0.5F, 4, 1, 2);
		base.setRotationPoint(0F, 1.6F, 1.5F);
		base.setTextureSize(32, 32);
		base.mirror = true;
		setRotation(base, 0F, -0.2094395F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		claw_1_1.render(f5);
		claw_1_2.render(f5);
		claw_2_1.render(f5);
		xlaw_2_2.render(f5);
		claw_3_1.render(f5);
		claw_3_2.render(f5);
		claw_base_1_1.render(f5);
		claw_base_1_2.render(f5);
		base1.render(f5);
		base2.render(f5);
		base3.render(f5);
		base4.render(f5);
		base5.render(f5);
		base6.render(f5);
		base.render(f5);
	}

	public void renderModel(TileEntityFossilMachine entity, float f5) {
		claw_1_1.render(f5);
		claw_1_2.render(f5);
		claw_2_1.render(f5);
		xlaw_2_2.render(f5);
		claw_3_1.render(f5);
		claw_3_2.render(f5);
		claw_base_1_1.render(f5);
		claw_base_1_2.render(f5);
		base1.render(f5);
		base2.render(f5);
		base3.render(f5);
		base4.render(f5);
		base5.render(f5);
		base6.render(f5);
		base.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
