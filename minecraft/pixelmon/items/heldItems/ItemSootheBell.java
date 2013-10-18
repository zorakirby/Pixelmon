package pixelmon.items.heldItems;

import pixelmon.battles.participants.BattleParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemSootheBell extends ItemHeld {

	public ItemSootheBell(int id) {
		super(id, EnumHeldItems.sootheBell, "soothebell", "Soothe Bell");
	}
}
