package pixelmon.client.models.animations.bird;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import pixelmon.client.models.animations.Module;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ModuleWing extends Module {

	ModelRenderer wing;

	float WingRotationLimit;
	float WingSpeed;
	float WingInitY;
	float WingInitZ;
	float WingDirection;
	float WingOrientation;
	// EnumWing WingOrientation;
	EnumWing WingVariable;

	public ModuleWing(ModelRenderer wing, EnumWing WingVariable,
			float WingOrientation, float WingRotationLimit, float WingSpeed) {
		this.wing = wing;
		this.WingSpeed = WingSpeed;
		this.WingRotationLimit = WingRotationLimit;
		this.WingOrientation = WingOrientation;
		WingInitY = wing.rotateAngleY;
		WingInitZ = wing.rotateAngleZ;
		this.WingVariable = WingVariable;
		if (WingVariable == EnumWing.Right) {
			WingDirection = 1;
		} else {
			WingDirection = -1;
		}

	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

		wing.rotateAngleY = MathHelper.cos((float) Math
				.toRadians(WingOrientation))
				* WingDirection
				* MathHelper.cos(f2 * WingSpeed)
				* (float) Math.PI
				* WingRotationLimit;
		
		wing.rotateAngleZ = MathHelper.sin((float) Math
				.toRadians(WingOrientation))
				* WingDirection
				* MathHelper.cos(f2 * WingSpeed)
				* (float) Math.PI
				* WingRotationLimit;

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

	}

}