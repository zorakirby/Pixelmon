package pixelmon.spawning.spawners;

import java.util.Random;

import com.google.common.eventbus.EventBus;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import pixelmon.database.SpawnLocation;

public abstract class SpawnerBase {
	public static final EventBus SPAWN_DENIAL_BUS = new EventBus("Pixelmon Spawn Denial");
	public SpawnLocation spawnLocation;
	public int count = 0;
	public int maxNum = 0;

	public SpawnerBase(int maxNum, SpawnLocation spawnLocation) {
		this.maxNum = maxNum;
		this.spawnLocation = spawnLocation;
	}
	
	public static void register(Object obj){
		SPAWN_DENIAL_BUS.register(obj);
	}
	
	protected boolean requestSpawn(World world, int x, int y, int z){
		SpawnRequestEvent event = new SpawnRequestEvent(world, x, y, z, this.spawnLocation);
		SPAWN_DENIAL_BUS.post(event);
		return event.approved;
	}

	public abstract boolean getSpawnCondition(World world, int cpX, int cpY, int cpZ);
	
	public abstract String getRandomPokemon(WorldProvider worldProvider, Random rand, int par2, int par3, int par4);
	
	public boolean canPokemonSpawnHere(World par1World, int par2, int par3, int par4){
		if(!requestSpawn(par1World, par2, par3, par4))
			return false;
		return canPokemonSpawnHereImpl(par1World, par2, par3, par4);
	}

	protected abstract boolean canPokemonSpawnHereImpl(World par1World, int par2, int par3, int par4);
}
