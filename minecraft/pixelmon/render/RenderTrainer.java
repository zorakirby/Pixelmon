package pixelmon.render;

import pixelmon.entities.trainers.EntityTrainer;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelVillager;
import net.minecraft.src.RenderLiving;

public class RenderTrainer extends RenderLiving {
	public RenderTrainer(float par2) {
		super(null, par2);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		mainModel = ((EntityTrainer) entityLiving).getModel();
		if (mainModel == null)
			return;
		super.doRenderLiving(entityLiving, d, d1, d2, f, f1);
	}
}
