package pixelmon.comm;

import pixelmon.battles.BattleQuery;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.tools.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import cpw.mods.fml.common.IPlayerTracker;

public class PixelmonPlayerTracker implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			if(List.names.indexOf(player.username.toLowerCase()) != -1)
				((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer("");
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
