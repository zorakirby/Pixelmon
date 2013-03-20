package pixelmon.items;

import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumBadges;

public class ItemBadge extends PixelmonItem {
	public EnumBadges badge;

	public ItemBadge(int id, EnumBadges badges) {
		super(id, "badges/" + badges.toString().toLowerCase(), badges.toString().substring(0, badges.toString().indexOf("badge")) + " Badge");
		String badgeName = badges.toString();
		badgeName = badgeName.substring(0, badgeName.indexOf("badge"));
		this.badge = badges;
		SetUsableInBattle(false);
		maxStackSize = 1;
		setMaxDamage(0xf4240);
		setCreativeTab(PixelmonCreativeTabs.badges);
	}

}
