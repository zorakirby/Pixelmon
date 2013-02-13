package pixelmon.client.render;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.TileEntityFossilCleaner;
import pixelmon.client.models.ModelFossilCleaningMachineOff;
import pixelmon.client.models.ModelFossilCleaningMachineOn;
import pixelmon.config.PixelmonBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderTileFossilCleaner extends TileEntitySpecialRenderer {
	private ModelFossilCleaningMachineOn onModel;
	private ModelFossilCleaningMachineOff offModel;
	
	public RenderTileFossilCleaner() {
		onModel = new ModelFossilCleaningMachineOn();
		offModel = new ModelFossilCleaningMachineOff();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double d, double d1, double d2, float f) {
		TileEntityFossilCleaner tileEntity = (TileEntityFossilCleaner)tile;
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

		if (i <0)
			return;
		if (Minecraft.getMinecraft().theWorld.getBlockId(tile.xCoord, tile.yCoord-1, tile.zCoord) == PixelmonBlocks.pc.blockID) return;

		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
		GL11.glScalef(1.0F, -1F, -1F); 
		
		if (tileEntity.isOn()){
			bindTextureByName("/pixelmon/texture/blocks/fossilcleaningmachineon.png");
			onModel.renderModel(tileEntity, 0.0625F); 
		}else{
			bindTextureByName("/pixelmon/texture/blocks/fossilcleaningmachineoff.png");
			offModel.renderModel(tileEntity, 0.0625F); 
		}
		GL11.glPopMatrix(); // end
	}

}
