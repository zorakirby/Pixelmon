package pixelmon.items;

import net.minecraft.src.CreativeTabs;
import pixelmon.enums.EnumPokeballs;

public class ItemPokeballLid extends PixelmonItem {

	private EnumPokeballs pokeball;

	public ItemPokeballLid(int id, EnumPokeballs pokeball) {
		super(id);
		this.pokeball = pokeball;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setIconIndex(pokeball.lidIconIndex);
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMaterials);
	}

}