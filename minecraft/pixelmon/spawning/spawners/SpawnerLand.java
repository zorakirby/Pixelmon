package pixelmon.spawning.spawners;

import java.util.List;
import java.util.Random;

import pixelmon.config.PixelmonConfig;
import pixelmon.database.SpawnLocation;
import pixelmon.spawning.SpawnData;
import pixelmon.spawning.SpawnRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;

public class SpawnerLand extends SpawnerBase {

	public SpawnerLand() {
		super(PixelmonConfig.maxNumLandPokemon, SpawnLocation.Land);
	}

	@Override
	public boolean getSpawnCondition(World world, int cpX, int cpY, int cpZ) {
		return (world.getWorldInfo().getTerrainType() == WorldType.FLAT || cpY > 60) && !world.isBlockNormalCube(cpX, cpY, cpZ)
				&& world.getBlockMaterial(cpX, cpY, cpZ) == Material.air;
	}

	@Override
	public String getRandomPokemon(WorldProvider worldProvider, Random rand, int par2, int par3, int par4) {
		BiomeGenBase b = worldProvider.worldObj.getBiomeGenForCoords(par2, par4);
		List<SpawnData> spawnData = SpawnRegistry.getSpawnsForBiome(b);
		return spawnData != null && !spawnData.isEmpty() ? getPokemonFromList(spawnData, worldProvider.worldObj) : null;
	}

	@Override
	public boolean canPokemonSpawnHereImpl(World par1World, int par2, int par3, int par4) {
		if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)) {
			return false;
		} else {
			int var5 = par1World.getBlockId(par2, par3 - 1, par4);
			boolean spawnBlock = (Block.blocksList[var5] != null && Block.blocksList[var5].canCreatureSpawn(EnumCreatureType.creature, par1World, par2, par3 - 1, par4));
			return spawnBlock && var5 != Block.bedrock.blockID && !par1World.isBlockNormalCube(par2, par3, par4) && !par1World.getBlockMaterial(par2, par3, par4).isLiquid()
					&& !par1World.isBlockNormalCube(par2, par3 + 1, par4);
		}
	}

}
