package pixelmon.client.render.tileEntities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.TileEntityAnvil;
import pixelmon.client.models.ModelAnvil;
import pixelmon.client.models.discs.ModelDiscFlat;
import pixelmon.client.models.discs.ModelDiscHemiSphere;
import pixelmon.client.models.discs.ModelDiscStage1;
import pixelmon.client.models.discs.ModelDiscStage2;
import pixelmon.client.models.plates.ModelIngot;
import pixelmon.client.models.plates.ModelPlate;
import pixelmon.client.models.plates.ModelPlateStage2;
import pixelmon.client.models.plates.ModelPlateStage3;
import pixelmon.client.render.RenderResources;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.items.ItemPokeballDisc;
import pixelmon.items.ItemPokeballLid;

public class RenderTileEntityAnvil extends TileEntitySpecialRenderer {
	private ModelAnvil model;
	private ModelDiscFlat modelDiscFlat;
	private ModelDiscHemiSphere modelDiscHemiSphere;
	private ModelDiscStage1 modelDiscStage1;
	private ModelDiscStage2 modelDiscStage2;
	private ModelIngot modelPlateIngot;
	private ModelPlate modelPlate;
	private ModelPlateStage2 modelPlateStage2;
	private ModelPlateStage3 modelPlateStage3;

	public RenderTileEntityAnvil() {
		model = new ModelAnvil();
		modelDiscFlat = new ModelDiscFlat();
		modelDiscHemiSphere = new ModelDiscHemiSphere();
		modelDiscStage1 = new ModelDiscStage1();
		modelDiscStage2 = new ModelDiscStage2();
		modelPlateIngot = new ModelIngot();
		modelPlate = new ModelPlate();
		modelPlateStage2 = new ModelPlateStage2();
		modelPlateStage3 = new ModelPlateStage3();
	}

	public void renderAModelAt(TileEntityAnvil tile, double d, double d1, double d2, float f) {
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

		if (i < 0)
			return;

		func_110628_a(RenderResources.anvil); // texture
		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
		GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
										// and you can see what happens
		model.renderModel(tile, 0.0625F); // renders and yes 0.0625 is a random
											// number
		if (tile.itemOnAnvil != -1) {
			GL11.glTranslatef((float) 0, (float) 0.03F, (float) 0); // size
			Item itemToRender = PixelmonItemsPokeballs.getItemFromID(tile.itemOnAnvil);
			if (tile.itemOnAnvil == PixelmonItems.aluminiumIngot.itemID) {
				func_110628_a(RenderResources.aluminiumIngot);
				if (tile.state == 0)
					modelPlateIngot.renderModel(0.0625f);
				else if (tile.state == 1)
					modelPlateStage2.renderModel(0.0625f);
				else if (tile.state == 2)
					modelPlateStage3.renderModel(0.0625f);
			} else if (tile.itemOnAnvil == PixelmonItems.aluminiumPlate.itemID) {
				func_110628_a(RenderResources.aluminiumIngot);
				modelPlate.renderModel(0.0625f);
			}
			if (itemToRender instanceof ItemPokeballDisc || itemToRender == PixelmonItemsPokeballs.ironDisc) {
				if (itemToRender == PixelmonItemsPokeballs.ironDisc)
					func_110628_a(RenderResources.ironDisc);
				else
					func_110628_a(new ResourceLocation("pixelmon:textures/pokeballs/" + ((ItemPokeballDisc) itemToRender).pokeball.getTexture()));
				if (tile.state == 0)
					modelDiscFlat.renderModel(0.0625f);
				else if (tile.state == 1)
					modelDiscStage1.renderModel(0.0625f);
				else if (tile.state == 2)
					modelDiscStage2.renderModel(0.0625f);
			} else if (itemToRender instanceof ItemPokeballLid || itemToRender == PixelmonItemsPokeballs.ironBase) {
				if (itemToRender == PixelmonItemsPokeballs.ironBase)
					func_110628_a(RenderResources.ironDisc);
				else
					func_110628_a(new ResourceLocation("pixelmon:textures/pokeballs/" + ((ItemPokeballLid) itemToRender).pokeball.getTexture()));
				modelDiscHemiSphere.renderModel(0.0625f);
			}
		}
		GL11.glPopMatrix(); // end

	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
		renderAModelAt((TileEntityAnvil) tileentity, d, d1, d2, f); // where to
																	// render
	}
}
