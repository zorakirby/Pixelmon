package pixelmon.client.models.pokemon;

import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSwinub extends PixelmonModelBase {
	// fields
	PixelmonModelRenderer Body;
	

	public ModelSwinub() {
		
		textureWidth = 128;
		textureHeight = 64;

		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0F, 20F, 0F);
		setRotation(Body, 0F, 0F, 0F);
		Body.mirror = true;

		PixelmonModelRenderer furlayer1 = new PixelmonModelRenderer(this, 0, 8);
		furlayer1.addBox(0F, 0F, 0F, 10, 7, 10);
		furlayer1.setRotationPoint(-5F, -3.3F, -5F);
		furlayer1.setTextureSize(128, 64);
		furlayer1.mirror = true;
		setRotation(furlayer1, 0F, 0F, 0F);
		Body.addChild(furlayer1);
		
		PixelmonModelRenderer furlayer2 = new PixelmonModelRenderer(this, 42, 27);
		furlayer2.addBox(0F, 0F, 0F, 8, 6, 11);
		furlayer2.setRotationPoint(-4F, -2.4F, -5.5F);
		furlayer2.setTextureSize(128, 64);
		furlayer2.mirror = true;
		setRotation(furlayer2, 0F, 0F, 0F);
		Body.addChild(furlayer2);
		
		PixelmonModelRenderer furlayer3 = new PixelmonModelRenderer(this, 0, 28);
		furlayer3.addBox(0F, 0F, 0F, 11, 6, 9);
		furlayer3.setRotationPoint(-5.5F, -2.4F, -4.5F);
		furlayer3.setTextureSize(128, 64);
		furlayer3.mirror = true;
		setRotation(furlayer3, 0F, 0F, 0F);
		Body.addChild(furlayer3);
		
		PixelmonModelRenderer furlayer4 = new PixelmonModelRenderer(this, 42, 8);
		furlayer4.addBox(0F, 0F, 0F, 8, 7, 9);
		furlayer4.setRotationPoint(-4F, -4F, -4.5F);
		furlayer4.setTextureSize(128, 64);
		furlayer4.mirror = true;
		setRotation(furlayer4, 0F, 0F, 0F);
		Body.addChild(furlayer4);
		
		PixelmonModelRenderer snout = new PixelmonModelRenderer(this, 11, 2);
		snout.addBox(0F, 0F, 0F, 3, 2, 3);
		snout.setRotationPoint(-1.5F, 1F, -6.5F);
		snout.setTextureSize(128, 64);
		snout.mirror = true;
		setRotation(snout, 0F, 0F, 0F);
		Body.addChild(snout);
		
		PixelmonModelRenderer eyeLpart1 = new PixelmonModelRenderer(this, 27, 2);
		eyeLpart1.addBox(0F, 0F, 0F, 1, 1, 1);
		eyeLpart1.setRotationPoint(2.7F, -0.5F, -5.6F);
		eyeLpart1.setTextureSize(128, 64);
		eyeLpart1.mirror = true;
		setRotation(eyeLpart1, 0F, 0F, 0F);
		Body.addChild(eyeLpart1);
		
		PixelmonModelRenderer eyeRpart2 = new PixelmonModelRenderer(this, 27, 2);
		eyeRpart2.addBox(0F, 0F, 0F, 1, 1, 1);
		eyeRpart2.setRotationPoint(-3.2F, -0.4F, -5.6F);
		eyeRpart2.setTextureSize(128, 64);
		eyeRpart2.mirror = true;
		setRotation(eyeRpart2, 0F, 0F, -0.3839724F);
		Body.addChild(eyeRpart2);
		
		PixelmonModelRenderer eyeRpart3 = new PixelmonModelRenderer(this, 27, 2);
		eyeRpart3.addBox(1F, -0.2F, 0F, 1, 1, 1);
		eyeRpart3.setRotationPoint(-3.2F, 0F, -5.6F);
		eyeRpart3.setTextureSize(128, 64);
		eyeRpart3.mirror = true;
		setRotation(eyeRpart3, 0F, 0F, -0.5585054F);
		Body.addChild(eyeRpart3);
		
		PixelmonModelRenderer eyeRpart1 = new PixelmonModelRenderer(this, 27, 2);
		eyeRpart1.addBox(0F, 0F, 0F, 1, 1, 1);
		eyeRpart1.setRotationPoint(-3.8F, -0.5F, -5.6F);
		eyeRpart1.setTextureSize(128, 64);
		eyeRpart1.mirror = true;
		setRotation(eyeRpart1, 0F, 0F, 0F);
		Body.addChild(eyeRpart1);
		
		PixelmonModelRenderer eyeLpart2 = new PixelmonModelRenderer(this, 27, 2);
		eyeLpart2.addBox(0F, 0F, 0F, 1, 1, 1);
		eyeLpart2.setRotationPoint(2.1F, -0.8F, -5.6F);
		eyeLpart2.setTextureSize(128, 64);
		eyeLpart2.mirror = true;
		setRotation(eyeLpart2, 0F, 0F, 0.3839724F);
		Body.addChild(eyeLpart2);
		
		PixelmonModelRenderer eyeLpart3 = new PixelmonModelRenderer(this, 27, 2);
		eyeLpart3.addBox(0.2F, 0.8F, 0F, 1, 1, 1);
		eyeLpart3.setRotationPoint(1.7F, -2F, -5.6F);
		eyeLpart3.setTextureSize(128, 64);
		eyeLpart3.mirror = true;
		setRotation(eyeLpart3, 0F, 0F, 0.5585054F);
		Body.addChild(eyeLpart3);
		
		PixelmonModelRenderer fur1 = new PixelmonModelRenderer(this, 33, 2);
		fur1.addBox(0F, 0F, 0F, 1, 1, 1);
		fur1.setRotationPoint(2F, 2.7F, 5F);
		fur1.setTextureSize(128, 64);
		fur1.mirror = true;
		setRotation(fur1, 0F, 0F, 0F);
		Body.addChild(fur1);
		
		PixelmonModelRenderer fur2 = new PixelmonModelRenderer(this, 33, 2);
		fur2.addBox(0F, 0F, 0F, 1, 1, 1);
		fur2.setRotationPoint(-3F, 2.7F, 5F);
		fur2.setTextureSize(128, 64);
		fur2.mirror = true;
		setRotation(fur2, 0F, 0F, 0F);
		Body.addChild(fur2);
		
		PixelmonModelRenderer fur3 = new PixelmonModelRenderer(this, 39, 2);
		fur3.addBox(0F, 0F, 0F, 2, 1, 1);
		fur3.setRotationPoint(-1F, 2.7F, 5F);
		fur3.setTextureSize(128, 64);
		fur3.mirror = true;
		setRotation(fur3, 0F, 0F, 0F);
		Body.addChild(fur3);
		
		PixelmonModelRenderer tail = new PixelmonModelRenderer(this, 39, 2);
		tail.addBox(0F, 0F, 0F, 1, 1, 1);
		tail.setRotationPoint(-0.5F, 1F, 5.3F);
		tail.setTextureSize(128, 64);
		tail.mirror = true;
		setRotation(tail, 0F, 0F, 0F);
		Body.addChild(tail);
		
		PixelmonModelRenderer LegLF = new PixelmonModelRenderer(this, "LegLF");
		LegLF.setRotationPoint(2.5F, 3F, -2F);
		setRotation(LegLF, 0F, 0F, 0F);
		LegLF.mirror = true;
		PixelmonModelRenderer foot1 = new PixelmonModelRenderer(this, 0, 5);
		foot1.addBox(0F, 0F, 0F, 3, 1, 2);
		foot1.setRotationPoint(-1.5F, 0F, -1F);
		foot1.setTextureSize(128, 64);
		foot1.mirror = true;
		setRotation(foot1, 0F, 0F, 0F);
		PixelmonModelRenderer toe1 = new PixelmonModelRenderer(this, 0, 0);
		toe1.addBox(0F, 0F, 0F, 1, 1, 3);
		toe1.setRotationPoint(-0.5F, 0F, -2F);
		toe1.setTextureSize(128, 64);
		toe1.mirror = true;
		setRotation(toe1, 0F, 0F, 0F);

		LegLF.addChild(foot1);
		LegLF.addChild(toe1);
		Body.addChild(LegLF);

		PixelmonModelRenderer LegRF = new PixelmonModelRenderer(this, "LegRF");
		LegRF.setRotationPoint(-2.5F, 3F, -2F);
		setRotation(LegRF, 0F, 0F, 0F);
		LegRF.mirror = true;
		PixelmonModelRenderer foot2 = new PixelmonModelRenderer(this, 0, 5);
		foot2.addBox(0F, 0F, 0F, 3, 1, 2);
		foot2.setRotationPoint(-1.5F, 0F, -1F);
		foot2.setTextureSize(128, 64);
		foot2.mirror = true;
		setRotation(foot2, 0F, 0F, 0F);
		PixelmonModelRenderer toe2 = new PixelmonModelRenderer(this, 0, 0);
		toe2.addBox(0F, 0F, 0F, 1, 1, 3);
		toe2.setRotationPoint(-0.5F, 0F, -2F);
		toe2.setTextureSize(128, 64);
		toe2.mirror = true;
		setRotation(toe2, 0F, 0F, 0F);

		LegRF.addChild(foot2);
		LegRF.addChild(toe2);
		Body.addChild(LegRF);

		PixelmonModelRenderer LegLB = new PixelmonModelRenderer(this, "LegLB");
		LegLB.setRotationPoint(2.5F, 3F, 3F);
		setRotation(LegLB, 0F, 0F, 0F);
		LegLB.mirror = true;
		PixelmonModelRenderer foot3 = new PixelmonModelRenderer(this, 0, 5);
		foot3.addBox(0F, 0F, 0F, 3, 1, 2);
		foot3.setRotationPoint(-1.5F, 0F, -1F);
		foot3.setTextureSize(128, 64);
		foot3.mirror = true;
		setRotation(foot3, 0F, 0F, 0F);
		PixelmonModelRenderer toe3 = new PixelmonModelRenderer(this, 0, 0);
		toe3.addBox(0F, 0F, 0F, 1, 1, 3);
		toe3.setRotationPoint(-0.5F, 0F, -2F);
		toe3.setTextureSize(128, 64);
		toe3.mirror = true;
		setRotation(toe3, 0F, 0F, 0F);

		LegLB.addChild(foot3);
		LegLB.addChild(toe3);
		Body.addChild(LegLB);

		PixelmonModelRenderer LegRB = new PixelmonModelRenderer(this, "LegRB");
		LegRB.setRotationPoint(-2.5F, 3F, 3F);
		setRotation(LegRB, 0F, 0F, 0F);
		LegRB.mirror = true;
		PixelmonModelRenderer foot4 = new PixelmonModelRenderer(this, 0, 5);
		foot4.addBox(0F, 0F, 0F, 3, 1, 2);
		foot4.setRotationPoint(-1.5F, 0F, -1F);
		foot4.setTextureSize(128, 64);
		foot4.mirror = true;
		setRotation(foot4, 0F, 0F, 0F);
		PixelmonModelRenderer toe4 = new PixelmonModelRenderer(this, 0, 0);
		toe4.addBox(0F, 0F, 0F, 1, 1, 3);
		toe4.setRotationPoint(-0.5F, 0F, -2F);
		toe4.setTextureSize(128, 64);
		toe4.mirror = true;
		setRotation(toe4, 0F, 0F, 0F);

		LegRB.addChild(foot4);
		LegRB.addChild(toe4);
		Body.addChild(LegRB);

		//<=====ASSEMBLE THE SKELETON=====>
		 ModuleHead headModule = new ModuleHead(Body);//He's all head!
		 
		 //front feet at the same time
		 ModuleLeg frontLeftLegModule = new ModuleLeg(LegLF, EnumLeg.FrontLeft,
				 EnumPhase.InPhase, EnumRotation.x, 0, 0.2F);
		 ModuleLeg frontRightLegModule = new ModuleLeg(LegRF, EnumLeg.FrontRight,
				 EnumPhase.InPhase, EnumRotation.x, 0, 0.2F);
		 ModuleLeg backLeftLegModule = new ModuleLeg(LegLB, EnumLeg.BackLeft,
				 EnumPhase.InPhase, EnumRotation.x, 0, 0.2F);
		 ModuleLeg backRightLegModule = new ModuleLeg(LegRB, EnumLeg.BackRight,
				 EnumPhase.InPhase, EnumRotation.x, 0, 0.2F);
		 ModuleTailBasic tailModule = new ModuleTailBasic(tail, .2F, .05F, .2F);
		 
		 skeleton = new SkeletonQuadruped(Body, headModule,
				 frontLeftLegModule, frontRightLegModule, backLeftLegModule,
				 backRightLegModule, tailModule);
		 
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		
	}
}