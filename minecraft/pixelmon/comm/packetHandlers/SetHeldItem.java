package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pixelmon.comm.EnumPackets;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.common.network.Player;

public class SetHeldItem extends PacketHandlerBase {

	public SetHeldItem() {
		packetsHandled.add(EnumPackets.SetHeldItem);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		int pokemonId = dataStream.readInt();
		int itemId = dataStream.readInt();
		try {
			PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player);
			int oldItemId = storage.getNBT(pokemonId).getInteger("HeldItem");
			if (storage.EntityAlreadyExists(pokemonId, ((EntityPlayerMP) player).worldObj)) {
				EntityPixelmon pixelmon = storage.getAlreadyExists(pokemonId, ((EntityPlayerMP) player).worldObj);
				Item heldItem = PixelmonItemsHeld.getHeldItem(itemId);
				if (heldItem != null)
					pixelmon.heldItem = new ItemStack(heldItem);
				else
					pixelmon.heldItem = null;
				storage.updateNBT(pixelmon);
			} else {
				storage.getNBT(pokemonId).setInteger("HeldItem", itemId);
			}

			ItemStack currentItem = ((EntityPlayerMP) player).inventory.getItemStack();
			if (currentItem != null)
				currentItem.stackSize--;
			if (oldItemId == -1) {
				if (currentItem == null || currentItem.stackSize <= 0)
					((EntityPlayerMP) player).inventory.setItemStack(null);
				else
					((EntityPlayerMP) player).inventory.setItemStack(currentItem);
			} else {
				if (itemId == -1) {
					((EntityPlayerMP) player).inventory.setItemStack(new ItemStack(PixelmonItemsHeld.getHeldItem(oldItemId)));
				} else if (itemId != oldItemId) {
					if (currentItem == null || currentItem.stackSize <= 0)
						((EntityPlayerMP) player).inventory.setItemStack(new ItemStack(PixelmonItemsHeld.getHeldItem(oldItemId)));
					else {
						((EntityPlayerMP) player).inventory.setItemStack(currentItem);
						Item item = PixelmonItemsHeld.getHeldItem(oldItemId);
						if (item != null)
							((EntityPlayerMP) player).dropPlayerItem(new ItemStack(item));
					}
				} else {
					if (currentItem != null)
						currentItem.stackSize++;
					((EntityPlayerMP) player).inventory.setItemStack(currentItem);
				}
			}
		} catch (PlayerNotLoadedException e) {
		}
	}
}
