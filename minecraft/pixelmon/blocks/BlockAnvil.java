package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.items.ItemPokeballDisc;

public class BlockAnvil extends BlockContainer {

	public BlockAnvil(int i) {
		super(i, Material.wood);
		setHardness(1f);
		setStepSound(new StepSound("pixelmon.anvilHits", 1, 1));
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

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("anvil_base");
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
		return PixelmonItems.anvilItem.itemID;
	}

	@SideOnly(Side.CLIENT)
    //only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return PixelmonItems.anvilItem.itemID;
    }
	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		onBlockDestroyed(world, x, y, z);
		return super.removeBlockByPlayer(world, player, x, y, z);
	}
	
	private void onBlockDestroyed(World world, int x, int y, int z) {
		if (!world.isRemote) {
			if (((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil != -1) {
				int itemId = ((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil;
				Item item = PixelmonItemsPokeballs.getItemFromID(itemId);
				if (item != null) {

					EntityItem var3 = new EntityItem(world, x, y + maxY, z, new ItemStack(item));

					var3.delayBeforeCanPickup = 10;
					world.spawnEntityInWorld(var3);
				}
			}
		}
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
			if (item == null) {
				if (itemId == PixelmonItems.aluminiumPlate.itemID)
					item = PixelmonItems.aluminiumPlate;
				else if (itemId == PixelmonItems.aluminiumIngot.itemID)
					item = PixelmonItems.aluminiumIngot;
			}

			if (item == null) {
				((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil = -1;
				return true;
			}

			EntityItem var3 = new EntityItem(world, x, y + maxY, z, new ItemStack(item));

			var3.delayBeforeCanPickup = 10;

			world.spawnEntityInWorld(var3);
			((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil = -1;
			((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).state = 0;
		}
		if (player.getCurrentEquippedItem() != null
				&& (player.getCurrentEquippedItem().getItem() instanceof ItemPokeballDisc
						|| player.getCurrentEquippedItem().getItem() == PixelmonItemsPokeballs.ironDisc || player.getCurrentEquippedItem().getItem() == PixelmonItems.aluminiumIngot)) {
			((TileEntityAnvil) world.getBlockTileEntity(x, y, z)).itemOnAnvil = player.getCurrentEquippedItem().itemID;
			player.getCurrentEquippedItem().stackSize--;
			((WorldServer) world).getPlayerManager().markBlockForUpdate(x, y, z);
			return true;
		}
		((WorldServer) world).getPlayerManager().markBlockForUpdate(x, y, z);
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityAnvil();
	}

}
