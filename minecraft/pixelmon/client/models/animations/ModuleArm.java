package pixelmon.client.models.animations;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.bird.EnumWing;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleArm extends Module {

	ModelRenderer arm;

	float ArmRotationLimit;
	float ArmSpeed;
	float ArmInitY;
	float ArmInitZ;
	float ArmDirection;
	float ArmOrientation;
	EnumArm ArmVariable;

	public ModuleArm(ModelRenderer arm, EnumArm ArmVariable, float ArmOrientation, float ArmRotationLimit, float ArmSpeed) {
		this.arm = arm;
		this.ArmSpeed = ArmSpeed;
		this.ArmRotationLimit = ArmRotationLimit;
		ArmInitY = arm.rotateAngleY;
		ArmInitZ = arm.rotateAngleZ;
		this.ArmOrientation = ArmOrientation;
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
		
		if (ArmOrientation == 90) {
			arm.rotateAngleY = MathHelper.cos(f * ArmSpeed + (float)Math.PI)
					* ArmRotationLimit
					* f1;
		} else {
			arm.rotateAngleX = MathHelper.cos(f * ArmSpeed + (float)Math.PI)
					* ArmRotationLimit
					* f1 * ArmDirection;
		}

		
		// TODO Auto-generated method stub

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}