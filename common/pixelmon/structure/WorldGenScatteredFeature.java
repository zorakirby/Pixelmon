package pixelmon.structure;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.MapGenScatteredFeature;
import net.minecraft.src.World;

public class WorldGenScatteredFeature extends MapGenScatteredFeature implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		//generate(chunkProvider, world, chunkX, chunkZ, par5ArrayOfByte)		
	}

	
}
