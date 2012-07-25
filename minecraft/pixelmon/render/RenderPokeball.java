package pixelmon.render;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPotion;
import net.minecraft.src.Item;
import net.minecraft.src.PotionHelper;
import net.minecraft.src.Render;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.RenderSnowball;
import net.minecraft.src.Tessellator;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.MinecraftForgeClient;

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
		MinecraftForgeClient.bindTexture("pixelmon/texture/pokeballs/pokeball.png");
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
		float factor = (float) (1);
		GL11.glPushMatrix();
		model.WhteTip.render(factor);
		model.WhiteTop.render(factor);
		model.WhiteBottom.render(factor);
		model.WhiteFront.render(factor);
		model.WhiteRight.render(factor);
		model.WhiteLeft.render(factor);
		model.WhiteBack.render(factor);
		model.Hinge.render(factor);
		GL11.glPopMatrix();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
}