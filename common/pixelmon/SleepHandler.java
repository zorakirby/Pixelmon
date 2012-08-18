package pixelmon;

import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class SleepHandler {
	@ForgeSubscribe
	public void onSleep(PlayerSleepInBedEvent event){
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)event.entityPlayer).healAllPokemon();
	}
}
