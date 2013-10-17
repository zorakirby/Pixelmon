package pixelmon.worldGeneration;

import net.minecraft.world.World;

public class BlockData {
	public int x, y, z, id, metadata;
	
	public BlockData(int x, int y, int z, int id, int meta){
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
		this.metadata = meta;
	}
	
	public void place(World world){
		world.setBlock(x, y, z, id, metadata, 2);
	}
}
