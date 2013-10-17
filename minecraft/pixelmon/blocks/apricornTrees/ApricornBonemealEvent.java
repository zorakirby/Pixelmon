package pixelmon.blocks.apricornTrees;

import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonBlocksApricornTrees;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumApricornTrees;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

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

				TileEntityApricornTree tileEntityApricornTree = (TileEntityApricornTree) world.getBlockTileEntity(event.X, event.Y, event.Z);

				if (event.entityPlayer.getHeldItem().itemID == PixelmonItems.wailmerPail.itemID) {
					if (!(tileEntityApricornTree.wasWateredToday())) {
						tileEntityApricornTree.updateWatering();
						world.setBlockMetadataWithNotify(event.X, event.Y, event.Z, world.getBlockMetadata(event.X, event.Y, event.Z) + 1, 2);
						event.setResult(Result.ALLOW);
					} else {
						ChatHandler.sendChat(event.entityPlayer, "It looks well watered today.");
					}
				} else {
					world.setBlockMetadataWithNotify(event.X, event.Y, event.Z, world.getBlockMetadata(event.X, event.Y, event.Z) + 1, 2);
					event.setResult(Result.ALLOW);
				}
			}
		}
	}
}
