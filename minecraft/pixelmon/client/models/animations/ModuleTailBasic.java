package pixelmon.client.models.animations;

import pixelmon.client.models.animations.IModulizable.EnumGeomData;
import pixelmon.client.models.animations.bird.EnumWing;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModuleTailBasic extends Module {
	
	IModulizable tail;

	float TailRotationLimitY;
	float TailRotationLimitX;
	float TailSpeed;
	float TailInitY;
	float TailInitX;
	float TailOrientation;
	float TurningSpeed;
	float TurningAngle;
	
	public ModuleTailBasic(Object tail, float TailRotationLimitY, float TailRotationLimitX, float TailSpeed) {
		this.tail = getModulizableFrom(tail);		this.TailSpeed = TailSpeed;
		this.TailRotationLimitY = TailRotationLimitY;
		this.TailRotationLimitX = TailRotationLimitX;
		TailInitY = this.tail.getValue(EnumGeomData.yrot);
		TailInitX = this.tail.getValue(EnumGeomData.xrot);

	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {
		TurningSpeed = Math.abs(entity.rotationYaw - entity.prevRotationYaw);
		
	
		tail.setValue(MathHelper.cos(f * TailSpeed)
				* (float) Math.PI
				* f1
				* TailRotationLimitY + TurningAngle, EnumGeomData.yrot);
		
		tail.setValue(MathHelper.cos(f * TailSpeed * 2)	* (float) Math.PI * f1 * TailRotationLimitX, EnumGeomData.xrot);
		
		
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {

	}
}
