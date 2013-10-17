package pixelmon.client.models.animations.bird;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import pixelmon.client.models.animations.IModulizable;
import pixelmon.client.models.animations.IModulizable.EnumGeomData;
import pixelmon.client.models.animations.Module;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ModuleWing extends Module {

	IModulizable wing;

	float WingRotationLimit;
	float WingSpeed;
	float WingInitY;
	float WingInitZ;
	float WingDirection;
	float WingOrientation;
	// EnumWing WingOrientation;
	EnumWing WingVariable;

	public ModuleWing(Object wing, EnumWing WingVariable,
			float WingOrientation, float WingRotationLimit, float WingSpeed) {
		this.wing = getModulizableFrom(wing);
		this.WingSpeed = WingSpeed;
		this.WingRotationLimit = WingRotationLimit;
		this.WingOrientation = WingOrientation;
		WingInitY = this.wing.getValue(EnumGeomData.yrot);
		WingInitZ = this.wing.getValue(EnumGeomData.zrot);
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

		wing.setValue(MathHelper.cos((float) Math
				.toRadians(WingOrientation))
				* WingDirection
				* MathHelper.cos(f2 * WingSpeed)
				* (float) Math.PI
				* WingRotationLimit, EnumGeomData.yrot);
		
		wing.setValue(MathHelper.sin((float) Math
				.toRadians(WingOrientation))
				* WingDirection
				* MathHelper.cos(f2 * WingSpeed)
				* (float) Math.PI
				* WingRotationLimit, EnumGeomData.zrot);

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		float angY = MathHelper.cos((float) Math
				.toRadians(WingOrientation))
				* WingDirection
				* MathHelper.cos(f2 * WingSpeed)
				* (float) Math.PI
				* WingRotationLimit;
		
		float angZ = MathHelper.sin((float) Math
				.toRadians(WingOrientation))
				* WingDirection
				* MathHelper.cos(f2 * WingSpeed)
				* (float) Math.PI
				* WingRotationLimit;

		wing.setValue(angY, EnumGeomData.yrot);
		wing.setValue(angZ, EnumGeomData.zrot);
	}

}