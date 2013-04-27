package pixelmon.client.models.animations;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
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
		this.ArmRotationLimit = ArmRotationLimit;
		this.ArmOrientation = ArmOrientation;
		ArmInitY = arm.rotateAngleY;
		ArmInitZ = arm.rotateAngleZ;
		this.ArmVariable = ArmVariable;
		if (ArmVariable == EnumArm.Right) {
			ArmDirection = 1;
		} else {
			ArmDirection =-1;
		}
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		
		arm.rotateAngleY = MathHelper.cos(f * ArmSpeed)
				* f1
				* ArmRotationLimit;
		
		// TODO Auto-generated method stub

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}