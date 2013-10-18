package pixelmon.structure;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
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
	public int offsetX = 0, offsetY = 0, offsetZ = 0;
	
	public StructureData creator;
	
	public SchematicImporter(String filename) {
		this.filename = filename;
	}
	
	public SchematicImporter(String filename, StructureData creator){
		this.filename = filename;
		this.creator = creator;
	}
	
	public void setOffset(int x, int y, int z){
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;
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
					int[] idAndMeta = {blockArray[i], blockDataArray[i]};
					if(creator != null && creator.filter != null){
						idAndMeta = creator.doFilter(idAndMeta);
					}
					blocks[x][y][z] = idAndMeta[0];
					blockData[x][y][z] = idAndMeta[1];
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
