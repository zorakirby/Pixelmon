package pixelmon.client.models.pokemon.flying;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
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
import pixelmon.client.models.animations.ModuleTail;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.bird.EnumWing;
import pixelmon.client.models.animations.bird.ModuleWingSegmented;
import pixelmon.client.models.animations.bird.SkeletonBird;

public class ModelCharizard extends PixelmonModelBase {

	PixelmonModelRenderer Body, LWing1, LWing2, LWing3, LWing4, RWing1, RWing2, RWing3, RWing4;

	public ModelCharizard() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/Body.obj")));

		RWing1 = new PixelmonModelRenderer(this, 0, 0);
		RWing1.setRotationPoint(-0.32247F, 2.80846F, -0.51372F);
		RWing1.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWing1.obj")));
		
		RWing2 = new PixelmonModelRenderer(this, 0, 0);
		RWing2.setRotationPoint(-0.79549F, .944F, -.21F);
		RWing2.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWing2.obj")));

		RWing3 = new PixelmonModelRenderer(this, 0, 0);
		RWing3.setRotationPoint(-1.1F, .79F, -.23F);
		RWing3.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWing3.obj")));

		RWing4 = new PixelmonModelRenderer(this, 0, 0);
		RWing4.setRotationPoint(-.78F, .447F, -.189F);
		RWing4.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWing4.obj")));

		LWing1 = new PixelmonModelRenderer(this, 0, 0);
		LWing1.setRotationPoint(0.32247F, 2.80846F, -0.51372F);
		LWing1.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWing1.obj")));

		LWing2 = new PixelmonModelRenderer(this, 0, 0);
		LWing2.setRotationPoint(0.73F, 1F, -.1F);
		LWing2.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWing2.obj")));

		LWing3 = new PixelmonModelRenderer(this, 0, 0);
		LWing3.setRotationPoint(1.1F, .79F, -.23F);
		LWing3.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWing3.obj")));

		LWing4 = new PixelmonModelRenderer(this, 0, 0);
		LWing4.setRotationPoint(.83F, .447F, -.189F);
		LWing4.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWing4.obj")));

		Body.addChild(LWing1);
		LWing1.addChild(LWing2);
		LWing2.addChild(LWing3);
		LWing3.addChild(LWing4);
		Body.addChild(RWing1);
		RWing1.addChild(RWing2);
		RWing2.addChild(RWing3);
		RWing3.addChild(RWing4);
		
		float initialRotation = 40;
				
		ModelRenderer[] LwingArgs = { LWing1, LWing2, LWing3, LWing4 };
		ModelRenderer[] RwingArgs = { RWing1, RWing2, RWing3, RWing4 };
		
		ModuleWingSegmented LWingModule = new ModuleWingSegmented(Body,
				EnumWing.Left, 65, 1.6F, initialRotation, 1.4F, .15F, 2.5F, LwingArgs);
		ModuleWingSegmented RWingModule = new ModuleWingSegmented(Body,
				EnumWing.Right, 65, 1.6F, initialRotation, 1.4F, .15F, 2.5F, RwingArgs);
		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);
//		float legspeed = 0.5F;
//		float legRotationLimit = 0.8F;

		skeleton = new SkeletonBird(Body, null, LWingModule, RWingModule, null, null);
		scale = 5;
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