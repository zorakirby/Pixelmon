package pixelmon.enums;

import net.minecraft.item.Item;
import pixelmon.config.PixelmonItems;
import pixelmon.items.IEnumItem;
import pixelmon.items.ItemEvolutionStone;

public enum EnumEvolutionStone implements IEnumItem{

	Firestone, Thunderstone, Waterstone, Sunstone, Leafstone, Dawnstone, Duskstone, Moonstone, Shinystone;

	private EnumEvolutionStone() {
	}

	public static EnumEvolutionStone getEvolutionStone(String name) {
		for (EnumEvolutionStone e : values())
			if (e.toString().equalsIgnoreCase(name))
				return e;
		return null;
	}

	public static boolean isEvolutionStone(String name) {
		for (EnumEvolutionStone e : values())
			if (e.toString().equalsIgnoreCase(name))
				return true;
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 * {@code EnumEvolutionStone} has only one item category, so the input
	 * parameter can be any number, and will not return {@code null} even if
	 * {@code useless > 0}
	 * @param useless - can be any number, because {@code EnumEvolutionStone}
	 * only has one Item category, so it doesn't care.
	 * @return Item associated with this value.
	 */
	public ItemEvolutionStone getItem(int useless){
		Item result = null;
		switch(this){
		case Dawnstone: break; //TODO : Dawn Stone.
		case Duskstone: break; //TODO : Dawn Stone.
		case Firestone: result = PixelmonItems.fireStone; break;
		case Leafstone: result = PixelmonItems.leafStone; break;
		case Moonstone: result = PixelmonItems.moonStone; break;
		case Shinystone:break; //TODO : Shiny Stone.
		case Sunstone:	break;
		case Thunderstone: result = PixelmonItems.thunderStone; break;
		case Waterstone: result = PixelmonItems.waterStone; break;
		}
		return (ItemEvolutionStone) result;
	}
	
	public int numTypes(){
		return 1;
	}

}
