/**
 * 
 */
package pixelmon.migration.worldRenderer;

import pixelmon.migration.IO.MigrationSaveManager;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.WorldProvider;

/**
 * @author Malcolm
 * 
 *         This class renders the world as given by the worldProvider. Will turn
 *         the world into a biome map that the migration plotters can work with
 */
public class WorldRenderer {

	private WorldProvider worldProvider;
	public boolean worldRendered = false;
	private static final int width = 100, height = 100;

	/**
	 * @param saveManager
	 * 
	 */
	public WorldRenderer(WorldProvider worldProvider) {
		this.worldProvider = worldProvider;
	}

	private int[] map;

	public void renderWorld() {
		map = new int[width * height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				map[x + y * width] = worldProvider.worldChunkMgr.getBiomeGenAt(x * 8, y * 8).biomeID;
			}
		}
		worldRendered = true;
	}

	public BiomeGenBase getBiome(int x, int y) {
		for (BiomeGenBase b : BiomeGenBase.biomeList)
			if (b.biomeID == map[x + y * width])
				return b;

		return null;
	}

	public NBTTagCompound getData() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setIntArray("map", map);

		return nbt;
	}
}
