package pixelmon.blocks;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

public class TradingRegistry {
	private static int tradeIndex = 0;
	private static ArrayList<TileEntityTradeMachine> tradeList = new ArrayList<TileEntityTradeMachine>();

	public static void registerTrade(TileEntityTradeMachine entity) {
		entity.tradeIndex = tradeIndex++;
		tradeList.add(entity);
	}

	public static TileEntityTradeMachine getTileEntity(int tradeIndex) {
		for (int i = 0; i < tradeList.size(); i++) {
			if (tradeList.get(i).tradeIndex == tradeIndex)
				return tradeList.get(i);
		}
		return null;
	}

	public static TileEntityTradeMachine getTileEntity(EntityPlayer player) {
		for (int i = 0; i < tradeList.size(); i++) {
			if (tradeList.get(i).player1 == player || tradeList.get(i).player2 == player)
				return tradeList.get(i);
		}
		return null;
	}

}
