package pixelmon.items.heldItems;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemBerryLeppa extends ItemHeld {

	public ItemBerryLeppa(int id) {
		super(id, EnumHeldItems.leppa);
		SetUsableInBattle(true);
	}

	public boolean effectEntity(EntityPixelmon helper) {
		for (int i=0; i < helper.moveset.size(); i++){
			Attack move = helper.moveset.get(i);
			if (move == null) {
				continue;
			}
			if (move.pp < move.ppBase - 10) {
				move.pp += 10;
				helper.setHeldItem(null);
				ChatHandler.sendChat(helper.getOwner(), helper.getName() + " just consumed a Leppa Berry. " + move.baseAttack.attackName + " gained 10 pp!");
				return true;
			}
		}
		return false;
	}

	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {

	}
}
