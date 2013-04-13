package pixelmon.client.models.animations.bird;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.Module;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleWing extends Module {

	ModelRenderer wing;

	float WingRotationLimit;
	float WingSpeed;
	float WingInitY;
	float WingInitZ;
	float WingDirection;
	EnumWing WingOrientation;
	EnumWing WingVariable;

	public ModuleWing(ModelRenderer wing, EnumWing WingVariable, EnumWing WingOrientation, float WingRotationLimit, float WingSpeed) {
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
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {

		if (WingOrientation == EnumWing.Horizontal) {

			wing.rotateAngleY = WingDirection * MathHelper.cos(f2 * WingSpeed) * (float) Math.PI * WingRotationLimit;
		} else
			wing.rotateAngleZ = WingDirection * MathHelper.cos(f2 * WingSpeed) * (float) Math.PI * WingRotationLimit;

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {

	}

}