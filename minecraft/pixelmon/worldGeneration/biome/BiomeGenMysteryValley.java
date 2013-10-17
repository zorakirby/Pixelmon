package pixelmon.worldGeneration.biome;

import java.awt.Color;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.config.PixelmonBlocks;
import pixelmon.database.SpawnLocation;
import pixelmon.enums.EnumTreasureRarity;
import pixelmon.spawning.spawners.SpawnRequestEvent;
import pixelmon.structure.generation.WorldStructureHelper;
import pixelmon.structure.generation.shrine.WorldStructureShrine;
import pixelmon.worldGeneration.WorldGenDragonHills;
import pixelmon.worldGeneration.WorldGenFloodDrain;
import pixelmon.worldGeneration.WorldGenFogboundLake;
import pixelmon.worldGeneration.WorldGenGoldragonHills;
import pixelmon.worldGeneration.WorldGenMysteryDungeon;
import pixelmon.worldGeneration.mysteryDungeon.DungeonBuilderStandard;
import pixelmon.worldGeneration.mysteryDungeon.DungeonEntranceStandard;
import pixelmon.worldGeneration.mysteryDungeon.DungeonExtraTreasure;
import pixelmon.worldGeneration.mysteryDungeon.MysteryDungeonValues;
import static pixelmon.enums.EnumTreasureRarity.*;

import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * <u><b>Stuff</b></u> <i>will</i> be happening here, just after I figure it out
 *
 *
 */
public class BiomeGenMysteryValley extends BiomeGenNeedsDraining{
	
	public static volatile boolean stuff = true;
	
	
	public static boolean done = false;
	
	protected static int colorSine= 0;
	protected static final int SINE_LENGTH = 120;
	protected static final Color GOLDEN_GREEN = new Color(0xDAC98F);
	protected static final float[] GOLDEN_GREEN_RGB = GOLDEN_GREEN.getColorComponents(null);
	
	protected WorldGenFogboundLake fogboundGen;
	protected WorldGenMysteryDungeon dungeonGen;
	
	//public static final BiomeGenMysteryValley instance = (BiomeGenMysteryValley) new BiomeGenMysteryValley(58).setTemperatureRainfall(0.95F, 1.0F).setMinMaxHeight(.3F, .36F);	
																														//Default : this.minHeight = 0.1F  this.maxHeight = 0.3F;
	
	public BiomeGenMysteryValley(int par1) {
		super(par1);
		this.theBiomeDecorator = new BiomeDecoratorMysteryValley(this);
		this.waterColorMultiplier = 0x00ffdc;// new Color(0F, .6F, 1F).getRGB();
		this.fogboundGen = new WorldGenFogboundLake(this);
		//this.dungeonGen = new WorldGenMysteryDungeon(new DungeonBuilderStandard(), new DungeonEntranceStandard(), MysteryDungeonValues.simpleBrick, this, 6, 6, true, false).setExtras(new DungeonExtraTreasure(0, 5, EnumTreasureRarity.values()));
	}
	
	
    public int getBiomeGrassColor()
    {
        //double d0 = (double)MathHelper.clamp_float(this.getFloatTemperature(), 0.0F, 1.0F);
        //double d1 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
        return 0x9ec7c5;//getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(d0, d1));
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return 0xe4e378;
    	//float[] rgb = GOLDEN_GREEN.getColorComponents(null);
    	//colorSine = MathHelper.clamp_int(colorSine+1, 0, SINE_LENGTH);
    	//float sineFactor = (float) FunctionHelper.sin(colorSine, SINE_LENGTH, .3F);
    	//System.out.println(sineFactor);
    	//rgb[0] = MathHelper.clamp_float(rgb[0]*(1+sineFactor), 0F, 1F);
    	//rgb[1] = MathHelper.clamp_float(rgb[1]*(1+sineFactor), 0F, 1F);
    	//return new Color(rgb[0], rgb[1], rgb[2]).getRGB();
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float temp)
    {
    	Color base = new Color(super.getSkyColorByTemp(temp));
    	float[] rgb = base.getColorComponents(null);
    	return new Color(rgb[0]*1.2F, rgb[1], rgb[2]).getRGB();
    }
    
    //Temple, WITHOUT extra parts, is 67 x 32 x 80

    public void decorate(World world, Random rand, int x, int z)
    {
    		this.dungeonGen = new WorldGenMysteryDungeon(new DungeonBuilderStandard(), new DungeonEntranceStandard(), MysteryDungeonValues.simpleBrick, this, 6, 6, true, false).setExtras(new DungeonExtraTreasure(0, 5, .35F, .75F, EnumTreasureRarity.values()));
        //super.decorate(world, rand, x, z);
        WorldGenFloodDrain drain = new WorldGenFloodDrain(this, PixelmonBlocks.templeBlockId, true, false);
        drain.generate(world, rand, x, -1, z);
        
        //fogbound(world, rand, x, z);
        //doDragon(world, rand, x, z);
    	//mysteryDungeon(world, rand, x, z);
    	System.out.println("!");
    }
    
    
    protected void mysteryDungeon(World world, Random rand, int x, int z){
    	if(!done){
        	int i = x + rand.nextInt(16);
            int j = z + rand.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(i, j);
            done = dungeonGen.generate(world, rand, i, y, j);
        	}
    }
    
    
    protected void doTemple(World world, Random rand, int chunkX, int chunkZ){
    	int[] baseLoc = WorldStructureHelper.getRandomSurfaceLocFromChunkCoord(world, rand, chunkX, chunkZ);
    	baseLoc[0]-=64;
    	baseLoc[2]-=64;

        if(WorldStructureShrine.isFarEnoughAway(baseLoc) && isLocationAcceptable(world, chunkX, chunkZ)){
        	System.out.println("temple at " + baseLoc[0] + ", " + baseLoc[1] + ", " + baseLoc[2]);
        	WorldStructureShrine wsshrine = new WorldStructureShrine(baseLoc);
        	wsshrine.generate(world, rand);
        }
    }
    
    protected void goldragon(World world, Random rand, int x, int z){
    	if(!done){
        WorldGenGoldragonHills dragon = new WorldGenGoldragonHills();
        done = dragon.generate(world, rand, x, -1, z);
    	}
    }
    
    protected void doDragon(World world, Random rand, int x, int z){
    	if(!done){
    	int i = x + rand.nextInt(16);
        int j = z + rand.nextInt(16);
        WorldGenDragonHills dragon = new WorldGenDragonHills(this);
        done = dragon.generate(world, rand, i, -1, j);
    	}
    }
    
    protected void fogbound(World world, Random rand, int x, int z){
    	if(!done){
    	int i = x + rand.nextInt(16);
        int j = z + rand.nextInt(16);
        int y = world.getTopSolidOrLiquidBlock(i, j);
        done = fogboundGen.generate(world, rand, i, y, j);
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
    	return false;//true;
    }

	@Subscribe
	public void onPixelmonSpawnRequest(SpawnRequestEvent event){
		BiomeGenBase eventBiome = event.world.getBiomeGenForCoords(event.x, event.z);
		if(eventBiome == this && event.spawn == SpawnLocation.UnderGround)
			event.approved = false;
	}


	@Override
	public void onDoneDraining(World world, Random random, int x, int y, int z) {
		mysteryDungeon(world, random, x, z);
	}

	
    
}
