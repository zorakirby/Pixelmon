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
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;

public class ModelMudkip extends PixelmonModelBase {

	PixelmonModelRenderer Body, footFL, footFR, head, footBL, footBR, Tail;

	public ModelMudkip() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24, 0);
		Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/mudkip/Body.obj")));
		
		footFL = new PixelmonModelRenderer(this, 0, 0);
		footFL.setRotationPoint(0.30777F, -0.206F, 0.549F);
		footFL.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/mudkip/FrontLeft.obj")));
		
		footFR = new PixelmonModelRenderer(this, 0, 0);
		footFR.setRotationPoint(-0.30777F, -0.206F, 0.549F);
		footFR.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/mudkip/FrontRight.obj")));
		
		footBL = new PixelmonModelRenderer(this, 0, 0);
		footBL.setRotationPoint(0.348F, -0.213F, 0.534F);
		footBL.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/mudkip/BackLeft.obj")));
		
		footBR = new PixelmonModelRenderer(this, 0, 0);
		footBR.setRotationPoint(-0.348F, -0.213F, 0.534F);
		footBR.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/mudkip/BackRight.obj")));
		
		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0F, 0.08F, .55F);
		head.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/mudkip/Head.obj")));
		
		Tail = new PixelmonModelRenderer(this, 0, 0);
		Tail.setRotationPoint(0F, 0.045F, 0.835F);
		Tail.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/mudkip/Tail.obj")));
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

		ModuleLeg leftLegModule = new ModuleLeg(footFL, EnumLeg.FrontLeft, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(footFR, EnumLeg.FrontRight, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backLeft = new ModuleLeg(footBL, EnumLeg.BackLeft, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backRight = new ModuleLeg(footBR, EnumLeg.BackRight, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		
		ModuleTailBasic tailModule = new ModuleTailBasic(Tail, .2F, .05F, legspeed);
		
		
		skeleton = new SkeletonQuadruped(Body, headModule, leftLegModule, rightLegModule, backLeft, backRight, tailModule);

		scale=4.4f;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Body.setRotationPoint(0, 23.3F, 0);
		footBL.setRotationPoint(0.348F, -0.213F, -0.534F);
		footBR.setRotationPoint(-0.348F, -0.213F, -0.534F);
		Tail.setRotationPoint(0F, 0.045F, -0.835F);
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
