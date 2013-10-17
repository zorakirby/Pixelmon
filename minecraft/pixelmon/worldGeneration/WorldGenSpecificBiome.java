package pixelmon.worldGeneration;

import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenSpecificBiome extends WorldGenerator{
	
	public final BiomeGenBase user;
	
	public WorldGenSpecificBiome(BiomeGenBase user){
		this.user = user;
	}
	
	protected int getFitting(World world, int x, int z, AbstractList2D points, int skip, int tolerance){
		return WorldHelper.getFitting(world, user, x, z, points, skip, tolerance);
	}

}
