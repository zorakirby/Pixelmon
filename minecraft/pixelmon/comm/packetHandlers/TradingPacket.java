package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import pixelmon.blocks.TileEntityTradeMachine;
import pixelmon.blocks.TradingRegistry;
import pixelmon.comm.EnumPackets;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerStorage;

import cpw.mods.fml.common.network.Player;

public class TradingPacket extends PacketHandlerBase {

	public TradingPacket() {
		packetsHandled.add(EnumPackets.SelectPokemonForTrade);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		if (index == EnumPackets.SelectPokemonForTrade.getIndex()) {
			int pos = dataStream.readInt();

			TileEntityTradeMachine te = TradingRegistry.getTileEntity((EntityPlayer) player);
			if (te.player1 == player) {
				te.setPos1(pos);
			} else if (te.player2 == player) {
				te.setPos2(pos);
			}
		} else if (index == EnumPackets.DeRegisterTrader.getIndex()) {
			TileEntityTradeMachine te = TradingRegistry.getTileEntity((EntityPlayer) player);
			te.removePlayer(player);
		} else if (index == EnumPackets.SetTradingReady.getIndex()) {
			boolean ready = dataStream.readInt() == 1;
			TileEntityTradeMachine te = TradingRegistry.getTileEntity((EntityPlayer) player);
			te.ready((EntityPlayer) player, ready);

		} else if (index == EnumPackets.Trade.getIndex()) {
			TileEntityTradeMachine te = TradingRegistry.getTileEntity((EntityPlayer) player);
			te.trade();
		}
	}
}
