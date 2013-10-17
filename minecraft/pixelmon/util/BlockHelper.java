package pixelmon.util;

import pixelmon.WorldHelper;
import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeDirection;

public class BlockHelper {
	
	public static boolean isBlockNormalCube(Block block){
		return block.blockMaterial.isOpaque() && block.renderAsNormalBlock() && !block.canProvidePower();
	}

}
