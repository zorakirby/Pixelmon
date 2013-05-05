package pixelmon.entities.pixelmon.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import pixelmon.api.interactions.IInteraction;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.items.ItemHeld;
import pixelmon.storage.PixelmonStorage;

public class InteractionHeldItem implements IInteraction {

	@Override
	public boolean interact(EntityPixelmon entityPixelmon, EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			if (entityPixelmon.getOwner() == player) {
				ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
				if (itemstack.getItem() instanceof ItemHeld) {
					if (entityPixelmon.getHeldItem() != null) {
						if (!entityPixelmon.worldObj.isRemote) {
							entityPixelmon.entityDropItem(entityPixelmon.heldItem.copy(), 1f);
						}
						entityPixelmon.setHeldItem(null);
					}
					ItemStack itemstack1 = itemstack.copy();
					itemstack1.stackSize = 1;
					player.inventory.consumeInventoryItem(itemstack.itemID);
					entityPixelmon.setHeldItem(itemstack1);
					entityPixelmon.updateNBT();
					return true;
				}
			}
		}
		return false;
	}

}
