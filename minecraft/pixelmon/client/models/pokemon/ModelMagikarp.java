package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelMagikarp extends PixelmonModelBase {
	// fields

	PixelmonModelRenderer Body;

	public ModelMagikarp() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 22, 0);
		Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/magikarp/Body.obj")));

		skeleton = new SkeletonBase(Body);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		if (entity.isInWater())
			setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		else
			setRotationAnglesOutOfWater(f, f1, f2, f3, f4, f5);
		Body.render(f5);
	}

	private void setRotationAnglesOutOfWater(float f, float f1, float f2, float f3, float f4, float f5) {
		Body.rotateAngleZ = 1.5F;
		Body.rotationPointY = 22F;
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);
		setRotation(Body, radians, 0, 0);
		Body.rotateAngleZ = 0F;
		Body.rotationPointY = 17.5F;
	}

}
