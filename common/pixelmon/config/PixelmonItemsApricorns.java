package pixelmon.config;

import java.lang.reflect.Field;

import pixelmon.enums.EnumApricornTrees;
import pixelmon.enums.EnumApricorns;
import pixelmon.enums.EnumPokeballs;
import pixelmon.items.ItemApricorn;
import pixelmon.items.ItemApricornCooked;
import pixelmon.items.ItemPokeBall;
import net.minecraft.src.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelmonItemsApricorns {
	public static int apricornBlackID;
	public static int apricornWhiteID;
	public static int apricornPinkID;
	public static int apricornGreenID;
	public static int apricornBlueID;
	public static int apricornYellowID;
	public static int apricornRedID;
	public static int apricornBlackCookedID;
	public static int apricornWhiteCookedID;
	public static int apricornPinkCookedID;
	public static int apricornGreenCookedID;
	public static int apricornBlueCookedID;
	public static int apricornYellowCookedID;
	public static int apricornRedCookedID;

	@Mod.Item(name = "Black Apricorn", typeClass = "pixelmon.items.ItemApricorn")
	public static Item apricornBlack;
	@Mod.Item(name = "White Apricorn", typeClass = "pixelmon.items.ItemApricorn")
	public static Item apricornWhite;
	@Mod.Item(name = "Pink Apricorn", typeClass = "pixelmon.items.ItemApricorn")
	public static Item apricornPink;
	@Mod.Item(name = "Green Apricorn", typeClass = "pixelmon.items.ItemApricorn")
	public static Item apricornGreen;
	@Mod.Item(name = "Blue Apricorn", typeClass = "pixelmon.items.ItemApricorn")
	public static Item apricornBlue;
	@Mod.Item(name = "Yellow Apricorn", typeClass = "pixelmon.items.ItemApricorn")
	public static Item apricornYellow;
	@Mod.Item(name = "Red Apricorn", typeClass = "pixelmon.items.ItemApricorn")
	public static Item apricornRed;
	@Mod.Item(name = "Cooked Black Apricorn", typeClass = "pixelmon.items.ItemApricornCooked")
	public static Item apricornBlackCooked;
	@Mod.Item(name = "Cooked White Apricorn", typeClass = "pixelmon.items.ItemApricornCooked")
	public static Item apricornWhiteCooked;
	@Mod.Item(name = "Cooked Pink Apricorn", typeClass = "pixelmon.items.ItemApricornCooked")
	public static Item apricornPinkCooked;
	@Mod.Item(name = "Cooked Green Apricorn", typeClass = "pixelmon.items.ItemApricornCooked")
	public static Item apricornGreenCooked;
	@Mod.Item(name = "Cooked Blue Apricorn", typeClass = "pixelmon.items.ItemApricornCooked")
	public static Item apricornBlueCooked;
	@Mod.Item(name = "Cooked Yellow Apricorn", typeClass = "pixelmon.items.ItemApricornCooked")
	public static Item apricornYellowCooked;
	@Mod.Item(name = "Cooked Red Apricorn", typeClass = "pixelmon.items.ItemApricornCooked")
	public static Item apricornRedCooked;

	public static void load(Configuration cfg) {
		apricornBlackID = cfg.getOrCreateIntProperty("Black Apricorn", "item", 10100).getInt();
		apricornWhiteID = cfg.getOrCreateIntProperty("White Apricorn", "item", 10101).getInt();
		apricornPinkID = cfg.getOrCreateIntProperty("Pink Apricorn", "item", 10102).getInt();
		apricornGreenID = cfg.getOrCreateIntProperty("Green Apricorn", "item", 10103).getInt();
		apricornBlueID = cfg.getOrCreateIntProperty("Blue Apricorn", "item", 10104).getInt();
		apricornYellowID = cfg.getOrCreateIntProperty("Yellow Apricorn", "item", 10105).getInt();
		apricornRedID = cfg.getOrCreateIntProperty("Red Apricorn", "item", 10106).getInt();
		apricornBlackCookedID = cfg.getOrCreateIntProperty("Cooked Black Apricorn", "item", 10112).getInt();
		apricornWhiteCookedID = cfg.getOrCreateIntProperty("Cooked White Apricorn", "item", 10113).getInt();
		apricornPinkCookedID = cfg.getOrCreateIntProperty("Cooked Pink Apricorn", "item", 10114).getInt();
		apricornGreenCookedID = cfg.getOrCreateIntProperty("Cooked Green Apricorn", "item", 10115).getInt();
		apricornBlueCookedID = cfg.getOrCreateIntProperty("Cooked Blue Apricorn", "item", 10116).getInt();
		apricornYellowCookedID = cfg.getOrCreateIntProperty("Cooked Yellow Apricorn", "item", 10117).getInt();
		apricornRedCookedID = cfg.getOrCreateIntProperty("Cooked Red Apricorn", "item", 10118).getInt();

		apricornBlack = new ItemApricorn(apricornBlackID, EnumApricorns.Black).setItemName("Black Apricorn");
		apricornWhite = new ItemApricorn(apricornWhiteID, EnumApricorns.White).setItemName("White Apricorn");
		apricornPink = new ItemApricorn(apricornPinkID, EnumApricorns.Pink).setItemName("Pink Apricorn");
		apricornGreen = new ItemApricorn(apricornGreenID, EnumApricorns.Green).setItemName("Green Apricorn");
		apricornBlue = new ItemApricorn(apricornBlueID, EnumApricorns.Blue).setItemName("Blue Apricorn");
		apricornYellow = new ItemApricorn(apricornYellowID, EnumApricorns.Yellow).setItemName("Yellow Apricorn");
		apricornRed = new ItemApricorn(apricornRedID, EnumApricorns.Red).setItemName("Red Apricorn");
		apricornBlackCooked = new ItemApricornCooked(apricornBlackCookedID, EnumApricorns.Black).setItemName("Cooked Black Apricorn");
		apricornWhiteCooked = new ItemApricornCooked(apricornWhiteCookedID, EnumApricorns.White).setItemName("Cooked White Apricorn");
		apricornPinkCooked = new ItemApricornCooked(apricornPinkCookedID, EnumApricorns.Pink).setItemName("Cooked Pink Apricorn");
		apricornGreenCooked = new ItemApricornCooked(apricornGreenCookedID, EnumApricorns.Green).setItemName("Cooked Green Apricorn");
		apricornBlueCooked = new ItemApricornCooked(apricornBlueCookedID, EnumApricorns.Blue).setItemName("Cooked Blue Apricorn");
		apricornYellowCooked = new ItemApricornCooked(apricornYellowCookedID, EnumApricorns.Yellow).setItemName("Cooked Yellow Apricorn");
		apricornRedCooked = new ItemApricornCooked(apricornRedCookedID, EnumApricorns.Red).setItemName("Cooked Red Apricorn");
	}

	public static void addNames() {
		PixelmonItemsPokeballs.addNames();
		try {
			for (Field field : PixelmonItems.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					LanguageRegistry.addName(item, field.getAnnotation(Mod.Item.class).name());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Item getCookedApricorn(EnumApricorns type) {
		try {
			for (Field field : PixelmonItemsApricorns.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemApricornCooked)
						if (((ItemApricornCooked) item).apricorn == type)
							return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Item getApricorn(EnumApricorns type) {
		try {
			for (Field field : PixelmonItemsApricorns.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemApricorn)
						if (((ItemApricorn) item).apricorn == type)
							return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
