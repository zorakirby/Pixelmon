package pixelmon.client.models.animations.bird;

import net.minecraft.client.model.ModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.SkeletonBase;

public class SkeletonBird extends SkeletonBase {
	private ModelRenderer LeftWing, RightWing, LeftLeg, RightLeg;

	public SkeletonBird(ModelRenderer body, ModuleHead head,
			ModuleWing leftWing, ModuleWing rightWing, ModelRenderer leftLeg,
			ModelRenderer rightLeg) {
		super(body);
		if (head != null) {
			modules.add(head);
		}
		if (leftWing != null) {
			modules.add(leftWing);
		}
		if (rightWing != null) {
		modules.add(rightWing);
		}
	}

}
