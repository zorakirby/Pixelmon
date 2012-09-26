package pixelmon.items;

import java.util.HashMap;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import pixelmon.enums.EnumApricorns;

public class ItemApricorn extends PixelmonItem {
	EnumApricorns apricorn;

	public ItemApricorn(int id, EnumApricorns apricorn) {
		super(id);

		this.apricorn = apricorn;
		SetUsableInBattle(true);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setIconIndex(apricorn.iconIndex);
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMisc);
	}

}
