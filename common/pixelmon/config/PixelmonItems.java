package pixelmon.config;

import java.lang.reflect.Field;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.Item;

import net.minecraftforge.common.Configuration;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumEthers;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumHeldItems;
import pixelmon.enums.EnumPokeballs;
import pixelmon.enums.EnumPotions;
import pixelmon.enums.EnumStatusAilmentHealers;
import pixelmon.items.ItemBlock;
import pixelmon.items.ItemEther;
import pixelmon.items.ItemEvolutionStone;
import pixelmon.items.ItemHeld;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.ItemPokedex;
import pixelmon.items.ItemPotion;
import pixelmon.items.ItemStatusAilmentHealer;
import pixelmon.items.PixelmonItem;
import pixelmon.items.heldItems.ItemBerryLeppa;
import pixelmon.items.heldItems.ItemBerryOran;
import pixelmon.items.heldItems.ItemBerryRawst;
import pixelmon.items.heldItems.ItemExpShare;
import pixelmon.items.heldItems.ItemLuckyEgg;

public class PixelmonItems {
	public static int pokeBallID;
	public static int greatBallID;
	public static int ultraBallID;
	public static int masterBallID;
	public static int pokeCheckerID;
	public static int pokeDexID;
	public static int rareCandyID;

	public static int potionID;
	public static int superPotionID;
	public static int hyperPotionID;
	public static int maxPotionID;
	public static int etherID;
	public static int maxEtherID;
	public static int elixirID;
	public static int maxElixirID;
	public static int fullRestoreID;
	public static int antidoteID;
	public static int parlyzHealID;
	public static int awakeningID;
	public static int burnHealID;
	public static int iceHealID;
	public static int fullHealID;

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
	public static int waterStoneShardID;
	public static int fireStoneShardID;
	
	public static int luckyEggID;
	public static int expShareID;
	
	public static int berryOranID;
	public static int berryRawstID;
	public static int berryLeppaID;
	
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
	
	@Mod.Item(name = "Potion", typeClass = "pixelmon.items.ItemPotion")
	public static Item potion;
	@Mod.Item(name = "Super Potion", typeClass = "pixelmon.items.ItemPotion")
	public static Item superPotion;
	@Mod.Item(name = "Hyper Potion", typeClass = "pixelmon.items.ItemPotion")
	public static Item hyperPotion;
	@Mod.Item(name = "Max Potion", typeClass = "pixelmon.items.ItemPotion")
	public static Item maxPotion;
	@Mod.Item(name = "Ether", typeClass = "pixelmon.items.ItemEther")
	public static Item ether;
	@Mod.Item(name = "Max Ether", typeClass = "pixelmon.items.ItemEther")
	public static Item maxEther;
	@Mod.Item(name = "Elixir", typeClass = "pixelmon.items.ItemEther")
	public static Item elixir;
	@Mod.Item(name = "Max Elixir", typeClass = "pixelmon.items.ItemEther")
	public static Item maxElixir;
	@Mod.Item(name = "Full Restore", typeClass = "pixelmon.items.ItemStatusAilmentHealer")
	public static Item fullRestore;
	@Mod.Item(name = "Antidote", typeClass = "pixelmon.items.ItemStatusAilmentHealer")
	public static Item antidote;
	@Mod.Item(name = "Parlyz Heal", typeClass = "pixelmon.items.ItemStatusAilmentHealer")
	public static Item parlyzHeal;
	@Mod.Item(name = "Awakening", typeClass = "pixelmon.items.ItemStatusAilmentHealer")
	public static Item awakening;
	@Mod.Item(name = "Burn Heal", typeClass = "pixelmon.items.ItemStatusAilmentHealer")
	public static Item burnHeal;
	@Mod.Item(name = "Ice Heal", typeClass = "pixelmon.items.ItemStatusAilmentHealer")
	public static Item iceHeal;
	@Mod.Item(name = "Full Heal", typeClass = "pixelmon.items.ItemStatusAilmentHealer")
	public static Item fullHeal;
	
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
	@Mod.Item(name = "Water Stone Shard", typeClass = "pixelmon.items.PixelmonItem")
	public static Item waterStoneShard;
	@Mod.Item(name = "Fire Stone Shard", typeClass = "pixelmon.items.PixelmonItem")
	public static Item fireStoneShard;
	
	@Mod.Item(name = "Lucky Egg", typeClass = "pixelmon.items.heldItems.ItemLuckyEgg")
	public static Item luckyEgg;
	@Mod.Item(name = "EXP Share", typeClass = "pixelmon.items.heldItems.ItemExpShare")
	public static Item expShare;
	
	@Mod.Item(name = "Oran Berry", typeClass = "pixelmon.items.heldItems.ItemBerryOran")
	public static Item berryOran;
	@Mod.Item(name = "Rawst Berry", typeClass = "pixelmon.items.heldItems.ItemBerryRawst")
	public static Item berryRawst;
	@Mod.Item(name = "Leppa Berry", typeClass = "pixelmon.items.heldItems.ItemBerryLeppa")
	public static Item berryLeppa;

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
		superPotionID = cfg.getOrCreateIntProperty("SuperPotion", "item", 10050).getInt();
		hyperPotionID = cfg.getOrCreateIntProperty("HyperPotion", "item", 10051).getInt();
		maxPotionID = cfg.getOrCreateIntProperty("MaxPotion", "item", 10052).getInt();
		etherID = cfg.getOrCreateIntProperty("Ether", "item", 10053).getInt();
		maxEtherID = cfg.getOrCreateIntProperty("MaxEther", "item", 10054).getInt();
		elixirID = cfg.getOrCreateIntProperty("Elixir", "item", 10055).getInt();
		maxElixirID = cfg.getOrCreateIntProperty("MaxElixir", "item", 10056).getInt();
		fullRestoreID = cfg.getOrCreateIntProperty("FullRestore", "item", 10057).getInt();
		antidoteID = cfg.getOrCreateIntProperty("Antidote", "item", 10058).getInt();
		parlyzHealID = cfg.getOrCreateIntProperty("ParlyzHeal", "item", 10059).getInt();
		awakeningID = cfg.getOrCreateIntProperty("Awakening", "item", 10060).getInt();
		burnHealID = cfg.getOrCreateIntProperty("BurnHeal", "item", 10061).getInt();
		iceHealID = cfg.getOrCreateIntProperty("IceHeal", "item", 10062).getInt();
		fullHealID = cfg.getOrCreateIntProperty("FullHeal", "item", 10063).getInt();
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
		waterStoneShardID = cfg.getOrCreateIntProperty("WaterStoneShard", "item", 10023).getInt();
		fireStoneShardID = cfg.getOrCreateIntProperty("FireStoneShard", "item", 10024).getInt();
		luckyEggID = cfg.getOrCreateIntProperty("LuckyEgg", "item", 10035).getInt();
		expShareID = cfg.getOrCreateIntProperty("EXPShare", "item", 10036).getInt();
		berryOranID = cfg.getOrCreateIntProperty("OranBerry", "item", 10040).getInt();
		berryRawstID = cfg.getOrCreateIntProperty("RawstBerry", "item", 10041).getInt();
		berryLeppaID = cfg.getOrCreateIntProperty("LeppaBerry", "item", 10042).getInt();

		pokeBall = new ItemPokeBall(pokeBallID, EnumPokeballs.PokeBall).setItemName("PokeBall");
		ultraBall = new ItemPokeBall(ultraBallID, EnumPokeballs.UltraBall).setItemName("UltraBall");
		greatBall = new ItemPokeBall(greatBallID, EnumPokeballs.GreatBall).setItemName("GreatBall");
		masterBall = new ItemPokeBall(masterBallID, EnumPokeballs.MasterBall).setItemName("MasterBall");
		pokeChecker = new PixelmonItem(pokeCheckerID).setItemName("PokeChecker").setIconIndex(6).setMaxStackSize(1);
		pokeDex = new ItemPokedex(pokeDexID).setItemName("Pokedex").setIconIndex(22).setMaxStackSize(1);
		rareCandy = new PixelmonItem(rareCandyID).setItemName("Rare Candy").setIconIndex(5);
		potion = new ItemPotion(potionID, EnumPotions.Potion).setItemName("Potion");
		superPotion = new ItemPotion(superPotionID, EnumPotions.SuperPotion).setItemName("Super Potion");
		hyperPotion = new ItemPotion(hyperPotionID, EnumPotions.HyperPotion).setItemName("Hyper Potion");
		maxPotion = new ItemPotion(maxPotionID, EnumPotions.MaxPotion).setItemName("Max Potion");
		ether = new ItemEther(etherID, EnumEthers.Ether).setItemName("Ether");
		maxEther = new ItemEther(maxEtherID, EnumEthers.MaxEther).setItemName("Max Ether");
		elixir = new ItemEther(elixirID, EnumEthers.Elixir).setItemName("Elixir");
		maxElixir = new ItemEther(maxElixirID, EnumEthers.MaxElixir).setItemName("Max Elixir");
		fullRestore = new ItemStatusAilmentHealer(fullRestoreID, EnumStatusAilmentHealers.FullRestore).setItemName("Full Restore");
		antidote = new ItemStatusAilmentHealer(antidoteID, EnumStatusAilmentHealers.Antidote).setItemName("Antidonte");
		parlyzHeal = new ItemStatusAilmentHealer(parlyzHealID, EnumStatusAilmentHealers.ParlyzHeal).setItemName("Parlyz Heal");
		awakening = new ItemStatusAilmentHealer(awakeningID, EnumStatusAilmentHealers.Awakening).setItemName("Awakening");
		burnHeal = new ItemStatusAilmentHealer(burnHealID, EnumStatusAilmentHealers.BurnHeal).setItemName("Burn Heal");
		iceHeal = new ItemStatusAilmentHealer(iceHealID, EnumStatusAilmentHealers.IceHeal).setItemName("Ice Heal");
		fullHeal = new ItemStatusAilmentHealer(fullHealID, EnumStatusAilmentHealers.FullHeal).setItemName("Full Heal");
		coalDust = new PixelmonItem(coalDustID).setItemName("CoalDust").setIconIndex(4);
		fireStone = new ItemEvolutionStone(fireStoneID, EnumEvolutionStone.Firestone, 3).setItemName("FireStone");
		waterStone = new ItemEvolutionStone(waterStoneID, EnumEvolutionStone.Waterstone, 1).setItemName("WaterStone");
		moonStone = new ItemEvolutionStone(moonStoneID, EnumEvolutionStone.Moonstone, 4).setItemName("MoonStone");
		thunderStone = new ItemEvolutionStone(thunderStoneID, EnumEvolutionStone.Thunderstone, 0).setItemName("ThunderStone");
		leafStone = new ItemEvolutionStone(leafStoneID, EnumEvolutionStone.Leafstone, 2).setItemName("LeafStone");
		pcItem = new ItemBlock(pcItemID, PixelmonBlocks.pc, 38).setItemName("PC");
		healerItem = new ItemBlock(healerItemID, PixelmonBlocks.healer, 54).setItemName("Healer");
		thunderStoneShard = new PixelmonItem(thunderStoneShardID).setItemName("ThunderStoneShard").setIconIndex(7);
		leafStoneShard = new PixelmonItem(leafStoneShardID).setItemName("LeafStoneShard").setIconIndex(39);
		waterStoneShard = new PixelmonItem(waterStoneShardID).setItemName("WaterStoneShard").setIconIndex(23);
		fireStoneShard = new PixelmonItem(fireStoneShardID).setItemName("FireStoneShard").setIconIndex(55);
		luckyEgg = new ItemLuckyEgg(luckyEggID).setItemName("LuckyEgg").setIconIndex(11);
		expShare = new ItemExpShare(expShareID).setItemName("ExpShare").setIconIndex(0);
		berryOran = new ItemBerryOran(berryOranID).setItemName("OranBerry").setIconIndex(9);
		berryLeppa = new ItemBerryLeppa(berryLeppaID).setItemName("LeppaBerry").setIconIndex(25);
		berryRawst = new ItemBerryRawst(berryRawstID).setItemName("RawstBerry").setIconIndex(41);
	}
	
	public static void addNames() {
		try
		{
			for(Field field : PixelmonItems.class.getFields())
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