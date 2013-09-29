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
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTail;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelCharizard extends PixelmonModelBase {

	PixelmonModelRenderer Body, RWing, LWing;

	public ModelCharizard() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 22, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/Body.obj")));

		LWing = new PixelmonModelRenderer(this, 0, 0);
		LWing.setRotationPoint(0.32F, 2.813F, -0.495F);
		LWing.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/LeftWing.obj")));

		RWing = new PixelmonModelRenderer(this, 0, 0);
		RWing.setRotationPoint(-0.32F, 2.813F, -0.495F);
		RWing.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/charizard/flying/RightWing.obj")));
		Body.addChild(LWing);
		Body.addChild(RWing);
		
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
		RWing.rotateAngleY = MathHelper.cos((float) Math.toRadians(35)) * 1 * MathHelper.cos(f2 * .2F) * (float) Math.PI * .25F;
		RWing.rotateAngleZ = MathHelper.sin((float) Math.toRadians(35)) * 1 * MathHelper.cos(f2 * .2F) * (float) Math.PI * .25F;
		LWing.rotateAngleY = MathHelper.cos((float) Math.toRadians(35)) * -1 * MathHelper.cos(f2 * .2F) * (float) Math.PI * .25F;
		LWing.rotateAngleZ = MathHelper.sin((float) Math.toRadians(35)) * -1 * MathHelper.cos(f2 * .2F) * (float) Math.PI * .25F;
	}
}
