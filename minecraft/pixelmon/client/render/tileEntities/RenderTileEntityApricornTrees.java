package pixelmon.client.render.tileEntities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.apricornTrees.TileEntityApricornTree;
import pixelmon.client.models.apricornTrees.ApricornTreeStore;

public class RenderTileEntityApricornTrees extends TileEntitySpecialRenderer {

	public void renderAModelAt(TileEntityApricornTree tile, double d, double d1, double d2, float f) {
		int i = tile.getBlockMetadata(); // this is for rotation

		func_110628_a(ApricornTreeStore.getTexture(tile.tree, i)); // texture
		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size
		GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
										// and you can see what happens

		ApricornTreeStore.getModel(i).renderModel(tile, 0.0625F); // renders
																		// and
																		// yes
																		// 0.0625
																		// is
																		// a
																		// random
		// number
		GL11.glPopMatrix(); // end

	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
		renderAModelAt((TileEntityApricornTree) tileentity, d, d1, d2, f); // where
																			// to
		// render
	}

}
