package pixelmon.worldGeneration;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Random;

import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.FunctionHelper;
import pixelmon.util.MappedList2D;
import pixelmon.util.PixelmonDebug;
import pixelmon.util.geom.ShapeHelper;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFogboundLake extends WorldGenSpecificBiome{
	private final static int HEIGHT = 40, LINK_HEIGHT=12, THICKNESS = 4;
	protected int maxBase, lakeSurfaceY;
	protected boolean generating = false;

	protected static final AbstractList2D<Boolean> fittingShape = new MappedList2D().modifyWithShape(ShapeHelper.centeredEllipse(100, 100), true);
	
	public WorldGenFogboundLake(BiomeGenBase user) {
		super(user);
	}
	
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		if(this.generating)
			return false;
		this.generating = true;
		if(!WorldHelper.canFitWithinBiome(world, user, i, j, 0, 5, fittingShape, 0)){
			this.generating = false;
			return false;
		}
		System.out.println("FogboundLake at " + PixelmonDebug.listObjs(i, j, k));
		AbstractList2D[] parts = fogboundLake(random, 2.5F, 1.5F);
/*		this.coordMode = getFitting(world, i, k, (AbstractList2D) startParts[0], 2, 0);
		if(this.coordMode == -1){
			this.generating = false;
			return false;
		}*/
		this.maxBase = 0;
		placeBase(world, i, j, k, parts[0], parts[1]);
		//placeLink(world, i, j, k, parts[1]);
		AbstractList2D<Float> lakeDirtEdge = placeLake(world, i, j, k, parts[2]);
		modifySurface(world, i, j, k, lakeDirtEdge, random);
		this.generating = false;
		return true;
	}
	
	public static AbstractList2D[] fogboundLake(Random rand, float xzScale, float heightScale){
		if(rand == null)
			rand = new Random();
		int size = (int) (40 * xzScale);
		
		Shape circle1 = ShapeHelper.warbledCircle(size, size/16, rand, null);
		Shape circle2 = ShapeHelper.warbledCircle(size*.95F, size*.95F/16, rand, null);
		Shape circle3 = ShapeHelper.warbledCircle(size*.65F, size*.65F/16, rand, null);
		Shape circle4 = ShapeHelper.warbledCircle(size*.22F, size*.22F/16, rand, null);
		
		AbstractList2D<Float> circleMap = new MappedList2D<Float>();
		
		circleMap.modifyWithShape(circle1, .04F);
		
		circleMap.modifyWithShape(circle2, .40F);
		

		
		circleMap.modifyWithShape(circle3, .63F);
		circleMap.modifyWithShape(circle4, .75F);
		circleMap = FunctionHelper.sequencedBlur(circleMap, 2, false, true);
		circleMap = FunctionHelper.sequencedBlur(circleMap, 3, true, true);
		
		circleMap = AbstractList2D.invert(circleMap, null);
		circleMap = AbstractList2D.add(circleMap, null, (float)-circleMap.minVal());
		AbstractList2D.mul(circleMap, circleMap, heightScale);
		return new AbstractList2D[]{fogboundBase(size, circle4, xzScale, heightScale, rand), fogboundLink(circle4), circleMap};
	}
	
	public static AbstractList2D fogboundBase(int size, Shape link, float xzScale, float heightScale, Random rand){
		//size*= scale;
		Shape circleBase = ShapeHelper.warbledCircle(size*.5F, size*.5F/16, rand, null);
		Shape circleMid = ShapeHelper.warbledCircle(size*.3F, size*.3F/16, rand, null);
		
		AbstractList2D<Float> circleMap = new MappedList2D<Float>();
		
		circleMap.modifyWithShape(circleBase, 0F);
		circleMap.modifyWithShape(circleMid, .1F);
		circleMap.modifyWithShape(link, .3F);
		AbstractList2D.mul(circleMap, circleMap, heightScale);
		circleMap = FunctionHelper.sequencedBlur(circleMap, 3, true, false);

		return circleMap;
	}
	
	public static AbstractList2D fogboundLink(Shape link){
		AbstractList2D<Float> result = new MappedList2D();
		result.modifyWithShape(link, 1F);
		return result;
	}
	
	protected void placeBase(World world, int x, int y, int z, AbstractList2D<Float> base, AbstractList2D link){
		for(int xi : base.xList()){
			for(int zi : base.zList(xi)){
				int heightLevel = (int) (link.containsValue(xi, zi) ? base.maxVal() * HEIGHT+LINK_HEIGHT : base.get(xi, zi)*HEIGHT);
				for(int yi = 0; yi < heightLevel; yi++){
					if(yi > this.maxBase)
						this.maxBase = yi;
					int x0 = x+xi;
					int z0 = z+zi;
					world.setBlock(x0, y+yi, z0, Block.dirt.blockID, 0, 2);
				}
			}
		}
	}
	

	
	protected void placeLink(World world, int x, int y, int z, AbstractList2D<Float> link){
		for(int xi : link.xList()){
			for(int zi : link.zList(xi)){
				int x0 = x+xi;
				int z0 = z+zi;
				int y0 = 0;
				for(int yi = LINK_HEIGHT; yi >= 0; yi--){
					y0 = y+maxBase+yi;
					world.setBlock(x0,y0,z0, Block.dirt.blockID, 0, 2);
				}
				y0--;
				while(world.getBlockId(x0, y0, z0) == 0){
					world.setBlock(x0,y0,z0, Block.dirt.blockID, 0, 2);
					y0--;
				}
			}
		}
	}
	
	protected AbstractList2D<Float> placeLake(World world, int x, int y, int z, AbstractList2D<Float> lake){
		AbstractList2D<Float> lakeDirtEdge = new MappedList2D();
		this.lakeSurfaceY = (int) (lake.maxVal()*HEIGHT);
		for(int xi : lake.xList()){
			for(int zi : lake.zList(xi)){
				int depthValue = (int) (lake.get(xi, zi)*HEIGHT);

				for(int yi = lakeSurfaceY; yi >= depthValue; yi--){
					int x0 = x+xi;
					int z0 = z+zi;
					//if point is surrounded and isn't one of the bottom 2 points, blockID = water, otherwise, dirt.
					//FIXME
					boolean useWater = shouldUseWater(lake, xi, yi, zi);
					int blockID = useWater ? Block.waterStill.blockID : Block.dirt.blockID;
					if(yi == lakeSurfaceY && !useWater)
						lakeDirtEdge.addValue(xi, zi, 1F);
					world.setBlock(x0, y+maxBase+yi-3, z0, blockID, 0, 2);
				}
			}
		}
		return lakeDirtEdge;
	}
	
	protected void modifySurface(World world, int x, int y, int z, AbstractList2D<Float> lakeDirtEdge, Random rand){
		makeWaterfalls(world, x, y, z, lakeDirtEdge, rand);
		lakeDirtEdge = FunctionHelper.sequencedBlur(lakeDirtEdge, 3, true);
		for(int xi : lakeDirtEdge.xList()){
			for(int zi : lakeDirtEdge.zList(xi)){
				int bumpHeight = (int) (lakeDirtEdge.get(xi, zi) * 7);
				for(int yi = 0; yi <= bumpHeight; yi++){
					int blockID = yi == bumpHeight ? Block.grass.blockID : Block.dirt.blockID;
					world.setBlock(x+xi, y+yi+maxBase+lakeSurfaceY-3, z+zi, blockID, 0, 2);
				}
			}
		}
	}
	
	protected void makeWaterfalls(World world, int x, int y, int z, AbstractList2D<Float> lakeDirtEdge, Random rand){
		AffineTransform rot36Deg = AffineTransform.getRotateInstance(Math.toRadians(36));
		Point2D point = new Point2D.Float(50, 0);
		
		for(int i = 0; i < 10; i++){
			Line2D intersector = new Line2D.Float(new Point2D.Float(0, 0), point);
			AbstractList2D<Float> lineAsPoints = AbstractList2D.fromLine(MappedList2D.class, intersector);
			lineAsPoints.intersect(lakeDirtEdge).expand(1, 1F);
			for(int xi : lineAsPoints.xList()){
				for(int zi : lineAsPoints.zList(xi)){
					int y0 = y+maxBase+lakeSurfaceY-3;
					int blockID = world.getBlockId(x+xi, y0, z+zi);
					if(lakeDirtEdge.containsValue(xi, zi) || blockID == 0){
						world.setBlock(x+xi, y0, z+zi, Block.waterMoving.blockID, 0, 2);
						if(isAirBelow(world, x+xi, y0, z+zi)){
							y0--;
							while(shouldReplaceWithWaterfall(world, x+xi, y0, z+zi)){
								world.setBlock(x+xi, y0, z+zi, Block.waterMoving.blockID, 0, 2);
								y0--;
							}
						}
						lakeDirtEdge.remove(xi, zi);
					}
				}
			}
			if(i != 9)
				rot36Deg.transform(point, point);
		}
	}
	
	protected boolean shouldReplaceWithWaterfall(World world, int x, int y, int z){
		int blockID = world.getBlockId(x, y, z);
		return blockID == 0 ? true : !Block.blocksList[blockID].blockMaterial.blocksMovement();
	}
	
	protected boolean isAirBelow(World world, int x, int y, int z){
		return world.getBlockId(x, y-1, z) == 0;
		
	}
	
	protected boolean shouldUseWater(AbstractList2D<Float> lake, int x, int y, int z){
		return y == lake.maxVal()*HEIGHT ? true : isLakePointSurrounded(lake, x, y, z, THICKNESS);
	}
	
	protected boolean isLakePointSurrounded(AbstractList2D<Float> lake, int x, int y, int z, int radius){
		for(int i = -radius; i <= radius; i++){
			for(int j = -radius; j <= radius; j++){
				if(!lake.containsValue(x+i, z+j))
					return false;
				int compareValue = (int) (lake.get(x+i, z+j)*HEIGHT);
				if (compareValue > y-radius)
					return false;
			}
		}
		return true;
	}
	
	public boolean isGenerating(){
		return generating;
	}

}
