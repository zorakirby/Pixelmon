package pixelmon.client.render;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.TileEntityFossilMachine;
import pixelmon.client.models.ModelFossilMachine;
import pixelmon.client.models.ModelPokeball;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonItemsFossils;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;

public class RenderTileFossilMachine extends TileEntitySpecialRenderer {
	private ModelFossilMachine model;
	private ModelPokeball pokeball;

	private EntityPixelmon pokemon;

	public RenderTileFossilMachine() {
		model = new ModelFossilMachine();
		pokeball = new ModelPokeball();
	}

	public void renderAModelAt(TileEntityFossilMachine tile, double d, double d1, double d2, float f) {
		pokemon = new EntityPixelmon(tile.worldObj);
		// this is for rotation
		int j = 0;
		int i = tile.getBlockMetadata();

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

		bindTextureByName("/pixelmon/texture/blocks/fossilmachine.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.6F, (float) d1 + 2.3F, (float) d2 + 0.5F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.5F, -1.5F, -1.5F);
		model.renderModel(tile, 0.0625F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		GL11.glTranslatef((float) d + 0.6F, (float) d1 + 2.3F, (float) d2 + 0.5F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.5F, -1.5F, -1.5F);
		model.renderGlass(tile, 0.0625F);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		renderCompletionLevel(tile, tile.completionRate + "%", d, d1, d2, 64, false, -1);
		if (tile.staticFlicker && tile.completionRate == 100 && tile.currentPokeball == -1)
			renderCompletionLevel(tile, "Insert Pokeball", d, d1 + 0.08f, d2, 64, true, -65536);
		else if (tile.completionRate > 0 && tile.completionRate < 100)
			renderCompletionLevel(tile, "Working" + tile.dots, d, d1 + 0.08f, d2, 64, true, -16711936);
		else if (tile.staticFlicker && tile.completionRate == 0 && tile.currentFossil == -1)
			renderCompletionLevel(tile, "Insert Fossil", d, d1 + 0.08f, d2, 64, true, -65536);
		else if (tile.staticFlicker && tile.completionRate == 100 && tile.currentPokeball != -1)
			renderCompletionLevel(tile, "Retrieve Pokemon", d, d1 + 0.08f, d2, 64, true, -16711936);
		renderBarLevel(tile, d, d1, d2, 64);

		bindTextureByName("/pixelmon/texture/fossils/" + fossilTexture(tile) + ".png");

		GL11.glPushMatrix();
		if (tile.currentFossil != -1) {
			if (i == 0)
				GL11.glTranslatef((float) d + 0.55F, (float) d1 + 1.3F + tile.y, (float) d2 + 0.55F);
			if (i == 1)
				GL11.glTranslatef((float) d + 0.65F, (float) d1 + 1.3F + tile.y, (float) d2 + 0.55F);
			if (i == 2)
				GL11.glTranslatef((float) d + 0.55F, (float) d1 + 1.3F + tile.y, (float) d2 + 0.55F);
			if (i == 3)
				GL11.glTranslatef((float) d + 0.55F, (float) d1 + 1.3F + tile.y, (float) d2 + 0.55F);
			GL11.glScalef(0.8F - tile.fossilProgress / 1000 / 2, -0.8F + tile.fossilProgress / 1000 / 2, -0.8F + tile.fossilProgress / 1000 / 2);
			if (i == 0 || i == 2)
				GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
			else if (i == 1 || i == 3)
				GL11.glRotatef(j + 180F, 0.0F, 1.0F, 0.0F);
			renderModel(tile, 0.0625F);
		}
		GL11.glPopMatrix();

		// Pokeball rendering

		if (tile.currentPokeball != -1) {
			bindTextureByName("/pixelmon/texture/pokeballs/" + tile.currentPokeballTexture);

			GL11.glPushMatrix();
			if (i == 0) {
				GL11.glTranslatef((float) d + 0.79F, (float) d1 + 0.4F, (float) d2 + 1.35F);
			}
			if (i == 1) {
				GL11.glTranslatef((float) d + 1.45F, (float) d1 + 0.4F, (float) d2 + 0.31F);
			}
			if (i == 2) {
				GL11.glTranslatef((float) d + 0.41F, (float) d1 + 0.4F, (float) d2 - 0.34F);
			}
			if (i == 3) {
				GL11.glTranslatef((float) d - 0.24F, (float) d1 + 0.4F, (float) d2 + 0.69F);
			}
			GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1F, -1F, -1F);
			pokeball.renderModel(tile, 0.0625F);
			GL11.glPopMatrix();
		}

		if (EnumPokemon.hasPokemon(tile.currentPokemon) && !EnumTrainers.has(tile.currentPokemon))
			pokemon = ((EntityPixelmon) PixelmonEntityList.createEntityByName(tile.currentPokemon, tile.getWorldObj()));

		// Pokemon Rendering

		GL11.glPushMatrix();
		if (pokemon != null && EnumPokemon.hasPokemon(tile.currentPokemon) && !EnumTrainers.has(tile.currentPokemon)) {
			GL11.glTranslatef((float) d + 0.65F, (float) d1 + 1.3F, (float) d2 + 0.5F);
			pokemon.setWorld(tile.getWorldObj());
			float var10 = 0.6375F;
			GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(0.0f + tile.pokemonProgress / 1000 / 4, 0.0f + tile.pokemonProgress / 1000 / 4, 0.0f + tile.pokemonProgress / 1000 / 4);
			if (tile.isShiny)
				pokemon.setIsShiny(true);
			pokemon.setLocationAndAngles(d, d1, d2, 0.0F, 0.0F);
			RenderManager.instance.renderEntityWithPosYaw(pokemon, 0.0D, 0.0D, 0.0D, 0.0F, f);
		}
		GL11.glPopMatrix();
	}

	protected void renderBarLevel(TileEntityFossilMachine tile, double par3, double par5, double par7, int par9) {
		int width = 40;
		int height = 10;
		int j = 0;
		int i = tile.getBlockMetadata();

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

		float f2 = 1.6F;
		float f3 = 0.00666667F * f2;
		GL11.glPushMatrix();
		float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
		if (i == 0)
			GL11.glTranslatef((float) par3 + 0.60F, (float) par5 + 0.63F, (float) par7 + 1.41F);
		if (i == 1)
			GL11.glTranslatef((float) par3 + 1.51F, (float) par5 + 0.63F, (float) par7 + 0.50F);
		if (i == 2)
			GL11.glTranslatef((float) par3 + 0.60F, (float) par5 + 0.63F, (float) par7 - 0.41F);
		if (i == 3)
			GL11.glTranslatef((float) par3 - 0.31F, (float) par5 + 0.63F, (float) par7 + 0.51F);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(j + 180, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(-f3, -f3, f3);
		GL11.glDisable(2896 /* GL_LIGHTING */);
		GL11.glDepthMask(false);
		GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glBlendFunc(770, 771);
		Tessellator tessellator = Tessellator.instance;
		byte byte0 = -20;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();
		float f8 = (float) (tile.completionRate / 2);
		tessellator.setColorRGBA_F(0.0039F, 0.03137F, 0.4196F, 1.0F);
		tessellator.addVertex(-25F + f8, -7 + byte0, 0.0D);
		tessellator.addVertex(-25F + f8, -6 + byte0, 0.0D);
		tessellator.addVertex(25D, -6 + byte0, 0.0D);
		tessellator.addVertex(25D, -7 + byte0, 0.0D);
		tessellator.setColorRGBA_F(0.0F, 0.8901F, 0.8901F, 1.0F);
		tessellator.addVertex(-25D, -7 + byte0, 0.0D);
		tessellator.addVertex(-25D, -6 + byte0, 0.0D);
		tessellator.addVertex(f8 - 25F, -6 + byte0, 0.0D);
		tessellator.addVertex(f8 - 25F, -7 + byte0, 0.0D);
		tessellator.draw();
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
		GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask(true);
		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	protected void renderCompletionLevel(TileEntity tile, String par2Str, double par3, double par5, double par7, int par9, boolean par10, int par11) {

		int j = 0;
		int i = tile.getBlockMetadata();

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

		FontRenderer var12 = this.getFontRenderer();
		float var13 = 1.6F;
		float var14 = 0.0116666668F * var13;
		GL11.glPushMatrix();
		if (i == 0)
			GL11.glTranslatef((float) par3 + 0.60F, (float) par5 + 1.1F, (float) par7 + 1.41F);
		if (i == 1)
			GL11.glTranslatef((float) par3 + 1.51F, (float) par5 + 1.1F, (float) par7 + 0.50F);
		if (i == 2)
			GL11.glTranslatef((float) par3 + 0.60F, (float) par5 + 1.1F, (float) par7 - 0.41F);
		if (i == 3)
			GL11.glTranslatef((float) par3 - 0.31F, (float) par5 + 1.1F, (float) par7 + 0.51F);
		GL11.glRotatef(j + 180, 0.0F, 1.0F, 0.0F);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		if (!par10) {
			GL11.glScalef(-var14, -var14, var14);
		} else {
			GL11.glScalef(-var14 + 0.012f, -var14 + 0.012f, var14 + 0.012f);
		}
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		Tessellator var15 = Tessellator.instance;
		byte var16 = 0;

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		int var17 = var12.getStringWidth(par2Str) / 2;
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, par11);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		var15.startDrawingQuads();
		var15.addVertex((double) (-var17 - 1), (double) (-1 + var16), 0.0D);
		var15.addVertex((double) (-var17 - 1), (double) (8 + var16), 0.0D);
		var15.addVertex((double) (var17 + 1), (double) (8 + var16), 0.0D);
		var15.addVertex((double) (var17 + 1), (double) (-1 + var16), 0.0D);
		var15.draw();
		var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, par11);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	public String fossilTexture(TileEntityFossilMachine tile) {
		if (tile.currentFossil != -1)
			return PixelmonItemsFossils.getFossilFromIndex(tile.currentFossil).getModelName().toLowerCase();
		else
			return "";
	}

	public void renderModel(TileEntityFossilMachine tile, float f) {
		if (tile.currentFossil != -1)
			if (PixelmonItemsFossils.getFossilFromIndex(tile.currentFossil).getModel() != null)
				PixelmonItemsFossils.getFossilFromIndex(tile.currentFossil).getModel().renderModel(tile, f);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
		renderAModelAt((TileEntityFossilMachine) tileentity, d, d1, d2, f); // where
																			// to
		// render
	}
}
