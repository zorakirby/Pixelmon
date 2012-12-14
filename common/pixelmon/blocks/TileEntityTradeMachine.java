package pixelmon.blocks;

import pixelmon.Pixelmon;
import pixelmon.comm.PacketCreator;
import pixelmon.enums.EnumGui;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.TileEntity;

public class TileEntityTradeMachine extends TileEntity {

	public int playerCount = 0;
	public EntityPlayer player1, player2;

	public int tradeIndex = -1;

	public TileEntityTradeMachine() {
	}

	public void registerPlayer(EntityPlayer player) {
		playerCount++;
		if (playerCount == 1)
			player1 = player;
		if (playerCount == 2)
			player2 = player;

		TradingRegistry.registerTrade(this);
		player.openGui(Pixelmon.instance, EnumGui.Trading.getIndex(), player.worldObj, tradeIndex, 0, 0);
	}

}
