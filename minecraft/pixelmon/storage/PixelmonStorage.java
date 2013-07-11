package pixelmon.storage;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.controller.BattleController;

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

