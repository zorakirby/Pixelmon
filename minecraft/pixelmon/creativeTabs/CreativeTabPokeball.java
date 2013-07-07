package pixelmon.creativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StringTranslate;
import pixelmon.Pixelmon;
import pixelmon.config.PixelmonItemsPokeballs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabPokeball extends CreativeTabs {

	String tabName;
	int tab;

	public CreativeTabPokeball(int par1, String par2Str) {
		super(par1, par2Str);
		tabName = par2Str;
		tab = par1;
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return PixelmonItemsPokeballs.pokeBall;
	}

	public String getTranslatedTabLabel() {
		return Pixelmon.stringtranslate.translateKey(tabName);
	}
}
