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
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTail;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelRaichu extends PixelmonModelBase {
	// fields

	PixelmonModelRenderer Body, head, Larm, Rarm, Lleg, RLeg, tail;

	public ModelRaichu() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 24.1f, 0);
		Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/raichu/Body.obj")));

		head = new PixelmonModelRenderer(this, 0, 0);
		head.setRotationPoint(0.02F, 3.72F, -0.105F);
		head.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/raichu/Head.obj")));

		Larm = new PixelmonModelRenderer(this, 0, 0);
		Larm.setRotationPoint(0.775F, 4.19F, -0.528F);
		Larm.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/raichu/LeftArm.obj")));

		Rarm = new PixelmonModelRenderer(this, 0, 0);
		Rarm.setRotationPoint(-0.751F, 4.193F, -0.528F);
		Rarm.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/raichu/RightArm.obj")));

		Lleg = new PixelmonModelRenderer(this, 0, 0);
		Lleg.setRotationPoint(0.84F, 2.728F, -1.347F);
		Lleg.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/raichu/LeftLeg.obj")));

		RLeg = new PixelmonModelRenderer(this, 0, 0);
		RLeg.setRotationPoint(-0.790F, 2.644F, -1.196F);
		RLeg.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/raichu/RightLeg.obj")));

		Body.addChild(head);
		Body.addChild(Larm);
		Body.addChild(Rarm);
		Body.addChild(Lleg);
		Body.addChild(RLeg);

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);
		float legspeed = 0.5F;
		float legRotationLimit = 0.8F;

		ModuleHead headModule = new ModuleHead(head);
		ModuleArm leftArm = new ModuleArm(Larm, EnumArm.Left, 0.2F, 0.3F, legspeed);
		ModuleArm rightArm = new ModuleArm(Rarm, EnumArm.Right, 0.2F, 0.3F, legspeed);
		ModuleLeg leftLeg = new ModuleLeg(Lleg, EnumLeg.FrontLeft, EnumPhase.InPhase, legRotationLimit, legspeed);
		ModuleLeg rightLeg = new ModuleLeg(RLeg, EnumLeg.FrontRight, EnumPhase.InPhase, legRotationLimit, legspeed);

		scale = 3.4f;

		skeleton = new SkeletonBiped(Body, headModule, leftArm, rightArm, leftLeg, rightLeg, null);
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
