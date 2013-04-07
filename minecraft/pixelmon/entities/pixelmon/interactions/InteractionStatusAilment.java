package pixelmon.entities.pixelmon.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import pixelmon.api.interactions.IInteraction;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.items.ItemStatusAilmentHealer;

public class InteractionStatusAilment implements IInteraction {

	@Override
	public boolean interact(EntityPixelmon entityPixelmon, EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
			if (itemstack.getItem() instanceof ItemStatusAilmentHealer) {
				if (((ItemStatusAilmentHealer) itemstack.getItem()).healPokemon(entityPixelmon)) {
					if (!player.capabilities.isCreativeMode)
						player.inventory.consumeInventoryItem(itemstack.itemID);
					return true;
				}
			}
		}
		return false;
	}

}
