package pixelmon.migration.IO;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.WorldProvider;

public class MigrationSaveManager {

	private NBTTagCompound data;

	/**
	 * The overall save handling system built for migration. This class will
	 * stay as an option and be passed information to store and return requested
	 * information from save files
	 * 
	 * @param provider
	 *            the world provider for the modelled world
	 */
	public MigrationSaveManager(WorldProvider provider) {
		data = FileHandling.loadMigration(provider);
	}

}
