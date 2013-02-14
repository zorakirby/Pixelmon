package pixelmon.blocks;

import java.util.Random;

import pixelmon.Pixelmon;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItemsFossils;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.enums.EnumGui;
import pixelmon.items.ItemFossilUncovered;
import pixelmon.items.ItemPokeballDisc;
import pixelmon.storage.ComputerBox;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerComputerStorage;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockFossilCleaner extends BlockContainer {

	public BlockFossilCleaner(int i) {
		super(i, Material.iron);
		setHardness(1f);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityFossilCleaner();
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			if (((TileEntityFossilCleaner) world.getBlockTileEntity(x, y, z)).itemInCleaner != -1) {
				int itemId = ((TileEntityFossilCleaner) world.getBlockTileEntity(x, y, z)).itemInCleaner;
				Item item = PixelmonItemsFossils.getItemFromIndex(itemId);
				if (item == null) {
					((TileEntityFossilCleaner) world.getBlockTileEntity(x, y, z)).itemInCleaner = -1;
					return true;
				}

				EntityItem var3 = new EntityItem(world, x, y + maxY, z, new ItemStack(item));

				var3.delayBeforeCanPickup = 10;

				world.spawnEntityInWorld(var3);
				((TileEntityFossilCleaner) world.getBlockTileEntity(x, y, z)).itemInCleaner = -1;
			}
			if (player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() instanceof ItemFossilUncovered)) {
				((TileEntityFossilCleaner) world.getBlockTileEntity(x, y, z)).setItemInCleaner(player.getCurrentEquippedItem().itemID);
				player.getCurrentEquippedItem().stackSize--;
				((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
				return true;
			}
			return true;
		}
		return true;
	}
}
