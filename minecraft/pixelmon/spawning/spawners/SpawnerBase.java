package pixelmon.spawning.spawners;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import pixelmon.database.SpawnLocation;

public abstract class SpawnerBase {
	public SpawnLocation spawnLocation;
	public int count = 0;
	public int maxNum = 0;

	public SpawnerBase(int maxNum, SpawnLocation spawnLocation) {
		this.maxNum = maxNum;
		this.spawnLocation = spawnLocation;
	}

	public abstract boolean getSpawnCondition(World world, int cpX, int cpY, int cpZ);
	
	public abstract String getRandomPokemon(WorldProvider worldProvider, Random rand, int par2, int par3, int par4);
	
	public abstract boolean canPokemonSpawnHere(World par1World, int par2, int par3, int par4);

}
