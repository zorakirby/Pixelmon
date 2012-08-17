package pixelmon.config;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumPokeballs;
import pixelmon.items.ItemBlock;
import pixelmon.items.ItemEvolutionStone;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.ItemPokedex;
import pixelmon.items.PixelmonItem;

public class PixelmonItems {
	private static final int pIMG = ModLoader.addOverride("/gui/items.png", "/pixelmon/image/pokeball.png");
	private static final int gIMG = ModLoader.addOverride("/gui/items.png", "/pixelmon/image/greatball.png");
	private static final int uIMG = ModLoader.addOverride("/gui/items.png", "/pixelmon/image/ultraball.png");
	private static final int mIMG = ModLoader.addOverride("/gui/items.png", "/pixelmon/image/masterball.png");
	public static final Item pokeBall = new ItemPokeBall(10000, EnumPokeballs.PokeBall).setItemName("PokeBall").setIconIndex(pIMG);
	public static final Item greatBall = new ItemPokeBall(10001, EnumPokeballs.GreatBall).setItemName("GreatBall").setIconIndex(gIMG);
	public static final Item ultraBall = new ItemPokeBall(10002, EnumPokeballs.UltraBall).setItemName("UltraBall").setIconIndex(uIMG);
	public static final Item masterBall = new ItemPokeBall(10003, EnumPokeballs.MasterBall).setItemName("MasterBall").setIconIndex(mIMG);
	public static final Item pokeChecker = new PixelmonItem(10004).setItemName("PokeChecker").setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/pokechecker.png")).setMaxStackSize(1);
	public static final Item pokeDex = new ItemPokedex(10027).setItemName("Pokedex").setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/pokedex.png")).setMaxStackSize(1);
	public static final Item rareCandy = new PixelmonItem(10005).setItemName("Rare Candy").setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/rarecandy.png"));
	public static final Item potion = new PixelmonItem(10006).setItemName("Potion").setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/potion.png")).setMaxStackSize(16);
	public static final Item coalDust = new PixelmonItem(10007).setItemName("CoalDust").setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/coaldust.png"));
	public static final Item fireStone = new ItemEvolutionStone(10008, EnumEvolutionStone.FIRESTONE).setItemName("FireStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/firestone.png"));
	public static final Item waterStone = new ItemEvolutionStone(10009, EnumEvolutionStone.WATERSTONE).setItemName("WaterStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/waterstone.png"));
	public static final Item moonStone = new ItemEvolutionStone(10010, EnumEvolutionStone.MOONSTONE).setItemName("MoonStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/moonstone.png"));
	public static final Item thunderStone = new ItemEvolutionStone(10011, EnumEvolutionStone.THUNDERSTONE).setItemName("ThunderStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/thunderstone.png"));
	public static final Item leafStone = new ItemEvolutionStone(10012, EnumEvolutionStone.LEAFSTONE).setItemName("LeafStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/leafstone.png"));
	public static final Item pcItem = new ItemBlock(10013, PixelmonBlocks.pc).setItemName("PC").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/pc.png"));
	public static final Item healerItem = new ItemBlock(10014, PixelmonBlocks.healer).setItemName("Healer").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/healer.png"));
	// 9 ids needed for the 9 stones, shards starting on next open id and are
	// the stone they make's id + 10
	public static final Item thunderStoneShard = new PixelmonItem(10021).setItemName("ThunderStoneShard").setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/thunderstoneshard.png"));
	public static final Item leafStoneShard = new PixelmonItem(10022).setItemName("LeafStoneShard").setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/leafstoneshard.png"));

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
