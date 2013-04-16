package pixelmon.client.models.animations.biped;

import net.minecraft.client.model.ModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class SkeletonBipedSitting extends SkeletonBase {
	ModelRenderer LeftArm, RightArm;

	public SkeletonBipedSitting(ModelRenderer body, ModuleHead headModule, ModelRenderer LeftArm, ModelRenderer RightArm, ModelRenderer leftLeg, ModelRenderer rightLeg) {
		super(body);
		modules.add(headModule);
		this.LeftArm = LeftArm;
		this.RightArm = RightArm;
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {
		super.walk(entity, f, f1, f2, f3, f4);
	}

}
