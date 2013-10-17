package pixelmon.worldGeneration.biome;

import java.awt.geom.Ellipse2D;
import java.util.Collection;

import pixelmon.util.AbstractList2D;
import pixelmon.util.FunctionHelper;
import pixelmon.util.MappedList2D;
import pixelmon.worldGeneration.WorldGenArchPlateau;
import net.minecraft.block.Block;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorMysteryValley extends BiomeDecorator{
	
	
	public BiomeDecoratorMysteryValley(BiomeGenBase par1BiomeGenBase) {
		super(par1BiomeGenBase);
		this.generateLakes = false;
	}
	
	protected void decorate()
    {
        this.generateOres();

    }





}
