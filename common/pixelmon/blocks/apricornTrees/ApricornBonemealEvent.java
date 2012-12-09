package pixelmon.blocks.apricornTrees;

import pixelmon.config.PixelmonBlocksApricornTrees;
import pixelmon.config.PixelmonItemsApricorns;
import pixelmon.enums.EnumApricornTrees;
import pixelmon.enums.EnumApricorns;
import net.minecraft.src.Item;
import net.minecraft.src.World;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.player.*;

public class ApricornBonemealEvent {

	public EnumApricornTrees tree;
	
	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event){
		World world = event.world;
		PixelmonBlocksApricornTrees trees = new PixelmonBlocksApricornTrees();
		int Id = event.ID;
		if(Id == trees.apricornTreeBlack.blockID || Id == trees.apricornTreeWhite.blockID|| Id == trees.apricornTreePink.blockID || Id == trees.apricornTreeGreen.blockID || Id == trees.apricornTreeBlue.blockID || Id == trees.apricornTreeYellow.blockID || Id == trees.apricornTreeRed.blockID){
			if(!world.isRemote && world.getBlockMetadata(event.X, event.Y, event.Z) != BlockApricornTree.numStages - 1){
			world.setBlockMetadata(event.X, event.Y, event.Z, world.getBlockMetadata(event.X, event.Y, event.Z) + 1);
			event.entityPlayer.inventory.getCurrentItem().stackSize--;
			}
		}
	}
}
