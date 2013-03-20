package pixelmon.items;

import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumPokeballs;

public class ItemPokeballLid extends PixelmonItem {

	public EnumPokeballs pokeball;

	public ItemPokeballLid(int id, EnumPokeballs type) {
		super(id, "pokeballs/" + type.toString().toLowerCase() + "lid", type.toString().substring(0, type.toString().indexOf("Ball")) + " Ball Lid");
		this.pokeball = type;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setCreativeTab(PixelmonCreativeTabs.pokeball);
	}

}
