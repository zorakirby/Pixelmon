package pixelmon.migration.IO;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import pixelmon.migration.worldRenderer.WorldRenderer;

public class MigrationSaveManager {

	public boolean isNewWorld = true;
	private NBTTagCompound data;
	private WorldRenderer renderer;

	/**
	 * The overall save handling system built for migration. This class will
	 * stay as an option and be passed information to store and return requested
	 * information from save files
	 * 
	 * @param provider
	 *            the world provider for the modelled world
	 */
	public MigrationSaveManager(WorldProvider provider, WorldRenderer renderer) {
		isNewWorld = FileHandling.loadMigration(provider, data);
		this.renderer = renderer;
		if (isNewWorld)
			loadWorld();
		else {
			renderer.renderWorld();
			saveWorld();
		}
	}

	/**
	 * The saving routine, outputting the worldRenderer Info to a save format
	 */
	public void saveWorld() {
		data = renderer.getData();
	}

	/**
	 * Loads the save file into a worldRenderer
	 */
	public WorldRenderer loadWorld() {
		
		return null;
	}

}