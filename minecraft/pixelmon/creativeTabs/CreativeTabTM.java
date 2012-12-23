package pixelmon.creativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StringTranslate;
import pixelmon.config.PixelmonItemsTMs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
