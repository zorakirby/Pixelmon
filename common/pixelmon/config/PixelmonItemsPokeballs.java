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
	public static int safariBallID;
	public static int heavyBallID;
	public static int fastBallID;
	public static int repeatBallID;
	public static int timerBallID;
	public static int nestBallID;
	public static int netBallID;
	public static int diveBallID;
	public static int luxuryBallID;
	public static int healBallID;
	public static int duskBallID;
	public static int premierBallID;

	public static int pokeBallLidID;
	public static int greatBallLidID;
	public static int ultraBallLidID;
	public static int levelBallLidID;
	public static int moonBallLidID;
	public static int friendBallLidID;
	public static int loveBallLidID;
	public static int safariBallLidID;
	public static int heavyBallLidID;
	public static int fastBallLidID;
	public static int repeatBallLidID;
	public static int timerBallLidID;
	public static int nestBallLidID;
	public static int netBallLidID;
	public static int diveBallLidID;
	public static int luxuryBallLidID;
	public static int healBallLidID;
	public static int duskBallLidID;
	public static int premierBallLidID;

	public static int pokeBallDiscID;
	public static int greatBallDiscID;
	public static int ultraBallDiscID;
	public static int levelBallDiscID;
	public static int moonBallDiscID;
	public static int friendBallDiscID;
	public static int loveBallDiscID;
	public static int safariBallDiscID;
	public static int heavyBallDiscID;
	public static int fastBallDiscID;
	public static int repeatBallDiscID;
	public static int timerBallDiscID;
	public static int nestBallDiscID;
	public static int netBallDiscID;
	public static int diveBallDiscID;
	public static int luxuryBallDiscID;
	public static int healBallDiscID;
	public static int duskBallDiscID;
	public static int premierBallDiscID;

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
	@Mod.Item(name = "Safari Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item safariBall;
	@Mod.Item(name = "Heavy Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item heavyBall;
	@Mod.Item(name = "Fast Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item fastBall;
	@Mod.Item(name = "Repeat Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item repeatBall;
	@Mod.Item(name = "Timer Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item timerBall;
	@Mod.Item(name = "Nest Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item nestBall;
	@Mod.Item(name = "Net Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item netBall;
	@Mod.Item(name = "Dive Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item diveBall;
	@Mod.Item(name = "Luxury Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item luxuryBall;
	@Mod.Item(name = "Heal Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item healBall;
	@Mod.Item(name = "Dusk Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item duskBall;
	@Mod.Item(name = "Premier Ball", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item premierBall;

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
	@Mod.Item(name = "Safari Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item safariBallLid;
	@Mod.Item(name = "Heavy Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item heavyBallLid;
	@Mod.Item(name = "Fast Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item fastBallLid;
	@Mod.Item(name = "Repeat Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item repeatBallLid;
	@Mod.Item(name = "Timer Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item timerBallLid;
	@Mod.Item(name = "Nest Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item nestBallLid;
	@Mod.Item(name = "Net Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item netBallLid;
	@Mod.Item(name = "Dive Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item diveBallLid;
	@Mod.Item(name = "Luxury Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item luxuryBallLid;
	@Mod.Item(name = "Heal Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item healBallLid;
	@Mod.Item(name = "Dusk Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item duskBallLid;
	@Mod.Item(name = "Premier Ball Lid", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item premierBallLid;

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
	@Mod.Item(name = "Safari Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item safariBallDisc;
	@Mod.Item(name = "Heavy Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item heavyBallDisc;
	@Mod.Item(name = "Fast Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item fastBallDisc;
	@Mod.Item(name = "Repeat Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item repeatBallDisc;
	@Mod.Item(name = "Timer Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item timerBallDisc;
	@Mod.Item(name = "Nest Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item nestBallDisc;
	@Mod.Item(name = "Net Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item netBallDisc;
	@Mod.Item(name = "Dive Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item diveBallDisc;
	@Mod.Item(name = "Luxury Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item luxuryBallDisc;
	@Mod.Item(name = "Heal Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item healBallDisc;
	@Mod.Item(name = "Dusk Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item duskBallDisc;
	@Mod.Item(name = "Premier Ball Disc", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item premierBallDisc;
	
	@Mod.Item(name = "Iron Disc", typeClass = "pixelmon.items.PixelmonItem")
	public static Item ironDisc;
	@Mod.Item(name = "Iron Base", typeClass = "pixelmon.items.PixelmonItem")
	public static Item ironBase;

	public static void load(Configuration cfg) {
		pokeBallID = cfg.get("PokeBall", "item", 8000).getInt();
		greatBallID = cfg.get("GreatBall", "item", 8001).getInt();
		ultraBallID = cfg.get("UltraBall", "item", 8002).getInt();
		masterBallID = cfg.get("MasterBall", "item", 8003).getInt();
		levelBallID = cfg.get("LevelBall", "item", 8004).getInt();
		moonBallID = cfg.get("MoonBall", "item", 8005).getInt();
		friendBallID = cfg.get("FriendBall", "item", 8006).getInt();
		loveBallID = cfg.get("LoveBall", "item", 8007).getInt();
		safariBallID = cfg.get("SafariBall", "item", 8008).getInt();
		heavyBallID = cfg.get("HeavyBall", "item", 8009).getInt();
		fastBallID = cfg.get("FastBall", "item", 8010).getInt();
		repeatBallID = cfg.get("RepeatBall", "item", 8011).getInt();
		timerBallID = cfg.get("TimerBall", "item", 8012).getInt();
		nestBallID = cfg.get("NestBall", "item", 8013).getInt();
		netBallID = cfg.get("NetBall", "item", 8014).getInt();
		diveBallID = cfg.get("DiveBall", "item", 8015).getInt();
		luxuryBallID = cfg.get("LuxuryBall", "item", 8016).getInt();
		healBallID = cfg.get("HealBall", "item", 8017).getInt();
		duskBallID = cfg.get("DuskBall", "item", 8018).getInt();
		premierBallID = cfg.get("PremierBall", "item", 8019).getInt();

		pokeBallLidID = cfg.get("PokeBallLid", "item", 8030).getInt();
		greatBallLidID = cfg.get("GreatBallLid", "item", 8031).getInt();
		ultraBallLidID = cfg.get("UltraBallLid", "item", 8032).getInt();
		levelBallLidID = cfg.get("LevelBallLid", "item", 8034).getInt();
		moonBallLidID = cfg.get("MoonBallLid", "item", 8035).getInt();
		friendBallLidID = cfg.get("FriendBallLid", "item", 8036).getInt();
		loveBallLidID = cfg.get("LoveBallLid", "item", 8037).getInt();
		safariBallLidID = cfg.get("SafariBallLid", "item", 8038).getInt();
		heavyBallLidID = cfg.get("HeavyBallLid", "item", 8039).getInt();
		fastBallLidID = cfg.get("FastBallLid", "item", 8040).getInt();
		repeatBallLidID = cfg.get("RepeatBallLid", "item", 8041).getInt();
		timerBallLidID = cfg.get("TimerBallLid", "item", 8042).getInt();
		nestBallLidID = cfg.get("NestBallLid", "item", 8043).getInt();
		netBallLidID = cfg.get("NetBallLid", "item", 8044).getInt();
		diveBallLidID = cfg.get("DiveBallLid", "item", 8045).getInt();
		luxuryBallLidID = cfg.get("LuxuryBallLid", "item", 8046).getInt();
		healBallLidID = cfg.get("HealBallLid", "item", 8047).getInt();
		duskBallLidID = cfg.get("DuskBallLid", "item", 8048).getInt();
		premierBallLidID = cfg.get("PremierBallLid", "item", 8049).getInt();

		pokeBallDiscID = cfg.get("PokeBallDisc", "item", 8050).getInt();
		greatBallDiscID = cfg.get("GreatBallDisc", "item", 8051).getInt();
		ultraBallDiscID = cfg.get("UltraBallDisc", "item", 8052).getInt();
		levelBallDiscID = cfg.get("LevelBallDisc", "item", 8054).getInt();
		moonBallDiscID = cfg.get("MoonBallDisc", "item", 8055).getInt();
		friendBallDiscID = cfg.get("FriendBallDisc", "item", 8056).getInt();
		loveBallDiscID = cfg.get("LoveBallDisc", "item", 8057).getInt();
		safariBallDiscID = cfg.get("SafariBallDisc", "item", 8058).getInt();
		heavyBallDiscID = cfg.get("HeavyBallDisc", "item", 8059).getInt();
		fastBallDiscID = cfg.get("FastBallDisc", "item", 8060).getInt();
		repeatBallDiscID = cfg.get("RepeatBallDisc", "item", 8061).getInt();
		timerBallDiscID = cfg.get("TimerBallDisc", "item", 8062).getInt();
		nestBallDiscID = cfg.get("NestBallDisc", "item", 8063).getInt();
		netBallDiscID = cfg.get("NetBallDisc", "item", 8064).getInt();
		diveBallDiscID = cfg.get("DiveBallDisc", "item", 8065).getInt();
		luxuryBallDiscID = cfg.get("LuxuryBallDisc", "item", 8066).getInt();
		healBallDiscID = cfg.get("HealBallDisc", "item", 8067).getInt();
		duskBallDiscID = cfg.get("DuskBallDisc", "item", 8068).getInt();
		premierBallDiscID = cfg.get("PremierBallDisc", "item", 8069).getInt();

		ironBaseID = cfg.get("IronBase", "item", 8070).getInt();
		ironDiscID = cfg.get("IronDisc", "item", 8071).getInt();

		pokeBall = new ItemPokeBall(pokeBallID, EnumPokeballs.PokeBall).setItemName("Poke Ball");
		ultraBall = new ItemPokeBall(ultraBallID, EnumPokeballs.UltraBall).setItemName("Ultra Ball");
		greatBall = new ItemPokeBall(greatBallID, EnumPokeballs.GreatBall).setItemName("Great Ball");
		masterBall = new ItemPokeBall(masterBallID, EnumPokeballs.MasterBall).setItemName("Master Ball");
		levelBall = new ItemPokeBall(levelBallID, EnumPokeballs.LevelBall).setItemName("Level Ball");
		moonBall = new ItemPokeBall(moonBallID, EnumPokeballs.MoonBall).setItemName("Moon Ball");
		friendBall = new ItemPokeBall(friendBallID, EnumPokeballs.FriendBall).setItemName("Friend Ball");
		loveBall = new ItemPokeBall(loveBallID, EnumPokeballs.LoveBall).setItemName("Love Ball");
		safariBall = new ItemPokeBall(safariBallID, EnumPokeballs.SafariBall).setItemName("Safari Ball");
		heavyBall = new ItemPokeBall(heavyBallID, EnumPokeballs.HeavyBall).setItemName("Heavy Ball");
		fastBall = new ItemPokeBall(fastBallID, EnumPokeballs.FastBall).setItemName("Fast Ball");
		repeatBall = new ItemPokeBall(repeatBallID, EnumPokeballs.RepeatBall).setItemName("Repeat Ball");
		timerBall = new ItemPokeBall(timerBallID, EnumPokeballs.TimerBall).setItemName("Timer Ball");
		nestBall = new ItemPokeBall(nestBallID, EnumPokeballs.NestBall).setItemName("Nest Ball");
		netBall = new ItemPokeBall(netBallID, EnumPokeballs.NetBall).setItemName("Net Ball");
		diveBall = new ItemPokeBall(diveBallID, EnumPokeballs.DiveBall).setItemName("Dive Ball");
		luxuryBall = new ItemPokeBall(luxuryBallID, EnumPokeballs.LuxuryBall).setItemName("Luxury Ball");
		healBall = new ItemPokeBall(healBallID, EnumPokeballs.HealBall).setItemName("Heal Ball");
		duskBall = new ItemPokeBall(duskBallID, EnumPokeballs.DuskBall).setItemName("Dusk Ball");
		premierBall = new ItemPokeBall(premierBallID, EnumPokeballs.PremierBall).setItemName("Premier Ball");

		pokeBallLid = new ItemPokeballLid(pokeBallLidID, EnumPokeballs.PokeBall).setItemName("Poke Ball Lid");
		ultraBallLid = new ItemPokeballLid(ultraBallLidID, EnumPokeballs.UltraBall).setItemName("Ultra Ball Lid");
		greatBallLid = new ItemPokeballLid(greatBallLidID, EnumPokeballs.GreatBall).setItemName("Great Ball Lid");
		levelBallLid = new ItemPokeballLid(levelBallLidID, EnumPokeballs.LevelBall).setItemName("Level Ball Lid");
		moonBallLid = new ItemPokeballLid(moonBallLidID, EnumPokeballs.MoonBall).setItemName("Moon Ball Lid");
		friendBallLid = new ItemPokeballLid(friendBallLidID, EnumPokeballs.FriendBall).setItemName("Friend Ball Lid");
		loveBallLid = new ItemPokeballLid(loveBallLidID, EnumPokeballs.LoveBall).setItemName("Love Ball Lid");
		safariBallLid = new ItemPokeballLid(safariBallLidID, EnumPokeballs.SafariBall).setItemName("Safari Ball Lid");
		heavyBallLid = new ItemPokeballLid(heavyBallLidID, EnumPokeballs.HeavyBall).setItemName("Heavy Ball Lid");
		fastBallLid = new ItemPokeballLid(fastBallLidID, EnumPokeballs.FastBall).setItemName("Fast Ball Lid");
		repeatBallLid = new ItemPokeballLid(repeatBallLidID, EnumPokeballs.RepeatBall).setItemName("Repeat Ball Lid");
		timerBallLid = new ItemPokeballLid(timerBallLidID, EnumPokeballs.TimerBall).setItemName("Timer Ball Lid");
		nestBallLid = new ItemPokeballLid(nestBallLidID, EnumPokeballs.NestBall).setItemName("Nest Ball Lid");
		netBallLid = new ItemPokeballLid(netBallLidID, EnumPokeballs.NetBall).setItemName("Net Ball Lid");
		diveBallLid = new ItemPokeballLid(diveBallLidID, EnumPokeballs.DiveBall).setItemName("Dive Ball Lid");
		luxuryBallLid = new ItemPokeballLid(luxuryBallLidID, EnumPokeballs.LuxuryBall).setItemName("Luxury Ball Lid");
		healBallLid = new ItemPokeballLid(healBallLidID, EnumPokeballs.HealBall).setItemName("Heal Ball Lid");
		duskBallLid = new ItemPokeballLid(duskBallLidID, EnumPokeballs.DuskBall).setItemName("Dusk Ball Lid");
		premierBallLid = new ItemPokeballLid(premierBallLidID, EnumPokeballs.PremierBall).setItemName("Premier Ball Lid");

		pokeBallDisc = new ItemPokeballDisc(pokeBallDiscID, EnumPokeballs.PokeBall).setItemName("Poke Ball Disc");
		ultraBallDisc = new ItemPokeballDisc(ultraBallDiscID, EnumPokeballs.UltraBall).setItemName("Ultra Ball Disc");
		greatBallDisc = new ItemPokeballDisc(greatBallDiscID, EnumPokeballs.GreatBall).setItemName("Great Ball Disc");
		levelBallDisc = new ItemPokeballDisc(levelBallDiscID, EnumPokeballs.LevelBall).setItemName("Level Ball Disc");
		moonBallDisc = new ItemPokeballDisc(moonBallDiscID, EnumPokeballs.MoonBall).setItemName("Moon Ball Disc");
		friendBallDisc = new ItemPokeballDisc(friendBallDiscID, EnumPokeballs.FriendBall).setItemName("Friend Ball Disc");
		loveBallDisc = new ItemPokeballDisc(loveBallDiscID, EnumPokeballs.LoveBall).setItemName("Love Ball Disc");
		safariBallDisc = new ItemPokeballDisc(safariBallDiscID, EnumPokeballs.SafariBall).setItemName("Safari Ball Disc");
		heavyBallDisc = new ItemPokeballDisc(heavyBallDiscID, EnumPokeballs.HeavyBall).setItemName("Heavy Ball Disc");
		fastBallDisc = new ItemPokeballDisc(fastBallDiscID, EnumPokeballs.FastBall).setItemName("Fast Ball Disc");
		repeatBallDisc = new ItemPokeballDisc(repeatBallDiscID, EnumPokeballs.RepeatBall).setItemName("Repeat Ball Disc");
		timerBallDisc = new ItemPokeballDisc(timerBallDiscID, EnumPokeballs.TimerBall).setItemName("Timer Ball Disc");
		nestBallDisc = new ItemPokeballDisc(nestBallDiscID, EnumPokeballs.NestBall).setItemName("Nest Ball Disc");
		netBallDisc = new ItemPokeballDisc(netBallDiscID, EnumPokeballs.NetBall).setItemName("Net Ball Disc");
		diveBallDisc = new ItemPokeballDisc(diveBallDiscID, EnumPokeballs.DiveBall).setItemName("Dive Ball Disc");
		luxuryBallDisc = new ItemPokeballDisc(luxuryBallDiscID, EnumPokeballs.LuxuryBall).setItemName("Luxury Ball Disc");
		healBallDisc = new ItemPokeballDisc(healBallDiscID, EnumPokeballs.HealBall).setItemName("Heal Ball Disc");
		duskBallDisc = new ItemPokeballDisc(duskBallDiscID, EnumPokeballs.DuskBall).setItemName("Dusk Ball Disc");
		premierBallDisc = new ItemPokeballDisc(premierBallDiscID, EnumPokeballs.PremierBall).setItemName("Premier Ball Disc");

		ironBase = new PixelmonItem(ironBaseID).setIconIndex(3 + 6 * 16).setItemName("Iron Base").setCreativeTab(CreativeTabs.tabMaterials);
		ironDisc = new PixelmonItem(ironDiscID).setIconIndex(3 + 7 * 16).setItemName("Iron Disc").setCreativeTab(CreativeTabs.tabMaterials);
		ironBase.setTextureFile("/pixelmon/image/pitems2.png");
		ironDisc.setTextureFile("/pixelmon/image/pitems2.png");
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
