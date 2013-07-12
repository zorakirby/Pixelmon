package pixelmon.client.models;

import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class ModelEntityBlock extends ModelBase{
	public abstract void renderInvBlock(BlockContainerPlus block, int meta, float scale);
	public abstract void renderTileEntity(TileEntityDecorativeBase tileEnt, float scale);
}
