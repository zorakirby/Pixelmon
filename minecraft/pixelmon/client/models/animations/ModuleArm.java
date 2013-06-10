package pixelmon.client.models.animations;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.IModulizable.EnumGeomData;
import pixelmon.client.models.animations.bird.EnumWing;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleArm extends Module {

	IModulizable arm;

	float ArmRotationLimit;
	float ArmSpeed;
	float ArmInitY;
	float ArmInitZ;
	float ArmDirection;
	float ArmAxisRotation; //either 0 or 90
	EnumArm ArmVariable;

	public ModuleArm(Object arm, EnumArm ArmVariable, float ArmAxisRotation, float ArmRotationLimit, float ArmSpeed) {
		this.arm = getModulizableFrom(arm);
		this.ArmSpeed = ArmSpeed;
		this.ArmRotationLimit = ArmRotationLimit;
		ArmInitY = this.arm.getValue(EnumGeomData.yrot);
		ArmInitZ = this.arm.getValue(EnumGeomData.zrot);
		this.ArmAxisRotation = ArmAxisRotation;
		this.ArmVariable = ArmVariable;
		if (ArmVariable == EnumArm.Right) {
			ArmDirection = 1;
		} else {
			ArmDirection = -1;
		}
		
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		
		if (ArmAxisRotation == 90) {
			float angleY = MathHelper.cos(f * ArmSpeed + (float)Math.PI)
					* ArmRotationLimit
					* f1;
			arm.setValue(angleY, EnumGeomData.yrot);
		} else {
			float angleX = MathHelper.cos(f * ArmSpeed + (float)Math.PI)
					* ArmRotationLimit
					* f1 * ArmDirection;
			arm.setValue(angleX, EnumGeomData.xrot);
		}

		
		// TODO Auto-generated method stub

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}