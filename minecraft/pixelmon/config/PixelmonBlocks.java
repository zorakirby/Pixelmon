package pixelmon.config;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import pixelmon.blocks.BlockAnvil;
import pixelmon.blocks.BlockBauxiteOre;
import pixelmon.blocks.BlockEvolutionRock;
import pixelmon.blocks.BlockEvolutionStoneOre;
import pixelmon.blocks.BlockFossil;
import pixelmon.blocks.BlockFossilCleaner;
import pixelmon.blocks.BlockFossilMachine;
import pixelmon.blocks.BlockHealer;
import pixelmon.blocks.BlockPC;
import pixelmon.blocks.BlockTradeMachine;
import pixelmon.blocks.TileEntityAnvil;
import pixelmon.blocks.TileEntityEvolutionRock;
import pixelmon.blocks.TileEntityFossilCleaner;
import pixelmon.blocks.TileEntityFossilMachine;
import pixelmon.blocks.TileEntityHealer;
import pixelmon.blocks.TileEntityPC;
import pixelmon.blocks.TileEntityTradeMachine;
import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.BlockFancyPillar;
import pixelmon.blocks.decorative.BlockPlus;
import pixelmon.blocks.decorative.BlockStairsPublic;
import pixelmon.blocks.decorative.BlockUnown;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import pixelmon.enums.EnumEvolutionRock;
import pixelmon.enums.EnumEvolutionStone;
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
	public static int fancyPillarId;
	//public static int fancyPillarBrokenId;
	public static int unownBlockId;
	public static int unownBlockId2;
	public static int shrineBlockId;
	public static int shrineBrickId;
	public static int shrineStairsId;
	public static int shrineBrickStairsId;

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
	@Mod.Block(name = "Ancient Pillar")
	public static Block fancyPillar;
	//@Mod.Block(name = "Broken Pillar")
	//public static Block brokenPillar;
	@Mod.Block(name = "Unown Block")
	public static Block unownBlock;
	@Mod.Block(name = "Unown Block")
	public static Block unownBlock2;
	@Mod.Block(name = "Shrine Block")
	public static Block shrineBlock;
	@Mod.Block(name = "Shrine Brick")
	public static Block shrineBrick;
	@Mod.Block(name = "Shrine Stairs")
	public static Block shrineStairs;
	@Mod.Block(name = "Shrine Brick Stairs")
	public static Block shrineBrickStairs;
	

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
		fancyPillarId = configuration.getBlock("Ancient Pillar", 320).getInt(320);
		//fancyPillarBrokenId = configuration.getBlock("Broken Ancient Pillar", 321).getInt(321);
		unownBlockId2 = configuration.getBlock("Unown Block (P-?)", 323).getInt(323); //THIS MUST COME BEFORE UNOWN BLOCK A-O, SO THE LETTERS COME AFTER EACH OTHER CORRECTLY, CUZ IT'S WEIRD
		unownBlockId = configuration.getBlock("Unown Block (A-O)", 322).getInt(322);
		shrineBlockId = configuration.getBlock("Shrine Block", 324).getInt(324);
		shrineBrickId = configuration.getBlock("Shrine Brick", 325).getInt(325);
		shrineStairsId = configuration.getBlock("Shrine Stairs", 326).getInt(326);
		shrineBrickStairsId = configuration.getBlock("Shrine Brick Stairs", 327).getInt(327);
		
		healer = new BlockHealer(pokemonHealerIdleId);
		thunderStoneOre = new BlockEvolutionStoneOre(thunderStoneOreId, EnumEvolutionStone.Thunderstone, 3.0f, "Thunder Stone Ore");
		leafStoneOre = new BlockEvolutionStoneOre(leafStoneOreId, EnumEvolutionStone.Leafstone, 3.0f, "Leaf Stone Ore");
		waterStoneOre = new BlockEvolutionStoneOre(waterStoneOreId, EnumEvolutionStone.Waterstone, 3.0f, "Water Stone Ore");
		fireStoneOre = new BlockEvolutionStoneOre(fireStoneOreId, EnumEvolutionStone.Firestone, 3.0f, "Fire Stone Ore");
		pc = new BlockPC(pcId, 0);
		anvil = new BlockAnvil(anvilId);
		fossilMachine = new BlockFossilMachine(fossilMachineId);
		bauxite = new BlockBauxiteOre(bauxiteId, Material.rock).setHardness(5f).setCreativeTab(PixelmonCreativeTabs.natural);
		fossil = new BlockFossil(fossilId).setHardness(5f);
		tradeMachine = new BlockTradeMachine(tradeMachineId);
		fossilCleaner = new BlockFossilCleaner(fossilCleanerId);
		mossyRock = new BlockEvolutionRock(mossyRockId, Material.rock, EnumEvolutionRock.MossyRock).setModelName("ModelMossyRock").setUnlocalizedName("mossyRock").setHardness(5f).setCreativeTab(PixelmonCreativeTabs.natural);
		icyRock = new BlockEvolutionRock(icyRockId, Material.rock, EnumEvolutionRock.IcyRock).setModelName("ModelIcyRock").setUnlocalizedName("icyRock").setHardness(5f).setCreativeTab(PixelmonCreativeTabs.natural);
		fancyPillar = new BlockFancyPillar(fancyPillarId, Material.rock).setDirectionalType(BlockContainerPlus.DirectionType.THREEAXIS).setModelName("ModelFancyPillar").setRenderOptions(-1, false, false).setHardness(6.5F).setCreativeTab(CreativeTabs.tabDecorations);
		//brokenPillar = new BlockFancyPillar(fancyPillarBrokenId, Material.rock).setIsDamaged(true).setDirectionalType(BlockContainerPlus.DirectionType.THREEAXIS).setModelName("ModelFancyPillar").setRenderOptions(-1, false, false).setHardness(3.5F).setUnlocalizedName("damagedPillar").setCreativeTab(CreativeTabs.tabDecorations);
		unownBlock = new BlockUnown(unownBlockId, Material.rock).setAlphaFlag(true).setHardness(5F).setCreativeTab(CreativeTabs.tabDecorations);
		unownBlock2 = new BlockUnown(unownBlockId2, Material.rock).setAlphaFlag(false).setHardness(5F).setCreativeTab(CreativeTabs.tabDecorations);
		shrineBrick = new BlockPlus(shrineBlockId, Material.rock).setIconName("pixelmon:shrinebrick").setUnlocalizedName("shrinebrick").setHardness(5F).setCreativeTab(CreativeTabs.tabDecorations);
		shrineBlock = new BlockPlus(shrineBrickId, Material.rock).setIconName("pixelmon:shrinebrickalt").setUnlocalizedName("shrinebrickalt").setHardness(5F).setCreativeTab(CreativeTabs.tabDecorations);
		shrineStairs = new BlockStairsPublic(shrineStairsId, shrineBlock, 0).setUnlocalizedName("shrinestairs");
		shrineBrickStairs = new BlockStairsPublic(shrineBrickStairsId, shrineBrick, 0).setUnlocalizedName("shrinebrickstairs");
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
		GameRegistry.registerBlock(mossyRock, "Mossy Rock");
		GameRegistry.registerBlock(icyRock, "Icy Rock");
		GameRegistry.registerBlock(fossilCleaner, "Fossil Cleaner");
		GameRegistry.registerBlock(fancyPillar, "Ancient Pillar");
		//GameRegistry.registerBlock(brokenPillar, "Damaged Pillar");
		GameRegistry.registerBlock(unownBlock, "UnownBlock");
		GameRegistry.registerBlock(unownBlock2, "UnownBlock2");
		GameRegistry.registerBlock(shrineBrick, "ShrineBlock");
		GameRegistry.registerBlock(shrineBlock, "ShrineBrick");
		GameRegistry.registerBlock(shrineStairs, "ShrineStairs");
		GameRegistry.registerBlock(shrineBrickStairs, "ShrineBrickStairs");

		MinecraftForge.setBlockHarvestLevel(bauxite, "pickaxe", 2);

		GameRegistry.registerTileEntity(TileEntityPC.class, "Pokemon PC");
		GameRegistry.registerTileEntity(TileEntityHealer.class, "Healer");
		GameRegistry.registerTileEntity(TileEntityAnvil.class, "Anvil");
		GameRegistry.registerTileEntity(TileEntityFossilMachine.class, "Fossil Machine");
		GameRegistry.registerTileEntity(TileEntityTradeMachine.class, "Trade Machine");
		GameRegistry.registerTileEntity(TileEntityFossilCleaner.class, "Fossil Cleaner");
		GameRegistry.registerTileEntity(TileEntityEvolutionRock.class, "Evolution Rock");
		GameRegistry.registerTileEntity(TileEntityDecorativeBase.class, "Decorative Tileent");

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
		//BlockUnown.registerNames();
		//PixelmonBlocksApricornTrees.addNames();
	}

}
