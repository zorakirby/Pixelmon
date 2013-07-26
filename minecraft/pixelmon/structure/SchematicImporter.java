package pixelmon.structure;

import java.io.File;
import java.io.FileInputStream;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;

public class SchematicImporter {
	private String filename;
	/**
	 * Blocks
	 */
	public int[][][] blocks;
	/**
	 * Blocks metadata
	 */
	public int[][][] blockData;
	/**
	 * Paintings, Containers, etc
	 */
	public NBTTagList tileEntities;
	/**
	 * The Bounding Box of the Schematic
	 */
	public int width, height, length;

	public SchematicImporter(String filename) {
		this.filename = filename;
	}

	public void readHeader() {
		NBTTagCompound n = getNBTTag();
		if (n == null)
			return;

		width = n.getShort("Width");
		height = n.getShort("Height");
		length = n.getShort("Length");
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
		byte[] blockArray = n.getByteArray("Blocks");
		byte[] blockDataArray = n.getByteArray("Data");
		tileEntities = n.getTagList("TileEntities");
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
			return CompressedStreamTools.readCompressed(StructureRegistry.class.getResourceAsStream(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
