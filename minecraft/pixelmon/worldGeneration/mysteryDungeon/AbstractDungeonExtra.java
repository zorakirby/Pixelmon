package pixelmon.worldGeneration.mysteryDungeon;

import java.util.Random;

import net.minecraft.world.World;
import pixelmon.worldGeneration.WorldGenMysteryDungeon;

public abstract class AbstractDungeonExtra extends AbstractDungeonBuilder{
	
	@Override
	public void build(World world, WorldGenMysteryDungeon dungeon, MysteryDungeonFloor[] floors, Random random, int x, int y, int z){
		//decorate each floor.
		this.dungeon = dungeon;
		for(int i = 0; i < floors.length; i++){
			int y0 = y + (dungeon.up ? i*(dungeon.floorHeight+2) : -(i+1)*(dungeon.floorHeight-2));
			genFloor(world, floors[i], random, x, y0, z, i);
		}
		genOthers(world, random, x, y, z);
	}

}
