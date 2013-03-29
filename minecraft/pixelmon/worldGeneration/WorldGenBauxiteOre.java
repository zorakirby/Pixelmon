package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonConfig;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBauxiteOre extends WorldGenerator implements IWorldGenerator {
	WorldGenMinable bauxiteGen = new WorldGenMinable(PixelmonBlocks.bauxite.blockID, 8);
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 5; i++) {
			int xPos = random.nextInt(2) + chunkX * 16;
			int zPos = random.nextInt(4) + chunkZ * 16;
			int yPos = random.nextInt(30) + 30;
			bauxiteGen.generate(world, random, xPos, yPos, zPos);
		}
	}
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		return false;
	}

}
