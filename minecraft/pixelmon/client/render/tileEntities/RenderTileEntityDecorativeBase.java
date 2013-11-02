package pixelmon.client.render.tileEntities;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import pixelmon.client.models.ModelEntityBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderTileEntityDecorativeBase extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{
	static HashMap<BlockContainerPlus, ModelEntityBlock> modelMap = new HashMap();
	public static final RenderTileEntityDecorativeBase INSTANCE = new RenderTileEntityDecorativeBase();
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float scale) {
		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GL11.glScalef(-1, -1, 1);
		doRotation((BlockContainerPlus) tileentity.blockType, tileentity.blockMetadata);
		ModelEntityBlock theModel = getModelFor((BlockContainerPlus) tileentity.blockType);
		theModel.renderTileEntity((TileEntityDecorativeBase) tileentity, 0.0625F); //and no, 0.0625 isn't random, it's 1/16 :3
		GL11.glPopMatrix();
	}
	
	public void doRotation(BlockContainerPlus block, int meta){
		switch(block.dir){
		case SIXAXIS: doAllSixRotation(meta);
			break;
		case THREEAXIS: do3SettingRotation(meta);
		case NONE:
			break;
		case STAIRS:
			break;
		case WOODLOG:
			break;
		case YAW:
			break;
		default:
			break;		
		}
	}
	
	public void do3SettingRotation(int meta) {
		switch((meta & 7) / 2){
		case 1 : GL11.glRotatef(-90, 1, 0, 0); break;
		case 2 : GL11.glRotatef(-90, 0, 0, 1); break;
		}
	}

	public void doAllSixRotation(int meta){
		switch(meta & 7){
		case 0 : GL11.glRotatef(180, 1, 0, 0); break;
		case 1 : break;
		case 2 : GL11.glRotatef(90, 1, 0, 0); break;
		case 3 : GL11.glRotatef(-90, 1, 0, 0); break;
		case 4 : GL11.glRotatef(90, 0, 0, 1); break;
		case 5 : GL11.glRotatef(-90, 0, 0, 1); break;
		}
	}

	public static ModelEntityBlock addModelFor(BlockContainerPlus block){
		ModelEntityBlock model = null;
		try {
			model = (ModelEntityBlock) block.modelClass.newInstance();
		} catch (Exception e){
			e.printStackTrace();
		}
		modelMap.put(block, model);
		return model;
	}
	
	public static ModelEntityBlock getModelFor(BlockContainerPlus block){
		return modelMap.containsKey(block) ? modelMap.get(block) : addModelFor((BlockContainerPlus) block);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        if(!(block).renderAsNormalBlock())
        {
		float scale = ((BlockContainerPlus)block).invScale;
        float[] trans = ((BlockContainerPlus)block).invOffsets;
        GL11.glPushMatrix();
		GL11.glTranslatef(trans[0], trans[1], trans[2]);
		GL11.glScalef(-1*scale, -1*scale, scale);
		getModelFor((BlockContainerPlus) block).renderInvBlock((BlockContainerPlus) block, metadata, 0.0625F);
		GL11.glPopMatrix();
        }
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return BlockContainerPlus.renderingID;
	}
	
	
	
	
	
	
	

}
