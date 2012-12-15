package pixelmon.migration;

import net.minecraft.world.WorldProvider;
import pixelmon.migration.IO.MigrationSaveManager;
import pixelmon.migration.worldRenderer.WorldRenderer;

public class Migration extends Thread {
	private WorldProvider worldProvider;
	private WorldRenderer worldRenderer;

	/**
	 * Constructor for the Migration code base. It's a threaded class with the
	 * normal sorts of functions, calls run on creation to create the thread and
	 * get it set up
	 * 
	 * @param provider
	 *            The world provider associated with the chosen dimension to
	 *            model migration for
	 */
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

	/*
	 * Sets up initial migration set up, loading in any previous saves found and
	 * setting up the file environment
	 */
	private void init() {
		worldRenderer = new WorldRenderer(worldProvider);
		saveManager = new MigrationSaveManager(worldProvider, worldRenderer);
	}

	private void loop() {

	}
}
