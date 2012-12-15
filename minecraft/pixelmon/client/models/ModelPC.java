package pixelmon.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import pixelmon.blocks.TileEntityPC;

public class ModelPC extends ModelBase {
	// fields
	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer Base3;
	ModelRenderer Base4;
	ModelRenderer Bezel1;
	ModelRenderer Bezel2;
	ModelRenderer Bezel3;
	ModelRenderer Bezel4;
	ModelRenderer Backing1;
	ModelRenderer Screen;
	ModelRenderer Keyboard;
	ModelRenderer Backing2;

	public ModelPC() {
		textureWidth = 128;
		textureHeight = 128;

		Base1 = new ModelRenderer(this, 0, 0);
		Base1.addBox(0F, 0F, 0F, 16, 14, 10);
		Base1.setRotationPoint(-8F, 10F, -5F);
		Base1.setTextureSize(128, 128);
		Base1.mirror = true;
		setRotation(Base1, 0F, 0F, 0F);
		Base2 = new ModelRenderer(this, 0, 24);
		Base2.addBox(0F, 0F, 0F, 10, 14, 16);
		Base2.setRotationPoint(-5F, 10F, -8F);
		Base2.setTextureSize(128, 128);
		Base2.mirror = true;
		setRotation(Base2, 0F, 0F, 0F);
		Base3 = new ModelRenderer(this, 0, 54);
		Base3.addBox(0F, 0F, -4F, 2, 14, 4);
		Base3.setRotationPoint(5F, 10F, -7.5F);
		Base3.setTextureSize(128, 128);
		Base3.mirror = true;
		setRotation(Base3, 0F, -2.377138F, 0F);
		Base4 = new ModelRenderer(this, 12, 54);
		Base4.addBox(0F, 0F, 0F, 2, 14, 4);
		Base4.setRotationPoint(-5F, 10F, -7.5F);
		Base4.setTextureSize(128, 128);
		Base4.mirror = true;
		setRotation(Base4, 0F, -0.7679449F, 0F);
		Bezel1 = new ModelRenderer(this, 0, 74);
		Bezel1.addBox(0F, 0F, 0F, 2, 14, 1);
		Bezel1.setRotationPoint(6F, -5F, -3F);
		Bezel1.setTextureSize(128, 128);
		Bezel1.mirror = true;
		setRotation(Bezel1, 0F, 0F, 0F);
		Bezel2 = new ModelRenderer(this, 0, 90);
		Bezel2.addBox(0F, 0F, 0F, 16, 2, 1);
		Bezel2.setRotationPoint(-8F, 8F, -3F);
		Bezel2.setTextureSize(128, 128);
		Bezel2.mirror = true;
		setRotation(Bezel2, 0F, 0F, 0F);
		Bezel3 = new ModelRenderer(this, 0, 94);
		Bezel3.addBox(0F, 0F, 0F, 16, 2, 1);
		Bezel3.setRotationPoint(-8F, -6F, -3F);
		Bezel3.setTextureSize(128, 128);
		Bezel3.mirror = true;
		setRotation(Bezel3, 0F, 0F, 0F);
		Bezel4 = new ModelRenderer(this, 7, 74);
		Bezel4.addBox(0F, 0F, 0F, 2, 14, 1);
		Bezel4.setRotationPoint(-8F, -5F, -3F);
		Bezel4.setTextureSize(128, 128);
		Bezel4.mirror = true;
		setRotation(Bezel4, 0F, 0F, 0F);
		Backing1 = new ModelRenderer(this, 54, 0);
		Backing1.addBox(0F, 0F, 0F, 14, 14, 7);
		Backing1.setRotationPoint(-7F, -5F, -1F);
		Backing1.setTextureSize(128, 128);
		Backing1.mirror = true;
		setRotation(Backing1, 0F, 0F, 0F);
		Screen = new ModelRenderer(this, 54, 40);
		Screen.addBox(0F, 0F, 0F, 16, 16, 7);
		Screen.setRotationPoint(-8F, -6F, -2F);
		Screen.setTextureSize(128, 128);
		Screen.mirror = true;
		setRotation(Screen, 0F, 0F, 0F);
		Keyboard = new ModelRenderer(this, 54, 64);
		Keyboard.addBox(-5F, -1F, 0F, 10, 1, 4);
		Keyboard.setRotationPoint(0F, 10.5F, -6.5F);
		Keyboard.setTextureSize(128, 128);
		Keyboard.mirror = true;
		setRotation(Keyboard, 0.296706F, 0F, 0F);
		Backing2 = new ModelRenderer(this, 54, 22);
		Backing2.addBox(0F, 0F, 0F, 10, 10, 7);
		Backing2.setRotationPoint(-5F, -3F, 1F);
		Backing2.setTextureSize(128, 128);
		Backing2.mirror = true;
		setRotation(Backing1, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Base1.render(f5);
		Base2.render(f5);
		Base3.render(f5);
		Base4.render(f5);
		Bezel1.render(f5);
		Bezel2.render(f5);
		Bezel3.render(f5);
		Bezel4.render(f5);
		Backing1.render(f5);
		Screen.render(f5);
		Keyboard.render(f5);
		Backing2.render(f5);
	}

	public void renderModel(TileEntityPC entity, float f5) {
		Base1.render(f5);
		Base2.render(f5);
		Base3.render(f5);
		Base4.render(f5);
		Bezel1.render(f5);
		Bezel2.render(f5);
		Bezel3.render(f5);
		Bezel4.render(f5);
		Backing1.render(f5);
		Screen.render(f5);
		Keyboard.render(f5);
		Backing2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}