package pixelmon.client.models.pokemon;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
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
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.serpent.SkeletonSerpent;
import pixelmon.client.models.animations.serpent.SkeletonSerpentFloating;


public class ModelRayquaza extends PixelmonModelBase {
	PixelmonModelRenderer Body, Head, LArm, RArm, RayBody, RayBody2, RayBody3, RayBody4, Ray1, Ray2, Ray3, Ray4, Ray5, Ray6, Ray7, Ray8, Ray9, Ray10, Ray11, Ray12, Ray13, Ray14, Ray15, Ray16,
			Ray17, Ray18, Ray19, Ray20, Ray21, RayTail;

	float animationSpeed = (float) 1/7;

	public ModelRayquaza() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0F, 0F, 0);
		Head = new PixelmonModelRenderer(this, 0, 0);
		Head.setRotationPoint(0, 0F, 0F);
		Head.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RayHead.obj")));
		LArm = new PixelmonModelRenderer(this, 0, 0);
		LArm.setRotationPoint(4.397F, -1.028F, -6.080F);
		LArm.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RayLeftArm.obj")));
		RArm = new PixelmonModelRenderer(this, 0, 0);
		RArm.setRotationPoint(-3.569F, -1.028F, -6.080F);
		RArm.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RayRightArm.obj")));
		Body.addChild(Head);
		
		
		Ray1 = new PixelmonModelRenderer(this, 0, 0);
		Ray1.setRotationPoint(0, 0F, 0F);
		Ray1.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray1.obj")));
		Ray2 = new PixelmonModelRenderer(this, 0, 0);
		Ray2.setRotationPoint(0, 0F, -0.963F);
		Ray2.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray2.obj")));
		Ray3 = new PixelmonModelRenderer(this, 0, 0);
		Ray3.setRotationPoint(0, 0F, -2.686F);
		Ray3.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray3.obj")));
		Ray4 = new PixelmonModelRenderer(this, 0, 0);
		Ray4.setRotationPoint(0, 0F, -2.803F);
		Ray4.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray4.obj")));
		Ray5 = new PixelmonModelRenderer(this, 0, 0);
		Ray5.setRotationPoint(0, 0F, -3.146F);
		Ray5.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray5.obj")));
		Ray6= new PixelmonModelRenderer(this, 0, 0);
		Ray6.setRotationPoint(0, 0F, -14.848F);
		Ray6.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray6.obj")));
		Ray7 = new PixelmonModelRenderer(this, 0, 0);
		Ray7.setRotationPoint(0, 0F, -3.962F);
		Ray7.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray7.obj")));
		Ray8 = new PixelmonModelRenderer(this, 0, 0);
		Ray8.setRotationPoint(0, 0F, -3.858F);
		Ray8.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray8.obj")));
		Ray9 = new PixelmonModelRenderer(this, 0, 0);
		Ray9.setRotationPoint(0, 0F, -3.748F);
		Ray9.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray9.obj")));
		Ray10 = new PixelmonModelRenderer(this, 0, 0);
		Ray10.setRotationPoint(0, 0F, -14.631F);
		Ray10.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray10.obj")));
		Ray11 = new PixelmonModelRenderer(this, 0, 0);
		Ray11.setRotationPoint(0, 0F, -4.519F);
		Ray11.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray11.obj")));
		Ray12 = new PixelmonModelRenderer(this, 0, 0);
		Ray12.setRotationPoint(0, 0F, -3.916F);
		Ray12.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray12.obj")));
		Ray13 = new PixelmonModelRenderer(this, 0, 0);
		Ray13.setRotationPoint(0, 0F, -3.662F);
		Ray13.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray13.obj")));
		Ray14 = new PixelmonModelRenderer(this, 0, 0);
		Ray14.setRotationPoint(0, 0F, -14.164F);
		Ray14.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray14.obj")));
		Ray15 = new PixelmonModelRenderer(this, 0, 0);
		Ray15.setRotationPoint(0, 0F, -4.272F);
		Ray15.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray15.obj")));
		Ray16 = new PixelmonModelRenderer(this, 0, 0);
		Ray16.setRotationPoint(0, 0F, -3.929F);
		Ray16.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray16.obj")));
		Ray17 = new PixelmonModelRenderer(this, 0, 0);
		Ray17.setRotationPoint(0, 0F, -3.604F);
		Ray17.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray17.obj")));
		Ray18 = new PixelmonModelRenderer(this, 0, 0);
		Ray18.setRotationPoint(0, 0F, -12.677F);
		Ray18.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray18.obj")));
		Ray19 = new PixelmonModelRenderer(this, 0, 0);
		Ray19.setRotationPoint(0, 0F, -5.607F);
		Ray19.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray19.obj")));
		Ray20 = new PixelmonModelRenderer(this, 0, 0);
		Ray20.setRotationPoint(0, 0F, -4.846F);
		Ray20.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray20.obj")));
		Ray21 = new PixelmonModelRenderer(this, 0, 0);
		Ray21.setRotationPoint(0, 0F, -6.011F);
		Ray21.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/Ray21.obj")));
		RayBody = new PixelmonModelRenderer(this, 0, 0);
		RayBody.setRotationPoint(0, 0F, -3.036F);
		RayBody.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RayBody.obj")));
		RayBody2 = new PixelmonModelRenderer(this, 0, 0);
		RayBody2.setRotationPoint(0, 0F, -3.457F);
		RayBody2.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RayBody2.obj")));
		RayBody3 = new PixelmonModelRenderer(this, 0, 0);
		RayBody3.setRotationPoint(0, 0F, -3.246F);
		RayBody3.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RayBody3.obj")));
		RayBody4 = new PixelmonModelRenderer(this, 0, 0);
		RayBody4.setRotationPoint(0, 0F, -3.586F);
		RayBody4.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RayBody4.obj")));
		RayTail = new PixelmonModelRenderer(this, 0, 0);
		RayTail.setRotationPoint(0, 0F, -3.325F);
		RayTail.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/rayquaza/RayTail.obj")));

		
		Body.addChild(Ray1);
		Ray1.addChild(Ray2);
		Ray2.addChild(Ray3);
		Ray3.addChild(Ray4);
		Ray4.addChild(Ray5);
		Ray5.addChild(RayBody);
		RayBody.addChild(LArm);
		RayBody.addChild(RArm);
		RayBody.addChild(Ray6);
		Ray6.addChild(Ray7);
		Ray7.addChild(Ray8);
		Ray8.addChild(Ray9);
		Ray9.addChild(RayBody2);
		RayBody2.addChild(Ray10);
		Ray10.addChild(Ray11);
		Ray11.addChild(Ray12);
		Ray12.addChild(Ray13);
		Ray13.addChild(RayBody3);
		RayBody3.addChild(Ray14);
		Ray14.addChild(Ray15);
		Ray15.addChild(Ray16);
		Ray16.addChild(Ray17);
		Ray17.addChild(RayBody4);
		RayBody4.addChild(Ray18);
		Ray18.addChild(Ray19);
		Ray19.addChild(Ray20);
		Ray20.addChild(Ray21);
		Ray21.addChild(RayTail);
		
		
		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);

		setRotation(Body, radians, 0, 0);
		
		ModuleHead headModule = new ModuleHead(Head);
		ModelRenderer[] bodyArgs = { RayBody, RayBody2, RayBody3, RayBody4, Ray1, Ray2, Ray3, Ray4, Ray5, Ray6, Ray7, Ray8, Ray9, Ray10, Ray11, Ray12, Ray13, Ray14, Ray15, Ray16,
				Ray17, Ray18, Ray19, Ray20, Ray21, RayTail };
		
		float animationAngle = 90;
		float topAngle = (float) (Math.PI/15);
		float dampeningFactor = (float) -0.001;
		float phaseoffset = (float) -0.3;		
		
		skeleton = new SkeletonSerpentFloating(Body, headModule, animationAngle, topAngle, dampeningFactor, animationSpeed,phaseoffset, bodyArgs);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Body.render(f5);
		Body.setRotationPoint(0F, -5*MathHelper.cos(f2*animationSpeed), 0F);
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
	}
}
