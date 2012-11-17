package pixelmon;

import java.io.*;

import cpw.mods.fml.common.asm.transformers.MCPMerger;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import pixelmon.Pixelmon;
import pixelmon.StarterList;
import pixelmon.battles.participants.ParticipantType;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonLevelUpPacket;
import pixelmon.comm.PixelmonPokedexPacket;
import pixelmon.config.PixelmonEntityList;
import pixelmon.database.DatabaseMoves;
import pixelmon.enums.EnumGui;
import pixelmon.gui.battles.ClientBattleManager;
import pixelmon.gui.battles.ClientBattleManager.AttackData;
import pixelmon.gui.battles.GuiBattle;
import pixelmon.gui.battles.GuiBattle.BattleMode;
import pixelmon.items.ItemData;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;

import net.minecraft.src.INetworkManager;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.Packet250CustomPayload;

public class ClientPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
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
			} else if (packetID == EnumPackets.BattleMessage.getIndex()) {
				ClientBattleManager.addMessage(Packet.readString(dataStream, 64));
			} else if (packetID == EnumPackets.SetOpponentType.getIndex()) {
				ClientBattleManager.opponentType = ParticipantType.get(dataStream.readInt());
			} else if (packetID == EnumPackets.SetOpponent.getIndex()) {
				PixelmonDataPacket p = new PixelmonDataPacket();
				try {
					p.readPacketData(dataStream);
					ClientBattleManager.opponent = p;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.SetBattlingPokemon.getIndex()) {
				ClientBattleManager.pokemonId = dataStream.readInt();
			} else if (packetID == EnumPackets.EnforcedSwitch.getIndex()) {
				ClientBattleManager.addMessage("Choose a new pokemon to send out!");
				GuiBattle.mode = BattleMode.EnforcedSwitch;
				GuiBattle.oldMode = BattleMode.MainMenu;
			} else if (packetID == EnumPackets.BackToMainMenu.getIndex()) {
				GuiBattle.oldMode = BattleMode.MainMenu;
				GuiBattle.mode = BattleMode.MainMenu;
			} else if (packetID == EnumPackets.ExitBattle.getIndex()) {
				GuiBattle.battleEnded = true;
			} else if (packetID == EnumPackets.ChooseMoveToReplace.getIndex()) {
				int pokemonID = dataStream.readInt();
				int newAttackId = dataStream.readInt();
				int level = dataStream.readInt();
				ClientBattleManager.newAttackList.add(new AttackData(pokemonID, DatabaseMoves.getAttack(newAttackId), level));
				if (!(Minecraft.getMinecraft().currentScreen instanceof GuiBattle))
					Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.LearnMove.getIndex(), Minecraft.getMinecraft().theWorld, 0, 0, 0);
			} else if (packetID == EnumPackets.LevelUp.getIndex()) {
				PixelmonLevelUpPacket p = new PixelmonLevelUpPacket();
				p.readPacketData(dataStream);
				ClientBattleManager.levelUpList.add(p);
				if (!(Minecraft.getMinecraft().currentScreen instanceof GuiBattle))
					Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.LevelUp.getIndex(), Minecraft.getMinecraft().theWorld, 0, 0, 0);
			} else if(packetID == EnumPackets.Pokedex.getIndex())
			{
				PixelmonPokedexPacket p = new PixelmonPokedexPacket();
				try
				{
					p.readPacketData(dataStream);
					EntityPlayer ep = (EntityPlayer) player;
					PixelmonStorage.PokeballManager.getPlayerStorage(PixelmonStorage.PokeballManager.getPlayerFromName(ep.username)).pokedex = p.getPokedex(ep.username);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
