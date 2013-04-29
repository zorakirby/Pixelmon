package pixelmon.client.models.animations.quadruped;

import pixelmon.client.models.animations.Module;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class SkeletonQuadruped extends SkeletonBase {

	public SkeletonQuadruped(ModelRenderer body, Module headModule, ModuleLeg frontLeftLeg, ModuleLeg frontRightLeg, ModuleLeg backLeftLeg,
			ModuleLeg backRightLeg, ModuleTailBasic tail) {
		super(body);
		if (headModule != null)
			modules.add(headModule);
		if (frontLeftLeg != null)
			modules.add(frontLeftLeg);
		if (frontRightLeg != null)
			modules.add(frontRightLeg);
		if (backLeftLeg != null)
			modules.add(backLeftLeg);
		if (backRightLeg != null)
			modules.add(backRightLeg);
		if (tail != null)
			modules.add(tail);
	}
}
