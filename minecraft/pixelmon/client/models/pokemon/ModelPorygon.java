// Date: 4/29/2013 4:31:52 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
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
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelPorygon extends PixelmonModelBase {
	// fields

	PixelmonModelRenderer Body, footL, footR, head;

	public ModelPorygon() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 20, 0);
		Body.addCustomModel(new ModelCustomWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/porygon/PorygonBody.obj")));
		footL = new PixelmonModelRenderer(this, 0, 0);
		footL.setRotationPoint(3.015F, 1.561F, .829F);
		footL.addCustomModel(new ModelCustomWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/porygon/PorygonLFoot.obj")));
		footR = new PixelmonModelRenderer(this, 0, 0);
		footR.setRotationPoint(-3.015F, 1.561F, .829F);
		footR.addCustomModel(new ModelCustomWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/porygon/PorygonRFoot.obj")));
		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0, 0F, 5F);
		head.addCustomModel(new ModelCustomWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/porygon/PorygonHead.obj")));
		Body.addChild(head);
		Body.addChild(footL);
		Body.addChild(footR);

		float legspeed = 0.5F;
		float legRotationLimit = 1.4F;

		int degrees = 90;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);

		ModuleHead headModule = new ModuleHead(head);

		ModuleLeg leftLegModule = new ModuleLeg(footL, EnumLeg.FrontLeft,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(footR, EnumLeg.FrontRight,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		skeleton = new SkeletonBiped(Body, headModule, null, null,
				leftLegModule, rightLegModule, null);
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
