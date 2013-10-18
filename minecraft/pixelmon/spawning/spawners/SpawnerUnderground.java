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
import net.minecraftforge.common.ForgeDirection;

public class SpawnerUnderground extends SpawnerBase {
	
	/**
	 * radius for searching for a solid block in each direction (N,W,S,E,U,D) to determine
	 * if the location is probably good enough to be an enclosure-like space. 
	 */
	protected final static int ENCLOSURE_RADIUS = 5;

	public SpawnerUnderground() {
		super(PixelmonConfig.maxNumUndergroundPokemon, SpawnLocation.UnderGround);
	}

	@Override
	public boolean getSpawnCondition(World world, int cpX, int cpY, int cpZ) {
		return world.getWorldInfo().getTerrainType() != WorldType.FLAT && cpY < 60 && !world.isBlockNormalCube(cpX, cpY, cpZ)
				&& world.getBlockMaterial(cpX, cpY, cpZ) == Material.air;
	}

	@Override
	public String getRandomPokemon(WorldProvider worldProvider, Random rand, int par2, int par3, int par4) {
		BiomeGenBase b = worldProvider.worldObj.getBiomeGenForCoords(par2, par4);
		List<SpawnData> spawnData = SpawnRegistry.getUndergroundSpawnsForBiome(b);
		return spawnData != null && !spawnData.isEmpty() ? getPokemonFromList(spawnData, worldProvider.worldObj) : null;
	}

	@Override
	public boolean canPokemonSpawnHereImpl(World par1World, int par2, int par3, int par4) {
		if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)) {
			return false;
		} else {
			int var5 = par1World.getBlockId(par2, par3 - 1, par4);
			int topBlock = par1World.getTopSolidOrLiquidBlock(par2, par4);
			//if(topBlock <= par3)
			//	return false;
			if(!isMostlyEnclosedSpace(par1World, par2, par3, par4, ENCLOSURE_RADIUS))
				return false;
			boolean spawnBlock = (Block.blocksList[var5] != null && Block.blocksList[var5].canCreatureSpawn(EnumCreatureType.creature, par1World, par2, par3 - 1, par4));
			return spawnBlock && var5 != Block.bedrock.blockID && !par1World.isBlockNormalCube(par2, par3, par4) && !par1World.getBlockMaterial(par2, par3, par4).isLiquid()
					&& !par1World.isBlockNormalCube(par2, par3 + 1, par4);
		}
	}
	
	protected boolean isMostlyEnclosedSpace(World world, int x, int y, int z, int radius){
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			boolean ok = false;
			for(int i = 0; i < radius; i++){
				if(world.isBlockNormalCube(x+dir.offsetX*i, y+dir.offsetY*i, z+dir.offsetZ*i)){
					ok = true;
					break;
				}
			}
			if(!ok) return false;
		}
		return true;
	}

}
