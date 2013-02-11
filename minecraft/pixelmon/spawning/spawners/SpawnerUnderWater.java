package pixelmon.spawning.spawners;

import java.util.List;
import java.util.Random;

import pixelmon.config.PixelmonConfig;
import pixelmon.database.SpawnLocation;
import pixelmon.spawning.SpawnData;
import pixelmon.spawning.SpawnRegistry;

import net.minecraft.block.material.Material;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;

public class SpawnerUnderWater extends SpawnerBase {

	public SpawnerUnderWater() {
		super(PixelmonConfig.maxNumWaterPokemon, SpawnLocation.Water);
	}

	@Override
	public boolean getSpawnCondition(World world, int cpX, int cpY, int cpZ) {
		return !world.isBlockNormalCube(cpX, cpY, cpZ) && world.getBlockMaterial(cpX, cpY, cpZ) == Material.water;
	}

	@Override
	public String getRandomPokemon(WorldProvider worldProvider, Random rand, int par2, int par3, int par4) {
		BiomeGenBase b = worldProvider.worldObj.getBiomeGenForCoords(par2, par4);
		List<SpawnData> spawnData = SpawnRegistry.getWaterSpawnsForBiome(b);
		return spawnData != null && !spawnData.isEmpty() ? ((SpawnData) WeightedRandom.getRandomItem(rand, spawnData)).name : null;
	}

	@Override
	public boolean canPokemonSpawnHere(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMaterial(par2, par3, par4).isLiquid() && !par1World.isBlockNormalCube(par2, par3 + 1, par4);
	}

}
