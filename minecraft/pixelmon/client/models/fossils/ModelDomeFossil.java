package pixelmon.client.models.fossils;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import pixelmon.blocks.TileEntityFossilMachine;

public class ModelDomeFossil extends ModelFossil {
	// fields
	ModelRenderer top_1;
	ModelRenderer top_l1;
	ModelRenderer top_r2;
	ModelRenderer top_r1;
	ModelRenderer top_l_2;
	ModelRenderer midle_l;
	ModelRenderer midle_r;
	ModelRenderer tail;
	ModelRenderer base;
	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer base3;
	ModelRenderer base5;
	ModelRenderer base4;

	public ModelDomeFossil() {
		setFossilName("DomeFossil");
		textureWidth = 32;
		textureHeight = 32;

		top_1 = new ModelRenderer(this, 8, 7);
		top_1.addBox(-1F, 0F, 0F, 2, 1, 2);
		top_1.setRotationPoint(0F, -0.1F, 0F);
		top_1.setTextureSize(32, 32);
		top_1.mirror = true;
		setRotation(top_1, 0F, 0F, 0F);
		top_l1 = new ModelRenderer(this, 0, 7);
		top_l1.addBox(-3F, 0F, -1F, 3, 1, 1);
		top_l1.setRotationPoint(-1F, 0F, 2F);
		top_l1.setTextureSize(32, 32);
		top_l1.mirror = true;
		setRotation(top_l1, 0F, -0.6981317F, 0F);
		top_r2 = new ModelRenderer(this, 0, 9);
		top_r2.addBox(-1.2F, 0F, 0F, 3, 1, 1);
		top_r2.setRotationPoint(1F, 0F, 0F);
		top_r2.setTextureSize(32, 32);
		top_r2.mirror = true;
		setRotation(top_r2, 0F, 0.4014257F, 0F);
		top_r1 = new ModelRenderer(this, 0, 7);
		top_r1.addBox(0F, 0F, -1F, 3, 1, 1);
		top_r1.setRotationPoint(1F, 0F, 2F);
		top_r1.setTextureSize(32, 32);
		top_r1.mirror = true;
		setRotation(top_r1, 0F, 0.6981317F, 0F);
		top_l_2 = new ModelRenderer(this, 0, 9);
		top_l_2.addBox(-1.8F, 0F, 0F, 3, 1, 1);
		top_l_2.setRotationPoint(-1F, 0F, 0F);
		top_l_2.setTextureSize(32, 32);
		top_l_2.mirror = true;
		setRotation(top_l_2, 0F, -0.4014257F, 0F);
		midle_l = new ModelRenderer(this, 8, 10);
		midle_l.addBox(-1.5F, 0F, 0F, 2, 1, 2);
		midle_l.setRotationPoint(0F, 0.2F, -1F);
		midle_l.setTextureSize(32, 32);
		midle_l.mirror = true;
		setRotation(midle_l, 0F, -0.2617994F, 0F);
		midle_r = new ModelRenderer(this, 8, 10);
		midle_r.addBox(-0.5F, 0F, 0F, 2, 1, 2);
		midle_r.setRotationPoint(0F, 0.2F, -1F);
		midle_r.setTextureSize(32, 32);
		setRotation(midle_r, 0F, 0.2617994F, 0F);
		midle_r.mirror = false;
		tail = new ModelRenderer(this, 10, 13);
		tail.addBox(-0.5F, 0F, -2F, 1, 1, 2);
		tail.setRotationPoint(0F, 0.3F, -1F);
		tail.setTextureSize(32, 32);
		tail.mirror = true;
		setRotation(tail, 0F, 0F, 0F);
		base = new ModelRenderer(this, 0, 0);
		base.addBox(-3F, 0F, -2F, 5, 1, 5);
		base.setRotationPoint(0F, 0.6F, 0F);
		base.setTextureSize(32, 32);
		base.mirror = true;
		setRotation(base, 0F, -0.7504916F, 0F);
		base1 = new ModelRenderer(this, 0, 0);
		base1.addBox(-1.3F, 0F, 0.5F, 4, 1, 3);
		base1.setRotationPoint(0F, 0.6F, 0F);
		base1.setTextureSize(32, 32);
		base1.mirror = true;
		setRotation(base1, 0F, 0.2094395F, 0F);
		base2 = new ModelRenderer(this, 0, 0);
		base2.addBox(0F, 0F, -2.8F, 4, 1, 5);
		base2.setRotationPoint(0F, 0.6F, 0F);
		base2.setTextureSize(32, 32);
		base2.mirror = true;
		setRotation(base2, 0F, -0.0872665F, 0F);
		base3 = new ModelRenderer(this, 0, 0);
		base3.addBox(-2.8F, 0F, -3.6F, 6, 1, 3);
		base3.setRotationPoint(0F, 0.6F, 0F);
		base3.setTextureSize(32, 32);
		base3.mirror = true;
		setRotation(base3, 0F, -0.3141593F, 0F);
		base5 = new ModelRenderer(this, 0, 0);
		base5.addBox(-2.2F, 0.4F, -2.3F, 5, 1, 4);
		base5.setRotationPoint(0F, 0.6F, 0F);
		base5.setTextureSize(32, 32);
		base5.mirror = true;
		setRotation(base5, 0F, -0.5410521F, 0F);
		base4 = new ModelRenderer(this, 0, 0);
		base4.addBox(-1F, 0.4F, -1.3F, 3, 1, 2);
		base4.setRotationPoint(0F, 1F, 0F);
		base4.setTextureSize(32, 32);
		base4.mirror = true;
		setRotation(base4, 0F, -0.296706F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		top_1.render(f5);
		top_l1.render(f5);
		top_r2.render(f5);
		top_r1.render(f5);
		top_l_2.render(f5);
		midle_l.render(f5);
		midle_r.render(f5);
		tail.render(f5);
		base.render(f5);
		base1.render(f5);
		base2.render(f5);
		base3.render(f5);
		base5.render(f5);
		base4.render(f5);
	}

	public void renderModel(float f5) {
		top_1.render(f5);
		top_l1.render(f5);
		top_r2.render(f5);
		top_r1.render(f5);
		top_l_2.render(f5);
		midle_l.render(f5);
		midle_r.render(f5);
		tail.render(f5);
		base.render(f5);
		base1.render(f5);
		base2.render(f5);
		base3.render(f5);
		base5.render(f5);
		base4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
