package pixelmon.client.models.pokemon.flyingModels;

import net.minecraft.client.model.ModelRenderer;
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
import pixelmon.client.models.animations.ModuleTail;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelFlyingCharizard extends PixelmonModelBase {

	PixelmonModelRenderer Body, LSeg1, LSeg2, LSeg3, LSeg4, RSeg1, RSeg2, RSeg3, RSeg4;

	public ModelFlyingCharizard() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 22, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/Body.obj")));

		LSeg1 = new PixelmonModelRenderer(this, 0, 0);
		LSeg1.setRotationPoint(0.309F, 2.848F, -0.454F);
		LSeg1.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWingSeg1.obj")));

		LSeg2 = new PixelmonModelRenderer(this, 0, 0);
		LSeg2.setRotationPoint(0.75F, 0.85F, -0.3F);
		LSeg2.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWingSeg2.obj")));
		
		LSeg3 = new PixelmonModelRenderer(this, 0, 0);
		LSeg3.setRotationPoint(1.1F, 0.7F, -0.41F);
		LSeg3.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWingSeg3.obj")));

		LSeg4 = new PixelmonModelRenderer(this, 0, 0);
		LSeg4.setRotationPoint(0.84F, 0.5F, -.13F);
		LSeg4.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWingSeg4.obj")));

		Body.addChild(LSeg1);
		LSeg1.addChild(LSeg2);
		LSeg2.addChild(LSeg3);
		LSeg3.addChild(LSeg4);
		
		RSeg1 = new PixelmonModelRenderer(this, 0, 0);
		RSeg1.setRotationPoint(-0.309F, 2.848F, -0.454F);
		RSeg1.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWingSeg1.obj")));

		
		RSeg2 = new PixelmonModelRenderer(this, 0, 0);
		RSeg2.setRotationPoint(-0.75F, 0.85F, -0.3F);
		RSeg2.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWingSeg2.obj")));

		RSeg3 = new PixelmonModelRenderer(this, 0, 0);
		RSeg3.setRotationPoint(-1.1F, 0.75F, -0.41F);
		RSeg3.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWingSeg3.obj")));

		RSeg4 = new PixelmonModelRenderer(this, 0, 0);
		RSeg4.setRotationPoint(-0.86F, 0.5F, -.13F);
		RSeg4.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWingSeg4.obj")));

		Body.addChild(RSeg1);
		RSeg1.addChild(RSeg2);
		RSeg2.addChild(RSeg3);
		RSeg3.addChild(RSeg4);
		
		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);
		float legspeed = 0.5F;
		float legRotationLimit = 0.8F;

		skeleton = new SkeletonBase(Body);
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
