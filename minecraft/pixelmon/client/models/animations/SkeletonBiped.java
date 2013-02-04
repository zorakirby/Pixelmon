package pixelmon.client.models.animations;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;

public class SkeletonBiped extends SkeletonBase {

	protected ModelRenderer leftArm, rightArm;
	protected ModelRenderer leftLeg, rightLeg;
	
	public SkeletonBiped(ModelRenderer body, ModuleHead headModule, ModelRenderer leftArm, ModelRenderer rightArm, ModelRenderer leftLeg, ModelRenderer rightLeg) {
		super(body);
		modules.add(headModule);
		this.leftArm = leftArm; this.rightArm = rightArm;
		this.leftLeg = leftLeg; this.rightLeg = rightLeg;
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {
		super.walk(entity, f, f1, f2, f3, f4);
	}
}
