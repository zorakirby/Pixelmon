package pixelmon.creativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StringTranslate;
import pixelmon.config.PixelmonItemsApricorns;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class CreativeTabNatural extends CreativeTabs {

	String tabName;
	int tab;

	public CreativeTabNatural(int par1, String par2Str) {
		super(par1, par2Str);
		tabName = par2Str;
		tab = par1;
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return PixelmonItemsApricorns.apricornRed;
	}

	public String getTranslatedTabLabel() {
		return StringTranslate.getInstance().translateKey(tabName);
	}
}
