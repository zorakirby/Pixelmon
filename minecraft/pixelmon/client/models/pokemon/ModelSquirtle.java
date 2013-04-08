package pixelmon.client.models.pokemon;

import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;

import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.Biped.SkeletonBiped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;


public class ModelSquirtle extends PixelmonModelBase {
	public ModelRenderer Body;

	public ModelSquirtle() {

		Body = new ModelRenderer(this, "Body");
		Body.setRotationPoint(0, 14, 0);

		ModelRenderer Shell_Middle = new ModelRenderer(this, 27, 0);
		Shell_Middle.addBox(-4F, 0F, -1F, 8, 7, 2, 0F);
		Shell_Middle.mirror = false;
		Body.addChild(Shell_Middle);

		ModelRenderer Shell_Front = new ModelRenderer(this, 50, 0);
		Shell_Front.addBox(-3F, 1F, -2F, 6, 6, 1, 0F);
		Shell_Front.setRotationPoint(0F, -1F, 0F);
		Shell_Front.mirror = false;
		Body.addChild(Shell_Front);

		ModelRenderer Shell_Back = new ModelRenderer(this, 32, 11);
		Shell_Back.addBox(-3F, 1F, 1F, 6, 6, 2, 0F);
		Shell_Back.setRotationPoint(0F, -1F, 0F);
		Shell_Back.mirror = false;
		Body.addChild(Shell_Back);

		PixelmonModelRenderer HeadPiece = new PixelmonModelRenderer(this,
				"Head");
		HeadPiece.setRotationPoint(0, 0, -1);
		ModelRenderer head = new ModelRenderer(this, 48, 24);
		head.addBox(-2F, -4F, -2F, 4, 4, 4, 0F);
		// head.setRotationPoint(0F, 14F, -1F);
		head.mirror = false;
		HeadPiece.addChild(head);
		Body.addChild(HeadPiece);

		ModelRenderer Arm_Right = new ModelRenderer(this, 0, 26);
		Arm_Right.addBox(-1F, 0F, -1F, 2, 4, 2, 0F);
		Arm_Right.setRotationPoint(-3F, 1F, -1F);
		Arm_Right.rotateAngleX = -0.4363323F;
		Arm_Right.rotateAngleY = 0.3490658F;
		Arm_Right.rotateAngleZ = 0F;
		Arm_Right.mirror = false;
		Body.addChild(Arm_Right);

		ModelRenderer Arm_Left = new ModelRenderer(this, 0, 26);
		Arm_Left.addBox(-1F, 0F, -1F, 2, 4, 2, 0F);
		Arm_Left.setRotationPoint(3F, 1F, -1F);
		Arm_Left.rotateAngleX = -0.4363323F;
		Arm_Left.rotateAngleY = -0.3490658F;
		Arm_Left.rotateAngleZ = 0F;
		Arm_Left.mirror = true;
		Body.addChild(Arm_Left);

		ModelRenderer Leg_Left = new ModelRenderer(this, 8, 26);
		Leg_Left.setRotationPoint(2F, 6F, -1F);
		Leg_Left.addBox(-1F, 0F, -1F, 2, 4, 2, 0F);
		Leg_Left.mirror = true;
		Body.addChild(Leg_Left);

		ModelRenderer Leg_Right = new ModelRenderer(this, 8, 26);
		Leg_Right.addBox(-1F, 0F, -1F, 2, 4, 2, 0F);
		Leg_Right.setRotationPoint(-2F, 6F, -1F);
		Leg_Right.mirror = false;
		Body.addChild(Leg_Right);

		ModelRenderer Tail = new ModelRenderer(this, "Tail");
		Tail.setRotationPoint(0, 6, 1);

		ModelRenderer Tail_Base = new ModelRenderer(this, 0, 12);
		Tail_Base.addBox(-1F, 0F, -1F, 2, 2, 1, 0F);
		// Tail_Base.setRotationPoint(0F, 20F, 1F);
		Tail_Base.rotateAngleX = 0.9424778F;
		Tail_Base.rotateAngleY = 0.02094395F;
		Tail_Base.rotateAngleZ = 0F;
		Tail_Base.mirror = false;
		Tail.addChild(Tail_Base);

		ModelRenderer Tail_Mid = new ModelRenderer(this, 0, 16);
		Tail_Mid.addBox(-1F, 0F, -1F, 2, 3, 2, 0F);
		Tail_Mid.setRotationPoint(0F, 1F, 1F);
		// Tail_Mid.setRotationPoint(0F, 21F, 2F);
		Tail_Mid.rotateAngleX = 1.570796F;
		Tail_Mid.mirror = false;
		Tail_Base.addChild(Tail_Mid);

		ModelRenderer Tail_End = new ModelRenderer(this, 6, 2);
		Tail_End.addBox(-1F, 0F, -1F, 2, 4, 4, 0F);
		Tail_End.setRotationPoint(0F, 0F, 3F);

		Tail_End.rotateAngleX = 1.658063F;
		Tail_End.mirror = false;
		Tail_Mid.addChild(Tail_End);
		Body.addChild(Tail);

		ModuleHead headModule = new ModuleHead(HeadPiece);

		ModuleArm leftArmModule = new ModuleArm(Arm_Left, EnumArm.Left);
		ModuleArm rightArmModule = new ModuleArm(Arm_Right, EnumArm.Right);

		float legspeed = 0.65F;
		float legRotationLimit = 1.4F;

		ModuleLeg leftLegModule = new ModuleLeg(Leg_Left, EnumLeg.FrontLeft,
				EnumPhase.InPhase, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(Leg_Right, EnumLeg.FrontRight,
				EnumPhase.InPhase, legRotationLimit, legspeed);

		skeleton = new SkeletonBiped(Body, headModule, rightArmModule,
				leftArmModule, leftLegModule, rightLegModule);

	}

	public void render(Entity var1, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(var1, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, var1);
		Body.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		// Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		// Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		// Arm_Right.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)
		// Math.PI) * 2.0F * f1 * 0.5F;
		// Arm_Left.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 *
		// 0.5F;
		// Arm_Right.rotateAngleZ = 0.0F;
		// Arm_Left.rotateAngleZ = 0.0F;
		// Leg_Right.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		// Leg_Left.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI)
		// * 1.4F * f1;
		// Leg_Right.rotateAngleY = 0.0F;
		// Leg_Left.rotateAngleY = 0.0F;
	}

}