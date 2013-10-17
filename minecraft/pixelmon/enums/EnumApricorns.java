package pixelmon.enums;

import net.minecraft.item.Item;
import pixelmon.config.PixelmonItemsApricorns;
import pixelmon.items.IEnumItem;
import pixelmon.items.ItemApricorn;
import pixelmon.items.ItemApricornCooked;

public enum EnumApricorns implements IEnumItem{
	Black(4 * 16 + 8, 5 * 16 + 8), 
	White(4 * 16 + 9, 5 * 16 + 9), 
	Pink(4 * 16 + 10, 5* 16 + 10), 
	Green(4 * 16 + 11, 5 * 16 + 11), 
	Blue(4 * 16 + 12,5 * 16 + 12), 
	Yellow(4 * 16 + 13, 5 * 16 + 13),
	Red(4 * 16 + 14, 5 * 16 + 14);

	public int iconIndex;
	public int meltedIconIndex;

	private EnumApricorns(int iconIndex, int meltedIconIndex) {
		this.iconIndex = iconIndex;
		this.meltedIconIndex = meltedIconIndex;
	}
	
	public ItemApricorn apricorn(){
		switch(this){
		case Black: return (ItemApricorn) PixelmonItemsApricorns.apricornBlack;
		case Blue: return (ItemApricorn) PixelmonItemsApricorns.apricornBlue;
		case Green: return (ItemApricorn) PixelmonItemsApricorns.apricornGreen;
		case Pink: return (ItemApricorn) PixelmonItemsApricorns.apricornPink;
		case Red: return (ItemApricorn) PixelmonItemsApricorns.apricornRed;
		case White: return (ItemApricorn) PixelmonItemsApricorns.apricornWhite;
		case Yellow: return (ItemApricorn) PixelmonItemsApricorns.apricornYellow;
		default: return null;
		}
	}
	
	
	public ItemApricornCooked cookedApricorn(){
		switch(this){
		case Black: return (ItemApricornCooked) PixelmonItemsApricorns.apricornBlackCooked;
		case Blue: return (ItemApricornCooked) PixelmonItemsApricorns.apricornBlueCooked;
		case Green: return (ItemApricornCooked) PixelmonItemsApricorns.apricornGreenCooked;
		case Pink: return (ItemApricornCooked) PixelmonItemsApricorns.apricornPinkCooked;
		case Red: return (ItemApricornCooked) PixelmonItemsApricorns.apricornRedCooked;
		case White: return (ItemApricornCooked) PixelmonItemsApricorns.apricornWhiteCooked;
		case Yellow: return (ItemApricornCooked) PixelmonItemsApricorns.apricornYellowCooked;
		default: return null;
		}
	}

	/**
	 * {@inheritDoc}
	 * The Item categories for {@code EnumPokeballs} are:<br>
	 * {@link ItemApricorn}, {@link ItemApricornCooked}
	 */
	@Override
	public Item getItem(int type) {
		switch(type){
		case 0 : return apricorn();
		case 1 : return cookedApricorn();
		}
		return null;
	}

	@Override
	public int numTypes() {
		return 2;
	}
}
