package pixelmon.config;

import java.lang.reflect.Field;

import pixelmon.enums.EnumPokeballs;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.ItemPokeballDisc;
import pixelmon.items.ItemPokeballLid;
import pixelmon.items.PixelmonItem;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelmonItemsPokeballs {

	public static int pokeBallID;
	public static int greatBallID;
	public static int ultraBallID;
	public static int masterBallID;
	public static int levelBallID;
	public static int moonBallID;
	public static int friendBallID;
	public static int loveBallID;

	public static int pokeBallLidID;
	public static int greatBallLidID;
	public static int ultraBallLidID;
	public static int levelBallLidID;
	public static int moonBallLidID;
	public static int friendBallLidID;
	public static int loveBallLidID;

	public static int pokeBallDiscID;
	public static int greatBallDiscID;
	public static int ultraBallDiscID;
	public static int levelBallDiscID;
	public static int moonBallDiscID;
	public static int friendBallDiscID;
	public static int loveBallDiscID;

	public static int ironDiscID;
	public static int ironBaseID;

	@Mod.Item(name = "Poke Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item pokeBall;
	@Mod.Item(name = "Great Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item greatBall;
	@Mod.Item(name = "Ultra Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item ultraBall;
	@Mod.Item(name = "Master Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item masterBall;
	@Mod.Item(name = "Level Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item levelBall;
	@Mod.Item(name = "Moon Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item moonBall;
	@Mod.Item(name = "Friend Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item friendBall;
	@Mod.Item(name = "Love Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item loveBall;

	@Mod.Item(name = "Poke Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item pokeBallLid;
	@Mod.Item(name = "Great Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item greatBallLid;
	@Mod.Item(name = "Ultra Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item ultraBallLid;
	@Mod.Item(name = "Level Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item levelBallLid;
	@Mod.Item(name = "Moon Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item moonBallLid;
	@Mod.Item(name = "Friend Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item friendBallLid;
	@Mod.Item(name = "Love Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item loveBallLid;

	@Mod.Item(name = "Poke Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item pokeBallDisc;
	@Mod.Item(name = "Great Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item greatBallDisc;
	@Mod.Item(name = "Ultra Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item ultraBallDisc;
	@Mod.Item(name = "Level Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item levelBallDisc;
	@Mod.Item(name = "Moon Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item moonBallDisc;
	@Mod.Item(name = "Friend Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item friendBallDisc;
	@Mod.Item(name = "Love Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item loveBallDisc;

	@Mod.Item(name = "Iron Disc", typeClass = "pixelmon.items.PixelmonItem")
	public static Item ironDisc;
	@Mod.Item(name = "Iron Base", typeClass = "pixelmon.items.PixelmonItem")
	public static Item ironBase;

	public static void load(Configuration cfg) {
		pokeBallID = cfg.getOrCreateIntProperty("PokeBall", "item", 8000).getInt();
		greatBallID = cfg.getOrCreateIntProperty("GreatBall", "item", 8001).getInt();
		ultraBallID = cfg.getOrCreateIntProperty("UltraBall", "item", 8002).getInt();
		masterBallID = cfg.getOrCreateIntProperty("MasterBall", "item", 8003).getInt();
		levelBallID = cfg.getOrCreateIntProperty("LevelBall", "item", 8004).getInt();
		moonBallID = cfg.getOrCreateIntProperty("MoonBall", "item", 8005).getInt();
		friendBallID = cfg.getOrCreateIntProperty("FriendBall", "item", 8006).getInt();
		loveBallID = cfg.getOrCreateIntProperty("LoveBall", "item", 8007).getInt();

		pokeBallLidID = cfg.getOrCreateIntProperty("PokeBallLid", "item", 8030).getInt();
		greatBallLidID = cfg.getOrCreateIntProperty("GreatBallLid", "item", 8031).getInt();
		ultraBallLidID = cfg.getOrCreateIntProperty("UltraBallLid", "item", 8032).getInt();
		levelBallLidID = cfg.getOrCreateIntProperty("LevelBallLid", "item", 8034).getInt();
		moonBallLidID = cfg.getOrCreateIntProperty("MoonBallLid", "item", 8035).getInt();
		friendBallLidID = cfg.getOrCreateIntProperty("FriendBallLid", "item", 8036).getInt();
		loveBallLidID = cfg.getOrCreateIntProperty("LoveBallLid", "item", 8037).getInt();

		pokeBallDiscID = cfg.getOrCreateIntProperty("PokeBallDisc", "item", 8050).getInt();
		greatBallDiscID = cfg.getOrCreateIntProperty("GreatBallDisc", "item", 8051).getInt();
		ultraBallDiscID = cfg.getOrCreateIntProperty("UltraBallDisc", "item", 8052).getInt();
		levelBallDiscID = cfg.getOrCreateIntProperty("LevelBallDisc", "item", 8054).getInt();
		moonBallDiscID = cfg.getOrCreateIntProperty("MoonBallDisc", "item", 8055).getInt();
		friendBallDiscID = cfg.getOrCreateIntProperty("FriendBallDisc", "item", 8056).getInt();
		loveBallDiscID = cfg.getOrCreateIntProperty("LoveBallDisc", "item", 8057).getInt();

		ironBaseID = cfg.getOrCreateIntProperty("IronBase", "item", 8070).getInt();
		ironDiscID = cfg.getOrCreateIntProperty("IronDisc", "item", 8071).getInt();

		pokeBall = new ItemPokeBall(pokeBallID, EnumPokeballs.PokeBall).setItemName("Poke Ball");
		ultraBall = new ItemPokeBall(ultraBallID, EnumPokeballs.UltraBall).setItemName("Ultra Ball");
		greatBall = new ItemPokeBall(greatBallID, EnumPokeballs.GreatBall).setItemName("Great Ball");
		masterBall = new ItemPokeBall(masterBallID, EnumPokeballs.MasterBall).setItemName("Master Ball");
		levelBall = new ItemPokeBall(levelBallID, EnumPokeballs.LevelBall).setItemName("Level Ball");
		moonBall = new ItemPokeBall(moonBallID, EnumPokeballs.MoonBall).setItemName("Moon Ball");
		friendBall = new ItemPokeBall(friendBallID, EnumPokeballs.FriendBall).setItemName("Friend Ball");
		loveBall = new ItemPokeBall(loveBallID, EnumPokeballs.LoveBall).setItemName("Love Ball");

		pokeBallLid = new ItemPokeballLid(pokeBallLidID, EnumPokeballs.PokeBall).setItemName("Poke Ball Lid");
		ultraBallLid = new ItemPokeballLid(ultraBallLidID, EnumPokeballs.UltraBall).setItemName("Ultra Ball Lid");
		greatBallLid = new ItemPokeballLid(greatBallLidID, EnumPokeballs.GreatBall).setItemName("Great Ball Lid");
		levelBallLid = new ItemPokeballLid(levelBallLidID, EnumPokeballs.LevelBall).setItemName("Level Ball Lid");
		moonBallLid = new ItemPokeballLid(moonBallLidID, EnumPokeballs.MoonBall).setItemName("Moon Ball Lid");
		friendBallLid = new ItemPokeballLid(friendBallLidID, EnumPokeballs.FriendBall).setItemName("Friend Ball Lid");
		loveBallLid = new ItemPokeballLid(loveBallLidID, EnumPokeballs.LoveBall).setItemName("Love Ball Lid");

		pokeBallDisc = new ItemPokeballDisc(pokeBallDiscID, EnumPokeballs.PokeBall).setItemName("Poke Ball Disc");
		ultraBallDisc = new ItemPokeballDisc(ultraBallDiscID, EnumPokeballs.UltraBall).setItemName("Ultra Ball Disc");
		greatBallDisc = new ItemPokeballDisc(greatBallDiscID, EnumPokeballs.GreatBall).setItemName("Great Ball Disc");
		levelBallDisc = new ItemPokeballDisc(levelBallDiscID, EnumPokeballs.LevelBall).setItemName("Level Ball Disc");
		moonBallDisc = new ItemPokeballDisc(moonBallDiscID, EnumPokeballs.MoonBall).setItemName("Moon Ball Disc");
		friendBallDisc = new ItemPokeballDisc(friendBallDiscID, EnumPokeballs.FriendBall).setItemName("Friend Ball Disc");
		loveBallDisc = new ItemPokeballDisc(loveBallDiscID, EnumPokeballs.LoveBall).setItemName("Love Ball Disc");

		ironBase = new PixelmonItem(ironBaseID).setIconIndex(9 + 10 * 16).setItemName("Iron Base").setTabToDisplayOn(CreativeTabs.tabMaterials);
		ironDisc = new PixelmonItem(ironDiscID).setIconIndex(9 + 11 * 16).setItemName("Iron Disc").setTabToDisplayOn(CreativeTabs.tabMaterials);
	}

	public static void addNames() {
		try {
			for (Field field : PixelmonItemsPokeballs.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					LanguageRegistry.addName(item, field.getAnnotation(Mod.Item.class).name());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Item getItemFromID(int itemId) {
		try {
			for (Field field : PixelmonItemsPokeballs.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item.shiftedIndex == itemId)
						return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Item getItemFromEnum(EnumPokeballs type) {

		try {
			for (Field field : PixelmonItemsPokeballs.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemPokeBall)
						if (((ItemPokeBall) item).type == type)
							return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Item getLidFromEnum(EnumPokeballs type) {
		try {
			for (Field field : PixelmonItemsPokeballs.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemPokeballLid)
						if (((ItemPokeballLid) item).pokeball == type)
							return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Item getDiscFromEnum(EnumPokeballs type) {
		try {
			for (Field field : PixelmonItemsPokeballs.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemPokeballDisc)
						if (((ItemPokeballDisc) item).pokeball == type)
							return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
