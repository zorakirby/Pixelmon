// Date: 4/29/2013 4:31:52 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
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

public class ModelTreecko extends PixelmonModelBase {
	// fields

	PixelmonModelRenderer Body, head, Larm, Rarm, Lleg, RLeg, tail;

	public ModelTreecko() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader
				.loadModel("/pixelmon/client/models/objFiles/treecko/Body.obj")));

		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0F, 8F, -0.42F);
		head.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader
				.loadModel("/pixelmon/client/models/objFiles/treecko/Head.obj")));

		Larm = new PixelmonModelRenderer(this, 0, 0);
		Larm.setRotationPoint(0.539F, 7.64F, -0.17F);
		Larm.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/treecko/LeftArm.obj")));

		Rarm = new PixelmonModelRenderer(this, 0, 0);
		Rarm.setRotationPoint(-0.539F, 7.64F, -0.17F);
		Rarm.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/treecko/RightArm.obj")));

		Lleg = new PixelmonModelRenderer(this, 0, 0);
		Lleg.setRotationPoint(0.81F, 3.2F, -0.4F);
		Lleg.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/treecko/Leftleg.obj")));

		RLeg = new PixelmonModelRenderer(this, 0, 0);
		RLeg.setRotationPoint(-0.81F, 3.2F, -0.4F);
		RLeg.addOBJModel(new ModelOBJWrapper(
				AdvancedModelLoader
						.loadModel("/pixelmon/client/models/objFiles/treecko/RightLeg.obj")));

		tail = new PixelmonModelRenderer(this, 0, 0);
		tail.setRotationPoint(0F, 3.827F, 0.56F);
		tail.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader
				.loadModel("/pixelmon/client/models/objFiles/treecko/Tail.obj")));

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
		float legRotationLimit = 0.9F;

		ModuleHead headModule = new ModuleHead(head);
		ModuleArm leftArm = new ModuleArm(Larm, EnumArm.Left, EnumRotation.x, 0.3F,
				legspeed);
		ModuleArm rightArm = new ModuleArm(Rarm, EnumArm.Right, EnumRotation.x, 0.3F,
				legspeed);
		ModuleLeg leftLeg = new ModuleLeg(Lleg, EnumLeg.FrontLeft,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLeg = new ModuleLeg(RLeg, EnumLeg.FrontRight,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleTailBasic tailmodule = new ModuleTailBasic(tail, .2F, .05F,
				legspeed);

		skeleton = new SkeletonBiped(Body, headModule, leftArm, rightArm,
				leftLeg, rightLeg, tailmodule);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Body.render(f5);
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y,
			float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5) {
	}
}
