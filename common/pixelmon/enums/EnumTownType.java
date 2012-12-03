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
		try {
		for (Field field : EnumTownType.class.getFields()) {
			EnumTownType town;
				town = (EnumTownType) field.get(1);// May need to work on this stuff,
			if (town.getBiome() == biome)
				return town;
			}
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static EnumTownType getTownFromId(int Id) {
		try {
			for (Field field : EnumTownType.class.getFields()) {
				EnumTownType town = (EnumTownType) field.get(null);
					if (town.getId() == Id)
						return town;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
	
