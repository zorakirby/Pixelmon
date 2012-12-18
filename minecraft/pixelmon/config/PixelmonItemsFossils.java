package pixelmon.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import pixelmon.items.ItemBlock;
import pixelmon.items.ItemFossilUncovered;
import pixelmon.items.ItemPokemonFossil;
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

	public static void load(Configuration cfg) {
		if (!isEnabled)
			return;
		fossilMachineItemID = cfg.get("fossils", "FossilMachineItem", 10016).getInt();

		helixFossilID = cfg.get("fossils", "HelixFossil", 10080).getInt();
		domeFossilID = cfg.get("fossils", "DomeFossil", 10081).getInt();
		oldAmberID = cfg.get("fossils", "OldAmber", 10082).getInt();
		rootFossilID = cfg.get("fossils", "RootFossil", 10083).getInt();
		clawFossilID = cfg.get("fossils", "ClawFossil", 10084).getInt();
		skullFossilID = cfg.get("fossils", "SkullFossil", 10085).getInt();
		armorFossilID = cfg.get("fossils", "ArmorFossil", 10086).getInt();
		coverFossilID = cfg.get("fossils", "PlumeFossil", 10087).getInt();
		plumeFossilID = cfg.get("fossils", "CoverFossil", 10088).getInt();

		helixFossilUncoveredID = cfg.get("fossils", "HelixFossilUncovered", 10089).getInt();
		domeFossilUncoveredID = cfg.get("fossils", "DomeFossilUncovered", 10090).getInt();
		oldAmberUncoveredID = cfg.get("fossils", "OldAmberUncovered", 10091).getInt();
		rootFossilUncoveredID = cfg.get("fossils", "RootFossilUncovered", 10092).getInt();
		clawFossilUncoveredID = cfg.get("fossils", "ClawFossilUncovered", 10093).getInt();
		skullFossilUncoveredID = cfg.get("fossils", "SkullFossilUncovered", 10094).getInt();
		armorFossilUncoveredID = cfg.get("fossils", "ArmorFossilUncovered", 10095).getInt();
		coverFossilUncoveredID = cfg.get("fossils", "PlumeFossilUncovered", 10096).getInt();
		plumeFossilUncoveredID = cfg.get("fossils", "CoverFossilUncovered", 10097).getInt();

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
