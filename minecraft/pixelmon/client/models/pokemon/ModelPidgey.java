package pixelmon.client.models.pokemon;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTail;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelPidgey extends PixelmonModelBase {

	PixelmonModelRenderer Body, Lleg, RLeg;

	public ModelPidgey() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 22.5F, 0);
		Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader
				.loadModel("/pixelmon/client/models/objFiles/pidgey/Body.obj")));

		Lleg = new PixelmonModelRenderer(this, 0, 0);
		Lleg.setRotationPoint(0.75F, -0.8F, 1F);
		Lleg.addCustomModel(new ModelCustomWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/pidgey/LeftLeg.obj")));

		RLeg = new PixelmonModelRenderer(this, 0, 0);
		RLeg.setRotationPoint(-0.75F, -0.8F, 1F);
		RLeg.addCustomModel(new ModelCustomWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/pidgey/RightLeg.obj")));

		Body.addChild(Lleg);
		Body.addChild(RLeg);

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);
		float legspeed = 0.5F;
		float legRotationLimit = 0.8F;

		ModuleLeg leftLeg = new ModuleLeg(Lleg, EnumLeg.FrontLeft,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLeg = new ModuleLeg(RLeg, EnumLeg.FrontRight,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);

		skeleton = new SkeletonBiped(Body, null, null, null, leftLeg, rightLeg,
				null);
		scale = 2.5f;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Body.render(f5);
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y,
			float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5) {
	}
}
