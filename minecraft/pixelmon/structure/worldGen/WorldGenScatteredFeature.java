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
		GeneralScattered g = new GeneralScattered(random, xPos, yPos, zPos, s);
		if (canSpawnStructureAtCoords(world, g, xPos, zPos)) {
			g.generate(world, random);
			if (structure.hasPokemon) {
			}
			System.out.println("A structure has Generated at: " + xPos + ", " + g.getBoundingBox().minY + ", " + zPos);
		}
	}

	protected boolean canSpawnStructureAtCoords(World world, StructureScattered s, int par1, int par2) {
		if (RandomHelper.getRandomNumberBetween(0, 40) == 0)
			return canStructureFitAtCoords(world, s, par1, par2);
		return false;
	}

	private boolean canStructureFitAtCoords(World world, StructureScattered s, int x, int z) {
		int maxHeight = -1, minHeight = -1;
		for (int ix = s.getBoundingBox().minX; ix <= s.getBoundingBox().maxX; ix++) {
			for (int iz = s.getBoundingBox().minZ; iz <= s.getBoundingBox().maxZ; iz++) {
				int blockHeight = getTopSolidOrLiquidBlock(world, ix, iz);
				if (maxHeight == -1 || blockHeight > maxHeight)
					maxHeight = blockHeight;
				else if (minHeight == -1 || blockHeight < minHeight)
					minHeight = blockHeight;
			}
		}

		if (maxHeight - minHeight > 8)
			return false;

		s.setBoundingBoxBase(minHeight);
		return true;
	}

	public int getTopSolidOrLiquidBlock(World world, int par1, int par2) {
		Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
		int k = chunk.getTopFilledSegment() + 15;
		par1 &= 15;

		for (par2 &= 15; k > 0; --k) {
			int l = chunk.getBlockID(par1, k, par2);

			if (l != 0 && Block.blocksList[l].blockMaterial.blocksMovement() && Block.blocksList[l].blockMaterial != Material.leaves
					&& Block.blocksList[l].blockMaterial != Material.wood && !Block.blocksList[l].isBlockFoliage(world, par1, k, par2)) {
				return k + 1;
			}
		}

		return -1;
	}
}
