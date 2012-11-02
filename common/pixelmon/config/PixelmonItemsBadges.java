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
	public static int boulderBadgeID;
	public static int cascadeBadgeID;
	public static int coalBadgeID;
	public static int cobbleBadgeID;
	public static int dynamoBadgeID;
	public static int earthBadgeID;
	public static int featherBadgeID;
	public static int fenBadgeID;
	public static int fogBadgeID;
	public static int forestBadgeID;
	public static int glacierBadgeID;
	public static int heatBadgeID;
	public static int hiveBadgeID;
	public static int icicleBadgeID;
	public static int knuckleBadgeID;
	public static int marshBadgeID;
	public static int mindBadgeID;
	public static int mineBadgeID;
	public static int mineralBadgeID;
	public static int plainBadgeID;
	public static int rainbowBadgeID;
	public static int rainBadgeID;
	public static int relicBadgeID;
	public static int risingBadgeID;
	public static int soulBadgeID;

	@Mod.Item(name = "Balance Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item balanceBadge;
	@Mod.Item(name = "Beacon Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item beaconBadge;
	@Mod.Item(name = "Boulder Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item boulderBadge;
	@Mod.Item(name = "Cascade Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item cascadeBadge;
	@Mod.Item(name = "Coal Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item coalBadge;
	@Mod.Item(name = "Cobble Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item cobbleBadge;
	@Mod.Item(name = "Dynamo Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item dynamoBadge;
	@Mod.Item(name = "Earth Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item earthBadge;
	@Mod.Item(name = "Feather Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item featherBadge;
	@Mod.Item(name = "Fen Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item fenBadge;
	@Mod.Item(name = "Fog Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item fogBadge;
	@Mod.Item(name = "Forest Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item forestBadge;
	@Mod.Item(name = "Glacier Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item glacierBadge;
	@Mod.Item(name = "Heat Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item heatBadge;
	@Mod.Item(name = "Hive Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item hiveBadge;
	@Mod.Item(name = "Icicle Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item icicleBadge;
	@Mod.Item(name = "Knuckle Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item knuckleBadge;
	@Mod.Item(name = "Marsh Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item marshBadge;
	@Mod.Item(name = "Mind Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item mindBadge;
	@Mod.Item(name = "Mine Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item mineBadge;
	@Mod.Item(name = "Mineral Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item mineralBadge;
	@Mod.Item(name = "Plain Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item plainBadge;
	@Mod.Item(name = "Rainbow Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item rainbowBadge;
	@Mod.Item(name = "Rain Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item rainBadge;
	@Mod.Item(name = "Relic Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item relicBadge;
	@Mod.Item(name = "Rising Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item risingBadge;
	@Mod.Item(name = "Soul Badge", typeClass = "pixelmon.items.ItemBadge")
	public static Item soulBadge;

	public static void load(Configuration cfg) {
		balanceBadgeID = cfg.get("Balance Badge", "item", 10200).getInt();
		beaconBadgeID = cfg.get("Beacon Badge", "item", 10201).getInt();
		boulderBadgeID = cfg.get("Boulder Badge", "item", 10202).getInt();
		cascadeBadgeID = cfg.get("Cascade Badge", "item", 10203).getInt();
		coalBadgeID = cfg.get("Coal Badge", "item", 10204).getInt();
		cobbleBadgeID = cfg.get("Cobble Badge", "item", 10205).getInt();
		dynamoBadgeID = cfg.get("Dynamo Badge", "item", 10206).getInt();
		earthBadgeID = cfg.get("Earth Badge", "item", 10207).getInt();
		featherBadgeID = cfg.get("Feather Badge", "item", 10208).getInt();
		fenBadgeID = cfg.get("Fen Badge", "item", 10209).getInt();
		fogBadgeID = cfg.get("Fog Badge", "item", 10210).getInt();
		forestBadgeID = cfg.get("Forest Badge", "item", 10211).getInt();
		glacierBadgeID = cfg.get("Glaceir Badge", "item", 10212).getInt();
		heatBadgeID = cfg.get("Heat Badge", "item", 10213).getInt();
		hiveBadgeID = cfg.get("Hive Badge", "item", 10214).getInt();
		icicleBadgeID = cfg.get("Icicle Badge", "item", 10215).getInt();
		knuckleBadgeID = cfg.get("Knuckle Badge", "item", 10216).getInt();
		marshBadgeID = cfg.get("Marsh Badge", "item", 10217).getInt();
		mindBadgeID = cfg.get("Mind Badge", "item", 10218).getInt();
		mineBadgeID = cfg.get("Mine Badge", "item", 10219).getInt();
		mineralBadgeID = cfg.get("Mineral Badge", "item", 10220).getInt();
		plainBadgeID = cfg.get("Plain Badge", "item", 10221).getInt();
		rainbowBadgeID = cfg.get("Rainbow Badge", "item", 10222).getInt();
		rainBadgeID = cfg.get("Rain Badge", "item", 10223).getInt();
		relicBadgeID = cfg.get("Relic Badge", "item", 10224).getInt();
		risingBadgeID = cfg.get("Rising Badge", "item", 10225).getInt();
		soulBadgeID = cfg.get("Soul Badge", "item", 10226).getInt();

		balanceBadge = new ItemBadge(balanceBadgeID, EnumBadges.Balancebadge).setItemName("Balance Badge");
		beaconBadge = new ItemBadge(beaconBadgeID, EnumBadges.Beaconbadge).setItemName("Beacon Badge");
		boulderBadge = new ItemBadge(boulderBadgeID, EnumBadges.Boulderbadge).setItemName("Boulder Badge");
		cascadeBadge = new ItemBadge(cascadeBadgeID, EnumBadges.Cascadebadge).setItemName("Cascade Badge");
		coalBadge = new ItemBadge(coalBadgeID, EnumBadges.Coalbadge).setItemName("Coal Badge");
		cobbleBadge = new ItemBadge(cobbleBadgeID, EnumBadges.Cobblebadge).setItemName("Cobble Badge");
		dynamoBadge = new ItemBadge(dynamoBadgeID, EnumBadges.Dynamobadge).setItemName("Dynamo Badge");
		earthBadge = new ItemBadge(earthBadgeID, EnumBadges.Earthbadge).setItemName("Earth Badge");
		featherBadge = new ItemBadge(featherBadgeID, EnumBadges.Featherbadge).setItemName("Feather Badge");
		fenBadge = new ItemBadge(fenBadgeID, EnumBadges.Fenbadge).setItemName("Fen Badge");
		fogBadge = new ItemBadge(fogBadgeID, EnumBadges.Fogbadge).setItemName("Fog Badge");
		forestBadge = new ItemBadge(forestBadgeID, EnumBadges.Forestbadge).setItemName("Forest Badge");
		glacierBadge = new ItemBadge(glacierBadgeID, EnumBadges.Glacierbadge).setItemName("Glacier Badge");
		heatBadge = new ItemBadge(heatBadgeID, EnumBadges.Heatbadge).setItemName("Heat Badge");
		hiveBadge = new ItemBadge(hiveBadgeID, EnumBadges.Hivebadge).setItemName("Hive Badge");
		icicleBadge = new ItemBadge(icicleBadgeID, EnumBadges.Iciclebadge).setItemName("Icicle Badge");
		knuckleBadge = new ItemBadge(knuckleBadgeID, EnumBadges.Knucklebadge).setItemName("Knuckle Badge");
		marshBadge = new ItemBadge(marshBadgeID, EnumBadges.Marshbadge).setItemName("Marsh Badge");
		mindBadge = new ItemBadge(mindBadgeID, EnumBadges.Mindbadge).setItemName("Mind Badge");
		mineBadge = new ItemBadge(mineBadgeID, EnumBadges.Minebadge).setItemName("Mine Badge");
		mineralBadge = new ItemBadge(mineralBadgeID, EnumBadges.Mineralbadge).setItemName("Mineral Badge");
		plainBadge = new ItemBadge(plainBadgeID, EnumBadges.Plainbadge).setItemName("Plain Badge");
		rainbowBadge = new ItemBadge(rainbowBadgeID, EnumBadges.Rainbowbadge).setItemName("Rainbow Badge");
		rainBadge = new ItemBadge(rainBadgeID, EnumBadges.Rainbadge).setItemName("Rain Badge");
		relicBadge = new ItemBadge(relicBadgeID, EnumBadges.Relicbadge).setItemName("Relic Badge");
		soulBadge = new ItemBadge(soulBadgeID, EnumBadges.Soulbadge).setItemName("Soul Badge");
	}

	public static void addNames() {
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
