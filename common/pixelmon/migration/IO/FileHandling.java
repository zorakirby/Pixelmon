package pixelmon.migration.IO;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.WorldProvider;

public class FileHandling {

	/**
	 * Retrieves the save as an NBTTagCompound for processing
	 * 
	 * @param provider
	 *            the world provider for the modelled world
	 * @return the NBTTagCompound stored in the save
	 */
	public static boolean loadMigration(WorldProvider provider, NBTTagCompound nbt) {
		File migrationFile = getSaveFile(provider);
		if (!migrationFile.exists()) {
			nbt = new NBTTagCompound();
			return true;
		}
		try {
			nbt = CompressedStreamTools.read(new DataInputStream(new FileInputStream(migrationFile)));
		} catch (Exception e) {
			ErrorHandling.handleError("Couldn't read from migration file");
		}
		return false;
	}

	/**
	 * Manages the path for saving and loading migration data
	 * 
	 * @param provider
	 *            the world provider for the modelled world
	 * @return the save file
	 */
	private static File getSaveFile(WorldProvider provider) {
		File f = new File("saves/" + provider.getSaveFolder() + "/migration/migration.data");
		if (!f.exists()) {
			f.mkdirs();
			try {
				f.createNewFile();
			} catch (Exception e) {

			}
		}
		return f;
	}

}
