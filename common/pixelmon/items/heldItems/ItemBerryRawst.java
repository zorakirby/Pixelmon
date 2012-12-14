package pixelmon.items.heldItems;

import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemBerryRawst extends ItemHeld {

	public ItemBerryRawst(int id) {
		super(id, EnumHeldItems.rawst);
		SetUsableInBattle(true);
	}

	public boolean effectEntity(EntityPixelmon helper) {
		if (helper.removeStatus(StatusEffectType.Burn)) {
			ChatHandler.sendChat(helper.getOwner(), helper.getName() + " just consumed a Rawst Berry and was healed of it's burn!");
			return true;
		}
		return false;
	}

	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
		if (userPokemon.removeStatus(StatusEffectType.Burn)) {
			ChatHandler.sendChat(userPokemon.getOwner(), userPokemon.getName() + " was healed of it's burn!");
		}
	}

}
