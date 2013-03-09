package pixelmon.config;

import java.lang.reflect.Field;

import pixelmon.Pixelmon;
import pixelmon.enums.heldItems.EnumChoiceItems;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;
import pixelmon.items.heldItems.ChoiceItem;
import pixelmon.items.heldItems.ItemBerryLeppa;
import pixelmon.items.heldItems.ItemBerryOran;
import pixelmon.items.heldItems.ItemBerryRawst;
import pixelmon.items.heldItems.ItemEverstone;
import pixelmon.items.heldItems.ItemExpShare;
import pixelmon.items.heldItems.ItemLuckyEgg;
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

	
	@Mod.Item(name = "Oran Berry", typeClass = "pixelmon.items.heldItems.ItemBerryOran")
	public static Item berryOran;
	@Mod.Item(name = "Rawst Berry", typeClass = "pixelmon.items.heldItems.ItemBerryRawst")
	public static Item berryRawst;
	@Mod.Item(name = "Leppa Berry", typeClass = "pixelmon.items.heldItems.ItemBerryLeppa")
	public static Item berryLeppa;
	
	

	public static void load(Configuration cfg) {
		everStoneID = cfg.get("heldItem", "EverStone", 12000).getInt();

		luckyEggID = cfg.get("heldItem", "LuckyEgg", 12001).getInt();
		expShareID = cfg.get("heldItem", "EXPShare", 12002).getInt();

		choiceBandID = cfg.get("heldItem", "ChoiceBand", 12006).getInt();
		choiceScarfID = cfg.get("heldItem", "ChoiceScarf", 12007).getInt();
		choiceSpectaclesID = cfg.get("heldItem", "ChoiceSpectacles", 12008).getInt();
		
		berryOranID = cfg.get("berry", "OranBerry", 12003).getInt();
		berryRawstID = cfg.get("berry", "RawstBerry", 12004).getInt();
		berryLeppaID = cfg.get("berry", "LeppaBerry", 12005).getInt();

		luckyEgg = new ItemLuckyEgg(luckyEggID).setItemName("Lucky Egg").setIconIndex(7);
		expShare = new ItemExpShare(expShareID).setItemName("Exp Share").setIconIndex(23);
		choiceBand = new ChoiceItem(choiceBandID, EnumChoiceItems.ChoiceBand).setItemName("Choice Band");
		choiceScarf = new ChoiceItem(choiceScarfID, EnumChoiceItems.ChoiceScarf).setItemName("Choice Scarf");
		choiceSpectacles = new ChoiceItem(choiceSpectaclesID, EnumChoiceItems.ChoiceSpecs).setItemName("Choice Spectacles");

		berryOran = new ItemBerryOran(berryOranID).setItemName("OranBerry").setIconIndex(5);
		berryLeppa = new ItemBerryLeppa(berryLeppaID).setItemName("LeppaBerry").setIconIndex(21);
		berryRawst = new ItemBerryRawst(berryRawstID).setItemName("RawstBerry").setIconIndex(37);

		everStone = new ItemEverstone(everStoneID).setItemName("Everstone").setIconIndex(4 + 6 * 16);
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
