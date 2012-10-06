/**
 * 
 */
package pixelmon.migration.worldRenderer;

import net.minecraft.src.BiomeGenBase;
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

	/**
	 * 
	 */
	public WorldRenderer(WorldProvider worldProvider) {
		this.worldProvider = worldProvider;
	}

	private BiomeGenBase[][] map;

	public void renderWorld() {
		map = new BiomeGenBase[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				map[i][j] = worldProvider.worldChunkMgr.getBiomeGenAt(i * 8, j * 8);
			}
		}
		worldRendered = true;
	}
}
