package pixelmon.blocks;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import net.minecraft.tileentity.TileEntity;
import pixelmon.Pixelmon;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PlayerStorage;

public class TileEntityTradeMachine extends TileEntity {

	public int playerCount = 0;
	public EntityPlayer player1, player2;
	public boolean ready1, ready2;
	public EntityPixelmon pokemon1, pokemon2;

	public int tradeIndex = -1;
	public TileEntityTradeMachine() {
		if(this.tradeIndex == -1)
		TradingRegistry.registerTrade(this);
	}

	public void registerPlayer(EntityPlayer player) {
		playerCount++;
		if (playerCount == 1)
			player1 = player;
		if (playerCount == 2)
			player2 = player;

		player.openGui(Pixelmon.instance, EnumGui.Trading.getIndex(), player.worldObj, tradeIndex, 0, 0);
	}
	
	public boolean ready(EntityPlayer player, int p){
		
//		PlayerStorage pStorage = new PlayerStorage(player);
		
		if (player1 !=null)
		if (player.username == player1.username){
			ready1 = true;
//			pokemon1 = pStorage.getAlreadyExists(p, worldObj);
		}
		if (player2 !=null)
		if (player.username == player2.username){
			ready2 = true;
//			pokemon2 = pStorage.getAlreadyExists(p, worldObj);
		}
			
		return false;
	}

}