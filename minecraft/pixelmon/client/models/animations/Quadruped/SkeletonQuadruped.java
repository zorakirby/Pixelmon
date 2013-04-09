package pixelmon.client.models.animations.Quadruped;

import pixelmon.client.models.animations.Module;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class SkeletonQuadruped extends SkeletonBase {

	public SkeletonQuadruped(ModelRenderer body, Module headModule,
			ModuleLeg frontLeftLeg, ModuleLeg frontRightLeg,
			ModuleLeg backLeftLeg, ModuleLeg backRightLeg) {
		super(body);
		modules.add(headModule);
		modules.add(frontLeftLeg);
		modules.add(frontRightLeg);
		modules.add(backLeftLeg);
		modules.add(backRightLeg);
	}
}
