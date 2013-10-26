package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelOBJWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;

public class ModelMetagross extends PixelmonModelBase {

	PixelmonModelRenderer Body, FLLeg, FRLeg, BLLeg, BRLeg;

	public ModelMetagross() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 23.9F, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/metagross/Body.obj")));
		FLLeg = new PixelmonModelRenderer(this, 0, 0);
		FLLeg.setRotationPoint(4.825F, 6.882F, 3.231F);
		FLLeg.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/metagross/FrontLeftLeg.obj")));
		FRLeg = new PixelmonModelRenderer(this, 0, 0);
		FRLeg.setRotationPoint(-4.825F, 6.882F, 3.231F);
		FRLeg.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/metagross/FrontRightLeg.obj")));
		BLLeg = new PixelmonModelRenderer(this, 0, 0);
		BLLeg.setRotationPoint(4.555F, 6.882F, -4.079F);
		BLLeg.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/metagross/BackLeftLeg.obj")));
		BRLeg = new PixelmonModelRenderer(this, 0, 0);
		BRLeg.setRotationPoint(-4.555F, 6.882F, -4.079F);
		BRLeg.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/metagross/BackRightLeg.obj")));
		Body.addChild(FLLeg);
		Body.addChild(FRLeg);
		Body.addChild(BLLeg);
		Body.addChild(BRLeg);

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);
		setRotation(Body, radians, 0, 0);

		float legspeed = 0.8F;
		float legRotationLimit = 1.1F;

		ModuleLeg frontlegLModule = new ModuleLeg(FLLeg, EnumLeg.FrontLeft, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg frontlegRModule = new ModuleLeg(FRLeg, EnumLeg.FrontRight, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backlegLModule = new ModuleLeg(BLLeg, EnumLeg.BackLeft, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg backlegRModule = new ModuleLeg(BRLeg, EnumLeg.BackRight, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit, legspeed);

		skeleton = new SkeletonQuadruped(Body, null, frontlegLModule, frontlegRModule, backlegLModule, backlegRModule, null);
		scale=2.8f;
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