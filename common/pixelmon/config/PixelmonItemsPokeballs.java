package pixelmon.config;

import java.lang.reflect.Field;

import pixelmon.enums.EnumPokeballs;
import pixelmon.items.ItemPokeBall;
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
	public static void load(Configuration cfg) {
		pokeBallID = cfg.getOrCreateIntProperty("PokeBall", "item", 8000).getInt();
		greatBallID = cfg.getOrCreateIntProperty("GreatBall", "item", 8001).getInt();
		ultraBallID = cfg.getOrCreateIntProperty("UltraBall", "item", 8002).getInt();
		masterBallID = cfg.getOrCreateIntProperty("MasterBall", "item", 8003).getInt();
		levelBallID = cfg.getOrCreateIntProperty("LevelBall", "item", 8004).getInt();
		moonBallID = cfg.getOrCreateIntProperty("MoonBall", "item", 8005).getInt();
		
		pokeBall = new ItemPokeBall(pokeBallID, EnumPokeballs.PokeBall).setItemName("Poke Ball");
		ultraBall = new ItemPokeBall(ultraBallID, EnumPokeballs.UltraBall).setItemName("Ultra Ball");
		greatBall = new ItemPokeBall(greatBallID, EnumPokeballs.GreatBall).setItemName("Great Ball");
		masterBall = new ItemPokeBall(masterBallID, EnumPokeballs.MasterBall).setItemName("Master Ball");
		levelBall = new ItemPokeBall(levelBallID, EnumPokeballs.LevelBall).setItemName("Level Ball");
		moonBall = new ItemPokeBall(moonBallID, EnumPokeballs.MoonBall).setItemName("Moon Ball");
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
