package pixelmon.structure.generation.shrine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

import pixelmon.config.PixelmonBlocks;
import pixelmon.structure.SchematicImporter;
import pixelmon.structure.generation.ComplexScattered;
import pixelmon.structure.generation.WorldStructure;
import pixelmon.structure.generation.WorldStructureHelper;


public class WorldStructureShrine extends WorldStructure{
	
	private static final int MIN_DISTANCE_AWAY = 2000;


	public WorldStructureShrine(int[] loc){
		super(loc);
	}
	

	
	public static boolean isFarEnoughAway(int[] loc){
		WorldStructure nearest = WorldStructureHelper.getNearestOfType(loc[0], loc[1], loc[2], WorldStructureShrine.class, true);
		return nearest == null ? true : WorldStructureHelper.getDistanceTo(nearest, loc[0], loc[1], loc[2]) >= MIN_DISTANCE_AWAY;
	}
	
	
	
	public boolean generate(World world, Random rand)
    {
		ComplexScattered temple = new ComplexScattered(rand, posX, posY, posZ, "temple");
    	temple.generate(world, rand);
		return true;
    }
	
	

	

}
