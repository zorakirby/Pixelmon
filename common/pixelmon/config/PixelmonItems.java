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
	public static final Item pokeBall = new ItemPokeBall(10000, EnumPokeballs.PokeBall).setItemName("PokeBall");
	public static final Item greatBall = new ItemPokeBall(10001, EnumPokeballs.GreatBall).setItemName("GreatBall");
	public static final Item ultraBall = new ItemPokeBall(10002, EnumPokeballs.UltraBall).setItemName("UltraBall");
	public static final Item masterBall = new ItemPokeBall(10003, EnumPokeballs.MasterBall).setItemName("MasterBall");
	public static final Item pokeChecker = new PixelmonItem(10004).setItemName("PokeChecker").setIconIndex(6).setMaxStackSize(1);
	public static final Item pokeDex = new ItemPokedex(10027).setItemName("Pokedex").setIconIndex(22).setMaxStackSize(1);
	public static final Item rareCandy = new PixelmonItem(10005).setItemName("Rare Candy").setIconIndex(5);
	public static final Item potion = new PixelmonItem(10006).setItemName("Potion").setIconIndex(2).setMaxStackSize(16);
	public static final Item coalDust = new PixelmonItem(10007).setItemName("CoalDust").setIconIndex(4);
	public static final Item fireStone = new ItemEvolutionStone(10008, EnumEvolutionStone.FIRESTONE, 3).setItemName("FireStone");
	public static final Item waterStone = new ItemEvolutionStone(10009, EnumEvolutionStone.WATERSTONE, 1).setItemName("WaterStone");
	public static final Item moonStone = new ItemEvolutionStone(10010, EnumEvolutionStone.MOONSTONE, 4).setItemName("MoonStone");
	public static final Item thunderStone = new ItemEvolutionStone(10011, EnumEvolutionStone.THUNDERSTONE, 0).setItemName("ThunderStone");
	public static final Item leafStone = new ItemEvolutionStone(10012, EnumEvolutionStone.LEAFSTONE, 2).setItemName("LeafStone");
	public static final Item pcItem = new ItemBlock(10013, PixelmonBlocks.pc, 38).setItemName("PC");
	public static final Item healerItem = new ItemBlock(10014, PixelmonBlocks.healer, 54).setItemName("Healer");
	// 9 ids needed for the 9 stones, shards starting on next open id and are
	// the stone they make's id + 10
	public static final Item thunderStoneShard = new PixelmonItem(10021).setItemName("ThunderStoneShard").setIconIndex(7);
	public static final Item leafStoneShard = new PixelmonItem(10022).setItemName("LeafStoneShard").setIconIndex(39);

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
