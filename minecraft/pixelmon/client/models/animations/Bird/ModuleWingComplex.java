package pixelmon.client.models.animations.Bird;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.Module;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleWingComplex extends ModuleWing {

	ModelRenderer wing;

	public ModuleWingComplex(ModelRenderer wing, EnumWing WingVariable,
			float WingRotationLimit, float WingSpeed) {
		super(wing, WingVariable, null, WingRotationLimit, WingSpeed);
		this.wing = wing;
		this.WingSpeed = WingSpeed;
		this.WingRotationLimit = WingRotationLimit;
		WingInitY = wing.rotateAngleY;
		WingInitZ = wing.rotateAngleZ;

	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

		entity.animationLimit = 360;

		if (WingVariable == EnumWing.Right) {
			
			System.out.println(entity.animationCounter);

			if (entity.animationCounter < 180) {
				System.out.println(entity.animationCounter);
				wing.rotateAngleZ = (float) (1.0091F - 1.009F * Math.exp(-Math
						.toRadians(entity.animationCounter) * 6))
						* (float) Math.PI
						* 2
						* WingRotationLimit
						- WingRotationLimit;

			} else {
				wing.rotateAngleZ = (float) (0.5F - 0.5F
						* Math.cos(Math.toRadians(entity.animationCounter) * 4)
						* (float) Math.PI * 2 * WingRotationLimit - WingRotationLimit);

			}
		} else {
			if (WingVariable == EnumWing.Right) {
				if (entity.animationCounter < 180) {
					wing.rotateAngleZ = (float) (Math.exp(-Math
							.toRadians(entity.animationCounter) * 6))
							* (float) Math.PI
							* 2
							* WingRotationLimit
							- WingRotationLimit;

				} else {
					wing.rotateAngleZ = (float) ((0.5F * Math.cos(Math
							.toRadians(entity.animationCounter) * 4) - 0.5F)
							* (float) Math.PI * 2 * WingRotationLimit + WingRotationLimit);
				}
			}
		}
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}
