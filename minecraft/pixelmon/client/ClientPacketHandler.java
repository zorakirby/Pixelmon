package pixelmon.client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import pixelmon.Pixelmon;
import pixelmon.battles.participants.ParticipantType;
import pixelmon.battles.status.Transformed;
import pixelmon.client.gui.ClientTradingManager;
import pixelmon.client.gui.GuiPixelmonOverlay;
import pixelmon.client.gui.battles.ClientBattleManager;
import pixelmon.client.gui.battles.GuiAcceptDeny;
import pixelmon.client.gui.battles.ClientBattleManager.AttackData;
import pixelmon.client.gui.battles.GuiBattle;
import pixelmon.client.gui.battles.GuiBattle.BattleMode;
import pixelmon.client.gui.pokedex.ClientPokedexManager;
import pixelmon.comm.BattleQueryPacket;
import pixelmon.comm.BossDropPacket;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonLevelUpPacket;
import pixelmon.comm.PixelmonPokedexPacket;
import pixelmon.comm.PixelmonStatsPacket;
import pixelmon.comm.PixelmonTransformPacket;
import pixelmon.comm.PixelmonUpdatePacket;
import pixelmon.comm.StarterListPacket;
import pixelmon.database.DatabaseMoves;
import pixelmon.enums.EnumGui;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

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
			} else if (packetID == EnumPackets.UpdatePokemon.getIndex()) {
				PixelmonUpdatePacket p = new PixelmonUpdatePacket();
				try {
					p.readPacketData(dataStream);
					ServerStorageDisplay.update(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.AddToTempStore.getIndex()) {
				PixelmonServerStore.addToList(dataStream);
			} else if (packetID == EnumPackets.RemoveFromTempStore.getIndex()) {
				PixelmonServerStore.removeFromList(dataStream.readInt(), dataStream.readInt());
			} else if (packetID == EnumPackets.ClearTempStore.getIndex()) {
				PixelmonServerStore.clearList();
			} else if (packetID == EnumPackets.SetMousePokemon.getIndex()) {
				PixelmonServerStore.setMousePokemon(dataStream);
			} else if (packetID == EnumPackets.ClearMousePokemon.getIndex()) {
				PixelmonServerStore.clearMousePokemon();
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
				ClientBattleManager.canSwitch = dataStream.readInt() == 1;
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
			} else if (packetID == EnumPackets.Pokedex.getIndex()) {
				PixelmonPokedexPacket p = new PixelmonPokedexPacket();
				try {
					p.readPacketData(dataStream);
					ClientPokedexManager.pokedex = p.getPokedex();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.RegisterTrader.getIndex()) {
				String username = Packet.readString(dataStream, 64);
				ClientTradingManager.findTradePartner(username);
			} else if (packetID == EnumPackets.SetTradeTarget.getIndex()) {
				PixelmonDataPacket p = new PixelmonDataPacket();
				try {
					p.readPacketData(dataStream);
					ClientTradingManager.tradeTarget = p;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.SetTradeTargetStats.getIndex()) {
				PixelmonStatsPacket p = new PixelmonStatsPacket();
				try {
					p.readPacketData(dataStream);
					ClientTradingManager.tradeTargetStats = p;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.SetSelectedStats.getIndex()) {
				PixelmonStatsPacket p = new PixelmonStatsPacket();
				try {
					p.readPacketData(dataStream);
					ClientTradingManager.selectedStats = p;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.SetTradingReadyClient.getIndex()) {
				boolean ready = dataStream.readInt() == 1;
				ClientTradingManager.player2Ready = ready;
			} else if (packetID == EnumPackets.Transform.getIndex()) {
				PixelmonTransformPacket p = new PixelmonTransformPacket();
				try {
					p.readPacketData(dataStream);
					Transformed.applyToClientEntity(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.SwitchCamera.getIndex()) {
				Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
				// Minecraft.getMinecraft().renderViewEntity =
				// Minecraft.getMinecraft().thePlayer;
			} else if (packetID == EnumPackets.PlayerDeath.getIndex()) {
				GuiPixelmonOverlay.isVisible = true;
				Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
				Minecraft.getMinecraft().gameSettings.hideGUI = false;
				Minecraft.getMinecraft().renderViewEntity = Minecraft.getMinecraft().thePlayer;
				Minecraft.getMinecraft().currentScreen = null;
			} else if (packetID == EnumPackets.BattleQuery.getIndex()) {
				BattleQueryPacket p = new BattleQueryPacket();
				try {
					p.readPacketData(dataStream);
					GuiAcceptDeny.opponent = p;
					Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.AcceptDeny.getIndex(), Minecraft.getMinecraft().theWorld,
							p.queryIndex, 0, 0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.BossDrop.getIndex()) {
				try {
					BossDropPacket p = new BossDropPacket();
					p.readPacketData(dataStream);
					for (int i = 0; i < p.itemIds.length; i++) {
						System.out.println("item " + i + " = " + p.itemIds[i]);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (packetID == EnumPackets.StarterList.getIndex()) {
				StarterListPacket p = new StarterListPacket();
				try {
					p.readPacketData(dataStream);
					PixelmonServerStore.starterListPacket = p;
				} catch (IOException e) {
					e.printStackTrace();
				}
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.ChooseStarter.getIndex(), Minecraft.getMinecraft().theWorld, 0, 0, 0);
			} else if (packetID == EnumPackets.Evolution.getIndex()) {
				int pokemonID = dataStream.readInt();
				String newPokemonName = Packet.readString(dataStream, 64);
				PixelmonServerStore.evolutionTarget = newPokemonName;
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.Evolution.getIndex(), Minecraft.getMinecraft().theWorld, pokemonID, 0, 0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
