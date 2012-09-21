package pixelmon.migration.IO;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.WorldProvider;

public class MigrationSaveManager {

	private NBTTagCompound data;
	
	public MigrationSaveManager(WorldProvider provider) {
		data = FileHandling.loadMigration(provider);
	}

}
