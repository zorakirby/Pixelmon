package pixelmon.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import pixelmon.items.ItemBlock;
import pixelmon.items.ItemFossilUncovered;
import pixelmon.items.ItemPokemonFossil;
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
	public static int helixFossilUncoveredID;
	public static int domeFossilUncoveredID;
	public static int oldAmberUncoveredID;
	public static int rootFossilUncoveredID;
	public static int clawFossilUncoveredID;
	public static int skullFossilUncoveredID;
	public static int armorFossilUncoveredID;
	public static int coverFossilUncoveredID;
	public static int plumeFossilUncoveredID;
	
	public static int fossilMachineDisplayID;
	public static int fossilMachineTankID;
	public static int fossilMachineTopID;
	public static int fossilMachineBaseID;
	
	public static int fossilMachineItemID;

	@Mod.Item(name = "Fossil Machine", typeClass = "pixelmon.items.ItemBlock")
	public static Item fossilMachineItem;

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
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item helixFossilUncovered;
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item domeFossilUncovered;
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.ItemPokemonFossil")
	public static Item oldAmberUncovered;
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item rootFossilUncovered;
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item clawFossilUncovered;
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item skullFossilUncovered;
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item armorFossilUncovered;
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item coverFossilUncovered;
	@Mod.Item(name = "Uncovered Fossil", typeClass = "pixelmon.items.PixelmonItem")
	public static Item plumeFossilUncovered;
	
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

		helixFossilUncoveredID = cfg.get("fossils", "HelixFossilUncovered", 21100).getInt();
		domeFossilUncoveredID = cfg.get("fossils", "DomeFossilUncovered", 21101).getInt();
		oldAmberUncoveredID = cfg.get("fossils", "OldAmberUncovered", 21102).getInt();
		rootFossilUncoveredID = cfg.get("fossils", "RootFossilUncovered", 21103).getInt();
		clawFossilUncoveredID = cfg.get("fossils", "ClawFossilUncovered", 21104).getInt();
		skullFossilUncoveredID = cfg.get("fossils", "SkullFossilUncovered", 21105).getInt();
		armorFossilUncoveredID = cfg.get("fossils", "ArmorFossilUncovered", 21106).getInt();
		coverFossilUncoveredID = cfg.get("fossils", "PlumeFossilUncovered", 21107).getInt();
		plumeFossilUncoveredID = cfg.get("fossils", "CoverFossilUncovered", 21108).getInt();
		

		fossilMachineItem = new ItemBlock(fossilMachineItemID, PixelmonBlocks.fossilMachine, 82).setItemName("Fossil Machine");
		helixFossil = new ItemPokemonFossil(helixFossilID, "Omanyte", "HelixFossil").setItemName("helixFossil").setIconIndex(10);
		domeFossil = new ItemPokemonFossil(domeFossilID, "Kabuto", "DomeFossil").setItemName("domeFossil").setIconIndex(26);
		oldAmber = new ItemPokemonFossil(oldAmberID, "Aerodactyl", "OldAmber").setItemName("oldAmber").setIconIndex(42);
		rootFossil = new ItemPokemonFossil(rootFossilID, "Lileep", "RootFossil").setItemName("rootFossil").setIconIndex(58);
		clawFossil = new ItemPokemonFossil(clawFossilID, "Anorith", "ClawFossil").setItemName("clawFossil").setIconIndex(74);
		skullFossil = new ItemPokemonFossil(skullFossilID, "Cranidos", "SkullFossil").setItemName("skullFossil").setIconIndex(90);
		armorFossil = new ItemPokemonFossil(armorFossilID, "Shieldon", "ArmorFossil").setItemName("armorFossil").setIconIndex(106);
		coverFossil = new ItemPokemonFossil(coverFossilID, "Tirtouga", "CoverFossil").setItemName("coverFossil").setIconIndex(122);
		plumeFossil = new ItemPokemonFossil(plumeFossilID, "Archen", "PlumeFossil").setItemName("plumeFossil").setIconIndex(138);

		fossilMachineTank = new PixelmonItem(fossilMachineTankID).setItemName("Fossil Machine Tank").setIconCoord(2, 7).setCreativeTab(CreativeTabs.tabDecorations);
		fossilMachineDisplay = new PixelmonItem(fossilMachineDisplayID).setItemName("Fossil Machine Display").setIconCoord(2, 6).setCreativeTab(CreativeTabs.tabDecorations);
		fossilMachineTop = new PixelmonItem(fossilMachineTopID).setItemName("Fossil Machine Top").setIconCoord(2, 8).setCreativeTab(CreativeTabs.tabDecorations);
		fossilMachineBase = new PixelmonItem(fossilMachineBaseID).setItemName("Fossil Machine Base").setIconCoord(2, 10).setCreativeTab(CreativeTabs.tabDecorations);
		
		helixFossilUncovered = new ItemFossilUncovered(helixFossilUncoveredID).setItemName("helixFossilUncovered").setIconIndex(11)
				.setCreativeTab(PixelmonCreativeTabs.natural);
		domeFossilUncovered = new ItemFossilUncovered(domeFossilUncoveredID).setItemName("domeFossilUncovered").setIconIndex(27)
				.setCreativeTab(PixelmonCreativeTabs.natural);
		oldAmberUncovered = new ItemFossilUncovered(oldAmberUncoveredID).setItemName("oldAmberUncovered").setIconIndex(43)
				.setCreativeTab(PixelmonCreativeTabs.natural);
		rootFossilUncovered = new ItemFossilUncovered(rootFossilUncoveredID).setItemName("rootFossilUncovered").setIconIndex(59)
				.setCreativeTab(PixelmonCreativeTabs.natural);
		clawFossilUncovered = new ItemFossilUncovered(clawFossilUncoveredID).setItemName("clawFossilUncovered").setIconIndex(75)
				.setCreativeTab(PixelmonCreativeTabs.natural);
		skullFossilUncovered = new ItemFossilUncovered(skullFossilUncoveredID).setItemName("skullFossilUncovered").setIconIndex(91)
				.setCreativeTab(PixelmonCreativeTabs.natural);
		armorFossilUncovered = new ItemFossilUncovered(armorFossilUncoveredID).setItemName("armorFossilUncovered").setIconIndex(107)
				.setCreativeTab(PixelmonCreativeTabs.natural);
		coverFossilUncovered = new ItemFossilUncovered(coverFossilUncoveredID).setItemName("coverFossilUncovered").setIconIndex(123)
				.setCreativeTab(PixelmonCreativeTabs.natural);
		plumeFossilUncovered = new ItemFossilUncovered(plumeFossilUncoveredID).setItemName("plumeFossilUncovered").setIconIndex(139)
				.setCreativeTab(PixelmonCreativeTabs.natural);

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
		ArrayList<ItemFossilUncovered> fossilList = new ArrayList<ItemFossilUncovered>();
		try {
			for (Field field : PixelmonItemsFossils.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemFossilUncovered)
						fossilList.add((ItemFossilUncovered) item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (fossilList.size() > 0) {
			return fossilList.get(new Random().nextInt(fossilList.size())).shiftedIndex;
		}
		return -1;
	}

	public static ItemPokemonFossil getFossilFromIndex(int currentFossil) {
		try {
			for (Field field : PixelmonItemsFossils.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemPokemonFossil)
						if (item.shiftedIndex == currentFossil)
							return (ItemPokemonFossil) item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
