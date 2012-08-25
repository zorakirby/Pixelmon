package pixelmon.items;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayerMP;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.storage.PixelmonStorage;

public class ItemPotion extends PixelmonItem {

	public ItemPotion(int par1) {
		super(par1);
		SetUsableInBattle(true);
	}

	@Override
	public void useFromBag(PixelmonEntityHelper userPokemon, PixelmonEntityHelper targetPokemon) {
		if (userPokemon.getHealth() + 20 >= userPokemon.stats.HP) {
			userPokemon.setHealth(userPokemon.stats.HP);
		} else {
			userPokemon.setHealth(userPokemon.getHealth() + 20);
		}
		if (userPokemon.getOwner()!=null)
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)userPokemon.getOwner()).updateNBT(userPokemon);
	}
}
