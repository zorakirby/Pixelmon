package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import pixelmon.Pixelmon;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.enums.EnumGui;
import pixelmon.items.ItemPokeballDisc;
import pixelmon.items.PixelmonItem;
import pixelmon.storage.ComputerBox;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerComputerStorage;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.WorldServer;

public class BlockAnvil extends BlockContainer {

	public BlockAnvil(int i) {
		super(i, Material.iron);
		setHardness(2.5f);
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
		this.setBlockBounds(0f, 0, 0f, 1f, 0.8f, 1f);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return PixelmonItems.anvilItem.shiftedIndex;
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

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
		if (world.isRemote)
			return false;
		if (((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil != -1) {
			int itemId = ((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil;
			Item item = PixelmonItemsPokeballs.getItemFromID(itemId);
			EntityItem var3 = new EntityItem(world, x, y + maxY, z, new ItemStack(item));

			var3.delayBeforeCanPickup = 10;

			world.spawnEntityInWorld(var3);
			((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil = -1;
		}
		if (player.getCurrentEquippedItem().getItem() instanceof ItemPokeballDisc) {
			((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil = player.getCurrentEquippedItem().itemID;
			player.getCurrentEquippedItem().stackSize--;
			((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
			return true;
		}
		((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityAnvil();
	}

}
