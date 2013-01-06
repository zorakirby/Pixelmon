package pixelmon.config;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import pixelmon.blocks.BlockAnvil;
import pixelmon.blocks.BlockEvolutionStoneOre;
import pixelmon.blocks.BlockFossil;
import pixelmon.blocks.BlockFossilMachine;
import pixelmon.blocks.BlockHealer;
import pixelmon.blocks.BlockPC;
import pixelmon.blocks.BlockTradeMachine;
import pixelmon.blocks.TileEntityAnvil;
import pixelmon.blocks.TileEntityFossilMachine;
import pixelmon.blocks.TileEntityHealer;
import pixelmon.blocks.TileEntityPC;
import pixelmon.blocks.TileEntityTradeMachine;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.items.ItemBlock;
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

	public static void load(Configuration configuration) {
		pokemonHealerActiveId = Integer.parseInt(configuration.getBlock("PokemonHealerActive", 300).value);
		pokemonHealerIdleId = Integer.parseInt(configuration.getBlock("PokemonHealerIdle", 301).value);
		thunderStoneOreId = Integer.parseInt(configuration.getBlock("ThunderStoneOre", 221).value);
		leafStoneOreId = Integer.parseInt(configuration.getBlock("LeafStoneOre", 222).value);
		waterStoneOreId = Integer.parseInt(configuration.getBlock("WaterStoneOre", 223).value);
		fireStoneOreId = Integer.parseInt(configuration.getBlock("FireStoneOre", 224).value);
		bauxiteId = Integer.parseInt(configuration.getBlock("Bauxite Ore", 220).value);
		pcId = Integer.parseInt(configuration.getBlock("PC", 307).value);
		anvilId = Integer.parseInt(configuration.getBlock("Anvil", 308).value);
		fossilMachineId = Integer.parseInt(configuration.getBlock("Fossil Machine", 309).value);
		fossilId = Integer.parseInt(configuration.getBlock("Fossil", 225).value);
		tradeMachineId = Integer.parseInt(configuration.getBlock("Trade Machine", 311).value);
		healer = new BlockHealer(pokemonHealerIdleId).setBlockName("PokeHealer");
		thunderStoneOre = new BlockEvolutionStoneOre(thunderStoneOreId, EnumEvolutionStone.Thunderstone, 3.0f).setBlockName("ThunderStoneOre");
		leafStoneOre = new BlockEvolutionStoneOre(leafStoneOreId, EnumEvolutionStone.Leafstone, 3.0f).setBlockName("LeafStoneOre");
		waterStoneOre = new BlockEvolutionStoneOre(waterStoneOreId, EnumEvolutionStone.Waterstone, 3.0f).setBlockName("WaterStoneOre");
		fireStoneOre = new BlockEvolutionStoneOre(fireStoneOreId, EnumEvolutionStone.Firestone, 3.0f).setBlockName("FireStoneOre");
		pc = new BlockPC(pcId, 0).setBlockName("pc");
		anvil = new BlockAnvil(anvilId).setBlockName("Anvil");
		fossilMachine = new BlockFossilMachine(fossilMachineId).setBlockName("Fossil Machine");
		bauxite = new Block(bauxiteId, 6, Material.rock).setBlockName("Bauxite Ore").setHardness(5f).setCreativeTab(PixelmonCreativeTabs.natural)
				.setTextureFile("/pixelmon/block/blocks.png");
		fossil = new BlockFossil(fossilId).setBlockName("Fossil").setHardness(5f);
		tradeMachine = new BlockTradeMachine(tradeMachineId).setBlockName("Trade Machine");
		PixelmonBlocksApricornTrees.load(configuration);
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(healer, "PokeHealer");
		GameRegistry.registerBlock(thunderStoneOre, "Thunderstone Ore");
		GameRegistry.registerBlock(leafStoneOre, "Leafstone Ore");
		GameRegistry.registerBlock(waterStoneOre, "Waterstone Ore");
		GameRegistry.registerBlock(fireStoneOre, "Firestone Ore");
		GameRegistry.registerBlock(pc, "PC");
		GameRegistry.registerBlock(anvil, "Pixelmon Anvil");
		GameRegistry.registerBlock(fossilMachine, "Fossil Machine");
		GameRegistry.registerBlock(bauxite, "Bauxite");
		GameRegistry.registerBlock(fossil, "Fossil");
		GameRegistry.registerBlock(tradeMachine, "Trading Machine");

		MinecraftForge.setBlockHarvestLevel(bauxite, "pickaxe", 2);

		GameRegistry.registerTileEntity(TileEntityPC.class, "Pokemon PC");
		GameRegistry.registerTileEntity(TileEntityHealer.class, "Healer");
		GameRegistry.registerTileEntity(TileEntityAnvil.class, "Anvil");
		GameRegistry.registerTileEntity(TileEntityFossilMachine.class, "Fossil Machine");
		GameRegistry.registerTileEntity(TileEntityTradeMachine.class, "Trade Machine");

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
