package pixelmon.client.models.pokemon;

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
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.serpent.SkeletonSerpent;

public class ModelRayquaza extends PixelmonModelBase {
	PixelmonModelRenderer Body, Head, LArm, RArm, Seg1, Seg2, Seg3, Seg4, Seg5C, Seg6, Seg7, Seg8, Seg9, Seg10C, Seg11, Seg12, Seg13, Seg14, Seg15C, Seg16,
			Seg17, Seg18, Seg19, Seg20C, Seg21, Seg22, Seg23, Seg24, Seg25Tail;

	public ModelRayquaza() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0F, 10F, -20);
		Head = new PixelmonModelRenderer(this, 0, 0);
		Head.setRotationPoint(0, 1.169F, 36.547F);
		Head.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Head.obj")));
		LArm = new PixelmonModelRenderer(this, 0, 0);
		LArm.setRotationPoint(4F, 0, 17.76F);
		LArm.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/LeftArm.obj")));
		RArm = new PixelmonModelRenderer(this, 0, 0);
		RArm.setRotationPoint(-4F, 0F, 17.76F);
		RArm.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RightArm.obj")));

		Seg1 = new PixelmonModelRenderer(this, 0, 0);
		Seg1.setRotationPoint(0F, 1.2F, 34.17F);
		Seg1.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg1.obj")));
		Seg2 = new PixelmonModelRenderer(this, 0, 0);
		Seg2.setRotationPoint(0F, 1.2F, 31.43F);
		Seg2.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg2.obj")));
		Seg3 = new PixelmonModelRenderer(this, 0, 0);
		Seg3.setRotationPoint(0F, 1.2F, 28.465F);
		Seg3.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg3.obj")));
		Seg4 = new PixelmonModelRenderer(this, 0, 0);
		Seg4.setRotationPoint(0F, 1.2F, 25.24F);
		Seg4.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg4.obj")));
		Seg5C = new PixelmonModelRenderer(this, 0, 0);
		Seg5C.setRotationPoint(0, 0, 16.10F);
		Seg5C.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg5C.obj")));
		Seg6 = new PixelmonModelRenderer(this, 0, 0);
		Seg6.setRotationPoint(0, 1.2F, 7F);
		Seg6.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg6.obj")));
		Seg7 = new PixelmonModelRenderer(this, 0, 0);
		Seg7.setRotationPoint(0, 1.2F, 3.147F);
		Seg7.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg7.obj")));
		Seg8 = new PixelmonModelRenderer(this, 0, 0);
		Seg8.setRotationPoint(0, 1.2F, 0.647F);
		Seg8.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg8.obj")));
		Seg9 = new PixelmonModelRenderer(this, 0, 0);
		Seg9.setRotationPoint(0, 1.2F, -3.25F);
		Seg9.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg9.obj")));
		Seg10C = new PixelmonModelRenderer(this, 0, 0);
		Seg10C.setRotationPoint(0, 1.2F, -12.25F);
		Seg10C.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg10C.obj")));
		Seg11 = new PixelmonModelRenderer(this, 0, 0);
		Seg11.setRotationPoint(0F, 1.2F, -21.93F);
		Seg11.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg11.obj")));
		Seg12 = new PixelmonModelRenderer(this, 0, 0);
		Seg12.setRotationPoint(0F, 1.2F, -26.04F);
		Seg12.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg12.obj")));
		Seg13 = new PixelmonModelRenderer(this, 0, 0);
		Seg13.setRotationPoint(0F, 1.2F, -29.849F);
		Seg13.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg13.obj")));
		Seg14 = new PixelmonModelRenderer(this, 0, 0);
		Seg14.setRotationPoint(0F, 1.2F, -33.955F);
		Seg14.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg14.obj")));
		Seg15C = new PixelmonModelRenderer(this, 0, 0);
		Seg15C.setRotationPoint(0F, 1.2F, -42.5F);
		Seg15C.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg15C.obj")));
		Seg16 = new PixelmonModelRenderer(this, 0, 0);
		Seg16.setRotationPoint(0F, 1.2F, -51.312F);
		Seg16.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg16.obj")));
		Seg17 = new PixelmonModelRenderer(this, 0, 0);
		Seg17.setRotationPoint(0F, 1.2F, -55.485F);
		Seg17.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg17.obj")));
		Seg18 = new PixelmonModelRenderer(this, 0, 0);
		Seg18.setRotationPoint(0F, 1.2F, -59.145F);
		Seg18.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg18.obj")));
		Seg19 = new PixelmonModelRenderer(this, 0, 0);
		Seg19.setRotationPoint(0F, 1.2F, -63.06F);
		Seg19.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg19.obj")));
		Seg20C = new PixelmonModelRenderer(this, 0, 0);
		Seg20C.setRotationPoint(0F, 1.2F, -71.56F);
		Seg20C.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg20C.obj")));
		Seg21 = new PixelmonModelRenderer(this, 0, 0);
		Seg21.setRotationPoint(0F, 1.2F, -81.06F);
		Seg21.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg21.obj")));
		Seg22 = new PixelmonModelRenderer(this, 0, 0);
		Seg22.setRotationPoint(0F, 1.2F, -85.26F);
		Seg22.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg22.obj")));
		Seg23 = new PixelmonModelRenderer(this, 0, 0);
		Seg23.setRotationPoint(0F, 1.2F, -90.625F);
		Seg23.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg23.obj")));
		Seg24 = new PixelmonModelRenderer(this, 0, 0);
		Seg24.setRotationPoint(0F, 1.2F, -94.35F);
		Seg24.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg24.obj")));
		Seg25Tail = new PixelmonModelRenderer(this, 0, 0);
		Seg25Tail.setRotationPoint(0F, 1.2F, -96.03F);
		Seg25Tail.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Seg25Tail.obj")));

		Body.addChild(Seg1);
		Body.addChild(Seg2);
		Body.addChild(Seg3);
		Body.addChild(Seg4);
		Body.addChild(Seg5C);
		Body.addChild(Seg6);
		Body.addChild(Seg7);
		Body.addChild(Seg8);
		Body.addChild(Seg9);
		Body.addChild(Seg10C);
		Body.addChild(Seg11);
		Body.addChild(Seg12);
		Body.addChild(Seg13);
		Body.addChild(Seg14);
		Body.addChild(Seg15C);
		Body.addChild(Seg16);
		Body.addChild(Seg17);
		Body.addChild(Seg18);
		Body.addChild(Seg19);
		Body.addChild(Seg20C);
		Body.addChild(Seg21);
		Body.addChild(Seg22);
		Body.addChild(Seg23);
		Body.addChild(Seg24);
		Body.addChild(Seg25Tail);
		Body.addChild(Head);
		Body.addChild(LArm);
		Body.addChild(RArm);
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
		Body.rotateAngleX = 15.7F;
	}
}
