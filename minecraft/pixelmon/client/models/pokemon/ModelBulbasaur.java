package pixelmon.client.models.pokemon;

import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBulbasaur extends PixelmonModelBase// Quadruped
{
	PixelmonModelRenderer BodyPiece;

	public ModelBulbasaur() {
		PixelmonModelRenderer Left_ear;
		PixelmonModelRenderer Right_Ear;
		PixelmonModelRenderer body;
		PixelmonModelRenderer head;
		PixelmonModelRenderer bulbTop;
		PixelmonModelRenderer bulbBase;
		PixelmonModelRenderer legFR;
		PixelmonModelRenderer legRR;
		PixelmonModelRenderer legRL;
		PixelmonModelRenderer legFL;

		Left_ear = new PixelmonModelRenderer(this, 27, 0);
		Left_ear.addBox(2, -6, -4, 2, 2, 4, 0F);
		Left_ear.setRotationPoint(0, 0, 0);

		Left_ear.rotateAngleX = 0F;
		Left_ear.rotateAngleY = 0F;
		Left_ear.rotateAngleZ = 0F;

		Right_Ear = new PixelmonModelRenderer(this, 27, 0);
		Right_Ear.addBox(-4, -6, -4, 2, 2, 4, 0F);
		Right_Ear.setRotationPoint(0, 0, 0);

		Right_Ear.rotateAngleX = 0F;
		Right_Ear.rotateAngleY = 0F;
		Right_Ear.rotateAngleZ = 0F;

		BodyPiece = new PixelmonModelRenderer(this, "Body");
		BodyPiece.setRotationPoint(0, 14, 1);
		body = new PixelmonModelRenderer(this, 0, 0);
		body.addBox(-5, -7, -7, 10, 14, 7, 0F);
		body.setRotationPoint(0, 0, 0);
		body.rotateAngleX = 1.396263F;

		BodyPiece.addChild(body);

		head = new PixelmonModelRenderer(this, 32, 17);
		head.addBox(-4, -4, -8, 8, 7, 8, 0F);
		head.setRotationPoint(0, 0, 0);

		PixelmonModelRenderer HeadPiece;
		HeadPiece = new PixelmonModelRenderer(this, "Head");
		HeadPiece.setRotationPoint(0, 2, -8);
		HeadPiece.addChild(head);
		HeadPiece.addChild(Left_ear);
		HeadPiece.addChild(Right_Ear);

		BodyPiece.addChild(HeadPiece);

		bulbTop = new PixelmonModelRenderer(this, 14, 21);
		bulbTop.addBox(-3, -3, 5, 6, 6, 3, 0F);
		bulbTop.setRotationPoint(0, 0, 0);

		bulbTop.rotateAngleX = 1.396263F;
		BodyPiece.addChild(bulbTop);

		bulbBase = new PixelmonModelRenderer(this, 34, 2);
		bulbBase.addBox(-5, -5, 0, 10, 10, 5, 0F);
		bulbBase.setRotationPoint(0, 0, 0);
		BodyPiece.addChild(bulbBase);

		bulbBase.rotateAngleX = 1.396263F;

		legFR = new PixelmonModelRenderer(this, 0, 21);
		legFR.addBox(-3, 0, -2, 3, 8, 3, 0F);
		legFR.setRotationPoint(-5, 2, -4);
		BodyPiece.addChild(legFR);
		legRR = new PixelmonModelRenderer(this, 0, 21);
		legRR.addBox(-3, 0, -2, 3, 6, 3, 0F);
		legRR.setRotationPoint(-5, 4, 4);
		BodyPiece.addChild(legRR);

		legRL = new PixelmonModelRenderer(this, 0, 21);
		legRL.addBox(0, 0, -2, 3, 6, 3, 0F);
		legRL.setRotationPoint(5, 4, 4);
		BodyPiece.addChild(legRL);

		legFL = new PixelmonModelRenderer(this, 0, 21);
		legFL.addBox(0, 0, -2, 3, 8, 3, 0F);
		legFL.setRotationPoint(5, 2, -4);
		BodyPiece.addChild(legFL);

		ModuleHead headModule = new ModuleHead(HeadPiece);
		skeleton = new SkeletonQuadruped(BodyPiece, headModule, legFL, legFR, legRL, legRR, 1.4F, 1.4F, 0.6F);
	}

	public void render(Entity var1, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(var1, f, f1, f2, f3, f4, f5);
		BodyPiece.render(f5);
	}
}
