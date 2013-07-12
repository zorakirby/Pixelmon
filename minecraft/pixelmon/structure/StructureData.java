package pixelmon.structure;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureData {
	public String path;
	public int xSize, ySize, zSize;
	public boolean hasPokemon = false;
	public BiomeGenBase[] biomes;
	public int depth = 0;
	public HashMap<Integer, Integer> filter;

	public int[] doFilter(int[] idAndMeta){
		if(filter!= null && filter.containsKey(idAndMeta[0])){
			idAndMeta[0] = filter.get(idAndMeta[0]);
		}
		return idAndMeta;
	}
}
