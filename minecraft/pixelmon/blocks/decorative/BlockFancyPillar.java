package pixelmon.blocks.decorative;

import java.util.List;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockFancyPillar extends BlockContainerPlus{
	public static final ForgeDirection[] UP_VALS = {ForgeDirection.UP, ForgeDirection.SOUTH, ForgeDirection.EAST};
	public static final ForgeDirection[] DOWN_VALS = {ForgeDirection.DOWN, ForgeDirection.NORTH, ForgeDirection.WEST};
	public BlockFancyPillar(int id, Material mat) {
		super(id, mat);
		// TODO Auto-generated constructor stub
	}

	
	@Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list)
    {
        list.add(new ItemStack(par1, 1, 0));
        list.add(new ItemStack(par1, 1, 8));
    }
	
	 /**
     * Called when the block is placed in the world.
     */
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack){
		int baseMeta = world.getBlockMetadata(x, y, z) | stack.getItemDamage();
    		world.setBlockMetadataWithNotify(x, y, z, baseMeta, 2);
    }
	
	public boolean getIsDamaged(int meta){
		return (meta & 8) != 0;
	}
	
	public int checkForAdjacent(IBlockAccess world, int x, int y, int z, int meta){
		int direction = (meta & 7); //only the direction bytes, will be 0 - 5
		int orientation = (int)(direction * 0.5);
		if(orientation > 2){
			System.out.println("Direction is invalid; it's " + direction);
			return meta;
		}
		int result = 0;
		result |= isAnotherWithSameOrientationOnSide(world, x, y, z, meta, UP_VALS[orientation]) ? 2 : 0;
		result |= isAnotherWithSameOrientationOnSide(world, x, y, z, meta, DOWN_VALS[orientation]) ? 4 : 0;
		return result;
	}
	
	public int getConnections(IBlockAccess world, int x, int y, int z, int meta){
		int direction = meta & 7;
		int result = 0;
		result |= isAnotherWithSameOrientationOnSide(world, x, y, z, meta, DOWN_VALS[direction / 2]) ? 2 : 0;
		result |= isAnotherWithSameOrientationOnSide(world, x, y, z, meta, UP_VALS[direction / 2]) ? 4 : 0;
		return result;
	}

}
