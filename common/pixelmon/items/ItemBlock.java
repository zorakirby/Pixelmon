package pixelmon.items;

import cpw.mods.fml.client.registry.RenderingRegistry;
import pixelmon.blocks.BlockHealer;
import pixelmon.blocks.BlockPC;
import pixelmon.config.PixelmonBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.BlockBed;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class ItemBlock extends Item {
	Block block;
	public ItemBlock(int par1, Block block, int iconIndex) {
		super(par1);
		this.block = block;
		setTabToDisplayOn(CreativeTabs.tabMisc);
		setIconIndex(iconIndex);
		setTextureFile("/pixelmon/image/pitems.png");
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS !
	 */
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if (side != 1) {
			return false;
		} else {
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
				var9 = 3;
			}

			if (var6 == 3) {
				var9 = 0;
			}

			if (player.canPlayerEdit(x, y, z) && player.canPlayerEdit(x, y + 1, z)) {
				if (world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z) && world.isBlockNormalCube(x, y - 1, z) && world.getBlockId(x, y - 1, z) != block.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z, block.blockID, var9);
					if (world.getBlockId(x, y, z) == block.blockID && block instanceof BlockPC) {
						world.setBlockAndMetadataWithNotify(x, y + 1, z, block.blockID, var9 - 6);
					}

					--stack.stackSize;
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
