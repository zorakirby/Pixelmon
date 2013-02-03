package pixelmon.client.models.animations;

import net.minecraft.client.model.ModelRenderer;

public class SkeletonBird extends SkeletonBase {
	private ModelRenderer LeftWing, RightWing, LeftLeg, RightLeg;
		
	public SkeletonBird(ModelRenderer body, ModuleHead head, ModelRenderer leftWing, ModelRenderer rightWing, ModelRenderer leftLeg, ModelRenderer rightLeg) {
		super(body);
		modules.add(head);
	}

}
