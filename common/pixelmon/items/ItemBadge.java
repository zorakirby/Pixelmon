package pixelmon.items;

import net.minecraft.src.CreativeTabs;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumBadges;

public class ItemBadge extends PixelmonItem {
	public EnumBadges badge;
	public ItemBadge(int id, EnumBadges badges) {
		super(id);
		this.badge = badges;
		SetUsableInBattle(false);
		maxStackSize = 1;
		setMaxDamage(0xf4240);
		setIconIndex(badges.iconIndex);
		setTextureFile("/pixelmon/image/badges.png");
		setCreativeTab(PixelmonCreativeTabs.badges);
	}

}
