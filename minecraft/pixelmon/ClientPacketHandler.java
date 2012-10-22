package pixelmon;

import java.io.*;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import pixelmon.Pixelmon;
import pixelmon.StarterList;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonEntityList;
import pixelmon.gui.battles.ClientBattleManager;
import pixelmon.gui.battles.GuiBattle;
import pixelmon.gui.battles.GuiBattle.BattleMode;
import pixelmon.items.ItemData;

import net.minecraft.src.EntityPlayer;

import net.minecraft.src.NetClientHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.Packet250CustomPayload;

public class ClientPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(NetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		try {
			int packetID = dataStream.readInt();
			if (packetID == EnumPackets.AddToStorage.getIndex()) {
				ServerStorageDisplay.add(dataStream);
			} else if (packetID == EnumPackets.RemoveFromStorage.getIndex()) {
				ServerStorageDisplay.remove(dataStream.readInt());
			} else if (packetID == EnumPackets.UpdateStorage.getIndex()) {
				ServerStorageDisplay.update(dataStream);
			} else if (packetID == EnumPackets.AddToTempStore.getIndex()) {
				PixelmonServerStore.addToList(dataStream);
			} else if (packetID == EnumPackets.ClearTempStore.getIndex()) {
				PixelmonServerStore.clearList();
			}else if (packetID == EnumPackets.BattleMessage.getIndex()){
				ClientBattleManager.addMessage(Packet.readString(dataStream, 64));
			}else if (packetID == EnumPackets.SetOpponent.getIndex()){
				PixelmonDataPacket p = new PixelmonDataPacket();
				try {
					p.readPacketData(dataStream);
					ClientBattleManager.opponentId = p.pokemonID;
					PixelmonServerStore.addToList(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if (packetID == EnumPackets.SetBattlingPokemon.getIndex()){
				ClientBattleManager.pokemonId = dataStream.readInt();
			}else if (packetID == EnumPackets.BackToMainMenu.getIndex()){
				GuiBattle.mode = BattleMode.MainMenu;
			}else if (packetID == EnumPackets.ExitBattle.getIndex()){
				GuiBattle.battleEnded = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
