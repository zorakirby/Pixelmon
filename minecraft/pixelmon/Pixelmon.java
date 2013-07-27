package pixelmon;

import java.io.File;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StringTranslate;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import pixelmon.battles.BattleTickHandler;
import pixelmon.blocks.apricornTrees.ApricornBonemealEvent;
import pixelmon.client.ClientPacketHandler;
import pixelmon.client.ClientProxy;
import pixelmon.comm.ConnectionHandler;
import pixelmon.comm.PacketHandler;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonRecipes;
import pixelmon.database.DatabaseHelper;
import pixelmon.entities.EntitySpawning;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.projectiles.EntityHook;
import pixelmon.migration.Migration;
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

@Mod(modid = "pixelmon", name = "Pixelmon", version = "2.3")
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

	public static File modDirectory;

	Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		boolean checkForDatabaseUpdates = config.get("general", "Check for database updates", true).getBoolean(true);
		modDirectory = new File(event.getModConfigurationDirectory().getParent());
		if (!DatabaseHelper.has(checkForDatabaseUpdates)) {
			throw new RuntimeException("Can not start Pixelmon without SQLite jar or database!!! Please reinstall!!");
		}
		if (Loader.isModLoaded("Pokemobs"))
			System.exit(1);

		MinecraftForge.EVENT_BUS.register(new ApricornBonemealEvent());

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		PixelmonConfig.loadConfig(config);

		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		proxy.registerKeyBindings();
		proxy.registerRenderers();
		proxy.registerInteractions();
		PixelmonRecipes.addRecipes();
		EntityRegistry.registerModEntity(EntityPokeBall.class, "Pokeball", PixelmonConfig.idPokeball, Pixelmon.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityHook.class, "Hook", 216, this, 75, 1, true);

		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());

		GameRegistry.registerWorldGenerator(new WorldGenLeafStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenWaterStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenThunderStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenFireStoneOre());
		GameRegistry.registerWorldGenerator(new WorldGenApricornTrees());
		GameRegistry.registerWorldGenerator(new WorldGenBauxiteOre());
		GameRegistry.registerWorldGenerator(new WorldGenFossils());
		GameRegistry.registerWorldGenerator(new WorldGenEvolutionRock());

		StructureRegistry.loadStructures(event.getSide());

		GameRegistry.registerWorldGenerator(new WorldGenScatteredFeature());

		MinecraftForge.EVENT_BUS.register(PixelmonStorage.PokeballManager);
		MinecraftForge.EVENT_BUS.register(PixelmonStorage.ComputerManager);
		MinecraftForge.EVENT_BUS.register(new EntitySpawning());

		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new SleepHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new PixelmonSpawner(), Side.SERVER);
		TickRegistry.registerTickHandler(new BattleTickHandler(), Side.SERVER);
		
		proxy.registerTickHandlers();
	}

	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent event) {
		PixelmonConfig.removeSpawns();
		proxy.registerSounds();
	}

	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		if (MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager) {
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandSpawn());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandStruc());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandFreeze());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandHeal());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new CommandBattle());
		}
	}

}
