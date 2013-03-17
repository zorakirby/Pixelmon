package pixelmon.items.heldItems;

import pixelmon.battles.participants.BattleParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemExpShare extends ItemHeld {

	public ItemExpShare(int id) {
		super(id, EnumHeldItems.expShare, "expshare");
	}
}
