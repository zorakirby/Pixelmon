package pixelmon.client.models.animations.bird;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ModuleWingSegmented extends ModuleWing {

	public ModelRenderer[] wingParts;
	public int[] lengths;
	float phaseOffset;
	float zeroPoint;
	float initialRotation;

	public ModuleWingSegmented(ModelRenderer wing, EnumWing WingVariable,
			float WingOrientation, float zeroPoint, float initialRotation,
			float WingRotationLimit, float WingSpeed, float phaseOffset,
			ModelRenderer... wingArgs) {
		super(wing, WingVariable, WingOrientation, WingRotationLimit, WingSpeed);

		this.wingParts = wingArgs;
		this.phaseOffset = phaseOffset;
		this.zeroPoint = zeroPoint;
		this.initialRotation = initialRotation;
		lengths = new int[wingParts.length];
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		for (int i = 0; i < wingParts.length; i++) {
			if (i == 0) {
				wingParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* WingDirection
						* ((float) Math.pow(MathHelper.cos(f2 * WingSpeed - i
								/ phaseOffset), 2)
								* (float) Math.PI - zeroPoint)
						* WingRotationLimit
						/ wingParts.length
						+ WingDirection
						* (float) (MathHelper.cos((float) Math
								.toRadians(WingOrientation)) * Math
								.toRadians(initialRotation));

				wingParts[i].rotateAngleZ = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* WingDirection
						* ((float) Math.pow(MathHelper.cos(f2 * WingSpeed - i
								/ phaseOffset), 2)
								* (float) Math.PI - zeroPoint)
						* WingRotationLimit
						/ wingParts.length
						+ WingDirection
						* (float) (MathHelper.sin((float) Math
								.toRadians(WingOrientation)) * Math
								.toRadians(initialRotation));
			} else {
				wingParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* WingDirection
						* ((float) Math.pow(MathHelper.cos(f2 * WingSpeed - i
								/ phaseOffset), 2)
								* (float) Math.PI - zeroPoint)
						* WingRotationLimit / (wingParts.length*i);

				wingParts[i].rotateAngleZ = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* WingDirection
						* ((float) Math.pow(MathHelper.cos(f2 * WingSpeed - i
								/ phaseOffset), 2)
								* (float) Math.PI - zeroPoint)
						* WingRotationLimit / (wingParts.length*i);
			}
		}
	}


	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		for (int i = 0; i < wingParts.length; i++) {
			if (i == 0) {
				wingParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* WingDirection
						* ((float) Math.pow(MathHelper.cos(f2 * WingSpeed - i
								/ phaseOffset), 2)
								* (float) Math.PI - zeroPoint)
						* WingRotationLimit
						/ wingParts.length
						+ WingDirection
						* (float) (MathHelper.cos((float) Math
								.toRadians(WingOrientation)) * Math
								.toRadians(initialRotation));

				wingParts[i].rotateAngleZ = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* WingDirection
						* ((float) Math.pow(MathHelper.cos(f2 * WingSpeed - i
								/ phaseOffset), 2)
								* (float) Math.PI - zeroPoint)
						* WingRotationLimit
						/ wingParts.length
						+ WingDirection
						* (float) (MathHelper.sin((float) Math
								.toRadians(WingOrientation)) * Math
								.toRadians(initialRotation));
			} else {
				wingParts[i].rotateAngleY = MathHelper.cos((float) Math
						.toRadians(WingOrientation))
						* WingDirection
						* ((float) Math.pow(MathHelper.cos(f2 * WingSpeed - i
								/ phaseOffset), 2)
								* (float) Math.PI - zeroPoint)
						* WingRotationLimit / (wingParts.length*i);

				wingParts[i].rotateAngleZ = MathHelper.sin((float) Math
						.toRadians(WingOrientation))
						* WingDirection
						* ((float) Math.pow(MathHelper.cos(f2 * WingSpeed - i
								/ phaseOffset), 2)
								* (float) Math.PI - zeroPoint)
						* WingRotationLimit / (wingParts.length*i);
			}
		}
	}
}
