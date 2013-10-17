package pixelmon.config;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import pixelmon.blocks.*;
import pixelmon.blocks.*;
import pixelmon.enums.EnumEvolutionRock;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumShrine;
import pixelmon.items.ItemBlock;
import pixelmon.items.PixelmonItemBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelmonBlocks {
	public static int pokemonHealerIdleId;
	public static int pokemonHealerActiveId;
	public static int thunderStoneOreId;
	public static int leafStoneOreId;
	public static int waterStoneOreId;
	public static int fireStoneOreId;
	public static int pcId;
	public static int anvilId;
	public static int fossilMachineId;
	public static int fossilId;
	public static int bauxiteId;
	public static int tradeMachineId;
	public static int fossilCleanerId;
	public static int mossyRockId;
	public static int icyRockId;
	public static int potionMakerId;

	public static int shrineUnoID;
	public static int shrineDosID;
	public static int shrineTresID;

	@Mod.Block(name = "Thunderstone Ore")
	public static Block thunderStoneOre;
	@Mod.Block(name = "LeafStone Ore")
	public static Block leafStoneOre;
	@Mod.Block(name = "WaterStone Ore")
	public static Block waterStoneOre;
	@Mod.Block(name = "FireStone Ore", itemTypeClass = ItemBlock.class)
	public static Block fireStoneOre;
	@Mod.Block(name = "Bauxite Ore")
	public static Block bauxite;
	@Mod.Block(name = "Healer", itemTypeClass = ItemBlock.class)
	public static Block healer;
	@Mod.Block(name = "Pokemon PC", itemTypeClass = ItemBlock.class)
	public static Block pc;
	@Mod.Block(name = "Anvil", itemTypeClass = ItemBlock.class)
	public static Block anvil;
	@Mod.Block(name = "Fossil Machine", itemTypeClass = ItemBlock.class)
	public static Block fossilMachine;
	@Mod.Block(name = "Fossil")
	public static Block fossil;
	@Mod.Block(name = "Trade Machine", itemTypeClass = ItemBlock.class)
	public static Block tradeMachine;
	@Mod.Block(name = "Fossil Cleaner", itemTypeClass = ItemBlock.class)
	public static Block fossilCleaner;
	@Mod.Block(name = "Mossy Rock", itemTypeClass = ItemBlock.class)
	public static Block mossyRock;
	@Mod.Block(name = "Icy Rock", itemTypeClass = ItemBlock.class)
	public static Block icyRock;

	@Mod.Block(name = "Articuno Shrine", itemTypeClass = ItemBlock.class)
	public static Block shrineUno;
	@Mod.Block(name = "Zapdos Shrine", itemTypeClass = ItemBlock.class)
	public static Block shrineDos;
	@Mod.Block(name = "Moltres Shrine", itemTypeClass = ItemBlock.class)
	public static Block shrineTres;

	public static void load(Configuration configuration) {
		pokemonHealerActiveId = configuration.getBlock("PokemonHealerActive", 300).getInt(300);
		pokemonHealerIdleId = configuration.getBlock("PokemonHealerIdle", 301).getInt(301);
		thunderStoneOreId = configuration.getBlock("ThunderStoneOre", 221).getInt(221);
		leafStoneOreId = configuration.getBlock("LeafStoneOre", 222).getInt(222);
		waterStoneOreId = configuration.getBlock("WaterStoneOre", 223).getInt(223);
		fireStoneOreId = configuration.getBlock("FireStoneOre", 224).getInt(224);
		bauxiteId = configuration.getBlock("Bauxite Ore", 220).getInt(220);
		pcId = configuration.getBlock("PC", 307).getInt(307);
		anvilId = configuration.getBlock("Anvil", 308).getInt(308);
		fossilMachineId = configuration.getBlock("Fossil Machine", 309).getInt(309);
		fossilId = configuration.getBlock("Fossil", 225).getInt(225);
		tradeMachineId = configuration.getBlock("Trade Machine", 311).getInt(311);
		fossilCleanerId = configuration.getBlock("Fossil Cleaner", 312).getInt(312);

		PixelmonBlocksApricornTrees.load(configuration);

		mossyRockId = configuration.getBlock("Mossy Rock", 318).getInt(318);
		icyRockId = configuration.getBlock("Icy Rock", 319).getInt(319);
		potionMakerId = configuration.getBlock("Potion Maker", 321).getInt(321);
		shrineUnoID = configuration.getBlock("Articuno Shrine", 322).getInt(322);
		shrineDosID = configuration.getBlock("Zapdos Shrine", 323).getInt(323);
		shrineTresID = configuration.getBlock("Moltres Shrine", 324).getInt(324);

		healer = new BlockHealer(pokemonHealerIdleId);
		thunderStoneOre = new BlockEvolutionStoneOre(thunderStoneOreId, EnumEvolutionStone.Thunderstone, 3.0f, "Thunder Stone Ore");
		leafStoneOre = new BlockLeafEvolutionOre(leafStoneOreId, EnumEvolutionStone.Leafstone, 3.0f, "Leaf Stone Ore");
		waterStoneOre = new BlockEvolutionStoneOre(waterStoneOreId, EnumEvolutionStone.Waterstone, 3.0f, "Water Stone Ore");
		fireStoneOre = new BlockEvolutionStoneOre(fireStoneOreId, EnumEvolutionStone.Firestone, 3.0f, "Fire Stone Ore");
		pc = new BlockPC(pcId, 0);
		anvil = new BlockAnvil(anvilId);
		fossilMachine = new BlockFossilMachine(fossilMachineId);
		bauxite = new BlockBauxiteOre(bauxiteId, Material.rock).setHardness(5f).setCreativeTab(PixelmonCreativeTabs.natural);
		fossil = new BlockFossil(fossilId).setHardness(5f);
		tradeMachine = new BlockTradeMachine(tradeMachineId);
		fossilCleaner = new BlockFossilCleaner(fossilCleanerId);
		mossyRock = new BlockEvolutionRock(mossyRockId, Material.rock, EnumEvolutionRock.MossyRock).setHardness(5f).setCreativeTab(PixelmonCreativeTabs.natural);
		icyRock = new BlockEvolutionRock(icyRockId, Material.rock, EnumEvolutionRock.IcyRock).setHardness(5f).setCreativeTab(PixelmonCreativeTabs.natural);

		shrineUno = new BlockShrine(shrineUnoID, Material.rock, EnumShrine.Articuno).setBlockUnbreakable().setUnlocalizedName("Articuno");
		shrineDos = new BlockShrine(shrineDosID, Material.rock, EnumShrine.Zapdos).setBlockUnbreakable().setUnlocalizedName("Zapdos");
		shrineTres = new BlockShrine(shrineTresID, Material.rock, EnumShrine.Moltres).setBlockUnbreakable().setUnlocalizedName("Moltres");
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(healer, "PokeHealer");
		GameRegistry.registerBlock(thunderStoneOre, PixelmonItemBlock.class, "Thunderstone Ore");
		GameRegistry.registerBlock(leafStoneOre, "Leafstone Ore");
		GameRegistry.registerBlock(waterStoneOre, "Waterstone Ore");
		GameRegistry.registerBlock(fireStoneOre, "Firestone Ore");
		GameRegistry.registerBlock(pc, "PC");
		GameRegistry.registerBlock(anvil, "Pixelmon Anvil");
		GameRegistry.registerBlock(fossilMachine, "Fossil Machine");
		GameRegistry.registerBlock(bauxite, "Bauxite");
		GameRegistry.registerBlock(fossil, "Fossil");
		GameRegistry.registerBlock(tradeMachine, "Trading Machine");
		GameRegistry.registerBlock(fossilCleaner, "Fossil Cleaner");
		//GameRegistry.registerBlock(shrineUno, "Articuno Shrine");
		//GameRegistry.registerBlock(shrineDos, "Zapdos Shrine");
		//GameRegistry.registerBlock(shrineTres, "Moltres Shrine");

		MinecraftForge.setBlockHarvestLevel(bauxite, "pickaxe", 2);

		GameRegistry.registerTileEntity(TileEntityPC.class, "Pokemon PC");
		GameRegistry.registerTileEntity(TileEntityHealer.class, "Healer");
		GameRegistry.registerTileEntity(TileEntityAnvil.class, "Anvil");
		GameRegistry.registerTileEntity(TileEntityFossilMachine.class, "Fossil Machine");
		GameRegistry.registerTileEntity(TileEntityTradeMachine.class, "Trade Machine");
		GameRegistry.registerTileEntity(TileEntityFossilCleaner.class, "Fossil Cleaner");
		GameRegistry.registerTileEntity(TileEntityEvolutionRock.class, "Evolution Rock");
		//GameRegistry.registerTileEntity(TileEntityShrine.class, "Bird Shrine");

		PixelmonBlocksApricornTrees.registerBlocks();
	}

	public static void addNames() {
		try {
			for (Field field : PixelmonBlocks.class.getFields()) {
				if (field.isAnnotationPresent(Mod.Block.class)) {
					Block block = (Block) field.get(null);
					LanguageRegistry.addName(block, field.getAnnotation(Mod.Block.class).name());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PixelmonBlocksApricornTrees.addNames();
	}

}
