package pixelmon.blocks.apricornTrees;

import pixelmon.enums.EnumApricornTrees;
import net.minecraft.src.TileEntity;

public class TileEntityApricornTree extends TileEntity {

	public BlockApricornTree block;
	public EnumApricornTrees tree;
	
	public TileEntityApricornTree(BlockApricornTree block, EnumApricornTrees tree) {
		this.block = block;
		this.tree = tree;
	}

}
