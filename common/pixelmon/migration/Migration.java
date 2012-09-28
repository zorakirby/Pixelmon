package pixelmon.migration;

import pixelmon.migration.IO.MigrationSaveManager;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldProvider;

public class Migration extends Thread {
	private WorldProvider worldProvider;
	public Migration(WorldProvider provider) {
		worldProvider = provider;
		start();
	}

	private MigrationSaveManager saveManager;

	@Override
	public void run() {
		init();
		loop();
	}
	
	private void init(){
		saveManager = new MigrationSaveManager(worldProvider);
	}
	
	private void loop(){
		
	}
}
