package pixelmon;

import java.util.logging.Level;

import pixelmon.comm.ConnectionHandler;
import pixelmon.comm.PacketHandler;
import pixelmon.config.IDListPixelmon;
import pixelmon.config.IDListTrainer;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonRecipes;
import pixelmon.database.DatabaseHelper;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.gui.GuiPixelmonOverlay;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
@Mod(modid = "Pixelmon", name = "Pixelmon", version = "4.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec = @SidedPacketHandler(channels={"Pixelmon"}, packetHandler=ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels={"Pixelmon"}, packetHandler=PacketHandler.class))

public class Pixelmon {
	@Instance
	public static Pixelmon instance;
	
	@SidedProxy(clientSide = "pixelmon.ClientProxy", serverSide = "pixelmon.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		if (!DatabaseHelper.has()) {
			ModLoader.throwException("Can not start Pixelmon without SQLite jar or database!!! Please reinstall!!", new java.lang.Error(
					"Can not start Pixelmon without SQLite jar or database!!! Please reinstall!!"));
		}
		if (!(ModLoader.isModLoaded("Forge")))
			ModLoader.throwException("Can not start Pixelmon without Minecraft Forge!!! Please download it!!!", new java.lang.Error(
					"Can not start Pixelmon without Minecraft Forge!!! Please download it!!!"));
		if (ModLoader.isModLoaded("Pokemobs"))
			System.exit(1);
		
		event.getModMetadata().version = "Pixelmon 1.6 for 1.3.1";

		PixelmonConfig.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
		PixelmonBlocks.registerBlocks();
		PixelmonBlocks.setTextureIds();
		PixelmonItems.addNames();
		
		proxy.registerKeyBindings();
		
		PixelmonEntityList.registerEntities();
		PixelmonEntityList.addSpawns();
		EntityRegistry.registerModEntity(EntityPokeBall.class, "Pokeball", IDListPixelmon.i++ , Pixelmon.instance, 80, 1, true);
		proxy.registerRenderers();
		proxy.preloadTextures();
		PixelmonRecipes.addRecipes();
		
		MinecraftForge.EVENT_BUS.register(new GuiPixelmonOverlay());
		MinecraftForge.EVENT_BUS.register(PixelmonStorage.PokeballManager);
		MinecraftForge.EVENT_BUS.register(PixelmonStorage.ComputerManager);
		MinecraftForge.EVENT_BUS.register(new SleepHandler());
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event){
		PixelmonConfig.removeSpawns();
	}
}
