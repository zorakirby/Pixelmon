package pixelmon.client.models.animations.Biped;

import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class SkeletonBiped extends SkeletonBase {

	public SkeletonBiped(ModelRenderer body, ModuleHead headModule, ModuleArm leftArm, ModuleArm rightArm, ModuleLeg leftLeg, ModuleLeg rightLeg) {
		super(body);
		if (headModule != null)
			modules.add(headModule);
		if (leftLeg != null)
			modules.add(leftLeg);
		if (rightLeg != null)
			modules.add(rightLeg);
		if (rightArm != null)
			modules.add(rightArm);
		if (leftArm != null)
			modules.add(leftArm);
	}
}
