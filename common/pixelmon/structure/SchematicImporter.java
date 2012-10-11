package pixelmon.structure;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

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
		File file = new File(filename);
		NBTTagCompound n = null;
		try {
			n = CompressedStreamTools.read(new DataInputStream(new FileInputStream(file)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}
