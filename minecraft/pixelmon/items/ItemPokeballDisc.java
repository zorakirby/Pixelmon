package pixelmon.items;

import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumPokeballs;

public class ItemPokeballDisc extends PixelmonItem {

	public EnumPokeballs pokeball;

	public ItemPokeballDisc(int id, EnumPokeballs pokeball) {
		super(id, "pokeballs/" + pokeball.toString().toLowerCase() + "disc");
		this.pokeball = pokeball;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setCreativeTab(PixelmonCreativeTabs.pokeball);
	}

}
