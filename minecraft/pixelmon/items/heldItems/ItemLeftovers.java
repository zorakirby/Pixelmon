package pixelmon.items.heldItems;

import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemLeftovers extends ItemHeld {

	public ItemLeftovers(int id) {
		super(id, EnumHeldItems.leftovers, "leftovers", "Leftovers");
		SetUsableInBattle(true);
	}

	public boolean effectEntity(EntityPixelmon helper) {
		int par1 = (int) (helper.getMaxHealth() * 0.0625F);
		if (helper.getHeldItem() != null) {
			if (helper.getHeldItem().itemID == PixelmonItemsHeld.leftovers.itemID) {
			helper.healEntityBy(par1);
			//ChatHandler.sendBattleMessage(helper.getOwner(), helper.getNickname() + " just regained some health with Leftovers!");			
			}
		}
		return false;
	}
}
