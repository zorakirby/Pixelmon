package pixelmon.client.render;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.client.models.ModelPokeball;
import pixelmon.entities.pokeballs.EntityPokeBall;

public class RenderPokeball extends Render {
	ModelPokeball model;

	public RenderPokeball() {
		model = new ModelPokeball();
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1) {
		doRender((EntityPokeBall) entity, x, y, z, f, f1);
	}

	private void doRender(EntityPokeBall pokeball, double x, double y, double z, float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glRotatef(180, 1, 0, 1);
		if (pokeball.getIsCaptured()) {
			bindTexture(new ResourceLocation("pixelmon:textures/pokeballs/" + pokeball.getType().getCaptureTexture()));
		} else if (pokeball.flashRed) {
			bindTexture(new ResourceLocation("pixelmon:textures/pokeballs/" + pokeball.getType().getFlashRedTexture()));
		} else {
			bindTexture(new ResourceLocation("pixelmon:textures/pokeballs/" + pokeball.getType().getTexture()));
		}
		RenderHelper.enableStandardItemLighting();
		float factor = (float) (1.0 / 16.0);
		GL11.glPushMatrix();
		model.setRotationAngles(pokeball.rotationPitch, pokeball.rotationYaw);
		model.render(pokeball, factor);
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}