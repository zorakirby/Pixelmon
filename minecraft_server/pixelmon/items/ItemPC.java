package pixelmon.items;

import pixelmon.blocks.BlockPC;
import pixelmon.blocks.PixelmonBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.BlockBed;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class ItemPC extends Item {

	public ItemPC(int par1) {
		super(par1);
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS !
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7) {
		if (par7 != 1) {
			return false;
		} else {
			++y;
			BlockPC pcBlock = (BlockPC) PixelmonBlocks.pc;
			int var6 = MathHelper.floor_double((double) (par2EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			int var9 = 0;
			if (var6 == 0) {
				var9 = 2;
			}

			if (var6 == 1) {
				var9 = 1;
			}

			if (var6 == 2) {
				var9 = 3;
			}

			if (var6 == 3) {
				var9 = 0;
			}

			if (par2EntityPlayer.canPlayerEdit(x, y, z) && par2EntityPlayer.canPlayerEdit(x, y + 1, z)) {
				if (par3World.isAirBlock(x, y, z) && par3World.isAirBlock(x, y + 1, z) && par3World.isBlockNormalCube(x, y - 1, z) && par3World.getBlockId(x, y - 1, z) != pcBlock.blockID) {
					par3World.setBlockAndMetadataWithNotify(x, y, z, pcBlock.blockID, var9);

					if (par3World.getBlockId(x, y, z) == pcBlock.blockID) {
						par3World.setBlockAndMetadataWithNotify(x, y + 1, z, pcBlock.blockID, var9 - 6);
					}

					--par1ItemStack.stackSize;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
