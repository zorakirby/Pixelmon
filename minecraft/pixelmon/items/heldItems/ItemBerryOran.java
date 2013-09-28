package pixelmon.items.heldItems;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemBerryOran extends ItemHeld {

	public ItemBerryOran(int id) {
		super(id, EnumHeldItems.oran, "oranberry", "Oran Berry");
		SetUsableInBattle(true);
	}

	public boolean effectEntity(EntityPixelmon helper) {
		if (helper.getHealth() < (int) ((float) helper.getMaxHealth() / .3f)) {
			helper.setHealth(helper.getHealth() + 10);
			ChatHandler.sendChat(helper.getOwner(), helper.getNickname() + " just consumed an Oran Berry and gained 10 health!");
			return true;
		}
		return false;
	}
	
	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
		if (userPokemon.getHealth()+10 > userPokemon.stats.HP)
			userPokemon.setHealth(userPokemon.stats.HP);
		else
			userPokemon.setHealth(userPokemon.getHealth() + 10);
		ChatHandler.sendChat(userPokemon.getOwner(), userPokemon.getNickname() + " gained 10 health!");
	}

}
