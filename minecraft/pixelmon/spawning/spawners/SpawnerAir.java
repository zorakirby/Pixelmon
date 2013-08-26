package pixelmon.spawning.spawners;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.SpawnLocation;
import pixelmon.spawning.SpawnData;
import pixelmon.spawning.SpawnRegistry;

public class SpawnerAir extends SpawnerBase {

	public SpawnerAir() {
		super(PixelmonConfig.maxNumAirPokemon, SpawnLocation.AirPersistent);
	}

	@Override
	public boolean getSpawnCondition(World world, int cpX, int cpY, int cpZ) {
		if (world.getBlockMaterial(cpX, cpY, cpZ) != Material.air)
			return false;
		return true;
	}

	@Override
	public String getRandomPokemon(WorldProvider worldProvider, Random rand, int par2, int par3, int par4) {
		BiomeGenBase b = worldProvider.worldObj.getBiomeGenForCoords(par2, par4);
		List<SpawnData> spawnData = SpawnRegistry.getAirSpawnsForBiome(b);
		return spawnData != null && !spawnData.isEmpty() ? ((SpawnData) WeightedRandom.getRandomItem(rand, spawnData)).name : null;
	}

	@Override
	public boolean canPokemonSpawnHere(World par1World, int par2, int par3, int par4) {
		return true;
	}
	
	@Override
	public float getYOffset() {
		return 4;
	}

}
