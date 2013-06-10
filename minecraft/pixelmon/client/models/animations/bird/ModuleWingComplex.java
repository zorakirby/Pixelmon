package pixelmon.client.models.animations.bird;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import pixelmon.client.models.animations.IModulizable.EnumGeomData;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ModuleWingComplex extends ModuleWing {

	public ModuleWingComplex(Object wing, EnumWing WingVariable,
			float WingOrientation, float WingRotationLimit) {
		super(wing, WingVariable, WingOrientation, WingRotationLimit, 0);
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

		entity.animationLimit = 90;
		entity.animationIncrement = 6;

		float yRot;
		float zRot;
		if (WingVariable == EnumWing.Left) {

			if (entity.animationCounter < 45) {
				yRot = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* ((float) (1.0091F - 1.009F * Math.exp(-Math
								.toRadians(entity.animationCounter) * 6))
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit);

				zRot = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* ((float) (1.0091F - 1.009F * Math.exp(-Math
								.toRadians(entity.animationCounter) * 6))
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit);

			} else {
				yRot = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* ((float) (0.5F
								- 0.5F
								* Math.cos(Math
										.toRadians(entity.animationCounter) * 4)
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit));

				zRot = MathHelper.sin((float) Math
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
				yRot = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* ((float) (Math.exp(-Math
								.toRadians(entity.animationCounter) * 6))
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit);

				zRot = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* ((float) (Math.exp(-Math
								.toRadians(entity.animationCounter) * 6))
								* (float) Math.PI * 2 * WingRotationLimit - (float) Math.PI
								* WingRotationLimit);

			} else {
				yRot = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* ((float) ((0.5F * Math.cos(Math
								.toRadians(entity.animationCounter) * 4) - 0.5F)
								* (float) Math.PI * 2 * WingRotationLimit + (float) Math.PI
								* WingRotationLimit));

				zRot = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* ((float) (0.5F * Math.cos(Math
								.toRadians(entity.animationCounter) * 4) - 0.5F)
								* (float) Math.PI * 2 * WingRotationLimit + (float) Math.PI
								* WingRotationLimit);
			}
		}
		wing.setValue(yRot, EnumGeomData.yrot);
		wing.setValue(zRot, EnumGeomData.zrot);
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}
