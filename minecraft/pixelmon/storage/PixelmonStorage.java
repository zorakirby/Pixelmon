package pixelmon.storage;

import net.minecraft.entity.player.EntityPlayer;

public class PixelmonStorage {
	public static PokeballManager PokeballManager = new PokeballManager();
	public static ComputerManager ComputerManager = new ComputerManager();

	public static void onPlayerDC(EntityPlayer player) {
		if (player == null)
			return;
		PokeballManager.onPlayerDC(player);
		ComputerManager.onPlayerDC(player);
	}
}
