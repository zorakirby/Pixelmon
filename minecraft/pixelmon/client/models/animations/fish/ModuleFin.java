package pixelmon.client.models.animations.fish;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.Module;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleFin extends Module {

	ModelRenderer Fin;

	float FinRotationLimit;
	float FinInitY;
	float FinInitZ;
	float FinSpeed;
	float FinDirection;
	float FinOrientation;
	EnumArm FinVariable;

	public ModuleFin(ModelRenderer Fin, EnumArm FinVariable,
			float FinOrientation, float FinRotationLimit, float FinSpeed) {
		this.Fin = Fin;
		this.FinRotationLimit = FinRotationLimit;
		this.FinOrientation = FinOrientation;
		this.FinSpeed = FinSpeed;
		FinInitY = Fin.rotateAngleY;
		FinInitZ = Fin.rotateAngleZ;
		if (FinVariable == EnumArm.Right) {
			FinDirection = 1;
		} else {
			FinDirection = -1;
		}
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

		Fin.rotateAngleY = MathHelper.cos((float) Math
				.toRadians(FinOrientation))
				* FinDirection
				* MathHelper.cos(f * FinSpeed)
				* FinRotationLimit
				* f1
				+ FinInitY;

		Fin.rotateAngleZ = MathHelper.sin((float) Math
				.toRadians(FinOrientation))
				* FinDirection
				* MathHelper.cos(f * FinSpeed)
				* FinRotationLimit
				* f1
				+ FinInitZ;

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}
