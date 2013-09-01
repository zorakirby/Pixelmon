package pixelmon.items.heldItems;

import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemWideLens extends ItemHeld {

	public ItemWideLens(int id) {
		super(id, EnumHeldItems.wideLens, "widelens", "WideLens");
		SetUsableInBattle(true);
	}

	public boolean effectEntity(EntityPixelmon helper) {
		int accuracy = (int) (helper.battleStats.getAccuracy() * .1F);
		if (helper.getHeldItem() != null) {
			if (helper.getHeldItem().itemID == PixelmonItemsHeld.wideLens.itemID) {
				helper.battleStats.IncreaseAccuracy(accuracy);
				int accuracy2 = (int) helper.battleStats.getAccuracy();
			}
		}
		return false;
	}
}
