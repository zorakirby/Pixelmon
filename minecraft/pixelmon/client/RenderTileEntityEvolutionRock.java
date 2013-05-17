package pixelmon.client;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.TileEntityEvolutionRock;
import pixelmon.config.PixelmonBlocks;
import pixelmon.enums.EnumEvolutionRock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderTileEntityEvolutionRock extends TileEntitySpecialRenderer {

	IModelCustom icyRockModel;
	IModelCustom mossyRockModel;
	
	public RenderTileEntityEvolutionRock() {
		icyRockModel = AdvancedModelLoader.loadModel("/pixelmon/models/icyrock/icyrock.obj");
		mossyRockModel = AdvancedModelLoader.loadModel("/pixelmon/models/mossyrock/mossyrock.obj");
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double d, double d1, double d2, float f) {
		if (Minecraft.getMinecraft().theWorld.getBlockId(tile.xCoord, tile.yCoord-1, tile.zCoord) == PixelmonBlocks.pc.blockID) return;

		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size

		GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
		
		if (((TileEntityEvolutionRock)tile).rockType == EnumEvolutionRock.MossyRock){
			bindTextureByName("/pixelmon/models/icyrock/icyrocktex.png"); 
			mossyRockModel.renderAll();
		}else if (((TileEntityEvolutionRock)tile).rockType == EnumEvolutionRock.IcyRock){
			icyRockModel.renderAll();
		}
		GL11.glPopMatrix(); // end

	}

}
