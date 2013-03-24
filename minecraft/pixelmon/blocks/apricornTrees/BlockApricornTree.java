package pixelmon.blocks.apricornTrees;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import pixelmon.config.PixelmonItemsApricorns;
import pixelmon.enums.EnumApricornTrees;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockApricornTree extends BlockContainer {
	public static final int numStages = 6;
	private Random rand = new Random();
	public EnumApricornTrees tree;

	public BlockApricornTree(int id, EnumApricornTrees tree) {
		super(id, Material.wood);
		this.tree = tree;
		setTickRandomly(true);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return PixelmonItemsApricorns.getApricorn(tree.apricorn).itemID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("tree_top");
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
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
		this.setBlockBounds(par1World.getBlockMetadata(par2, par3, par4));
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBounds(par1World.getBlockMetadata(par2, par3, par4));
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 * 
	 * @param world
	 */
	public void setBlockBounds(int stage) {
		if (stage == 0)
			this.setBlockBounds(0.4f, 0, 0.4f, 0.6f, 0.2f, 0.6f);
		else if (stage == 1)
			this.setBlockBounds(0.25f, 0, 0.25f, 0.75f, 0.7f, 0.75f);
		else if (stage == 2)
			this.setBlockBounds(0.17f, 0, 0.17f, 0.83f, 1.1f, 0.83f);
		else
			this.setBlockBounds(0.06f, 0, 0.06f, 0.94f, 1.65f, 0.94f);
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
			Item item = PixelmonItemsApricorns.getApricorn(tree.apricorn);

			EntityItem var3 = new EntityItem(world, x, y + maxY, z, new ItemStack(item));
			var3.delayBeforeCanPickup = 10;

			world.spawnEntityInWorld(var3);
			world.setBlockMetadataWithNotify(x, y, z, numStages - 3, 2);
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

				if (par5Random.nextInt(3) == 0) {
					world.setBlockMetadataWithNotify(x, y, z, stage + 1, 2);
					((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
				}
			}
		}
	}
}
