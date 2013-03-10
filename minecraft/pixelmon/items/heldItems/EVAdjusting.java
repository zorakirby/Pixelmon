package pixelmon.items.heldItems;

import pixelmon.enums.heldItems.EnumEvAdjustingItems;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class EVAdjusting extends ItemHeld {

	public EnumEvAdjustingItems type;

	public EVAdjusting(int id, EnumEvAdjustingItems type) {
		super(id, EnumHeldItems.evAdjusting);
		this.type = type;

		setIconCoord(12, type.iconRow);
	}
}
