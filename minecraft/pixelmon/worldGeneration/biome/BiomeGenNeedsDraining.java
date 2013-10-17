package pixelmon.worldGeneration.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class BiomeGenNeedsDraining extends BiomeGenBase{
	
	public BiomeGenNeedsDraining(int par1) {
		super(par1);
	}

	public abstract void onDoneDraining(World world, Random random, int x, int y, int z);

}
