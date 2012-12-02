package pixelmon.render;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPotion;
import net.minecraft.src.Item;

import net.minecraft.src.PotionHelper;
import net.minecraft.src.Render;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.RenderSnowball;
import net.minecraft.src.Tessellator;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.models.ModelPokeball;

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
			ForgeHooksClient.bindTexture("/pixelmon/texture/pokeballs/" + pokeball.getType().getCaptureTexture(), 0);
		} else if (pokeball.flashRed) {
			ForgeHooksClient.bindTexture("/pixelmon/texture/pokeballs/" + pokeball.getType().getFlashRedTexture(), 0);
		} else {
			ForgeHooksClient.bindTexture("/pixelmon/texture/pokeballs/" + pokeball.getType().getTexture(), 0);
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
}