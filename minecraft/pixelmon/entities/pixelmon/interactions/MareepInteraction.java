package pixelmon.entities.pixelmon.interactions;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pixelmon.entities.pixelmon.Entity8HoldsItems;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class MareepInteraction extends PixelmonInteraction {

	public MareepInteraction() {
		super("Mareep", 1);
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (numInteractions == 0)
			return false;

		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

		if (var2 != null && var2.itemID == Item.shears.itemID) {
			par1EntityPlayer.dropPlayerItem(new ItemStack(Block.cloth.blockID, 1, 0));

			numInteractions--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PixelmonInteraction getInstance() {
		return new MareepInteraction();
	}

}
