package pixelmon.storage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PixelmonStorage {
	public static PokeballManager PokeballManager = new PokeballManager();
	public static ComputerManager ComputerManager = new ComputerManager();

	public static void onPlayerDC(EntityPlayer player) {
		if (player == null)
			return;
		PokeballManager.onPlayerDC(player);
		ComputerManager.onPlayerDC(player);
	}

	public static void playerLoggedIn(EntityPlayerMP player) {
		PokeballManager.playerLoggedIn(player);
		ComputerManager.playerLoggedIn(player);
	}
}

