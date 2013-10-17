package pixelmon.structure.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkDataEvent;
import pixelmon.worldGeneration.GenHelperEllipse;

public class WorldStructureHelper {

	public final static ArrayList<WorldStructure> worldStructures = new ArrayList<WorldStructure>();
	public final static WorldStructureHelper instance = new WorldStructureHelper();
	static final HashMap<int[], GenHelperEllipse> dimensionsToEllipseMqp = new HashMap();
	
	
	public static void addStructure(WorldStructure struct){
		worldStructures.add(struct);
	}
	
	public static int[] getRandomSurfaceLocFromChunkCoord(World world, Random rand, int x, int z){
		int xl = rand.nextInt(16) + x;
		int zl = rand.nextInt(16) + z;
		int yl = world.getHeightValue(xl, zl);
		return new int[]{xl, yl, zl};
	}
	
	public static void placeEllipseAt(World world, Random rand, int x, int y, int z, int width, int length, int blockID){
		placeEllipseAt(world, rand, x, y, z, getEllipse(rand, new int[]{width, length}), blockID);
	}
	
	public static void placeEllipseAt(World world, Random rand, int x, int y, int z, GenHelperEllipse ellipse, int blockID){
		ellipse.placeHorizontallyAt(world, rand, x, y, z, blockID);
	}
	
	/**
	 * @param overlaps - Whether or not to include allow <code>WorldStructures</code> literally overlapping the <code>(x, y, z)</code> location to be picked.
	 * @return The<code>WorldStructure</code> of class <code>type</code> nearest to the input <code>(x, y, z)</code> location.<br>
	 * However, if there are no <code>WorldStructures</code> of class <code>type</code> within the world, returns <code>null</code><br>
	 */
	public static WorldStructure getNearestOfType(int x, int y, int z, Class<? extends WorldStructure> type, boolean overlaps){
		WorldStructure closest = null;
		double distanceTo = -1;
		for(WorldStructure struct : worldStructures){
			if(struct.getClass().equals(type)){
				double check /*no pun intended*/ = Vec3.createVectorHelper(x, y, z).distanceTo(Vec3.createVectorHelper(struct.posX,  struct.posY,  struct.posZ));
				if((distanceTo != 0 || overlaps) && (distanceTo == -1 || check < distanceTo)){
					distanceTo = check;
					closest = struct;
				}
			}
		}
		return closest;
	}
	
	public static double getDistanceTo(WorldStructure target, int thisX, int thisY, int thisZ){
		return Vec3.createVectorHelper(target.posX, target.posY, target.posZ).distanceTo(Vec3.createVectorHelper(thisX, thisY, thisZ));
	}
	
	public static ArrayList<Chunk> getChunksInBounds(World world, StructureBoundingBox bounds){
		ArrayList<Chunk> result = new ArrayList();
		int x = bounds.minX;
		int z = bounds.minZ;
		System.out.println("loop starting: x = " + x + ", z = " + z + ", width = " + bounds.getXSize() + ", depth = " + bounds.getZSize());
		for(int cx = 0; cx < bounds.getXSize(); cx+=16){
			for(int cz = 0; cz < bounds.getZSize(); cz+=16){
				Chunk currentChunk = world.getChunkFromBlockCoords(x + cx, z + cz);
				System.out.println("CurrentChunkBiome : " + currentChunk);
				result.add(currentChunk);
			}
		}
		System.out.println("Chunk List length = " + result.size());
		return result;
	}
	
	public static GenHelperEllipse getEllipse(Random rand, int[] dims){
		if(!dimensionsToEllipseMqp.containsKey(dims)){
			dimensionsToEllipseMqp.put(dims, new GenHelperEllipse(dims));
		}
		return dimensionsToEllipseMqp.get(dims);
	}
	
/*	@ForgeSubscribe
	public void handleWorldDataSave(ChunkDataEvent event)
    {
		NBTTagCompound tagCompound = event.getData();
        NBTTagList nbttaglist = new NBTTagList("PixelmonWorldStructures");
        Iterator iterator = worldStructures.iterator();

        while (iterator.hasNext())
        {
            WorldStructure struct = (WorldStructure) iterator.next();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound("WorldStructure");
            struct.writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
        }

        tagCompound.setTag("PixelmonWorldStructures", nbttaglist);
    }*/
	
/*	@ForgeSubscribe
	public void handleWorldDataLoad(ChunkDataEvent event)
	{
		NBTTagCompound tagCompound = event.getData();
		NBTTagList structureList = tagCompound.getTagList("PixelmonWorldStructures");

		for (int i = 0; i < structureList.tagCount(); ++i)
		{
			NBTTagCompound structureData = (NBTTagCompound)structureList.tagAt(i);
			WorldStructure newStruct = null;
			try {
				Class structClass = Class.forName(structureData.getString("WorldStructureClass"));
				newStruct = (WorldStructure) structClass.newInstance();
			} catch (Exception e){
				System.out.println("Error loading WorldStructure");
				e.printStackTrace();
			}
			newStruct.readFromNBT(structureData);
		}
	}*/
}
	
	