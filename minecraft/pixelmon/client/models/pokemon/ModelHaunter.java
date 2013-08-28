// Date: 4/29/2013 4:31:52 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package pixelmon.client.models.pokemon;

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
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelHaunter extends PixelmonModelBase {
	// fields

	PixelmonModelRenderer Body, ArmL, ArmR;

	public ModelHaunter() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 15, -1);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/haunter/Head.obj")));
		ArmL = new PixelmonModelRenderer(this, 0, 0);
		ArmL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/haunter/LeftHand.obj"), 0, 0, 0));
		ArmL.setRotationPoint(8F, 4F, -3F);
		ArmR = new PixelmonModelRenderer(this, 0, 0);
		ArmR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/haunter/RightHand.obj"), 0, 0, 0));
		ArmR.setRotationPoint(-8F, 4F, -3F);
		Body.addChild(ArmL);
		Body.addChild(ArmR);

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);

		float legspeed = 0.5F;
		float legRotationLimit = 1.4F;

		ModuleHead headModule = new ModuleHead(Body);

		ModuleArm leftArmModule = new ModuleArm(ArmL, EnumArm.Left, 0, 1F, legspeed);
		ModuleArm rightArmModule = new ModuleArm(ArmR, EnumArm.Right, 0, 1F, legspeed);

		skeleton = new SkeletonBiped(Body, headModule, leftArmModule, rightArmModule, null, null, null);
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
		Body.rotationPointY = MathHelper.cos(.2F * f2) + 10;
		ArmL.rotationPointY = MathHelper.cos(.4F * f2) * 0.5F - 7.5F;
		ArmR.rotationPointY = MathHelper.cos(.4F * f2) * 0.5F - 7.5F;
	}
}