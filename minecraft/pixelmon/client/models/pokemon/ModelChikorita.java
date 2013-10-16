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

public class ModelChikorita extends PixelmonModelBase {

	PixelmonModelRenderer Body, footFL, footFR, footBL, footBR, Tail, leaf;

	public ModelChikorita() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/chikorita/Body.obj")));
		footFL = new PixelmonModelRenderer(this, 0, 0);
		footFL.setRotationPoint(0.4F, 0.6F, 0F);
		footFL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/chikorita/FrontLeftLeg.obj")));
		footFR = new PixelmonModelRenderer(this, 0, 0);
		footFR.setRotationPoint(-0.44F, 0.6F, 0F);
		footFR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/chikorita/FrontRightLeg.obj")));
		footBL = new PixelmonModelRenderer(this, 0, 0);
		footBL.setRotationPoint(0.276F, 0.6F, -0.9F);
		footBL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/chikorita/BackLeftLeg.obj")));
		footBR = new PixelmonModelRenderer(this, 0, 0);
		footBR.setRotationPoint(-0.39F, 0.6F, -0.9F);
		footBR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/chikorita/BackRightLeg.obj")));
		Tail = new PixelmonModelRenderer(this, 0, 0);
		Tail.setRotationPoint(-0.03458F, 1.015F, -1.24F);
		Tail.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/chikorita/Tail.obj")));
		leaf = new PixelmonModelRenderer(this, 0, 0);
		leaf.setRotationPoint(0F, 2.06F, 0.5F);
		leaf.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/chikorita/Leaf.obj")));
		Body.addChild(leaf);
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


		ModuleLeg leftLegModule = new ModuleLeg(footFL, EnumLeg.FrontLeft, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(footFR, EnumLeg.FrontRight, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg backLeft = new ModuleLeg(footBL, EnumLeg.BackLeft, EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg backRight = new ModuleLeg(footBR, EnumLeg.BackRight, EnumPhase.OutPhase, legRotationLimit, legspeed);
		
		ModuleTailBasic tailModule = new ModuleTailBasic(Tail, .2F, .05F, legspeed);
		
		
		skeleton = new SkeletonQuadruped(Body, null, leftLegModule, rightLegModule, backLeft, backRight, tailModule);
		scale = 5f;
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
