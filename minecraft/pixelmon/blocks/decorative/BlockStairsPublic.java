package pixelmon.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;


/**
 * This is literally just <code>BlockStairs</code> but with the constructor being public instead of protected, because the constructor even being protected in the first place makes a whole lotta sense.
 */
public class BlockStairsPublic extends BlockStairs{
	public BlockStairsPublic(int par1, Block par2Block, int par3) {
		super(par1, par2Block, par3);
	}
}
