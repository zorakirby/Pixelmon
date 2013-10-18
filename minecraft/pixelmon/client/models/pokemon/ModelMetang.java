package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelOBJWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;

public class ModelMetang extends PixelmonModelBase {

	PixelmonModelRenderer Body, LeftArm, LeftHand, RightArm, RightHand;

	public ModelMetang() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 20F, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/Metang/Body.obj")));
		LeftArm = new PixelmonModelRenderer(this, 0, 0);
		LeftArm.setRotationPoint(2.734F, 3.658F, 1.274F);
		LeftArm.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/Metang/LeftArm.obj")));
		RightArm = new PixelmonModelRenderer(this, 0, 0);
		RightArm.setRotationPoint(-2.734F, 3.658F, 1.274F);
		RightArm.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/Metang/RightArm.obj")));
		LeftHand = new PixelmonModelRenderer(this, 0, 0);
		LeftHand.setRotationPoint(4.415F, 4.040F, 1.346F);
		LeftHand.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/Metang/LeftHand.obj")));
		RightHand = new PixelmonModelRenderer(this, 0, 0);
		RightHand.setRotationPoint(-4.415f, 4.040f, 1.346f);
		RightHand.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/Metang/RightHand.obj")));
		Body.addChild(LeftArm);
		Body.addChild(RightArm);
		Body.addChild(LeftHand);
		Body.addChild(RightHand);

		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);

		skeleton = new SkeletonQuadruped(Body, null, null, null, null, null, null);
		scale=2f;
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