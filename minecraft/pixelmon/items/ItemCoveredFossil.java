package pixelmon.items;

import net.minecraft.item.Item;

public class ItemCoveredFossil extends PixelmonItem {

	public ItemFossil cleanedFossil;

	public ItemCoveredFossil(int par1, ItemFossil cleanedFossil) {
		super(par1, "fossils/covered" + cleanedFossil.modelName.toLowerCase());
		this.cleanedFossil = cleanedFossil;
	}

}
