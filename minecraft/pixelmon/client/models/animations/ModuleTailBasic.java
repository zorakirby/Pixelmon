package pixelmon.client.models.animations;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleTailBasic extends ModuleTail {
	float PI = (float) Math.PI;

	public ModuleTailBasic(ModelRenderer... tailArgs) {
		super(tailArgs);
	}

	public float TopAngle = 1 * PI / 4;
	public float initialOffset = PI / 2;
	public float offset = PI * 3 / 11;
	public float animationSpeed = -0.35f;
	public float dampingFactor = 0.99f;
	public boolean horizontal = true;

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {
		float currentAngle = 0;
		int count = 0;
		int dampingNum = -5;
		float speedModifier = 1f;
		for (int i = 0; i < tailParts.size(); i++) {
			ModelRenderer segment = tailParts.get(i);
			if (count > 0)
				dampingNum = count + 1;
			float angle = ((float) Math.pow(dampingFactor, dampingNum)) * TopAngle * (MathHelper.cos(animationSpeed * f + speedModifier * count * initialOffset)) - currentAngle;
			if (horizontal)
				segment.rotateAngleY = angle;
			else
				segment.rotateAngleX = angle;
			count++;
			speedModifier += 0.1f;
			currentAngle = 0;
			for (int j = 0; j < i; j++)
				if (horizontal)
					currentAngle += tailParts.get(j).rotateAngleY;
				else
					currentAngle += tailParts.get(j).rotateAngleX;
		}
	}
}
