package pixelmon.client.render.tileEntities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.blocks.TileEntityHealer;
import pixelmon.client.models.ModelHealer;
import pixelmon.client.models.ModelPokeball;
import pixelmon.client.render.RenderResources;

public class RenderTileEntityHealer extends TileEntitySpecialRenderer {
	private ModelHealer model;
	private ModelPokeball pokeball;

	public RenderTileEntityHealer() {
		model = new ModelHealer();
		pokeball = new ModelPokeball();
	}

	public void renderAModelAt(TileEntityHealer tile, double d, double d1, double d2, float f) {
		int i = tile.getBlockMetadata(); // this is for rotation
		int j = 0;

		if (i == 0) {
			j = 0;
		}

		if (i == 1) {
			j = 90;
		}

		if (i == 2) {
			j = 180;
		}

		if (i == 3) {
			j = 270;
		}

		func_110628_a(RenderResources.healer); // texture
		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
		GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
										// and you can see what happens
		model.renderModel(tile, 0.0625F); // renders and yes 0.0625 is a random
											// number

		if (!tile.stayDark)
			tile.rotation += 4;
		else
			tile.rotation = 0;
		if (tile.beingUsed) {
			for (int k = 0; k < tile.pokeballType.length; k++) {
				if (tile.pokeballType[k] != null)
					renderPokeball(k, tile);
			}
		}
		GL11.glPopMatrix(); // end

	}

	private void renderPokeball(int k, TileEntityHealer tile) {
		GL11.glPushMatrix();
		switch (k) {
		case 0:
			GL11.glTranslated(-0.12F, (float) 0.78F, 0.24f);
			break;
		case 1:
			GL11.glTranslated(0.12F, (float) 0.78F, 0.24f);
			break;
		case 2:
			GL11.glTranslated(-0.12F, (float) 0.78F, 0f);
			break;
		case 3:
			GL11.glTranslated(0.12F, (float) 0.78F, 0f);
			break;
		case 4:
			GL11.glTranslated(-0.12F, (float) 0.78F, -0.24f);
			break;
		case 5:
			GL11.glTranslated(0.12F, (float) 0.78F, -0.24f);
			break;
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glRotatef(tile.rotation, 0, 1, 0);
	
		if (tile.allPlaced) {
			if (tile.flashTimer < 10 || tile.stayDark)
				func_110628_a(new ResourceLocation("/mods/pixelmon/textures/pokeballs/" + tile.pokeballType[k].getCaptureTexture()));
			else
				func_110628_a(new ResourceLocation("/mods/pixelmon/textures/pokeballs/" + tile.pokeballType[k].getTexture()));
			if (tile.flashTimer >= 20)
				tile.flashTimer = 0;
		} else {
			func_110628_a(new ResourceLocation("/mods/pixelmon/textures/pokeballs/" + tile.pokeballType[k].getTexture()));
		}
		RenderHelper.enableStandardItemLighting();
		GL11.glScalef(0.8F, 0.8F, 0.8F);
		float factor = (float) (1.0 / 16.0);
		GL11.glPushMatrix();
		pokeball.render(null, factor);
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
		renderAModelAt((TileEntityHealer) tileentity, d, d1, d2, f); // where to
																		// render
	}
}
