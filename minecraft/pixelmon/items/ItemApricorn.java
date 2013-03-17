package pixelmon.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pixelmon.config.PixelmonBlocksApricornTrees;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumApricorns;

public class ItemApricorn extends PixelmonItem {
	public EnumApricorns apricorn;

	public ItemApricorn(int id, EnumApricorns apricorn) {
		super(id, "apricorns/" + apricorn.toString().toLowerCase() + "apricorn");

		this.apricorn = apricorn;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setCreativeTab(PixelmonCreativeTabs.natural);
		if (apricorn == EnumApricorns.Black)
			blockID = PixelmonBlocksApricornTrees.apricornTreeBlack.blockID;
		else if (apricorn == EnumApricorns.White)
			blockID = PixelmonBlocksApricornTrees.apricornTreeWhite.blockID;
		else if (apricorn == EnumApricorns.Pink)
			blockID = PixelmonBlocksApricornTrees.apricornTreePink.blockID;
		else if (apricorn == EnumApricorns.Green)
			blockID = PixelmonBlocksApricornTrees.apricornTreeGreen.blockID;
		else if (apricorn == EnumApricorns.Blue)
			blockID = PixelmonBlocksApricornTrees.apricornTreeBlue.blockID;
		else if (apricorn == EnumApricorns.Yellow)
			blockID = PixelmonBlocksApricornTrees.apricornTreeYellow.blockID;
		else if (apricorn == EnumApricorns.Red)
			blockID = PixelmonBlocksApricornTrees.apricornTreeRed.blockID;
	}

	/** The ID of the block the reed will spawn when used from inventory bar. */
	private int blockID;

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8,
			float par9, float par10) {
		if (!PixelmonConfig.allowPlanting)
			return false;
		int var11 = par3World.getBlockId(par4, par5, par6);
		if (par7 != 1)
			return false;

		par5++;
		if (var11 != Block.grass.blockID && var11 != Block.dirt.blockID)
			return false;

		if (par1ItemStack.stackSize == 0) {
			return false;
		} else {
			if (par3World.canPlaceEntityOnSide(this.blockID, par4, par5, par6, false, par7, par2EntityPlayer, par1ItemStack)) {
				Block var12 = Block.blocksList[this.blockID];
				int var13 = this.getMetadata(par1ItemStack.getItemDamage());
				int var14 = Block.blocksList[this.blockID].onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, var13);
				if (placeBlockAt(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10, var14)) {
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F),
							var12.stepSound.getStepSound(), (var12.stepSound.getVolume() + 1.0F) / 2.0F, var12.stepSound.getPitch() * 0.8F);
					--par1ItemStack.stackSize;
				}
			}

			return true;
		}
	}

	/**
	 * Called to actually place the block, after the location is determined and
	 * all permission checks have been made.
	 * 
	 * @param stack
	 *            The item stack that was used to place the block. This can be
	 *            changed inside the method.
	 * @param player
	 *            The player who is placing the block. Can be null if the block
	 *            is not being placed by a player.
	 * @param side
	 *            The side the player (or machine) right-clicked on.
	 */
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ,
			int metadata) {
		if (!world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, metadata, 2)) {
			return false;
		}

		if (world.getBlockId(x, y, z) == this.blockID) {
			Block.blocksList[this.blockID].onBlockPlacedBy(world, x, y, z, player, stack);
			Block.blocksList[this.blockID].onPostBlockPlaced(world, x, y, z, metadata);
		}

		return true;
	}
}
