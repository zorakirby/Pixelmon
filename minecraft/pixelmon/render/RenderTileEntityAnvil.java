package pixelmon.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Item;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;
import pixelmon.blocks.TileEntityAnvil;
import pixelmon.blocks.TileEntityPC;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.items.ItemPokeballDisc;
import pixelmon.items.ItemPokeballLid;
import pixelmon.models.ModelAnvil;
import pixelmon.models.ModelPC;

public class RenderTileEntityAnvil extends TileEntitySpecialRenderer {
	private ModelAnvil model;

	public RenderTileEntityAnvil() {
		model = new ModelAnvil();
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

		bindTextureByName("/pixelmon/texture/blocks/anvil.png"); // texture
		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
		GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
										// and you can see what happens
		model.renderModel(tile, 0.0625F); // renders and yes 0.0625 is a random
											// number
		if (tile.itemOnAnvil != -1) {
			Item itemToRender = PixelmonItemsPokeballs.getItemFromID(tile.itemOnAnvil);
			if (itemToRender instanceof ItemPokeballDisc) {
				
			} else if (itemToRender instanceof ItemPokeballLid) {
				
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
