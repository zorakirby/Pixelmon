package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.controller.BattleController;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import cpw.mods.fml.common.network.Player;

public class BagPacket extends PacketHandlerBase {

	public BagPacket() {
		packetsHandled.add(EnumPackets.BagPacket);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		int itemIndex = dataStream.readInt();
		int battleIndex = dataStream.readInt();
		int additionalInfo = dataStream.readInt();

		ItemStack usedStack = null;
		for (ItemStack i : ((EntityPlayer) player).inventory.mainInventory) {
		     if (i != null && i.getItem().itemID == itemIndex){
				usedStack = i;
				break;
		     }
		}

		if (usedStack == null) {
			ChatHandler.sendChat((EntityPlayer) player, "Item Could not be found!");
			return;
		}

		BattleController bc = BattleRegistry.getBattle(battleIndex);
		if (bc == null) {
			ChatHandler.sendChat((EntityPlayer) player, "Battle Could not be found!");
			return;
		}
		
		bc.setUseItem(player,  usedStack,  additionalInfo);
	}

}
