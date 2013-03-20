package pixelmon.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import pixelmon.enums.EnumPokemon;
import pixelmon.items.ItemBlock;
import pixelmon.items.ItemCoveredFossil;
import pixelmon.items.ItemFossil;
import pixelmon.items.PixelmonItem;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelmonItemsFossils {
	public static final boolean isEnabled = true;
	public static int helixFossilID;
	public static int domeFossilID;
	public static int oldAmberID;
	public static int rootFossilID;
	public static int clawFossilID;
	public static int skullFossilID;
	public static int armorFossilID;
	public static int coverFossilID;
	public static int plumeFossilID;
	public static int helixFossilCoveredID;
	public static int domeFossilCoveredID;
	public static int oldAmberCoveredID;
	public static int rootFossilCoveredID;
	public static int clawFossilCoveredID;
	public static int skullFossilCoveredID;
	public static int armorFossilCoveredID;
	public static int coverFossilCoveredID;
	public static int plumeFossilCoveredID;

	public static int fossilMachineDisplayID;
	public static int fossilMachineTankID;
	public static int fossilMachineTopID;
	public static int fossilMachineBaseID;

	public static int fossilMachineItemID;
	public static int fossilCleanerItemID;

	@Mod.Item(name = "Fossil Machine", typeClass = "pixelmon.items.ItemBlock")
	public static Item fossilMachineItem;
	@Mod.Item(name = "Fossil Cleaner", typeClass = "pixelmon.items.ItemBlock")
	public static Item fossilCleanerItem;

	@Mod.Item(name = "Helix Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item helixFossil;
	@Mod.Item(name = "Dome Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item domeFossil;
	@Mod.Item(name = "Old Amber", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item oldAmber;
	@Mod.Item(name = "Root Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item rootFossil;
	@Mod.Item(name = "Claw Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item clawFossil;
	@Mod.Item(name = "Skull Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item skullFossil;
	@Mod.Item(name = "Armor Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item armorFossil;
	@Mod.Item(name = "Cover Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item coverFossil;
	@Mod.Item(name = "Plume Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item plumeFossil;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item helixFossilCovered;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item domeFossilCovered;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item oldAmberCovered;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item rootFossilCovered;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item clawFossilCovered;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item skullFossilCovered;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item armorFossilCovered;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item coverFossilCovered;
	@Mod.Item(name = "Covered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item plumeFossilCovered;

	@Mod.Item(name = "Fossil Machine Tank", typeClass = "pixelmon.items.PixelmonItem")
	public static Item fossilMachineTank;
	@Mod.Item(name = "Fossil Machine Display", typeClass = "pixelmon.items.PixelmonItem")
	public static Item fossilMachineDisplay;
	@Mod.Item(name = "Fossil Machine Top", typeClass = "pixelmon.items.PixelmonItem")
	public static Item fossilMachineTop;
	@Mod.Item(name = "Fossil Machine Base", typeClass = "pixelmon.items.PixelmonItem")
	public static Item fossilMachineBase;

	public static void load(Configuration cfg) {
		if (!isEnabled)
			return;
		fossilMachineItemID = cfg.get("fossils", "FossilMachineItem", 20000).getInt();
		fossilCleanerItemID = cfg.get("item", "FossilCleanerItem", 10028).getInt();
		fossilMachineTankID = cfg.get("fossils", "FossilMachineTank", 20001).getInt();
		fossilMachineDisplayID = cfg.get("fossils", "FossilMachineDisplay", 20002).getInt();
		fossilMachineTopID = cfg.get("fossils", "FossilMachineTop", 20003).getInt();
		fossilMachineBaseID = cfg.get("fossils", "FossilMachineBase", 20004).getInt();

		helixFossilID = cfg.get("fossils", "HelixFossil", 21000).getInt();
		domeFossilID = cfg.get("fossils", "DomeFossil", 21001).getInt();
		oldAmberID = cfg.get("fossils", "OldAmber", 21002).getInt();
		rootFossilID = cfg.get("fossils", "RootFossil", 21003).getInt();
		clawFossilID = cfg.get("fossils", "ClawFossil", 21004).getInt();
		skullFossilID = cfg.get("fossils", "SkullFossil", 21005).getInt();
		armorFossilID = cfg.get("fossils", "ArmorFossil", 21006).getInt();
		coverFossilID = cfg.get("fossils", "PlumeFossil", 21007).getInt();
		plumeFossilID = cfg.get("fossils", "CoverFossil", 21008).getInt();

		helixFossilCoveredID = cfg.get("fossils", "HelixFossilCovered", 21100).getInt();
		domeFossilCoveredID = cfg.get("fossils", "DomeFossilCovered", 21101).getInt();
		oldAmberCoveredID = cfg.get("fossils", "OldAmberCovered", 21102).getInt();
		rootFossilCoveredID = cfg.get("fossils", "RootFossilCovered", 21103).getInt();
		clawFossilCoveredID = cfg.get("fossils", "ClawFossilCovered", 21104).getInt();
		skullFossilCoveredID = cfg.get("fossils", "SkullFossilCovered", 21105).getInt();
		armorFossilCoveredID = cfg.get("fossils", "ArmorFossilCovered", 21106).getInt();
		coverFossilCoveredID = cfg.get("fossils", "PlumeFossilCovered", 21107).getInt();
		plumeFossilCoveredID = cfg.get("fossils", "CoverFossilCovered", 21108).getInt();

		fossilMachineItem = new ItemBlock(fossilMachineItemID, PixelmonBlocks.fossilMachine, "fossilmachine", "Fossil Machine");
		fossilCleanerItem = new ItemBlock(fossilCleanerItemID, PixelmonBlocks.fossilCleaner, "fossilcleaner", "Fossil Cleaner");

		helixFossil = new ItemFossil(helixFossilID, "Omanyte", "HelixFossil");
		domeFossil = new ItemFossil(domeFossilID, "Kabuto", "DomeFossil");
		oldAmber = new ItemFossil(oldAmberID, "Aerodactyl", "OldAmber");
		rootFossil = new ItemFossil(rootFossilID, "Lileep", "RootFossil");
		clawFossil = new ItemFossil(clawFossilID, "Anorith", "ClawFossil");
		skullFossil = new ItemFossil(skullFossilID, "Cranidos", "SkullFossil");
		armorFossil = new ItemFossil(armorFossilID, "Shieldon", "ArmorFossil");
		coverFossil = new ItemFossil(coverFossilID, "Tirtouga", "CoverFossil");
		plumeFossil = new ItemFossil(plumeFossilID, "Archen", "PlumeFossil");

		fossilMachineTank = new PixelmonItem(fossilMachineTankID, "blocks/fossilmachinetank", "Fossil Machine Tank").setCreativeTab(CreativeTabs.tabDecorations);
		fossilMachineDisplay = new PixelmonItem(fossilMachineDisplayID, "blocks/fossilmachinedisplay", "Fossil Machine Display").setCreativeTab(CreativeTabs.tabDecorations);
		fossilMachineTop = new PixelmonItem(fossilMachineTopID, "blocks/fossilmachinetop", "Fossil Machine Top").setCreativeTab(CreativeTabs.tabDecorations);
		fossilMachineBase = new PixelmonItem(fossilMachineBaseID, "blocks/fossilmachinebase", "Fossil Machine Base").setCreativeTab(CreativeTabs.tabDecorations);

		helixFossilCovered = new ItemCoveredFossil(helixFossilCoveredID, (ItemFossil) helixFossil).setCreativeTab(PixelmonCreativeTabs.natural);
		domeFossilCovered = new ItemCoveredFossil(domeFossilCoveredID, (ItemFossil) domeFossil).setCreativeTab(PixelmonCreativeTabs.natural);
		oldAmberCovered = new ItemCoveredFossil(oldAmberCoveredID, (ItemFossil) oldAmber).setCreativeTab(PixelmonCreativeTabs.natural);
		rootFossilCovered = new ItemCoveredFossil(rootFossilCoveredID, (ItemFossil) rootFossil).setCreativeTab(PixelmonCreativeTabs.natural);
		clawFossilCovered = new ItemCoveredFossil(clawFossilCoveredID, (ItemFossil) clawFossil).setCreativeTab(PixelmonCreativeTabs.natural);
		skullFossilCovered = new ItemCoveredFossil(skullFossilCoveredID, (ItemFossil) skullFossil).setCreativeTab(PixelmonCreativeTabs.natural);
		armorFossilCovered = new ItemCoveredFossil(armorFossilCoveredID, (ItemFossil) armorFossil).setCreativeTab(PixelmonCreativeTabs.natural);
		coverFossilCovered = new ItemCoveredFossil(coverFossilCoveredID, (ItemFossil) coverFossil).setCreativeTab(PixelmonCreativeTabs.natural);
		plumeFossilCovered = new ItemCoveredFossil(plumeFossilCoveredID, (ItemFossil) plumeFossil).setCreativeTab(PixelmonCreativeTabs.natural);

	}

	public static void addNames() {
		if (!isEnabled)
			return;
		try {
			for (Field field : PixelmonItemsFossils.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					LanguageRegistry.addName(item, field.getAnnotation(Mod.Item.class).name());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getRandomFossilId() {
		ArrayList<ItemCoveredFossil> fossilList = new ArrayList<ItemCoveredFossil>();
		try {
			for (Field field : PixelmonItemsFossils.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemCoveredFossil)
						if (EnumPokemon.hasPokemon(((ItemCoveredFossil) item).cleanedFossil.pokemon))
							fossilList.add((ItemCoveredFossil) item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (fossilList.size() > 0) {
			return fossilList.get(new Random().nextInt(fossilList.size())).itemID;
		}
		return -1;
	}

	public static ItemFossil getFossilFromIndex(int currentFossil) {
		try {
			for (Field field : PixelmonItemsFossils.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemFossil)
						if (item.itemID == currentFossil)
							return (ItemFossil) item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ItemCoveredFossil getCoveredFossilFromIndex(int currentFossil) {
		try {
			for (Field field : PixelmonItemsFossils.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemCoveredFossil)
						if (item.itemID == currentFossil)
							return (ItemCoveredFossil) item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Item getItemFromIndex(int itemID) {
		try {
			for (Field field : PixelmonItemsFossils.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item.itemID == itemID)
						return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
