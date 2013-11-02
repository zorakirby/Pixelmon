package pixelmon.client.models.animations;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.IModulizable.EnumGeomData;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleLeg extends Module {

	IModulizable leg;

	float WalkOffset;
	float LegRotationLimit;
	float LegInitX;
	float LegInitY;
	float LegInitZ;
	float legSpeed;
	EnumRotation rotationAxis;
	EnumPhase phaseVariable;
	EnumLeg legVariable;

	public ModuleLeg(Object leg, EnumLeg legVariable,
            EnumPhase phaseVariable, EnumRotation rotationAxis, float LegRotationLimit, float legSpeed) {
		this.leg = this.getModulizableFrom(leg);		
		this.LegRotationLimit = LegRotationLimit;
		this.legSpeed = legSpeed;
		this.phaseVariable = phaseVariable;
		this.legVariable = legVariable;
		this.rotationAxis = rotationAxis;
		LegInitX = this.leg.getValue(EnumGeomData.xrot);
		LegInitY = this.leg.getValue(EnumGeomData.yrot);
		LegInitZ = this.leg.getValue(EnumGeomData.zrot);


		if (phaseVariable == EnumPhase.InPhase) {

			if (legVariable == EnumLeg.FrontLeft) {
				WalkOffset = (float) Math.PI;
			}

			else if (legVariable == EnumLeg.FrontRight) {
				WalkOffset = 0F;
			}

			else if (legVariable == EnumLeg.BackLeft) {
				WalkOffset = (float) Math.PI;
			}

			else {
				WalkOffset = 0F;
			}
		}

		else {
			if (legVariable == EnumLeg.FrontLeft) {
				WalkOffset = (float) Math.PI;
			}

			else if (legVariable == EnumLeg.FrontRight) {
				WalkOffset = 0F;

			} else if (legVariable == EnumLeg.BackLeft) {
				WalkOffset = 0F;
			}

			else {
				WalkOffset = (float) Math.PI;
			}
		}
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		switch(rotationAxis){
		case x:	leg.setValue(MathHelper.cos(f * legSpeed + WalkOffset)
				* LegRotationLimit * f1 + LegInitX, EnumGeomData.xrot);
			break;
		case y:	leg.setValue(MathHelper.cos(f * legSpeed + WalkOffset)
				* LegRotationLimit * f1 + LegInitY, EnumGeomData.yrot);
			break;
		case z:	leg.setValue(MathHelper.cos(f * legSpeed + WalkOffset)
				* LegRotationLimit * f1 + LegInitZ, EnumGeomData.zrot);
			break;
		}
		if (rotationAxis == EnumRotation.x) {

		}
		if (rotationAxis == EnumRotation.y) {
			
		}
		if (rotationAxis == EnumRotation.z) {
			
		}
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}
