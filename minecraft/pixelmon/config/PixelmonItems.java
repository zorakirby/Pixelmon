package pixelmon.config;

import java.lang.reflect.Field;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.Configuration;
import pixelmon.Pixelmon;
import pixelmon.entities.pixelmon.helpers.DropItemHelper;
import pixelmon.enums.EnumEthers;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumPotions;
import pixelmon.enums.EnumStatusAilmentHealers;
import pixelmon.items.ItemBlock;
import pixelmon.items.ItemEther;
import pixelmon.items.ItemEvolutionStone;
import pixelmon.items.ItemGoodRod;
import pixelmon.items.ItemHammer;
import pixelmon.items.ItemHeld;
import pixelmon.items.ItemOldRod;
import pixelmon.items.ItemPixelmonArmor;
import pixelmon.items.ItemPixelmonBoots;
import pixelmon.items.ItemPokedex;
import pixelmon.items.ItemFossil;
import pixelmon.items.ItemPotion;
import pixelmon.items.ItemStatusAilmentHealer;
import pixelmon.items.ItemSuperRod;
import pixelmon.items.ItemWailmerPail;
import pixelmon.items.PixelmonItem;
import pixelmon.items.heldItems.ItemBerryLeppa;
import pixelmon.items.heldItems.ItemBerryOran;
import pixelmon.items.heldItems.ItemBerryRawst;
import pixelmon.items.heldItems.ItemExpShare;
import pixelmon.items.heldItems.ItemLuckyEgg;
import pixelmon.items.weapons.PixelmonItemAxe;
import pixelmon.items.weapons.PixelmonItemHoe;
import pixelmon.items.weapons.PixelmonItemPickAxe;
import pixelmon.items.weapons.PixelmonItemSpade;
import pixelmon.items.weapons.PixelmonItemSword;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.LanguageRegistry;

//import pixelmon.items.heldItems.ItemEverstone;

public class PixelmonItems {
	public static int itemID = 10000;
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

	public static int pcItemID;
	public static int healerItemID;
	public static int anvilItemID;
	public static int tradeMachineItemID;

	public static int fireStoneID;
	public static int waterStoneID;
	public static int moonStoneID;
	public static int thunderStoneID;
	public static int leafStoneID;
	public static int thunderStoneShardID;
	public static int leafStoneShardID;
	public static int waterStoneShardID;
	public static int fireStoneShardID;
	public static int everStoneShardID;

	public static int hammerWoodID;
	public static int hammerStoneID;
	public static int hammerIronID;
	public static int hammerGoldID;
	public static int hammerDiamondID;
	public static int hammerAluminiumID;

	public static int swordAluminiumID;
	public static int axeAluminiumID;
	public static int shovelAluminiumID;
	public static int pickaxeAluminiumID;
	public static int hoeAluminiumID;

	public static int helmetAluminiumID;
	public static int torsoAluminiumID;
	public static int leggingsAluminiumID;
	public static int bootsAluminiumID;
	public static int newRunningShoesID;
	public static int oldRunningShoesID;

	public static int aluminiumIngotID;
	public static int aluminiumPlateID;

/*	public static int mossyRockID;
	public static int icyRockID;*/

	public static int wailmerPailID;
	public static int oldRodID;
	public static int goodRodID;
	public static int superRodID;
	
	
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
	@Mod.Item(name = "Anvil", typeClass = "pixelmon.items.ItemBlock")
	public static Item anvilItem;
	@Mod.Item(name = "Trade Machine", typeClass = "pixelmon.items.ItemBlock")
	public static Item tradeMachineItem;

	@Mod.Item(name = "Thunder Stone Shard", typeClass = "pixelmon.items.PixelmonItem")
	public static Item thunderStoneShard;
	@Mod.Item(name = "Leaf Stone Shard", typeClass = "pixelmon.items.PixelmonItem")
	public static Item leafStoneShard;
	@Mod.Item(name = "Water Stone Shard", typeClass = "pixelmon.items.PixelmonItem")
	public static Item waterStoneShard;
	@Mod.Item(name = "Fire Stone Shard", typeClass = "pixelmon.items.PixelmonItem")
	public static Item fireStoneShard;

	@Mod.Item(name = "Wood Hammer", typeClass = "pixelmon.items.ItemHammer")
	public static Item hammerWood;
	@Mod.Item(name = "Stone Hammer", typeClass = "pixelmon.items.ItemTool")
	public static Item hammerStone;
	@Mod.Item(name = "Iron Hammer", typeClass = "pixelmon.items.ItemTool")
	public static Item hammerIron;
	@Mod.Item(name = "Gold Hammer", typeClass = "pixelmon.items.ItemTool")
	public static Item hammerGold;
	@Mod.Item(name = "Diamond Hammer", typeClass = "pixelmon.items.ItemTool")
	public static Item hammerDiamond;
	@Mod.Item(name = "Aluminium Hammer", typeClass = "pixelmon.items.ItemTool")
	public static Item hammerAluminium;

	@Mod.Item(name = "Aluminium Ingot", typeClass = "pixelmon.items.PixelmonItem")
	public static Item aluminiumIngot;
	@Mod.Item(name = "Aluminium Plate", typeClass = "pixelmon.items.PixelmonItem")
	public static Item aluminiumPlate;

	@Mod.Item(name = "Aluminium Sword", typeClass = "pixelmon.items.ItemTool")
	public static Item swordAluminium;
	@Mod.Item(name = "Aluminium Axe", typeClass = "pixelmon.items.ItemTool")
	public static Item axeAluminium;
	@Mod.Item(name = "Aluminium Spade", typeClass = "pixelmon.items.ItemTool")
	public static Item spadeAluminium;
	@Mod.Item(name = "Aluminium Pickaxe", typeClass = "pixelmon.items.ItemTool")
	public static Item pickaxeAluminium;
	@Mod.Item(name = "Aluminium Hoe", typeClass = "pixelmon.items.ItemTool")
	public static Item hoeAluminium;

	@Mod.Item(name = "Aluminium Helmet", typeClass = "pixelmon.items.ItemPixelmonArmor")
	public static Item helmetAluminium;
	@Mod.Item(name = "Aluminium Chestplate", typeClass = "pixelmon.items.ItemPixelmonArmor")
	public static Item torsoAluminium;
	@Mod.Item(name = "Aluminium Leggings", typeClass = "pixelmon.items.ItemPixelmonArmor")
	public static Item leggingsAluminium;
	@Mod.Item(name = "Aluminium Boots", typeClass = "pixelmon.items.ItemPixelmonArmor")
	public static Item bootsAluminium;
	@Mod.Item(name = "New Running Shoes", typeClass = "pixelmon.items.ItemPixelmonBoots")
	public static Item newRunningShoes;


	@Mod.Item(name = "Old Running Shoes", typeClass = "pixelmon.items.ItemPixelmonBoots")
	public static Item oldRunningShoes;

	@Mod.Item(name = "Wailmer Pail", typeClass = "pixelmon.items.ItemWailmerPail")
	public static Item wailmerPail;
	
	@Mod.Item(name = "Old Rod", typeClass = "pixelmon.items.ItemOldRod")
	public static Item oldRod;
	@Mod.Item(name = "Good Rod", typeClass = "pixelmon.items.ItemGoodRod")
	public static Item goodRod;
	@Mod.Item(name = "Super Rod", typeClass = "pixelmon.items.ItemSuperRod")
	public static Item superRod;
	

	


	public static void load(Configuration cfg) {
		PixelmonItemsPokeballs.load(cfg);
		PixelmonItemsApricorns.load(cfg);
		PixelmonItemsBadges.load(cfg);
		PixelmonItemsTMs.load(cfg);
		PixelmonItemsFossils.load(cfg);
		PixelmonItemsHeld.load(cfg);

		pokeDexID = cfg.get("item", "PokeDex", 10027).getInt();
		rareCandyID = cfg.get("item", "RareCandy", 10005).getInt();
		potionID = cfg.get("item", "Potion", 10006).getInt();
		superPotionID = cfg.get("item", "SuperPotion", 10050).getInt();
		hyperPotionID = cfg.get("item", "HyperPotion", 10051).getInt();
		maxPotionID = cfg.get("item", "MaxPotion", 10052).getInt();
		etherID = cfg.get("item", "Ether", 10053).getInt();
		maxEtherID = cfg.get("item", "MaxEther", 10054).getInt();
		elixirID = cfg.get("item", "Elixir", 10055).getInt();
		maxElixirID = cfg.get("item", "MaxElixir", 10056).getInt();
		fullRestoreID = cfg.get("item", "FullRestore", 10057).getInt();
		antidoteID = cfg.get("item", "Antidote", 10058).getInt();
		parlyzHealID = cfg.get("item", "ParlyzHeal", 10059).getInt();
		awakeningID = cfg.get("item", "Awakening", 10060).getInt();
		burnHealID = cfg.get("item", "BurnHeal", 10061).getInt();
		iceHealID = cfg.get("item", "IceHeal", 10062).getInt();
		fullHealID = cfg.get("item", "FullHeal", 10063).getInt();
		pcItemID = cfg.get("item", "PCItem", 10013).getInt();
		healerItemID = cfg.get("item", "HealerItem", 10014).getInt();
		anvilItemID = cfg.get("item", "AnvilItem", 10015).getInt();
		tradeMachineItemID = cfg.get("item", "TradeMachineItem", 10016).getInt();

		fireStoneID = cfg.get("evolutionStone", "FireStone", 10008).getInt();
		waterStoneID = cfg.get("evolutionStone", "WaterStone", 10009).getInt();
		moonStoneID = cfg.get("evolutionStone", "MoonStone", 10010).getInt();
		thunderStoneID = cfg.get("evolutionStone", "ThunderStone", 10011).getInt();
		leafStoneID = cfg.get("evolutionStone", "LeafStone", 10012).getInt();
		thunderStoneShardID = cfg.get("evolutionStone", "ThunderStoneShard", 10021).getInt();
		leafStoneShardID = cfg.get("evolutionStone", "LeafStoneShard", 10022).getInt();
		waterStoneShardID = cfg.get("evolutionStone", "WaterStoneShard", 10023).getInt();
		fireStoneShardID = cfg.get("evolutionStone", "FireStoneShard", 10024).getInt();
		// everStoneShardID = cfg.get("EverStoneShard", "item", 10025).getInt();

		hammerWoodID = cfg.get("item", "Wood Hammer", 10107).getInt();
		hammerStoneID = cfg.get("item", "Stone Hammer", 10108).getInt();
		hammerIronID = cfg.get("item", "Iron Hammer", 10109).getInt();
		hammerGoldID = cfg.get("item", "Gold Hammer", 10110).getInt();
		hammerDiamondID = cfg.get("item", "Diamond Hammer", 10111).getInt();
		hammerAluminiumID = cfg.get("item", "Aluminium Hammer", 10122).getInt();

		aluminiumIngotID = cfg.get("item", "AluminiumIngot", 10120).getInt();
		aluminiumPlateID = cfg.get("item", "AluminiumPlate", 10121).getInt();

		swordAluminiumID = cfg.get("item", "Aluminium Sword", 10123).getInt();
		axeAluminiumID = cfg.get("item", "Aluminium Axe", 10124).getInt();
		shovelAluminiumID = cfg.get("item", "Aluminium Shovel", 10125).getInt();
		pickaxeAluminiumID = cfg.get("item", "Aluminium Pickaxe", 10126).getInt();
		hoeAluminiumID = cfg.get("item", "Aluminium Hoe", 10127).getInt();

		helmetAluminiumID = cfg.get("item", "Aluminium Helmet", 10128).getInt();
		torsoAluminiumID = cfg.get("item", "Aluminium Chestplate", 10129).getInt();
		leggingsAluminiumID = cfg.get("item", "Aluminium Leggings", 10130).getInt();
		bootsAluminiumID = cfg.get("item", "Aluminium Boots", 10131).getInt();



		newRunningShoesID = cfg.get("item", "New Running Boots", 10134).getInt();
		oldRunningShoesID = cfg.get("item", "Old Running Boots", 10135).getInt();
		wailmerPailID = cfg.get("item", "Wailmer Pail", 10136).getInt();
		oldRodID = cfg.get("item", "Old Rod", 10137).getInt();
		goodRodID = cfg.get("item", "Good Rod", 10138).getInt();
		superRodID = cfg.get("item", "Super Rod", 10139).getInt();



		
		pokeDex = new ItemPokedex(pokeDexID).setMaxStackSize(1);
		rareCandy = new PixelmonItem(rareCandyID, "healingitems/rarecandy", "Rare Candy").setCreativeTab(PixelmonCreativeTabs.restoration);
		potion = new ItemPotion(potionID, EnumPotions.Potion, "Potion");
		superPotion = new ItemPotion(superPotionID, EnumPotions.SuperPotion, "Super Potion");
		hyperPotion = new ItemPotion(hyperPotionID, EnumPotions.HyperPotion, "Hyper Potion");
		maxPotion = new ItemPotion(maxPotionID, EnumPotions.MaxPotion, "Max Potion");
		ether = new ItemEther(etherID, EnumEthers.Ether, "Ether");
		maxEther = new ItemEther(maxEtherID, EnumEthers.MaxEther, "Max Ether");
		elixir = new ItemEther(elixirID, EnumEthers.Elixir, "Elixir");
		maxElixir = new ItemEther(maxElixirID, EnumEthers.MaxElixir, "Max Elixir");
		fullRestore = new ItemStatusAilmentHealer(fullRestoreID, EnumStatusAilmentHealers.FullRestore, "Full Restore");
		antidote = new ItemStatusAilmentHealer(antidoteID, EnumStatusAilmentHealers.Antidote, "Antidote");
		parlyzHeal = new ItemStatusAilmentHealer(parlyzHealID, EnumStatusAilmentHealers.ParlyzHeal, "Paralyze Heal");
		awakening = new ItemStatusAilmentHealer(awakeningID, EnumStatusAilmentHealers.Awakening, "Awakening");
		burnHeal = new ItemStatusAilmentHealer(burnHealID, EnumStatusAilmentHealers.BurnHeal, "Burn Heal");
		iceHeal = new ItemStatusAilmentHealer(iceHealID, EnumStatusAilmentHealers.IceHeal, "Ice Heal");
		fullHeal = new ItemStatusAilmentHealer(fullHealID, EnumStatusAilmentHealers.FullHeal, "Full Heal");
		fireStone = new ItemEvolutionStone(fireStoneID, EnumEvolutionStone.Firestone, "Fire Stone").setCreativeTab(PixelmonCreativeTabs.natural);
		waterStone = new ItemEvolutionStone(waterStoneID, EnumEvolutionStone.Waterstone, "Water Stone").setCreativeTab(PixelmonCreativeTabs.natural);
		moonStone = new ItemEvolutionStone(moonStoneID, EnumEvolutionStone.Moonstone, "Moon Stone").setCreativeTab(PixelmonCreativeTabs.natural);
		thunderStone = new ItemEvolutionStone(thunderStoneID, EnumEvolutionStone.Thunderstone, "Thunder Stone").setCreativeTab(PixelmonCreativeTabs.natural);
		leafStone = new ItemEvolutionStone(leafStoneID, EnumEvolutionStone.Leafstone, "Leaf Stone").setCreativeTab(PixelmonCreativeTabs.natural);
		pcItem = new ItemBlock(pcItemID, PixelmonBlocks.pc, "pc", "PC");
		healerItem = new ItemBlock(healerItemID, PixelmonBlocks.healer, "healer", "Healer");
		anvilItem = new ItemBlock(anvilItemID, PixelmonBlocks.anvil, "anvil", "Anvil");
		tradeMachineItem = new ItemBlock(tradeMachineItemID, PixelmonBlocks.tradeMachine, "trademachine", "Trade Machine");

		thunderStoneShard = new PixelmonItem(thunderStoneShardID, "evolutionstones/thunderstoneshard", "Thunder Stone Shard")
				.setCreativeTab(PixelmonCreativeTabs.natural);
		leafStoneShard = new PixelmonItem(leafStoneShardID, "evolutionstones/leafstoneshard", "Leaf Stone Shard").setCreativeTab(PixelmonCreativeTabs.natural);
		waterStoneShard = new PixelmonItem(waterStoneShardID, "evolutionstones/waterstoneshard", "Water Stone Shard")
				.setCreativeTab(PixelmonCreativeTabs.natural);
		fireStoneShard = new PixelmonItem(fireStoneShardID, "evolutionstones/firestoneshard", "Fire Stone Shard").setCreativeTab(PixelmonCreativeTabs.natural);

		aluminiumIngot = new PixelmonItem(aluminiumIngotID, "aluminiumingot", "Aluminium Ingot").setCreativeTab(CreativeTabs.tabMaterials);
		aluminiumPlate = new PixelmonItem(aluminiumPlateID, "aluminiumplate", "Aluminium Plate").setCreativeTab(CreativeTabs.tabMaterials);

		hammerWood = new ItemHammer(hammerWoodID, EnumToolMaterial.WOOD, "hammerwood", "Wood Hammer");
		hammerStone = new ItemHammer(hammerStoneID, EnumToolMaterial.STONE, "hammerstone", "Stone Hammer");
		hammerIron = new ItemHammer(hammerIronID, EnumToolMaterial.IRON, "hammeriron", "Iron Hammer");
		hammerGold = new ItemHammer(hammerGoldID, EnumToolMaterial.GOLD, "hammergold", "Gold Hammer");
		hammerDiamond = new ItemHammer(hammerDiamondID, EnumToolMaterial.EMERALD, "hammerdiamond", "Diamond Hammer");
		hammerAluminium = new ItemHammer(hammerAluminiumID, Pixelmon.ALUMINIUM, "hammeraluminium", "Aluminium Hammer");

		swordAluminium = new PixelmonItemSword(swordAluminiumID, Pixelmon.ALUMINIUM, "pixelmon:aluminiumsword");
		axeAluminium = new PixelmonItemAxe(axeAluminiumID, Pixelmon.ALUMINIUM, "pixelmon:aluminiumaxe");
		spadeAluminium = new PixelmonItemSpade(shovelAluminiumID, Pixelmon.ALUMINIUM, "pixelmon:aluminiumspade");
		pickaxeAluminium = new PixelmonItemPickAxe(pickaxeAluminiumID, Pixelmon.ALUMINIUM, "pixelmon:aluminiumpickaxe");
		hoeAluminium = new PixelmonItemHoe(hoeAluminiumID, Pixelmon.ALUMINIUM, "pixelmon:aluminiumhoe");

		helmetAluminium = new ItemPixelmonArmor(helmetAluminiumID, 14, Pixelmon.ALUMINIUMARMOR, 0, 0, "pixelmon:aluminiumhelmet", "Aluminium Helmet");
		torsoAluminium = new ItemPixelmonArmor(torsoAluminiumID, 8, Pixelmon.ALUMINIUMARMOR, 0, 1, "pixelmon:aluminiumtorso", "Aluminium Torso");
		leggingsAluminium = new ItemPixelmonArmor(leggingsAluminiumID, 9, Pixelmon.ALUMINIUMARMOR, 0, 2, "pixelmon:aluminiumleggings", "Aluminium Leggings");
		bootsAluminium = new ItemPixelmonArmor(bootsAluminiumID, 7, Pixelmon.ALUMINIUMARMOR, 0, 3, "pixelmon:aluminiumboots", "Aluminium Boots");


		newRunningShoes = new ItemPixelmonBoots(newRunningShoesID, 6, Pixelmon.RUNNINGARMOR, 0, 3, "pixelmon:runningboots", "New Running Boots");
		oldRunningShoes = new ItemPixelmonBoots(oldRunningShoesID, 6, Pixelmon.OLDRUNNINGARMOR, 0, 3, "pixelmon:oldrunningboots", "Old Running Boots");
		wailmerPail = new ItemWailmerPail(wailmerPailID, "pixelmon:wailmerpail", "Wailmer Pail");
		
		oldRod = new ItemOldRod(oldRodID);
		goodRod = new ItemGoodRod(goodRodID);
		superRod = new ItemSuperRod(superRodID);
	}

	public static void addNames() {
		Pixelmon.proxy.registerBossDropItem(moonStone);
		PixelmonItemsPokeballs.addNames();
		PixelmonItemsApricorns.addNames();
		PixelmonItemsBadges.addNames();
		PixelmonItemsTMs.addNames();
		PixelmonItemsFossils.addNames();
		PixelmonItemsHeld.addNames();
		try {
			for (Field field : PixelmonItems.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					LanguageRegistry.addName(item, field.getAnnotation(Mod.Item.class).name());
					if (item instanceof ItemPotion || item instanceof ItemStatusAilmentHealer || item instanceof ItemHammer || item instanceof ItemEther
							|| item instanceof ItemEvolutionStone)
						Pixelmon.proxy.registerBossDropItem(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * er, what about <code>{@link Item#itemsList}[id]</code> ? - ZoraKirby
	 * @param id
	 * @return
	 */
	public static Item getFossilItem(int id) {
		try {
			for (Field field : PixelmonItems.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item instanceof ItemFossil)
						if (item.itemID == id)
							return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * aren't some of these methods like.... totally unnecessary?
	 */
	public static Item getItem(int id) {
		try {
			for (Field field : PixelmonItems.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Item.class)) {
					Item item = (Item) field.get(null);
					if (item.itemID == id)
						return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PixelmonItemsPokeballs.getItemFromID(id);
	}

}
