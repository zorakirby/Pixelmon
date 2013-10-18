package pixelmon.entities.pixelmon.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import pixelmon.api.interactions.IInteraction;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.items.ItemPotion;

public class InteractionPotion implements IInteraction {

	@Override
	public boolean interact(EntityPixelmon entityPixelmon, EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
			if (itemstack.getItem() instanceof ItemPotion) {
				if (entityPixelmon.getHealth() < entityPixelmon.stats.HP) {
					((ItemPotion) itemstack.getItem()).healPokemon(entityPixelmon);
					if (!player.capabilities.isCreativeMode)
						player.inventory.consumeInventoryItem(itemstack.itemID);
					return true;
				}
			}
		}
		return false;
	}
}
