package pixelmon.items;

import net.minecraft.src.CreativeTabs;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumApricorns;

public class ItemApricornCooked extends PixelmonItem {
	public EnumApricorns apricorn;
	public ItemApricornCooked(int id, EnumApricorns apricorn) {
		super(id);
		this.apricorn = apricorn;
		SetUsableInBattle(false);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		setIconIndex(apricorn.meltedIconIndex);
		setTextureFile("/pixelmon/image/pitems2.png");
		setCreativeTab(PixelmonCreativeTabs.natural);
	}

}
