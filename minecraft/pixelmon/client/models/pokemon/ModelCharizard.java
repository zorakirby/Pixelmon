package pixelmon.client.models.pokemon;

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
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTail;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelCharizard extends PixelmonModelBase {

	PixelmonModelRenderer Body, head, Larm, Rarm, Lleg, RLeg, tail, LWing, RWing;

	public ModelCharizard() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/Body.obj")));

		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0, 5F, 0.74675F);
		head.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/Head.obj")));

		Larm = new PixelmonModelRenderer(this, 0, 0);
		Larm.setRotationPoint(0.573F, 3.146F, -0.045F);
		Larm.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/LeftArm.obj")));

		Rarm = new PixelmonModelRenderer(this, 0, 0);
		Rarm.setRotationPoint(-0.573F, 3.146F, -0.045F);
		Rarm.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/RightArm.obj")));

		Lleg = new PixelmonModelRenderer(this, 0, 0);
		Lleg.setRotationPoint(0.762F, 2.07F, -0.146F);
		Lleg.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/LeftLeg.obj")));

		RLeg = new PixelmonModelRenderer(this, 0, 0);
		RLeg.setRotationPoint(-0.762F, 2.07F, -0.146F);
		RLeg.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/RightLeg.obj")));

		tail = new PixelmonModelRenderer(this, 0, 0);
		tail.setRotationPoint(0F, 1.267F, -0.072F);
		tail.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/Tail.obj")));

		RWing = new PixelmonModelRenderer(this, 0, 0);
		RWing.setRotationPoint(-0.318F, 3.23F, -0.501F);
		RWing.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/RightWing.obj")));

		LWing = new PixelmonModelRenderer(this, 0, 0);
		LWing.setRotationPoint(0.318F, 3.23F, -0.501F);
		LWing.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/LeftWing.obj")));

		Body.addChild(head);
		Body.addChild(Larm);
		Body.addChild(Rarm);
		Body.addChild(Lleg);
		Body.addChild(RLeg);
		Body.addChild(tail);
		Body.addChild(LWing);
		Body.addChild(RWing);

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);
		float legspeed = 0.5F;
		float legRotationLimit = 0.8F;

		ModuleHead headModule = new ModuleHead(head);
		ModuleArm leftArm = new ModuleArm(Larm, EnumArm.Left, EnumRotation.x, 0.8F, legspeed);
		ModuleArm rightArm = new ModuleArm(Rarm, EnumArm.Right, EnumRotation.x, 0.8F, legspeed);
		ModuleLeg leftLeg = new ModuleLeg(Lleg, EnumLeg.FrontLeft, EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLeg = new ModuleLeg(RLeg, EnumLeg.FrontRight, EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleTailBasic tailmodule = new ModuleTailBasic(tail, .2F, .05F, legspeed);

		skeleton = new SkeletonBiped(Body, headModule, leftArm, rightArm, leftLeg, rightLeg, tailmodule);
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