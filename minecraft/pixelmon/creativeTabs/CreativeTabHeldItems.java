package pixelmon.creativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StringTranslate;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsHeld;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabHeldItems extends CreativeTabs {

	String tabName;
	int tab;

	public CreativeTabHeldItems(int par1, String par2Str) {
		super(par1, par2Str);
		tabName = par2Str;
		tab = par1;
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return PixelmonItemsHeld.expShare;
	}

	public String getTranslatedTabLabel() {
		return StringTranslate.getInstance().translateKey(tabName);
	}
}
