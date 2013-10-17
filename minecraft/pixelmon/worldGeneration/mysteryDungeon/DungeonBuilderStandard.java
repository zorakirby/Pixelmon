package pixelmon.worldGeneration.mysteryDungeon;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.worldGeneration.WorldGenMysteryDungeon;

/**
 * Simple, cuboid-room-style builder. 
 * Looks at a {@link MysteryDungeonFloor#floorMain(Random, int) floor's values} and builds
 * it, without doing anything special.
 * 
 * @author Jack
 *
 */
public class DungeonBuilderStandard extends AbstractDungeonBuilder{
	
	
	public DungeonBuilderStandard(){}
	

	
	protected void genFloor(World world, MysteryDungeonFloor floor, Random random, int x, int y, int z, int floorIndex) {
		for(int i : floor.theMap.xList()){
			for(int j : floor.theMap.zList(i)){
					int[] danger = MysteryDungeonFloor.isDangerBlock(i, j) ? new int[]{Block.bedrock.blockID, 0} : new int[]{dungeon.values.wallID, dungeon.values.wallMeta} ;
					world.setBlock(x+i, y-1, z+j, danger[0], danger[1], 2);
					genStripAt(world, x+i, y, z+j, floor.theMap.get(i, j), floorIndex);
			}
		}
		genLadder(world, floor, x, y, z, floorIndex);
	}
	



	@Override
	protected void genOthers(World world, Random random, int x, int y, int z) {
		if(this.dungeon.extras!= null)
			for(AbstractDungeonExtra extra : this.dungeon.extras)
				extra.genOthers(world, random, x, y, z);
		genRoofAndFoundation(world, x, y, z);
	}
	
	protected void genStripAt(World world, int x, int y, int z, int id, int floorIndex){
		for(int n = 0; n <= dungeon.floorHeight; n++){
			switch(id){
			case MysteryDungeonFloor.ID_WALL : {
				world.setBlock(x, y+n, z, dungeon.values.wallID, dungeon.values.wallMeta, 2);
				break;
			}
			case MysteryDungeonFloor.ID_ROOM : 
			case MysteryDungeonFloor.ID_HALL :{
				hollowPart(world, x, y, z, n);
				break;
			}
			case MysteryDungeonFloor.ID_LADDERSIDE:{
				if(floorIndex + 1 == dungeon.numFloors){
					hollowPart(world,x,y,z,n);
				}
				else{
					if(n == 0)
						world.setBlock(x, y, z, dungeon.values.floorID, dungeon.values.floorMeta, 2);
					else if (n == dungeon.floorHeight)
						world.setBlock(x, y+n, z, dungeon.values.ceilingID, dungeon.values.ceilingMeta, 2);
					else
						world.setBlock(x, y+n, z, dungeon.values.shaftID, dungeon.values.shaftMeta, 2);
				}
				break;
			}
			case MysteryDungeonFloor.ID_ANTICHEAT:{
				world.setBlock(x, y+n, z, Block.bedrock.blockID, 0, 2);
				break;
			}
			}
		}
	}
	
	private void hollowPart(World world, int x, int y, int z, int n){
		if(n == 0)
			world.setBlock(x, y, z, dungeon.values.floorID, dungeon.values.floorMeta, 2);
		else if (n == dungeon.floorHeight)
			world.setBlock(x, y+n, z, dungeon.values.ceilingID, dungeon.values.ceilingMeta, 2);
		else
			world.setBlockToAir(x, y+n, z);
	}
	
	private void genLadder(World world, MysteryDungeonFloor floor, int x, int y, int z, int floorIndex){
		int[] ladder = floor.myStairsPoint();
		
		int x0 = x + ladder[0];
		int z0 = z + ladder[1];
		if(floorIndex == 0)
			System.out.println("ladder goes at " + x0 + ", " + z0);
		int ladderMeta = ladder[2];
		int[] ensuredLadder = floor.ensureStairsPoint();
		//whether or not this floor's ladder point is the same as the previous floor's ladder point
		boolean stackedLadder = ensuredLadder != null && ensuredLadder[0] == ladder[0] && ensuredLadder[1] == ladder[1];
		for(int n = 0; n <= dungeon.floorHeight; n++){
			if(n == 0){
				if(!stackedLadder)
					world.setBlock(x0, y, z0, dungeon.values.floorID, dungeon.values.floorMeta, 2);
			}
			else{
				if(floorIndex+1 == dungeon.numFloors){
					if(n == dungeon.floorHeight)
						world.setBlock(x0, y+n, z0, dungeon.values.ceilingID, dungeon.values.ceilingMeta, 2);
					else
						world.setBlockToAir(x0, y+n, z0);
				}
				else
					world.setBlock(x0, y+n, z0, Block.ladder.blockID, ladderMeta, 2);
			}
			//the previous floor's ladder
			if(ensuredLadder != null){
				if(n == 0){
					world.setBlock(x+ensuredLadder[0], y, z+ensuredLadder[1], Block.ladder.blockID,  ensuredLadder[2], 2);
					world.setBlock(x+ensuredLadder[0],y-1, z+ensuredLadder[1], Block.ladder.blockID, ensuredLadder[2], 2);
					for(ForgeDirection dir : WorldHelper.NWSE)
						world.setBlock(x+ensuredLadder[0]+dir.offsetX, y-1, z+ensuredLadder[1]+dir.offsetZ, dungeon.values.wallID, dungeon.values.wallMeta, 2);
				}
				else if(n==dungeon.floorHeight){
					if(!stackedLadder)
						world.setBlock(x+ensuredLadder[0], y+dungeon.floorHeight, z+ensuredLadder[1], dungeon.values.ceilingID, dungeon.values.ceilingMeta, 2);
				}
				else{
					if(!stackedLadder)
						world.setBlockToAir(x+ensuredLadder[0], y+n, z+ensuredLadder[1]);
				}
			}
		}
		if(ensuredLadder != null){
			world.setBlock(x+ensuredLadder[0], y, z+ensuredLadder[1], Block.ladder.blockID, ensuredLadder[2], 2);
			if(!stackedLadder)
				world.setBlock(x+ensuredLadder[0], y+dungeon.floorHeight, z+ensuredLadder[1], dungeon.values.ceilingID, dungeon.values.ceilingMeta, 2);
		}
	}
	
	
	private void genDangerStripAt(World world, int x, int y, int z){
		for(int n = 0; n <= dungeon.floorHeight; n++){
			world.setBlock(x, y+n, z, Block.bedrock.blockID, 0, 2);
		}
	}
	
	private void genRoofAndFoundation(World world, int x, int y, int z){
		int ry = y + dungeon.numFloors*(dungeon.floorHeight+2) - 1;
		int fy = y-1;
		for(int i : MysteryDungeonFloor.outerLayer.xList()){
			for(int j : MysteryDungeonFloor.outerLayer.zList(i)){
				world.setBlock(x+i, ry, z+j, Block.bedrock.blockID, 0, 2);
				world.setBlock(x+i, fy, z+j, Block.bedrock.blockID, 0, 2);
			}
		}
		for(int i = 0; i <= dungeon.width; i++){
			for(int j = 0; j <= dungeon.length; j++){
				world.setBlock(x+i, ry, z+j, Block.bedrock.blockID, 0, 2);
				world.setBlock(x+i, fy, z+j, Block.bedrock.blockID, 0, 2);
			}
		}
	}


}
