package pixelmon.config;

import java.lang.reflect.Field;

import pixelmon.enums.EnumPokeballs;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.ItemPokeballLid;
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

	public static int pokeBallLidID;
	public static int greatBallLidID;
	public static int ultraBallLidID;
	public static int levelBallLidID;
	public static int moonBallLidID;
	
	public static int pokeBallDiscID;
	public static int greatBallDiscID;
	public static int ultraBallDiscID;
	public static int levelBallDiscID;
	public static int moonBallDiscID;
	
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
	
	@Mod.Item(name = "Poke Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item pokeBallLid;
	@Mod.Item(name = "Great Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item greatBallLid;
	@Mod.Item(name = "Ultra Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item ultraBallLid;
	@Mod.Item(name = "Master Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item masterBallLid;
	@Mod.Item(name = "Level Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item levelBallLid;
	@Mod.Item(name = "Moon Ball Lid", typeClass = "pixelmon.items.ItemPokeBallLid")
	public static Item moonBallLid;
	
	@Mod.Item(name = "Poke Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item pokeBallDisc;
	@Mod.Item(name = "Great Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item greatBallDisc;
	@Mod.Item(name = "Ultra Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item ultraBallDisc;
	@Mod.Item(name = "Master Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item masterBallDisc;
	@Mod.Item(name = "Level Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item levelBallDisc;
	@Mod.Item(name = "Moon Ball Disc", typeClass = "pixelmon.items.ItemPokeBallDisc")
	public static Item moonBallDisc;
	
	public static void load(Configuration cfg) {
		pokeBallID = cfg.getOrCreateIntProperty("PokeBall", "item", 8000).getInt();
		greatBallID = cfg.getOrCreateIntProperty("GreatBall", "item", 8001).getInt();
		ultraBallID = cfg.getOrCreateIntProperty("UltraBall", "item", 8002).getInt();
		masterBallID = cfg.getOrCreateIntProperty("MasterBall", "item", 8003).getInt();
		levelBallID = cfg.getOrCreateIntProperty("LevelBall", "item", 8004).getInt();
		moonBallID = cfg.getOrCreateIntProperty("MoonBall", "item", 8005).getInt();
		
		pokeBallLidID = cfg.getOrCreateIntProperty("PokeBall Lid", "item", 8030).getInt();
		greatBallLidID = cfg.getOrCreateIntProperty("GreatBall Lid", "item", 8031).getInt();
		ultraBallLidID = cfg.getOrCreateIntProperty("UltraBall Lid", "item", 8032).getInt();
		levelBallLidID = cfg.getOrCreateIntProperty("LevelBall Lid", "item", 8034).getInt();
		moonBallLidID = cfg.getOrCreateIntProperty("MoonBall Lid", "item", 8035).getInt();
		
		pokeBallDiscID = cfg.getOrCreateIntProperty("PokeBall", "item", 8050).getInt();
		greatBallDiscID = cfg.getOrCreateIntProperty("GreatBall", "item", 8051).getInt();
		ultraBallDiscID = cfg.getOrCreateIntProperty("UltraBall", "item", 8052).getInt();
		levelBallDiscID = cfg.getOrCreateIntProperty("LevelBall", "item", 8054).getInt();
		moonBallDiscID = cfg.getOrCreateIntProperty("MoonBall", "item", 8055).getInt();
		
		pokeBall = new ItemPokeBall(pokeBallID, EnumPokeballs.PokeBall).setItemName("Poke Ball");
		ultraBall = new ItemPokeBall(ultraBallID, EnumPokeballs.UltraBall).setItemName("Ultra Ball");
		greatBall = new ItemPokeBall(greatBallID, EnumPokeballs.GreatBall).setItemName("Great Ball");
		masterBall = new ItemPokeBall(masterBallID, EnumPokeballs.MasterBall).setItemName("Master Ball");
		levelBall = new ItemPokeBall(levelBallID, EnumPokeballs.LevelBall).setItemName("Level Ball");
		moonBall = new ItemPokeBall(moonBallID, EnumPokeballs.MoonBall).setItemName("Moon Ball");
		
		pokeBallLid = new ItemPokeballLid(pokeBallLidID, EnumPokeballs.PokeBall).setItemName("Poke Ball Lid");
		ultraBallLid = new ItemPokeballLid(ultraBallLidID, EnumPokeballs.UltraBall).setItemName("Ultra Ball Lid");
		greatBallLid = new ItemPokeballLid(greatBallLidID, EnumPokeballs.GreatBall).setItemName("Great Ball Lid");
		levelBallLid = new ItemPokeballLid(levelBallLidID, EnumPokeballs.LevelBall).setItemName("Level Ball Lid");
		moonBallLid = new ItemPokeballLid(moonBallLidID, EnumPokeballs.MoonBall).setItemName("Moon Ball Lid");

		pokeBallDisc = new ItemPokeballLid(pokeBallDiscID, EnumPokeballs.PokeBall).setItemName("Poke Ball Disc");
		ultraBallDisc = new ItemPokeballLid(ultraBallDiscID, EnumPokeballs.UltraBall).setItemName("Ultra Ball Disc");
		greatBallDisc = new ItemPokeballLid(greatBallDiscID, EnumPokeballs.GreatBall).setItemName("Great Ball Disc");
		levelBallDisc = new ItemPokeballLid(levelBallDiscID, EnumPokeballs.LevelBall).setItemName("Level Ball Disc");
		moonBallDisc = new ItemPokeballLid(moonBallDiscID, EnumPokeballs.MoonBall).setItemName("Moon Ball Disc");
	}
	public static void addNames() {
		try
		{
			for(Field field : PixelmonItemsPokeballs.class.getFields())
			{
				if(field.isAnnotationPresent(Mod.Item.class))
				{
					Item item = (Item)field.get(null);
					LanguageRegistry.addName(item, field.getAnnotation(Mod.Item.class).name());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
