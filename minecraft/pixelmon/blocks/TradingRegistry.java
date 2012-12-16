package pixelmon.blocks;

import java.util.ArrayList;

public class TradingRegistry {
	private static int tradeIndex = 0;
	private static ArrayList<TileEntityTradeMachine> tradeList = new ArrayList<TileEntityTradeMachine>();

	public static void registerTrade(TileEntityTradeMachine entity) {
		entity.tradeIndex = tradeIndex++;
		tradeList.add(entity);
	}

	public static TileEntityTradeMachine getTileEntity(int tradeIndex){
		return tradeList.get(tradeIndex);
	}

}
