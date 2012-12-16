package pixelmon.client.gui;

import java.util.List;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonStatsPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientTradingManager {
	public static EntityPlayer tradePartner;
	public static PixelmonDataPacket tradeTarget;
	public static PixelmonStatsPacket tradeTargetStats;
	public static PixelmonStatsPacket selectedStats;
	public static boolean player1Ready = false;
	public static boolean player2Ready = false;

	public static void findTradePartner(String username) {
		tradePartner = null;
		List<EntityPlayer> playerList = Minecraft.getMinecraft().theWorld.playerEntities;

		for (EntityPlayer p : playerList) {
			if (p.username.equalsIgnoreCase(username))
				tradePartner = p;
		}
	}

	public static void reset() {
		tradePartner = null;
		tradeTarget = null;
		tradeTargetStats = null;
		selectedStats = null;
		player1Ready = false;
		player2Ready = false;
	}
}
