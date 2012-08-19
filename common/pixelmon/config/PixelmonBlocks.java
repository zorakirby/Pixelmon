package pixelmon.config;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import pixelmon.blocks.BlockEvolutionStoneOre;
import pixelmon.blocks.BlockHealer;
import pixelmon.blocks.BlockPC;
import pixelmon.blocks.TileEntityHealer;
import pixelmon.blocks.TileEntityPC;
import pixelmon.items.ItemBlock;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;

public class PixelmonBlocks {
	public static int pokemonHealerIdleId;
	public static int pokemonHealerActiveId;
	public static int thunderStoneOreId;
	public static int leafStoneOreId;
	public static int waterStoneOreId;
	public static int pcId;

	@Mod.Block(name = "Healer", itemTypeClass = ItemBlock.class)
	public static Block healer;
	@Mod.Block(name = "Thunderstone Ore")
	public static Block thunderStoneOre;
	@Mod.Block(name = "LeafStone Ore")
	public static Block leafStoneOre;
	@Mod.Block(name = "WaterStone Ore")
	public static Block waterStoneOre;
	@Mod.Block(name = "Pokemon PC", itemTypeClass = ItemBlock.class)
	public static Block pc;

	public static void load(Configuration configuration) {
		pokemonHealerActiveId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PokemonHealerActive", 201).value);
		pokemonHealerIdleId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PokemonHealerIdle", 202).value);
		thunderStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ThunderStoneOre", 203).value);
		leafStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("LeafStoneOre", 204).value);
		pcId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PC", 205).value);
		waterStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("WaterStoneOre", 206).value);
		healer = new BlockHealer(pokemonHealerIdleId).setBlockName("PokeHealer");
		thunderStoneOre = new BlockEvolutionStoneOre(thunderStoneOreId, 0, 3.0f).setBlockName("ThunderStoneOre");
		leafStoneOre = new BlockEvolutionStoneOre(leafStoneOreId, 1, 3.0f).setBlockName("LeafStoneOre");
		waterStoneOre = new BlockEvolutionStoneOre(waterStoneOreId, 2, 3.0f).setBlockName("WaterStoneOre");
		pc = new BlockPC(pcId, 0).setBlockName("pc");
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(healer);
		GameRegistry.registerBlock(thunderStoneOre);
		GameRegistry.registerBlock(leafStoneOre);
		GameRegistry.registerBlock(waterStoneOre);
		GameRegistry.registerBlock(pc);
		
		GameRegistry.registerTileEntity(TileEntityPC.class, "Pokemon PC");
		GameRegistry.registerTileEntity(TileEntityHealer.class, "Healer");
	}

	public static void addNames() {
		ModLoader.addName(healer, "Healer");
		ModLoader.addName(thunderStoneOre, "Thunder Stone Ore");
		ModLoader.addName(leafStoneOre, "Leaf Stone Ore");
		ModLoader.addName(waterStoneOre, "Water Stone Ore");
		ModLoader.addName(pc, "PC");
	}

	public static void setTextureIds() {
		thunderStoneOre.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/pixelmon/block/Thunderstone.png");
		leafStoneOre.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/pixelmon/block/Leafstone.png");
		waterStoneOre.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/pixelmon/block/Waterstone.png");
	}
}
