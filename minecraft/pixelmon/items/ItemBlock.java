package pixelmon.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import pixelmon.blocks.BlockPC;

public class ItemBlock extends PixelmonItem {
	Block block;

	public ItemBlock(int par1, Block block, String textureName, String name) {
		super(par1, "blocks/" + textureName, name);
		this.block = block;
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS !
	 */
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1) {
			return false;
		} else {
			if (!world.isRemote) {
				++y;
				int var6 = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				int var9 = 0;
				if (var6 == 0) {
					var9 = 2;
				}

				if (var6 == 1) {
					var9 = 1;
				}

				if (var6 == 2) {
					var9 = 0;
				}

				if (var6 == 3) {
					var9 = 3;
				}

				if (world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z) && world.isBlockNormalCube(x, y - 1, z)
						&& world.getBlockId(x, y - 1, z) != block.blockID) {
					if (block instanceof BlockPC && world.getBlockId(x, y + 2, z) == block.blockID) {
						return false;
					}
					world.setBlock(x, y, z, block.blockID, var9, 2);
					if (world.getBlockId(x, y, z) == block.blockID && block instanceof BlockPC) {
						world.setBlock(x, y + 1, z, block.blockID, var9 - 6, 2);
					}
					if (!player.capabilities.isCreativeMode) {
						--stack.stackSize;
					}
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
