package pixelmon.config;

import java.lang.reflect.Field;

import pixelmon.enums.EnumBadges;
import pixelmon.items.ItemBadge;
import net.minecraft.src.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelmonItemsBadges {
	public static int balanceBadgeID;
	public static int beaconBadgeID;

	@Mod.Item(name = "Balance Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item balanceBadge;
	@Mod.Item(name = "Beacon Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item beaconBadge;

	public static void load(Configuration cfg) {
		balanceBadgeID = cfg.get("Balance Badge", "item", 10200).getInt();
		beaconBadgeID = cfg.get("Beacon Badge", "item", 10201).getInt();

		balanceBadge = new ItemBadge(balanceBadgeID, EnumBadges.Balancebadge).setItemName("Balance Badge");
		beaconBadge = new ItemBadge(beaconBadgeID, EnumBadges.Beaconbadge).setItemName("Beacon Badge");
	}

	public static void addNames() {
		PixelmonItemsBadges.addNames();
		try {
			for (Field field : PixelmonItemsBadges.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					LanguageRegistry.addName(item, field.getAnnotation(Mod.Item.class).name());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
