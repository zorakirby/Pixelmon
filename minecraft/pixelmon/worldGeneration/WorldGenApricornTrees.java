package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import pixelmon.blocks.apricornTrees.BlockApricornTree;
import pixelmon.config.PixelmonBlocksApricornTrees;
import pixelmon.enums.EnumBiomes;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenApricornTrees implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.isHellWorld) // if nether
			return;
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16);
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST)){ /*this check will equate to 'true' for the same biomes as before (forest, forestHills, taiga, taigahills) but with the added bonus of also 
		 																			equating to 'true' for any biomes we register with FOREST type in the future*/
			for (int i = 0; i < random.nextInt(3) - 1; i++) {
				int x = random.nextInt(16) + chunkX * 16;
				int z = random.nextInt(16) + chunkZ * 16;
				int y = world.getHeightValue(x, z);

				int blockId = world.getBlockId(x, y - 1, z);
				if (blockId == Block.grass.blockID || blockId == Block.dirt.blockID) {
					int newId = 0;
					switch (random.nextInt(7)) {
					case 0:
						newId = PixelmonBlocksApricornTrees.apricornTreeBlack.blockID;
						break;
					case 1:
						newId = PixelmonBlocksApricornTrees.apricornTreeWhite.blockID;
						break;
					case 2:
						newId = PixelmonBlocksApricornTrees.apricornTreePink.blockID;
						break;
					case 3:
						newId = PixelmonBlocksApricornTrees.apricornTreeGreen.blockID;
						break;
					case 4:
						newId = PixelmonBlocksApricornTrees.apricornTreeBlue.blockID;
						break;
					case 5:
						newId = PixelmonBlocksApricornTrees.apricornTreeYellow.blockID;
						break;
					case 6:
						newId = PixelmonBlocksApricornTrees.apricornTreeRed.blockID;
						break;
					}
					world.setBlock(x, y, z, newId, BlockApricornTree.numStages - 1, 2);
				}
			}
		}

	}
}
