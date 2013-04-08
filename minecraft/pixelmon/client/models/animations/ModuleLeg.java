package pixelmon.client.models.animations;

import java.util.ArrayList;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleLeg extends Module {

	ModelRenderer leg;

	float WalkOffset;
	float LegRotationLimit;
	float LegInitX;
	float legSpeed;
	EnumPhase phaseVariable;
	EnumLeg legVariable;

	public ModuleLeg(ModelRenderer leg, EnumLeg legVariable,
			EnumPhase phaseVariable, float LegRotationLimit, float legSpeed) {
		this.leg = leg;
		this.LegRotationLimit = LegRotationLimit;
		this.legSpeed = legSpeed;
		this.phaseVariable = phaseVariable;
		this.legVariable = legVariable;
		LegInitX = leg.rotateAngleX;

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

		leg.rotateAngleX = MathHelper.cos(f * legSpeed + WalkOffset)
				* LegRotationLimit * f1 + LegInitX;

	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		// TODO Auto-generated method stub

	}
}
