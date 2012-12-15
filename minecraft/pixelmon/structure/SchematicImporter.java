package pixelmon.structure;

import java.io.File;
import java.io.FileInputStream;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

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
		// NBTTagCompound tileEntitiesArray = n.getCompoundTag("TileEntities");
		int i = 0;
		for (int y = 0; y < height; y++) {
			for (int z = 0; z < length; z++) {
				for (int x = 0; x < width; x++) {
					blocks[x][y][z] = blockArray[i];
					blockData[x][y][z] = blockDataArray[i];
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
