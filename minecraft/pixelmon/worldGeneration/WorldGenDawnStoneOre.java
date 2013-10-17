package pixelmon.worldGeneration;

import java.util.Random;

import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonGen;
import pixelmon.worldGeneration.biome.BiomeGenMysteryValley;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenDawnStoneOre implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX*16, chunkZ*16);
		float likelyHood = biome == PixelmonGen.mysteryValley ? 1F: BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MAGICAL) ? .33F : -1;
		if(random.nextFloat() <= likelyHood){
			for (int i = 0; i < 30; i++) {
				int xPos = random.nextInt(16) + chunkX*16;
				int zPos = random.nextInt(16) + chunkZ*16;
				int yPos = random.nextInt(50);
				new WorldGenMinable(PixelmonBlocks.dawnStoneOre.blockID, 2 + random.nextInt(2)).generate(world, random, xPos, yPos, zPos);
			}
		}
	}

}
