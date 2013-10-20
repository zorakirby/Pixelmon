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
	float ArmInitX;
	float ArmInitY;
	float ArmInitZ;
	float ArmDirection;
	EnumArm ArmVariable;
	EnumRotation rotationAxis;

	public ModuleArm(ModelRenderer arm, EnumArm ArmVariable,
			EnumRotation rotationAxis, float ArmRotationLimit, float ArmSpeed) {
		this.arm = arm;
		this.ArmSpeed = ArmSpeed;
		this.ArmRotationLimit = ArmRotationLimit;
		ArmInitY = arm.rotateAngleY;
		ArmInitZ = arm.rotateAngleZ;
		ArmInitX = arm.rotateAngleX;
		this.ArmVariable = ArmVariable;
		this.rotationAxis = rotationAxis;
		if (ArmVariable == EnumArm.Right) {
			ArmDirection = 1;
		} else {
			ArmDirection = -1;
		}

	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {

		if (rotationAxis == EnumRotation.x) {
			arm.rotateAngleX = MathHelper.cos(f * ArmSpeed + (float) Math.PI)
					* ArmRotationLimit * f1 * ArmDirection + ArmInitX;
		}

		if (rotationAxis == EnumRotation.y) {
			arm.rotateAngleY = MathHelper.cos(f * ArmSpeed + (float) Math.PI)
					* ArmRotationLimit * f1 * ArmDirection + ArmInitY;
		}

		if (rotationAxis == EnumRotation.z) {
			arm.rotateAngleZ = MathHelper.cos(f * ArmSpeed + (float) Math.PI)
					* ArmRotationLimit * f1 * ArmDirection + ArmInitZ;
		}
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}