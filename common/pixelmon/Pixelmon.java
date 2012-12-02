package pixelmon;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.logging.Level;

import pixelmon.blocks.apricornTrees.ApricornBonemealEvent;
import pixelmon.comm.ConnectionHandler;
import pixelmon.comm.PacketHandler;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonRecipes;
import pixelmon.database.DatabaseHelper;
import pixelmon.entities.EntityCamera;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.migration.Migration;
import pixelmon.migration.MigrationLoader;
import pixelmon.pokedex.Pokedex;
import pixelmon.spawning.ChunkDataEvents;
import pixelmon.spawning.PixelmonSpawner;
import pixelmon.spawning.PixelmonWaterSpawner;
import pixelmon.storage.PixelmonStorage;
import pixelmon.structure.GeneralScattered;
import pixelmon.structure.SchematicImporter;
import pixelmon.structure.WorldGenScatteredFeature;
import pixelmon.worldGeneration.WorldGenApricornTrees;
import pixelmon.worldGeneration.WorldGenFireStoneOre;
import pixelmon.worldGeneration.WorldGenFossils;
import pixelmon.worldGeneration.WorldGenLeafStoneOre;
import pixelmon.worldGeneration.WorldGenThunderStoneOre;
import pixelmon.worldGeneration.WorldGenWaterStoneOre;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ServerCommandManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkDataEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

@Mod(modid = "Pixelmon", name = "Pixelmon", version = "1.8.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels = { "Pixelmon" }, packetHandler = ClientPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = { "Pixelmon" }, packetHandler = PacketHandler.class))
public class Pixelmon {
	@Instance
	public static Pixelmon instance;
	public static Migration migration;

	@SidedProxy(clientSide = "pixelmon.ClientProxy", serverSide = "pixelmon.CommonProxy")
	public static CommonProxy proxy;

	public static boolean freeze = false;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		if (!DatabaseHelper.has()) {
			throw new RuntimeException("Can not start Pixelmon without SQLite jar or database!!! Please reinstall!!");
		}
		if (Loader.isModLoaded("Pokemobs"))
			System.exit(1);

		event.getModMetadata().version = "Pixelmon 1.8.4 for 1.4.4";

		MinecraftForge.EVENT_BUS.register(new ApricornBonemealEvent());
		
		PixelmonConfig.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
		Pokedex.init();
	}

	@Init
	public void load(FMLInitializationEvent event) {
		Random rand = new Random();
		
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		proxy.registerKeyBindings();
		proxy.registerRenderers();
		proxy.preloadTextures();
		proxy.registerSounds();
		PixelmonRecipes.addRecipes();
		EntityRegistry.registerModEntity(EntityPokeBall.class, "Pokeball", PixelmonConfig.idPokeball, Pixelmon.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityCamera.class, "Camera", PixelmonConfig.idCamera, Pixelmon.instance, 80, 1, true);
		
		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());

		GameRegistry.registerWorldGenerator(new WorldGenLeafStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenWaterStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenThunderStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenFireStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenApricornTrees());
		GameRegistry.registerWorldGenerator(new WorldGenFossils());
		
		GameRegistry.registerWorldGenerator(new WorldGenScatteredFeature());

		MinecraftForge.EVENT_BUS.register(new ChunkDataEvents());
		MinecraftForge.EVENT_BUS.register(new PixelmonSpawner());
		MinecraftForge.EVENT_BUS.register(new MigrationLoader());
		MinecraftForge.EVENT_BUS.register(PixelmonStorage.PokeballManager);
		MinecraftForge.EVENT_BUS.register(PixelmonStorage.ComputerManager);

		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new SleepHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new PixelmonWaterSpawner(), Side.SERVER);
		proxy.registerTickHandlers();
		
//		SchematicImporter s = new SchematicImporter("resources/pixelmon/structures/standAlone/Mansion.schematic");
//		s.readSchematic();
		//GameRegistry.registerWorldGenerator(new GeneralScattered(rand, s.width, s.width, s));
	}

	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event) {
		PixelmonConfig.removeSpawns();
	}

	@ServerStarting
	public void onServerStart(FMLServerStartingEvent event) {
		if (MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager) {
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandSpawn());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandFreeze());
		}
	}

}
