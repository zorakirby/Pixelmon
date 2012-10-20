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
	/**
	 * Blocks
	 */
	int[][][] blocks;
	/**
	 * Blocks metadata
	 */
	int[][][] blockData;
	/**
	 * Paintings, Containers, etc
	 */
	int[][][] tileEntities;
	/**
	 * The Bounding Box of the Schematic
	 */
	public int width, height, length;

	public SchematicImporter(String filename) {
		this.filename = filename;
	}

	public void readSchematic() {
		NBTTagCompound n = getNBTTag();
		if (n == null)
			return;

		width = n.getShort("Width");
		height = n.getShort("Height");
		length = n.getShort("Length");

		blocks = new int[width][height][length];
		blockData = new int[width][height][length];
		tileEntities = new int[width][height][length];
		byte[] blockArray = n.getByteArray("Blocks");
		byte[] blockDataArray = n.getByteArray("Data");
		//NBTTagCompound tileEntitiesArray = n.getCompoundTag("TileEntities");
		int i = 0;
		for (int y = 0; y < length; y++) {
			for (int z = 0; z < height; z++) {
				for (int x = 0; x < width; x++) {
					blocks[x][z][y] = blockArray[i];
					blockData[x][z][y] = blockDataArray[i];
					i++;
				}
			}
		}
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
