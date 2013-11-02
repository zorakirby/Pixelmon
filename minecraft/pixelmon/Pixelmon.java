package pixelmon;

import java.io.File;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraft.util.StringTranslate;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import pixelmon.battles.BattleTickHandler;
import pixelmon.blocks.apricornTrees.ApricornBonemealEvent;
import pixelmon.client.ClientPacketHandler;
import pixelmon.client.ClientProxy;
import pixelmon.client.materials.Cubemap;
import pixelmon.client.render.RenderResources;
import pixelmon.comm.ConnectionHandler;
import pixelmon.comm.PacketHandler;
import pixelmon.comm.PixelmonPlayerTracker;
import pixelmon.commands.CommandBattle;
import pixelmon.commands.CommandFreeze;
import pixelmon.commands.CommandHeal;
import pixelmon.commands.CommandPokegive;
import pixelmon.commands.CommandSpawn;
import pixelmon.commands.CommandStruc;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonRecipes;
import pixelmon.database.DatabaseHelper;
import pixelmon.entities.EntitySpawning;
import pixelmon.entities.EntityDeath;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.projectiles.EntityHook;
import pixelmon.enums.EnumPokemon;
import pixelmon.items.ArmorToolLibrary;
import pixelmon.migration.Migration;
import pixelmon.migration.MigrationLoader;
import pixelmon.spawning.PixelmonSpawner;
import pixelmon.storage.PixelmonStorage;
import pixelmon.structure.StructureRegistry;
import pixelmon.structure.generation.ComplexScattered;
import pixelmon.structure.worldGen.WorldGenScatteredFeature;
import pixelmon.structure.StructureRegistry;
import pixelmon.structure.worldGen.WorldGenScatteredFeature;
import pixelmon.util.InfiltratorGenLayer;
import pixelmon.util.geom.Fractal;
import pixelmon.worldGeneration.MapGenDenialWrapper;
import pixelmon.worldGeneration.WorldGenApricornTrees;
import pixelmon.worldGeneration.WorldGenBauxiteOre;
import pixelmon.worldGeneration.WorldGenEvolutionRock;
import pixelmon.worldGeneration.WorldGenFireStoneOre;
import pixelmon.worldGeneration.WorldGenFossils;
import pixelmon.worldGeneration.WorldGenLeafStoneOre;
import pixelmon.worldGeneration.WorldGenThunderStoneOre;
import pixelmon.worldGeneration.WorldGenWaterStoneOre;
import pixelmon.worldGeneration.biome.BiomeGenMysteryValley;
import pixelmon.worldGeneration.mysteryDungeon.DungeonExtraTreasure;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "pixelmon", name = "Pixelmon", version = "2.5.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels = { "Pixelmon" }, packetHandler = ClientPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = { "Pixelmon" }, packetHandler = PacketHandler.class))
public class Pixelmon {

	public static EnumToolMaterial ALUMINIUM = EnumHelper.addToolMaterial("ALUMINUM", 2, 200, 6.5F, 2, 14);
	public static EnumArmorMaterial ALUMINIUMARMOR = EnumHelper.addArmorMaterial("ALUMINUM", 15, new int[] { 2, 6, 5, 2 }, 8);
	public static EnumArmorMaterial RUNNINGARMOR = EnumHelper.addArmorMaterial("RUNNING", 66, new int[] { 3, 8, 6, 3 }, 22);
	public static EnumArmorMaterial OLDRUNNINGARMOR = EnumHelper.addArmorMaterial("OLDRUNNING", 999999, new int[] { 2, 6, 5, 1 }, 13);

	@Instance("pixelmon")
	public static Pixelmon instance;
	public static Migration migration;

	public static StringTranslate stringtranslate = new StringTranslate();

	@SidedProxy(clientSide = "pixelmon.client.ClientProxy", serverSide = "pixelmon.CommonProxy")
	public static CommonProxy proxy;

	public static boolean freeze = false;
	private static boolean preInit = false, init = false, postInit = false;

	public static File modDirectory;

	Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		modDirectory = new File(event.getModConfigurationDirectory().getParent());

		if (Loader.isModLoaded("Pokemobs"))
			System.exit(1);

		MinecraftForge.EVENT_BUS.register(new ApricornBonemealEvent());
		preInit = true;
	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
		PixelmonConfig.loadConfig(config);
		proxy.registerKeyBindings();
		proxy.registerRenderers();
		proxy.registerInteractions();

		RegistryHelper.init(event, this);
		
		proxy.registerTickHandlers();
		init = true;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		PixelmonConfig.removeSpawns();
		ArmorToolLibrary.init();
		DungeonExtraTreasure.initRareItems();
		proxy.registerSounds();
		postInit = true;
	}

	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		if (MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager) {
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandSpawn());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandStruc());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandFreeze());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandHeal());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandBattle());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandPokegive());
		}
	}
	
	/**
	 * whether or not Pixelmon has finished the {@link #preInit(FMLPreInitializationEvent) preInit} phase.
	 */
	public static boolean preInitialized(){return preInit;}
	
	/**
	 * whether or not Pixelmon has finished the {@link #load(FMLInitializationEvent) load} phase.
	 */
	public static boolean initialized(){return init;}
	
	/**
	 * whether or not Pixelmon has finished the {@link #modsLoaded(FMLPostInitializationEvent) modsLoaded} phase.
	 */
	public static boolean postInitialized(){return postInit;}

}
