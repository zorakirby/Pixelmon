package pixelmon.client.models.animations.serpent;

import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class SkeletonSerpentFloating extends SkeletonBase {

	public ModelRenderer[] bodyParts;
	public int[] lengths;
	float animationAngle;
	float animationSpeed;
	float currentAngleY;
	float currentAngleX;
	float topAngle;
	float dampeningFactor;
	float phaseoffset;

	public SkeletonSerpentFloating(ModelRenderer body, ModuleHead headModule,
			float animationAngle, float topAngle, float dampeningFactor,
			float animationSpeed, float phaseoffset, ModelRenderer... bodyArgs) {
		super(body);
		modules.add(headModule);
		this.animationAngle = animationAngle;
		this.topAngle = topAngle;
		this.dampeningFactor = dampeningFactor;
		this.animationSpeed = animationSpeed;
		this.phaseoffset = phaseoffset;
		this.bodyParts = bodyArgs;
		lengths = new int[bodyParts.length];
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		float PI = (float) Math.PI;

		for (int i = 0; i < bodyParts.length; i++) {
			if (i == 0) {
				bodyParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(animationAngle))
						* topAngle
						* MathHelper.cos(f2 * animationSpeed);
				currentAngleY = bodyParts[i].rotateAngleY;

				bodyParts[i].rotateAngleX = MathHelper.sin((float) Math
						.toRadians(animationAngle))
						* topAngle
						* MathHelper.cos(f2 * animationSpeed);
				currentAngleX = bodyParts[i].rotateAngleX;

			} else {

				bodyParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(animationAngle))
						* ((float) Math.exp(dampeningFactor * i) * topAngle
								* MathHelper.cos(f2 * animationSpeed + i*phaseoffset) - currentAngleY);

				bodyParts[i].rotateAngleX = MathHelper.sin((float) Math
						.toRadians(animationAngle))
						* ((float) Math.exp(dampeningFactor * i) * topAngle
								* MathHelper.cos(f2 * animationSpeed + i*phaseoffset) - currentAngleX);

				currentAngleY = bodyParts[i].rotateAngleY + currentAngleY;
				currentAngleX = bodyParts[i].rotateAngleX + currentAngleX;
			}
		}
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		float PI = (float) Math.PI;

		for (int i = 0; i < bodyParts.length; i++) {
			if (i == 0) {
				bodyParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(animationAngle))
						* topAngle
						* MathHelper.cos(f2 * animationSpeed);
				currentAngleY = bodyParts[i].rotateAngleY;

				bodyParts[i].rotateAngleX = MathHelper.sin((float) Math
						.toRadians(animationAngle))
						* topAngle
						* MathHelper.cos(f2 * animationSpeed);
				currentAngleX = bodyParts[i].rotateAngleX;

			} else {

				bodyParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(animationAngle))
						* ((float) Math.exp(dampeningFactor * i) * topAngle
								* MathHelper.cos(f2 * animationSpeed + i*phaseoffset) - currentAngleY);

				bodyParts[i].rotateAngleX = MathHelper.sin((float) Math
						.toRadians(animationAngle))
						* ((float) Math.exp(dampeningFactor * i) * topAngle
								* MathHelper.cos(f2 * animationSpeed + i*phaseoffset) - currentAngleX);

				currentAngleY = bodyParts[i].rotateAngleY + currentAngleY;
				currentAngleX = bodyParts[i].rotateAngleX + currentAngleX;
			}
		}
	}
}