package pixelmon.client.models.animations.fish;

import pixelmon.client.models.animations.Module;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class SkeletonFish extends SkeletonBase {

	public SkeletonFish(ModelRenderer body, Module headModule,
			ModuleFin LeftFrontFin, ModuleFin RightFrontFin, ModuleFin LeftBackFin, ModuleFin RightBackFin, ModuleTailFish tail) {
		super(body);
		if (headModule != null)
			modules.add(headModule);
		if (LeftFrontFin != null)
			modules.add(LeftFrontFin);
		if (RightFrontFin != null)
			modules.add(RightFrontFin);
		if (LeftBackFin != null)
			modules.add(LeftBackFin);
		if (RightBackFin != null)
			modules.add(RightBackFin);
		if (tail != null)
			modules.add(tail);
	}
}
