package pixelmon.items;

import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumPokeballs;

public class ItemPokeballLid extends PixelmonItem {

	public EnumPokeballs pokeball;

	public ItemPokeballLid(int id, EnumPokeballs pokeball) {
		super(id, "pokeballs/" + pokeball.toString().toLowerCase() + "lid");
		this.pokeball = pokeball;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setCreativeTab(PixelmonCreativeTabs.pokeball);
	}

}
