package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import pixelmon.config.PixelmonBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenThunderStoneOre implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 30; i++) {
			int xPos = random.nextInt(16) + chunkX*16;
			int zPos = random.nextInt(16) + chunkZ*16;
			int yPos = random.nextInt(50) + 75; // generates 75 to 125
			new WorldGenMinable(PixelmonBlocks.thunderStoneOre.blockID, 2 + random.nextInt(2)).generate(world, random, xPos, yPos, zPos);
		}
	}

}
