package pixelmon.structure;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;

public class SchematicImporter {
	private String filename;

	public SchematicImporter(String filename) {
		this.filename = filename;
	}

	public void readSchematic() {
		NBTTagCompound n = getNBTTag();
		if (n == null)
			return;
		Iterator iterator = n.getTags().iterator();
		do {
			if (!iterator.hasNext())
				break;

			NBTBase nbtbase = (NBTBase) iterator.next();

			if (nbtbase instanceof NBTTagCompound) {
			}
		} while (true);
	}

	NBTTagCompound getNBTTag() {
		try {
			File file = new File(filename);
			return CompressedStreamTools.readCompressed(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
