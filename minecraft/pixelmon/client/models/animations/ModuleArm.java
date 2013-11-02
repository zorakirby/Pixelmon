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
	float ArmInitX;
	float ArmInitY;
	float ArmInitZ;
	float ArmDirection;
	EnumArm ArmVariable;
	EnumRotation rotationAxis;

	public ModuleArm(Object arm, EnumArm ArmVariable,
            EnumRotation rotationAxis, float ArmRotationLimit, float ArmSpeed) {
		this.arm = this.getModulizableFrom(arm);		
		this.ArmSpeed = ArmSpeed;
		this.ArmRotationLimit = ArmRotationLimit;
		ArmInitY = this.arm.getValue(EnumGeomData.zrot);
		ArmInitZ = this.arm.getValue(EnumGeomData.zrot);
		ArmInitX = this.arm.getValue(EnumGeomData.xrot);
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
		
		switch(rotationAxis){
		case x: arm.setValue(MathHelper.cos(f * ArmSpeed + (float) Math.PI)
				* ArmRotationLimit * f1 * ArmDirection + ArmInitX, EnumGeomData.xrot);
			break;
		case y: arm.setValue(MathHelper.cos(f * ArmSpeed + (float) Math.PI)
					* ArmRotationLimit * f1 * ArmDirection + ArmInitY, EnumGeomData.yrot);
			break;
		case z: arm.setValue(MathHelper.cos(f * ArmSpeed + (float) Math.PI)
				* ArmRotationLimit * f1 * ArmDirection + ArmInitZ, EnumGeomData.zrot);
			break;
		}
		
	}


	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}