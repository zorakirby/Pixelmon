package pixelmon.entities.pixelmon.interactions;

import pixelmon.entities.pixelmon.Entity8HoldsItems;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class CameruptInteraction extends PixelmonInteraction {

	public CameruptInteraction(Entity8HoldsItems pixelmon) {
		super (pixelmon);
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

		if (var2 != null && var2.itemID == Item.bucketEmpty.shiftedIndex) {
			par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Item.bucketLava));
			return true;
		} else {
			return false;
		}
	}

}
