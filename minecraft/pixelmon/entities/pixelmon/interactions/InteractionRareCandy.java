package pixelmon.entities.pixelmon.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import pixelmon.api.interactions.IInteraction;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class InteractionRareCandy implements IInteraction {

	@Override
	public boolean interact(EntityPixelmon entityPixelmon, EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			if (entityPixelmon.getOwner() == player) {
				ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
				if (itemstack.itemID == PixelmonItems.rareCandy.itemID) {
					entityPixelmon.getLvl().awardEXP(entityPixelmon.getLvl().getExpToNextLevel() - entityPixelmon.getLvl().getExp());
					if (!player.capabilities.isCreativeMode)
						player.inventory.consumeInventoryItem(itemstack.itemID);
					return true;
				}
			}
		}
		return false;
	}

}
