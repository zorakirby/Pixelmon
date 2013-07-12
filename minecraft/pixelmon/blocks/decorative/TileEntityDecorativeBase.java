package pixelmon.blocks.decorative;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityDecorativeBase extends TileEntity{
	
	@SuppressWarnings("incomplete-switch")
	public boolean isAnotherWithSameDirectionOnSide(World world, int x, int y, int z, ForgeDirection dir){
		TileEntity ent = null;
		switch(dir){
		case DOWN: ent = world.getBlockTileEntity(x, y - 1, z);
			break;
		case EAST: ent = world.getBlockTileEntity(x + 1, y, z);
			break; 
		case NORTH:ent = world.getBlockTileEntity(x, y, z - 1);
			break;
		case SOUTH:ent = world.getBlockTileEntity(x, y, z + 1);
			break;
		case UP: ent = world.getBlockTileEntity(x, y + 1, z);
			break;
		case WEST: ent = world.getBlockTileEntity(x - 1, y, z);
			break;
		}
		return isSameDirectionAndType(ent);
	}
	public boolean isSameDirectionAndType(TileEntity ent){
		return ent == null ? false : ent.getClass() == this.getClass() && ((ent.blockMetadata & 12) == (this.blockMetadata & 12));
	}
	
}
