package pixelmon;

import java.util.logging.Level;

import pixelmon.comm.PacketHandler;
import pixelmon.config.IDListPixelmon;
import pixelmon.config.IDListTrainer;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonItems;
import pixelmon.database.DatabaseHelper;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.NetworkMod;
@Mod(modid = "Pixelmon", name = "Iron Chests", version = "4.0")
@NetworkMod(channels = { "IronChest" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
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
		
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		event.getModMetadata().version = "Pixelmon 1.6 for 1.3.1";
		try{
			cfg.load();
			PixelmonBlocks.load(cfg);
			IDListPixelmon.load(cfg);
			IDListTrainer.load(cfg);
			
		}catch(Exception e){
			FMLLog.log(Level.SEVERE, e, "Can't load the pixelmon configuration file");
		}finally{
			cfg.save();
		}
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		PixelmonBlocks.registerBlocks();
		PixelmonBlocks.setTextureIds();
		PixelmonItems.addNames();
		
		PixelmonEntityList.registerEntities();
		PixelmonEntityList.addSpawns();
		proxy.registerRenderers();
		proxy.preloadTextures();
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event){
		
	}
}
