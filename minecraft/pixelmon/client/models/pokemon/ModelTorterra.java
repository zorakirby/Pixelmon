package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelOBJWrapper;
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
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;

public class ModelTorterra extends PixelmonModelBase {

	PixelmonModelRenderer Body, footFL, footFR, head, footBL, footBR, Tail;

	public ModelTorterra() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24.1f, 0);
		Body.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/torterra/TorterraBody.obj")));
		footFL = new PixelmonModelRenderer(this, 0, 0);
		footFL.setRotationPoint(10.137F, 9.652F, 12.282F);
		footFL.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/torterra/TorterraFrontLeftLeg.obj")));
		footFR = new PixelmonModelRenderer(this, 0, 0);
		footFR.setRotationPoint(-10.137F, 9.652F, 12.282F);
		footFR.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/torterra/TorterraFrontRightLeg.obj")));
		footBL = new PixelmonModelRenderer(this, 0, 0);
		footBL.setRotationPoint(10.137F, 9.652F, -10.922F);
		footBL.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/torterra/TorterraBackLeftLeg.obj")));
		footBR = new PixelmonModelRenderer(this, 0, 0);
		footBR.setRotationPoint(-10.137F, 9.652F, -10.922F);
		footBR.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/torterra/TorterraBackRightLeg.obj")));
		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0.603F, 12.629F, 22.697F);
		head.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/torterra/TorterraHead.obj")));
		Tail = new PixelmonModelRenderer(this, 0, 0);
		Tail.setRotationPoint(0.0F, 10.717F, -15.295F);
		Tail.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/torterra/TorterraTail.obj")));
		Body.addChild(head);
		Body.addChild(footFL);
		Body.addChild(footFR);
		Body.addChild(footBL);
		Body.addChild(footBR);
		Body.addChild(Tail);

		float legspeed = 0.5F;
		float legRotationLimit = 1.4F;

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);

		ModuleHead headModule = new ModuleHead(head);

		ModuleLeg leftLegModule = new ModuleLeg(footFL, EnumLeg.FrontLeft,
				EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(footFR, EnumLeg.FrontRight,
				EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backLeft = new ModuleLeg(footBL, EnumLeg.BackLeft,
				EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backRight = new ModuleLeg(footBR, EnumLeg.BackRight,
				EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);

		ModuleTailBasic tailModule = new ModuleTailBasic(Tail, .2F, .05F,
				legspeed);

		skeleton = new SkeletonQuadruped(Body, headModule, leftLegModule,
				rightLegModule, backLeft, backRight, tailModule);
		scale = 0.8f;
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
