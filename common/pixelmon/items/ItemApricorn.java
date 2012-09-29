package pixelmon.items;

import java.util.HashMap;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonBlocksApricornTrees;
import pixelmon.enums.EnumApricorns;

public class ItemApricorn extends PixelmonItem {
	public EnumApricorns apricorn;

	public ItemApricorn(int id, EnumApricorns apricorn) {
		super(id);

		this.apricorn = apricorn;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setIconIndex(apricorn.iconIndex);
		setTextureFile("/pixelmon/image/pitems2.png");
		setTabToDisplayOn(CreativeTabs.tabMaterials);
		if (apricorn == EnumApricorns.Black)
			spawnID = PixelmonBlocksApricornTrees.apricornTreeBlack.blockID;
		else if (apricorn == EnumApricorns.White)
			spawnID = PixelmonBlocksApricornTrees.apricornTreeWhite.blockID;
		else if (apricorn == EnumApricorns.Pink)
			spawnID = PixelmonBlocksApricornTrees.apricornTreePink.blockID;
		else if (apricorn == EnumApricorns.Green)
			spawnID = PixelmonBlocksApricornTrees.apricornTreeGreen.blockID;
		else if (apricorn == EnumApricorns.Blue)
			spawnID = PixelmonBlocksApricornTrees.apricornTreeBlue.blockID;
		else if (apricorn == EnumApricorns.Yellow)
			spawnID = PixelmonBlocksApricornTrees.apricornTreeYellow.blockID;
		else if (apricorn == EnumApricorns.Red)
			spawnID = PixelmonBlocksApricornTrees.apricornTreeRed.blockID;
	}

	/** The ID of the block the reed will spawn when used from inventory bar. */
	private int spawnID;

	public boolean tryPlaceIntoWorld(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7,
			float par8, float par9, float par10) {
		int var11 = par3World.getBlockId(par4, par5, par6);

		if (var11 != Block.grass.blockID && var11 != Block.dirt.blockID)
			return false;

		if (par7 == 0) {
			--par5;
		}

		if (par7 == 1) {
			++par5;
		}

		if (par7 == 2) {
			--par6;
		}

		if (par7 == 3) {
			++par6;
		}

		if (par7 == 4) {
			--par4;
		}

		if (par7 == 5) {
			++par4;
		}

		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6)) {
			return false;
		} else if (par1ItemStack.stackSize == 0) {
			return false;
		} else {
			if (par3World.canPlaceEntityOnSide(this.spawnID, par4, par5, par6, false, par7, (Entity) null)) {
				Block var12 = Block.blocksList[this.spawnID];

				if (par3World.setBlockWithNotify(par4, par5, par6, this.spawnID)) {
					if (par3World.getBlockId(par4, par5, par6) == this.spawnID) {
						Block.blocksList[this.spawnID].updateBlockMetadata(par3World, par4, par5, par6, par7, par8, par9, par10);
						Block.blocksList[this.spawnID].onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer);
					}

					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F),
							var12.stepSound.getStepSound(), (var12.stepSound.getVolume() + 1.0F) / 2.0F, var12.stepSound.getPitch() * 0.8F);
					--par1ItemStack.stackSize;
				}
			}

			return true;
		}
	}
}
