package pixelmon.spawning.spawners;

import java.util.List;
import java.util.Random;

import com.google.common.eventbus.EventBus;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import pixelmon.RandomHelper;
import pixelmon.database.SpawnLocation;
import pixelmon.spawning.SpawnData;

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

	public float getYOffset() {
		return 0;
	}

	enum worldState {
		day, dusk, dawn, night
	};

	public String getPokemonFromList(List<SpawnData> spawnList, World world) {
		long time = world.getWorldTime();
		worldState state;
		if (time >= 23000 && time < 2000)
			state = worldState.dawn;
		else if (time >= 2000 && time < 13000)
			state = worldState.day;
		else if (time >= 13000 && time < 16000)
			state = worldState.dusk;
		else
			state = worldState.night;
		int totalRarity = 0;
		for (int i = 0; i < spawnList.size(); i++) {
			if (state == worldState.day)
				totalRarity += spawnList.get(i).rarity.day;
			else if (state == worldState.night)
				totalRarity += spawnList.get(i).rarity.night;
			else if (state == worldState.dawn || state == worldState.dusk)
				totalRarity += spawnList.get(i).rarity.dawndusk;
		}

		int num = RandomHelper.getRandomNumberBetween(0, totalRarity);
		int sum = 0;
		for (int i = 0; i < spawnList.size(); i++) {
			int rarity = 0;
			if (state == worldState.day)
				rarity = spawnList.get(i).rarity.day;
			else if (state == worldState.night)
				rarity = spawnList.get(i).rarity.night;
			else if (state == worldState.dawn || state == worldState.dusk)
				rarity = spawnList.get(i).rarity.dawndusk;
			if (num <= sum + rarity)
				return spawnList.get(i).name;
			sum += rarity;
		}
		return null;
	}
	protected abstract boolean canPokemonSpawnHereImpl(World par1World, int par2, int par3, int par4);
}
