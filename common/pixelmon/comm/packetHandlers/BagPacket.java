package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.PixelmonItem;

import cpw.mods.fml.common.network.Player;

public class BagPacket extends PacketHandlerBase {

	public BagPacket() {
		packetsHandled.add(EnumPackets.BagPacket);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		int itemIndex = dataStream.readInt();
		int battleIndex = dataStream.readInt();

		ItemStack usedStack = null;
		for (ItemStack i : ((EntityPlayer) player).inventory.mainInventory)
			if (i.getItem().shiftedIndex == itemIndex)
				usedStack = i;

		if (usedStack == null) {
			ChatHandler.sendChat((EntityPlayer) player, "Item Could not be found!");
			return;
		}

		BattleController bc = BattleRegistry.getBattle(battleIndex);
		if (bc == null) {
			ChatHandler.sendChat((EntityPlayer) player, "Battle Could not be found!");
			return;
		}

		PixelmonEntityHelper userPokemon = null, targetPokemon = null;

		if (bc.participant1 instanceof PlayerParticipant) {
			if (((PlayerParticipant) bc.participant1).player == (EntityPlayerMP) player) {
				userPokemon = bc.participant1.currentPokemon();
				targetPokemon = bc.participant2.currentPokemon();
			} else {
				userPokemon = bc.participant2.currentPokemon();
				targetPokemon = bc.participant1.currentPokemon();
			}
		}

		PixelmonItem item = (PixelmonItem) usedStack.getItem();
		item.useFromBag(userPokemon, targetPokemon);

		usedStack.stackSize--;
	}

}
