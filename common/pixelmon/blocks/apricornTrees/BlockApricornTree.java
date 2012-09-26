package pixelmon.blocks.apricornTrees;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import pixelmon.Pixelmon;
import pixelmon.blocks.TileEntityHealer;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumApricornTrees;
import pixelmon.enums.EnumGui;
import pixelmon.items.ItemApricorn;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockApricornTree extends BlockContainer {
	public static final int numStages = 6;
	private Random rand = new Random();
	public EnumApricornTrees tree;

	public BlockApricornTree(int id, EnumApricornTrees tree) {
		super(id, Material.wood);
		this.tree = tree;
		setTickRandomly(true);
		setBlockBounds();
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Block.wood.blockID;
	}

	@Override
	public int quantityDropped(Random random) {
		return rand.nextInt(3) + 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBounds();
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBounds();
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	public void setBlockBounds() {
		this.setBlockBounds(0, 0, 0, 1, 2, 1);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityApricornTree(tree);
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta) {
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (world.isRemote)
			return false;
		if (world.getBlockMetadata(x, y, z) == numStages - 1) {
			Item item = null;
			if (tree == EnumApricornTrees.Black)
				item = PixelmonItems.apricornBlack;
			else if (tree == EnumApricornTrees.White)
				item = PixelmonItems.apricornWhite;
			else if (tree == EnumApricornTrees.Pink)
				item = PixelmonItems.apricornPink;
			else if (tree == EnumApricornTrees.Green)
				item = PixelmonItems.apricornGreen;
			else if (tree == EnumApricornTrees.Blue)
				item = PixelmonItems.apricornBlue;
			else if (tree == EnumApricornTrees.Yellow)
				item = PixelmonItems.apricornYellow;
			else if (tree == EnumApricornTrees.Red)
				item = PixelmonItems.apricornRed;

			EntityItem var3 = new EntityItem(world, x, y + maxY, z, new ItemStack(item));
			var3.delayBeforeCanPickup = 10;

			world.spawnEntityInWorld(var3);
			world.setBlockMetadata(x, y, z, numStages - 3);
			return true;
		}
		return false;
	}

	@Override
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random par5Random) {
		super.updateTick(world, x, y, z, par5Random);

		if (world.getBlockLightValue(x, y + 1, z) >= 9) {
			int stage = world.getBlockMetadata(x, y, z);
			if (stage < numStages - 1) {
				float var7 = 10;

				if (par5Random.nextInt(1) == 0) {
					world.setBlockMetadata(x, y, z, stage + 1);
					world.getBlockTileEntity(x, y, z).updateContainingBlockInfo();
					world.getBlockTileEntity(x, y, z).invalidate();
				}
			}
		}
	}
}
