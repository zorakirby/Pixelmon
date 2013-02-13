package pixelmon.items;

import net.minecraft.item.Item;

public class ItemFossilUncovered extends PixelmonItem {

	public ItemPokemonFossil cleanedFossil;

	public ItemFossilUncovered(int par1, ItemPokemonFossil cleanedFossil) {
		super(par1);
		this.cleanedFossil = cleanedFossil;
	}

}
