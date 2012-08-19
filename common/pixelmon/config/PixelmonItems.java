package pixelmon.config;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumPokeballs;
import pixelmon.items.ItemBlock;
import pixelmon.items.ItemEvolutionStone;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.ItemPokedex;
import pixelmon.items.PixelmonItem;

public class PixelmonItems {
	public static int pokeBallID;
	public static int greatBallID;
	public static int ultraBallID;
	public static int masterBallID;
	public static int pokeCheckerID;
	public static int pokeDexID;
	public static int rareCandyID;
	public static int potionID;
	public static int coalDustID;
	public static int fireStoneID;
	public static int waterStoneID;
	public static int moonStoneID;
	public static int thunderStoneID;
	public static int leafStoneID;
	public static int pcItemID;
	public static int healerItemID;
	public static int thunderStoneShardID;
	public static int leafStoneShardID;
	
	@Mod.Item(name = "PokeBall", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item pokeBall;
	@Mod.Item(name = "GreatBall", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item greatBall;
	@Mod.Item(name = "UltraBall", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item ultraBall;
	@Mod.Item(name = "MasterBall", typeClass = "pixelmon.items.ItemPokeBall")
	public static Item masterBall;
	
	@Mod.Item(name = "PokeChecker", typeClass = "pixelmon.items.PixelmonItem")
	public static Item pokeChecker;
	@Mod.Item(name = "Pokedex", typeClass = "pixelmon.items.ItemPokedex")
	public static Item pokeDex;
	
	@Mod.Item(name = "Rare Candy", typeClass = "pixelmon.items.PixelmonItem")
	public static Item rareCandy;
	@Mod.Item(name = "Potion", typeClass = "pixelmon.items.PixelmonItem")
	public static Item potion;
	@Mod.Item(name = "Coal Dust", typeClass = "pixelmon.items.PixelmonItem")
	public static Item coalDust;
	
	@Mod.Item(name = "Fire Stone", typeClass = "pixelmon.items.ItemEvolutionStone")
	public static Item fireStone;
	@Mod.Item(name = "Water Stone", typeClass = "pixelmon.items.ItemEvolutionStone")
	public static Item waterStone;
	@Mod.Item(name = "Moon Stone", typeClass = "pixelmon.items.ItemEvolutionStone")
	public static Item moonStone;
	@Mod.Item(name = "Thunder Stone", typeClass = "pixelmon.items.ItemEvolutionStone")
	public static Item thunderStone;
	@Mod.Item(name = "Leaf Stone", typeClass = "pixelmon.items.ItemEvolutionStone")
	public static Item leafStone;
	
	@Mod.Item(name = "PC", typeClass = "pixelmon.items.ItemBlock")
	public static Item pcItem;
	@Mod.Item(name = "Healer", typeClass = "pixelmon.items.ItemBlock")
	public static Item healerItem;
	
	@Mod.Item(name = "Thunder Stone Shard", typeClass = "pixelmon.items.PixelmonItem")
	public static Item thunderStoneShard;
	@Mod.Item(name = "Leaf Stone Shard", typeClass = "pixelmon.items.PixelmonItem")
	public static Item leafStoneShard;

	public static void load(Configuration cfg)
	{
		pokeBallID = cfg.getOrCreateIntProperty("PokeBall", "item", 10000).getInt();
		greatBallID = cfg.getOrCreateIntProperty("GreatBall", "item", 10001).getInt();
		ultraBallID = cfg.getOrCreateIntProperty("UltraBall", "item", 10002).getInt();
		masterBallID = cfg.getOrCreateIntProperty("MasterBall", "item", 10003).getInt();
		pokeCheckerID = cfg.getOrCreateIntProperty("PokeChecker", "item", 10004).getInt();
		pokeDexID = cfg.getOrCreateIntProperty("PokeDex", "item", 10027).getInt();
		rareCandyID = cfg.getOrCreateIntProperty("RareCandy", "item", 10005).getInt();
		potionID = cfg.getOrCreateIntProperty("Potion", "item", 10006).getInt();
		coalDustID = cfg.getOrCreateIntProperty("CoalDust", "item", 10007).getInt();
		fireStoneID = cfg.getOrCreateIntProperty("FireStone", "item", 10008).getInt();
		waterStoneID = cfg.getOrCreateIntProperty("WaterStone", "item", 10009).getInt();
		moonStoneID = cfg.getOrCreateIntProperty("MoonStone", "item", 10010).getInt();
		thunderStoneID = cfg.getOrCreateIntProperty("ThunderStone", "item", 10011).getInt();
		leafStoneID = cfg.getOrCreateIntProperty("LeafStone", "item", 10012).getInt();
		pcItemID = cfg.getOrCreateIntProperty("PCItem", "item", 10013).getInt();
		healerItemID = cfg.getOrCreateIntProperty("HealerItem", "item", 10014).getInt();
		thunderStoneShardID = cfg.getOrCreateIntProperty("ThunderStoneShard", "item", 10021).getInt();
		leafStoneShardID = cfg.getOrCreateIntProperty("LeafStoneShard", "item", 10022).getInt();
		pokeBall = new ItemPokeBall(pokeBallID, EnumPokeballs.PokeBall).setItemName("PokeBall");
		greatBall = new ItemPokeBall(greatBallID, EnumPokeballs.GreatBall).setItemName("GreatBall");
		ultraBall = new ItemPokeBall(ultraBallID, EnumPokeballs.UltraBall).setItemName("UltraBall");
		masterBall = new ItemPokeBall(masterBallID, EnumPokeballs.MasterBall).setItemName("MasterBall");
		pokeChecker = new PixelmonItem(pokeCheckerID).setItemName("PokeChecker").setIconIndex(6).setMaxStackSize(1);
		pokeDex = new ItemPokedex(pokeDexID).setItemName("Pokedex").setIconIndex(22).setMaxStackSize(1);
		rareCandy = new PixelmonItem(rareCandyID).setItemName("Rare Candy").setIconIndex(5);
		potion = new PixelmonItem(potionID).setItemName("Potion").setIconIndex(2).setMaxStackSize(16);
		coalDust = new PixelmonItem(coalDustID).setItemName("CoalDust").setIconIndex(4);
		fireStone = new ItemEvolutionStone(fireStoneID, EnumEvolutionStone.FIRESTONE, 3).setItemName("FireStone");
		waterStone = new ItemEvolutionStone(waterStoneID, EnumEvolutionStone.WATERSTONE, 1).setItemName("WaterStone");
		moonStone = new ItemEvolutionStone(moonStoneID, EnumEvolutionStone.MOONSTONE, 4).setItemName("MoonStone");
		thunderStone = new ItemEvolutionStone(thunderStoneID, EnumEvolutionStone.THUNDERSTONE, 0).setItemName("ThunderStone");
		leafStone = new ItemEvolutionStone(leafStoneID, EnumEvolutionStone.LEAFSTONE, 2).setItemName("LeafStone");
		pcItem = new ItemBlock(pcItemID, PixelmonBlocks.pc, 38).setItemName("PC");
		healerItem = new ItemBlock(healerItemID, PixelmonBlocks.healer, 54).setItemName("Healer");
		thunderStoneShard = new PixelmonItem(thunderStoneShardID).setItemName("ThunderStoneShard").setIconIndex(7);
		leafStoneShard = new PixelmonItem(leafStoneShardID).setItemName("LeafStoneShard").setIconIndex(39);
	}
	
	public static void addNames() {
		ModLoader.addName(pokeBall, "PokeBall");
		ModLoader.addName(greatBall, "GreatBall");
		ModLoader.addName(ultraBall, "UltraBall");
		ModLoader.addName(masterBall, "MasterBall");
		ModLoader.addName(pokeChecker, "PokeChecker");
		ModLoader.addName(rareCandy, "Rare Candy");
		ModLoader.addName(potion, "Potion");
		ModLoader.addName(coalDust, "Coal Dust");
		ModLoader.addName(pokeDex, "Pokedex");		
		ModLoader.addName(fireStone, "Fire Stone");
		ModLoader.addName(leafStone, "Leaf Stone");
		ModLoader.addName(waterStone, "Water Stone");
		ModLoader.addName(thunderStone, "Thunder Stone");
		ModLoader.addName(moonStone, "Moon Stone");
		ModLoader.addName(thunderStoneShard, "Thunder Stone Shard");
		ModLoader.addName(leafStoneShard, "Leaf Stone Shard");	
		ModLoader.addName(pcItem, "PC");
		ModLoader.addName(healerItem, "Healer");
	}
}
