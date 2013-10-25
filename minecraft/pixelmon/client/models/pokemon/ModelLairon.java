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

public class ModelLairon extends PixelmonModelBase {

	PixelmonModelRenderer Body, footFL, footFR, footBL, footBR;

	public ModelLairon() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 22, -4);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/lairon/Body.obj")));
		footFL = new PixelmonModelRenderer(this, 0, 0);
		footFL.setRotationPoint(3.201F, 0.895F, -3.701F);
		footFL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/lairon/FrontLeftLeg.obj")));
		footFR = new PixelmonModelRenderer(this, 0, 0);
		footFR.setRotationPoint(-3.201F, 0.895F, -3.701F);
		footFR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/lairon/FrontRightLeg.obj")));
		footBL = new PixelmonModelRenderer(this, 0, 0);
		footBL.setRotationPoint(1.950F, 0.706F, -8.598F);
		footBL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/lairon/BackLeftLeg.obj")));
		footBR = new PixelmonModelRenderer(this, 0, 0);
		footBR.setRotationPoint(-1.950F, 0.706F, -8.598F);
		footBR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/lairon/BackRightLeg.obj")));
		Body.addChild(footFL);
		Body.addChild(footFR);
		Body.addChild(footBL);
		Body.addChild(footBR);


		float legspeed = 1F;
		float legRotationLimit = 0.8F;

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);


		ModuleLeg leftLegModule = new ModuleLeg(footFL, EnumLeg.FrontLeft, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(footFR, EnumLeg.FrontRight, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backLeft = new ModuleLeg(footBL, EnumLeg.BackLeft, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backRight = new ModuleLeg(footBR, EnumLeg.BackRight, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		
		
		skeleton = new SkeletonQuadruped(Body, null, leftLegModule, rightLegModule, backLeft, backRight, null);
		scale=2f;
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
