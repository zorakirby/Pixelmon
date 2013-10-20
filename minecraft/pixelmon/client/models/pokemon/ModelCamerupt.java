package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.EnumRotation;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;

public class ModelCamerupt extends PixelmonModelBase {
	// fields
	PixelmonModelRenderer UPPERLEFTLEGPIECE;
	PixelmonModelRenderer BOTTOMRIGHTLEGPIECE;
	PixelmonModelRenderer UPPERRIGHTLEGPIECE;
	PixelmonModelRenderer BOTTOMLEFTLEGPIECE;
	PixelmonModelRenderer BODYPIECE;
	PixelmonModelRenderer UPPERTAILPIECE;
	PixelmonModelRenderer LOWERTAILPIECE;
	PixelmonModelRenderer NECKPIECE;
	PixelmonModelRenderer HEADPIECE;
	PixelmonModelRenderer MOUTHPIECE;

	public ModelCamerupt() {
		textureWidth = 200;
		textureHeight = 200;

		UPPERLEFTLEGPIECE = new PixelmonModelRenderer(this, "UPPERLEFTLEGPIECE");
		UPPERLEFTLEGPIECE.setRotationPoint(4.5F, 18F, -10F);
		setRotation(UPPERLEFTLEGPIECE, 0F, 0F, 0F);
		UPPERLEFTLEGPIECE.mirror = true;
		PixelmonModelRenderer upperlefthorizontalheel = new PixelmonModelRenderer(
				this, 123, 10);
		upperlefthorizontalheel.addBox(-3F, 4F, -2.5F, 6, 2, 4);
		upperlefthorizontalheel.setRotationPoint(0F, 0F, 0F);
		upperlefthorizontalheel.setTextureSize(200, 200);
		upperlefthorizontalheel.mirror = true;
		setRotation(upperlefthorizontalheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperleftinnerheel = new PixelmonModelRenderer(
				this, 144, 0);
		upperleftinnerheel.addBox(-0.5F, 4F, -3F, 1, 2, 5);
		upperleftinnerheel.setRotationPoint(0F, 0F, 0F);
		upperleftinnerheel.setTextureSize(200, 200);
		upperleftinnerheel.mirror = true;
		setRotation(upperleftinnerheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperleftupperleftheel = new PixelmonModelRenderer(
				this, 123, 0);
		upperleftupperleftheel.addBox(0.5F, 4F, -4F, 1, 2, 1);
		upperleftupperleftheel.setRotationPoint(0F, 0F, 0F);
		upperleftupperleftheel.setTextureSize(200, 200);
		upperleftupperleftheel.mirror = true;
		setRotation(upperleftupperleftheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperleftupperrightheel = new PixelmonModelRenderer(
				this, 123, 0);
		upperleftupperrightheel.addBox(-1.5F, 4F, -4F, 1, 2, 1);
		upperleftupperrightheel.setRotationPoint(0F, 0F, 0F);
		upperleftupperrightheel.setTextureSize(200, 200);
		upperleftupperrightheel.mirror = true;
		setRotation(upperleftupperrightheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperleftlowerleftheel = new PixelmonModelRenderer(
				this, 123, 0);
		upperleftlowerleftheel.addBox(0.5F, 4F, -3.5F, 2, 2, 6);
		upperleftlowerleftheel.setRotationPoint(0F, 0F, 0F);
		upperleftlowerleftheel.setTextureSize(200, 200);
		upperleftlowerleftheel.mirror = true;
		setRotation(upperleftlowerleftheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperleftneck = new PixelmonModelRenderer(this,
				96, 0);
		upperleftneck.addBox(-2.5F, 0F, -2.5F, 5, 4, 5);
		upperleftneck.setRotationPoint(0F, 0F, 0F);
		upperleftneck.setTextureSize(200, 200);
		upperleftneck.mirror = true;
		setRotation(upperleftneck, 0F, 0F, 0F);
		PixelmonModelRenderer upperleftlowerrightheel = new PixelmonModelRenderer(
				this, 123, 0);
		upperleftlowerrightheel.addBox(-2.5F, 4F, -3.5F, 2, 2, 6);
		upperleftlowerrightheel.setRotationPoint(0F, 0F, 0F);
		upperleftlowerrightheel.setTextureSize(200, 200);
		upperleftlowerrightheel.mirror = true;
		setRotation(upperleftlowerrightheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperleftlowleg = new PixelmonModelRenderer(this,
				96, 0);
		upperleftlowleg.addBox(-3F, 3F, -3F, 6, 1, 6);
		upperleftlowleg.setRotationPoint(0F, 0F, 0F);
		upperleftlowleg.setTextureSize(200, 200);
		upperleftlowleg.mirror = true;
		setRotation(upperleftlowleg, 0F, 0F, 0F);

		UPPERLEFTLEGPIECE.addChild(upperlefthorizontalheel);
		UPPERLEFTLEGPIECE.addChild(upperleftinnerheel);
		UPPERLEFTLEGPIECE.addChild(upperleftlowerleftheel);
		UPPERLEFTLEGPIECE.addChild(upperleftlowerrightheel);
		UPPERLEFTLEGPIECE.addChild(upperleftupperleftheel);
		UPPERLEFTLEGPIECE.addChild(upperleftupperrightheel);
		UPPERLEFTLEGPIECE.addChild(upperleftneck);
		UPPERLEFTLEGPIECE.addChild(upperleftlowleg);

		BOTTOMRIGHTLEGPIECE = new PixelmonModelRenderer(this,
				"BOTTOMRIGHTLEGPIECE");
		BOTTOMRIGHTLEGPIECE.setRotationPoint(-4.5F, 17.96667F, 11.5F);
		setRotation(BOTTOMRIGHTLEGPIECE, 0F, 0F, 0F);
		BOTTOMRIGHTLEGPIECE.mirror = true;
		PixelmonModelRenderer bottomrighthorizontalheel = new PixelmonModelRenderer(
				this, 123, 10);
		bottomrighthorizontalheel.addBox(-3F, 4F, -2.5F, 6, 2, 4);
		bottomrighthorizontalheel.setRotationPoint(0F, 0F, 0F);
		bottomrighthorizontalheel.setTextureSize(200, 200);
		bottomrighthorizontalheel.mirror = true;
		setRotation(bottomrighthorizontalheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomrightlowerleftheel = new PixelmonModelRenderer(
				this, 123, 0);
		bottomrightlowerleftheel.addBox(0.5F, 4F, -3.5F, 2, 2, 6);
		bottomrightlowerleftheel.setRotationPoint(0F, 0F, 0F);
		bottomrightlowerleftheel.setTextureSize(200, 200);
		bottomrightlowerleftheel.mirror = true;
		setRotation(bottomrightlowerleftheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomrightinnerheel = new PixelmonModelRenderer(
				this, 144, 0);
		bottomrightinnerheel.addBox(-0.5F, 4F, -3F, 1, 2, 5);
		bottomrightinnerheel.setRotationPoint(0F, 0F, 0F);
		bottomrightinnerheel.setTextureSize(200, 200);
		bottomrightinnerheel.mirror = true;
		setRotation(bottomrightinnerheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomrightupperleftheel = new PixelmonModelRenderer(
				this, 123, 0);
		bottomrightupperleftheel.addBox(0.5F, 4F, -4F, 1, 2, 1);
		bottomrightupperleftheel.setRotationPoint(0F, 0F, 0F);
		bottomrightupperleftheel.setTextureSize(200, 200);
		bottomrightupperleftheel.mirror = true;
		setRotation(bottomrightupperleftheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomrightupperrightheel = new PixelmonModelRenderer(
				this, 123, 0);
		bottomrightupperrightheel.addBox(-1.5F, 4F, -4F, 1, 2, 1);
		bottomrightupperrightheel.setRotationPoint(0F, 0F, 0F);
		bottomrightupperrightheel.setTextureSize(200, 200);
		bottomrightupperrightheel.mirror = true;
		setRotation(bottomrightupperrightheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomrightlowleg = new PixelmonModelRenderer(
				this, 78, 0);
		bottomrightlowleg.addBox(-3F, 3F, -3F, 6, 1, 6);
		bottomrightlowleg.setRotationPoint(0F, 0F, 0F);
		bottomrightlowleg.setTextureSize(200, 200);
		bottomrightlowleg.mirror = true;
		setRotation(bottomrightlowleg, 0F, 0F, 0F);
		PixelmonModelRenderer bottomrightlowerrightheel = new PixelmonModelRenderer(
				this, 123, 0);
		bottomrightlowerrightheel.addBox(-2.5F, 4F, -3.5F, 2, 2, 6);
		bottomrightlowerrightheel.setRotationPoint(0F, 0F, 0F);
		bottomrightlowerrightheel.setTextureSize(200, 200);
		bottomrightlowerrightheel.mirror = true;
		setRotation(bottomrightlowerrightheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomrightneck = new PixelmonModelRenderer(this,
				28, 16);
		bottomrightneck.addBox(-2.5F, 0F, -2.5F, 5, 4, 5);
		bottomrightneck.setRotationPoint(0F, 0F, 0F);
		bottomrightneck.setTextureSize(200, 200);
		bottomrightneck.mirror = true;
		setRotation(bottomrightneck, 0F, 0F, 0F);

		BOTTOMRIGHTLEGPIECE.addChild(bottomrighthorizontalheel);
		BOTTOMRIGHTLEGPIECE.addChild(bottomrightinnerheel);
		BOTTOMRIGHTLEGPIECE.addChild(bottomrightlowerleftheel);
		BOTTOMRIGHTLEGPIECE.addChild(bottomrightlowerrightheel);
		BOTTOMRIGHTLEGPIECE.addChild(bottomrightupperleftheel);
		BOTTOMRIGHTLEGPIECE.addChild(bottomrightupperrightheel);
		BOTTOMRIGHTLEGPIECE.addChild(bottomrightneck);
		BOTTOMRIGHTLEGPIECE.addChild(bottomrightlowleg);

		UPPERRIGHTLEGPIECE = new PixelmonModelRenderer(this,
				"UPPERRIGHTLEGPIECE");
		UPPERRIGHTLEGPIECE.setRotationPoint(-4.5F, 18F, -10F);
		setRotation(UPPERRIGHTLEGPIECE, 0F, 0F, 0F);
		UPPERRIGHTLEGPIECE.mirror = true;
		PixelmonModelRenderer upperrightupperleftheel = new PixelmonModelRenderer(
				this, 123, 0);
		upperrightupperleftheel.addBox(0.5F, 4F, -4F, 1, 2, 1);
		upperrightupperleftheel.setRotationPoint(0F, 0F, 0F);
		upperrightupperleftheel.setTextureSize(200, 200);
		upperrightupperleftheel.mirror = true;
		setRotation(upperrightupperleftheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperrighthorizontalheel = new PixelmonModelRenderer(
				this, 123, 10);
		upperrighthorizontalheel.addBox(-3F, 4F, -2.5F, 6, 2, 4);
		upperrighthorizontalheel.setRotationPoint(0F, 0F, 0F);
		upperrighthorizontalheel.setTextureSize(200, 200);
		upperrighthorizontalheel.mirror = true;
		setRotation(upperrighthorizontalheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperrightinnerheel = new PixelmonModelRenderer(
				this, 144, 0);
		upperrightinnerheel.addBox(-0.5F, 4F, -3F, 1, 2, 5);
		upperrightinnerheel.setRotationPoint(0F, 0F, 0F);
		upperrightinnerheel.setTextureSize(200, 200);
		upperrightinnerheel.mirror = true;
		setRotation(upperrightinnerheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperrightlowerleftheel = new PixelmonModelRenderer(
				this, 123, 0);
		upperrightlowerleftheel.addBox(0.5F, 4F, -3.5F, 2, 2, 6);
		upperrightlowerleftheel.setRotationPoint(0F, 0F, 0F);
		upperrightlowerleftheel.setTextureSize(200, 200);
		upperrightlowerleftheel.mirror = true;
		setRotation(upperrightlowerleftheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperrightupperrightheel = new PixelmonModelRenderer(
				this, 123, 0);
		upperrightupperrightheel.addBox(-1.5F, 4F, -4F, 1, 2, 1);
		upperrightupperrightheel.setRotationPoint(0F, 0F, 0F);
		upperrightupperrightheel.setTextureSize(200, 200);
		upperrightupperrightheel.mirror = true;
		setRotation(upperrightupperrightheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperrightlowerrightheel = new PixelmonModelRenderer(
				this, 123, 0);
		upperrightlowerrightheel.addBox(-2.5F, 4F, -3.5F, 2, 2, 6);
		upperrightlowerrightheel.setRotationPoint(0F, 0F, 0F);
		upperrightlowerrightheel.setTextureSize(200, 200);
		upperrightlowerrightheel.mirror = true;
		setRotation(upperrightlowerrightheel, 0F, 0F, 0F);
		PixelmonModelRenderer upperrightneck = new PixelmonModelRenderer(this,
				100, 1);
		upperrightneck.addBox(-2.5F, 0F, -2.5F, 5, 4, 5);
		upperrightneck.setRotationPoint(0F, 0F, 0F);
		upperrightneck.setTextureSize(200, 200);
		upperrightneck.mirror = true;
		setRotation(upperrightneck, 0F, 0F, 0F);
		PixelmonModelRenderer upperrightlowleg = new PixelmonModelRenderer(
				this, 96, 0);
		upperrightlowleg.addBox(-3F, 3F, -3F, 6, 1, 6);
		upperrightlowleg.setRotationPoint(0F, 0F, 0F);
		upperrightlowleg.setTextureSize(200, 200);
		upperrightlowleg.mirror = true;
		setRotation(upperrightlowleg, 0F, 0F, 0F);

		UPPERRIGHTLEGPIECE.addChild(upperrighthorizontalheel);
		UPPERRIGHTLEGPIECE.addChild(upperrightinnerheel);
		UPPERRIGHTLEGPIECE.addChild(upperrightlowerleftheel);
		UPPERRIGHTLEGPIECE.addChild(upperrightlowerrightheel);
		UPPERRIGHTLEGPIECE.addChild(upperrightupperleftheel);
		UPPERRIGHTLEGPIECE.addChild(upperrightupperrightheel);
		UPPERRIGHTLEGPIECE.addChild(upperrightneck);
		UPPERRIGHTLEGPIECE.addChild(upperrightlowleg);

		BOTTOMLEFTLEGPIECE = new PixelmonModelRenderer(this,
				"BOTTOMLEFTLEGPIECE");
		BOTTOMLEFTLEGPIECE.setRotationPoint(4.5F, 18F, 11.5F);
		setRotation(BOTTOMLEFTLEGPIECE, 0F, 0F, 0F);
		BOTTOMLEFTLEGPIECE.mirror = true;
		PixelmonModelRenderer bottomlefthorizontalheel = new PixelmonModelRenderer(
				this, 123, 10);
		bottomlefthorizontalheel.addBox(-3F, 4F, -2.5F, 6, 2, 4);
		bottomlefthorizontalheel.setRotationPoint(0F, 0F, 0F);
		bottomlefthorizontalheel.setTextureSize(200, 200);
		bottomlefthorizontalheel.mirror = true;
		setRotation(bottomlefthorizontalheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomleftinnerheel = new PixelmonModelRenderer(
				this, 144, 0);
		bottomleftinnerheel.addBox(-0.5F, 4F, -3F, 1, 2, 5);
		bottomleftinnerheel.setRotationPoint(0F, 0F, 0F);
		bottomleftinnerheel.setTextureSize(200, 200);
		bottomleftinnerheel.mirror = true;
		setRotation(bottomleftinnerheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomleftlowerleftheel = new PixelmonModelRenderer(
				this, 123, 0);
		bottomleftlowerleftheel.addBox(0.5F, 4F, -3.5F, 2, 2, 6);
		bottomleftlowerleftheel.setRotationPoint(0F, 0F, 0F);
		bottomleftlowerleftheel.setTextureSize(200, 200);
		bottomleftlowerleftheel.mirror = true;
		setRotation(bottomleftlowerleftheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomleftlowerrightheel = new PixelmonModelRenderer(
				this, 123, 0);
		bottomleftlowerrightheel.addBox(-2.5F, 4F, -3.5F, 2, 2, 6);
		bottomleftlowerrightheel.setRotationPoint(0F, 0F, 0F);
		bottomleftlowerrightheel.setTextureSize(200, 200);
		bottomleftlowerrightheel.mirror = true;
		setRotation(bottomleftlowerrightheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomleftupperleftheel = new PixelmonModelRenderer(
				this, 123, 0);
		bottomleftupperleftheel.addBox(0.5F, 4F, -4F, 1, 2, 1);
		bottomleftupperleftheel.setRotationPoint(0F, 0F, 0F);
		bottomleftupperleftheel.setTextureSize(200, 200);
		bottomleftupperleftheel.mirror = true;
		setRotation(bottomleftupperleftheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomleftupperrightheel = new PixelmonModelRenderer(
				this, 123, 0);
		bottomleftupperrightheel.addBox(-1.5F, 4F, -4F, 1, 2, 1);
		bottomleftupperrightheel.setRotationPoint(0F, 0F, 0F);
		bottomleftupperrightheel.setTextureSize(200, 200);
		bottomleftupperrightheel.mirror = true;
		setRotation(bottomleftupperrightheel, 0F, 0F, 0F);
		PixelmonModelRenderer bottomleftneck = new PixelmonModelRenderer(this,
				100, 1);
		bottomleftneck.addBox(-2.5F, 0F, -2.5F, 5, 4, 5);
		bottomleftneck.setRotationPoint(0F, 0F, 0F);
		bottomleftneck.setTextureSize(200, 200);
		bottomleftneck.mirror = true;
		setRotation(bottomleftneck, 0F, 0F, 0F);
		PixelmonModelRenderer bottomleftlowleg = new PixelmonModelRenderer(
				this, 96, 1);
		bottomleftlowleg.addBox(-3F, 3F, -3F, 6, 1, 6);
		bottomleftlowleg.setRotationPoint(0F, 0F, 0F);
		bottomleftlowleg.setTextureSize(200, 200);
		bottomleftlowleg.mirror = true;
		setRotation(bottomleftlowleg, 0F, 0F, 0F);

		BOTTOMLEFTLEGPIECE.addChild(bottomlefthorizontalheel);
		BOTTOMLEFTLEGPIECE.addChild(bottomleftinnerheel);
		BOTTOMLEFTLEGPIECE.addChild(bottomleftlowerleftheel);
		BOTTOMLEFTLEGPIECE.addChild(bottomleftlowerrightheel);
		BOTTOMLEFTLEGPIECE.addChild(bottomleftupperleftheel);
		BOTTOMLEFTLEGPIECE.addChild(bottomleftupperrightheel);
		BOTTOMLEFTLEGPIECE.addChild(bottomleftneck);
		BOTTOMLEFTLEGPIECE.addChild(bottomleftlowleg);

		BODYPIECE = new PixelmonModelRenderer(this, "BODYPIECE");
		BODYPIECE.setRotationPoint(0F, 0F, 0F);
		setRotation(BODYPIECE, 0F, 0F, 0F);
		BODYPIECE.mirror = true;
		PixelmonModelRenderer rock10 = new PixelmonModelRenderer(this, 140, 90);
		rock10.addBox(4F, -11F, 5F, 3, 2, 3);
		rock10.setRotationPoint(0F, 10F, 0F);
		rock10.setTextureSize(200, 200);
		rock10.mirror = true;
		setRotation(rock10, 0F, 0F, 0F);
		PixelmonModelRenderer rock6 = new PixelmonModelRenderer(this, 138, 85);
		rock6.addBox(-7F, -12F, 5F, 3, 4, 4);
		rock6.setRotationPoint(0F, 10F, 0F);
		rock6.setTextureSize(200, 200);
		rock6.mirror = true;
		setRotation(rock6, 0F, 0F, 0F);
		PixelmonModelRenderer rock9 = new PixelmonModelRenderer(this, 141, 87);
		rock9.addBox(0F, -10.7F, 11F, 3, 2, 2);
		rock9.setRotationPoint(0F, 10F, 0F);
		rock9.setTextureSize(200, 200);
		rock9.mirror = true;
		setRotation(rock9, 0F, 0F, 0F);
		PixelmonModelRenderer Frontline1 = new PixelmonModelRenderer(this, 0,
				153);
		Frontline1.addBox(-4.5F, -16F, -11.5F, 9, 6, 1);
		Frontline1.setRotationPoint(0F, 10F, 0F);
		Frontline1.setTextureSize(200, 200);
		Frontline1.mirror = true;
		setRotation(Frontline1, 0F, 0F, 0F);
		PixelmonModelRenderer Backline4 = new PixelmonModelRenderer(this, 8,
				158);
		Backline4.addBox(-4.5F, -16F, 3.5F, 1, 6, 7);
		Backline4.setRotationPoint(0F, 10F, 0F);
		Backline4.setTextureSize(200, 200);
		Backline4.mirror = true;
		setRotation(Backline4, 0F, 0F, 0F);
		PixelmonModelRenderer rock7 = new PixelmonModelRenderer(this, 145, 89);
		rock7.addBox(-4F, -10.4F, 1F, 2, 2, 2);
		rock7.setRotationPoint(0F, 10F, 0F);
		rock7.setTextureSize(200, 200);
		rock7.mirror = true;
		setRotation(rock7, 0F, 0F, 0F);
		PixelmonModelRenderer rock8 = new PixelmonModelRenderer(this, 140, 93);
		rock8.addBox(-2F, -13F, -3.3F, 5, 4, 2);
		rock8.setRotationPoint(0F, 10F, 0F);
		rock8.setTextureSize(200, 200);
		rock8.mirror = true;
		setRotation(rock8, 0F, 0F, 0F);
		PixelmonModelRenderer rock4 = new PixelmonModelRenderer(this, 138, 95);
		rock4.addBox(2F, -11F, 1F, 2, 2, 2);
		rock4.setRotationPoint(0F, 10F, 0F);
		rock4.setTextureSize(200, 200);
		rock4.mirror = true;
		setRotation(rock4, 0F, 0F, 0F);
		PixelmonModelRenderer Backline3 = new PixelmonModelRenderer(this, 5,
				159);
		Backline3.addBox(3.5F, -16F, 3.5F, 1, 6, 7);
		Backline3.setRotationPoint(0F, 10F, 0F);
		Backline3.setTextureSize(200, 200);
		Backline3.mirror = true;
		setRotation(Backline3, 0F, 0F, 0F);
		PixelmonModelRenderer rock5 = new PixelmonModelRenderer(this, 140, 86);
		rock5.addBox(4F, -12F, -9F, 2, 3, 4);
		rock5.setRotationPoint(0F, 10F, 0F);
		rock5.setTextureSize(200, 200);
		rock5.mirror = true;
		setRotation(rock5, 0F, 0F, 0F);
		PixelmonModelRenderer rock3 = new PixelmonModelRenderer(this, 144, 96);
		rock3.addBox(2.1F, -11F, -13.2F, 3, 2, 2);
		rock3.setRotationPoint(0F, 10F, 0F);
		rock3.setTextureSize(200, 200);
		rock3.mirror = true;
		setRotation(rock3, 0F, 0F, 0F);
		PixelmonModelRenderer rock2 = new PixelmonModelRenderer(this, 142, 84);
		rock2.addBox(-5.2F, -10.3F, -13F, 4, 2, 2);
		rock2.setRotationPoint(0F, 10F, 0F);
		rock2.setTextureSize(200, 200);
		rock2.mirror = true;
		setRotation(rock2, 0F, 0F, 0F);
		PixelmonModelRenderer rock1 = new PixelmonModelRenderer(this, 139, 85);
		rock1.addBox(-6F, -11F, -7F, 2, 2, 2);
		rock1.setRotationPoint(0F, 10F, 0F);
		rock1.setTextureSize(200, 200);
		rock1.mirror = true;
		setRotation(rock1, 0F, 0F, 0F);
		PixelmonModelRenderer Frontline3 = new PixelmonModelRenderer(this, 7,
				153);
		Frontline3.addBox(3.5F, -16F, -10.5F, 1, 6, 7);
		Frontline3.setRotationPoint(0F, 10F, 0F);
		Frontline3.setTextureSize(200, 200);
		Frontline3.mirror = true;
		setRotation(Frontline3, 0F, 0F, 0F);
		PixelmonModelRenderer Backline2 = new PixelmonModelRenderer(this, 5,
				156);
		Backline2.addBox(-4.5F, -16F, 10.5F, 9, 6, 1);
		Backline2.setRotationPoint(0F, 10F, 0F);
		Backline2.setTextureSize(200, 200);
		Backline2.mirror = true;
		setRotation(Backline2, 0F, 0F, 0F);
		PixelmonModelRenderer Frontline4 = new PixelmonModelRenderer(this, 0,
				153);
		Frontline4.addBox(-4.5F, -16F, -10.5F, 1, 6, 7);
		Frontline4.setRotationPoint(0F, 10F, 0F);
		Frontline4.setTextureSize(200, 200);
		Frontline4.mirror = true;
		setRotation(Frontline4, 0F, 0F, 0F);
		PixelmonModelRenderer Backline1 = new PixelmonModelRenderer(this, 3,
				162);
		Backline1.addBox(-4.5F, -16F, 2.5F, 9, 6, 1);
		Backline1.setRotationPoint(0F, 10F, 0F);
		Backline1.setTextureSize(200, 200);
		Backline1.mirror = true;
		setRotation(Backline1, 0F, 0F, 0F);
		PixelmonModelRenderer Frontline2 = new PixelmonModelRenderer(this, 6,
				157);
		Frontline2.addBox(-4.5F, -16F, -3.5F, 9, 6, 1);
		Frontline2.setRotationPoint(0F, 10F, 0F);
		Frontline2.setTextureSize(200, 200);
		Frontline2.mirror = true;
		setRotation(Frontline2, 0F, 0F, 0F);
		PixelmonModelRenderer volcanobase2 = new PixelmonModelRenderer(this,
				20, 142);
		volcanobase2.addBox(-5F, -10F, 2F, 10, 1, 10);
		volcanobase2.setRotationPoint(0F, 10F, 0F);
		volcanobase2.setTextureSize(200, 200);
		volcanobase2.mirror = true;
		setRotation(volcanobase2, 0F, 0F, 0F);
		PixelmonModelRenderer horizontal1 = new PixelmonModelRenderer(this, 0,
				49);
		horizontal1.addBox(-8.5F, -8F, -15F, 17, 15, 30);
		horizontal1.setRotationPoint(0F, 10F, 0F);
		horizontal1.setTextureSize(200, 200);
		horizontal1.mirror = true;
		setRotation(horizontal1, 0F, 0F, 0F);
		PixelmonModelRenderer volcanobase1 = new PixelmonModelRenderer(this, 0,
				142);
		volcanobase1.addBox(-5F, -10F, -12F, 10, 1, 10);
		volcanobase1.setRotationPoint(0F, 10F, 0F);
		volcanobase1.setTextureSize(200, 200);
		volcanobase1.mirror = true;
		setRotation(volcanobase1, 0F, 0F, 0F);
		PixelmonModelRenderer horizontal3 = new PixelmonModelRenderer(this, 0,
				94);
		horizontal3.addBox(-7.5F, -8F, -16.5F, 15, 15, 33);
		horizontal3.setRotationPoint(0F, 10F, 0F);
		horizontal3.setTextureSize(200, 200);
		horizontal3.mirror = true;
		setRotation(horizontal3, 0F, 0F, 0F);
		PixelmonModelRenderer horizontal2 = new PixelmonModelRenderer(this, 0,
				94);
		horizontal2.addBox(-7.5F, 7F, -15F, 15, 1, 30);
		horizontal2.setRotationPoint(0F, 10F, 0F);
		horizontal2.setTextureSize(200, 200);
		horizontal2.mirror = true;
		setRotation(horizontal2, 0F, 0F, 0F);
		PixelmonModelRenderer body = new PixelmonModelRenderer(this, 0, 0);
		body.addBox(-8F, -9.5F, -16F, 16, 17, 32);
		body.setRotationPoint(0F, 10F, 0F);
		body.setTextureSize(200, 200);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);

		BODYPIECE.addChild(rock1);
		BODYPIECE.addChild(rock2);
		BODYPIECE.addChild(rock3);
		BODYPIECE.addChild(rock4);
		BODYPIECE.addChild(rock5);
		BODYPIECE.addChild(rock6);
		BODYPIECE.addChild(rock7);
		BODYPIECE.addChild(rock8);
		BODYPIECE.addChild(rock8);
		BODYPIECE.addChild(rock10);
		BODYPIECE.addChild(Frontline1);
		BODYPIECE.addChild(Frontline2);
		BODYPIECE.addChild(Frontline3);
		BODYPIECE.addChild(Frontline4);
		BODYPIECE.addChild(Backline1);
		BODYPIECE.addChild(Backline2);
		BODYPIECE.addChild(Backline3);
		BODYPIECE.addChild(Backline4);
		BODYPIECE.addChild(volcanobase1);
		BODYPIECE.addChild(volcanobase2);
		BODYPIECE.addChild(horizontal1);
		BODYPIECE.addChild(horizontal2);
		BODYPIECE.addChild(horizontal3);
		BODYPIECE.addChild(body);

		UPPERTAILPIECE = new PixelmonModelRenderer(this, "UPPERTAILPIECE");
		UPPERTAILPIECE.setRotationPoint(0F, 10.5F, 16.5F);
		setRotation(UPPERTAILPIECE, 0F, 0F, 0F);
		UPPERTAILPIECE.mirror = true;
		PixelmonModelRenderer tailup = new PixelmonModelRenderer(this, 188, 0);
		tailup.addBox(-2F, 0F, 0F, 4, 4, 1);
		tailup.setRotationPoint(0F, 0F, 0F);
		tailup.setTextureSize(200, 200);
		tailup.mirror = true;
		setRotation(tailup, 0F, 0F, 0F);

		UPPERTAILPIECE.addChild(tailup);

		LOWERTAILPIECE = new PixelmonModelRenderer(this, "LOWERTAILPIECE");
		LOWERTAILPIECE.setRotationPoint(0F, 4F, 0F);
		setRotation(LOWERTAILPIECE, 0F, 0F, 0F);
		LOWERTAILPIECE.mirror = true;
		PixelmonModelRenderer taildown = new PixelmonModelRenderer(this, 97, 0);
		taildown.addBox(-1.5F, 0F, 0F, 3, 3, 1);
		taildown.setRotationPoint(0F, 0F, 0F);
		taildown.setTextureSize(200, 200);
		taildown.mirror = true;
		setRotation(taildown, 0F, 0F, 0F);

		LOWERTAILPIECE.addChild(taildown);
		UPPERTAILPIECE.addChild(LOWERTAILPIECE);

		NECKPIECE = new PixelmonModelRenderer(this, "NECKPIECE");
		NECKPIECE.setRotationPoint(0F, 10.5F, -16.5F);
		setRotation(NECKPIECE, 0F, 0F, 0F);
		NECKPIECE.mirror = true;
		PixelmonModelRenderer neck = new PixelmonModelRenderer(this, 63, 15);
		neck.addBox(-3F, -4F, -4F, 6, 9, 5);
		neck.setRotationPoint(0F, 0F, 0F);
		neck.setTextureSize(200, 200);
		neck.mirror = true;
		setRotation(neck, 0F, 0F, 0F);

		NECKPIECE.addChild(neck);
		HEADPIECE = new PixelmonModelRenderer(this, "HEADPIECE");
		HEADPIECE.setRotationPoint(0F, 0F, -3F);
		setRotation(HEADPIECE, 0F, 0F, 0F);
		HEADPIECE.mirror = true;
		PixelmonModelRenderer righteye = new PixelmonModelRenderer(this, 108,
				65);
		righteye.addBox(-4.5F, -4F, -6.2F, 2, 5, 2);
		righteye.setRotationPoint(0F, 0F, 0F);
		righteye.setTextureSize(200, 200);
		righteye.mirror = true;
		setRotation(righteye, 0F, 0F, 0F);
		PixelmonModelRenderer top1upper = new PixelmonModelRenderer(this, 101,
				56);
		top1upper.addBox(-1F, -10F, -3F, 2, 4, 4);
		top1upper.setRotationPoint(0F, 0F, 0F);
		top1upper.setTextureSize(200, 200);
		top1upper.mirror = true;
		setRotation(top1upper, 0F, 0F, 0F);
		PixelmonModelRenderer lefteye = new PixelmonModelRenderer(this, 117, 65);
		lefteye.addBox(2.5F, -4F, -6.2F, 2, 5, 2);
		lefteye.setRotationPoint(0F, 0F, 0F);
		lefteye.setTextureSize(200, 200);
		lefteye.mirror = true;
		setRotation(lefteye, 0F, 0F, 0F);
		PixelmonModelRenderer top2lower = new PixelmonModelRenderer(this, 101,
				56);
		top2lower.addBox(-1F, -7F, -6F, 2, 1, 3);
		top2lower.setRotationPoint(0F, 0F, 0F);
		top2lower.setTextureSize(200, 200);
		top2lower.mirror = true;
		setRotation(top2lower, 0F, 0F, 0F);
		PixelmonModelRenderer head = new PixelmonModelRenderer(this, 98, 27);
		head.addBox(-4F, -6F, -6F, 8, 8, 6);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(200, 200);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		PixelmonModelRenderer noses = new PixelmonModelRenderer(this, 101, 46);
		noses.addBox(-5F, 2F, -6F, 10, 4, 2);
		noses.setRotationPoint(0F, 0F, 0F);
		noses.setTextureSize(200, 200);
		noses.mirror = true;
		setRotation(noses, 0F, 0F, 0F);
		PixelmonModelRenderer leftwisp = new PixelmonModelRenderer(this, 101,
				56);
		leftwisp.addBox(-1F, -8.9F, -2.9F, 1, 4, 3);
		leftwisp.setRotationPoint(0F, 0F, 0F);
		leftwisp.setTextureSize(200, 200);
		leftwisp.mirror = true;
		setRotation(leftwisp, 0F, 0F, 0.4363323F);
		PixelmonModelRenderer rightwisp = new PixelmonModelRenderer(this, 101,
				56);
		rightwisp.addBox(0F, -8.9F, -2.9F, 1, 4, 3);
		rightwisp.setRotationPoint(0F, 0F, 0F);
		rightwisp.setTextureSize(200, 200);
		rightwisp.mirror = true;
		setRotation(rightwisp, 0F, 0F, -0.4363323F);
		PixelmonModelRenderer leftear1 = new PixelmonModelRenderer(this, 97, 14);
		leftear1.addBox(3F, -6.1F, -5F, 4, 3, 3);
		leftear1.setRotationPoint(0F, 0F, 0F);
		leftear1.setTextureSize(200, 200);
		leftear1.mirror = true;
		setRotation(leftear1, 0F, 0F, 0.1745329F);
		PixelmonModelRenderer leftear2 = new PixelmonModelRenderer(this, 97, 14);
		leftear2.addBox(7F, -6.1F, -4F, 2, 3, 2);
		leftear2.setRotationPoint(0F, 0F, 0F);
		leftear2.setTextureSize(200, 200);
		leftear2.mirror = true;
		setRotation(leftear2, 0F, 0F, 0.1745329F);
		PixelmonModelRenderer rightear1 = new PixelmonModelRenderer(this, 97,
				14);
		rightear1.addBox(-6.3F, -6.1F, -5F, 4, 3, 3);
		rightear1.setRotationPoint(0F, 0F, 0F);
		rightear1.setTextureSize(200, 200);
		rightear1.mirror = true;
		setRotation(rightear1, 0F, 0F, -0.1745329F);
		PixelmonModelRenderer rightear2 = new PixelmonModelRenderer(this, 97,
				14);
		rightear2.addBox(-8.3F, -6.1F, -4F, 2, 3, 2);
		rightear2.setRotationPoint(0F, 0F, 0F);
		rightear2.setTextureSize(200, 200);
		rightear2.mirror = true;
		setRotation(rightear2, 0F, 0F, -0.1745329F);

		HEADPIECE.addChild(righteye);
		HEADPIECE.addChild(lefteye);
		HEADPIECE.addChild(rightwisp);
		HEADPIECE.addChild(leftwisp);
		HEADPIECE.addChild(rightear1);
		HEADPIECE.addChild(leftear1);
		HEADPIECE.addChild(rightear2);
		HEADPIECE.addChild(leftear2);
		HEADPIECE.addChild(noses);
		HEADPIECE.addChild(head);
		HEADPIECE.addChild(top1upper);
		HEADPIECE.addChild(top2lower);
		NECKPIECE.addChild(HEADPIECE);

		MOUTHPIECE = new PixelmonModelRenderer(this, "MOUTHPIECE");
		MOUTHPIECE.setRotationPoint(0F, 2F, -1F);
		setRotation(MOUTHPIECE, 0F, 0F, 0F);
		MOUTHPIECE.mirror = true;
		PixelmonModelRenderer mouth = new PixelmonModelRenderer(this, 131, 32);
		mouth.addBox(-5F, 0F, -3F, 10, 4, 4);
		mouth.setRotationPoint(0F, 0F, 0F);
		mouth.setTextureSize(200, 200);
		mouth.mirror = true;
		setRotation(mouth, 0F, 0F, 0F);

		MOUTHPIECE.addChild(mouth);
		HEADPIECE.addChild(MOUTHPIECE);

		BODYPIECE.addChild(UPPERLEFTLEGPIECE);
		BODYPIECE.addChild(UPPERRIGHTLEGPIECE);
		BODYPIECE.addChild(BOTTOMLEFTLEGPIECE);
		BODYPIECE.addChild(BOTTOMRIGHTLEGPIECE);
		BODYPIECE.addChild(UPPERTAILPIECE);
		BODYPIECE.addChild(NECKPIECE);

		ModuleHead headModule = new ModuleHead(HEADPIECE);
		float legspeed = 0.8F;
		float legRotationLimit = 1.1F;

		ModuleLeg frontlegLModule = new ModuleLeg(UPPERLEFTLEGPIECE,
				EnumLeg.FrontLeft, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit,
				legspeed);
		ModuleLeg frontlegRModule = new ModuleLeg(UPPERRIGHTLEGPIECE,
				EnumLeg.FrontRight, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit,
				legspeed);
		ModuleLeg backlegLModule = new ModuleLeg(BOTTOMLEFTLEGPIECE,
				EnumLeg.BackLeft, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit,
				legspeed);
		ModuleLeg backlegRModule = new ModuleLeg(BOTTOMRIGHTLEGPIECE,
				EnumLeg.BackRight, EnumPhase.OutPhase, EnumRotation.x, legRotationLimit,
				legspeed);

		skeleton = new SkeletonQuadruped(BODYPIECE, headModule,
				frontlegLModule, frontlegRModule, backlegLModule,
				backlegRModule, null);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		BODYPIECE.render(f5);
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y,
			float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}