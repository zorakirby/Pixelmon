package pixelmon.blocks.apricornTrees;

import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import pixelmon.config.PixelmonBlocksApricornTrees;
import pixelmon.enums.EnumApricornTrees;

public class ApricornBonemealEvent {

	public EnumApricornTrees tree;

	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event) {
		World world = event.world;
		PixelmonBlocksApricornTrees trees = new PixelmonBlocksApricornTrees();
		int Id = event.ID;
		if (Id == trees.apricornTreeBlack.blockID || Id == trees.apricornTreeWhite.blockID || Id == trees.apricornTreePink.blockID
				|| Id == trees.apricornTreeGreen.blockID || Id == trees.apricornTreeBlue.blockID || Id == trees.apricornTreeYellow.blockID
				|| Id == trees.apricornTreeRed.blockID) {
			if (!world.isRemote && world.getBlockMetadata(event.X, event.Y, event.Z) != BlockApricornTree.numStages - 1) {
				world.setBlockMetadataWithNotify(event.X, event.Y, event.Z, world.getBlockMetadata(event.X, event.Y, event.Z) + 1, 2);
				event.entityPlayer.inventory.getCurrentItem().stackSize--;
			}
		}
	}
}
