package pixelmon.migration;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import pixelmon.Pixelmon;

public class MigrationLoader {

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		if (!event.world.provider.hasNoSky && !event.world.provider.isHellWorld)
			Pixelmon.migration = new Migration(event.world.provider);
	}
}
