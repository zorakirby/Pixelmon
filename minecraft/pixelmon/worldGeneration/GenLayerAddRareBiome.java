package pixelmon.worldGeneration;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import pixelmon.RandomHelper;
import pixelmon.config.PixelmonGen;
import pixelmon.util.AbstractList2D;
import pixelmon.util.ChancedWrapper;
import pixelmon.util.IRandomPicker;
import pixelmon.util.PixelmonDebug;
import pixelmon.util.WeightedWrapper;
import pixelmon.worldGeneration.biome.BiomeGenMysteryValley;
import pixelmon.worldGeneration.biome.BiomeGenSpecial;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public class GenLayerAddRareBiome extends GenLayer implements IRandomPicker{
	protected Random random;
	protected BiomeGenBase[] defaultBiomes;
	private static float baseChance = 1F/50;
	private static boolean debugReplacing = false;
	
	public GenLayerAddRareBiome(long seed, GenLayer parent, WorldType type) {
		super(seed);
		this.parent = parent;
		this.defaultBiomes = type.getBiomesForWorldType();
		this.random = new Random(seed);
	}
	
	public float nextFloat(){
		return ((float)this.nextInt(1000))/1000F;
	}
	
	public int getNextInt(int n){
		return this.nextInt(n);
	}

	
	@Override
	public int[] getInts(int x, int z, int width, int length)
	{
		return getIntsMain(x, z, width, length);
	}
	
	public int[] getIntsTest(int x, int z, int width, int length){
		int[] aint = this.parent.getInts(x, z, width, length);
		int[] result = IntCache.getIntCache(width*length);
			
			for(int j = 0; j < length; j++){
				for(int i = 0; i < width; i++){
					this.initChunkSeed(x+i, z+j);
					int biomeID = aint[i+j*width];
					if (this.nextInt(10) == 0){
						biomeID = PixelmonGen.mysteryValley.biomeID;
					}
					result[i + j * width] = biomeID;
				}
			}
		//PixelmonDebug.printArrayIn2D(result, width, length);
		return result;
	}
	
	public int[] getIntsMain(int x, int z, int width, int length){
		int[] aint = this.parent.getInts(x, z, width, length);
		int[] result = IntCache.getIntCache(width*length);
			
			for(int j = 0; j < length; j++){
				for(int i = 0; i < width; i++){
					this.initChunkSeed(x+i, z+j);
					int biomeID = aint[i+j*width];
					BiomeGenBase rareBiome = useRareBiome(biomeID);
					if(rareBiome != null){
						biomeID = rareBiome.biomeID;
						if(debugReplacing)
							System.out.println("Replacing " + BiomeGenBase.biomeList[aint[i+j*width]].biomeName);
					}
					result[i + j * width] = biomeID;
				}
			}
		//PixelmonDebug.printArrayIn2D(result, width, length);
		return result;
		
	}
	
	public BiomeGenBase useRareBiome(int id){
		if(!PixelmonGen.rareBiomes.containsKey(id))
			return null;
		return ChancedWrapper.weightedChoice(PixelmonGen.rareBiomes.get(id), this, true);
	}

}
