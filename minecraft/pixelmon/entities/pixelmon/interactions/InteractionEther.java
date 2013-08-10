package pixelmon.entities.pixelmon.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import pixelmon.api.interactions.IInteraction;
import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.items.ItemEther;

public class InteractionEther implements IInteraction {

	@Override
	public boolean interact(EntityPixelmon entityPixelmon, EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			if (entityPixelmon.getOwner() == player) {
				ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
				if (itemstack.getItem() instanceof ItemEther) {
					boolean canUseEther = false;
					for (int i = 0; i < entityPixelmon.getMoveset().size(); i++) {
						Attack a = entityPixelmon.getMoveset().get(i);
						if (a.pp < a.ppBase) {
							canUseEther = true;
							break;
						}
					}
					if (canUseEther) {
						ItemEther ether = (ItemEther) itemstack.getItem();
						if (ether.type.restoresAllMoves()) {
							ether.restoreAllMoves(entityPixelmon);
							if (!player.capabilities.isCreativeMode)
								player.inventory.consumeInventoryItem(itemstack.itemID);
							return true;
						} else {

						}
					}
				}
			}
		}
		return false;
	}

}
