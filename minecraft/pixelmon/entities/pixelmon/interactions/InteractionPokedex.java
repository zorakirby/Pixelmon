package pixelmon.entities.pixelmon.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import pixelmon.api.interactions.IInteraction;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.items.ItemPokedex;
import pixelmon.pokedex.Pokedex;
import pixelmon.pokedex.Pokedex.DexRegisterStatus;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;

public class InteractionPokedex implements IInteraction {

	@Override
	public boolean interact(EntityPixelmon entityPixelmon, EntityPlayer player) {
		ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
		if (itemstack.getItem() instanceof ItemPokedex) {
			ItemPokedex pokedex = (ItemPokedex) itemstack.getItem();
			try {
				PixelmonStorage.PokeballManager.getPlayerStorage(PixelmonStorage.PokeballManager.getPlayerFromName(player.username)).pokedex.set(
						Pokedex.nameToID(entityPixelmon.getName()), DexRegisterStatus.seen);
				pokedex.openPokedexGui(Pokedex.nameToID(entityPixelmon.getName()), player, entityPixelmon.worldObj);
			} catch (PlayerNotLoadedException e) {
			}
			return true;
		}
		return false;
	}

}
