package pixelmon.structure.worldGen;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureComponent;
import pixelmon.RandomHelper;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumScatteredStructure;
import pixelmon.structure.SchematicImporter;
import pixelmon.structure.StructureData;
import pixelmon.structure.StructureRegistry;
import pixelmon.structure.generation.GeneralScattered;
import pixelmon.structure.generation.StructureScattered;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenScatteredFeature extends MapGenScatteredFeature implements IWorldGenerator {

	private static boolean hasGenerated = false;

	private int maxDistanceBetweenScatteredFeatures = 16;
	private int minDistanceBetweenScatteredFeatures = 1;

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int xPos = random.nextInt(16) + chunkX * 16;
		int yPos = 64;
		int zPos = random.nextInt(16) + chunkZ * 16;
		StructureData structure = StructureRegistry.getScatteredStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos));
		if (structure == null)
			return;
		SchematicImporter s = new SchematicImporter(structure.path);
		s.readSchematic();
		GeneralScattered g = new GeneralScattered(random, xPos, yPos, zPos, s, structure);
		if (canSpawnStructureAtCoords(world, g, structure, xPos, zPos)) {
			g.generate(world, random);
			if (structure.hasPokemon) {
			}
		}
	}

	protected boolean canSpawnStructureAtCoords(World world, StructureScattered s, StructureData structureData, int par1, int par2) {
		if (RandomHelper.getRandomNumberBetween(0, 40) == 0)
			return true;
		return false;
	}

	
}
