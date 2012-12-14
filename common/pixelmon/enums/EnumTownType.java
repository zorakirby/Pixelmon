package pixelmon.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.Item;

public enum EnumTownType {
	
	basicTown(0, 5, BiomeGenBase.plains, Block.cobblestone.blockID, "resources/pixelmon/structures/basicTown/"),
	desertTown(1, 5, BiomeGenBase.desert, Block.sandStone.blockID, "resources/pixelmon/structures/desertTown/"),
	snowTown(2, 5, BiomeGenBase.icePlains, Block.stoneBrick.blockID, "resources/pixelmon/structures/snowTown/");
	
	public int townId;
	public int rarity;
	public BiomeGenBase biome;
	public int pathId;
	public String folderPath;
	
	private EnumTownType(int townId, int rarity, BiomeGenBase biome, int pathBlockId, String folderPath){
		this.townId = townId;
		this.rarity = rarity;
		this.biome = biome;
		this.pathId = pathBlockId;
		this.folderPath = folderPath;
	}
	
	public int getId(){
		return townId;
	}
	
	public int getRarity(){
		return rarity;
	}
	
	public BiomeGenBase getBiome(){
		return biome;
	}
	
	public int getPathId(){
		return pathId;
	}
	
	public static EnumTownType getTownFromBiome(BiomeGenBase biome) {
		for (EnumTownType e: values()){
			if (e.getBiome() == biome) return e;
		}
		return null;
	}
	
	public static EnumTownType getTownFromId(int Id) {
		for (EnumTownType e: values()){
			if (e.getId() == Id) return e;
		}
		return null;
	}
}
	
