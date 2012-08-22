package pixelmon.items.heldItems;

import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemBerryRawst extends ItemHeld {

	public ItemBerryRawst(int id) {
		super(id, EnumHeldItems.rawst);
		SetUsableInBattle(true);
	}

	public boolean effectEntity(PixelmonEntityHelper helper) {
		for (int i = 0; i < helper.status.size(); i++) {
			StatusEffectBase base = helper.status.get(i);
			if (base.type == StatusEffectType.Burn) {
				helper.status.remove(i);
				ChatHandler.sendChat(helper.getOwner(), helper.getName() + " just consumed a Rawst Berry and was healed of it's burn!");
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void useFromBag(PixelmonEntityHelper userPokemon, PixelmonEntityHelper targetPokemon) {
		for (int i = 0; i < userPokemon.status.size(); i++) {
			StatusEffectBase base = userPokemon.status.get(i);
			if (base.type == StatusEffectType.Burn) {
				userPokemon.status.remove(i);
				ChatHandler.sendChat(userPokemon.getOwner(), userPokemon.getName() + " was healed of it's burn!");
			}
		}
	}

}
