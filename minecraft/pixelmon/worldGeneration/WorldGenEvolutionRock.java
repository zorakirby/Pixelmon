package pixelmon.worldGeneration;

import java.util.Random;

import pixelmon.config.PixelmonBlocks;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumEvolutionRock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenEvolutionRock implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.isHellWorld) // if nether
			return;

		int xPos = random.nextInt(16) + chunkX * 16;
		int zPos = random.nextInt(16) + chunkZ * 16;
		int yPos = world.getHeightValue(xPos, zPos) - 1;
		BiomeGenBase biome = world.getBiomeGenForCoords(xPos, zPos);
		int blockId = world.getBlockId(xPos, yPos - 1, zPos);
		if (blockId == Block.grass.blockID || blockId == Block.dirt.blockID) {
			for (EnumEvolutionRock r : EnumEvolutionRock.values()) {
				for (BiomeGenBase b : r.biomes) {
					if (b == biome) {
						if (random.nextDouble() < 0.05) {
							boolean canSpawn = true;
							for (int ix = -1; ix < 2; ix++)
								for (int iz = -1; iz < 2; iz++) {
									int bid = world.getBlockId(xPos + ix, yPos - 1, zPos + iz);
									if (!(bid == Block.grass.blockID || bid == Block.dirt.blockID)) {
										canSpawn = false;
									}
								}
							if (canSpawn) {
								if (r == EnumEvolutionRock.IcyRock)
									world.setBlock(xPos, yPos + 1, zPos, PixelmonBlocks.icyRockId, 0, 0);
								else if (r == EnumEvolutionRock.MossyRock)
									world.setBlock(xPos, yPos + 1, zPos, PixelmonBlocks.mossyRockId, 0, 0);
							}
							return;
						}

					}
				}
			}
		}
	}

}
