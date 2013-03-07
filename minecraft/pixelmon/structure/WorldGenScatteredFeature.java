package pixelmon.structure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumScatteredStructure;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenScatteredFeature extends MapGenScatteredFeature implements IWorldGenerator {

	private static boolean hasGenerated = false;

	EnumScatteredStructure structure;

	private int maxDistanceBetweenScatteredFeatures = 32;
	private int minDistanceBetweenScatteredFeatures = 8;

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int xPos = random.nextInt(16) + chunkX * 16;
		int yPos = 64;
		int zPos = random.nextInt(16) + chunkZ * 16;
		if (canSpawnStructureAtCoords(world, chunkX, chunkZ)) {
			structure = structure.getStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos));
			SchematicImporter s = new SchematicImporter(structure.getSchematicPath());
			s.readSchematic();
			GeneralScattered g = new GeneralScattered(random, xPos, structure.getY(yPos), zPos, s);
			g.generate(world, random);
			if (structure.spawnPokemon) {
			}
			System.out.println("A structure has Generated at: " + xPos + ", " + structure.getY(yPos) + ", " + zPos);
		}
	}

	protected boolean canSpawnStructureAtCoords(World world, int par1, int par2) {
		BiomeGenBase var8 = world.getWorldChunkManager().getBiomeGenAt(par1 * 16 + 8, par2 * 16 + 8);
		if (EnumScatteredStructure.getStructureFromBiome(var8) == null)
			return false;
		int var3 = par1;
		int var4 = par2;

		if (par1 < 0) {
			par1 -= this.maxDistanceBetweenScatteredFeatures - 1;
		}

		if (par2 < 0) {
			par2 -= this.maxDistanceBetweenScatteredFeatures - 1;
		}

		int var5 = par1 / this.maxDistanceBetweenScatteredFeatures;
		int var6 = par2 / this.maxDistanceBetweenScatteredFeatures;
		Random var7 = world.setRandomSeed(var5, var6, 14357617);
		var5 *= this.maxDistanceBetweenScatteredFeatures;
		var6 *= this.maxDistanceBetweenScatteredFeatures;
		var5 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
		var6 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);

		if (var3 == var5 && var4 == var6) {
			return true;
		}

		return false;
	}
}
