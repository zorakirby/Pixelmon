package pixelmon.client.models.animations.bird;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.Module;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleWingComplex extends ModuleWing {

	public ModuleWingComplex(ModelRenderer wing, EnumWing WingVariable,
			float WingOrientation, float WingRotationLimit) {
		super(wing, WingVariable, WingOrientation, WingRotationLimit, 0);
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

		entity.animationLimit = 90;
		entity.animationIncrement = 6;

		if (WingVariable == EnumWing.Left) {

			if (entity.animationCounter < 45) {
				wing.rotateAngleY = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* ((float) (1.0091F - 1.009F * Math.exp(-Math
								.toRadians(entity.animationCounter) * 6))
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit);

				wing.rotateAngleZ = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* ((float) (1.0091F - 1.009F * Math.exp(-Math
								.toRadians(entity.animationCounter) * 6))
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit);

			} else {
				wing.rotateAngleY = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* ((float) (0.5F
								- 0.5F
								* Math.cos(Math
										.toRadians(entity.animationCounter) * 4)
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit));

				wing.rotateAngleZ = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* ((float) (0.5F
								- 0.5F
								* Math.cos(Math
										.toRadians(entity.animationCounter) * 4)
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit));

			}
		} else {
			if (entity.animationCounter < 45) {
				wing.rotateAngleY = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* ((float) (Math.exp(-Math
								.toRadians(entity.animationCounter) * 6))
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit);

				wing.rotateAngleZ = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* ((float) (Math.exp(-Math
								.toRadians(entity.animationCounter) * 6))
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit);

			} else {
				wing.rotateAngleY = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* ((float) ((0.5F * Math.cos(Math
								.toRadians(entity.animationCounter) * 4) - 0.5F)
								* (float) Math.PI * 2 * WingRotationLimit + (float) Math.PI
								* WingRotationLimit));

				wing.rotateAngleZ = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* ((float) (0.5F * Math.cos(Math
								.toRadians(entity.animationCounter) * 4) - 0.5F)
								* (float) Math.PI * 2 * WingRotationLimit + (float) Math.PI
								* WingRotationLimit);
			}
		}
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}
