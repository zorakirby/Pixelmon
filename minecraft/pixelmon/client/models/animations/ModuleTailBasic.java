package pixelmon.client.models.animations;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleTailBasic extends Module {
	
	ModelRenderer tail;

	float TailRotationLimitY;
	float TailRotationLimitX;
	float TailSpeed;
	float TailInitY;
	float TailInitX;
	float TailOrientation;
	float TurningSpeed;
	float TurningAngle;
	
	public ModuleTailBasic(ModelRenderer tail, float TailRotationLimitY, float TailRotationLimitX, float TailSpeed) {
		this.tail = tail;
		this.TailSpeed = TailSpeed;
		this.TailRotationLimitY = TailRotationLimitY;
		this.TailRotationLimitX = TailRotationLimitX;
		TailInitY = tail.rotateAngleY;
		TailInitX = tail.rotateAngleX;


	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {
		TurningSpeed = Math.abs(entity.rotationYaw - entity.prevRotationYaw);
		
	
		tail.rotateAngleY =  MathHelper.cos(f * TailSpeed)
				* (float) Math.PI
				* f1
				* TailRotationLimitY + TurningAngle;
		
		tail.rotateAngleX = MathHelper.cos(f * TailSpeed * 2)
				* (float) Math.PI
				* f1
				* TailRotationLimitX;
		
		
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {

	}
}
