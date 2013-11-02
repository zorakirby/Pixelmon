package pixelmon.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import pixelmon.spawning.spawners.SpawnerBase;
import pixelmon.util.ChancedWrapper;
import pixelmon.util.InfiltratorGenLayer;
import pixelmon.util.WeightedWrapper;
import pixelmon.util.geom.Fractal;
import pixelmon.worldGeneration.MapGenDenialWrapper;
import pixelmon.worldGeneration.biome.BiomeGenMysteryValley;
import static net.minecraft.world.biome.BiomeGenBase.*;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;

public class PixelmonGen {
	
	public static TreeMap<Integer, Collection<ChancedWrapper<BiomeGenBase>>> rareBiomes = new TreeMap();
	
	public static int mysteryValleyId;
	
	public static boolean mysteryValleyTryout = false;
	
	public static BiomeGenBase mysteryValley;//= new BiomeGenMysteryValley(58).setBiomeName("Mystery Valley").setTemperatureRainfall(0.95F, 1.0F).setMinMaxHeight(-.9F, .1F);
	
	
	/**
	 * load biome IDs from config file and initialize them.<br>
	 */
	public static void load(Configuration configuration) {
		if(!mysteryValleyTryout)
			return;
		Fractal.preloadFractals();
		//IDs
		mysteryValleyId = loadBiomeID(configuration, "Mystery Valley", 23);
		
		//Biomes
		mysteryValley = new BiomeGenMysteryValley(mysteryValleyId).setBiomeName("Mystery Valley").setTemperatureRainfall(0.95F, 1.0F).setMinMaxHeight(-.9F, .1F);
		register();
	}
	
	public static void register(){
		
		//Dictionary
		BiomeDictionary.registerBiomeType(mysteryValley, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MAGICAL );
		
		//Registration
		registerRareBiome(mysteryValley, 0.5F, forest, swampland);
		//registerBiomeForDenial(mysteryValley, CAVE, MINESHAFT, RAVINE);
		SpawnerBase.register(mysteryValley);
		
		MinecraftForge.TERRAIN_GEN_BUS.register(InfiltratorGenLayer.INSTANCE);
	}
	
	private static int loadBiomeID(Configuration configuration, String key, int defaultValue){
		int result = configuration.get("Biomes", key, defaultValue).getInt(defaultValue);
		if(result > 255 || result < 23)
			throw new IllegalArgumentException("Error Initializing Biome: " + key + "! Expected a Biome ID between 23 & 255 (inclusive), but instead got '" + result + "'! Check the \"Biomes\" section in \"Pixelmon.cfg\" to fix this.");
		return result;
	}
	
	/**
	 * @param biome : The Biome you want to be registered for use in GenLayerAddRareBiome
	 * @param percentageChance : likelyhood for biome to be chosen. Likelyhood ranges from 0F to 1F.
	 * @param replaceThese : Biomes that <code>biome</code> is allowed a chance to 
	 * replace.<br>For the time being, it is advised against replacing 
	 * <code>extremeHills</code> or <code>extremeHillsEdge</code> as this causes the 
	 * other genLayers to act strangely and ultimately generate weird, unsightly 
	 * extremeHills.
	 * <br><br>
	 * How does it work?<br>
	 * When requesting biomes for a chunk, first, an array of integers is generated
	 * representing a 2D values-to-BiomeId values. Then, GenLayerAddRareBiome 
	 * takes these, and looks through each value, randomly replacing these values 
	 * with rare biome ids.
	 */
	public static void registerRareBiome(BiomeGenBase biome, float percentageChance, BiomeGenBase... replaceThese){
		for(int i = 0; i < replaceThese.length; i++){
			BiomeGenBase replace = replaceThese[i];
			if(rareBiomes.get(replace.biomeID) == null)
				rareBiomes.put(replace.biomeID, new HashSet());
			rareBiomes.get(replace.biomeID).add(new ChancedWrapper<BiomeGenBase>(biome, percentageChance));
		}
	}
	
	
	/**
	 * Shorthand for <code>MapGenDenialWrapper.registerBiomeForDenial(BiomeGenBase, EventType...)</code>.
	 */
//	public static void registerBiomeForDenial(BiomeGenBase biome, EventType... types){
//		MapGenDenialWrapper.registerBiomeForDenial(biome, types);
//	}
	


}
