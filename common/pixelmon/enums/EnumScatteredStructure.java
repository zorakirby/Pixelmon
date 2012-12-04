package pixelmon.enums;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.src.BiomeGenBase;

public enum EnumScatteredStructure {
	
	mansion(0, 55, 4, BiomeGenBase.swampland, "mansion"),
	gymRed(1, 10, 2, BiomeGenBase.plains, "gymRed"),
	moltresSpawn(2, 35, 1, BiomeGenBase.forest, "moltresSpawn", "Pikachu"/*Just a place holder.*/, 57, 38, 54),
	zapdosSpawn(3, 30, 0, BiomeGenBase.extremeHills, "zapdosSpawn", "Pikachu"/*Just a place holder. again.*/, 5, 2, 5),
	;

	public int structureId;
	public int rarity;
	public BiomeGenBase biome;
	public String schematicName;
	public boolean spawnPokemon = false;
	public String pokemonSpawn;
	public int pokemonX;
	public int pokemonZ;
	public int pokemonY;
	public int depthInGround;
	
	private EnumScatteredStructure(int structureId, int rarity, int depthInGround, BiomeGenBase biome, String schematicName){
		this.structureId = structureId;
		this.rarity = rarity;
		this.depthInGround = depthInGround;
		this.biome = biome;
		this.schematicName = schematicName;
	}
	
	private EnumScatteredStructure(int structureId, int rarity, int depthInGround, BiomeGenBase biome, String schematicName, String pokemonSpawn, int pokemonX, int pokemonY, int pokemonZ){
		this.structureId = structureId;
		this.rarity = rarity;
		this.depthInGround = depthInGround;
		this.biome = biome;
		this.schematicName = schematicName;
		this.spawnPokemon = true;
		this.pokemonSpawn = pokemonSpawn;
		this.pokemonX = pokemonX;
		this.pokemonY = pokemonY;
		this.pokemonZ = pokemonZ;
	}
	
	public int getRarity(){
		return new Random().nextInt(this.rarity*10);
	}
	
	public int getY(int par1){
		return par1 - depthInGround;
	}
	
	public BiomeGenBase biomeToSpawnIn(){
		return this.biome;
	}
	
	public String getSchematicPath(){
		return "resources/pixelmon/structures/standAlone/" + this.schematicName + ".schematic";
	}
	
	public static EnumScatteredStructure getStructureFromBiome(BiomeGenBase biome) {
		for (EnumScatteredStructure e: values()){
			if (e.biomeToSpawnIn() == biome) return e;
		}
		return null;
	}
	
}
