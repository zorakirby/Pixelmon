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

public class ModelPrinplup extends PixelmonModelBase {
	// fields

	PixelmonModelRenderer Body, Larm, Rarm, Lleg, RLeg;

	public ModelPrinplup() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 22f, 0);
		Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/prinplup/Body.obj")));
		
		Larm = new PixelmonModelRenderer(this, 0, 0);
		Larm.setRotationPoint(1.31F, 3.228F, -0.03F);
		Larm.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/prinplup/LeftArm.obj")));

		Rarm = new PixelmonModelRenderer(this, 0, 0);
		Rarm.setRotationPoint(-1.31F, 3.228F, -0.03F);
		Rarm.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/prinplup/RightArm.obj")));

		Lleg = new PixelmonModelRenderer(this, 0, 0);
		Lleg.setRotationPoint(1.096F, -0.62F, -0.1F);
		Lleg.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/prinplup/LeftLeg.obj")));

		RLeg = new PixelmonModelRenderer(this, 0, 0);
		RLeg.setRotationPoint(-1.096F, -0.62F, -0.1F);
		RLeg.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/prinplup/RightLeg.obj")));

		Body.addChild(Larm);
		Body.addChild(Rarm);
		Body.addChild(Lleg);
		Body.addChild(RLeg);

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);
		float legspeed = 0.5F;
		float legRotationLimit = 0.8F;

		ModuleArm leftArm = new ModuleArm(Larm, EnumArm.Left, EnumRotation.x, 0.3F, legspeed);
		ModuleArm rightArm = new ModuleArm(Rarm, EnumArm.Right, EnumRotation.x, 0.3F, legspeed);
		ModuleLeg leftLeg = new ModuleLeg(Lleg, EnumLeg.FrontLeft, EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLeg = new ModuleLeg(RLeg, EnumLeg.FrontRight, EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);

		skeleton = new SkeletonBiped(Body, null, leftArm, rightArm, leftLeg, rightLeg, null);
		scale=1.9f;
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
