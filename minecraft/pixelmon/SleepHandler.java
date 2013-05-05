package pixelmon;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SleepHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		for (int i = 0; i < MinecraftServer.getServer().worldServers.length; i++) {
			WorldServer world = MinecraftServer.getServer().worldServers[i];
			if (world != null) {
				if (world.areAllPlayersAsleep()) {
					for (int j = 0; j < world.playerEntities.size(); j++) {
						try {
							PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) world.playerEntities.get(j)).healAllPokemon();
						} catch (PlayerNotLoadedException e) {
						}
					}
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return "Sleep Tick Handler";
	}
}
