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
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;

public class ModelTurtwig extends PixelmonModelBase {

	PixelmonModelRenderer Body, footFL, footFR, head, footBL, footBR, Tail;

	public ModelTurtwig() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24.2f, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/turtwig/TurtwigBody.obj")));
		footFL = new PixelmonModelRenderer(this, 0, 0);
		footFL.setRotationPoint(0.964F, 1.646F, 0.938F);
		footFL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/turtwig/TurtwigFrontLeftLeg.obj")));
		footFR = new PixelmonModelRenderer(this, 0, 0);
		footFR.setRotationPoint(-0.964F, 1.646F, 0.938F);
		footFR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/turtwig/TurtwigFrontRightLeg.obj")));
		footBL = new PixelmonModelRenderer(this, 0, 0);
		footBL.setRotationPoint(1.148F, 1.494F, -1.840F);
		footBL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/turtwig/TurtwigBackLeftLeg.obj")));
		footBR = new PixelmonModelRenderer(this, 0, 0);
		footBR.setRotationPoint(-1.148F, 1.494F, -1.840F);
		footBR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/turtwig/TurtwigBackRightLeg.obj")));
		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0F, 3.5F, 1.9F);
		head.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/turtwig/TurtwigHead.obj")));
		Tail = new PixelmonModelRenderer(this, 0, 0);
		Tail.setRotationPoint(0.032F, 1.590F, -2.129F);
		Tail.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/turtwig/TurtwigTail.obj")));
		Body.addChild(head);
		Body.addChild(footFL);
		Body.addChild(footFR);
		Body.addChild(footBL);
		Body.addChild(footBR);
		Body.addChild(Tail);


		float legspeed = 0.5F;
		float legRotationLimit = 1.4F;

		int degrees = 90;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);

		ModuleHead headModule = new ModuleHead(head);

		ModuleLeg leftLegModule = new ModuleLeg(footFL, EnumLeg.FrontLeft, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(footFR, EnumLeg.FrontRight, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg backLeft = new ModuleLeg(footBL, EnumLeg.BackLeft, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg backRight = new ModuleLeg(footBR, EnumLeg.BackRight, EnumPhase.OutPhase, legRotationLimit, legspeed);
		
		ModuleTailBasic tailModule = new ModuleTailBasic(Tail, .2F, .05F, legspeed);
		
		
		skeleton = new SkeletonQuadruped(Body, headModule, leftLegModule, rightLegModule, backLeft, backRight, tailModule);
		scale=1.4f;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Body.render(f5);
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		Body.rotateAngleX = 15.6F;
	}
}
