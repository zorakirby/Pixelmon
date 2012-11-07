package pixelmon.creativeTabs;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import pixelmon.config.PixelmonItemsBadges;
import pixelmon.config.PixelmonItemsPokeballs;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.StringTranslate;

public class CreativeTabPokeball extends CreativeTabs{

	String tabName;
	int tab;
	public CreativeTabPokeball(int par1, String par2Str) {
		super(par1, par2Str);
		tabName = par2Str;
		tab = par1;
	}

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return PixelmonItemsPokeballs.pokeBall;
    }

    public String getTranslatedTabLabel()
    {
        return StringTranslate.getInstance().translateKey(tabName);
    }
}
