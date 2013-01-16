package pixelmon.client.models;

import pixelmon.client.models.animations.SkeletonBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public abstract class PixelmonModelBase extends ModelBase {
	protected SkeletonBase skeleton;

	@Override
	public void render(Entity var1, float f, float f1, float f2, float f3, float f4, float f5) {
		doAnimation(f, f1, f2, f3, f4, f5);
	}

	public void doAnimation(float f, float f1, float f2, float f3, float f4, float f5) {
		skeleton.walk(f, f1, f2, f3, f4);
	}

}
