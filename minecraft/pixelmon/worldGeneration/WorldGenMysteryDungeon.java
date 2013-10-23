package pixelmon.worldGeneration;

import java.util.Random;

import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.BlockHelper;
import pixelmon.util.MappedList2D;
import pixelmon.util.PixelmonDebug;
import pixelmon.worldGeneration.mysteryDungeon.AbstractDungeonBuilder;
import pixelmon.worldGeneration.mysteryDungeon.AbstractDungeonEntrance;
import pixelmon.worldGeneration.mysteryDungeon.AbstractDungeonExtra;
import pixelmon.worldGeneration.mysteryDungeon.MysteryDungeonFloor;
import pixelmon.worldGeneration.mysteryDungeon.MysteryDungeonValues;
import pixelmon.worldGeneration.mysteryDungeon.RoomMarker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

/**
 * Generates a mystery dungeon. TODO:explain how it's modular. <br>
 * linux2go [1412]: it would make sense to have boss mode only apply to Mystery Dungeons, where in boss mode, you fight a group of 4 like in the game

 * @author Jack
 *
 */
public class WorldGenMysteryDungeon extends WorldGenSpecificBiome{
	public final static int width = 65, length = 65;
	public static final AbstractList2D<Boolean> fittingShape = new MappedList2D(65, 65, true);
	//public static final RoomMarker outerLayerShape = new RoomMarker(-1,-1,67,67,0,false);
	//public static final AbstractList2D<Integer> outerLayer = outerLayerShape.getAllEdgePointsForgeDir(false);
	
	public final AbstractDungeonBuilder builder;
	public final AbstractDungeonEntrance entrance;
	public AbstractDungeonExtra[] extras;
	public final MysteryDungeonValues values;
	protected BiomeGenBase user;
	public final int numFloors;

	
	/**
	 * The distance from floor to ceiling in the sense that, for example, if it were 5:
	 * <br>
	 * {@code 5} Ceiling<br>
	 * {@code 4} Air<br>
	 * {@code 3} Air<br>
	 * {@code 2} Air<br>
	 * {@code 1} Air<br>
	 * {@code 0} Floor
	 */
	public final int floorHeight;
	public final boolean up, ladderShaftOverwrite;
	protected boolean generating = false;


	/**
	 * @param user - The Biome that is using this Mystery Dungeon generator, or null if
	 * there isn't a specific biome using this.
	 * @param width - Width for this Mystery Dungeon
	 * @param height - Height for this Mystery Dungeon
	 * @param up - Whether the Mystery Dungeon progresses upwards or downwards.

	 */
	public WorldGenMysteryDungeon(AbstractDungeonBuilder builder, AbstractDungeonEntrance entrance, MysteryDungeonValues vals, BiomeGenBase user, int floorHeight, int floors, boolean up, boolean ladderShaftOverwrite){
		super(user);
		this.builder = builder;
		this.entrance = entrance;
		this.values = vals;
		this.floorHeight = floorHeight;
		this.numFloors = floors;
		this.up = up;
		this.ladderShaftOverwrite = ladderShaftOverwrite;
	}
	
	public WorldGenMysteryDungeon setExtras(AbstractDungeonExtra... extras){
		this.extras = extras;
		return this;
	}
	
	public static AbstractList2D<Integer>[] floorMaps(int width, int length, MysteryDungeonFloor[] floors, Random random, boolean ladderShaftOverwrite){
		AbstractList2D<Integer>[] result = new AbstractList2D[floors.length];
		MysteryDungeonFloor floor = new MysteryDungeonFloor(width, length, random, ladderShaftOverwrite);
		for(int i = 0; i < floors.length; i++){
			result[i] = floor.floorMain(random, 2);
			floors[i] = floor;
			if(i+1 != floors.length)
				floor = new MysteryDungeonFloor(width, length, floor.myStairsPoint(), random, ladderShaftOverwrite);
		}
		return result;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		if(generating || (user != null && !WorldHelper.canFitWithinBiome(world, user, x, z, 0, 2, fittingShape, 0)))
			return false;
		this.generating = true;
		System.out.println("Generating Mystery Dungeon at " + PixelmonDebug.listObjs(x,y,z));
		MysteryDungeonFloor[] floors = new MysteryDungeonFloor[numFloors];
		floorMaps(width, length, floors, random, ladderShaftOverwrite);
		this.builder.build(world, this, floors, random, x, y, z);
		for(AbstractDungeonExtra extra : extras)
			extra.build(world, this, floors, random, x, y, z);
		this.generating = false;
		return true;
		
	}
	


}
