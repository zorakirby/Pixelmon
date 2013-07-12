package pixelmon.blocks.decorative;

import java.util.Random;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLog;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;


public class BlockContainerPlus extends BlockContainer{
	public static final int renderingID = RenderingRegistry.getNextAvailableRenderId();
	public DirectionType dir = DirectionType.NONE;
	protected String iconName = "quartzblock_bottom";
	public String modelName;
	protected int renderType, idDropped, amountDropped = 1;
	protected boolean opaqueCube = true, renderNormalBlock = true;
	protected Class<? extends TileEntity> tileClass = TileEntityDecorativeBase.class;
	public float invScale = 1F;
	public float[] invOffsets = {0, 0, 0};
	
	public BlockContainerPlus(int id, Material mat) {
		super(id, mat);
		this.idDropped = id;
	}
	
	public BlockContainerPlus setRenderOptions(int renderType, boolean opaqueCube, boolean renderNormal){
		this.renderType = renderType;
		this.opaqueCube = opaqueCube;
		this.renderNormalBlock = renderNormal;
		return this;
	}
	
	/**
	 * @param name - Name of the Model Class. The class itself should be located in "pixelmon.client.models", but <i><b>that should not be included</i></b> in the name.
	 * @return
	 */
	public BlockContainerPlus setModelName(String name){
		this.modelName = name;
		return this;
	}
	public BlockContainerPlus setIdDropped(int id){
		this.idDropped = id;
		return this;
	}
	public BlockContainerPlus setAmountDropped(int amount){
		this.amountDropped = amount;
		return this;
	}
	public BlockContainerPlus setIconName(String name){
		this.iconName = name;
		return this;
	}
	
	/**
	 * Sets the scale factor the model will use when rendering itself in the inventory. The scale value is '1' by default, but this should be changed should the block be too big to fit the square, or too small to see. Particularly large blocks (like, the size of the Trade Machine) would <i>probably</i> be better off using a sprite for the inventory.
	 */
	public BlockContainerPlus setInventoryScale(float scale){
		this.invScale = scale;
		return this;
	}
	
	
	/**
	 * sets the offset the model will use when rendering itself in the inventory. This may be useful if you want the model to fit the player's hands in third-person better, should the model itself be oriented to the side or up a little too high.
	 */
	public BlockContainerPlus setInventoryOffsets(float x, float y, float z){
		this.invOffsets = new float[] {x, y, z};
		return this;
	}
	
	public BlockContainerPlus setTileEntityClass(Class<? extends TileEntity> tileClass){
		this.tileClass = tileClass;
		return this;
	}
	/**
	 * sets the direction mode for this block.
	 * @See {@link DirectionType#values()}
	 */
	public BlockContainerPlus setDirectionalType(DirectionType type){
		this.dir = type;
		return this;
	}
	
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.idDropped;
	}

	@Override
	public int quantityDropped(Random random) {
		return this.amountDropped;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return this.opaqueCube;
	}

	@Override
	public boolean renderAsNormalBlock(){
		return this.renderNormalBlock;
	}

	@Override
	public int getRenderType(){
		return renderingID;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon(iconName);
	}
	
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
    	int newMeta = meta;
    	switch(this.dir){
		case NONE: break;
		case YAW: break; //both return the same metadata, but later on, with the rendering, if this block has YAW as its type, it will actually rotate it, as opposed to NONE, which will ignore metadata.
		case STAIRS: newMeta = doStairsDirectional(side, hitY, meta); break;
		case WOODLOG: newMeta = doWoodLogDirectional(side, meta); break;
		case SIXAXIS: newMeta = side;
		case THREEAXIS: newMeta = side;
			break;
    	}
    	System.out.println("Metadata : " + newMeta);
		return newMeta;
    }
    
    
    /**
     * copied from <code>BlockStairs.onBlockPlaced</code>
     */
    public int doStairsDirectional(int side, float hitY, int meta)
    {
        int result = side != 0 && (side == 1 || (double)hitY <= 0.5D) ? meta : meta | 4;
        return result;
    }
    
    
    /**
     * copied from <code>BlockLog.onBlockPlaced</code>
     */
    public int doWoodLogDirectional(int side, int meta){
    	 int j1 = meta & 3;
         byte b0 = 0;

         switch (side)
         {
             case 0:
             case 1:
                 b0 = 0;
                 break;
             case 2:
             case 3:
                 b0 = 8;
                 break;
             case 4:
             case 5:
                 b0 = 4;
         }

         int result = j1 | b0;
         System.out.println("MetaData<woodLogDirectionType> = " + result);
         return result;
    }
    
	@SuppressWarnings("incomplete-switch")
	public boolean isAnotherWithSameOrientationOnSide(IBlockAccess world, int x, int y, int z, int metaData, ForgeDirection dir){
		int[] coords = null;
		switch(dir){
		case DOWN: coords = new int[]{x, y-1, z};
			break;
		case EAST: coords = new int[]{x+1, y, z};
			break; 
		case NORTH: coords = new int[]{x, y, z-1};
			break;
		case SOUTH: coords = new int[]{x, y, z + 1};
			break;
		case UP: coords = new int[]{x, y + 1, z};
			break;
		case WEST: coords = new int[]{x - 1, y, z};
			break;
		}
		int blockID = world.getBlockId(coords[0], coords[1], coords[2]);
		int metaData2 = world.getBlockMetadata(coords[0], coords[1], coords[2]);
		return blockID > 0 ? isSameOrientationAndType(Block.blocksList[blockID].getClass(), metaData, metaData2) : false;
	}
	public boolean isSameOrientationAndType(Class<? extends Block> blockClass, int thisMeta, int thatMeta){
		boolean flag = false;
		switch(this.dir){
		case SIXAXIS: flag = (int)((thisMeta & 7) / 2) == (int)((thatMeta & 7) / 2);
			break;
		case THREEAXIS: flag = (int)((thisMeta & 7) / 2) == (int)((thatMeta & 7) / 2);
			break;
		case NONE: flag = true;;
			break;
		case STAIRS: //TODO
			break;
		case WOODLOG: flag = true;
			break;
		case YAW: flag = true;
			break;
		default:
			break;
		}
		return blockClass == this.getClass() && flag;
	}
    
	public static enum DirectionType {
		NONE, YAW, WOODLOG, STAIRS, SIXAXIS, THREEAXIS;
		//WOODLOG: north/south = 8, west/east = 4, up/down = 0
		//ALLSIX : ceiling = 0, floor = 1, southwall = 2, northwall = 3, eastwall = 4, westwall = 5
		//THREEAXIS is the same as SIXAXIS, but only 3 different rotation settings (none, 90 X, 90 Z)
	}
	


	@Override
	public TileEntity createNewTileEntity(World world) {
		try {
			return tileClass.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static int rotate(int coordbaseMode, Block block, int meta){
		//TODO figure out the actual answer
		return meta;
	}


}
