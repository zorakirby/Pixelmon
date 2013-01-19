package pixelmon.client.models.animations;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;

public class SkeletonBipedSitting extends SkeletonBiped {

	public SkeletonBipedSitting(ModelRenderer leftArm, ModelRenderer rightArm, ModelRenderer leftLeg, ModelRenderer rightLeg) {
		super(leftArm, rightArm, leftLeg, rightLeg);
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {
		super.walk(entity, f, f1, f2, f3, f4);
	}

}
