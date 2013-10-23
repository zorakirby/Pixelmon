package pixelmon.client.models.animations.fish;

import pixelmon.client.models.animations.Module;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleTailFish extends Module {

	ModelRenderer tail;

	float TailRotationLimit;
	float TailSpeed;
	float TailInitY;
	float TailInitZ;
	float TailOrientation;
	float TurningSpeed;

	public ModuleTailFish(ModelRenderer tail, float TailOrientation,
			float TailRotationLimit, float TailSpeed) {
		this.tail = tail;
		this.TailSpeed = TailSpeed;
		this.TailRotationLimit = TailRotationLimit;
		this.TailOrientation = TailOrientation;
		TailInitY = tail.rotateAngleY;
		TailInitZ = tail.rotateAngleZ;

	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

		tail.rotateAngleY = MathHelper.cos((float) Math
				.toRadians(TailOrientation))
				* MathHelper.cos(f * TailSpeed)
				* (float) Math.PI * f1 * TailRotationLimit + TailInitY;

		tail.rotateAngleX = MathHelper.sin((float) Math
				.toRadians(TailOrientation))
				* MathHelper.cos(f * TailSpeed)
				* (float) Math.PI * f1 * TailRotationLimit + TailInitY;

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

	}
}
