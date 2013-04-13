package pixelmon.client.models.pokemon;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.Quadruped.SkeletonQuadruped;

public class ModelArcanine extends PixelmonModelBase {
	// fields
	ModelRenderer body;
	ModelRenderer tail;
	ModelRenderer backlegL;
	ModelRenderer head;
	ModelRenderer backlegR;
	ModelRenderer frontlegR;
	ModelRenderer frontlegL;

	public ModelArcanine() {
		textureWidth = 128;
		textureHeight = 64;

		body = new ModelRenderer(this, "body");
		body.setRotationPoint(1.5F, 9.5F, 0.5F);
		setRotation(body, 0F, 0F, 0F);
		body.mirror = true;

		ModelRenderer bodyback = new ModelRenderer(this, 0, 44);
		bodyback.addBox(-5F, -4.5F, -3F, 8, 8, 12);
		bodyback.setRotationPoint(0F, 0F, 0F);
		bodyback.setTextureSize(128, 64);
		bodyback.mirror = true;
		setRotation(bodyback, 0F, 0F, 0F);

		ModelRenderer bodyfront = new ModelRenderer(this, 0, 25);
		bodyfront.addBox(-5.5F, -5.5F, -11F, 9, 9, 9);
		bodyfront.setRotationPoint(0F, 0F, 0F);
		bodyfront.setTextureSize(128, 64);
		bodyfront.mirror = true;
		setRotation(bodyfront, 0F, 0F, 0F);

		ModelRenderer bodychest = new ModelRenderer(this, 112, 55);
		bodychest.addBox(-4.5F, -5.5F, -12F, 7, 8, 1);
		bodychest.setRotationPoint(0F, 0F, 0F);
		bodychest.setTextureSize(128, 64);
		bodychest.mirror = true;
		setRotation(bodychest, 0F, 0F, 0F);

		ModelRenderer fur30 = new ModelRenderer(this, 116, 59);
		fur30.addBox(-2F, -8.5F, -4.5F, 2, 1, 4);
		fur30.setRotationPoint(0F, 0F, 0F);
		fur30.setTextureSize(128, 64);
		fur30.mirror = true;
		setRotation(fur30, 0.5235988F, 0F, 0F);

		ModelRenderer fur31 = new ModelRenderer(this, 114, 58);
		fur31.addBox(-2F, -7F, -4.5F, 2, 1, 5);
		fur31.setRotationPoint(0F, 0F, 0F);
		fur31.setTextureSize(128, 64);
		fur31.mirror = true;
		setRotation(fur31, 0.3490659F, 0F, 0F);

		ModelRenderer fur32 = new ModelRenderer(this, 116, 60);
		fur32.addBox(-2.5F, -12.5F, -6.5F, 3, 1, 3);
		fur32.setRotationPoint(0F, 0F, 0F);
		fur32.setTextureSize(128, 64);
		fur32.mirror = true;
		setRotation(fur32, 0.8726646F, 0F, 0F);

		ModelRenderer fur33 = new ModelRenderer(this, 112, 58);
		fur33.addBox(-2.5F, -11.5F, -9F, 3, 1, 5);
		fur33.setRotationPoint(0F, 0F, 0F);
		fur33.setTextureSize(128, 64);
		fur33.mirror = true;
		setRotation(fur33, 0.8726646F, 0F, 0F);

		ModelRenderer fur34 = new ModelRenderer(this, 114, 59);
		fur34.addBox(-2.5F, -12F, -5.3F, 3, 1, 4);
		fur34.setRotationPoint(0F, 0F, 0F);
		fur34.setTextureSize(128, 64);
		fur34.mirror = true;
		setRotation(fur34, 1.22173F, 0F, 0F);

		body.addChild(bodyback);
		body.addChild(bodyfront);
		body.addChild(bodychest);
		body.addChild(fur30);
		body.addChild(fur31);
		body.addChild(fur32);
		body.addChild(fur33);
		body.addChild(fur34);

		tail = new ModelRenderer(this, "tail");
		tail.setRotationPoint(-1F, -4F, 7.5F);
		setRotation(tail, 0F, 0F, 0F);
		tail.mirror = true;

		ModelRenderer tail1 = new ModelRenderer(this, 114, 52);
		tail1.addBox(-1.5F, -7F, -0.5F, 3, 8, 4);
		tail1.setRotationPoint(0F, 0F, 0F);
		tail1.setTextureSize(128, 64);
		tail1.mirror = true;
		setRotation(tail1, 0F, 0F, 0F);

		ModelRenderer tail2 = new ModelRenderer(this, 73, 45);
		tail2.addBox(-2.5F, -8F, 0F, 5, 13, 5);
		tail2.setRotationPoint(0F, 0F, 0F);
		tail2.setTextureSize(128, 64);
		tail2.mirror = true;
		setRotation(tail2, 0F, 0F, 0F);

		ModelRenderer tail3 = new ModelRenderer(this, 100, 51);
		tail3.addBox(-1.5F, -8.7F, -6.5F, 3, 2, 11);
		tail3.setRotationPoint(0F, 0F, 0F);
		tail3.setTextureSize(128, 64);
		tail3.mirror = true;
		setRotation(tail3, -1.047198F, 0F, 0F);

		ModelRenderer tail4 = new ModelRenderer(this, 96, 47);
		tail4.addBox(-2F, -8.3F, -7F, 4, 4, 12);
		tail4.setRotationPoint(0F, 0F, 0F);
		tail4.setTextureSize(128, 64);
		tail4.mirror = true;
		setRotation(tail4, -1.047198F, 0F, 0F);

		ModelRenderer tail5 = new ModelRenderer(this, 94, 46);
		tail5.addBox(-1.5F, -7.7F, -7.5F, 3, 3, 14);
		tail5.setRotationPoint(0F, 0F, 0F);
		tail5.setTextureSize(128, 64);
		tail5.mirror = true;
		setRotation(tail5, -1.047198F, 0F, 0F);

		tail.addChild(tail1);
		tail.addChild(tail2);
		tail.addChild(tail3);
		tail.addChild(tail4);
		tail.addChild(tail5);

		body.addChild(tail);

		backlegL = new ModelRenderer(this, "backlegL");
		backlegL.setRotationPoint(3.5F, -2.5F, 4F);
		setRotation(backlegL, 0F, 0F, 0F);
		backlegL.mirror = true;

		ModelRenderer backlegL1 = new ModelRenderer(this, 114, 22);
		backlegL1.addBox(-1.8F, 0F, -2.5F, 2, 5, 5);
		backlegL1.setRotationPoint(0F, 0F, 0F);
		backlegL1.setTextureSize(128, 64);
		backlegL1.mirror = true;
		setRotation(backlegL1, 0F, 0F, 0F);

		ModelRenderer backlegL2 = new ModelRenderer(this, 110, 33);
		backlegL2.addBox(-3F, 0.5F, 3F, 3, 4, 6);
		backlegL2.setRotationPoint(0F, 0F, 0F);
		backlegL2.setTextureSize(128, 64);
		backlegL2.mirror = true;
		setRotation(backlegL2, -1.047198F, 0F, 0F);

		ModelRenderer backlegL3 = new ModelRenderer(this, 101, 22);
		backlegL3.addBox(-3F, 8.5F, 2.4F, 3, 6, 3);
		backlegL3.setRotationPoint(0F, 0F, 0F);
		backlegL3.setTextureSize(128, 64);
		backlegL3.mirror = true;
		setRotation(backlegL3, -0.1745329F, 0F, 0F);

		ModelRenderer paw7 = new ModelRenderer(this, 48, 0);
		paw7.addBox(-0.9F, 13.5F, -1F, 1, 2, 2);
		paw7.setRotationPoint(0F, 0F, 0F);
		paw7.setTextureSize(128, 64);
		paw7.mirror = true;
		setRotation(paw7, 0F, 0F, 0F);

		ModelRenderer paw8 = new ModelRenderer(this, 48, 0);
		paw8.addBox(-2F, 13.5F, -1F, 1, 2, 2);
		paw8.setRotationPoint(0F, 0F, 0F);
		paw8.setTextureSize(128, 64);
		paw8.mirror = true;
		setRotation(paw8, 0F, 0F, 0F);

		ModelRenderer paw9 = new ModelRenderer(this, 48, 0);
		paw9.addBox(-3.1F, 13.5F, -1F, 1, 2, 2);
		paw9.setRotationPoint(0F, 0F, 0F);
		paw9.setTextureSize(128, 64);
		paw9.mirror = true;
		setRotation(paw9, 0F, 0F, 0F);

		ModelRenderer paw10 = new ModelRenderer(this, 116, 33);
		paw10.addBox(-3F, 13.5F, -0.1F, 3, 2, 3);
		paw10.setRotationPoint(0F, 0F, 0F);
		paw10.setTextureSize(128, 64);
		paw10.mirror = true;
		setRotation(paw10, 0F, 0F, 0F);

		ModelRenderer fur7 = new ModelRenderer(this, 114, 55);
		fur7.addBox(-0.5F, 8F, 0F, 1, 3, 6);
		fur7.setRotationPoint(0F, 0F, 0F);
		fur7.setTextureSize(128, 64);
		fur7.mirror = true;
		setRotation(fur7, 0F, 0F, 0F);

		ModelRenderer fur8 = new ModelRenderer(this, 114, 56);
		fur8.addBox(-2.5F, 8F, 0F, 2, 3, 5);
		fur8.setRotationPoint(0F, 0F, 0F);
		fur8.setTextureSize(128, 64);
		fur8.mirror = true;
		setRotation(fur8, 0F, 0F, 0F);

		ModelRenderer fur9 = new ModelRenderer(this, 110, 53);
		fur9.addBox(-3.5F, 8F, 0F, 1, 3, 8);
		fur9.setRotationPoint(0F, 0F, 0F);
		fur9.setTextureSize(128, 64);
		fur9.mirror = true;
		setRotation(fur9, 0F, 0F, 0F);

		backlegL.addChild(backlegL1);
		backlegL.addChild(backlegL2);
		backlegL.addChild(backlegL3);
		backlegL.addChild(paw7);
		backlegL.addChild(paw8);
		backlegL.addChild(paw9);
		backlegL.addChild(paw10);
		backlegL.addChild(fur7);
		backlegL.addChild(fur8);
		backlegL.addChild(fur9);

		body.addChild(backlegL);

		PixelmonModelRenderer HeadPiece = new PixelmonModelRenderer(this,
				"Head");
		HeadPiece.setRotationPoint(-1.5F, -6F, -8.5F);
		head = new ModelRenderer(this, "head");
		head.setRotationPoint(0.5F, 4F, -7.5F);
		setRotation(head, 0F, 0F, 0F);
		head.mirror = true;

		ModelRenderer headback = new ModelRenderer(this, 30, 0);
		headback.addBox(-3F, -6F, -5.5F, 6, 6, 6);
		headback.setRotationPoint(0F, 0F, 0F);
		headback.setTextureSize(128, 64);
		headback.mirror = true;
		setRotation(headback, 0F, 0F, 0F);

		ModelRenderer headfront = new ModelRenderer(this, 54, 0);
		headfront.addBox(-2F, -3.5F, -10F, 4, 3, 6);
		headfront.setRotationPoint(0F, 0F, 0F);
		headfront.setTextureSize(128, 64);
		headfront.mirror = true;
		setRotation(headfront, 0F, 0F, 0F);

		ModelRenderer headfront2 = new ModelRenderer(this, 110, 58);
		headfront2.addBox(-2F, -4F, -10F, 4, 1, 5);
		headfront2.setRotationPoint(0F, 0F, 0F);
		headfront2.setTextureSize(128, 64);
		headfront2.mirror = true;
		setRotation(headfront2, 0F, 0F, 0F);

		ModelRenderer eyeL = new ModelRenderer(this, 122, 0);
		eyeL.addBox(4.7F, -2.5F, -5.8F, 1, 1, 2);
		eyeL.setRotationPoint(0F, 0F, 0F);
		eyeL.setTextureSize(128, 64);
		eyeL.mirror = true;
		setRotation(eyeL, 0F, 0.3490659F, -0.5235988F);

		ModelRenderer eyeR = new ModelRenderer(this, 92, 0);
		eyeR.addBox(-5.7F, -2.5F, -5.7F, 1, 1, 2);
		eyeR.setRotationPoint(0F, 0F, 0F);
		eyeR.setTextureSize(128, 64);
		eyeR.mirror = true;
		setRotation(eyeR, 0F, -0.3490659F, 0.5235988F);

		ModelRenderer earL = new ModelRenderer(this, 118, 33);
		earL.addBox(2.2F, -5F, -3.7F, 1, 2, 4);
		earL.setRotationPoint(0F, 0F, 0F);
		earL.setTextureSize(128, 64);
		earL.mirror = true;
		setRotation(earL, 0F, 0F, 0F);

		ModelRenderer earR = new ModelRenderer(this, 118, 33);
		earR.addBox(-3.2F, -5F, -3.7F, 1, 2, 4);
		earR.setRotationPoint(0F, 0F, 0F);
		earR.setTextureSize(128, 64);
		earR.mirror = true;
		setRotation(earR, 0F, 0F, 0F);

		ModelRenderer innerearL = new ModelRenderer(this, 120, 60);
		innerearL.addBox(2.3F, -4.5F, -3.2F, 1, 1, 3);
		innerearL.setRotationPoint(0F, 0F, 0F);
		innerearL.setTextureSize(128, 64);
		innerearL.mirror = true;
		setRotation(innerearL, 0F, 0F, 0F);

		ModelRenderer innerearR = new ModelRenderer(this, 120, 60);
		innerearR.addBox(-3.3F, -4.5F, -3.2F, 1, 1, 3);
		innerearR.setRotationPoint(0F, 0F, 0F);
		innerearR.setTextureSize(128, 64);
		innerearR.mirror = true;
		setRotation(innerearR, 0F, 0F, 0F);

		ModelRenderer toothR = new ModelRenderer(this, 119, 2);
		toothR.addBox(-1.5F, -7F, -6F, 0, 1, 1);
		toothR.setRotationPoint(0F, 0F, 0F);
		toothR.setTextureSize(128, 64);
		toothR.mirror = true;
		setRotation(toothR, 0.7853982F, 0F, 0.0174533F);

		ModelRenderer toothL = new ModelRenderer(this, 119, 2);
		toothL.addBox(1.5F, -7F, -6F, 0, 1, 1);
		toothL.setRotationPoint(0F, 0F, 0F);
		toothL.setTextureSize(128, 64);
		toothL.mirror = true;
		setRotation(toothL, 0.7853982F, 0F, 0.0174533F);

		ModelRenderer tongue = new ModelRenderer(this, 104, 0);
		tongue.addBox(-1.5F, -1.5F, -8.5F, 3, 1, 4);
		tongue.setRotationPoint(0F, 0F, 0F);
		tongue.setTextureSize(128, 64);
		tongue.mirror = true;
		setRotation(tongue, 0.1745329F, 0F, 0F);

		ModelRenderer nose = new ModelRenderer(this, 118, 0);
		nose.addBox(-0.5F, -4.1F, -10.3F, 1, 1, 1);
		nose.setRotationPoint(0F, 0F, 0F);
		nose.setTextureSize(128, 64);
		nose.mirror = true;
		setRotation(nose, 0F, 0F, 0F);

		ModelRenderer chin = new ModelRenderer(this, 74, 0);
		chin.addBox(-2F, -1.4F, -9F, 4, 1, 5);
		chin.setRotationPoint(0F, 0F, 0F);
		chin.setTextureSize(128, 64);
		chin.mirror = true;
		setRotation(chin, 0.1745329F, 0F, 0F);

		ModelRenderer fur18 = new ModelRenderer(this, 104, 50);
		fur18.addBox(3.5F, -3.5F, -9F, 1, 3, 11);
		fur18.setRotationPoint(0F, 0F, 0F);
		fur18.setTextureSize(128, 64);
		fur18.mirror = true;
		setRotation(fur18, 0F, 0.2617994F, 0F);

		ModelRenderer fur19 = new ModelRenderer(this, 104, 50);
		fur19.addBox(-4.5F, -3.5F, -9F, 1, 3, 11);
		fur19.setRotationPoint(0F, 0F, 0F);
		fur19.setTextureSize(128, 64);
		fur19.mirror = true;
		setRotation(fur19, 0F, -0.2617994F, 0F);

		ModelRenderer fur20 = new ModelRenderer(this, 102, 52);
		fur20.addBox(-1F, -8F, -6.4F, 2, 1, 11);
		fur20.setRotationPoint(0F, 0F, 0F);
		fur20.setTextureSize(128, 64);
		fur20.mirror = true;
		setRotation(fur20, 0.5235988F, 0F, 0F);

		ModelRenderer fur21 = new ModelRenderer(this, 108, 55);
		fur21.addBox(-1F, -8.7F, -4.6F, 2, 1, 8);
		fur21.setRotationPoint(0F, 0F, 0F);
		fur21.setTextureSize(128, 64);
		fur21.mirror = true;
		setRotation(fur21, 0.6981317F, 0F, 0F);

		ModelRenderer fur22 = new ModelRenderer(this, 114, 58);
		fur22.addBox(-1F, -9.7F, -3F, 2, 1, 5);
		fur22.setRotationPoint(0F, 0F, 0F);
		fur22.setTextureSize(128, 64);
		fur22.mirror = true;
		setRotation(fur22, 0.8726646F, 0F, 0F);

		ModelRenderer fur23 = new ModelRenderer(this, 102, 52);
		fur23.addBox(-2F, -7.7F, -5.8F, 2, 1, 11);
		fur23.setRotationPoint(0F, 0F, 0F);
		fur23.setTextureSize(128, 64);
		fur23.mirror = true;
		setRotation(fur23, 0.5235988F, 0F, 0.5235988F);

		ModelRenderer fur24 = new ModelRenderer(this, 102, 52);
		fur24.addBox(0F, -7.7F, -6F, 2, 1, 11);
		fur24.setRotationPoint(0F, 0F, 0F);
		fur24.setTextureSize(128, 64);
		fur24.mirror = true;
		setRotation(fur24, 0.5235988F, 0F, -0.5235988F);

		ModelRenderer fur25 = new ModelRenderer(this, 114, 58);
		fur25.addBox(-1F, -6.5F, -0.5F, 2, 1, 5);
		fur25.setRotationPoint(0F, 0F, 0F);
		fur25.setTextureSize(128, 64);
		fur25.mirror = true;
		setRotation(fur25, 0.3490659F, 0F, 0F);

		ModelRenderer fur26 = new ModelRenderer(this, 118, 60);
		fur26.addBox(-1F, -5.5F, 1F, 2, 1, 3);
		fur26.setRotationPoint(0F, 0F, 0F);
		fur26.setTextureSize(128, 64);
		fur26.mirror = true;
		setRotation(fur26, 0.1745329F, 0F, 0F);

		ModelRenderer fur27 = new ModelRenderer(this, 116, 59);
		fur27.addBox(-1F, -1.6F, -5.5F, 2, 1, 4);
		fur27.setRotationPoint(0F, 0F, 0F);
		fur27.setTextureSize(128, 64);
		fur27.mirror = true;
		setRotation(fur27, -1.308997F, 0F, 0F);

		HeadPiece.addChild(head);
		HeadPiece.addChild(headback);
		HeadPiece.addChild(headfront);
		HeadPiece.addChild(headfront2);
		HeadPiece.addChild(eyeL);
		HeadPiece.addChild(eyeR);
		HeadPiece.addChild(earL);
		HeadPiece.addChild(earR);
		HeadPiece.addChild(innerearL);
		HeadPiece.addChild(innerearR);
		HeadPiece.addChild(toothR);
		HeadPiece.addChild(toothL);
		HeadPiece.addChild(nose);
		HeadPiece.addChild(chin);
		HeadPiece.addChild(tongue);
		HeadPiece.addChild(fur18);
		HeadPiece.addChild(fur19);
		HeadPiece.addChild(fur20);
		HeadPiece.addChild(fur21);
		HeadPiece.addChild(fur22);
		HeadPiece.addChild(fur23);
		HeadPiece.addChild(fur24);
		HeadPiece.addChild(fur25);
		HeadPiece.addChild(fur26);
		HeadPiece.addChild(fur27);

		body.addChild(HeadPiece);

		backlegR = new ModelRenderer(this, "backlegR");
		backlegR.setRotationPoint(-5.5F, -2.5F, 4F);
		setRotation(backlegR, 0F, 0F, 0F);
		backlegR.mirror = true;
		
		ModelRenderer backlegR1 = new ModelRenderer(this, 114, 22);
		backlegR1.addBox(-0.2F, 0.1F, -2.5F, 2, 5, 5);
		backlegR1.setRotationPoint(0F, 0F, 0F);
		backlegR1.setTextureSize(128, 64);
		backlegR1.mirror = true;
		
		setRotation(backlegR1, 0F, 0F, 0F);
		ModelRenderer backlegR2 = new ModelRenderer(this, 110, 33);
		backlegR2.addBox(0F, 0.5F, 3F, 3, 4, 6);
		backlegR2.setRotationPoint(0F, 0F, 0F);
		backlegR2.setTextureSize(128, 64);
		backlegR2.mirror = true;
		
		setRotation(backlegR2, -1.047198F, 0F, 0F);
		ModelRenderer backlegR3 = new ModelRenderer(this, 101, 22);
		backlegR3.addBox(0F, 8.5F, 2.5F, 3, 6, 3);
		backlegR3.setRotationPoint(0F, 0F, 0F);
		backlegR3.setTextureSize(128, 64);
		backlegR3.mirror = true;
		
		setRotation(backlegR3, -0.1745329F, 0F, 0F);
		ModelRenderer paw11 = new ModelRenderer(this, 48, 0);
		paw11.addBox(2.1F, 13.5F, -1F, 1, 2, 2);
		paw11.setRotationPoint(0F, 0F, 0F);
		paw11.setTextureSize(128, 64);
		paw11.mirror = true;
		
		setRotation(paw11, 0F, 0F, 0F);
		ModelRenderer paw12 = new ModelRenderer(this, 48, 0);
		paw12.addBox(1F, 13.5F, -1F, 1, 2, 2);
		paw12.setRotationPoint(0F, 0F, 0F);
		paw12.setTextureSize(128, 64);
		paw12.mirror = true;
		
		setRotation(paw12, 0F, 0F, 0F);
		ModelRenderer paw13 = new ModelRenderer(this, 48, 0);
		paw13.addBox(-0.1F, 13.5F, -1F, 1, 2, 2);
		paw13.setRotationPoint(0F, 0F, 0F);
		paw13.setTextureSize(128, 64);
		paw13.mirror = true;
		
		setRotation(paw13, 0F, 0F, 0F);
		ModelRenderer paw14 = new ModelRenderer(this, 116, 33);
		paw14.addBox(0F, 13.5F, 0F, 3, 2, 3);
		paw14.setRotationPoint(0F, 0F, 0F);
		paw14.setTextureSize(128, 64);
		paw14.mirror = true;
		
		setRotation(paw14, 0F, 0F, 0F);
		ModelRenderer fur11 = new ModelRenderer(this, 114, 56);
		fur11.addBox(0.5F, 8F, 0F, 2, 3, 5);
		fur11.setRotationPoint(0F, 0F, 0F);
		fur11.setTextureSize(128, 64);
		fur11.mirror = true;
		
		setRotation(fur11, 0F, 0F, 0F);
		ModelRenderer fur12 = new ModelRenderer(this, 114, 55);
		fur12.addBox(-0.5F, 8F, 0F, 1, 3, 6);
		fur12.setRotationPoint(0F, 0F, 0F);
		fur12.setTextureSize(128, 64);
		fur12.mirror = true;
		
		setRotation(fur12, 0F, 0F, 0F);
		ModelRenderer fur13 = new ModelRenderer(this, 110, 53);
		fur13.addBox(2.5F, 8F, 0F, 1, 3, 8);
		fur13.setRotationPoint(0F, 0F, 0F);
		fur13.setTextureSize(128, 64);
		fur13.mirror = true;
		setRotation(fur13, 0F, 0F, 0F);

		backlegR.addChild(backlegR1);
		backlegR.addChild(backlegR2);
		backlegR.addChild(backlegR3);
		backlegR.addChild(paw11);
		backlegR.addChild(paw12);
		backlegR.addChild(paw13);
		backlegR.addChild(paw14);
		backlegR.addChild(fur11);
		backlegR.addChild(fur12);
		backlegR.addChild(fur13);
		
		body.addChild(backlegR);

		frontlegR = new ModelRenderer(this, "frontlegR");
		frontlegR.setRotationPoint(-5.5F, 0F, -6.5F);
		setRotation(frontlegR, 0F, 0F, 0F);
		frontlegR.mirror = true;
		
		ModelRenderer frontlegright = new ModelRenderer(this, 116, 5);
		frontlegright.addBox(-0.5F, 0F, -1.5F, 3, 13, 3);
		frontlegright.setRotationPoint(0F, 0F, 0F);
		frontlegright.setTextureSize(128, 64);
		frontlegright.mirror = true;
		setRotation(frontlegright, 0F, 0F, 0F);

		ModelRenderer paw4 = new ModelRenderer(this, 48, 0);
		paw4.addBox(1.6F, 11F, -3F, 1, 2, 2);
		paw4.setRotationPoint(0F, 0F, 0F);
		paw4.setTextureSize(128, 64);
		paw4.mirror = true;
		setRotation(paw4, 0F, 0F, 0F);

		ModelRenderer paw5 = new ModelRenderer(this, 48, 0);
		paw5.addBox(0.5F, 11F, -3F, 1, 2, 2);
		paw5.setRotationPoint(0F, 0F, 0F);
		paw5.setTextureSize(128, 64);
		paw5.mirror = true;
		setRotation(paw5, 0F, 0F, 0F);

		ModelRenderer paw6 = new ModelRenderer(this, 48, 0);
		paw6.addBox(-0.6F, 11F, -3F, 1, 2, 2);
		paw6.setRotationPoint(0F, 0F, 0F);
		paw6.setTextureSize(128, 64);
		paw6.mirror = true;
		setRotation(paw6, 0F, 0F, 0F);

		ModelRenderer fur4 = new ModelRenderer(this, 120, 58);
		fur4.addBox(1.5F, 5F, 1.5F, 1, 3, 3);
		fur4.setRotationPoint(0F, 0F, 0F);
		fur4.setTextureSize(128, 64);
		fur4.mirror = true;
		setRotation(fur4, 0F, 0F, 0F);

		ModelRenderer fur5 = new ModelRenderer(this, 124, 60);
		fur5.addBox(0.5F, 5F, 1.5F, 1, 3, 1);
		fur5.setRotationPoint(0F, 0F, 0F);
		fur5.setTextureSize(128, 64);
		fur5.mirror = true;
		setRotation(fur5, 0F, 0F, 0F);

		ModelRenderer fur6 = new ModelRenderer(this, 122, 59);
		fur6.addBox(-0.5F, 5F, 1.5F, 1, 3, 2);
		fur6.setRotationPoint(0F, 0F, 0F);
		fur6.setTextureSize(128, 64);
		fur6.mirror = true;
		setRotation(fur6, 0F, 0F, 0F);

		frontlegR.addChild(frontlegright);
		frontlegR.addChild(paw4);
		frontlegR.addChild(paw5);
		frontlegR.addChild(paw6);
		frontlegR.addChild(fur4);
		frontlegR.addChild(fur5);
		frontlegR.addChild(fur6);
		
		body.addChild(frontlegR);

		frontlegL = new ModelRenderer(this, "frontlegL");
		frontlegL.setRotationPoint(3.5F, 0F, -6.5F);
		setRotation(frontlegL, 0F, 0F, 0F);
		frontlegL.mirror = true;
		ModelRenderer frontlegleft = new ModelRenderer(this, 116, 5);
		frontlegleft.addBox(-2.5F, 0F, -1.5F, 3, 13, 3);
		frontlegleft.setRotationPoint(0F, 0F, 0F);
		frontlegleft.setTextureSize(128, 64);
		frontlegleft.mirror = true;
		setRotation(frontlegleft, 0F, 0F, 0F);

		ModelRenderer paw1 = new ModelRenderer(this, 48, 0);
		paw1.addBox(-0.4F, 11F, -3F, 1, 2, 2);
		paw1.setRotationPoint(0F, 0F, 0F);
		paw1.setTextureSize(128, 64);
		paw1.mirror = true;
		setRotation(paw1, 0F, 0F, 0F);

		ModelRenderer paw2 = new ModelRenderer(this, 48, 0);
		paw2.addBox(-1.5F, 11F, -3F, 1, 2, 2);
		paw2.setRotationPoint(0F, 0F, 0F);
		paw2.setTextureSize(128, 64);
		paw2.mirror = true;
		setRotation(paw2, 0F, 0F, 0F);

		ModelRenderer paw3 = new ModelRenderer(this, 48, 0);
		paw3.addBox(-2.6F, 11F, -3F, 1, 2, 2);
		paw3.setRotationPoint(0F, 0F, 0F);
		paw3.setTextureSize(128, 64);
		paw3.mirror = true;
		setRotation(paw3, 0F, 0F, 0F);

		ModelRenderer fur1 = new ModelRenderer(this, 122, 59);
		fur1.addBox(-0.5F, 5F, 1.5F, 1, 3, 2);
		fur1.setRotationPoint(0F, 0F, 0F);
		fur1.setTextureSize(128, 64);
		fur1.mirror = true;
		setRotation(fur1, 0F, 0F, 0F);

		ModelRenderer fur2 = new ModelRenderer(this, 124, 60);
		fur2.addBox(-1.5F, 5F, 1.5F, 1, 3, 1);
		fur2.setRotationPoint(0F, 0F, 0F);
		fur2.setTextureSize(128, 64);
		fur2.mirror = true;
		setRotation(fur2, 0F, 0F, 0F);

		ModelRenderer fur3 = new ModelRenderer(this, 120, 58);
		fur3.addBox(-2.5F, 5F, 1.5F, 1, 3, 3);
		fur3.setRotationPoint(0F, 0F, 0F);
		fur3.setTextureSize(128, 64);
		fur3.mirror = true;
		setRotation(fur3, 0F, 0F, 0F);

		frontlegL.addChild(frontlegleft);
		frontlegL.addChild(paw1);
		frontlegL.addChild(paw2);
		frontlegL.addChild(paw3);
		frontlegL.addChild(fur1);
		frontlegL.addChild(fur2);
		frontlegL.addChild(fur3);
		
		body.addChild(frontlegL);

		ModuleHead headModule = new ModuleHead(HeadPiece);

		float legspeed = 0.523598776F;
		float legRotationLimit = 0.7F;

		ModuleLeg frontlegLModule = new ModuleLeg(frontlegL, EnumLeg.FrontLeft,
				EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg frontlegRModule = new ModuleLeg(frontlegR,
				EnumLeg.FrontRight, EnumPhase.OutPhase, legRotationLimit,
				legspeed);
		ModuleLeg backlegLModule = new ModuleLeg(backlegL, EnumLeg.BackLeft,
				EnumPhase.OutPhase, legRotationLimit, legspeed);
		ModuleLeg backlegRModule = new ModuleLeg(backlegR, EnumLeg.BackRight,
				EnumPhase.OutPhase, legRotationLimit, legspeed);
		
		skeleton = new SkeletonQuadruped(body, headModule, frontlegLModule,
				frontlegRModule, backlegLModule, backlegRModule);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body.render(f5);
	//	tail.render(f5);
	//	backlegL.render(f5);
	//	head.render(f5);
	//	backlegR.render(f5);
	//	frontlegR.render(f5);
	//	frontlegL.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	// public void setRotationAngles(float f, float f1, float f2, float f3,
	// float f4, float f5, Entity entity) {
	// super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	// head.rotateAngleX = f4 / 57.29578F;
	// head.rotateAngleY = f3 / 57.29578F;
	// backlegR.rotateAngleX = MathHelper.cos(f * 0.523598776F) * 0.7F * f1;
	// backlegL.rotateAngleX = MathHelper.cos(f * 0.523598776F + 3.141593F)
	// * 0.7F * f1;
	// frontlegR.rotateAngleX = MathHelper.cos(f * 0.523598776F + 3.141593F)
	// * 0.7F * f1;
	// frontlegL.rotateAngleX = MathHelper.cos(f * 0.523598776F) * 0.7F * f1;
	// }

}
