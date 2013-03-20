package pixelmon.items;

import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumApricorns;

public class ItemApricornCooked extends PixelmonItem {
	public EnumApricorns apricorn;

	public ItemApricornCooked(int id, EnumApricorns apricorn) {
		super(id, "apricorns/cooked" + apricorn.toString().toLowerCase() + "apricorn", "Cooked " + apricorn.toString() + " Apricorn");
		this.apricorn = apricorn;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setCreativeTab(PixelmonCreativeTabs.natural);
	}
}
