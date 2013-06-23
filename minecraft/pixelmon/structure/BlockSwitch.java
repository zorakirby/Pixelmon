package pixelmon.structure;

import pixelmon.config.PixelmonBlocks;
import net.minecraft.block.Block;

public class BlockSwitch {
	public int initBlockID;
	public Block replacementBlock;
	
	public BlockSwitch(int initBlockID, String replacementString){
		this.initBlockID = initBlockID;
		if (replacementString.equalsIgnoreCase("healer")){
			replacementBlock = PixelmonBlocks.healer;
		}
	}
}
