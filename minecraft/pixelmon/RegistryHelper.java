package pixelmon;

import net.minecraftforge.common.MinecraftForge;
import pixelmon.battles.BattleTickHandler;
import pixelmon.comm.ConnectionHandler;
import pixelmon.comm.PixelmonPlayerTracker;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonRecipes;
import pixelmon.entities.EntityDeath;
import pixelmon.entities.EntitySpawning;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.projectiles.EntityHook;
import pixelmon.enums.EnumPokemon;
import pixelmon.spawning.PixelmonSpawner;
import pixelmon.storage.PixelmonStorage;
import pixelmon.structure.StructureRegistry;
import pixelmon.structure.worldGen.WorldGenScatteredFeature;
import pixelmon.worldGeneration.WorldGenApricornTrees;
import pixelmon.worldGeneration.WorldGenBauxiteOre;
import pixelmon.worldGeneration.WorldGenEvolutionRock;
import pixelmon.worldGeneration.WorldGenFireStoneOre;
import pixelmon.worldGeneration.WorldGenFossils;
import pixelmon.worldGeneration.WorldGenLeafStoneOre;
import pixelmon.worldGeneration.WorldGenThunderStoneOre;
import pixelmon.worldGeneration.WorldGenWaterStoneOre;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class RegistryHelper {

	public static void init(FMLInitializationEvent event, Pixelmon pixelmon){
		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());

		GameRegistry.registerPlayerTracker(new PixelmonPlayerTracker());
		
		GameRegistry.registerWorldGenerator(new WorldGenLeafStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenWaterStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenThunderStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenFireStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenApricornTrees());
		GameRegistry.registerWorldGenerator(new WorldGenBauxiteOre());
		GameRegistry.registerWorldGenerator(new WorldGenFossils());
		GameRegistry.registerWorldGenerator(new WorldGenEvolutionRock());

		PixelmonRecipes.addRecipes();
		EntityRegistry.registerModEntity(EntityPokeBall.class, "Pokeball", PixelmonConfig.idPokeball, Pixelmon.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityHook.class, "Hook", 216, pixelmon, 75, 1, true);
		
		NetworkRegistry.instance().registerGuiHandler(pixelmon.instance, pixelmon.proxy);
		
		StructureRegistry.loadStructures(event.getSide());
		
	
		MinecraftForge.EVENT_BUS.register(PixelmonStorage.PokeballManager);
		MinecraftForge.EVENT_BUS.register(PixelmonStorage.ComputerManager);
		MinecraftForge.EVENT_BUS.register(new EntitySpawning());
		MinecraftForge.EVENT_BUS.register(new EntityDeath());

		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new SleepHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new PixelmonSpawner(), Side.SERVER);
		TickRegistry.registerTickHandler(new BattleTickHandler(), Side.SERVER);
		
		for(EnumPokemon pokemon: EnumPokemon.values()){
			Entity3HasStats.getBaseStats(pokemon.name);
		}
		
		if(PixelmonConfig.spawnStructures){
			GameRegistry.registerWorldGenerator(new WorldGenScatteredFeature());
		}
	}
	
}
