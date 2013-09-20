package pixelmon.config;

import java.lang.reflect.Field;

import pixelmon.Pixelmon;
import pixelmon.enums.heldItems.EnumChoiceItems;
import pixelmon.enums.heldItems.EnumEvAdjustingItems;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;
import pixelmon.items.heldItems.*;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelmonItemsHeld {
	public static int luckyEggID;
	public static int expShareID;
	public static int everStoneID;
	
	public static int berryOranID;
	public static int berryRawstID;
	public static int berryLeppaID;

	public static int choiceBandID;
	public static int choiceScarfID;
	public static int choiceSpectaclesID;

	public static int machoBraceID;
	public static int powerWeightID;
	public static int powerBracerID;
	public static int powerBeltID;
	public static int powerLensID;
	public static int powerBandID;
	public static int powerAnkletID;
	public static int sootheBellID;
	public static int smokeBallID;
	public static int wideLensID;
	public static int leftoversID;


	@Mod.Item(name = "Everstone", typeClass = "pixelmon.items.ItemEvolutionStone")
	public static Item everStone;

	@Mod.Item(name = "Lucky Egg", typeClass = "pixelmon.items.heldItems.ItemLuckyEgg")
	public static Item luckyEgg;
	@Mod.Item(name = "EXP Share", typeClass = "pixelmon.items.heldItems.ItemExpShare")
	public static Item expShare;

	@Mod.Item(name = "Choice Band", typeClass = "pixelmon.items.heldItems.ChoiceItem")
	public static Item choiceBand;
	@Mod.Item(name = "Choice Scarf", typeClass = "pixelmon.items.heldItems.ChoiceItem")
	public static Item choiceScarf;
	@Mod.Item(name = "Choice Spectacles", typeClass = "pixelmon.items.heldItems.ChoiceItem")
	public static Item choiceSpectacles;

	@Mod.Item(name = "Macho Brace", typeClass = "pixelmon.items.heldItems.EVAdjusting")
	public static Item machoBrace;
	@Mod.Item(name = "Power Weight", typeClass = "pixelmon.items.heldItems.EVAdjusting")
	public static Item powerWeight;
	@Mod.Item(name = "Power Bracer", typeClass = "pixelmon.items.heldItems.EVAdjusting")
	public static Item powerBracer;
	@Mod.Item(name = "Power Belt", typeClass = "pixelmon.items.heldItems.EVAdjusting")
	public static Item powerBelt;
	@Mod.Item(name = "Power Lens", typeClass = "pixelmon.items.heldItems.EVAdjusting")
	public static Item powerLens;
	@Mod.Item(name = "Power Band", typeClass = "pixelmon.items.heldItems.EVAdjusting")
	public static Item powerBand;
	@Mod.Item(name = "Power Anklet", typeClass = "pixelmon.items.heldItems.EVAdjusting")
	public static Item powerAnklet;

	@Mod.Item(name = "Oran Berry", typeClass = "pixelmon.items.heldItems.ItemBerryOran")
	public static Item berryOran;
	@Mod.Item(name = "Rawst Berry", typeClass = "pixelmon.items.heldItems.ItemBerryRawst")
	public static Item berryRawst;
	@Mod.Item(name = "Leppa Berry", typeClass = "pixelmon.items.heldItems.ItemBerryLeppa")
	public static Item berryLeppa;
	
	@Mod.Item(name = "Soothe Bell", typeClass = "pixelmon.items.heldItems.ItemSootheBell")
	public static Item sootheBell;
	@Mod.Item(name = "Smoke Ball", typeClass = "pixelmon.items.heldItems.ItemSmokeBall")
	public static Item smokeBall;
	@Mod.Item(name = "Wide Lens", typeClass = "pixelmon.items.heldItems.ItemWideLens")
	public static Item wideLens;
	@Mod.Item(name = "Leftovers", typeClass = "pixelmon.items.heldItems.ItemLeftovers")
	public static Item leftovers;


	
	public static void load(Configuration cfg) {
		everStoneID = cfg.get("heldItem", "EverStone", 12000).getInt();

		luckyEggID = cfg.get("heldItem", "LuckyEgg", 12001).getInt();
		expShareID = cfg.get("heldItem", "EXPShare", 12002).getInt();

		choiceBandID = cfg.get("heldItem", "ChoiceBand", 12006).getInt();
		choiceScarfID = cfg.get("heldItem", "ChoiceScarf", 12007).getInt();
		choiceSpectaclesID = cfg.get("heldItem", "ChoiceSpectacles", 12008).getInt();

		machoBraceID = cfg.get("heldItem", "MachoBrace", 12009).getInt();
		powerWeightID = cfg.get("heldItem", "PowerWeight", 12010).getInt();
		powerBracerID = cfg.get("heldItem", "PowerBracer", 12011).getInt();
		powerBeltID = cfg.get("heldItem", "PowerBelt", 12012).getInt();
		powerLensID = cfg.get("heldItem", "PowerLens", 12013).getInt();
		powerBandID = cfg.get("heldItem", "PowerBand", 12014).getInt();
		powerAnkletID = cfg.get("heldItem", "PowerAnklet", 12015).getInt();

		berryOranID = cfg.get("berry", "OranBerry", 12003).getInt();
		berryRawstID = cfg.get("berry", "RawstBerry", 12004).getInt();
		berryLeppaID = cfg.get("berry", "LeppaBerry", 12005).getInt();
		sootheBellID = cfg.get("heldItem", "SootheBell", 12016).getInt();
		smokeBallID = cfg.get("heldItem", "SmokeBall", 12017).getInt();
		wideLensID = cfg.get("heldItem", "WideLens", 12018).getInt();
		leftoversID = cfg.get("heldItem", "Leftovers", 12019).getInt();



		luckyEgg = new ItemLuckyEgg(luckyEggID);
		expShare = new ItemExpShare(expShareID);
		choiceBand = new ChoiceItem(choiceBandID, EnumChoiceItems.ChoiceBand, "Choice Band");
		choiceScarf = new ChoiceItem(choiceScarfID, EnumChoiceItems.ChoiceScarf, "Choice Scarf");
		choiceSpectacles = new ChoiceItem(choiceSpectaclesID, EnumChoiceItems.ChoiceSpecs, "Choice Specs");

		machoBrace = new EVAdjusting(machoBraceID, EnumEvAdjustingItems.MachoBrace, "Macho Brace");
		powerWeight = new EVAdjusting(powerWeightID, EnumEvAdjustingItems.PowerWeight, "Power Weight");
		powerBracer = new EVAdjusting(powerBracerID, EnumEvAdjustingItems.PowerBracer, "Power Bracer");
		powerBelt = new EVAdjusting(powerBeltID, EnumEvAdjustingItems.PowerBelt, "Power Belt");
		powerLens = new EVAdjusting(powerLensID, EnumEvAdjustingItems.PowerLens, "Power Lens");
		powerBand = new EVAdjusting(powerBandID, EnumEvAdjustingItems.PowerBand, "Power Band");
		powerAnklet = new EVAdjusting(powerAnkletID, EnumEvAdjustingItems.PowerAnklet, "Power Anklet");

		berryOran = new ItemBerryOran(berryOranID);
		berryLeppa = new ItemBerryLeppa(berryLeppaID);
		berryRawst = new ItemBerryRawst(berryRawstID);
		sootheBell = new ItemSootheBell(sootheBellID);
		smokeBall = new ItemSmokeBall(smokeBallID);
		wideLens = new ItemWideLens(wideLensID);
		leftovers = new ItemLeftovers(leftoversID);
		everStone = new ItemEverstone(everStoneID);
	}

	public static void addNames() {
		try {
			for (Field field : PixelmonItemsHeld.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					LanguageRegistry.addName(item, field.getAnnotation(Mod.Item.class).name());
					Pixelmon.proxy.registerBossDropItem(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ItemHeld getHeldItem(int id) {
		try {
			for (Field field : PixelmonItemsHeld.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemHeld)
						if (item.itemID == id)
							return (ItemHeld) item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
