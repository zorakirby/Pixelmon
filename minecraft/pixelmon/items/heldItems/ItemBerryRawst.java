package pixelmon.items.heldItems;

import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemBerryRawst extends ItemHeld {

	public ItemBerryRawst(int id) {
		super(id, EnumHeldItems.rawst, "rawstberry", "Rawst Berry");
		SetUsableInBattle(true);
	}

	public boolean effectEntity(EntityPixelmon helper) {
		if (helper.removeStatus(StatusType.Burn)) {
			ChatHandler.sendChat(helper.getOwner(), helper.getName() + " just consumed a Rawst Berry and was healed of it's burn!");
			return true;
		}
		return false;
	}

	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
		if (userPokemon.removeStatus(StatusType.Burn)) {
			ChatHandler.sendChat(userPokemon.getOwner(), userPokemon.getName() + " was healed of it's burn!");
		}
	}

}
