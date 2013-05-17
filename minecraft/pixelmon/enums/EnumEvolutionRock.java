package pixelmon.enums;

import net.minecraft.world.biome.BiomeGenBase;

public enum EnumEvolutionRock {
	MossyRock(new BiomeGenBase[]{ BiomeGenBase.forest, BiomeGenBase.forestHills}), 
	IcyRock(new BiomeGenBase[]{ BiomeGenBase.icePlains, BiomeGenBase.iceMountains});

	public BiomeGenBase[] biomes;

	private EnumEvolutionRock(BiomeGenBase[] biomes) {
		this.biomes = biomes;
	}

	public static boolean isEvolutionRock(String string) {
		for (EnumEvolutionRock rock: values()){
			if (string.equalsIgnoreCase(rock.toString()))
				return true;
		}
		return false;
	}

	public static EnumEvolutionRock getEvolutionRock(String string) {
		for (EnumEvolutionRock rock: values()){
			if (string.equalsIgnoreCase(rock.toString()))
				return rock;
		}
		return null;
	}
}
