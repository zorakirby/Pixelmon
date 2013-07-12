package pixelmon.worldGeneration.biome;

import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import pixelmon.entities.EntitySpawning;
import pixelmon.structure.generation.WorldStructureHelper;
import pixelmon.structure.generation.shrine.WorldStructureShrine;
import pixelmon.worldGeneration.ShapeHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.world.WorldEvent;

/**
 * <u><b>Stuff</b></u> <i>will</i> be happening here, just after I figure it out
 *
 *
 */
public class BiomeGenMysteryValley extends BiomeGenBase{
	
	public static volatile boolean stuff = true;

	public BiomeGenMysteryValley(int par1) {
		super(par1);
		MinecraftForge.EVENT_BUS.register(this);
		//this.theBiomeDecorator = this.getModdedBiomeDecorator(new BiomeDecoratorMysteryValley(this));
	}
	
	public static BiomeGenMysteryValley instance = (BiomeGenMysteryValley) new BiomeGenMysteryValley(58).setBiomeName("Shrine Falls").setMinMaxHeight(.65F, .8F);	
																																		//Default : this.minHeight = 0.1F  this.maxHeight = 0.3F;
    public int getBiomeGrassColor()
    {
        double d0 = (double)MathHelper.clamp_float(this.getFloatTemperature(), 0.0F, 1.0F);
        double d1 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
        return 0x9ec7c5;//getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(d0, d1));
    }
    
    //Temple, WITHOUT extra parts, is 67 x 32 x 80

    public void decorate(World world, Random rand, int chunkX, int chunkZ)
    {
        //super.decorate(world, rand, chunkX, chunkZ);
        //adjustHeight(world, chunkX, chunkZ);
        //boolean shouldPlace = rand.nextInt(10) == 3; //1 in 10 chance to place
    	//TODO: HOWWWWWWWWW TO DO STUFF
    	int[] baseLoc = WorldStructureHelper.getRandomSurfaceLocFromChunkCoord(world, rand, chunkX, chunkZ);
    	baseLoc[0]-=64;
    	baseLoc[2]-=64;
        if(WorldStructureShrine.isFarEnoughAway(baseLoc) && isLocationAcceptable(world, chunkX, chunkZ)){
        	System.out.println("temple at " + baseLoc[0] + ", " + baseLoc[1] + ", " + baseLoc[2]);
        	WorldStructureShrine wsshrine = new WorldStructureShrine(baseLoc);
        	wsshrine.generate(world, rand);
        }
    }
    
    
    /**
     * Determines whether or not there is an 8 x 8 set of chunks that are all of this biome's type (which, although technically not necessary, is ideal for spawning a temple building.)
     * If thought of as an 8 x 8 grid, the x-z location specified would be the chunk at (5, 5)
     */
    public boolean isLocationAcceptable(World world, int x, int z){
    	for(int i = 0; i < 8; i++){
    		for(int j = 0; j < 8; j++){
    			BiomeGenBase biome = world.getBiomeGenForCoords(x + (16 * (i - 4)), z + (16 * (j - 4)));
    			if(!(biome instanceof BiomeGenMysteryValley)){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public boolean isLocationAcceptableFromCorner(World world, int chunkX, int chunkZ, int width, int length){
    	for(int i = 0; i < width; i+=16){
    		for(int j = 0; j < length; j+=16){
    			BiomeGenBase biome = world.getBiomeGenForCoords(chunkX + i, chunkZ + j);
    			if(!(biome instanceof BiomeGenMysteryValley))
    				return false;
    		}
    	}
    	return true;
    }
    

    
    public void createBigAssValley(World world, Random rand, int chunkX, int chunkZ){
    	int width = 150;
    	int length = 150;
    	if(stuff && isLocationAcceptableFromCorner(world, chunkX, chunkZ, width, length)){
    		stuff = false;
    		System.out.println("Makin' A big-ass valley at " + chunkX + ", " + chunkZ);
    		Path2D shape = ShapeHelper.createWarbledShape(rand, width, length);
    		for(int i = 0; i < width; i++){
    			for(int j = 0; j < length; j++){
    				if(shape.contains(i, j))
    				deleteAllUntilHeight(world, chunkX + i, chunkZ + j, 37);
    			}
    		}
    	}
    }
    
    protected void deleteAllUntilHeight(World world, int x, int z, int toHeight){
    	int y = world.getTopSolidOrLiquidBlock(x, z);
    	for(; y > toHeight; y--){
    		world.setBlockToAir(x, y, z);
    	}
    }
    
    /**
	 * This is going to fail kinda hard.
	 */
	public void adjustHeight(World world, int chunkX, int chunkZ){
		//get the highest block at x-z, delete that block and all blocks from there to (20 blocks down decreased by how close this x-z coordinate is to a non-mysteryValley chunk), then place the first block there. If the block was a water block, use dirt instead.
		float[][] adjustments = createAdjustmentmap(getShapeOfNearbyOtherBiomes(world, chunkX, chunkZ));
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				int pushAmount = (int) ((1F - adjustments[i][j]) * 20);
				int blockX = chunkX + i;
				int blockZ = chunkZ + j;
				pushDown(world, blockX, blockZ, pushAmount);
			}
		}
	}
	
	public void pushDown(World world, int blockX, int blockZ, int pushAmount){
		System.out.println("Pushing the blocks at (" + blockX + ", " + blockZ + ") down by " + pushAmount);
		int blockY = world.getHeightValue(blockX, blockZ);
		for(int i = 0; i < pushAmount; i++){
			int id = world.getBlockId(blockX, blockY - i, blockZ);
			int meta = world.getBlockMetadata(blockX, blockY - i, blockZ);
			world.setBlockToAir(blockX, blockY - i, blockZ);
			world.setBlock(blockX, blockY - i - pushAmount, blockZ, id, meta, 2);
		}
	}
	
	public void experimentA(World world, Random rand, int x, int z, int width, int length){
		Path2D shape = ShapeHelper.createWarbledShape(rand, width, length);
		int o = 0;
		Rectangle2D bbox = shape.getBounds2D();
		for(int i = 0; i <bbox.getWidth(); i++){
			for(int j = 0; j < bbox.getHeight(); j++){
				if(shape.contains(i, j)){
					float weirdness = MathHelper.sin((float) (j*(7F/bbox.getHeight())));
					o++;
					float newHeight = (int) (world.getTopSolidOrLiquidBlock(x+i, z+j) + (weirdness * 10));
					for(int y = world.getTopSolidOrLiquidBlock(x+i, z+j); y < newHeight; y++){
						float fraction = y/(float)newHeight;
						int blockID = y+1 == newHeight ? Block.grass.blockID : fraction <= .5F ? Block.stone.blockID : Block.dirt.blockID;
						world.setBlock(x+i, y, z+j, blockID, 0, 2);
					}
				}
			}
		}
	}
	
	
	/**
	 * @return A 3x3 'grid' of booleans. The center would be this BiomeDecorator's chunk, and the surrounding booleans are the surrounding chunks. If the surrounding chunk's biome IS NOT BiomeGenMysteryValley, then the boolean is true. Thus, the boolean [1][1] will always be false.
	 */
	public boolean[][] getShapeOfNearbyOtherBiomes(World world, int x, int z){
		boolean[][] result = new boolean[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				BiomeGenBase biome = world.getBiomeGenForCoords(x+((i-1)*16), z+((j-1)*16));
				result[i][j] = !(biome instanceof BiomeGenMysteryValley);
			}
		}
		return result;
	}
	public float[][] createAdjustmentmap(boolean[][] nonBiomeShape){
		float[][] result = new float[16][16];
		for(float i = 0; i < 16; i++){
			for(float j = 0; j < 16; j++){
				//assuming 0,0 is bottom left
				float proxW = (15F-i)/15F;
				float proxE = 1F-proxW;
				float proxN = (15F-j)/15F;
				float proxS = 1F-proxN;
				float paramW = proxW * (nonBiomeShape[0][1] ? 1F : 0F);
				float paramE = proxE * (nonBiomeShape[2][1] ? 1F : 0F);
				float paramN = proxN * (nonBiomeShape[1][0] ? 1F : 0F);
				float paramS = proxS * (nonBiomeShape[1][2] ? 1F : 0F);
				result[(int) i][(int) j] = (paramW + paramE + paramN + paramS)/4F;
			}
		}
		return result;
	}
	
	@ForgeSubscribe
	public void onNoiseFieldEvent(ChunkProviderEvent.InitNoiseField event){
		//net.minecraft.world.gen.ChunkProviderGenerate
	}
	
	public void onReplaceBiomeBlocksEvent(ChunkProviderEvent.ReplaceBiomeBlocks event){
		//if(event.biomeArray)
	}
    
}
