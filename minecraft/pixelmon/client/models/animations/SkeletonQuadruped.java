package pixelmon.client.models.animations;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class SkeletonQuadruped extends SkeletonBase {

	ModelRenderer flLeg, frLeg, blLeg, brLeg;
	float flLegInitX, flLegInitY;
	float frLegInitX, frLegInitY;
	float blLegInitX, blLegInitY;
	float brLegInitX, brLegInitY;
	float frontLegRotationLimit, backLegRotationLimit;
	float legSpeed;

	public SkeletonQuadruped(ModelRenderer body, Module headModule, ModelRenderer frontLeftLeg, ModelRenderer frontRightLeg, ModelRenderer backLeftLeg, ModelRenderer backRightLeg,
			float frontLegRotationLimit, float backLegRotationLimit, float legSpeed) {
		super(body);
		modules.add(headModule);
		flLeg = frontLeftLeg;
		frLeg = frontRightLeg;
		blLeg = backLeftLeg;
		brLeg = backRightLeg;
		flLegInitX = flLeg.rotateAngleX;
		flLegInitY = flLeg.rotateAngleY;
		frLegInitX = frLeg.rotateAngleX;
		frLegInitY = frLeg.rotateAngleY;
		blLegInitX = blLeg.rotateAngleX;
		blLegInitY = blLeg.rotateAngleY;
		brLegInitX = brLeg.rotateAngleX;
		brLegInitY = brLeg.rotateAngleY;

		this.frontLegRotationLimit = frontLegRotationLimit;
		this.backLegRotationLimit = backLegRotationLimit;
		this.legSpeed = legSpeed;

	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {
		super.walk(entity, f, f1, f2, f3, f4);
		brLeg.rotateAngleX = MathHelper.cos(f * legSpeed) * backLegRotationLimit * f1 + brLegInitX;
		blLeg.rotateAngleX = MathHelper.cos(f * legSpeed + 3.141593F) * backLegRotationLimit * f1 + blLegInitX;
		frLeg.rotateAngleX = MathHelper.cos(f * legSpeed + 3.141593F) * frontLegRotationLimit * f1 + frLegInitX;
		flLeg.rotateAngleX = MathHelper.cos(f * legSpeed) * frontLegRotationLimit * f1 + flLegInitX;
	}

}
