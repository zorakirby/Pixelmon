package pixelmon.items;

import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumPokeballs;
import net.minecraft.src.CreativeTabs;

public class ItemPokeballDisc extends PixelmonItem {

	public EnumPokeballs pokeball;

	public ItemPokeballDisc(int id, EnumPokeballs pokeball) {
		super(id);
		this.pokeball = pokeball;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setIconIndex(pokeball.discIconIndex);
		setTextureFile("/pixelmon/image/pitems2.png");
		setCreativeTab(PixelmonCreativeTabs.pokeball);
	}

}
