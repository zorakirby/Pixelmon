package pixelmon.creativeTabs;

import pixelmon.config.PixelmonItemsBadges;
import pixelmon.config.PixelmonItemsTMs;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.StringTranslate;

public class CreativeTabTM extends CreativeTabs {
	String tabName;
	int tab;

	public CreativeTabTM(int tab, String tabName) {
		super(tab, tabName);
		this.tabName = tabName;
		this.tab = tab;
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return PixelmonItemsTMs.TMs.get(0);
	}

	public String getTranslatedTabLabel() {
		return StringTranslate.getInstance().translateKey(tabName);
	}
}
