package pixelmon.client.models.animations.Bird;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.Module;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleWingComplex extends Module {

	ModelRenderer wing;

	float WingRotationLimit;
	float WingSpeed;
	float WingInitY;
	float WingInitZ;
	float WingDirection;
	EnumWing WingVariable;

	public ModuleWingComplex(ModelRenderer wing, EnumWing WingVariable,
			float WingRotationLimit, float WingSpeed) {
		this.wing = wing;
		this.WingSpeed = WingSpeed;
		this.WingRotationLimit = WingRotationLimit;
		WingInitY = wing.rotateAngleY;
		WingInitZ = wing.rotateAngleZ;

		if (WingVariable == EnumWing.Right) {
			WingDirection = 1;
		} else {
			WingDirection = -1;
		}

	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		if (((EntityPixelmon)entity).animationCounter2 * 3 - 180 < 0) {
		}
		
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub
		
	}
}
