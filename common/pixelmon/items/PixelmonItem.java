package pixelmon.items;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class PixelmonItem extends Item {

	public PixelmonItem(int par1) {
		super(par1);
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMisc);
	}

}
