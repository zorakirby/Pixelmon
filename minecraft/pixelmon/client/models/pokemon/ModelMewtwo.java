package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelMewtwo extends PixelmonModelBase {

	PixelmonModelRenderer Body;

	public ModelMewtwo() {
		textureWidth = 128;
		textureHeight = 64;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 0, 0);

		PixelmonModelRenderer ChestFrontMid = new PixelmonModelRenderer(this,
				0, 0);
		ChestFrontMid.addBox(-3.5F, -11F, -1.8F, 7, 6, 3);
		ChestFrontMid.setRotationPoint(0F, -22F, -2F);
		ChestFrontMid.setTextureSize(128, 64);
		ChestFrontMid.mirror = true;
		setRotation(ChestFrontMid, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer ChestFrontMidLower = new PixelmonModelRenderer(
				this, 0, 0);
		ChestFrontMidLower.addBox(-3.5F, -5.1F, 1.4F, 7, 5, 3);
		ChestFrontMidLower.setRotationPoint(0F, -22F, -2F);
		ChestFrontMidLower.setTextureSize(128, 64);
		ChestFrontMidLower.mirror = true;
		setRotation(ChestFrontMidLower, 0.4363323F, 0F, 0F);
		PixelmonModelRenderer ChestFrontL = new PixelmonModelRenderer(this, 0,
				0);
		ChestFrontL.addBox(3F, -11F, -2.6F, 6, 6, 3);
		ChestFrontL.setRotationPoint(0F, -22F, -2F);
		ChestFrontL.setTextureSize(128, 64);
		ChestFrontL.mirror = true;
		setRotation(ChestFrontL, -0.1745329F, -0.2617994F, 0.0349066F);
		PixelmonModelRenderer ChestFrontR = new PixelmonModelRenderer(this, 0,
				0);
		ChestFrontR.addBox(-9F, -11F, -2.6F, 6, 6, 2);
		ChestFrontR.setRotationPoint(0F, -22F, -2F);
		ChestFrontR.setTextureSize(128, 64);
		ChestFrontR.mirror = true;
		setRotation(ChestFrontR, -0.1745329F, 0.2617994F, -0.0349066F);
		PixelmonModelRenderer ChestFrontLUpper = new PixelmonModelRenderer(
				this, 0, 0);
		ChestFrontLUpper.addBox(3F, -10.1F, 5F, 6, 3, 5);
		ChestFrontLUpper.setRotationPoint(0F, -22F, -2F);
		ChestFrontLUpper.setTextureSize(128, 64);
		ChestFrontLUpper.mirror = true;
		setRotation(ChestFrontLUpper, 0.5235988F, -0.2617994F, 0.0349066F);
		PixelmonModelRenderer ChestFrontRUpper = new PixelmonModelRenderer(
				this, 0, 0);
		ChestFrontRUpper.addBox(-9F, -10.1F, 5F, 6, 2, 5);
		ChestFrontRUpper.setRotationPoint(0F, -22F, -2F);
		ChestFrontRUpper.setTextureSize(128, 64);
		ChestFrontRUpper.mirror = true;
		setRotation(ChestFrontRUpper, 0.5235988F, 0.2617994F, -0.0349066F);
		PixelmonModelRenderer ChestFrontLLower = new PixelmonModelRenderer(
				this, 0, 0);
		ChestFrontLLower.addBox(3F, -5.6F, 0.7F, 6, 3, 3);
		ChestFrontLLower.setRotationPoint(0F, -22F, -2F);
		ChestFrontLLower.setTextureSize(128, 64);
		ChestFrontLLower.mirror = true;
		setRotation(ChestFrontLLower, 0.4363323F, -0.2617994F, 0.0349066F);
		PixelmonModelRenderer ChestFrontRLower = new PixelmonModelRenderer(
				this, 0, 0);
		ChestFrontRLower.addBox(-9F, -5.6F, 0.7F, 6, 3, 2);
		ChestFrontRLower.setRotationPoint(0F, -22F, -2F);
		ChestFrontRLower.setTextureSize(128, 64);
		ChestFrontRLower.mirror = true;
		setRotation(ChestFrontRLower, 0.4363323F, 0.2617994F, -0.0349066F);
		PixelmonModelRenderer ChestFrontRLowest = new PixelmonModelRenderer(
				this, 0, 0);
		ChestFrontRLowest.addBox(-6.6F, -7F, 0.7F, 5, 3, 2);
		ChestFrontRLowest.setRotationPoint(0F, -22F, -2F);
		ChestFrontRLowest.setTextureSize(128, 64);
		ChestFrontRLowest.mirror = true;
		setRotation(ChestFrontRLowest, 0.5061455F, 0.5148721F, -0.5235988F);
		PixelmonModelRenderer ChestFrontLLowest = new PixelmonModelRenderer(
				this, 0, 0);
		ChestFrontLLowest.addBox(1.6F, -7F, 0.7F, 5, 3, 3);
		ChestFrontLLowest.setRotationPoint(0F, -22F, -2F);
		ChestFrontLLowest.setTextureSize(128, 64);
		ChestFrontLLowest.mirror = true;
		setRotation(ChestFrontLLowest, 0.5061455F, -0.5148721F, 0.5235988F);
		PixelmonModelRenderer ChestBumpUpper = new PixelmonModelRenderer(this,
				0, 0);
		ChestBumpUpper.addBox(-1.5F, -11F, -3.8F, 3, 5, 3);
		ChestBumpUpper.setRotationPoint(0F, -22F, -2F);
		ChestBumpUpper.setTextureSize(128, 64);
		ChestBumpUpper.mirror = true;
		setRotation(ChestBumpUpper, -0.3490659F, 0F, 0F);
		PixelmonModelRenderer ChestBumpMid = new PixelmonModelRenderer(this, 0,
				0);
		ChestBumpMid.addBox(-1.5F, -7F, -1.5F, 3, 2, 3);
		ChestBumpMid.setRotationPoint(0F, -22F, -2F);
		ChestBumpMid.setTextureSize(128, 64);
		ChestBumpMid.mirror = true;
		setRotation(ChestBumpMid, 0F, 0F, 0F);
		PixelmonModelRenderer ChestBumpLower = new PixelmonModelRenderer(this,
				0, 0);
		ChestBumpLower.addBox(-1.5F, -4.6F, 2.5F, 3, 5, 3);
		ChestBumpLower.setRotationPoint(0F, -22F, -2F);
		ChestBumpLower.setTextureSize(128, 64);
		ChestBumpLower.mirror = true;
		setRotation(ChestBumpLower, 0.7853982F, 0F, 0F);
		PixelmonModelRenderer ChestLTop = new PixelmonModelRenderer(this, 0, 0);
		ChestLTop.addBox(3F, -13.7F, 3.6F, 6, 3, 5);
		ChestLTop.setRotationPoint(0F, -22F, -2F);
		ChestLTop.setTextureSize(128, 64);
		ChestLTop.mirror = true;
		setRotation(ChestLTop, 0F, -0.2617994F, 0.0349066F);
		PixelmonModelRenderer ChestRTop = new PixelmonModelRenderer(this, 0, 0);
		ChestRTop.addBox(-9F, -13.7F, 3.6F, 6, 2, 5);
		ChestRTop.setRotationPoint(0F, -22F, -2F);
		ChestRTop.setTextureSize(128, 64);
		ChestRTop.mirror = true;
		setRotation(ChestRTop, 0F, 0.2617994F, -0.0349066F);
		PixelmonModelRenderer ChestBackL = new PixelmonModelRenderer(this, 0, 0);
		ChestBackL.addBox(3F, -10.2F, 9.2F, 6, 5, 3);
		ChestBackL.setRotationPoint(0F, -22F, -2F);
		ChestBackL.setTextureSize(128, 64);
		ChestBackL.mirror = true;
		setRotation(ChestBackL, 0F, -0.2617994F, 0.0349066F);
		PixelmonModelRenderer ChestBackR = new PixelmonModelRenderer(this, 0, 0);
		ChestBackR.addBox(-9F, -10.2F, 10.2F, 6, 5, 2);
		ChestBackR.setRotationPoint(0F, -22F, -2F);
		ChestBackR.setTextureSize(128, 64);
		ChestBackR.mirror = true;
		setRotation(ChestBackR, 0F, 0.2617994F, -0.0349066F);
		PixelmonModelRenderer ChestBackLUpper = new PixelmonModelRenderer(this,
				0, 0);
		ChestBackLUpper.addBox(3F, -15.8F, -3.5F, 6, 3, 5);
		ChestBackLUpper.setRotationPoint(0F, -22F, -2F);
		ChestBackLUpper.setTextureSize(128, 64);
		ChestBackLUpper.mirror = true;
		setRotation(ChestBackLUpper, -0.7853982F, -0.2617994F, 0.0349066F);
		PixelmonModelRenderer ChestBackRUpper = new PixelmonModelRenderer(this,
				0, 0);
		ChestBackRUpper.addBox(-9F, -15.8F, -3.5F, 6, 2, 5);
		ChestBackRUpper.setRotationPoint(0F, -22F, -2F);
		ChestBackRUpper.setTextureSize(128, 64);
		ChestBackRUpper.mirror = true;
		setRotation(ChestBackRUpper, -0.7853982F, 0.2617994F, -0.0349066F);
		PixelmonModelRenderer ChestBackLLower = new PixelmonModelRenderer(this,
				0, 0);
		ChestBackLLower.addBox(3F, -11.2F, 4F, 6, 7, 3);
		ChestBackLLower.setRotationPoint(0F, -22F, -2F);
		ChestBackLLower.setTextureSize(128, 64);
		ChestBackLLower.mirror = true;
		setRotation(ChestBackLLower, -0.6108652F, -0.2617994F, 0.0349066F);
		PixelmonModelRenderer ChestBackRLower = new PixelmonModelRenderer(this,
				0, 0);
		ChestBackRLower.addBox(-9F, -11.2F, 5F, 6, 7, 2);
		ChestBackRLower.setRotationPoint(0F, -22F, -2F);
		ChestBackRLower.setTextureSize(128, 64);
		ChestBackRLower.mirror = true;
		setRotation(ChestBackRLower, -0.6108652F, 0.2617994F, -0.0349066F);
		PixelmonModelRenderer ChestNeckBaseL = new PixelmonModelRenderer(this,
				0, 0);
		ChestNeckBaseL.addBox(5.9F, -10.9F, 3.6F, 3, 3, 5);
		ChestNeckBaseL.setRotationPoint(0F, -22F, -2F);
		ChestNeckBaseL.setTextureSize(128, 64);
		ChestNeckBaseL.mirror = true;
		setRotation(ChestNeckBaseL, 0F, -0.2617994F, -0.4363323F);
		PixelmonModelRenderer ChestNeckBaseR = new PixelmonModelRenderer(this,
				0, 0);
		ChestNeckBaseR.addBox(-8.9F, -10.9F, 3.6F, 3, 3, 5);
		ChestNeckBaseR.setRotationPoint(0F, -22F, -2F);
		ChestNeckBaseR.setTextureSize(128, 64);
		ChestNeckBaseR.mirror = true;
		setRotation(ChestNeckBaseR, 0F, 0.2617994F, 0.4363323F);
		PixelmonModelRenderer MidsectionFrontTop = new PixelmonModelRenderer(
				this, 0, 0);
		MidsectionFrontTop.addBox(-3.5F, -7F, 0.8F, 7, 9, 3);
		MidsectionFrontTop.setRotationPoint(0F, -22F, -2F);
		MidsectionFrontTop.setTextureSize(128, 64);
		MidsectionFrontTop.mirror = true;
		setRotation(MidsectionFrontTop, 0.1745329F, 0F, 0F);
		PixelmonModelRenderer MidsectionLTop = new PixelmonModelRenderer(this,
				0, 0);
		MidsectionLTop.addBox(4.2F, -4.5F, 3.2F, 3, 8, 5);
		MidsectionLTop.setRotationPoint(0F, -22F, -2F);
		MidsectionLTop.setTextureSize(128, 64);
		MidsectionLTop.mirror = true;
		setRotation(MidsectionLTop, 0F, 0F, 0.1745329F);
		PixelmonModelRenderer MidsectionRTop = new PixelmonModelRenderer(this,
				0, 0);
		MidsectionRTop.addBox(-7.2F, -4.5F, 3.2F, 3, 8, 5);
		MidsectionRTop.setRotationPoint(0F, -22F, -2F);
		MidsectionRTop.setTextureSize(128, 64);
		MidsectionRTop.mirror = true;
		setRotation(MidsectionRTop, 0F, 0F, -0.1745329F);
		PixelmonModelRenderer MidsectionBackTop = new PixelmonModelRenderer(
				this, 0, 0);
		MidsectionBackTop.addBox(-3.5F, -7.2F, 8.2F, 7, 7, 3);
		MidsectionBackTop.setRotationPoint(0F, -22F, -2F);
		MidsectionBackTop.setTextureSize(128, 64);
		MidsectionBackTop.mirror = true;
		setRotation(MidsectionBackTop, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer MidsectionFrontBottom = new PixelmonModelRenderer(
				this, 108, 45);
		MidsectionFrontBottom.addBox(-3.5F, 0.4F, 1.2F, 7, 9, 3);
		MidsectionFrontBottom.setRotationPoint(0F, -22F, -2F);
		MidsectionFrontBottom.setTextureSize(128, 64);
		MidsectionFrontBottom.mirror = true;
		setRotation(MidsectionFrontBottom, -0.3490659F, 0F, 0F);
		PixelmonModelRenderer MidsectionRBottom = new PixelmonModelRenderer(
				this, 0, 0);
		MidsectionRBottom.addBox(-4.6F, 3F, 3.2F, 3, 10, 5);
		MidsectionRBottom.setRotationPoint(0F, -22F, -2F);
		MidsectionRBottom.setTextureSize(128, 64);
		MidsectionRBottom.mirror = true;
		setRotation(MidsectionRBottom, 0F, 0F, 0.3490659F);
		PixelmonModelRenderer MidsectionLBottom = new PixelmonModelRenderer(
				this, 0, 0);
		MidsectionLBottom.addBox(1.6F, 3F, 3.2F, 3, 10, 5);
		MidsectionLBottom.setRotationPoint(0F, -22F, -2F);
		MidsectionLBottom.setTextureSize(128, 64);
		MidsectionLBottom.mirror = true;
		setRotation(MidsectionLBottom, 0F, 0F, -0.3490659F);
		PixelmonModelRenderer MidsectionBackBottom = new PixelmonModelRenderer(
				this, 0, 0);
		MidsectionBackBottom.addBox(-3.5F, 4.4F, 7.2F, 7, 10, 3);
		MidsectionBackBottom.setRotationPoint(0F, -22F, -2F);
		MidsectionBackBottom.setTextureSize(128, 64);
		MidsectionBackBottom.mirror = true;
		setRotation(MidsectionBackBottom, 0.3490659F, 0F, 0F);
		PixelmonModelRenderer BellyFront = new PixelmonModelRenderer(this, 108,
				54);
		BellyFront.addBox(-3.5F, 9.3F, -2.1F, 7, 7, 3);
		BellyFront.setRotationPoint(0F, -22F, -2F);
		BellyFront.setTextureSize(128, 64);
		BellyFront.mirror = true;
		setRotation(BellyFront, 0F, 0F, 0F);
		PixelmonModelRenderer BellyFrontR = new PixelmonModelRenderer(this, 85,
				45);
		BellyFrontR.addBox(-8.7F, 9.3F, -4F, 8, 7, 3);
		BellyFrontR.setRotationPoint(0F, -22F, -2F);
		BellyFrontR.setTextureSize(128, 64);
		BellyFrontR.mirror = true;
		setRotation(BellyFrontR, 0F, 0.8726646F, 0F);
		PixelmonModelRenderer BellyFrontL = new PixelmonModelRenderer(this, 84,
				35);
		BellyFrontL.addBox(0.7F, 9.3F, -4F, 9, 7, 3);
		BellyFrontL.setRotationPoint(0F, -22F, -2F);
		BellyFrontL.setTextureSize(128, 64);
		BellyFrontL.mirror = true;
		setRotation(BellyFrontL, 0F, -0.8726646F, 0F);
		PixelmonModelRenderer BellyFrontLow = new PixelmonModelRenderer(this,
				108, 39);
		BellyFrontLow.addBox(-3.5F, 15.6F, -5.2F, 7, 3, 3);
		BellyFrontLow.setRotationPoint(0F, -22F, -2F);
		BellyFrontLow.setTextureSize(128, 64);
		BellyFrontLow.mirror = true;
		setRotation(BellyFrontLow, 0.1919862F, 0F, 0F);
		PixelmonModelRenderer BellyFrontLower = new PixelmonModelRenderer(this,
				108, 39);
		BellyFrontLower.addBox(-3.5F, 16.8F, -9.5F, 7, 2, 3);
		BellyFrontLower.setRotationPoint(0F, -22F, -2F);
		BellyFrontLower.setTextureSize(128, 64);
		BellyFrontLower.mirror = true;
		setRotation(BellyFrontLower, 0.4363323F, 0F, 0F);
		PixelmonModelRenderer BellyFrontLowest = new PixelmonModelRenderer(
				this, 106, 32);
		BellyFrontLowest.addBox(-3.5F, 15.8F, 9.8F, 7, 3, 4);
		BellyFrontLowest.setRotationPoint(0F, -22F, 0F);
		BellyFrontLowest.setTextureSize(128, 64);
		BellyFrontLowest.mirror = true;
		setRotation(BellyFrontLowest, -0.6108652F, 0F, 0F);
		PixelmonModelRenderer BellyUndersideFront = new PixelmonModelRenderer(
				this, 106, 32);
		BellyUndersideFront.addBox(-3.5F, 17.9F, 10.3F, 7, 3, 4);
		BellyUndersideFront.setRotationPoint(0F, -22F, 0F);
		BellyUndersideFront.setTextureSize(128, 64);
		BellyUndersideFront.mirror = true;
		setRotation(BellyUndersideFront, -0.4363323F, 0F, 0F);
		PixelmonModelRenderer NeckBottom = new PixelmonModelRenderer(this, 0, 0);
		NeckBottom.addBox(-1.5F, -16.7F, 3.6F, 3, 5, 3);
		NeckBottom.setRotationPoint(0F, -22F, -2F);
		NeckBottom.setTextureSize(128, 64);
		NeckBottom.mirror = true;
		setRotation(NeckBottom, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer NeckTop = new PixelmonModelRenderer(this, 0, 0);
		NeckTop.addBox(-1.5F, -18.4F, 8.9F, 3, 7, 3);
		NeckTop.setRotationPoint(0F, -22F, -2F);
		NeckTop.setTextureSize(128, 64);
		NeckTop.mirror = true;
		setRotation(NeckTop, 0.1745329F, 0F, 0F);
		PixelmonModelRenderer MidsectionFrontBottomL = new PixelmonModelRenderer(
				this, 92, 55);
		MidsectionFrontBottomL.addBox(1.1F, 1F, -1.6F, 8, 9, 0);
		MidsectionFrontBottomL.setRotationPoint(0F, -22F, -2F);
		MidsectionFrontBottomL.setTextureSize(128, 64);
		MidsectionFrontBottomL.mirror = true;
		setRotation(MidsectionFrontBottomL, -0.2443461F, -0.8412487F,
				0.0349066F);
		PixelmonModelRenderer MidsectionFrontBottomR = new PixelmonModelRenderer(
				this, 84, 55);
		MidsectionFrontBottomR.addBox(-9.1F, 1F, -1.6F, 8, 9, 0);
		MidsectionFrontBottomR.setRotationPoint(0F, -22F, -2F);
		MidsectionFrontBottomR.setTextureSize(128, 64);
		MidsectionFrontBottomR.mirror = true;
		setRotation(MidsectionFrontBottomR, -0.2443461F, 0.8412487F,
				-0.0349066F);
		PixelmonModelRenderer ChestFrontMidTopFlat = new PixelmonModelRenderer(
				this, 39, 48);
		ChestFrontMidTopFlat.addBox(-3.5F, -12F, -9.3F, 7, 6, 0);
		ChestFrontMidTopFlat.setRotationPoint(0F, -22F, -2F);
		ChestFrontMidTopFlat.setTextureSize(128, 64);
		ChestFrontMidTopFlat.mirror = true;
		setRotation(ChestFrontMidTopFlat, -1.029744F, 0F, 0F);
		PixelmonModelRenderer MidsectionBackRFillBottom = new PixelmonModelRenderer(
				this, 24, 21);
		MidsectionBackRFillBottom.addBox(1.7F, 2.6F, 8.9F, 8, 11, 0);
		MidsectionBackRFillBottom.setRotationPoint(0F, -22F, -2F);
		MidsectionBackRFillBottom.setTextureSize(128, 64);
		MidsectionBackRFillBottom.mirror = true;
		setRotation(MidsectionBackRFillBottom, 0.2094395F, -0.9599311F, 0F);
		PixelmonModelRenderer MidsectionBackLFillBottom = new PixelmonModelRenderer(
				this, 40, 21);
		MidsectionBackLFillBottom.addBox(-9.7F, 2.6F, 8.9F, 8, 11, 0);
		MidsectionBackLFillBottom.setRotationPoint(0F, -22F, -2F);
		MidsectionBackLFillBottom.setTextureSize(128, 64);
		MidsectionBackLFillBottom.mirror = true;
		setRotation(MidsectionBackLFillBottom, 0.2094395F, 0.9599311F, 0F);
		PixelmonModelRenderer MidsectionLBackFillTop = new PixelmonModelRenderer(
				this, 54, 3);
		MidsectionLBackFillTop.addBox(10.9F, -4.5F, -1F, 0, 8, 6);
		MidsectionLBackFillTop.setRotationPoint(0F, -22F, -2F);
		MidsectionLBackFillTop.setTextureSize(128, 64);
		MidsectionLBackFillTop.mirror = true;
		setRotation(MidsectionLBackFillTop, 0F, -0.8901179F, 0.1134464F);
		PixelmonModelRenderer MidsectionRBackFillTop = new PixelmonModelRenderer(
				this, 3, 18);
		MidsectionRBackFillTop.addBox(-10.9F, -4.5F, -1F, 0, 8, 6);
		MidsectionRBackFillTop.setRotationPoint(0F, -22F, -2F);
		MidsectionRBackFillTop.setTextureSize(128, 64);
		MidsectionRBackFillTop.mirror = true;
		setRotation(MidsectionRBackFillTop, 0F, 0.8901179F, -0.1134464F);
		PixelmonModelRenderer MidsectionFrontTopRFill = new PixelmonModelRenderer(
				this, 42, 13);
		MidsectionFrontTopRFill.addBox(-8.5F, -3F, -1F, 6, 8, 0);
		MidsectionFrontTopRFill.setRotationPoint(0F, -22F, -2F);
		MidsectionFrontTopRFill.setTextureSize(128, 64);
		MidsectionFrontTopRFill.mirror = true;
		setRotation(MidsectionFrontTopRFill, 0.0872665F, 0.5497787F, 0F);
		PixelmonModelRenderer MidsectionFrontTopLFill = new PixelmonModelRenderer(
				this, 42, 5);
		MidsectionFrontTopLFill.addBox(2.5F, -3F, -1F, 6, 8, 0);
		MidsectionFrontTopLFill.setRotationPoint(0F, -22F, -2F);
		MidsectionFrontTopLFill.setTextureSize(128, 64);
		MidsectionFrontTopLFill.mirror = true;
		setRotation(MidsectionFrontTopLFill, 0.0872665F, -0.5497787F, 0F);
		PixelmonModelRenderer ArmpitL = new PixelmonModelRenderer(this, 54, -12);
		ArmpitL.addBox(9F, -12F, -0.6F, 0, 9, 12);
		ArmpitL.setRotationPoint(0F, -22F, -2F);
		ArmpitL.setTextureSize(128, 64);
		ArmpitL.mirror = true;
		setRotation(ArmpitL, 0F, -0.2617994F, 0F);
		PixelmonModelRenderer ArmpitR = new PixelmonModelRenderer(this, 54, -12);
		ArmpitR.addBox(-9F, -12F, -0.6F, 0, 9, 12);
		ArmpitR.setRotationPoint(0F, -22F, -2F);
		ArmpitR.setTextureSize(128, 64);
		ArmpitR.mirror = true;
		setRotation(ArmpitR, 0F, 0.2617994F, 0F);
		PixelmonModelRenderer ChestBackUpperFill = new PixelmonModelRenderer(
				this, 0, 0);
		ChestBackUpperFill.addBox(-2F, -16.1F, -2.9F, 4, 1, 5);
		ChestBackUpperFill.setRotationPoint(0F, -22F, -2F);
		ChestBackUpperFill.setTextureSize(128, 64);
		ChestBackUpperFill.mirror = true;
		setRotation(ChestBackUpperFill, -0.7853982F, 0F, 0F);
		PixelmonModelRenderer BellyFrontLowR = new PixelmonModelRenderer(this,
				72, 41);
		BellyFrontLowR.addBox(-1.4F, 15.8F, -5.7F, 3, 3, 3);
		BellyFrontLowR.setRotationPoint(0F, -22F, -2F);
		BellyFrontLowR.setTextureSize(128, 64);
		BellyFrontLowR.mirror = true;
		setRotation(BellyFrontLowR, 0.1047198F, 0.8726646F, 0.1396263F);
		PixelmonModelRenderer BellyFrontLowestR = new PixelmonModelRenderer(
				this, 68, 40);
		BellyFrontLowestR.addBox(11F, 9.1F, 9.6F, 4, 3, 4);
		BellyFrontLowestR.setRotationPoint(0F, -22F, 0F);
		BellyFrontLowestR.setTextureSize(128, 64);
		BellyFrontLowestR.mirror = true;
		setRotation(BellyFrontLowestR, -0.9599311F, 0.8028515F, 0.8203047F);
		PixelmonModelRenderer BellyFrontLowestL = new PixelmonModelRenderer(
				this, 68, 40);
		BellyFrontLowestL.addBox(-15F, 9.1F, 9.6F, 4, 3, 4);
		BellyFrontLowestL.setRotationPoint(0F, -22F, 0F);
		BellyFrontLowestL.setTextureSize(128, 64);
		BellyFrontLowestL.mirror = true;
		setRotation(BellyFrontLowestL, -0.9599311F, -0.8028515F, -0.8203047F);
		PixelmonModelRenderer BellyUndersideFrontR = new PixelmonModelRenderer(
				this, 70, 42);
		BellyUndersideFrontR.addBox(3.7F, 19.2F, 10.1F, 3, 1, 4);
		BellyUndersideFrontR.setRotationPoint(0F, -22F, 0F);
		BellyUndersideFrontR.setTextureSize(128, 64);
		BellyUndersideFrontR.mirror = true;
		setRotation(BellyUndersideFrontR, -0.4712389F, 0.1919862F, 0.4363323F);
		PixelmonModelRenderer BellyUndersideFrontL = new PixelmonModelRenderer(
				this, 70, 42);
		BellyUndersideFrontL.addBox(-6.7F, 19.2F, 10.1F, 3, 1, 4);
		BellyUndersideFrontL.setRotationPoint(0F, -22F, 0F);
		BellyUndersideFrontL.setTextureSize(128, 64);
		BellyUndersideFrontL.mirror = true;
		setRotation(BellyUndersideFrontL, -0.4712389F, -0.1919862F, -0.4363323F);
		PixelmonModelRenderer BellyFrontLowerR = new PixelmonModelRenderer(
				this, 72, 42);
		BellyFrontLowerR.addBox(1.1F, 16.3F, -10.1F, 3, 2, 3);
		BellyFrontLowerR.setRotationPoint(0F, -22F, -2F);
		BellyFrontLowerR.setTextureSize(128, 64);
		BellyFrontLowerR.mirror = true;
		setRotation(BellyFrontLowerR, 0.3839724F, 0.6283185F, 0.3141593F);
		PixelmonModelRenderer BellyFrontLowerL = new PixelmonModelRenderer(
				this, 72, 42);
		BellyFrontLowerL.addBox(-4.1F, 16.3F, -10.1F, 3, 2, 3);
		BellyFrontLowerL.setRotationPoint(0F, -22F, -2F);
		BellyFrontLowerL.setTextureSize(128, 64);
		BellyFrontLowerL.mirror = true;
		setRotation(BellyFrontLowerL, 0.3839724F, -0.6283185F, -0.3141593F);
		PixelmonModelRenderer BellyFrontLowL = new PixelmonModelRenderer(this,
				72, 41);
		BellyFrontLowL.addBox(-1.6F, 15.8F, -5.7F, 3, 3, 3);
		BellyFrontLowL.setRotationPoint(0F, -22F, -2F);
		BellyFrontLowL.setTextureSize(128, 64);
		BellyFrontLowL.mirror = true;
		setRotation(BellyFrontLowL, 0.1047198F, -0.8726646F, -0.1396263F);

		Body.addChild(ChestFrontMid);
		Body.addChild(ChestFrontMidLower);
		Body.addChild(ChestFrontL);
		Body.addChild(ChestFrontR);
		Body.addChild(ChestFrontLUpper);
		Body.addChild(ChestFrontRUpper);
		Body.addChild(ChestFrontLLower);
		Body.addChild(ChestFrontRLower);
		Body.addChild(ChestFrontRLowest);
		Body.addChild(ChestFrontLLowest);
		Body.addChild(ChestBumpUpper);
		Body.addChild(ChestBumpMid);
		Body.addChild(ChestBumpLower);
		Body.addChild(ChestLTop);
		Body.addChild(ChestRTop);
		Body.addChild(ChestBackL);
		Body.addChild(ChestBackR);
		Body.addChild(ChestBackLUpper);
		Body.addChild(ChestBackRUpper);
		Body.addChild(ChestBackLLower);
		Body.addChild(ChestBackRLower);
		Body.addChild(ChestNeckBaseL);
		Body.addChild(ChestNeckBaseR);
		Body.addChild(MidsectionFrontTop);
		Body.addChild(MidsectionLTop);
		Body.addChild(MidsectionRTop);
		Body.addChild(MidsectionBackTop);
		Body.addChild(MidsectionFrontBottom);
		Body.addChild(MidsectionRBottom);
		Body.addChild(MidsectionLBottom);
		Body.addChild(MidsectionBackBottom);
		Body.addChild(BellyFront);
		Body.addChild(BellyFrontR);
		Body.addChild(BellyFrontL);
		Body.addChild(BellyFrontLow);
		Body.addChild(BellyFrontLower);
		Body.addChild(BellyFrontLowest);
		Body.addChild(BellyUndersideFront);
		Body.addChild(NeckBottom);
		Body.addChild(NeckTop);
		Body.addChild(MidsectionFrontBottomL);
		Body.addChild(MidsectionFrontBottomR);
		Body.addChild(ChestFrontMidTopFlat);
		Body.addChild(MidsectionBackRFillBottom);
		Body.addChild(MidsectionBackLFillBottom);
		Body.addChild(MidsectionLBackFillTop);
		Body.addChild(MidsectionRBackFillTop);
		Body.addChild(MidsectionFrontTopRFill);
		Body.addChild(MidsectionFrontTopLFill);
		Body.addChild(ArmpitL);
		Body.addChild(ArmpitR);
		Body.addChild(ChestBackUpperFill);
		Body.addChild(BellyFrontLowR);
		Body.addChild(BellyFrontLowestR);
		Body.addChild(BellyFrontLowestL);
		Body.addChild(BellyUndersideFrontR);
		Body.addChild(BellyUndersideFrontL);
		Body.addChild(BellyFrontLowerR);
		Body.addChild(BellyFrontLowerL);
		Body.addChild(BellyFrontLowL);

		PixelmonModelRenderer Tail = new PixelmonModelRenderer(this, "Tail");
		Tail.setRotationPoint(0, 0, 0);

		PixelmonModelRenderer TailBaseTop = new PixelmonModelRenderer(this, 36,
				54);
		TailBaseTop.addBox(-3.5F, 15.4F, 3.2F, 7, 7, 3);
		TailBaseTop.setRotationPoint(0F, -22F, -2F);
		TailBaseTop.setTextureSize(128, 64);
		TailBaseTop.mirror = true;
		setRotation(TailBaseTop, 0.6108652F, 0F, 0F);
		PixelmonModelRenderer TailBaseTopR = new PixelmonModelRenderer(this,
				78, 6);
		TailBaseTopR.addBox(-4.4F, 15.4F, 3.7F, 7, 7, 3);
		TailBaseTopR.setRotationPoint(0F, -22F, -2F);
		TailBaseTopR.setTextureSize(128, 64);
		TailBaseTopR.mirror = true;
		setRotation(TailBaseTopR, 0.418879F, -0.7853982F, -0.4537856F);
		PixelmonModelRenderer TailBaseTopL = new PixelmonModelRenderer(this,
				78, 13);
		TailBaseTopL.addBox(-2.6F, 15.4F, 3.7F, 7, 7, 3);
		TailBaseTopL.setRotationPoint(0F, -22F, -2F);
		TailBaseTopL.setTextureSize(128, 64);
		TailBaseTopL.mirror = true;
		setRotation(TailBaseTopL, 0.418879F, 0.7853982F, 0.4537856F);
		PixelmonModelRenderer TailBaseBottom = new PixelmonModelRenderer(this,
				100, 22);
		TailBaseBottom.addBox(-3.5F, 20.9F, 8.4F, 7, 3, 7);
		TailBaseBottom.setRotationPoint(0F, -22F, 0F);
		TailBaseBottom.setTextureSize(128, 64);
		TailBaseBottom.mirror = true;
		setRotation(TailBaseBottom, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailBaseBottomL = new PixelmonModelRenderer(this,
				104, 12);
		TailBaseBottomL.addBox(-10.9F, 18.4F, 8.6F, 5, 3, 7);
		TailBaseBottomL.setRotationPoint(0F, -22F, 0F);
		TailBaseBottomL.setTextureSize(128, 64);
		TailBaseBottomL.mirror = true;
		setRotation(TailBaseBottomL, -0.2268928F, -0.1396263F, -0.6108652F);
		PixelmonModelRenderer TailBaseBottomR = new PixelmonModelRenderer(this,
				104, 12);
		TailBaseBottomR.addBox(5.9F, 18.4F, 8.6F, 5, 3, 7);
		TailBaseBottomR.setRotationPoint(0F, -22F, 0F);
		TailBaseBottomR.setTextureSize(128, 64);
		TailBaseBottomR.mirror = true;
		setRotation(TailBaseBottomR, -0.2268928F, 0.1396263F, 0.6108652F);
		PixelmonModelRenderer TailSeg1Bottom = new PixelmonModelRenderer(this,
				102, 9);
		TailSeg1Bottom.addBox(-2F, 1.1F, -3F, 4, 4, 9);
		TailSeg1Bottom.setRotationPoint(0F, -2F, 10F);
		TailSeg1Bottom.setTextureSize(128, 64);
		TailSeg1Bottom.mirror = true;
		setRotation(TailSeg1Bottom, -0.3490659F, 0F, 0F);
		PixelmonModelRenderer TailSeg1Top = new PixelmonModelRenderer(this,
				102, 9);
		TailSeg1Top.addBox(-2F, -7.4F, -4.8F, 4, 4, 9);
		TailSeg1Top.setRotationPoint(0F, -2F, 10F);
		TailSeg1Top.setTextureSize(128, 64);
		TailSeg1Top.mirror = true;
		setRotation(TailSeg1Top, -0.6108652F, 0F, 0F);
		PixelmonModelRenderer TailSeg1BottomR = new PixelmonModelRenderer(this,
				104, 13);
		TailSeg1BottomR.addBox(-4.9F, 2.4F, 0F, 6, 3, 6);
		TailSeg1BottomR.setRotationPoint(0F, -2F, 10F);
		TailSeg1BottomR.setTextureSize(128, 64);
		TailSeg1BottomR.mirror = true;
		setRotation(TailSeg1BottomR, -0.4014257F, 0.2094395F, 0.5235988F);
		PixelmonModelRenderer TailSeg1BottomL = new PixelmonModelRenderer(this,
				104, 13);
		TailSeg1BottomL.addBox(-1.1F, 2.4F, 0F, 6, 3, 6);
		TailSeg1BottomL.setRotationPoint(0F, -2F, 10F);
		TailSeg1BottomL.setTextureSize(128, 64);
		TailSeg1BottomL.mirror = true;
		setRotation(TailSeg1BottomL, -0.4014257F, -0.2094395F, -0.5235988F);
		PixelmonModelRenderer TailSeg1TopR = new PixelmonModelRenderer(this,
				104, 13);
		TailSeg1TopR.addBox(-4.3F, -6.2F, 0F, 6, 3, 6);
		TailSeg1TopR.setRotationPoint(0F, -2F, 10F);
		TailSeg1TopR.setTextureSize(128, 64);
		TailSeg1TopR.mirror = true;
		setRotation(TailSeg1TopR, -0.4014257F, -0.2094395F, -0.5235988F);
		PixelmonModelRenderer TailSeg1TopL = new PixelmonModelRenderer(this,
				104, 13);
		TailSeg1TopL.addBox(-1.7F, -6.2F, 0F, 6, 3, 6);
		TailSeg1TopL.setRotationPoint(0F, -2F, 10F);
		TailSeg1TopL.setTextureSize(128, 64);
		TailSeg1TopL.mirror = true;
		setRotation(TailSeg1TopL, -0.4014257F, 0.2094395F, 0.5235988F);
		PixelmonModelRenderer TailSeg1BaseL = new PixelmonModelRenderer(this,
				104, 8);
		TailSeg1BaseL.addBox(4F, -3F, -3F, 3, 5, 9);
		TailSeg1BaseL.setRotationPoint(0F, -2F, 10F);
		TailSeg1BaseL.setTextureSize(128, 64);
		TailSeg1BaseL.mirror = true;
		setRotation(TailSeg1BaseL, -0.3490659F, 0F, 0F);
		PixelmonModelRenderer TailSeg1BaseR = new PixelmonModelRenderer(this,
				104, 8);
		TailSeg1BaseR.addBox(-7F, -3F, -3F, 3, 5, 9);
		TailSeg1BaseR.setRotationPoint(0F, -2F, 10F);
		TailSeg1BaseR.setTextureSize(128, 64);
		TailSeg1BaseR.mirror = true;
		setRotation(TailSeg1BaseR, -0.3490659F, 0F, 0F);
		PixelmonModelRenderer TailSeg2BaseR = new PixelmonModelRenderer(this,
				112, 12);
		TailSeg2BaseR.addBox(-6F, -1F, -1F, 3, 5, 5);
		TailSeg2BaseR.setRotationPoint(0F, -2F, 15F);
		TailSeg2BaseR.setTextureSize(128, 64);
		TailSeg2BaseR.mirror = true;
		setRotation(TailSeg2BaseR, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailSeg2Top = new PixelmonModelRenderer(this,
				104, 6);
		TailSeg2Top.addBox(-2.5F, -3F, -3F, 5, 9, 7);
		TailSeg2Top.setRotationPoint(0F, -2F, 15F);
		TailSeg2Top.setTextureSize(128, 64);
		TailSeg2Top.mirror = true;
		setRotation(TailSeg2Top, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailSeg2TopL = new PixelmonModelRenderer(this,
				106, 10);
		TailSeg2TopL.addBox(0.7F, -3.9F, -3F, 4, 5, 7);
		TailSeg2TopL.setRotationPoint(0F, -2F, 15F);
		TailSeg2TopL.setTextureSize(128, 64);
		TailSeg2TopL.mirror = true;
		setRotation(TailSeg2TopL, -0.1919862F, 0.0872665F, 0.5235988F);
		PixelmonModelRenderer TailSeg2TopR = new PixelmonModelRenderer(this,
				106, 10);
		TailSeg2TopR.addBox(-4.7F, -3.9F, -3F, 4, 5, 7);
		TailSeg2TopR.setRotationPoint(0F, -2F, 15F);
		TailSeg2TopR.setTextureSize(128, 64);
		TailSeg2TopR.mirror = true;
		setRotation(TailSeg2TopR, -0.1919862F, -0.0872665F, -0.5235988F);
		PixelmonModelRenderer TailSeg2BottomL = new PixelmonModelRenderer(this,
				110, 12);
		TailSeg2BottomL.addBox(-0.9F, 1.5F, -1F, 4, 5, 5);
		TailSeg2BottomL.setRotationPoint(0F, -2F, 15F);
		TailSeg2BottomL.setTextureSize(128, 64);
		TailSeg2BottomL.mirror = true;
		setRotation(TailSeg2BottomL, -0.1919862F, -0.0872665F, -0.5235988F);
		PixelmonModelRenderer TailSeg2BottomR = new PixelmonModelRenderer(this,
				110, 12);
		TailSeg2BottomR.addBox(-3.1F, 1.5F, -1F, 4, 5, 5);
		TailSeg2BottomR.setRotationPoint(0F, -2F, 15F);
		TailSeg2BottomR.setTextureSize(128, 64);
		TailSeg2BottomR.mirror = true;
		setRotation(TailSeg2BottomR, -0.1919862F, 0.0872665F, 0.5235988F);
		PixelmonModelRenderer TailSeg2BaseL = new PixelmonModelRenderer(this,
				112, 12);
		TailSeg2BaseL.addBox(3F, -1F, -1F, 3, 5, 5);
		TailSeg2BaseL.setRotationPoint(0F, -2F, 15F);
		TailSeg2BaseL.setTextureSize(128, 64);
		TailSeg2BaseL.mirror = true;
		setRotation(TailSeg2BaseL, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailSeg3BaseR = new PixelmonModelRenderer(this,
				108, 11);
		TailSeg3BaseR.addBox(-5.5F, -2F, -3F, 3, 4, 7);
		TailSeg3BaseR.setRotationPoint(0F, 0F, 19F);
		TailSeg3BaseR.setTextureSize(128, 64);
		TailSeg3BaseR.mirror = true;
		setRotation(TailSeg3BaseR, 0F, 0F, 0F);
		PixelmonModelRenderer TailSeg3Top = new PixelmonModelRenderer(this,
				106, 7);
		TailSeg3Top.addBox(-2F, -4F, -3F, 4, 8, 7);
		TailSeg3Top.setRotationPoint(0F, 0F, 19F);
		TailSeg3Top.setTextureSize(128, 64);
		TailSeg3Top.mirror = true;
		setRotation(TailSeg3Top, 0F, 0F, 0F);
		PixelmonModelRenderer TailSeg3TopR = new PixelmonModelRenderer(this,
				106, 11);
		TailSeg3TopR.addBox(-3.8F, -4.5F, -3F, 4, 4, 7);
		TailSeg3TopR.setRotationPoint(0F, 0F, 19F);
		TailSeg3TopR.setTextureSize(128, 64);
		TailSeg3TopR.mirror = true;
		setRotation(TailSeg3TopR, 0F, 0F, -0.5235988F);
		PixelmonModelRenderer TailSeg3TopL = new PixelmonModelRenderer(this,
				106, 11);
		TailSeg3TopL.addBox(-0.2F, -4.5F, -3F, 4, 4, 7);
		TailSeg3TopL.setRotationPoint(0F, 0F, 19F);
		TailSeg3TopL.setTextureSize(128, 64);
		TailSeg3TopL.mirror = true;
		setRotation(TailSeg3TopL, 0F, 0F, 0.5235988F);
		PixelmonModelRenderer TailSeg3BottomL = new PixelmonModelRenderer(this,
				106, 11);
		TailSeg3BottomL.addBox(-0.2F, 0.5F, -3F, 4, 4, 7);
		TailSeg3BottomL.setRotationPoint(0F, 0F, 19F);
		TailSeg3BottomL.setTextureSize(128, 64);
		TailSeg3BottomL.mirror = true;
		setRotation(TailSeg3BottomL, 0F, 0F, -0.5235988F);
		PixelmonModelRenderer TailSeg3BottomR = new PixelmonModelRenderer(this,
				106, 11);
		TailSeg3BottomR.addBox(-3.8F, 0.5F, -3F, 4, 4, 7);
		TailSeg3BottomR.setRotationPoint(0F, 0F, 19F);
		TailSeg3BottomR.setTextureSize(128, 64);
		TailSeg3BottomR.mirror = true;
		setRotation(TailSeg3BottomR, 0F, 0F, 0.5235988F);
		PixelmonModelRenderer TailSeg3BaseL = new PixelmonModelRenderer(this,
				108, 11);
		TailSeg3BaseL.addBox(2.5F, -2F, -3F, 3, 4, 7);
		TailSeg3BaseL.setRotationPoint(0F, 0F, 19F);
		TailSeg3BaseL.setTextureSize(128, 64);
		TailSeg3BaseL.mirror = true;
		setRotation(TailSeg3BaseL, 0F, 0F, 0F);
		PixelmonModelRenderer TailSeg4Base = new PixelmonModelRenderer(this,
				98, 13);
		TailSeg4Base.addBox(-5F, -2F, -1F, 10, 4, 5);
		TailSeg4Base.setRotationPoint(0F, 0F, 23F);
		TailSeg4Base.setTextureSize(128, 64);
		TailSeg4Base.mirror = true;
		setRotation(TailSeg4Base, 0.2617994F, 0F, 0F);
		PixelmonModelRenderer TailSeg4Top = new PixelmonModelRenderer(this,
				108, 13);
		TailSeg4Top.addBox(-2.5F, -3.6F, -1F, 5, 4, 5);
		TailSeg4Top.setRotationPoint(0F, 0F, 23F);
		TailSeg4Top.setTextureSize(128, 64);
		TailSeg4Top.mirror = true;
		setRotation(TailSeg4Top, 0.2617994F, 0F, 0F);
		PixelmonModelRenderer TailSeg4TopR = new PixelmonModelRenderer(this,
				112, 13);
		TailSeg4TopR.addBox(-3.2F, -4.3F, -1F, 3, 4, 5);
		TailSeg4TopR.setRotationPoint(0F, 0F, 23F);
		TailSeg4TopR.setTextureSize(128, 64);
		TailSeg4TopR.mirror = true;
		setRotation(TailSeg4TopR, 0.3141593F, 0.1570796F, -0.5235988F);
		PixelmonModelRenderer TailSeg4TopL = new PixelmonModelRenderer(this,
				112, 13);
		TailSeg4TopL.addBox(0.3F, -4.3F, -1F, 3, 4, 5);
		TailSeg4TopL.setRotationPoint(0F, 0F, 23F);
		TailSeg4TopL.setTextureSize(128, 64);
		TailSeg4TopL.mirror = true;
		setRotation(TailSeg4TopL, 0.3141593F, -0.1570796F, 0.5235988F);
		PixelmonModelRenderer TailSeg4BottomL = new PixelmonModelRenderer(this,
				112, 13);
		TailSeg4BottomL.addBox(0.2F, 0.3F, -1F, 3, 4, 5);
		TailSeg4BottomL.setRotationPoint(0F, 0F, 23F);
		TailSeg4BottomL.setTextureSize(128, 64);
		TailSeg4BottomL.mirror = true;
		setRotation(TailSeg4BottomL, 0.3141593F, 0.1570796F, -0.5235988F);
		PixelmonModelRenderer TailSeg4BottomR = new PixelmonModelRenderer(this,
				112, 13);
		TailSeg4BottomR.addBox(-3.2F, 0.3F, -1F, 3, 4, 5);
		TailSeg4BottomR.setRotationPoint(0F, 0F, 23F);
		TailSeg4BottomR.setTextureSize(128, 64);
		TailSeg4BottomR.mirror = true;
		setRotation(TailSeg4BottomR, 0.3141593F, -0.1570796F, 0.5235988F);
		PixelmonModelRenderer TailSeg4Bottom = new PixelmonModelRenderer(this,
				108, 13);
		TailSeg4Bottom.addBox(-2.5F, -0.5F, -1F, 5, 4, 5);
		TailSeg4Bottom.setRotationPoint(0F, 0F, 23F);
		TailSeg4Bottom.setTextureSize(128, 64);
		TailSeg4Bottom.mirror = true;
		setRotation(TailSeg4Bottom, 0.2617994F, 0F, 0F);
		PixelmonModelRenderer TailSeg5High = new PixelmonModelRenderer(this,
				104, 12);
		TailSeg5High.addBox(-3F, -3.3F, -1.8F, 6, 4, 6);
		TailSeg5High.setRotationPoint(0F, -1F, 27F);
		TailSeg5High.setTextureSize(128, 64);
		TailSeg5High.mirror = true;
		setRotation(TailSeg5High, 0.5759587F, 0F, 0F);
		PixelmonModelRenderer TailSeg5Base = new PixelmonModelRenderer(this,
				98, 12);
		TailSeg5Base.addBox(-4.5F, -2F, -1.8F, 9, 4, 6);
		TailSeg5Base.setRotationPoint(0F, -1F, 27F);
		TailSeg5Base.setTextureSize(128, 64);
		TailSeg5Base.mirror = true;
		setRotation(TailSeg5Base, 0.6981317F, 0F, 0F);
		PixelmonModelRenderer TailSeg6Base = new PixelmonModelRenderer(this,
				104, 12);
		TailSeg6Base.addBox(-4F, -3.3F, -2.3F, 8, 5, 4);
		TailSeg6Base.setRotationPoint(0F, -4F, 30F);
		TailSeg6Base.setTextureSize(128, 64);
		TailSeg6Base.mirror = true;
		setRotation(TailSeg6Base, -0.4363323F, 0F, 0F);
		PixelmonModelRenderer TailSeg7Base = new PixelmonModelRenderer(this,
				106, 12);
		TailSeg7Base.addBox(-3.5F, -3.3F, -2F, 7, 4, 4);
		TailSeg7Base.setRotationPoint(0F, -7F, 31F);
		TailSeg7Base.setTextureSize(128, 64);
		TailSeg7Base.mirror = true;
		setRotation(TailSeg7Base, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailSeg8Base = new PixelmonModelRenderer(this,
				108, 12);
		TailSeg8Base.addBox(-3F, -3.3F, -1.7F, 6, 5, 4);
		TailSeg8Base.setRotationPoint(0F, -11F, 31F);
		TailSeg8Base.setTextureSize(128, 64);
		TailSeg8Base.mirror = true;
		setRotation(TailSeg8Base, 0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailSeg9Base = new PixelmonModelRenderer(this,
				110, 8);
		TailSeg9Base.addBox(-2.5F, -8.3F, -2.2F, 5, 9, 4);
		TailSeg9Base.setRotationPoint(0F, -14F, 31F);
		TailSeg9Base.setTextureSize(128, 64);
		TailSeg9Base.mirror = true;
		setRotation(TailSeg9Base, 0.4363323F, 0F, 0F);
		PixelmonModelRenderer TailSeg10Base = new PixelmonModelRenderer(this,
				110, 14);
		TailSeg10Base.addBox(-2.5F, -3.6F, -2.4F, 5, 4, 4);
		TailSeg10Base.setRotationPoint(0F, -21F, 28F);
		TailSeg10Base.setTextureSize(128, 64);
		TailSeg10Base.mirror = true;
		setRotation(TailSeg10Base, 0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailSeg11Base = new PixelmonModelRenderer(this,
				110, 12);
		TailSeg11Base.addBox(-2.5F, -4.3F, -1.8F, 5, 6, 4);
		TailSeg11Base.setRotationPoint(0F, -25F, 27F);
		TailSeg11Base.setTextureSize(128, 64);
		TailSeg11Base.mirror = true;
		setRotation(TailSeg11Base, -0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailSeg12Base = new PixelmonModelRenderer(this,
				112, 12);
		TailSeg12Base.addBox(-2F, -4.3F, -2F, 4, 5, 4);
		TailSeg12Base.setRotationPoint(0F, -29F, 28F);
		TailSeg12Base.setTextureSize(128, 64);
		TailSeg12Base.mirror = true;
		setRotation(TailSeg12Base, -0.6108652F, 0F, 0F);
		PixelmonModelRenderer TailSeg13Base = new PixelmonModelRenderer(this,
				108, 12);
		TailSeg13Base.addBox(-2F, -1.9F, -0.8F, 4, 3, 5);
		TailSeg13Base.setRotationPoint(0F, -32F, 30F);
		TailSeg13Base.setTextureSize(128, 64);
		TailSeg13Base.mirror = true;
		setRotation(TailSeg13Base, 0.1745329F, 0F, 0F);
		PixelmonModelRenderer TailSeg14Base = new PixelmonModelRenderer(this,
				112, 13);
		TailSeg14Base.addBox(-1.5F, -1.5F, -0.7F, 3, 3, 4);
		TailSeg14Base.setRotationPoint(0F, -33F, 34F);
		TailSeg14Base.setTextureSize(128, 64);
		TailSeg14Base.mirror = true;
		setRotation(TailSeg14Base, -0.2617994F, 0F, 0F);
		PixelmonModelRenderer TailSeg15Base = new PixelmonModelRenderer(this,
				112, 14);
		TailSeg15Base.addBox(-1.5F, -1.5F, -0.7F, 3, 3, 3);
		TailSeg15Base.setRotationPoint(0F, -32F, 37F);
		TailSeg15Base.setTextureSize(128, 64);
		TailSeg15Base.mirror = true;
		setRotation(TailSeg15Base, -0.7853982F, 0F, 0F);
		PixelmonModelRenderer TailSeg16Base = new PixelmonModelRenderer(this,
				116, 14);
		TailSeg16Base.addBox(-1.5F, -1.3F, -1.8F, 3, 5, 3);
		TailSeg16Base.setRotationPoint(0F, -30F, 39F);
		TailSeg16Base.setTextureSize(128, 64);
		TailSeg16Base.mirror = true;
		setRotation(TailSeg16Base, 0.3490659F, 0F, 0F);
		PixelmonModelRenderer TailTipBase = new PixelmonModelRenderer(this,
				112, 16);
		TailTipBase.addBox(-2F, 8F, -2.3F, 4, 2, 4);
		TailTipBase.setRotationPoint(0F, -27F, 40F);
		TailTipBase.setTextureSize(128, 64);
		TailTipBase.mirror = true;
		setRotation(TailTipBase, 0F, 0F, 0F);
		PixelmonModelRenderer TailTipFront = new PixelmonModelRenderer(this,
				118, 12);
		TailTipFront.addBox(-2F, -0.5F, -2.3F, 4, 8, 1);
		TailTipFront.setRotationPoint(0F, -27F, 40F);
		TailTipFront.setTextureSize(128, 64);
		TailTipFront.mirror = true;
		setRotation(TailTipFront, -0.122173F, 0F, 0F);
		PixelmonModelRenderer TailTipBack = new PixelmonModelRenderer(this,
				118, 12);
		TailTipBack.addBox(-2F, -0.5F, 0.7F, 4, 8, 1);
		TailTipBack.setRotationPoint(0F, -27F, 40F);
		TailTipBack.setTextureSize(128, 64);
		TailTipBack.mirror = true;
		setRotation(TailTipBack, 0.122173F, 0F, 0F);
		PixelmonModelRenderer TailTipRight = new PixelmonModelRenderer(this,
				118, 9);
		TailTipRight.addBox(-2F, -0.5F, -2.3F, 1, 8, 4);
		TailTipRight.setRotationPoint(0F, -27F, 40F);
		TailTipRight.setTextureSize(128, 64);
		TailTipRight.mirror = true;
		setRotation(TailTipRight, 0F, 0F, 0.122173F);
		PixelmonModelRenderer TailTipLeft = new PixelmonModelRenderer(this,
				118, 9);
		TailTipLeft.addBox(1F, -0.5F, -2.3F, 1, 8, 4);
		TailTipLeft.setRotationPoint(0F, -27F, 40F);
		TailTipLeft.setTextureSize(128, 64);
		TailTipLeft.mirror = true;
		setRotation(TailTipLeft, 0F, 0F, -0.122173F);
		PixelmonModelRenderer TailTipFrontLower = new PixelmonModelRenderer(
				this, 118, 18);
		TailTipFrontLower.addBox(-2F, 5.6F, -5.5F, 4, 3, 1);
		TailTipFrontLower.setRotationPoint(0F, -27F, 40F);
		TailTipFrontLower.setTextureSize(128, 64);
		TailTipFrontLower.mirror = true;
		setRotation(TailTipFrontLower, 0.3490659F, 0F, 0F);
		PixelmonModelRenderer TailTipBackLower = new PixelmonModelRenderer(
				this, 118, 18);
		TailTipBackLower.addBox(-2F, 5.8F, 3.9F, 4, 3, 1);
		TailTipBackLower.setRotationPoint(0F, -27F, 40F);
		TailTipBackLower.setTextureSize(128, 64);
		TailTipBackLower.mirror = true;
		setRotation(TailTipBackLower, -0.3490659F, 0F, 0F);
		PixelmonModelRenderer TailTipLeftLower = new PixelmonModelRenderer(
				this, 118, 15);
		TailTipLeftLower.addBox(4.2F, 5.8F, -2.3F, 1, 3, 4);
		TailTipLeftLower.setRotationPoint(0F, -27F, 40F);
		TailTipLeftLower.setTextureSize(128, 64);
		TailTipLeftLower.mirror = true;
		setRotation(TailTipLeftLower, 0F, 0F, 0.3490659F);
		PixelmonModelRenderer TailTipRightLower = new PixelmonModelRenderer(
				this, 118, 15);
		TailTipRightLower.addBox(-5.2F, 5.8F, -2.3F, 1, 3, 4);
		TailTipRightLower.setRotationPoint(0F, -27F, 40F);
		TailTipRightLower.setTextureSize(128, 64);
		TailTipRightLower.mirror = true;
		setRotation(TailTipRightLower, 0F, 0F, -0.3490659F);
		PixelmonModelRenderer TailTipBackFillL = new PixelmonModelRenderer(
				this, 86, 23);
		TailTipBackFillL.addBox(-0.7F, -0.5F, 2.6F, 2, 8, 0);
		TailTipBackFillL.setRotationPoint(0F, -27F, 40F);
		TailTipBackFillL.setTextureSize(128, 64);
		TailTipBackFillL.mirror = true;
		setRotation(TailTipBackFillL, 0.0872665F, 0.7853982F, 0F);
		PixelmonModelRenderer TailTipBackFillR = new PixelmonModelRenderer(
				this, 86, 23);
		TailTipBackFillR.addBox(-1.2F, -0.5F, 2.6F, 2, 8, 0);
		TailTipBackFillR.setRotationPoint(0F, -27F, 40F);
		TailTipBackFillR.setTextureSize(128, 64);
		TailTipBackFillR.mirror = true;
		setRotation(TailTipBackFillR, 0.0872665F, -0.7853982F, 0F);
		PixelmonModelRenderer TailTipFrontFillR = new PixelmonModelRenderer(
				this, 84, 23);
		TailTipFrontFillR.addBox(-0.9F, -0.5F, -3F, 2, 8, 0);
		TailTipFrontFillR.setRotationPoint(0F, -27F, 40F);
		TailTipFrontFillR.setTextureSize(128, 64);
		TailTipFrontFillR.mirror = true;
		setRotation(TailTipFrontFillR, -0.0872665F, 0.7853982F, 0F);
		PixelmonModelRenderer TailTipFrontFillL = new PixelmonModelRenderer(
				this, 84, 23);
		TailTipFrontFillL.addBox(-1.3F, -0.5F, -3F, 2, 8, 0);
		TailTipFrontFillL.setRotationPoint(0F, -27F, 40F);
		TailTipFrontFillL.setTextureSize(128, 64);
		TailTipFrontFillL.mirror = true;
		setRotation(TailTipFrontFillL, -0.0872665F, -0.7853982F, 0F);
		PixelmonModelRenderer TailTipBackLowerFillL = new PixelmonModelRenderer(
				this, 86, 31);
		TailTipBackLowerFillL.addBox(-0.7F, 6.2F, 4.8F, 2, 3, 0);
		TailTipBackLowerFillL.setRotationPoint(0F, -27F, 40F);
		TailTipBackLowerFillL.setTextureSize(128, 64);
		TailTipBackLowerFillL.mirror = true;
		setRotation(TailTipBackLowerFillL, -0.2356194F, 0.7853982F, 0F);
		PixelmonModelRenderer TailTipBackLowerFillR = new PixelmonModelRenderer(
				this, 86, 31);
		TailTipBackLowerFillR.addBox(-1.2F, 6.2F, 4.8F, 2, 3, 0);
		TailTipBackLowerFillR.setRotationPoint(0F, -27F, 40F);
		TailTipBackLowerFillR.setTextureSize(128, 64);
		TailTipBackLowerFillR.mirror = true;
		setRotation(TailTipBackLowerFillR, -0.2356194F, -0.7853982F, 0F);
		PixelmonModelRenderer TailTipFrontLowerFillR = new PixelmonModelRenderer(
				this, 84, 31);
		TailTipFrontLowerFillR.addBox(-0.9F, 6.2F, -5.2F, 2, 3, 0);
		TailTipFrontLowerFillR.setRotationPoint(0F, -27F, 40F);
		TailTipFrontLowerFillR.setTextureSize(128, 64);
		TailTipFrontLowerFillR.mirror = true;
		setRotation(TailTipFrontLowerFillR, 0.2356194F, 0.7853982F, 0F);
		PixelmonModelRenderer TailTipFrontLowerFillL = new PixelmonModelRenderer(
				this, 84, 31);
		TailTipFrontLowerFillL.addBox(-1.3F, 6.2F, -5.2F, 2, 3, 0);
		TailTipFrontLowerFillL.setRotationPoint(0F, -27F, 40F);
		TailTipFrontLowerFillL.setTextureSize(128, 64);
		TailTipFrontLowerFillL.mirror = true;
		setRotation(TailTipFrontLowerFillL, 0.2356194F, -0.7853982F, 0F);

		Tail.addChild(TailBaseTop);
		Tail.addChild(TailBaseTopR);
		Tail.addChild(TailBaseTopL);
		Tail.addChild(TailBaseBottom);
		Tail.addChild(TailBaseBottomL);
		Tail.addChild(TailBaseBottomR);
		Tail.addChild(TailSeg1Bottom);
		Tail.addChild(TailSeg1Top);
		Tail.addChild(TailSeg1BottomR);
		Tail.addChild(TailSeg1BottomL);
		Tail.addChild(TailSeg1TopR);
		Tail.addChild(TailSeg1TopL);
		Tail.addChild(TailSeg1BaseL);
		Tail.addChild(TailSeg1BaseR);
		Tail.addChild(TailSeg2BaseR);
		Tail.addChild(TailSeg2Top);
		Tail.addChild(TailSeg2TopL);
		Tail.addChild(TailSeg2TopR);
		Tail.addChild(TailSeg2BottomL);
		Tail.addChild(TailSeg2BottomR);
		Tail.addChild(TailSeg2BaseL);
		Tail.addChild(TailSeg3BaseR);
		Tail.addChild(TailSeg3Top);
		Tail.addChild(TailSeg3TopR);
		Tail.addChild(TailSeg3TopL);
		Tail.addChild(TailSeg3BottomL);
		Tail.addChild(TailSeg3BottomR);
		Tail.addChild(TailSeg3BaseL);
		Tail.addChild(TailSeg4Base);
		Tail.addChild(TailSeg4Top);
		Tail.addChild(TailSeg4TopR);
		Tail.addChild(TailSeg4TopL);
		Tail.addChild(TailSeg4BottomL);
		Tail.addChild(TailSeg4BottomR);
		Tail.addChild(TailSeg4Bottom);
		Tail.addChild(TailSeg5High);
		Tail.addChild(TailSeg5Base);
		Tail.addChild(TailSeg6Base);
		Tail.addChild(TailSeg7Base);
		Tail.addChild(TailSeg8Base);
		Tail.addChild(TailSeg9Base);
		Tail.addChild(TailSeg10Base);
		Tail.addChild(TailSeg11Base);
		Tail.addChild(TailSeg12Base);
		Tail.addChild(TailSeg13Base);
		Tail.addChild(TailSeg14Base);
		Tail.addChild(TailSeg15Base);
		Tail.addChild(TailSeg16Base);
		Tail.addChild(TailTipBase);
		Tail.addChild(TailTipFront);
		Tail.addChild(TailTipBack);
		Tail.addChild(TailTipRight);
		Tail.addChild(TailTipLeft);
		Tail.addChild(TailTipFrontLower);
		Tail.addChild(TailTipBackLower);
		Tail.addChild(TailTipLeftLower);
		Tail.addChild(TailTipRightLower);
		Tail.addChild(TailTipBackFillL);
		Tail.addChild(TailTipBackFillR);
		Tail.addChild(TailTipFrontFillR);
		Tail.addChild(TailTipFrontFillL);
		Tail.addChild(TailTipBackLowerFillL);
		Tail.addChild(TailTipBackLowerFillR);
		Tail.addChild(TailTipFrontLowerFillR);
		Tail.addChild(TailTipFrontLowerFillL);
		Body.addChild(Tail);

		PixelmonModelRenderer Head = new PixelmonModelRenderer(this, "Head");
		Head.setRotationPoint(0, 0, 0);

		PixelmonModelRenderer NeckTube1 = new PixelmonModelRenderer(this, 0, 0);
		NeckTube1.addBox(-1F, -1F, -3F, 2, 2, 7);
		NeckTube1.setRotationPoint(0F, -31F, 10F);
		NeckTube1.setTextureSize(128, 64);
		NeckTube1.mirror = true;
		setRotation(NeckTube1, 0.3490659F, 0F, 0F);
		PixelmonModelRenderer NeckTube2 = new PixelmonModelRenderer(this, 0, 0);
		NeckTube2.addBox(-1F, 0.3F, 3.3F, 2, 2, 4);
		NeckTube2.setRotationPoint(0F, -31F, 10F);
		NeckTube2.setTextureSize(128, 64);
		NeckTube2.mirror = true;
		setRotation(NeckTube2, 0.6981317F, 0F, 0F);
		PixelmonModelRenderer NeckTube3 = new PixelmonModelRenderer(this, 0, 0);
		NeckTube3.addBox(-1F, -8F, 3.7F, 2, 3, 2);
		NeckTube3.setRotationPoint(0F, -31F, 10F);
		NeckTube3.setTextureSize(128, 64);
		NeckTube3.mirror = true;
		setRotation(NeckTube3, -0.3490659F, 0F, 0F);
		PixelmonModelRenderer NeckTube4 = new PixelmonModelRenderer(this, 0, 0);
		NeckTube4.addBox(-1F, -8.6F, 6.1F, 2, 3, 2);
		NeckTube4.setRotationPoint(0F, -31F, 10F);
		NeckTube4.setTextureSize(128, 64);
		NeckTube4.mirror = true;
		setRotation(NeckTube4, 0F, 0F, 0F);
		PixelmonModelRenderer NeckTube5 = new PixelmonModelRenderer(this, 0, 0);
		NeckTube5.addBox(-1F, -6.3F, 9.3F, 2, 3, 2);
		NeckTube5.setRotationPoint(0F, -31F, 10F);
		NeckTube5.setTextureSize(128, 64);
		NeckTube5.mirror = true;
		setRotation(NeckTube5, 0.5235988F, 0F, 0F);
		PixelmonModelRenderer NeckTube6 = new PixelmonModelRenderer(this, 0, 0);
		NeckTube6.addBox(-1F, -12.7F, -1.5F, 2, 2, 4);
		NeckTube6.setRotationPoint(0F, -31F, 10F);
		NeckTube6.setTextureSize(128, 64);
		NeckTube6.mirror = true;
		setRotation(NeckTube6, -0.3490659F, 0F, 0F);
		PixelmonModelRenderer HeadBackMid = new PixelmonModelRenderer(this, 0,
				0);
		HeadBackMid.addBox(-2.5F, -8.3F, 7.2F, 5, 7, 3);
		HeadBackMid.setRotationPoint(0F, -40F, 5F);
		HeadBackMid.setTextureSize(128, 64);
		HeadBackMid.mirror = true;
		setRotation(HeadBackMid, 0.2617994F, 0F, 0F);
		PixelmonModelRenderer HeadBackMidLower = new PixelmonModelRenderer(
				this, 0, 0);
		HeadBackMidLower.addBox(-2.5F, -7.8F, 3.7F, 5, 6, 3);
		HeadBackMidLower.setRotationPoint(0F, -40F, 5F);
		HeadBackMidLower.setTextureSize(128, 64);
		HeadBackMidLower.mirror = true;
		setRotation(HeadBackMidLower, -0.4712389F, 0F, 0F);
		PixelmonModelRenderer HeadBackMidR = new PixelmonModelRenderer(this, 0,
				0);
		HeadBackMidR.addBox(-2.7F, -8.1F, 6.7F, 7, 7, 3);
		HeadBackMidR.setRotationPoint(0F, -40F, 5F);
		HeadBackMidR.setTextureSize(128, 64);
		HeadBackMidR.mirror = true;
		setRotation(HeadBackMidR, 0.2268928F, -0.6457718F, -0.1570796F);
		PixelmonModelRenderer HeadBackMidL = new PixelmonModelRenderer(this, 0,
				0);
		HeadBackMidL.addBox(-4.2F, -8.1F, 6.7F, 7, 7, 3);
		HeadBackMidL.setRotationPoint(0F, -40F, 5F);
		HeadBackMidL.setTextureSize(128, 64);
		HeadBackMidL.mirror = true;
		setRotation(HeadBackMidL, 0.2268928F, 0.6457718F, 0.1570796F);
		PixelmonModelRenderer LowerJawLFlat = new PixelmonModelRenderer(this,
				39, 47);
		LowerJawLFlat.addBox(0.6F, 2.7F, -10.5F, 7, 0, 17);
		LowerJawLFlat.setRotationPoint(0F, -40F, 5F);
		LowerJawLFlat.setTextureSize(128, 64);
		LowerJawLFlat.mirror = true;
		setRotation(LowerJawLFlat, 0.0942478F, 0.0349066F, -0.5235988F);
		PixelmonModelRenderer LowerJawRFlat = new PixelmonModelRenderer(this,
				53, 47);
		LowerJawRFlat.addBox(-7.6F, 2.7F, -10.5F, 7, 0, 17);
		LowerJawRFlat.setRotationPoint(0F, -40F, 5F);
		LowerJawRFlat.setTextureSize(128, 64);
		LowerJawRFlat.mirror = true;
		setRotation(LowerJawRFlat, 0.0942478F, -0.0349066F, 0.5235988F);
		PixelmonModelRenderer FaceLFill = new PixelmonModelRenderer(this, 0, 0);
		FaceLFill.addBox(3F, -8F, 1.3F, 3, 5, 7);
		FaceLFill.setRotationPoint(0F, -40F, 5F);
		FaceLFill.setTextureSize(128, 64);
		FaceLFill.mirror = true;
		setRotation(FaceLFill, 0.2617994F, 0.3490659F, 0.0872665F);
		PixelmonModelRenderer FaceRFill = new PixelmonModelRenderer(this, 0, 0);
		FaceRFill.addBox(-6F, -8F, 1.3F, 3, 5, 7);
		FaceRFill.setRotationPoint(0F, -40F, 5F);
		FaceRFill.setTextureSize(128, 64);
		FaceRFill.mirror = true;
		setRotation(FaceRFill, 0.2617994F, -0.3490659F, -0.0872665F);
		PixelmonModelRenderer HeadTopBack = new PixelmonModelRenderer(this, 0,
				0);
		HeadTopBack.addBox(-2.5F, -11.3F, 9F, 5, 6, 3);
		HeadTopBack.setRotationPoint(0F, -40F, 5F);
		HeadTopBack.setTextureSize(128, 64);
		HeadTopBack.mirror = true;
		setRotation(HeadTopBack, 0.5235988F, 0F, 0F);
		PixelmonModelRenderer HeadTopTipL = new PixelmonModelRenderer(this, 0,
				0);
		HeadTopTipL.addBox(-5.7F, -14.9F, -0.2F, 5, 3, 5);
		HeadTopTipL.setRotationPoint(0F, -40F, 5F);
		HeadTopTipL.setTextureSize(128, 64);
		HeadTopTipL.mirror = true;
		setRotation(HeadTopTipL, 0F, 0F, 0.5235988F);
		PixelmonModelRenderer HeadTopTipR = new PixelmonModelRenderer(this, 0,
				0);
		HeadTopTipR.addBox(0.7F, -14.9F, -0.2F, 5, 3, 5);
		HeadTopTipR.setRotationPoint(0F, -40F, 5F);
		HeadTopTipR.setTextureSize(128, 64);
		HeadTopTipR.mirror = true;
		setRotation(HeadTopTipR, 0F, 0F, -0.5235988F);
		PixelmonModelRenderer HeadTopTip = new PixelmonModelRenderer(this, 0, 0);
		HeadTopTip.addBox(-2.5F, -15.8F, -1.2F, 5, 3, 6);
		HeadTopTip.setRotationPoint(0F, -40F, 5F);
		HeadTopTip.setTextureSize(128, 64);
		HeadTopTip.mirror = true;
		setRotation(HeadTopTip, 0F, 0F, 0F);
		PixelmonModelRenderer HornLBack = new PixelmonModelRenderer(this, 0, 0);
		HornLBack.addBox(2.1F, -17.7F, 1.3F, 2, 7, 4);
		HornLBack.setRotationPoint(0F, -40F, 5F);
		HornLBack.setTextureSize(128, 64);
		HornLBack.mirror = true;
		setRotation(HornLBack, 0F, 0.0698132F, 0.1745329F);
		PixelmonModelRenderer HornLTip = new PixelmonModelRenderer(this, 0, 0);
		HornLTip.addBox(3.9F, -20.5F, 1.9F, 2, 1, 4);
		HornLTip.setRotationPoint(0F, -40F, 5F);
		HornLTip.setTextureSize(128, 64);
		HornLTip.mirror = true;
		setRotation(HornLTip, 0F, 0.0523599F, 0.0872665F);
		PixelmonModelRenderer HornLInner = new PixelmonModelRenderer(this, 0, 0);
		HornLInner.addBox(-0.9F, -16.7F, -3.2F, 2, 7, 4);
		HornLInner.setRotationPoint(0F, -40F, 5F);
		HornLInner.setTextureSize(128, 64);
		HornLInner.mirror = true;
		setRotation(HornLInner, -0.2617994F, 0.122173F, 0.3490659F);
		PixelmonModelRenderer HornLOuter = new PixelmonModelRenderer(this, 0, 0);
		HornLOuter.addBox(6.4F, -18F, -3.4F, 1, 6, 4);
		HornLOuter.setRotationPoint(0F, -40F, 5F);
		HornLOuter.setTextureSize(128, 64);
		HornLOuter.mirror = true;
		setRotation(HornLOuter, -0.2617994F, 0.0261799F, 0F);
		PixelmonModelRenderer HornLBase = new PixelmonModelRenderer(this, 0, 0);
		HornLBase.addBox(2.1F, -20.7F, -3.4F, 2, 9, 4);
		HornLBase.setRotationPoint(0F, -40F, 5F);
		HornLBase.setTextureSize(128, 64);
		HornLBase.mirror = true;
		setRotation(HornLBase, -0.2617994F, 0.0698132F, 0.1745329F);
		PixelmonModelRenderer HornRBack = new PixelmonModelRenderer(this, 0, 0);
		HornRBack.addBox(-4.1F, -17.7F, 1.3F, 2, 7, 4);
		HornRBack.setRotationPoint(0F, -40F, 5F);
		HornRBack.setTextureSize(128, 64);
		HornRBack.mirror = true;
		setRotation(HornRBack, 0F, -0.0698132F, -0.1745329F);
		PixelmonModelRenderer HornRInner = new PixelmonModelRenderer(this, 0, 0);
		HornRInner.addBox(-1.1F, -16.7F, -3.2F, 2, 7, 4);
		HornRInner.setRotationPoint(0F, -40F, 5F);
		HornRInner.setTextureSize(128, 64);
		HornRInner.mirror = true;
		setRotation(HornRInner, -0.2617994F, -0.122173F, -0.3490659F);
		PixelmonModelRenderer HornROuter = new PixelmonModelRenderer(this, 0, 0);
		HornROuter.addBox(-7.4F, -18F, -3.4F, 1, 6, 4);
		HornROuter.setRotationPoint(0F, -40F, 5F);
		HornROuter.setTextureSize(128, 64);
		HornROuter.mirror = true;
		setRotation(HornROuter, -0.2617994F, -0.0261799F, 0F);
		PixelmonModelRenderer HornRTip = new PixelmonModelRenderer(this, 0, 0);
		HornRTip.addBox(-5.9F, -20.5F, 1.9F, 2, 1, 4);
		HornRTip.setRotationPoint(0F, -40F, 5F);
		HornRTip.setTextureSize(128, 64);
		HornRTip.mirror = true;
		setRotation(HornRTip, 0F, -0.0523599F, -0.0872665F);
		PixelmonModelRenderer HornRBase = new PixelmonModelRenderer(this, 0, 0);
		HornRBase.addBox(-4.1F, -20.7F, -3.4F, 2, 9, 4);
		HornRBase.setRotationPoint(0F, -40F, 5F);
		HornRBase.setTextureSize(128, 64);
		HornRBase.mirror = true;
		setRotation(HornRBase, -0.2617994F, -0.0698132F, -0.1745329F);
		PixelmonModelRenderer ForeheadL = new PixelmonModelRenderer(this, 0, 0);
		ForeheadL.addBox(-6.5F, -10.4F, 0.3F, 3, 3, 10);
		ForeheadL.setRotationPoint(0F, -40F, 5F);
		ForeheadL.setTextureSize(128, 64);
		ForeheadL.mirror = true;
		setRotation(ForeheadL, 0.9477138F, -0.6021386F, 0.5113815F);
		PixelmonModelRenderer ForeheadR = new PixelmonModelRenderer(this, 0, 0);
		ForeheadR.addBox(3.5F, -10.4F, 0.3F, 3, 3, 10);
		ForeheadR.setRotationPoint(0F, -40F, 5F);
		ForeheadR.setTextureSize(128, 64);
		ForeheadR.mirror = true;
		setRotation(ForeheadR, 0.9477138F, 0.6021386F, -0.5113815F);
		PixelmonModelRenderer ForeheadEyeFlatR = new PixelmonModelRenderer(
				this, 0, 0);
		ForeheadEyeFlatR.addBox(-2.2F, -10.7F, -2.4F, 6, 1, 13);
		ForeheadEyeFlatR.setRotationPoint(0F, -40F, 5F);
		ForeheadEyeFlatR.setTextureSize(128, 64);
		ForeheadEyeFlatR.mirror = true;
		setRotation(ForeheadEyeFlatR, 0.8203047F, -0.0698132F, -0.3665191F);
		PixelmonModelRenderer EyeL = new PixelmonModelRenderer(this, 4, 18);
		EyeL.addBox(3F, -8.8F, -2.5F, 3, 7, 14);
		EyeL.setRotationPoint(0F, -40F, 5F);
		EyeL.setTextureSize(128, 64);
		EyeL.mirror = true;
		setRotation(EyeL, 0.7504916F, 0.3490659F, 0.0872665F);
		PixelmonModelRenderer Forehead = new PixelmonModelRenderer(this, 0, 0);
		Forehead.addBox(-2.5F, -12F, -2.7F, 5, 3, 13);
		Forehead.setRotationPoint(0F, -40F, 5F);
		Forehead.setTextureSize(128, 64);
		Forehead.mirror = true;
		setRotation(Forehead, 0.7853982F, 0F, 0F);
		PixelmonModelRenderer NoseTop = new PixelmonModelRenderer(this, 0, 0);
		NoseTop.addBox(-1.5F, -2.4F, -11F, 3, 7, 3);
		NoseTop.setRotationPoint(0F, -40F, 5F);
		NoseTop.setTextureSize(128, 64);
		NoseTop.mirror = true;
		setRotation(NoseTop, -0.4363323F, 0F, 0F);
		PixelmonModelRenderer Nose = new PixelmonModelRenderer(this, 36, 41);
		Nose.addBox(-1.5F, -3.5F, -11.4F, 3, 3, 3);
		Nose.setRotationPoint(0F, -40F, 5F);
		Nose.setTextureSize(128, 64);
		Nose.mirror = true;
		setRotation(Nose, 0.2617994F, 0F, 0F);
		PixelmonModelRenderer BrowMid = new PixelmonModelRenderer(this, 0, 0);
		BrowMid.addBox(-2.5F, -5.7F, -10.9F, 5, 6, 3);
		BrowMid.setRotationPoint(0F, -40F, 5F);
		BrowMid.setTextureSize(128, 64);
		BrowMid.mirror = true;
		setRotation(BrowMid, -0.0872665F, 0F, 0F);
		PixelmonModelRenderer LowerJawMid = new PixelmonModelRenderer(this, 0,
				0);
		LowerJawMid.addBox(-2F, -1F, -10.5F, 4, 3, 17);
		LowerJawMid.setRotationPoint(0F, -40F, 5F);
		LowerJawMid.setTextureSize(128, 64);
		LowerJawMid.mirror = true;
		setRotation(LowerJawMid, 0.0872665F, 0F, 0F);
		PixelmonModelRenderer NoseR = new PixelmonModelRenderer(this, 39, 36);
		NoseR.addBox(-8.5F, -3.6F, -7.7F, 2, 3, 2);
		NoseR.setRotationPoint(0F, -40F, 5F);
		NoseR.setTextureSize(128, 64);
		NoseR.mirror = true;
		setRotation(NoseR, 0.2007129F, -0.6981317F, -0.1745329F);
		PixelmonModelRenderer NoseL = new PixelmonModelRenderer(this, 41, 36);
		NoseL.addBox(6.5F, -3.6F, -7.7F, 2, 3, 2);
		NoseL.setRotationPoint(0F, -40F, 5F);
		NoseL.setTextureSize(128, 64);
		NoseL.mirror = true;
		setRotation(NoseL, 0.2007129F, 0.6981317F, 0.1745329F);
		PixelmonModelRenderer JawL = new PixelmonModelRenderer(this, 0, 0);
		JawL.addBox(3F, -3F, -4.7F, 3, 3, 13);
		JawL.setRotationPoint(0F, -40F, 5F);
		JawL.setTextureSize(128, 64);
		JawL.mirror = true;
		setRotation(JawL, 0.2617994F, 0.3490659F, 0.0872665F);
		PixelmonModelRenderer EyeR = new PixelmonModelRenderer(this, 1, 25);
		EyeR.addBox(-6F, -8.8F, -2.5F, 3, 7, 14);
		EyeR.setRotationPoint(0F, -40F, 5F);
		EyeR.setTextureSize(128, 64);
		EyeR.mirror = true;
		setRotation(EyeR, 0.7504916F, -0.3490659F, -0.0872665F);
		PixelmonModelRenderer JawR = new PixelmonModelRenderer(this, 0, 0);
		JawR.addBox(-6F, -3F, -4.7F, 3, 3, 13);
		JawR.setRotationPoint(0F, -40F, 5F);
		JawR.setTextureSize(128, 64);
		JawR.mirror = true;
		setRotation(JawR, 0.2617994F, -0.3490659F, -0.0872665F);
		PixelmonModelRenderer NoseFlatFillLowerL = new PixelmonModelRenderer(
				this, 12, 46);
		NoseFlatFillLowerL.addBox(5.8F, -4.5F, -8.7F, 1, 5, 6);
		NoseFlatFillLowerL.setRotationPoint(0F, -40F, 5F);
		NoseFlatFillLowerL.setTextureSize(128, 64);
		NoseFlatFillLowerL.mirror = true;
		setRotation(NoseFlatFillLowerL, 0.122173F, 0.4537856F, 0.1134464F);
		PixelmonModelRenderer NoseFlatFillUpperL = new PixelmonModelRenderer(
				this, 18, 49);
		NoseFlatFillUpperL.addBox(7.3F, -7.2F, -8.7F, 1, 7, 8);
		NoseFlatFillUpperL.setRotationPoint(0F, -40F, 5F);
		NoseFlatFillUpperL.setTextureSize(128, 64);
		NoseFlatFillUpperL.mirror = true;
		setRotation(NoseFlatFillUpperL, 0.1570796F, 0.6283185F, -0.0610865F);
		PixelmonModelRenderer NoseFlatFillUpperR = new PixelmonModelRenderer(
				this, 0, 49);
		NoseFlatFillUpperR.addBox(-8.3F, -7.2F, -7.7F, 0, 7, 8);
		NoseFlatFillUpperR.setRotationPoint(0F, -40F, 5F);
		NoseFlatFillUpperR.setTextureSize(128, 64);
		NoseFlatFillUpperR.mirror = true;
		setRotation(NoseFlatFillUpperR, 0.1570796F, -0.6283185F, 0.0610865F);
		PixelmonModelRenderer NoseFlatFillLowerR = new PixelmonModelRenderer(
				this, 0, 46);
		NoseFlatFillLowerR.addBox(-6.8F, -4.5F, -8.7F, 0, 5, 6);
		NoseFlatFillLowerR.setRotationPoint(0F, -40F, 5F);
		NoseFlatFillLowerR.setTextureSize(128, 64);
		NoseFlatFillLowerR.mirror = true;
		setRotation(NoseFlatFillLowerR, 0.122173F, -0.4537856F, -0.1134464F);
		PixelmonModelRenderer ForeheadFrontFlatR = new PixelmonModelRenderer(
				this, 95, 32);
		ForeheadFrontFlatR.addBox(3.5F, -10.4F, -2.7F, 3, 0, 3);
		ForeheadFrontFlatR.setRotationPoint(0F, -40F, 5F);
		ForeheadFrontFlatR.setTextureSize(128, 64);
		ForeheadFrontFlatR.mirror = true;
		setRotation(ForeheadFrontFlatR, 0.9477138F, 0.6021386F, -0.5113815F);
		PixelmonModelRenderer ForeheadFrontFlatL = new PixelmonModelRenderer(
				this, 101, 32);
		ForeheadFrontFlatL.addBox(-6.5F, -10.4F, -2.7F, 3, 0, 3);
		ForeheadFrontFlatL.setRotationPoint(0F, -40F, 5F);
		ForeheadFrontFlatL.setTextureSize(128, 64);
		ForeheadFrontFlatL.mirror = true;
		setRotation(ForeheadFrontFlatL, 0.9477138F, -0.6021386F, 0.5113815F);
		PixelmonModelRenderer HeadBackMidLowerFilllR = new PixelmonModelRenderer(
				this, 70, 34);
		HeadBackMidLowerFilllR.addBox(-2.7F, -7.1F, 6.6F, 7, 6, 0);
		HeadBackMidLowerFilllR.setRotationPoint(0F, -40F, 5F);
		HeadBackMidLowerFilllR.setTextureSize(128, 64);
		HeadBackMidLowerFilllR.mirror = true;
		setRotation(HeadBackMidLowerFilllR, -0.4712389F, -0.6457718F,
				-0.1570796F);
		PixelmonModelRenderer HeadBackMidLowerFilllL = new PixelmonModelRenderer(
				this, 56, 34);
		HeadBackMidLowerFilllL.addBox(-4.3F, -7.1F, 6.6F, 7, 6, 0);
		HeadBackMidLowerFilllL.setRotationPoint(0F, -40F, 5F);
		HeadBackMidLowerFilllL.setTextureSize(128, 64);
		HeadBackMidLowerFilllL.mirror = true;
		setRotation(HeadBackMidLowerFilllL, -0.4712389F, 0.6457718F, 0.1570796F);
		PixelmonModelRenderer HeadBackMidUpperFillR = new PixelmonModelRenderer(
				this, 58, 41);
		HeadBackMidUpperFillR.addBox(-0.7F, -12.2F, 10.9F, 5, 6, 0);
		HeadBackMidUpperFillR.setRotationPoint(0F, -40F, 5F);
		HeadBackMidUpperFillR.setTextureSize(128, 64);
		HeadBackMidUpperFillR.mirror = true;
		setRotation(HeadBackMidUpperFillR, 0.4014257F, -0.6457718F, -0.1570796F);
		PixelmonModelRenderer HeadBackMidUpperFillL = new PixelmonModelRenderer(
				this, 48, 41);
		HeadBackMidUpperFillL.addBox(-4.3F, -12.2F, 10.9F, 5, 6, 0);
		HeadBackMidUpperFillL.setRotationPoint(0F, -40F, 5F);
		HeadBackMidUpperFillL.setTextureSize(128, 64);
		HeadBackMidUpperFillL.mirror = true;
		setRotation(HeadBackMidUpperFillL, 0.4014257F, 0.6457718F, 0.1570796F);
		PixelmonModelRenderer NoseTopFillR = new PixelmonModelRenderer(this,
				94, 32);
		NoseTopFillR.addBox(5.5F, -0.4F, -9.1F, 2, 3, 0);
		NoseTopFillR.setRotationPoint(0F, -40F, 5F);
		NoseTopFillR.setTextureSize(128, 64);
		NoseTopFillR.mirror = true;
		setRotation(NoseTopFillR, -0.3316126F, 0.7853982F, 0F);
		PixelmonModelRenderer NoseTopFillL = new PixelmonModelRenderer(this,
				90, 32);
		NoseTopFillL.addBox(-7.5F, -0.4F, -9.1F, 2, 3, 0);
		NoseTopFillL.setRotationPoint(0F, -40F, 5F);
		NoseTopFillL.setTextureSize(128, 64);
		NoseTopFillL.mirror = true;
		setRotation(NoseTopFillL, -0.3316126F, -0.7853982F, 0F);
		PixelmonModelRenderer Mouth = new PixelmonModelRenderer(this, 36, 41);
		Mouth.addBox(-1.5F, 6.8F, -7F, 3, 2, 1);
		Mouth.setRotationPoint(0F, -40F, 5F);
		Mouth.setTextureSize(128, 64);
		Mouth.mirror = true;
		setRotation(Mouth, -0.6981317F, 0F, 0F);
		PixelmonModelRenderer ForeheadFillFlatLL = new PixelmonModelRenderer(
				this, 60, 17);
		ForeheadFillFlatLL.addBox(10.4F, -7.1F, 0.8F, 0, 5, 12);
		ForeheadFillFlatLL.setRotationPoint(0F, -40F, 5F);
		ForeheadFillFlatLL.setTextureSize(128, 64);
		ForeheadFillFlatLL.mirror = true;
		setRotation(ForeheadFillFlatLL, 0.863938F, 0.9075712F, -0.4712389F);
		PixelmonModelRenderer ForeheadFillFlatL = new PixelmonModelRenderer(
				this, 20, 48);
		ForeheadFillFlatL.addBox(-1.8F, -12.1F, 3.8F, 5, 0, 9);
		ForeheadFillFlatL.setRotationPoint(0F, -40F, 5F);
		ForeheadFillFlatL.setTextureSize(128, 64);
		ForeheadFillFlatL.mirror = true;
		setRotation(ForeheadFillFlatL, 0.8203047F, -0.2617994F, 0.2443461F);
		PixelmonModelRenderer HeadTopTipRFill = new PixelmonModelRenderer(this,
				46, 36);
		HeadTopTipRFill.addBox(-6F, -14.6F, -7.2F, 5, 0, 5);
		HeadTopTipRFill.setRotationPoint(0F, -40F, 5F);
		HeadTopTipRFill.setTextureSize(128, 64);
		HeadTopTipRFill.mirror = true;
		setRotation(HeadTopTipRFill, -0.3490659F, 0F, -0.0872665F);
		PixelmonModelRenderer ForeheadFillFlatRR = new PixelmonModelRenderer(
				this, 60, 12);
		ForeheadFillFlatRR.addBox(-10.4F, -7.1F, 0.8F, 0, 5, 12);
		ForeheadFillFlatRR.setRotationPoint(0F, -40F, 5F);
		ForeheadFillFlatRR.setTextureSize(128, 64);
		ForeheadFillFlatRR.mirror = true;
		setRotation(ForeheadFillFlatRR, 0.863938F, -0.9075712F, 0.4712389F);
		PixelmonModelRenderer ForeheadFillFlatR = new PixelmonModelRenderer(
				this, 59, 15);
		ForeheadFillFlatR.addBox(-3.2F, -12.1F, 3.8F, 5, 0, 9);
		ForeheadFillFlatR.setRotationPoint(0F, -40F, 5F);
		ForeheadFillFlatR.setTextureSize(128, 64);
		ForeheadFillFlatR.mirror = true;
		setRotation(ForeheadFillFlatR, 0.8203047F, 0.2617994F, -0.2443461F);
		PixelmonModelRenderer HeadTopTipLFill = new PixelmonModelRenderer(this,
				-5, 47);
		HeadTopTipLFill.addBox(1F, -14.6F, -7.2F, 5, 0, 5);
		HeadTopTipLFill.setRotationPoint(0F, -40F, 5F);
		HeadTopTipLFill.setTextureSize(128, 64);
		HeadTopTipLFill.mirror = true;
		setRotation(HeadTopTipLFill, -0.3490659F, 0F, 0.0872665F);
		PixelmonModelRenderer ForeheadEyeFlatL = new PixelmonModelRenderer(
				this, 0, 0);
		ForeheadEyeFlatL.addBox(-3.8F, -10.7F, -2.4F, 6, 1, 13);
		ForeheadEyeFlatL.setRotationPoint(0F, -40F, 5F);
		ForeheadEyeFlatL.setTextureSize(128, 64);
		ForeheadEyeFlatL.mirror = true;
		setRotation(ForeheadEyeFlatL, 0.8203047F, 0.0698132F, 0.3665191F);

		Head.addChild(NeckTube1);
		Head.addChild(NeckTube2);
		Head.addChild(NeckTube3);
		Head.addChild(NeckTube4);
		Head.addChild(NeckTube5);
		Head.addChild(NeckTube6);
		Head.addChild(HeadBackMid);
		Head.addChild(HeadBackMidLower);
		Head.addChild(HeadBackMidR);
		Head.addChild(HeadBackMidL);
		Head.addChild(LowerJawLFlat);
		Head.addChild(LowerJawRFlat);
		Head.addChild(FaceLFill);
		Head.addChild(FaceRFill);
		Head.addChild(HeadTopBack);
		Head.addChild(HeadTopTipL);
		Head.addChild(HeadTopTipR);
		Head.addChild(HeadTopTip);
		Head.addChild(HornLBack);
		Head.addChild(HornLTip);
		Head.addChild(HornLInner);
		Head.addChild(HornLOuter);
		Head.addChild(HornLBase);
		Head.addChild(HornRBack);
		Head.addChild(HornRInner);
		Head.addChild(HornROuter);
		Head.addChild(HornRTip);
		Head.addChild(HornRBase);
		Head.addChild(ForeheadL);
		Head.addChild(ForeheadR);
		Head.addChild(ForeheadEyeFlatR);
		Head.addChild(EyeL);
		Head.addChild(Forehead);
		Head.addChild(NoseTop);
		Head.addChild(Nose);
		Head.addChild(BrowMid);
		Head.addChild(LowerJawMid);
		Head.addChild(NoseR);
		Head.addChild(NoseL);
		Head.addChild(JawL);
		Head.addChild(EyeR);
		Head.addChild(JawR);
		Head.addChild(NoseFlatFillLowerL);
		Head.addChild(NoseFlatFillUpperL);
		Head.addChild(NoseFlatFillUpperR);
		Head.addChild(NoseFlatFillLowerR);
		Head.addChild(ForeheadFrontFlatR);
		Head.addChild(ForeheadFrontFlatL);
		Head.addChild(HeadBackMidLowerFilllR);
		Head.addChild(HeadBackMidLowerFilllL);
		Head.addChild(HeadBackMidUpperFillR);
		Head.addChild(HeadBackMidUpperFillL);
		Head.addChild(NoseTopFillR);
		Head.addChild(NoseTopFillL);
		Head.addChild(Mouth);
		Head.addChild(ForeheadFillFlatLL);
		Head.addChild(ForeheadFillFlatL);
		Head.addChild(HeadTopTipRFill);
		Head.addChild(ForeheadFillFlatRR);
		Head.addChild(ForeheadFillFlatR);
		Head.addChild(HeadTopTipLFill);
		Head.addChild(ForeheadEyeFlatL);
		Body.addChild(Head);

		PixelmonModelRenderer LArm = new PixelmonModelRenderer(this, "Left Arm");
		LArm.setRotationPoint(0, 0, 0);
		PixelmonModelRenderer ArmUpperL1 = new PixelmonModelRenderer(this, 0, 0);
		ArmUpperL1.addBox(-1F, -2F, -1F, 14, 3, 3);
		ArmUpperL1.setRotationPoint(6F, -32F, 4F);
		ArmUpperL1.setTextureSize(128, 64);
		ArmUpperL1.mirror = true;
		setRotation(ArmUpperL1, 0F, 0F, 0.9599311F);
		PixelmonModelRenderer ArmUpperL2 = new PixelmonModelRenderer(this, 0, 0);
		ArmUpperL2.addBox(-0.7F, -2F, -0.5F, 14, 3, 3);
		ArmUpperL2.setRotationPoint(6F, -32F, 5F);
		ArmUpperL2.setTextureSize(128, 64);
		ArmUpperL2.mirror = true;
		setRotation(ArmUpperL2, -0.1396263F, 0.1745329F, 0.9529498F);
		PixelmonModelRenderer ArmLowerL1 = new PixelmonModelRenderer(this, 0, 0);
		ArmLowerL1.addBox(-1F, 0F, -2F, 3, 14, 3);
		ArmLowerL1.setRotationPoint(13F, -22F, 5F);
		ArmLowerL1.setTextureSize(128, 64);
		ArmLowerL1.mirror = true;
		setRotation(ArmLowerL1, -0.2443461F, 0F, -0.1047198F);
		PixelmonModelRenderer ArmLowerL2 = new PixelmonModelRenderer(this, 0, 0);
		ArmLowerL2.addBox(-1F, 0F, -2F, 3, 14, 3);
		ArmLowerL2.setRotationPoint(13F, -22F, 5F);
		ArmLowerL2.setTextureSize(128, 64);
		ArmLowerL2.mirror = true;
		setRotation(ArmLowerL2, -0.2443461F, 0F, -0.0349066F);
		PixelmonModelRenderer ThumbL1x1 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL1x1.addBox(1.2F, 14.8F, -0.3F, 1, 2, 1);
		ThumbL1x1.setRotationPoint(13F, -22F, 5F);
		ThumbL1x1.setTextureSize(128, 64);
		ThumbL1x1.mirror = true;
		setRotation(ThumbL1x1, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer BackFingerL3x1 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL3x1.addBox(1.3F, 17.5F, -4.1F, 1, 1, 1);
		BackFingerL3x1.setRotationPoint(13F, -22F, 5F);
		BackFingerL3x1.setTextureSize(128, 64);
		BackFingerL3x1.mirror = true;
		setRotation(BackFingerL3x1, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL1x2 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL1x2.addBox(1.5F, 14.8F, -0.3F, 1, 2, 1);
		ThumbL1x2.setRotationPoint(13F, -22F, 5F);
		ThumbL1x2.setTextureSize(128, 64);
		ThumbL1x2.mirror = true;
		setRotation(ThumbL1x2, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer BackFingerL3x4 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL3x4.addBox(1F, 17.5F, -4.1F, 1, 1, 1);
		BackFingerL3x4.setRotationPoint(13F, -22F, 5F);
		BackFingerL3x4.setTextureSize(128, 64);
		BackFingerL3x4.mirror = true;
		setRotation(BackFingerL3x4, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL1x3 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL1x3.addBox(1.5F, 13.8F, 0F, 1, 3, 1);
		ThumbL1x3.setRotationPoint(13F, -22F, 5F);
		ThumbL1x3.setTextureSize(128, 64);
		ThumbL1x3.mirror = true;
		setRotation(ThumbL1x3, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer BackFingerL3x3 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL3x3.addBox(1F, 17.5F, -3.8F, 1, 1, 1);
		BackFingerL3x3.setRotationPoint(13F, -22F, 5F);
		BackFingerL3x3.setTextureSize(128, 64);
		BackFingerL3x3.mirror = true;
		setRotation(BackFingerL3x3, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL1x4 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL1x4.addBox(1.2F, 14.8F, 0F, 1, 2, 1);
		ThumbL1x4.setRotationPoint(13F, -22F, 5F);
		ThumbL1x4.setTextureSize(128, 64);
		ThumbL1x4.mirror = true;
		setRotation(ThumbL1x4, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer BackFingerL3x2 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL3x2.addBox(1.3F, 17.5F, -3.8F, 1, 1, 1);
		BackFingerL3x2.setRotationPoint(13F, -22F, 5F);
		BackFingerL3x2.setTextureSize(128, 64);
		BackFingerL3x2.mirror = true;
		setRotation(BackFingerL3x2, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL = new PixelmonModelRenderer(this, 0, 0);
		ThumbL.addBox(2.7F, 12.3F, -1.5F, 2, 3, 2);
		ThumbL.setRotationPoint(13F, -22F, 5F);
		ThumbL.setTextureSize(128, 64);
		ThumbL.mirror = true;
		setRotation(ThumbL, -0.2443461F, 0F, 0.2617994F);
		PixelmonModelRenderer FrontFingerL3x4 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL3x4.addBox(1F, 18.2F, -1.8F, 1, 1, 1);
		FrontFingerL3x4.setRotationPoint(13F, -22F, 5F);
		FrontFingerL3x4.setTextureSize(128, 64);
		FrontFingerL3x4.mirror = true;
		setRotation(FrontFingerL3x4, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer MidFingerL = new PixelmonModelRenderer(this, 0, 0);
		MidFingerL.addBox(-4.7F, 13F, 1.3F, 2, 3, 2);
		MidFingerL.setRotationPoint(13F, -22F, 5F);
		MidFingerL.setTextureSize(128, 64);
		MidFingerL.mirror = true;
		setRotation(MidFingerL, -0.5235988F, -0.0698132F, -0.4537856F);
		PixelmonModelRenderer FrontFingerL3x1 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL3x1.addBox(1.3F, 18.2F, -1.8F, 1, 1, 1);
		FrontFingerL3x1.setRotationPoint(13F, -22F, 5F);
		FrontFingerL3x1.setTextureSize(128, 64);
		FrontFingerL3x1.mirror = true;
		setRotation(FrontFingerL3x1, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FingerBackL = new PixelmonModelRenderer(this, 0,
				0);
		FingerBackL.addBox(-4.7F, 12F, -5.7F, 2, 3, 2);
		FingerBackL.setRotationPoint(13F, -22F, 5F);
		FingerBackL.setTextureSize(128, 64);
		FingerBackL.mirror = true;
		setRotation(FingerBackL, 0.0872665F, -0.0698132F, -0.4537856F);
		PixelmonModelRenderer FrontFingerL3x2 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL3x2.addBox(1.3F, 18.2F, -1.5F, 1, 1, 1);
		FrontFingerL3x2.setRotationPoint(13F, -22F, 5F);
		FrontFingerL3x2.setTextureSize(128, 64);
		FrontFingerL3x2.mirror = true;
		setRotation(FrontFingerL3x2, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL1x1 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL1x1.addBox(1.3F, 16F, -1.8F, 1, 2, 1);
		FrontFingerL1x1.setRotationPoint(13F, -22F, 5F);
		FrontFingerL1x1.setTextureSize(128, 64);
		FrontFingerL1x1.mirror = true;
		setRotation(FrontFingerL1x1, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL3x3 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL3x3.addBox(1F, 18.2F, -1.5F, 1, 1, 1);
		FrontFingerL3x3.setRotationPoint(13F, -22F, 5F);
		FrontFingerL3x3.setTextureSize(128, 64);
		FrontFingerL3x3.mirror = true;
		setRotation(FrontFingerL3x3, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL1x3 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL1x3.addBox(1F, 16F, -1.8F, 1, 2, 1);
		FrontFingerL1x3.setRotationPoint(13F, -22F, 5F);
		FrontFingerL1x3.setTextureSize(128, 64);
		FrontFingerL1x3.mirror = true;
		setRotation(FrontFingerL1x3, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL1x2 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL1x2.addBox(1.3F, 16F, -1.5F, 1, 2, 1);
		FrontFingerL1x2.setRotationPoint(13F, -22F, 5F);
		FrontFingerL1x2.setTextureSize(128, 64);
		FrontFingerL1x2.mirror = true;
		setRotation(FrontFingerL1x2, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL3x3 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL3x3.addBox(1.2F, 17F, 5.551115E-17F, 1, 1, 1);
		ThumbL3x3.setRotationPoint(13F, -22F, 5F);
		ThumbL3x3.setTextureSize(128, 64);
		ThumbL3x3.mirror = true;
		setRotation(ThumbL3x3, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer FrontFingerL1x4 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL1x4.addBox(1F, 15F, -1.5F, 1, 3, 1);
		FrontFingerL1x4.setRotationPoint(13F, -22F, 5F);
		FrontFingerL1x4.setTextureSize(128, 64);
		FrontFingerL1x4.mirror = true;
		setRotation(FrontFingerL1x4, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer BackFingerL1x4 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL1x4.addBox(1F, 15.3F, -4.1F, 1, 2, 1);
		BackFingerL1x4.setRotationPoint(13F, -22F, 5F);
		BackFingerL1x4.setTextureSize(128, 64);
		BackFingerL1x4.mirror = true;
		setRotation(BackFingerL1x4, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL3x2 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL3x2.addBox(1.2F, 17F, -0.3F, 1, 1, 1);
		ThumbL3x2.setRotationPoint(13F, -22F, 5F);
		ThumbL3x2.setTextureSize(128, 64);
		ThumbL3x2.mirror = true;
		setRotation(ThumbL3x2, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer ThumbL3x1 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL3x1.addBox(1.5F, 17F, -0.3F, 1, 1, 1);
		ThumbL3x1.setRotationPoint(13F, -22F, 5F);
		ThumbL3x1.setTextureSize(128, 64);
		ThumbL3x1.mirror = true;
		setRotation(ThumbL3x1, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer BackFingerL1x2 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL1x2.addBox(1.3F, 15.3F, -4.1F, 1, 2, 1);
		BackFingerL1x2.setRotationPoint(13F, -22F, 5F);
		BackFingerL1x2.setTextureSize(128, 64);
		BackFingerL1x2.mirror = true;
		setRotation(BackFingerL1x2, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer BackFingerL1x1 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL1x1.addBox(1.3F, 15.3F, -3.8F, 1, 2, 1);
		BackFingerL1x1.setRotationPoint(13F, -22F, 5F);
		BackFingerL1x1.setTextureSize(128, 64);
		BackFingerL1x1.mirror = true;
		setRotation(BackFingerL1x1, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL3x4 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL3x4.addBox(1.5F, 17F, 5.551115E-17F, 1, 1, 1);
		ThumbL3x4.setRotationPoint(13F, -22F, 5F);
		ThumbL3x4.setTextureSize(128, 64);
		ThumbL3x4.mirror = true;
		setRotation(ThumbL3x4, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer BackFingerL1x3 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL1x3.addBox(1F, 14.3F, -3.8F, 1, 3, 1);
		BackFingerL1x3.setRotationPoint(13F, -22F, 5F);
		BackFingerL1x3.setTextureSize(128, 64);
		BackFingerL1x3.mirror = true;
		setRotation(BackFingerL1x3, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL4 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL4.addBox(1.1F, 18.4F, -1.7F, 1, 1, 1);
		FrontFingerL4.setRotationPoint(13F, -22F, 5F);
		FrontFingerL4.setTextureSize(128, 64);
		FrontFingerL4.mirror = true;
		setRotation(FrontFingerL4, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer BackFingerL2x1 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL2x1.addBox(1.5F, 17.3F, -4.3F, 1, 1, 1);
		BackFingerL2x1.setRotationPoint(13F, -22F, 5F);
		BackFingerL2x1.setTextureSize(128, 64);
		BackFingerL2x1.mirror = true;
		setRotation(BackFingerL2x1, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer BackFingerL2x2 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL2x2.addBox(1.5F, 17.3F, -3.6F, 1, 1, 1);
		BackFingerL2x2.setRotationPoint(-13F, -22F, 5F);
		BackFingerL2x2.setTextureSize(128, 64);
		BackFingerL2x2.mirror = true;
		setRotation(BackFingerL2x2, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer BackFingerL2x4 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL2x4.addBox(0.8F, 17.3F, -4.3F, 1, 1, 1);
		BackFingerL2x4.setRotationPoint(13F, -22F, 5F);
		BackFingerL2x4.setTextureSize(128, 64);
		BackFingerL2x4.mirror = true;
		setRotation(BackFingerL2x4, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer BackFingerL2x3 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerL2x3.addBox(0.8F, 17.3F, -3.6F, 1, 1, 1);
		BackFingerL2x3.setRotationPoint(13F, -22F, 5F);
		BackFingerL2x3.setTextureSize(128, 64);
		BackFingerL2x3.mirror = true;
		setRotation(BackFingerL2x3, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL2x3 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL2x3.addBox(0.8F, 18F, -1.3F, 1, 1, 1);
		FrontFingerL2x3.setRotationPoint(13F, -22F, 5F);
		FrontFingerL2x3.setTextureSize(128, 64);
		FrontFingerL2x3.mirror = true;
		setRotation(FrontFingerL2x3, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL2x1 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL2x1.addBox(1.5F, 18F, -2F, 1, 1, 1);
		FrontFingerL2x1.setRotationPoint(13F, -22F, 5F);
		FrontFingerL2x1.setTextureSize(128, 64);
		FrontFingerL2x1.mirror = true;
		setRotation(FrontFingerL2x1, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL2x2 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL2x2.addBox(1.5F, 18F, -1.3F, 1, 1, 1);
		FrontFingerL2x2.setRotationPoint(13F, -22F, 5F);
		FrontFingerL2x2.setTextureSize(128, 64);
		FrontFingerL2x2.mirror = true;
		setRotation(FrontFingerL2x2, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer FrontFingerL2x4 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerL2x4.addBox(0.8F, 18F, -2F, 1, 1, 1);
		FrontFingerL2x4.setRotationPoint(13F, -22F, 5F);
		FrontFingerL2x4.setTextureSize(128, 64);
		FrontFingerL2x4.mirror = true;
		setRotation(FrontFingerL2x4, -0.296706F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL4 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL4.addBox(1.3F, 17.2F, -0.2F, 1, 1, 1);
		ThumbL4.setRotationPoint(13F, -22F, 5F);
		ThumbL4.setTextureSize(128, 64);
		ThumbL4.mirror = true;
		setRotation(ThumbL4, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer BackFingerL4 = new PixelmonModelRenderer(this, 0,
				0);
		BackFingerL4.addBox(1.1F, 17.7F, -4F, 1, 1, 1);
		BackFingerL4.setRotationPoint(13F, -22F, 5F);
		BackFingerL4.setTextureSize(128, 64);
		BackFingerL4.mirror = true;
		setRotation(BackFingerL4, 0F, -0.0523599F, -0.1047198F);
		PixelmonModelRenderer ThumbL2x2 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL2x2.addBox(1F, 16.8F, -0.5F, 1, 1, 1);
		ThumbL2x2.setRotationPoint(13F, -22F, 5F);
		ThumbL2x2.setTextureSize(128, 64);
		ThumbL2x2.mirror = true;
		setRotation(ThumbL2x2, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer ThumbL2x3 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL2x3.addBox(1F, 16.8F, 0.2F, 1, 1, 1);
		ThumbL2x3.setRotationPoint(13F, -22F, 5F);
		ThumbL2x3.setTextureSize(128, 64);
		ThumbL2x3.mirror = true;
		setRotation(ThumbL2x3, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer ThumbL2x1 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL2x1.addBox(1.7F, 16.8F, -0.5F, 1, 1, 1);
		ThumbL2x1.setRotationPoint(13F, -22F, 5F);
		ThumbL2x1.setTextureSize(128, 64);
		ThumbL2x1.mirror = true;
		setRotation(ThumbL2x1, -0.296706F, -0.0523599F, 0.1396263F);
		PixelmonModelRenderer ThumbL2x4 = new PixelmonModelRenderer(this, 0, 0);
		ThumbL2x4.addBox(1.7F, 16.8F, 0.2F, 1, 1, 1);
		ThumbL2x4.setRotationPoint(13F, -22F, 5F);
		ThumbL2x4.setTextureSize(128, 64);
		ThumbL2x4.mirror = true;
		setRotation(ThumbL2x4, -0.296706F, -0.0523599F, 0.1396263F);

		LArm.addChild(ArmUpperL1);
		LArm.addChild(ArmUpperL2);
		LArm.addChild(ArmLowerL1);
		LArm.addChild(ArmLowerL2);
		LArm.addChild(ThumbL1x1);
		LArm.addChild(BackFingerL3x1);
		LArm.addChild(ThumbL1x2);
		LArm.addChild(BackFingerL3x4);
		LArm.addChild(ThumbL1x3);
		LArm.addChild(BackFingerL3x3);
		LArm.addChild(ThumbL1x4);
		LArm.addChild(BackFingerL3x2);
		LArm.addChild(ThumbL);
		LArm.addChild(FrontFingerL3x4);
		LArm.addChild(MidFingerL);
		LArm.addChild(FrontFingerL3x1);
		LArm.addChild(FingerBackL);
		LArm.addChild(FrontFingerL3x2);
		LArm.addChild(FrontFingerL1x1);
		LArm.addChild(FrontFingerL3x3);
		LArm.addChild(FrontFingerL1x3);
		LArm.addChild(FrontFingerL1x2);
		LArm.addChild(ThumbL3x3);
		LArm.addChild(FrontFingerL1x4);
		LArm.addChild(BackFingerL1x4);
		LArm.addChild(ThumbL3x2);
		LArm.addChild(ThumbL3x1);
		LArm.addChild(BackFingerL1x2);
		LArm.addChild(BackFingerL1x1);
		LArm.addChild(ThumbL3x4);
		LArm.addChild(BackFingerL1x3);
		LArm.addChild(FrontFingerL4);
		LArm.addChild(BackFingerL2x1);
		LArm.addChild(BackFingerL2x2);
		LArm.addChild(BackFingerL2x4);
		LArm.addChild(BackFingerL2x3);
		LArm.addChild(FrontFingerL2x3);
		LArm.addChild(FrontFingerL2x1);
		LArm.addChild(FrontFingerL2x2);
		LArm.addChild(FrontFingerL2x4);
		LArm.addChild(ThumbL4);
		LArm.addChild(BackFingerL4);
		LArm.addChild(ThumbL2x2);
		LArm.addChild(ThumbL2x3);
		LArm.addChild(ThumbL2x1);
		LArm.addChild(ThumbL2x4);
		Body.addChild(LArm);

		PixelmonModelRenderer RArm = new PixelmonModelRenderer(this,
				"Right Arm");
		RArm.setRotationPoint(0, 0, 0);

		PixelmonModelRenderer ArmUpperR1 = new PixelmonModelRenderer(this, 0, 0);
		ArmUpperR1.addBox(-13F, -2F, -2F, 14, 3, 3);
		ArmUpperR1.setRotationPoint(-6F, -32F, 5F);
		ArmUpperR1.setTextureSize(128, 64);
		ArmUpperR1.mirror = true;
		setRotation(ArmUpperR1, 0F, 0F, -0.9599311F);
		PixelmonModelRenderer ArmUpperR2 = new PixelmonModelRenderer(this, 0, 0);
		ArmUpperR2.addBox(-13.33333F, -2F, -0.5F, 14, 3, 3);
		ArmUpperR2.setRotationPoint(-6F, -32F, 5F);
		ArmUpperR2.setTextureSize(128, 64);
		ArmUpperR2.mirror = true;
		setRotation(ArmUpperR2, -0.1396263F, -0.1745329F, -0.9529498F);
		PixelmonModelRenderer ArmLowerR1 = new PixelmonModelRenderer(this, 0, 0);
		ArmLowerR1.addBox(-2F, 0F, -2F, 3, 14, 3);
		ArmLowerR1.setRotationPoint(-13F, -22F, 5F);
		ArmLowerR1.setTextureSize(128, 64);
		ArmLowerR1.mirror = true;
		setRotation(ArmLowerR1, -0.2443461F, 0F, 0.1047198F);
		PixelmonModelRenderer ArmLowerR2 = new PixelmonModelRenderer(this, 0, 0);
		ArmLowerR2.addBox(-2F, 0F, -2F, 3, 14, 3);
		ArmLowerR2.setRotationPoint(-13F, -22F, 5F);
		ArmLowerR2.setTextureSize(128, 64);
		ArmLowerR2.mirror = true;
		setRotation(ArmLowerR2, -0.2443461F, 0F, 0.0349066F);
		PixelmonModelRenderer BackFingerR3x4 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR3x4.addBox(-2F, 17.5F, -4.1F, 1, 1, 1);
		BackFingerR3x4.setRotationPoint(-13F, -22F, 5F);
		BackFingerR3x4.setTextureSize(128, 64);
		BackFingerR3x4.mirror = true;
		setRotation(BackFingerR3x4, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR3x2 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR3x2.addBox(-2.3F, 17.5F, -3.8F, 1, 1, 1);
		BackFingerR3x2.setRotationPoint(-13F, -22F, 5F);
		BackFingerR3x2.setTextureSize(128, 64);
		BackFingerR3x2.mirror = true;
		setRotation(BackFingerR3x2, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR3x3 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR3x3.addBox(-2F, 17.5F, -3.8F, 1, 1, 1);
		BackFingerR3x3.setRotationPoint(-13F, -22F, 5F);
		BackFingerR3x3.setTextureSize(128, 64);
		BackFingerR3x3.mirror = true;
		setRotation(BackFingerR3x3, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR3x1 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR3x1.addBox(-2.3F, 17.5F, -4.1F, 1, 1, 1);
		BackFingerR3x1.setRotationPoint(-13F, -22F, 5F);
		BackFingerR3x1.setTextureSize(128, 64);
		BackFingerR3x1.mirror = true;
		setRotation(BackFingerR3x1, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR2x1 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR2x1.addBox(-2.5F, 18F, -2F, 1, 1, 1);
		FrontFingerR2x1.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR2x1.setTextureSize(128, 64);
		FrontFingerR2x1.mirror = true;
		setRotation(FrontFingerR2x1, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR2x3 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR2x3.addBox(-1.8F, 18F, -1.3F, 1, 1, 1);
		FrontFingerR2x3.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR2x3.setTextureSize(128, 64);
		FrontFingerR2x3.mirror = true;
		setRotation(FrontFingerR2x3, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR2x2 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR2x2.addBox(-2.5F, 18F, -1.3F, 1, 1, 1);
		FrontFingerR2x2.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR2x2.setTextureSize(128, 64);
		FrontFingerR2x2.mirror = true;
		setRotation(FrontFingerR2x2, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR2x4 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR2x4.addBox(-1.8F, 18F, -2F, 1, 1, 1);
		FrontFingerR2x4.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR2x4.setTextureSize(128, 64);
		FrontFingerR2x4.mirror = true;
		setRotation(FrontFingerR2x4, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer ThumbR1x2 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR1x2.addBox(-2.2F, 14.8F, -0.3F, 1, 2, 1);
		ThumbR1x2.setRotationPoint(-13F, -22F, 5F);
		ThumbR1x2.setTextureSize(128, 64);
		ThumbR1x2.mirror = true;
		setRotation(ThumbR1x2, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR1x1 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR1x1.addBox(-2.5F, 14.8F, -0.3F, 1, 2, 1);
		ThumbR1x1.setRotationPoint(-13F, -22F, 5F);
		ThumbR1x1.setTextureSize(128, 64);
		ThumbR1x1.mirror = true;
		setRotation(ThumbR1x1, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR1x3 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR1x3.addBox(-2.2F, 13.8F, 0F, 1, 3, 1);
		ThumbR1x3.setRotationPoint(-13F, -22F, 5F);
		ThumbR1x3.setTextureSize(128, 64);
		ThumbR1x3.mirror = true;
		setRotation(ThumbR1x3, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR1x4 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR1x4.addBox(-2.5F, 14.8F, 0F, 1, 2, 1);
		ThumbR1x4.setRotationPoint(-13F, -22F, 5F);
		ThumbR1x4.setTextureSize(128, 64);
		ThumbR1x4.mirror = true;
		setRotation(ThumbR1x4, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer FrontFingerR3x2 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR3x2.addBox(-2.3F, 18.2F, -1.5F, 1, 1, 1);
		FrontFingerR3x2.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR3x2.setTextureSize(128, 64);
		FrontFingerR3x2.mirror = true;
		setRotation(FrontFingerR3x2, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR3x3 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR3x3.addBox(-2F, 18.2F, -1.5F, 1, 1, 1);
		FrontFingerR3x3.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR3x3.setTextureSize(128, 64);
		FrontFingerR3x3.mirror = true;
		setRotation(FrontFingerR3x3, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR3x1 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR3x1.addBox(-2.3F, 18.2F, -1.8F, 1, 1, 1);
		FrontFingerR3x1.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR3x1.setTextureSize(128, 64);
		FrontFingerR3x1.mirror = true;
		setRotation(FrontFingerR3x1, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR3x4 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR3x4.addBox(-2F, 18.2F, -1.8F, 1, 1, 1);
		FrontFingerR3x4.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR3x4.setTextureSize(128, 64);
		FrontFingerR3x4.mirror = true;
		setRotation(FrontFingerR3x4, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer MidFingerR = new PixelmonModelRenderer(this, 0, 0);
		MidFingerR.addBox(2.7F, 13F, 1.3F, 2, 3, 2);
		MidFingerR.setRotationPoint(-13F, -22F, 5F);
		MidFingerR.setTextureSize(128, 64);
		MidFingerR.mirror = true;
		setRotation(MidFingerR, -0.5235988F, 0.0698132F, 0.4537856F);
		PixelmonModelRenderer FrontFingerR4 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR4.addBox(-2.1F, 18.4F, -1.7F, 1, 1, 1);
		FrontFingerR4.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR4.setTextureSize(128, 64);
		FrontFingerR4.mirror = true;
		setRotation(FrontFingerR4, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR4 = new PixelmonModelRenderer(this, 0,
				0);
		BackFingerR4.addBox(-2.1F, 17.7F, -4F, 1, 1, 1);
		BackFingerR4.setRotationPoint(-13F, -22F, 5F);
		BackFingerR4.setTextureSize(128, 64);
		BackFingerR4.mirror = true;
		setRotation(BackFingerR4, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FingerBackR = new PixelmonModelRenderer(this, 0,
				0);
		FingerBackR.addBox(2.7F, 12F, -5.7F, 2, 3, 2);
		FingerBackR.setRotationPoint(-13F, -22F, 5F);
		FingerBackR.setTextureSize(128, 64);
		FingerBackR.mirror = true;
		setRotation(FingerBackR, 0.0872665F, 0.0698132F, 0.4537856F);
		PixelmonModelRenderer BackFingerR2x4 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR2x4.addBox(-1.8F, 17.3F, -4.3F, 1, 1, 1);
		BackFingerR2x4.setRotationPoint(-13F, -22F, 5F);
		BackFingerR2x4.setTextureSize(128, 64);
		BackFingerR2x4.mirror = true;
		setRotation(BackFingerR2x4, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR2x3 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR2x3.addBox(-1.8F, 17.3F, -3.6F, 1, 1, 1);
		BackFingerR2x3.setRotationPoint(-13F, -22F, 5F);
		BackFingerR2x3.setTextureSize(128, 64);
		BackFingerR2x3.mirror = true;
		setRotation(BackFingerR2x3, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer ThumbR3x3 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR3x3.addBox(-2.2F, 17F, 5.551115E-17F, 1, 1, 1);
		ThumbR3x3.setRotationPoint(-13F, -22F, 5F);
		ThumbR3x3.setTextureSize(128, 64);
		ThumbR3x3.mirror = true;
		setRotation(ThumbR3x3, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR3x2 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR3x2.addBox(-2.2F, 17F, -0.3F, 1, 1, 1);
		ThumbR3x2.setRotationPoint(-13F, -22F, 5F);
		ThumbR3x2.setTextureSize(128, 64);
		ThumbR3x2.mirror = true;
		setRotation(ThumbR3x2, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR3x4 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR3x4.addBox(-2.5F, 17F, 5.551115E-17F, 1, 1, 1);
		ThumbR3x4.setRotationPoint(-13F, -22F, 5F);
		ThumbR3x4.setTextureSize(128, 64);
		ThumbR3x4.mirror = true;
		setRotation(ThumbR3x4, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR3x1 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR3x1.addBox(-2.5F, 17F, -0.3F, 1, 1, 1);
		ThumbR3x1.setRotationPoint(-13F, -22F, 5F);
		ThumbR3x1.setTextureSize(128, 64);
		ThumbR3x1.mirror = true;
		setRotation(ThumbR3x1, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer FrontFingerR1x1 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR1x1.addBox(-2.3F, 16F, -1.8F, 1, 2, 1);
		FrontFingerR1x1.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR1x1.setTextureSize(128, 64);
		FrontFingerR1x1.mirror = true;
		setRotation(FrontFingerR1x1, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR2x1 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR2x1.addBox(-2.5F, 17.3F, -4.3F, 1, 1, 1);
		BackFingerR2x1.setRotationPoint(-13F, -22F, 5F);
		BackFingerR2x1.setTextureSize(128, 64);
		BackFingerR2x1.mirror = true;
		setRotation(BackFingerR2x1, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer ThumbR = new PixelmonModelRenderer(this, 0, 0);
		ThumbR.addBox(-4.7F, 12.3F, -1.5F, 2, 3, 2);
		ThumbR.setRotationPoint(-13F, -22F, 5F);
		ThumbR.setTextureSize(128, 64);
		ThumbR.mirror = true;
		setRotation(ThumbR, -0.2443461F, 0F, -0.2617994F);
		PixelmonModelRenderer BackFingerR2x2 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR2x2.addBox(-2.5F, 17.3F, -3.6F, 1, 1, 1);
		BackFingerR2x2.setRotationPoint(-13F, -22F, 5F);
		BackFingerR2x2.setTextureSize(128, 64);
		BackFingerR2x2.mirror = true;
		setRotation(BackFingerR2x2, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR1x3 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR1x3.addBox(-2F, 16F, -1.8F, 1, 2, 1);
		FrontFingerR1x3.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR1x3.setTextureSize(128, 64);
		FrontFingerR1x3.mirror = true;
		setRotation(FrontFingerR1x3, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR1x2 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR1x2.addBox(-2.3F, 16F, -1.5F, 1, 2, 1);
		FrontFingerR1x2.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR1x2.setTextureSize(128, 64);
		FrontFingerR1x2.mirror = true;
		setRotation(FrontFingerR1x2, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer FrontFingerR1x4 = new PixelmonModelRenderer(this,
				0, 0);
		FrontFingerR1x4.addBox(-2F, 15F, -1.5F, 1, 3, 1);
		FrontFingerR1x4.setRotationPoint(-13F, -22F, 5F);
		FrontFingerR1x4.setTextureSize(128, 64);
		FrontFingerR1x4.mirror = true;
		setRotation(FrontFingerR1x4, -0.296706F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR1x3 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR1x3.addBox(-2F, 14.3F, -3.8F, 1, 3, 1);
		BackFingerR1x3.setRotationPoint(-13F, -22F, 5F);
		BackFingerR1x3.setTextureSize(128, 64);
		BackFingerR1x3.mirror = true;
		setRotation(BackFingerR1x3, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR1x4 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR1x4.addBox(-2F, 15.3F, -4.1F, 1, 2, 1);
		BackFingerR1x4.setRotationPoint(-13F, -22F, 5F);
		BackFingerR1x4.setTextureSize(128, 64);
		BackFingerR1x4.mirror = true;
		setRotation(BackFingerR1x4, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR1x2 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR1x2.addBox(-2.3F, 15.3F, -4.1F, 1, 2, 1);
		BackFingerR1x2.setRotationPoint(-13F, -22F, 5F);
		BackFingerR1x2.setTextureSize(128, 64);
		BackFingerR1x2.mirror = true;
		setRotation(BackFingerR1x2, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer BackFingerR1x1 = new PixelmonModelRenderer(this,
				0, 0);
		BackFingerR1x1.addBox(-2.3F, 15.3F, -3.8F, 1, 2, 1);
		BackFingerR1x1.setRotationPoint(-13F, -22F, 5F);
		BackFingerR1x1.setTextureSize(128, 64);
		BackFingerR1x1.mirror = true;
		setRotation(BackFingerR1x1, 0F, 0.0523599F, 0.1047198F);
		PixelmonModelRenderer ThumbR4 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR4.addBox(-2.3F, 17.2F, -0.2F, 1, 1, 1);
		ThumbR4.setRotationPoint(-13F, -22F, 5F);
		ThumbR4.setTextureSize(128, 64);
		ThumbR4.mirror = true;
		setRotation(ThumbR4, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR2x3 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR2x3.addBox(-2F, 16.8F, 0.2F, 1, 1, 1);
		ThumbR2x3.setRotationPoint(-13F, -22F, 5F);
		ThumbR2x3.setTextureSize(128, 64);
		ThumbR2x3.mirror = true;
		setRotation(ThumbR2x3, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR2x4 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR2x4.addBox(-2.7F, 16.8F, 0.2F, 1, 1, 1);
		ThumbR2x4.setRotationPoint(-13F, -22F, 5F);
		ThumbR2x4.setTextureSize(128, 64);
		ThumbR2x4.mirror = true;
		setRotation(ThumbR2x4, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR2x2 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR2x2.addBox(-2F, 16.8F, -0.5F, 1, 1, 1);
		ThumbR2x2.setRotationPoint(-13F, -22F, 5F);
		ThumbR2x2.setTextureSize(128, 64);
		ThumbR2x2.mirror = true;
		setRotation(ThumbR2x2, -0.296706F, 0.0523599F, -0.1396263F);
		PixelmonModelRenderer ThumbR2x1 = new PixelmonModelRenderer(this, 0, 0);
		ThumbR2x1.addBox(-2.7F, 16.8F, -0.5F, 1, 1, 1);
		ThumbR2x1.setRotationPoint(-13F, -22F, 5F);
		ThumbR2x1.setTextureSize(128, 64);
		ThumbR2x1.mirror = true;
		setRotation(ThumbR2x1, -0.296706F, 0.0523599F, -0.1396263F);

		RArm.addChild(ArmUpperR1);
		RArm.addChild(ArmUpperR2);
		RArm.addChild(ArmLowerR1);
		RArm.addChild(ArmLowerR2);
		RArm.addChild(BackFingerR3x4);
		RArm.addChild(BackFingerR3x2);
		RArm.addChild(BackFingerR3x3);
		RArm.addChild(BackFingerR3x1);
		RArm.addChild(FrontFingerR2x1);
		RArm.addChild(FrontFingerR2x3);
		RArm.addChild(FrontFingerR2x2);
		RArm.addChild(FrontFingerR2x4);
		RArm.addChild(ThumbR1x2);
		RArm.addChild(ThumbR1x1);
		RArm.addChild(ThumbR1x3);
		RArm.addChild(ThumbR1x4);
		RArm.addChild(FrontFingerR3x2);
		RArm.addChild(FrontFingerR3x3);
		RArm.addChild(FrontFingerR3x1);
		RArm.addChild(FrontFingerR3x4);
		RArm.addChild(MidFingerR);
		RArm.addChild(FrontFingerR4);
		RArm.addChild(BackFingerR4);
		RArm.addChild(FingerBackR);
		RArm.addChild(BackFingerR2x4);
		RArm.addChild(BackFingerR2x3);
		RArm.addChild(ThumbR3x3);
		RArm.addChild(ThumbR3x2);
		RArm.addChild(ThumbR3x4);
		RArm.addChild(ThumbR3x1);
		RArm.addChild(FrontFingerR1x1);
		RArm.addChild(BackFingerR2x1);
		RArm.addChild(ThumbR);
		RArm.addChild(BackFingerR2x2);
		RArm.addChild(FrontFingerR1x3);
		RArm.addChild(FrontFingerR1x2);
		RArm.addChild(FrontFingerR1x4);
		RArm.addChild(BackFingerR1x3);
		RArm.addChild(BackFingerR1x4);
		RArm.addChild(BackFingerR1x2);
		RArm.addChild(BackFingerR1x1);
		RArm.addChild(ThumbR4);
		RArm.addChild(ThumbR2x3);
		RArm.addChild(ThumbR2x4);
		RArm.addChild(ThumbR2x2);
		RArm.addChild(ThumbR2x1);
		Body.addChild(RArm);

		PixelmonModelRenderer LLeg = new PixelmonModelRenderer(this, "Left Leg");
		LLeg.setRotationPoint(0, 0, 0);

		PixelmonModelRenderer LThighMid = new PixelmonModelRenderer(this, 0, 0);
		LThighMid.addBox(0F, -5F, -4F, 4, 8, 12);
		LThighMid.setRotationPoint(4.5F, -5F, 1F);
		LThighMid.setTextureSize(128, 64);
		LThighMid.mirror = true;
		setRotation(LThighMid, 0F, 0F, 0F);
		PixelmonModelRenderer LThighBackOuter = new PixelmonModelRenderer(this,
				0, 0);
		LThighBackOuter.addBox(-0.5F, -5F, 5.9F, 4, 8, 3);
		LThighBackOuter.setRotationPoint(4.5F, -5F, 1F);
		LThighBackOuter.setTextureSize(128, 64);
		LThighBackOuter.mirror = true;
		setRotation(LThighBackOuter, 0F, 0.5235988F, 0F);
		PixelmonModelRenderer LThighBottomFront = new PixelmonModelRenderer(
				this, 0, 0);
		LThighBottomFront.addBox(0F, 0.6F, -4.9F, 4, 6, 3);
		LThighBottomFront.setRotationPoint(4.5F, -5F, 1F);
		LThighBottomFront.setTextureSize(128, 64);
		LThighBottomFront.mirror = true;
		setRotation(LThighBottomFront, 0.5235988F, 0F, 0F);
		PixelmonModelRenderer LThighFrontOuter = new PixelmonModelRenderer(
				this, 0, 0);
		LThighFrontOuter.addBox(1.4F, -5F, -5.4F, 4, 8, 3);
		LThighFrontOuter.setRotationPoint(4.5F, -5F, 1F);
		LThighFrontOuter.setTextureSize(128, 64);
		LThighFrontOuter.mirror = true;
		setRotation(LThighFrontOuter, 0F, -0.5235988F, 0F);
		PixelmonModelRenderer LThighBottomBack = new PixelmonModelRenderer(
				this, 0, 0);
		LThighBottomBack.addBox(0F, -1.4F, 5.5F, 4, 6, 3);
		LThighBottomBack.setRotationPoint(4.5F, -5F, 1F);
		LThighBottomBack.setTextureSize(128, 64);
		LThighBottomBack.mirror = true;
		setRotation(LThighBottomBack, -0.5235988F, 0F, 0F);
		PixelmonModelRenderer LThighTopBack = new PixelmonModelRenderer(this,
				0, 0);
		LThighTopBack.addBox(0F, -6.4F, 6.4F, 4, 6, 3);
		LThighTopBack.setRotationPoint(4.5F, -5F, 1F);
		LThighTopBack.setTextureSize(128, 64);
		LThighTopBack.mirror = true;
		setRotation(LThighTopBack, 0.5235988F, 0F, 0F);
		PixelmonModelRenderer LThighBottomInner = new PixelmonModelRenderer(
				this, 0, 0);
		LThighBottomInner.addBox(-3.9F, 1.3F, -1F, 2, 6, 6);
		LThighBottomInner.setRotationPoint(4.5F, -5F, 1F);
		LThighBottomInner.setTextureSize(128, 64);
		LThighBottomInner.mirror = true;
		setRotation(LThighBottomInner, 0F, 0F, -0.4886922F);
		PixelmonModelRenderer LThighFrontInner = new PixelmonModelRenderer(
				this, 0, 0);
		LThighFrontInner.addBox(-1.2F, -5F, -2.8F, 4, 8, 3);
		LThighFrontInner.setRotationPoint(4.5F, -5F, 1F);
		LThighFrontInner.setTextureSize(128, 64);
		LThighFrontInner.mirror = true;
		setRotation(LThighFrontInner, 0F, 0.7853982F, 0F);
		PixelmonModelRenderer LThighBackInner = new PixelmonModelRenderer(this,
				0, 0);
		LThighBackInner.addBox(1.7F, -5F, 2.7F, 4, 8, 3);
		LThighBackInner.setRotationPoint(4.5F, -5F, 1F);
		LThighBackInner.setTextureSize(128, 64);
		LThighBackInner.mirror = true;
		setRotation(LThighBackInner, 0F, -0.7853982F, 0F);
		PixelmonModelRenderer LThighTopFront = new PixelmonModelRenderer(this,
				0, 0);
		LThighTopFront.addBox(0F, -8.4F, -6F, 4, 6, 3);
		LThighTopFront.setRotationPoint(4.5F, -5F, 1F);
		LThighTopFront.setTextureSize(128, 64);
		LThighTopFront.mirror = true;
		setRotation(LThighTopFront, -0.5235988F, 0F, 0F);
		PixelmonModelRenderer LThighBottomOuter = new PixelmonModelRenderer(
				this, 0, 0);
		LThighBottomOuter.addBox(5.9F, -1.6F, -1F, 2, 6, 6);
		LThighBottomOuter.setRotationPoint(4.5F, -5F, 1F);
		LThighBottomOuter.setTextureSize(128, 64);
		LThighBottomOuter.mirror = true;
		setRotation(LThighBottomOuter, 0F, 0F, 0.6108652F);
		PixelmonModelRenderer LThighTopInner = new PixelmonModelRenderer(this,
				0, 0);
		LThighTopInner.addBox(-3.9F, -7.2F, -1F, 2, 6, 6);
		LThighTopInner.setRotationPoint(6.5F, -5F, 1F);
		LThighTopInner.setTextureSize(128, 64);
		LThighTopInner.mirror = true;
		setRotation(LThighTopInner, 0F, 0F, 0.4886922F);
		PixelmonModelRenderer LThighTopOuter = new PixelmonModelRenderer(this,
				0, 0);
		LThighTopOuter.addBox(5.5F, -7.2F, -1F, 2, 6, 6);
		LThighTopOuter.setRotationPoint(6.5F, -5F, 1F);
		LThighTopOuter.setTextureSize(128, 64);
		LThighTopOuter.mirror = true;
		setRotation(LThighTopOuter, 0F, 0F, -0.6108652F);
		PixelmonModelRenderer LThighTop = new PixelmonModelRenderer(this, 0, 0);
		LThighTop.addBox(0F, -10.2F, -1F, 4, 6, 6);
		LThighTop.setRotationPoint(4.5F, -5F, 1F);
		LThighTop.setTextureSize(128, 64);
		LThighTop.mirror = true;
		setRotation(LThighTop, 0F, 0F, 0F);
		PixelmonModelRenderer LThighTopOuterFrontFill = new PixelmonModelRenderer(
				this, 118, -5);
		LThighTopOuterFrontFill.addBox(7.2F, -8F, 0F, 0, 6, 5);
		LThighTopOuterFrontFill.setRotationPoint(4.5F, -5F, 1F);
		LThighTopOuterFrontFill.setTextureSize(128, 64);
		LThighTopOuterFrontFill.mirror = true;
		setRotation(LThighTopOuterFrontFill, 0F, 0.8726646F, -0.4328417F);
		PixelmonModelRenderer LThighBottomOuterFrontFill = new PixelmonModelRenderer(
				this, 88, -5);
		LThighBottomOuterFrontFill.addBox(6.3F, 0F, 0F, 0, 6, 5);
		LThighBottomOuterFrontFill.setRotationPoint(4.5F, -5F, 1F);
		LThighBottomOuterFrontFill.setTextureSize(128, 64);
		LThighBottomOuterFrontFill.mirror = true;
		setRotation(LThighBottomOuterFrontFill, 0F, 0.8726646F, 0.4328417F);
		PixelmonModelRenderer LThighBottomInnerFrontFill = new PixelmonModelRenderer(
				this, 88, -5);
		LThighBottomInnerFrontFill.addBox(-4F, 1.2F, -3F, 0, 6, 5);
		LThighBottomInnerFrontFill.setRotationPoint(4.5F, -5F, 1F);
		LThighBottomInnerFrontFill.setTextureSize(128, 64);
		LThighBottomInnerFrontFill.mirror = true;
		setRotation(LThighBottomInnerFrontFill, 0F, -0.8726646F, -0.4153884F);
		PixelmonModelRenderer LThighOuter = new PixelmonModelRenderer(this, 90,
				15);
		LThighOuter.addBox(7.4F, -5.5F, -2F, 0, 9, 8);
		LThighOuter.setRotationPoint(4.5F, -5F, 1F);
		LThighOuter.setTextureSize(128, 64);
		LThighOuter.mirror = true;
		setRotation(LThighOuter, 0F, 0F, 0F);
		PixelmonModelRenderer LThighTopOuterBackFill = new PixelmonModelRenderer(
				this, 108, -5);
		LThighTopOuterBackFill.addBox(10F, -6.8F, -2.4F, 0, 6, 5);
		LThighTopOuterBackFill.setRotationPoint(4.5F, -5F, 1F);
		LThighTopOuterBackFill.setTextureSize(128, 64);
		LThighTopOuterBackFill.mirror = true;
		setRotation(LThighTopOuterBackFill, 0F, -0.8726646F, -0.4328417F);
		PixelmonModelRenderer LThighBottomOuterBackFill = new PixelmonModelRenderer(
				this, 98, -5);
		LThighBottomOuterBackFill.addBox(9.2F, -1.2F, -2.4F, 0, 6, 5);
		LThighBottomOuterBackFill.setRotationPoint(4.5F, -5F, 1F);
		LThighBottomOuterBackFill.setTextureSize(128, 64);
		LThighBottomOuterBackFill.mirror = true;
		setRotation(LThighBottomOuterBackFill, 0F, -0.8726646F, 0.4328417F);
		PixelmonModelRenderer LShinBack = new PixelmonModelRenderer(this, 0, 0);
		LShinBack.addBox(-1.6F, 0F, -0.3F, 3, 14, 3);
		LShinBack.setRotationPoint(7.5F, 1F, 3F);
		LShinBack.setTextureSize(128, 64);
		LShinBack.mirror = true;
		setRotation(LShinBack, 0.1745329F, -0.1047198F, -0.1745329F);
		PixelmonModelRenderer LShinFront = new PixelmonModelRenderer(this, 0, 0);
		LShinFront.addBox(-1.6F, -0.1F, -2F, 3, 14, 3);
		LShinFront.setRotationPoint(7.5F, 1F, 3F);
		LShinFront.setTextureSize(128, 64);
		LShinFront.mirror = true;
		setRotation(LShinFront, 0.3665191F, -0.1047198F, -0.1745329F);
		PixelmonModelRenderer LFootBaseUpper = new PixelmonModelRenderer(this,
				0, 0);
		LFootBaseUpper.addBox(-3F, -0.4F, -6.6F, 5, 1, 7);
		LFootBaseUpper.setRotationPoint(10.5F, 15F, 5F);
		LFootBaseUpper.setTextureSize(128, 64);
		LFootBaseUpper.mirror = true;
		setRotation(LFootBaseUpper, 1.22173F, -0.4014257F, 0F);
		PixelmonModelRenderer LFootArchBack = new PixelmonModelRenderer(this,
				0, 0);
		LFootArchBack.addBox(-2.5F, 1.3F, -2.6F, 4, 2, 4);
		LFootArchBack.setRotationPoint(10.5F, 15F, 5F);
		LFootArchBack.setTextureSize(128, 64);
		LFootArchBack.mirror = true;
		setRotation(LFootArchBack, 0.6981317F, -0.4014257F, 0F);
		PixelmonModelRenderer LFootBase = new PixelmonModelRenderer(this, 0, 0);
		LFootBase.addBox(-3F, 0.6F, -6.6F, 5, 2, 7);
		LFootBase.setRotationPoint(10.5F, 15F, 5F);
		LFootBase.setTextureSize(128, 64);
		LFootBase.mirror = true;
		setRotation(LFootBase, 0.9599311F, -0.4014257F, 0F);
		PixelmonModelRenderer LFootUpper = new PixelmonModelRenderer(this, 0, 0);
		LFootUpper.addBox(-2F, -1.5F, -1.3F, 3, 8, 2);
		LFootUpper.setRotationPoint(10.5F, 15F, 5F);
		LFootUpper.setTextureSize(128, 64);
		LFootUpper.mirror = true;
		setRotation(LFootUpper, -0.3141593F, -0.4014257F, 0F);
		PixelmonModelRenderer LHeelBase = new PixelmonModelRenderer(this, 0, 0);
		LHeelBase.addBox(-2.5F, -3.4F, -0.4F, 4, 4, 5);
		LHeelBase.setRotationPoint(10.5F, 15F, 5F);
		LHeelBase.setTextureSize(128, 64);
		LHeelBase.mirror = true;
		setRotation(LHeelBase, -0.3141593F, -0.4014257F, 0F);
		PixelmonModelRenderer LToeInnerSlantBack = new PixelmonModelRenderer(
				this, 0, 0);
		LToeInnerSlantBack.addBox(-3.5F, 9.2F, -3.1F, 3, 1, 3);
		LToeInnerSlantBack.setRotationPoint(10.5F, 15F, 5F);
		LToeInnerSlantBack.setTextureSize(128, 64);
		LToeInnerSlantBack.mirror = true;
		setRotation(LToeInnerSlantBack, -0.418879F, -0.3490659F, 0F);
		PixelmonModelRenderer LToeInnerSlantFront = new PixelmonModelRenderer(
				this, 0, 0);
		LToeInnerSlantFront.addBox(-3.5F, 2.9F, -11.2F, 3, 2, 2);
		LToeInnerSlantFront.setRotationPoint(10.5F, 15F, 5F);
		LToeInnerSlantFront.setTextureSize(128, 64);
		LToeInnerSlantFront.mirror = true;
		setRotation(LToeInnerSlantFront, 0.5235988F, -0.3490659F, 0F);
		PixelmonModelRenderer LToeInner = new PixelmonModelRenderer(this, 0, 0);
		LToeInner.addBox(-3.5F, 8.1F, -8.3F, 3, 2, 4);
		LToeInner.setRotationPoint(10.5F, 15F, 5F);
		LToeInner.setTextureSize(128, 64);
		LToeInner.mirror = true;
		setRotation(LToeInner, 0F, -0.3490659F, 0F);
		PixelmonModelRenderer LFootBaseInner = new PixelmonModelRenderer(this,
				0, 0);
		LFootBaseInner.addBox(-2.3F, 0.7F, -7F, 1, 2, 7);
		LFootBaseInner.setRotationPoint(10.5F, 15F, 5F);
		LFootBaseInner.setTextureSize(128, 64);
		LFootBaseInner.mirror = true;
		setRotation(LFootBaseInner, 0.9424778F, -0.2792527F, 0.122173F);
		PixelmonModelRenderer LToeOuterSlantFront = new PixelmonModelRenderer(
				this, 0, 0);
		LToeOuterSlantFront.addBox(-0.5F, 2.9F, -11.3F, 3, 2, 2);
		LToeOuterSlantFront.setRotationPoint(10.5F, 15F, 5F);
		LToeOuterSlantFront.setTextureSize(128, 64);
		LToeOuterSlantFront.mirror = true;
		setRotation(LToeOuterSlantFront, 0.5235988F, -0.5235988F, 0F);
		PixelmonModelRenderer LFootBall = new PixelmonModelRenderer(this, 0, 0);
		LFootBall.addBox(-3F, 6.8F, -7F, 5, 2, 3);
		LFootBall.setRotationPoint(10.5F, 15F, 5F);
		LFootBall.setTextureSize(128, 64);
		LFootBall.mirror = true;
		setRotation(LFootBall, 0.0872665F, -0.4363323F, 0F);
		PixelmonModelRenderer LFootBaseOuter = new PixelmonModelRenderer(this,
				0, 0);
		LFootBaseOuter.addBox(0.4F, 0.7F, -6.9F, 1, 2, 7);
		LFootBaseOuter.setRotationPoint(10.5F, 15F, 5F);
		LFootBaseOuter.setTextureSize(128, 64);
		LFootBaseOuter.mirror = true;
		setRotation(LFootBaseOuter, 0.9424778F, -0.5235988F, -0.122173F);
		PixelmonModelRenderer LFootBallInner = new PixelmonModelRenderer(this,
				0, 0);
		LFootBallInner.addBox(-2.4F, 6.8F, 0.1F, 1, 3, 2);
		LFootBallInner.setRotationPoint(10.5F, 15F, 5F);
		LFootBallInner.setTextureSize(128, 64);
		LFootBallInner.mirror = true;
		setRotation(LFootBallInner, -0.5235988F, -0.3839724F, 0.0785398F);
		PixelmonModelRenderer LFootBallOuter = new PixelmonModelRenderer(this,
				0, 0);
		LFootBallOuter.addBox(0.8F, 6.8F, 0F, 1, 3, 2);
		LFootBallOuter.setRotationPoint(10.5F, 15F, 5F);
		LFootBallOuter.setTextureSize(128, 64);
		LFootBallOuter.mirror = true;
		setRotation(LFootBallOuter, -0.5235988F, -0.4886922F, -0.0785398F);
		PixelmonModelRenderer LFootBallUpper = new PixelmonModelRenderer(this,
				0, 0);
		LFootBallUpper.addBox(-3.5F, 2.7F, -10.1F, 6, 2, 3);
		LFootBallUpper.setRotationPoint(10.5F, 15F, 5F);
		LFootBallUpper.setTextureSize(128, 64);
		LFootBallUpper.mirror = true;
		setRotation(LFootBallUpper, 0.6981317F, -0.4363323F, 0F);
		PixelmonModelRenderer LFootArchFront = new PixelmonModelRenderer(this,
				0, 0);
		LFootArchFront.addBox(-3.5F, 6.5F, 0F, 6, 3, 2);
		LFootArchFront.setRotationPoint(10.5F, 15F, 5F);
		LFootArchFront.setTextureSize(128, 64);
		LFootArchFront.mirror = true;
		setRotation(LFootArchFront, -0.5235988F, -0.4014257F, 0F);
		PixelmonModelRenderer LToeOuter = new PixelmonModelRenderer(this, 0, 0);
		LToeOuter.addBox(-0.5F, 8.1F, -8.3F, 3, 2, 4);
		LToeOuter.setRotationPoint(10.5F, 15F, 5F);
		LToeOuter.setTextureSize(128, 64);
		LToeOuter.mirror = true;
		setRotation(LToeOuter, 0F, -0.5235988F, 0F);
		PixelmonModelRenderer LToeOuterSlantBack = new PixelmonModelRenderer(
				this, 0, 0);
		LToeOuterSlantBack.addBox(-0.5F, 9.2F, -3.2F, 3, 1, 3);
		LToeOuterSlantBack.setRotationPoint(10.5F, 15F, 5F);
		LToeOuterSlantBack.setTextureSize(128, 64);
		LToeOuterSlantBack.mirror = true;
		setRotation(LToeOuterSlantBack, -0.418879F, -0.5235988F, 0F);
		PixelmonModelRenderer LBackToeFront = new PixelmonModelRenderer(this,
				0, 0);
		LBackToeFront.addBox(-5F, -3F, -1.1F, 3, 3, 2);
		LBackToeFront.setRotationPoint(10.5F, 15F, 5F);
		LBackToeFront.setTextureSize(128, 64);
		LBackToeFront.mirror = true;
		setRotation(LBackToeFront, -0.2443461F, 0.296706F, -0.2094395F);
		PixelmonModelRenderer LBackToeBack = new PixelmonModelRenderer(this, 0,
				0);
		LBackToeBack.addBox(-5.1F, -3F, -2.5F, 1, 3, 2);
		LBackToeBack.setRotationPoint(10.5F, 15F, 5F);
		LBackToeBack.setTextureSize(128, 64);
		LBackToeBack.mirror = true;
		setRotation(LBackToeBack, -0.1396263F, 0.6981317F, -0.2792527F);
		PixelmonModelRenderer LBackToeMid = new PixelmonModelRenderer(this, 0,
				0);
		LBackToeMid.addBox(-4.5F, -3F, 2.5F, 2, 3, 1);
		LBackToeMid.setRotationPoint(10.5F, 15F, 5F);
		LBackToeMid.setTextureSize(128, 64);
		LBackToeMid.mirror = true;
		setRotation(LBackToeMid, -0.3141593F, -0.4014257F, 0F);

		LLeg.addChild(LThighMid);
		LLeg.addChild(LThighBackOuter);
		LLeg.addChild(LThighBottomFront);
		LLeg.addChild(LThighFrontOuter);
		LLeg.addChild(LThighBottomBack);
		LLeg.addChild(LThighTopBack);
		LLeg.addChild(LThighBottomInner);
		LLeg.addChild(LThighFrontInner);
		LLeg.addChild(LThighBackInner);
		LLeg.addChild(LThighTopFront);
		LLeg.addChild(LThighBottomOuter);
		LLeg.addChild(LThighTopInner);
		LLeg.addChild(LThighTopOuter);
		LLeg.addChild(LThighTop);
		LLeg.addChild(LThighTopOuterFrontFill);
		LLeg.addChild(LThighBottomOuterFrontFill);
		LLeg.addChild(LThighBottomInnerFrontFill);
		LLeg.addChild(LThighOuter);
		LLeg.addChild(LThighTopOuterBackFill);
		LLeg.addChild(LThighBottomOuterBackFill);
		LLeg.addChild(LShinBack);
		LLeg.addChild(LShinFront);
		LLeg.addChild(LFootBaseUpper);
		LLeg.addChild(LFootArchBack);
		LLeg.addChild(LFootBase);
		LLeg.addChild(LFootUpper);
		LLeg.addChild(LHeelBase);
		LLeg.addChild(LToeInnerSlantBack);
		LLeg.addChild(LToeInnerSlantFront);
		LLeg.addChild(LToeInner);
		LLeg.addChild(LFootBaseInner);
		LLeg.addChild(LToeOuterSlantFront);
		LLeg.addChild(LFootBall);
		LLeg.addChild(LFootBaseOuter);
		LLeg.addChild(LFootBallInner);
		LLeg.addChild(LFootBallOuter);
		LLeg.addChild(LFootBallUpper);
		LLeg.addChild(LFootArchFront);
		LLeg.addChild(LToeOuter);
		LLeg.addChild(LToeOuterSlantBack);
		LLeg.addChild(LBackToeFront);
		LLeg.addChild(LBackToeBack);
		LLeg.addChild(LBackToeMid);
		Body.addChild(LLeg);

		PixelmonModelRenderer RLeg = new PixelmonModelRenderer(this,
				"Right Leg");
		RLeg.setRotationPoint(0, 0, 0);

		PixelmonModelRenderer RThighBackOuter = new PixelmonModelRenderer(this,
				0, 0);
		RThighBackOuter.addBox(-3.5F, -5F, 5.9F, 4, 8, 3);
		RThighBackOuter.setRotationPoint(-4.5F, -5F, 1F);
		RThighBackOuter.setTextureSize(128, 64);
		RThighBackOuter.mirror = true;
		setRotation(RThighBackOuter, 0F, -0.5235988F, 0F);
		PixelmonModelRenderer RThighFrontOuter = new PixelmonModelRenderer(
				this, 0, 0);
		RThighFrontOuter.addBox(-5.4F, -5F, -5.4F, 4, 8, 3);
		RThighFrontOuter.setRotationPoint(-4.5F, -5F, 1F);
		RThighFrontOuter.setTextureSize(128, 64);
		RThighFrontOuter.mirror = true;
		setRotation(RThighFrontOuter, 0F, 0.5235988F, 0F);
		PixelmonModelRenderer RThighTopOuter = new PixelmonModelRenderer(this,
				0, 0);
		RThighTopOuter.addBox(-9.1F, -6.1F, -1F, 2, 6, 6);
		RThighTopOuter.setRotationPoint(-4.5F, -5F, 1F);
		RThighTopOuter.setTextureSize(128, 64);
		RThighTopOuter.mirror = true;
		setRotation(RThighTopOuter, 0F, 0F, 0.6108652F);
		PixelmonModelRenderer RThighBottomBack = new PixelmonModelRenderer(
				this, 0, 0);
		RThighBottomBack.addBox(-4F, -1.4F, 5.5F, 4, 6, 3);
		RThighBottomBack.setRotationPoint(-4.5F, -5F, 1F);
		RThighBottomBack.setTextureSize(128, 64);
		RThighBottomBack.mirror = true;
		setRotation(RThighBottomBack, -0.5235988F, 0F, 0F);
		PixelmonModelRenderer RThighBackInner = new PixelmonModelRenderer(this,
				0, 0);
		RThighBackInner.addBox(-5.7F, -3F, 2.7F, 4, 6, 3);
		RThighBackInner.setRotationPoint(-4.5F, -5F, 1F);
		RThighBackInner.setTextureSize(128, 64);
		RThighBackInner.mirror = true;
		setRotation(RThighBackInner, 0F, 0.7853982F, 0F);
		PixelmonModelRenderer RThighFrontInner = new PixelmonModelRenderer(
				this, 0, 0);
		RThighFrontInner.addBox(-2.8F, -3F, -2.8F, 4, 6, 3);
		RThighFrontInner.setRotationPoint(-4.5F, -5F, 1F);
		RThighFrontInner.setTextureSize(128, 64);
		RThighFrontInner.mirror = true;
		setRotation(RThighFrontInner, 0F, -0.7853982F, 0F);
		PixelmonModelRenderer RThighTopFront = new PixelmonModelRenderer(this,
				0, 0);
		RThighTopFront.addBox(-4F, -8.4F, -6F, 4, 6, 3);
		RThighTopFront.setRotationPoint(-4.5F, -5F, 1F);
		RThighTopFront.setTextureSize(128, 64);
		RThighTopFront.mirror = true;
		setRotation(RThighTopFront, -0.5235988F, 0F, 0F);
		PixelmonModelRenderer RThighTopInner = new PixelmonModelRenderer(this,
				0, 0);
		RThighTopInner.addBox(1.9F, -7.2F, -1F, 2, 6, 6);
		RThighTopInner.setRotationPoint(-4.5F, -5F, 1F);
		RThighTopInner.setTextureSize(128, 64);
		RThighTopInner.mirror = true;
		setRotation(RThighTopInner, 0F, 0F, -0.4886922F);
		PixelmonModelRenderer RThighBottomFront = new PixelmonModelRenderer(
				this, 0, 0);
		RThighBottomFront.addBox(-4F, 0.6F, -4.9F, 4, 6, 3);
		RThighBottomFront.setRotationPoint(-4.5F, -5F, 1F);
		RThighBottomFront.setTextureSize(128, 64);
		RThighBottomFront.mirror = true;
		setRotation(RThighBottomFront, 0.5235988F, 0F, 0F);
		PixelmonModelRenderer RThighBottomOuter = new PixelmonModelRenderer(
				this, 0, 0);
		RThighBottomOuter.addBox(-7.9F, -1.6F, -1F, 2, 6, 6);
		RThighBottomOuter.setRotationPoint(-4.5F, -5F, 1F);
		RThighBottomOuter.setTextureSize(128, 64);
		RThighBottomOuter.mirror = true;
		setRotation(RThighBottomOuter, 0F, 0F, -0.6108652F);
		PixelmonModelRenderer RThighBottomInner = new PixelmonModelRenderer(
				this, 0, 0);
		RThighBottomInner.addBox(1.9F, 1.3F, -1F, 2, 6, 6);
		RThighBottomInner.setRotationPoint(-4.5F, -5F, 1F);
		RThighBottomInner.setTextureSize(128, 64);
		RThighBottomInner.mirror = true;
		setRotation(RThighBottomInner, 0F, 0F, 0.4886922F);
		PixelmonModelRenderer RThighTopBack = new PixelmonModelRenderer(this,
				0, 0);
		RThighTopBack.addBox(-4F, -6.4F, 6.4F, 4, 6, 3);
		RThighTopBack.setRotationPoint(-4.5F, -5F, 1F);
		RThighTopBack.setTextureSize(128, 64);
		RThighTopBack.mirror = true;
		setRotation(RThighTopBack, 0.5235988F, 0F, 0F);
		PixelmonModelRenderer RThighTop = new PixelmonModelRenderer(this, 0, 0);
		RThighTop.addBox(-4F, -10.2F, -1F, 4, 6, 6);
		RThighTop.setRotationPoint(-4.5F, -5F, 1F);
		RThighTop.setTextureSize(128, 64);
		RThighTop.mirror = true;
		setRotation(RThighTop, 0F, 0F, 0F);
		PixelmonModelRenderer RThighMid = new PixelmonModelRenderer(this, 0, 0);
		RThighMid.addBox(-4F, -5F, -4F, 4, 8, 12);
		RThighMid.setRotationPoint(-4.5F, -5F, 1F);
		RThighMid.setTextureSize(128, 64);
		RThighMid.mirror = true;
		setRotation(RThighMid, 0F, 0F, 0F);
		PixelmonModelRenderer RThighBottom = new PixelmonModelRenderer(this, 0,
				0);
		RThighBottom.addBox(-4F, 2.2F, -1F, 4, 6, 6);
		RThighBottom.setRotationPoint(-4.5F, -5F, 1F);
		RThighBottom.setTextureSize(128, 64);
		RThighBottom.mirror = true;
		setRotation(RThighBottom, 0F, 0F, 0F);
		PixelmonModelRenderer RThighOuter = new PixelmonModelRenderer(this, 90,
				15);
		RThighOuter.addBox(-7.4F, -5.5F, -2F, 0, 9, 8);
		RThighOuter.setRotationPoint(-4.5F, -5F, 1F);
		RThighOuter.setTextureSize(128, 64);
		RThighOuter.mirror = true;
		setRotation(RThighOuter, 0F, 0F, 0F);
		PixelmonModelRenderer RThighTopOuterFrontFill = new PixelmonModelRenderer(
				this, 78, -5);
		RThighTopOuterFrontFill.addBox(-7.2F, -8F, 0F, 0, 6, 5);
		RThighTopOuterFrontFill.setRotationPoint(-4.5F, -5F, 1F);
		RThighTopOuterFrontFill.setTextureSize(128, 64);
		RThighTopOuterFrontFill.mirror = true;
		setRotation(RThighTopOuterFrontFill, 0F, -0.8726646F, 0.4328417F);
		PixelmonModelRenderer RThighTopOuterBackFill = new PixelmonModelRenderer(
				this, 108, -5);
		RThighTopOuterBackFill.addBox(-10F, -6.8F, -2.4F, 0, 6, 5);
		RThighTopOuterBackFill.setRotationPoint(-4.5F, -5F, 1F);
		RThighTopOuterBackFill.setTextureSize(128, 64);
		RThighTopOuterBackFill.mirror = true;
		setRotation(RThighTopOuterBackFill, 0F, 0.8726646F, 0.4328417F);
		PixelmonModelRenderer RThighBottomOuterBackFill = new PixelmonModelRenderer(
				this, 98, -5);
		RThighBottomOuterBackFill.addBox(-9.2F, -1.2F, -2.4F, 0, 6, 5);
		RThighBottomOuterBackFill.setRotationPoint(-4.5F, -5F, 1F);
		RThighBottomOuterBackFill.setTextureSize(128, 64);
		RThighBottomOuterBackFill.mirror = true;
		setRotation(RThighBottomOuterBackFill, 0F, 0.8726646F, -0.4328417F);
		PixelmonModelRenderer RThighBottomOuterFrontFill = new PixelmonModelRenderer(
				this, 68, 4);
		RThighBottomOuterFrontFill.addBox(-6.3F, 0F, 0F, 0, 6, 5);
		RThighBottomOuterFrontFill.setRotationPoint(-4.5F, -5F, 1F);
		RThighBottomOuterFrontFill.setTextureSize(128, 64);
		RThighBottomOuterFrontFill.mirror = true;
		setRotation(RThighBottomOuterFrontFill, 0F, -0.8726646F, -0.4328417F);
		PixelmonModelRenderer RThighBottomInnerFrontFill = new PixelmonModelRenderer(
				this, 88, -5);
		RThighBottomInnerFrontFill.addBox(4F, 1.2F, -3F, 0, 6, 5);
		RThighBottomInnerFrontFill.setRotationPoint(-4.5F, -5F, 1F);
		RThighBottomInnerFrontFill.setTextureSize(128, 64);
		RThighBottomInnerFrontFill.mirror = true;
		setRotation(RThighBottomInnerFrontFill, 0F, 0.8726646F, 0.4153884F);
		PixelmonModelRenderer RShinBack = new PixelmonModelRenderer(this, 0, 0);
		RShinBack.addBox(-1.4F, 0F, -0.3F, 3, 14, 3);
		RShinBack.setRotationPoint(-7.5F, 1F, 3F);
		RShinBack.setTextureSize(128, 64);
		RShinBack.mirror = true;
		setRotation(RShinBack, 0.1745329F, 0.1047198F, 0.1745329F);
		PixelmonModelRenderer RShinFront = new PixelmonModelRenderer(this, 0, 0);
		RShinFront.addBox(-1.4F, -0.1F, -2F, 3, 14, 3);
		RShinFront.setRotationPoint(-7.5F, 1F, 3F);
		RShinFront.setTextureSize(128, 64);
		RShinFront.mirror = true;
		setRotation(RShinFront, 0.3665191F, 0.1047198F, 0.1745329F);
		PixelmonModelRenderer RToeOuter = new PixelmonModelRenderer(this, 0, 0);
		RToeOuter.addBox(-2.5F, 8.1F, -8.3F, 3, 2, 4);
		RToeOuter.setRotationPoint(-10.5F, 15F, 5F);
		RToeOuter.setTextureSize(128, 64);
		RToeOuter.mirror = true;
		setRotation(RToeOuter, 0F, 0.5235988F, 0F);
		PixelmonModelRenderer RToeOuterSlantFront = new PixelmonModelRenderer(
				this, 0, 0);
		RToeOuterSlantFront.addBox(-2.5F, 2.9F, -11.3F, 3, 2, 2);
		RToeOuterSlantFront.setRotationPoint(-10.5F, 15F, 5F);
		RToeOuterSlantFront.setTextureSize(128, 64);
		RToeOuterSlantFront.mirror = true;
		setRotation(RToeOuterSlantFront, 0.5235988F, 0.5235988F, 0F);
		PixelmonModelRenderer RToeOuterSlantBack = new PixelmonModelRenderer(
				this, 0, 0);
		RToeOuterSlantBack.addBox(-2.5F, 9.2F, -3.2F, 3, 1, 3);
		RToeOuterSlantBack.setRotationPoint(-10.5F, 15F, 5F);
		RToeOuterSlantBack.setTextureSize(128, 64);
		RToeOuterSlantBack.mirror = true;
		setRotation(RToeOuterSlantBack, -0.418879F, 0.5235988F, 0F);
		PixelmonModelRenderer RToeInnerSlantBack = new PixelmonModelRenderer(
				this, 0, 0);
		RToeInnerSlantBack.addBox(0.5F, 9.2F, -3.1F, 3, 1, 3);
		RToeInnerSlantBack.setRotationPoint(-10.5F, 15F, 5F);
		RToeInnerSlantBack.setTextureSize(128, 64);
		RToeInnerSlantBack.mirror = true;
		setRotation(RToeInnerSlantBack, -0.418879F, 0.3490659F, 0F);
		PixelmonModelRenderer RToeInnerSlantFront = new PixelmonModelRenderer(
				this, 0, 0);
		RToeInnerSlantFront.addBox(0.5F, 2.9F, -11.2F, 3, 2, 2);
		RToeInnerSlantFront.setRotationPoint(-10.5F, 15F, 5F);
		RToeInnerSlantFront.setTextureSize(128, 64);
		RToeInnerSlantFront.mirror = true;
		setRotation(RToeInnerSlantFront, 0.5235988F, 0.3490659F, 0F);
		PixelmonModelRenderer RToeInner = new PixelmonModelRenderer(this, 0, 0);
		RToeInner.addBox(0.5F, 8.1F, -8.3F, 3, 2, 4);
		RToeInner.setRotationPoint(-10.5F, 15F, 5F);
		RToeInner.setTextureSize(128, 64);
		RToeInner.mirror = true;
		setRotation(RToeInner, 0F, 0.3490659F, 0F);
		PixelmonModelRenderer RFootBaseUpper = new PixelmonModelRenderer(this,
				0, 0);
		RFootBaseUpper.addBox(-2F, -0.4F, -6.6F, 5, 1, 7);
		RFootBaseUpper.setRotationPoint(-10.5F, 15F, 5F);
		RFootBaseUpper.setTextureSize(128, 64);
		RFootBaseUpper.mirror = true;
		setRotation(RFootBaseUpper, 1.22173F, 0.4014257F, 0F);
		PixelmonModelRenderer RFootArchBack = new PixelmonModelRenderer(this,
				0, 0);
		RFootArchBack.addBox(-1.5F, 1.3F, -2.6F, 4, 2, 4);
		RFootArchBack.setRotationPoint(-10.5F, 15F, 5F);
		RFootArchBack.setTextureSize(128, 64);
		RFootArchBack.mirror = true;
		setRotation(RFootArchBack, 0.6981317F, 0.4014257F, 0F);
		PixelmonModelRenderer RFootBall = new PixelmonModelRenderer(this, 0, 0);
		RFootBall.addBox(-2F, 6.8F, -7F, 5, 2, 3);
		RFootBall.setRotationPoint(-10.5F, 15F, 5F);
		RFootBall.setTextureSize(128, 64);
		RFootBall.mirror = true;
		setRotation(RFootBall, 0.0872665F, 0.4363323F, 0F);
		PixelmonModelRenderer RFootBallOuter = new PixelmonModelRenderer(this,
				0, 0);
		RFootBallOuter.addBox(-1.8F, 6.8F, 0F, 1, 3, 2);
		RFootBallOuter.setRotationPoint(-10.5F, 15F, 5F);
		RFootBallOuter.setTextureSize(128, 64);
		RFootBallOuter.mirror = true;
		setRotation(RFootBallOuter, -0.5235988F, 0.4886922F, 0.0785398F);
		PixelmonModelRenderer RFootBallUpper = new PixelmonModelRenderer(this,
				0, 0);
		RFootBallUpper.addBox(-2.5F, 2.7F, -10.1F, 6, 2, 3);
		RFootBallUpper.setRotationPoint(-10.5F, 15F, 5F);
		RFootBallUpper.setTextureSize(128, 64);
		RFootBallUpper.mirror = true;
		setRotation(RFootBallUpper, 0.6981317F, 0.4363323F, 0F);
		PixelmonModelRenderer RFootBallInner = new PixelmonModelRenderer(this,
				0, 0);
		RFootBallInner.addBox(1.9F, 6.8F, 0.1F, 1, 3, 2);
		RFootBallInner.setRotationPoint(-10.5F, 15F, 5F);
		RFootBallInner.setTextureSize(128, 64);
		RFootBallInner.mirror = true;
		setRotation(RFootBallInner, -0.5235988F, 0.3839724F, -0.0785398F);
		PixelmonModelRenderer RFootArchFront = new PixelmonModelRenderer(this,
				0, 0);
		RFootArchFront.addBox(-2.5F, 6.5F, 0F, 6, 3, 2);
		RFootArchFront.setRotationPoint(-10.5F, 15F, 5F);
		RFootArchFront.setTextureSize(128, 64);
		RFootArchFront.mirror = true;
		setRotation(RFootArchFront, -0.5235988F, 0.4014257F, 0F);
		PixelmonModelRenderer RFootBaseOuter = new PixelmonModelRenderer(this,
				0, 0);
		RFootBaseOuter.addBox(-1.4F, 0.7F, -7F, 1, 2, 7);
		RFootBaseOuter.setRotationPoint(-10.5F, 15F, 5F);
		RFootBaseOuter.setTextureSize(128, 64);
		RFootBaseOuter.mirror = true;
		setRotation(RFootBaseOuter, 0.9424778F, 0.5235988F, 0.122173F);
		PixelmonModelRenderer RFootBaseInner = new PixelmonModelRenderer(this,
				0, 0);
		RFootBaseInner.addBox(1.3F, 0.7F, -7F, 1, 2, 7);
		RFootBaseInner.setRotationPoint(-10.5F, 15F, 5F);
		RFootBaseInner.setTextureSize(128, 64);
		RFootBaseInner.mirror = true;
		setRotation(RFootBaseInner, 0.9424778F, 0.2792527F, -0.122173F);
		PixelmonModelRenderer RFootBase = new PixelmonModelRenderer(this, 0, 0);
		RFootBase.addBox(-2F, 0.6F, -6.6F, 5, 2, 7);
		RFootBase.setRotationPoint(-10.5F, 15F, 5F);
		RFootBase.setTextureSize(128, 64);
		RFootBase.mirror = true;
		setRotation(RFootBase, 0.9599311F, 0.4014257F, 0F);
		PixelmonModelRenderer RFootBaseTop = new PixelmonModelRenderer(this, 0,
				0);
		RFootBaseTop.addBox(-1F, -1.5F, -1.3F, 3, 8, 2);
		RFootBaseTop.setRotationPoint(-10.5F, 15F, 5F);
		RFootBaseTop.setTextureSize(128, 64);
		RFootBaseTop.mirror = true;
		setRotation(RFootBaseTop, -0.3141593F, 0.4014257F, 0F);
		PixelmonModelRenderer RHeelBase = new PixelmonModelRenderer(this, 0, 0);
		RHeelBase.addBox(-1.5F, -3.4F, -0.4F, 4, 4, 5);
		RHeelBase.setRotationPoint(-10.5F, 15F, 5F);
		RHeelBase.setTextureSize(128, 64);
		RHeelBase.mirror = true;
		setRotation(RHeelBase, -0.3141593F, 0.4014257F, 0F);
		PixelmonModelRenderer RBackToeFront = new PixelmonModelRenderer(this,
				0, 0);
		RBackToeFront.addBox(2F, -3F, -1.1F, 3, 3, 2);
		RBackToeFront.setRotationPoint(-10.5F, 15F, 5F);
		RBackToeFront.setTextureSize(128, 64);
		RBackToeFront.mirror = true;
		setRotation(RBackToeFront, -0.2443461F, -0.296706F, 0.2094395F);
		PixelmonModelRenderer RBackToeBack = new PixelmonModelRenderer(this, 0,
				0);
		RBackToeBack.addBox(4.1F, -3F, -2.5F, 1, 3, 2);
		RBackToeBack.setRotationPoint(-10.5F, 15F, 5F);
		RBackToeBack.setTextureSize(128, 64);
		RBackToeBack.mirror = true;
		setRotation(RBackToeBack, -0.1396263F, -0.6981317F, 0.2792527F);
		PixelmonModelRenderer RBackToeMid = new PixelmonModelRenderer(this, 0,
				0);
		RBackToeMid.addBox(2.5F, -3F, 2.5F, 2, 3, 1);
		RBackToeMid.setRotationPoint(-10.5F, 15F, 5F);
		RBackToeMid.setTextureSize(128, 64);
		RBackToeMid.mirror = true;
		setRotation(RBackToeMid, -0.3141593F, 0.4014257F, 0F);

		RLeg.addChild(RThighBackOuter);
		RLeg.addChild(RThighFrontOuter);
		RLeg.addChild(RThighTopOuter);
		RLeg.addChild(RThighBottomBack);
		RLeg.addChild(RThighBackInner);
		RLeg.addChild(RThighFrontInner);
		RLeg.addChild(RThighTopFront);
		RLeg.addChild(RThighTopInner);
		RLeg.addChild(RThighBottomFront);
		RLeg.addChild(RThighBottomOuter);
		RLeg.addChild(RThighBottomInner);
		RLeg.addChild(RThighTopBack);
		RLeg.addChild(RThighTop);
		RLeg.addChild(RThighMid);
		RLeg.addChild(RThighBottom);
		RLeg.addChild(RThighOuter);
		RLeg.addChild(RThighTopOuterFrontFill);
		RLeg.addChild(RThighTopOuterBackFill);
		RLeg.addChild(RThighBottomOuterBackFill);
		RLeg.addChild(RThighBottomOuterFrontFill);
		RLeg.addChild(RThighBottomInnerFrontFill);
		RLeg.addChild(RShinBack);
		RLeg.addChild(RShinFront);
		RLeg.addChild(RToeOuter);
		RLeg.addChild(RToeOuterSlantFront);
		RLeg.addChild(RToeOuterSlantBack);
		RLeg.addChild(RToeInnerSlantBack);
		RLeg.addChild(RToeInnerSlantFront);
		RLeg.addChild(RToeInner);
		RLeg.addChild(RFootBaseUpper);
		RLeg.addChild(RFootArchBack);
		RLeg.addChild(RFootBall);
		RLeg.addChild(RFootBallOuter);
		RLeg.addChild(RFootBallUpper);
		RLeg.addChild(RFootBallInner);
		RLeg.addChild(RFootArchFront);
		RLeg.addChild(RFootBaseOuter);
		RLeg.addChild(RFootBaseInner);
		RLeg.addChild(RFootBase);
		RLeg.addChild(RFootBaseTop);
		RLeg.addChild(RHeelBase);
		RLeg.addChild(RBackToeFront);
		RLeg.addChild(RBackToeBack);
		RLeg.addChild(RBackToeMid);
		Body.addChild(RLeg);

		ModuleArm leftArmModule = new ModuleArm(LArm, EnumArm.Left, EnumRotation.x, 0, 0);
		ModuleArm rightArmModule = new ModuleArm(RArm, EnumArm.Right, EnumRotation.x, 0, 0);

		float legspeed = 0.5F;
		float legRotationLimit = 1.4F;

		ModuleLeg leftLegModule = new ModuleLeg(LLeg, EnumLeg.FrontLeft,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleLeg rightLegModule = new ModuleLeg(RLeg, EnumLeg.FrontRight,
				EnumPhase.InPhase, EnumRotation.x, legRotationLimit, legspeed);
		ModuleTailBasic tailModule = new ModuleTailBasic(Tail, .2F, .05F,
				legspeed);

		skeleton = new SkeletonBiped(Body, null, leftArmModule, rightArmModule,
				leftLegModule, rightLegModule, tailModule);

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
