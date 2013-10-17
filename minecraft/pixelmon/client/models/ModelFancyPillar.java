package pixelmon.client.models;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.BlockFancyPillar;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import pixelmon.client.render.RenderResources;
import pixelmon.enums.EnumCustomModel;

public class ModelFancyPillar extends ModelEntityBlock{
	
	PixelmonModelRenderer platform, column, fracturedBottom, fracturedTop;
	
	public ModelFancyPillar(){
		platform = new PixelmonModelRenderer(this);
		column = new PixelmonModelRenderer(this);
		fracturedBottom = new PixelmonModelRenderer(this);
		fracturedTop = new PixelmonModelRenderer(this);
		platform.addCustomModel(new ModelCustomWrapper(EnumCustomModel.PillarPlatform.theModel));
		column.addCustomModel(new ModelCustomWrapper(EnumCustomModel.PillarColumn.theModel));
		fracturedBottom.addCustomModel((new ModelCustomWrapper(EnumCustomModel.PillarColumnFracturedBottom.theModel)));
		fracturedTop.addCustomModel((new ModelCustomWrapper(EnumCustomModel.PillarColumnFracturedTop.theModel)));
	}
	

	@Override
	public void renderTileEntity(TileEntityDecorativeBase tileEnt, float scale) {
		BlockFancyPillar theBlock = (BlockFancyPillar) tileEnt.getBlockType();
		int x = tileEnt.xCoord;
		int y = tileEnt.yCoord;
		int z = tileEnt.zCoord;
		int meta = tileEnt.getBlockMetadata();
		int sidebitmask = theBlock.getConnections(tileEnt.worldObj, x, y, z, meta);
		GL11.glPushMatrix();
		if(!theBlock.getIsDamaged(meta))
			renderStandardPillar(tileEnt.blockMetadata, sidebitmask, scale);
		else
			renderDamagedPillar(tileEnt.blockMetadata, sidebitmask, scale);
		GL11.glPopMatrix();
	}


	@Override
	public void renderInvBlock(BlockContainerPlus block, int meta, float scale) {
		BlockFancyPillar theBlock = (BlockFancyPillar) block;
		GL11.glPushMatrix();
		if(!theBlock.getIsDamaged(meta))
			renderStandardPillar(0, 0, scale);
		else
			renderDamagedPillar(1, 4, scale);
		GL11.glPopMatrix();
	}
	
	public void renderStandardPillar(int blockMeta, int bitmask, float scale){
		Minecraft.getMinecraft().renderEngine.func_110577_a(RenderResources.pillar);
		column.render(scale);
		boolean flag = (bitmask & 2) == 0;
		if(flag){
			platform.setRotationPoint(0, 6.5F, 0);
			platform.render(scale);
		}
		flag = (bitmask & 4) == 0;
		if(flag){
			platform.setRotationPoint(0, -6.5F, 0);
			platform.render(scale);
		}
		
	}
	
	public void renderDamagedPillar(int blockMeta, int bitmask, float scale){
		
		Minecraft.getMinecraft().renderEngine.func_110577_a(RenderResources.pillarDamaged);
		boolean useBottom = (blockMeta & 7) % 2 != 0;
		boolean flagbottom = (bitmask & 2) == 0; // true if nothing connectable on bottom side
		boolean flagtop = (bitmask & 4) == 0; //true if nothing connectable on top side
		//if the side the block was placed on has a connectable block, then DON'T render the platform, but otherwise, DO
		if(useBottom){
			fracturedBottom.render(scale);
			if(flagbottom){
				platform.setRotationPoint(0, 6.5F, 0);
				platform.render(scale);
			}
			if(!flagtop){
				fracturedTop.render(scale);
			}
		}
		else{
			fracturedTop.render(scale);
			if(flagtop){
				platform.setRotationPoint(0, -6.5F, 0);
				platform.render(scale);
			}
			if(!flagbottom){
				fracturedBottom.render(scale);
			}
		}
	}
	
	
	
	
	
	

}
