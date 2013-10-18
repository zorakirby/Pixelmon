package pixelmon.client.render.tileEntities;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.BlockShrine;
import pixelmon.blocks.TileEntityShrine;
import pixelmon.client.models.pokemon.ModelAbra;
import pixelmon.client.render.RenderResources;
import pixelmon.config.PixelmonBlocks;
import pixelmon.enums.EnumShrine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.ForgeDirection;

public class RenderTileEntityShrine extends TileEntitySpecialRenderer {

	IModelCustom unoModel;
	IModelCustom dosModel;
	IModelCustom tresModel;

	IModelCustom unoModelOrb;
	IModelCustom dosModelOrb;
	IModelCustom tresModelOrb;

	public RenderTileEntityShrine() {
		unoModel = AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/shrines/articuno/EmptyShrine.obj");
		dosModel = AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/shrines/zapdos/EmptyShrine.obj");
		tresModel = AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/shrines/moltres/EmptyShrine.obj");

		unoModelOrb = AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/shrines/articuno/Shrine.obj");
		dosModelOrb = AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/shrines/zapdos/Shrine.obj");
		tresModelOrb = AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/shrines/moltres/Shrine.obj");

	}

	public void renderModelAt(TileEntityShrine tile, double d, double d1, double d2, float f) {
		BlockShrine block = (BlockShrine) ((TileEntityShrine) tile).getBlockType();
		int i = tile.getBlockMetadata(); // this is for rotation
		int j = 0;
		int y = 0;
		if (i != 4) {
			if (i == 0) {
				j = 0;
			}

			if (i == 1) {
				j = -1;
			}

			if (i == 2) {
				j = 180;
			}

			if (i == 3) {
				j = 1;
			}

			if (i < 0)
				return;
		}
		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.04f, (float) d2 + 0.5F); // size
		GL11.glRotatef(180, 1, 0, j);
		GL11.glScalef(.3F, -.3F, -.3F); // if you read this comment out this
										// line

		if (block.rockType == EnumShrine.Articuno) {
			if (block.uno = true && tile.blockMetadata == 4) {
				bindTexture(RenderResources.uno);
				unoModelOrb.renderAll();
			} else {
				bindTexture(RenderResources.uno);
				unoModel.renderAll();
			}
		} else if (block.rockType == EnumShrine.Zapdos) {
			if (block.dos = true && tile.blockMetadata == 4) {
				bindTexture(RenderResources.dos);
				dosModelOrb.renderAll();
			} else {
				bindTexture(RenderResources.dos);
				dosModel.renderAll();
			}
		} else if (block.rockType == EnumShrine.Moltres) {
			if (block.tres = true && tile.blockMetadata == 4) {
				bindTexture(RenderResources.tres);
				tresModelOrb.renderAll();
			} else {
				bindTexture(RenderResources.tres);
				tresModel.renderAll();
			}
		}
		GL11.glPopMatrix(); // end

	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double d, double d1, double d2, float f) {
		renderModelAt((TileEntityShrine) tile, d, d1, d2, f);
	}
}
