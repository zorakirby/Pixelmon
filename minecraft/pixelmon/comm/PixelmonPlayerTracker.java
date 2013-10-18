package pixelmon.comm;

import java.lang.reflect.Field;

import pixelmon.battles.BattleQuery;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.TcpConnection;
import net.minecraft.network.packet.NetHandler;
import cpw.mods.fml.common.IPlayerTracker;

public class PixelmonPlayerTracker implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			PixelmonStorage.playerLoggedIn((EntityPlayerMP) player);
			try {
				PixelmonStorage.PokeballManager.loadPlayer((EntityPlayerMP) player);
			} catch (PlayerNotLoadedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			try {
				PixelmonStorage.onPlayerDC(player);
				BattleQuery bq = BattleQuery.getQuery((EntityPlayerMP) player);
				if (bq != null)
					bq.declineQuery((EntityPlayerMP) player);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {

	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {

	}

}
