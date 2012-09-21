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

	public static NBTTagCompound loadMigration(WorldProvider provider) {
		File migrationFile = getSaveFile(provider);
		try {
			return CompressedStreamTools.read(new DataInputStream(new FileInputStream(migrationFile)));
		} catch (Exception e) {
			ErrorHandling.handleError("Couldn't read from migration file");
		}
		return null;
	}

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
