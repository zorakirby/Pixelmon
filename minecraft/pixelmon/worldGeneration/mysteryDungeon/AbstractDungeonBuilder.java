package pixelmon.worldGeneration.mysteryDungeon;

import java.util.Random;

import net.minecraft.world.World;

import pixelmon.worldGeneration.WorldGenMysteryDungeon;

public abstract class AbstractDungeonBuilder {
	
	
	protected WorldGenMysteryDungeon dungeon;

	public void build(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor[] floors, Random random, int x, int y, int z){
		this.dungeon = dungeon;
		//possibly modify each floor's map with an/some entrance(s).
		for(int i = 0; i < floors.length; i++){
			int y0 = y + (dungeon.up ? i*(dungeon.floorHeight+2) : -(i+1)*(dungeon.floorHeight-2));
			dungeon.entrance.modFloorMap(world, dungeon, floors[i], random, x, y0, z, i);
		}
		//build each floor.
		for(int i = 0; i < floors.length; i++){
			int y0 = y + (dungeon.up ? i*(dungeon.floorHeight+2) : -(i+1)*(dungeon.floorHeight-2));
			genFloor(world, floors[i], random, x, y0, z, i);
			dungeon.entrance.build(world, dungeon, floors[i], random, x, y0, z, i);
			if(dungeon.extras!=null)
				for(AbstractDungeonExtra extra : dungeon.extras)
					extra.genFloor(world, floors[i], random, x, y0+1, z, i);
		}
		genOthers(world, random, x, y, z);
	}
	protected abstract void genFloor(World world, MysteryDungeonFloor floor, Random random, int x, int y, int z, int floorIndex);
	protected abstract void genOthers(World world, Random random, int x, int y, int z);
}
