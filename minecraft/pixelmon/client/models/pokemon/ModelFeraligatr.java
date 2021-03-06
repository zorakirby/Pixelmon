package pixelmon.client.models.pokemon;

import net.minecraft.client.model.ModelRenderer;
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
import pixelmon.client.models.animations.ModuleTail;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelFeraligatr extends PixelmonModelBase {
	// fields

	PixelmonModelRenderer Body, head, Larm, Rarm, Lleg, RLeg, tail;

	public ModelFeraligatr() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24, 0);
		Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/feraligatr/Body.obj")));
		
		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0, 5.05F, 1.57F);
		head.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/feraligatr/Head.obj")));

		Larm = new PixelmonModelRenderer(this, 0, 0);
		Larm.setRotationPoint(0.81F, 4.6F, -0.51F);
		Larm.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/feraligatr/LeftArm.obj")));

		Rarm = new PixelmonModelRenderer(this, 0, 0);
		Rarm.setRotationPoint(-1F, 4.47F, -0.09F);
		Rarm.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/feraligatr/RightArm.obj")));

		Lleg = new PixelmonModelRenderer(this, 0, 0);
		Lleg.setRotationPoint(0.9F,  2.3F, 0.2F);
		Lleg.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/feraligatr/LeftLeg.obj")));

		RLeg = new PixelmonModelRenderer(this, 0, 0);
		RLeg.setRotationPoint(-0.9F, 2.3F, 0.20F);
		RLeg.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/feraligatr/RightLeg.obj")));

		tail = new PixelmonModelRenderer(this, 0, 0);
		tail.setRotationPoint(0, 1.458F, 0.377F);
		tail.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/feraligatr/Tail.obj")));

		Body.addChild(head);
		Body.addChild(Larm);
		Body.addChild(Rarm);
		Body.addChild(Lleg);
		Body.addChild(RLeg);
		Body.addChild(tail);

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);
		float legspeed = 0.5F;
		float legRotationLimit = 0.8F;

		ModuleHead headModule = new ModuleHead(head);
		ModuleArm leftArm = new ModuleArm(Larm, EnumArm.Left, EnumRotation.x, 0.3F, legspeed);
		ModuleArm rightArm = new ModuleArm(Rarm, EnumArm.Right, EnumRotation.x, 0.3F, legspeed);
		ModuleLeg leftLeg = new ModuleLeg(Lleg, EnumLeg.FrontLeft, EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLeg = new ModuleLeg(RLeg, EnumLeg.FrontRight, EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleTailBasic tailmodule = new ModuleTailBasic(tail, .2F, .05F, legspeed);
		scale = 7F;
		skeleton = new SkeletonBiped(Body, headModule, leftArm, rightArm, leftLeg, rightLeg, tailmodule);
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
