package pixelmon.client.render.tileEntities;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.BlockEvolutionRock;
import pixelmon.blocks.TileEntityEvolutionRock;
import pixelmon.client.render.RenderResources;
import pixelmon.config.PixelmonBlocks;
import pixelmon.enums.EnumCustomModel;
import pixelmon.enums.EnumEvolutionRock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@Deprecated
public class RenderTileEntityEvolutionRock extends TileEntitySpecialRenderer {

	IModelCustom rockModel;
	IModelCustom icyRockModel;
	IModelCustom mossyRockModel;
	
	public RenderTileEntityEvolutionRock() {
		rockModel = EnumCustomModel.EvoRock.theModel;
	//	icyRockModel = AdvancedModelLoader.loadModel("/pixelmon/models/icyrock/icyrock.obj");
	//	mossyRockModel = AdvancedModelLoader.loadModel("/pixelmon/models/mossyrock/mossyrock.obj");
		icyRockModel = AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/icyrock/icyrock.obj");
		mossyRockModel = AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/mossyrock/mossyrock.obj");



	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double d, double d1, double d2, float f) {
		if (Minecraft.getMinecraft().theWorld.getBlockId(tile.xCoord, tile.yCoord-1, tile.zCoord) == PixelmonBlocks.pc.blockID) return;
		BlockEvolutionRock block = (BlockEvolutionRock)((TileEntityEvolutionRock)tile).blockType;
		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1+0.04f, (float) d2 + 0.5F); // size
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
		
		if (block.rockType == EnumEvolutionRock.MossyRock){
			bindTexture(RenderResources.mossrocktex); 
			mossyRockModel.renderAll();

		}else if (block.rockType == EnumEvolutionRock.IcyRock){
			bindTexture(RenderResources.icyrocktex); 
			icyRockModel.renderAll();

		}
		GL11.glPopMatrix(); // end

	}

}
