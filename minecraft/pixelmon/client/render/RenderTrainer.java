package pixelmon.client.render;

import java.lang.Math;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.npcs.EntityTrainer;

public class RenderTrainer extends RenderLiving {

	private int defaultNameRenderDistance = 8;
	private int configNameRenderMultiplier = (int) Math.max(1, Math.min(PixelmonConfig.nameplateRangeModifier, 3)); //keeps in bounds [1, 3], forces to int type
	private int nameRenderDistanceNormal = defaultNameRenderDistance * configNameRenderMultiplier;

	public RenderTrainer(float par2) {
		super(null, par2);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		mainModel = ((EntityTrainer) entityLiving).getModel();
		if (mainModel == null)
			return;
		float var10 = entityLiving.getDistanceToEntity(this.renderManager.livingPlayer);
		if (var10 <= (float) nameRenderDistanceNormal) {
			drawNameTag(entityLiving, d, d1, d2);
		}
		super.doRenderLiving(entityLiving, d, d1, d2, f, f1);
	}

	public void drawNameTag(EntityLiving entityliving, double par2, double par4, double par6) {
		if (Minecraft.isGuiEnabled() && (entityliving instanceof EntityTrainer)) {
			EntityTrainer trainer = (EntityTrainer) entityliving;
			String s = trainer.getNickName() + " Lv: " + trainer.getLvl();
			renderLivingLabel(trainer, s, par2, par4, par6, 64);
		}
	}
}
