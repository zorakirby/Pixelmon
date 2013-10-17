package pixelmon.worldGeneration;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class GenHelperEllipse{
	public final HashSet<Point2D> points;
	final HashSet<Point2D> edgePoints;
	final int width, length;
	protected static boolean jittered = false;

	public GenHelperEllipse(int[] dims){
		this.points = new HashSet<Point2D>();
		this.edgePoints = new HashSet<Point2D>();
		this.width = dims[0];
		this.length = dims[1];
		Ellipse2D theShape = new Ellipse2D.Double(0, 0, dims[0], dims[1]);
		for(int i = 0; i < dims[0]; i++){
			for(int j = 0; j < dims[1]; j++){
				Point2D point = new Point2D.Double(i, j);
				if(theShape.contains(point)){
					this.points.add(point);
				}
			}
		}
		for(Point2D p : points){
			if(isEdgePoint(p)){
				edgePoints.add(p);
			}
		}
	}
	
	/**
	 * Determines if the specified point is on the edge of the ellipse by looking for at least one empty space next to it
	 */
	public boolean isEdgePoint(Point2D point){
		int numEmptyPoints = 0;
		for(int i = -1; i < 2; i++){
			Point2D check = new Point2D.Double(point.getX() + i, point.getY());
			if(!points.contains(check)){
				numEmptyPoints++;
			}
			check = new Point2D.Double(point.getX(), point.getY() + i);
			if(!points.contains(check)){
				numEmptyPoints++;
			}
		}
		return numEmptyPoints > 0 ? true : false;
	}

	
	public void placeHorizontallyAt(World world, Random rand, int x, int y, int z, int blockID){
		Block theBlock = null;
		try{
			theBlock = Block.blocksList[blockID];
			placeHorizontallyAt(world, rand, x, y, z, theBlock);
		}catch(Exception e){
			System.out.println("ERROR: A Block with ID \""+blockID + "\" does not exist!");
		}
	}
	
	/**
	 * Places this ellipse made of <code>block</code> Blocks centered at the specified coordinates, or removes them (sets to air) if <code>block</code> is <code>null</code>.<br>
	 * <u>Remember to call <code>setJittered(boolean)</code> to change whether or not Jittering should occur!</u>
	 */
	public void placeHorizontallyAt(World world, Random rand, int x, int y, int z, Block block){
		HashSet<Point2D> currentSet = (HashSet) points.clone();
		HashSet<Point2D> currentEdge = (HashSet) edgePoints.clone();
		if(jittered){
			int numToSubtract = (int) ((edgePoints.size() - 4) * .25);
			for(int i = 0; i < numToSubtract; i++){
				int index = (int) (rand.nextFloat() * currentEdge.size());
				Point2D removal = (Point2D) currentEdge.toArray()[index];
				currentEdge.remove(removal);
				currentSet.remove(removal);
			}
		}
		for(Point2D p : currentSet){
			int xPoint = (int) (x + p.getX());
			int zPoint = (int) (z + p.getY());
			if(block != null)
				world.setBlock(xPoint - (int)(this.width * .5), y, zPoint - (int)(this.length * .5), block.blockID);
			else
				world.destroyBlock(xPoint, y, zPoint, false);
		}
	}
	
	public static void setJittered(boolean b){
		jittered = b;
	}
}


