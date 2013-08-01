package pixelmon.client.models.animations.serpent;

import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class SkeletonSerpent extends SkeletonBase {

	public ModelRenderer[] bodyParts;
	public int[] lengths;
	float animationAngle;
	float animationSpeed;
	float currentAngleY;
	float currentAngleX;
	float topAngle;
	float dampeningFactor;

	public SkeletonSerpent(ModelRenderer body, ModuleHead headModule,
			float animationAngle, float topAngle, float dampeningFactor,
			float animationSpeed, ModelRenderer... bodyArgs) {
		super(body);
		modules.add(headModule);
		this.animationAngle = animationAngle;
		this.topAngle = topAngle;
		this.dampeningFactor = dampeningFactor;
		this.animationSpeed = animationSpeed;
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
						* MathHelper.cos(f * animationSpeed);
				currentAngleY = bodyParts[i].rotateAngleY;

				bodyParts[i].rotateAngleX = MathHelper.sin((float) Math
						.toRadians(animationAngle))
						* topAngle
						* MathHelper.cos(f * animationSpeed);
				currentAngleX = bodyParts[i].rotateAngleX;

			} else {

				bodyParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(animationAngle))
						* ((float) Math.exp(dampeningFactor * i) * topAngle
								* MathHelper.cos(f * animationSpeed + 20 / i) - currentAngleY);

				bodyParts[i].rotateAngleX = MathHelper.sin((float) Math
						.toRadians(animationAngle))
						* ((float) Math.exp(dampeningFactor * i) * topAngle
								* MathHelper.cos(f * animationSpeed + 20 / i) - currentAngleX);

				currentAngleY = bodyParts[i].rotateAngleY + currentAngleY;
				currentAngleX = bodyParts[i].rotateAngleX + currentAngleX;
			}
		}

		// float initialAngle = (float) -PI/6;
		// float topAngle = initialAngle*3+PI/6;
		// for (int i =0; i < bodyParts.length; i++){
		// if(i==0){
		// bodyParts[i].rotateAngleX = initialAngle*MathHelper.cos(f/2);
		// }
		// else{
		// bodyParts[i].rotateAngleX = (float)
		// (-topAngle*Math.exp(-1/2*i)*MathHelper.cos(f/2));
		// topAngle = -topAngle;
		// }
		// }
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}
