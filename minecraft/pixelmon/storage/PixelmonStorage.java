package pixelmon.storage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.battles.BattleRegistry;

public class PixelmonStorage {
	public static PokeballManager PokeballManager = new PokeballManager();
	public static ComputerManager ComputerManager = new ComputerManager();

	public static void onPlayerDC(EntityPlayer player) {
		if (player == null)
			return;
		PokeballManager.onPlayerDC(player);
		ComputerManager.onPlayerDC(player);
		if(BattleRegistry.getBattle(player) != null) 
			BattleRegistry.getBattle(player).endBattleWithoutXP();
			
	}

	public static void playerLoggedIn(EntityPlayerMP player) {
		PokeballManager.playerLoggedIn(player);
		ComputerManager.playerLoggedIn(player);
	}
}
