package pixelmon.entities;

import pixelmon.RandomHelper;
import pixelmon.config.PixelmonConfig;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;

public class EntitySpawning {
	@ForgeSubscribe
	public void onSpawnInChunk(EntityEvent.EnteringChunk event) {
		if (event.entity.posX > event.newChunkX * 16 + 16 || event.entity.posX < event.newChunkX * 16 || event.entity.posZ > event.newChunkZ * 16 + 16
				|| event.entity.posZ < event.newChunkZ * 16) {
			if (PixelmonConfig.printErrors)
				System.out.println("Fixing entity");
			event.entity.posX = RandomHelper.getRandomNumberBetween(0, 15) + event.newChunkX * 16;
			event.entity.posZ = RandomHelper.getRandomNumberBetween(0, 15) + event.newChunkZ * 16;
		}
	}
}
