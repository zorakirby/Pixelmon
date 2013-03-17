package pixelmon.items;

import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumBadges;

public class ItemBadge extends PixelmonItem {
	public EnumBadges badge;

	public ItemBadge(int id, EnumBadges badges) {
		super(id, "badges/" + badges.toString().toLowerCase());
		this.badge = badges;
		SetUsableInBattle(false);
		maxStackSize = 1;
		setMaxDamage(0xf4240);
		setCreativeTab(PixelmonCreativeTabs.badges);
	}

}
