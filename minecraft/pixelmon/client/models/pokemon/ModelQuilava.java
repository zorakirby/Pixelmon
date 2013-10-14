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

public class ModelQuilava extends PixelmonModelBase {

	PixelmonModelRenderer Body, footFL, footFR, footBL, footBR, head;

	public ModelQuilava() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24.2f, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/quilava/Body.obj")));
		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0F, 2.739F, 2.261F);
		head.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/quilava/Head.obj")));
		footFL = new PixelmonModelRenderer(this, 0, 0);
		footFL.setRotationPoint(0.47F,2.462F, 1.9F);
		footFL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/quilava/FrontLeftLeg.obj")));
		footFR = new PixelmonModelRenderer(this, 0, 0);
		footFR.setRotationPoint(-0.47F,2.462F, 1.9F);
		footFR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/quilava/FrontRightLeg.obj")));
		footBL = new PixelmonModelRenderer(this, 0, 0);
		footBL.setRotationPoint(0.693F, 1.433F, -1.72F);
		footBL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/quilava/BackLeftLeg.obj")));
		footBR = new PixelmonModelRenderer(this, 0, 0);
		footBR.setRotationPoint(-0.693F, 1.433F, -1.72F);
		footBR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/quilava/BackRightLeg.obj")));
		Body.addChild(footFL);
		Body.addChild(footFR);
		Body.addChild(footBL);
		Body.addChild(footBR);
		Body.addChild(head);

		float legspeed = 0.5F;
		float legRotationLimit = 0.85F;

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);

		ModuleLeg leftLegModule = new ModuleLeg(footFL, EnumLeg.FrontLeft, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(footFR, EnumLeg.FrontRight, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg backLeft = new ModuleLeg(footBL, EnumLeg.BackLeft, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg backRight = new ModuleLeg(footBR, EnumLeg.BackRight, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleHead headModule = new ModuleHead(head);

		skeleton = new SkeletonQuadruped(Body, headModule, leftLegModule, rightLegModule, backLeft, backRight, null);
		scale=3.2f;
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
	}
}
