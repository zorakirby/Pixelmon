package pixelmon.items.heldItems;

import pixelmon.enums.heldItems.EnumEvAdjustingItems;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class EVAdjusting extends ItemHeld {

	public EnumEvAdjustingItems type;

	public EVAdjusting(int id, EnumEvAdjustingItems type, String itemName) {
		super(id, EnumHeldItems.evAdjusting, type.toString().toLowerCase(), itemName);
		this.type = type;
	}
}
