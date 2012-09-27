package pixelmon.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import pixelmon.blocks.BlockEvolutionStoneOre;
import pixelmon.blocks.BlockHealer;
import pixelmon.blocks.BlockPC;
import pixelmon.blocks.TileEntityHealer;
import pixelmon.blocks.TileEntityPC;
import pixelmon.blocks.apricornTrees.BlockApricornTree;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.items.ItemBlock;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;

import net.minecraftforge.common.Configuration;

public class PixelmonBlocks {
	public static int pokemonHealerIdleId;
	public static int pokemonHealerActiveId;
	public static int thunderStoneOreId;
	public static int leafStoneOreId;
	public static int waterStoneOreId;
	public static int pcId;
	public static int fireStoneOreId;

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
	@Mod.Block(name = "FireStone Ore", itemTypeClass = ItemBlock.class)
	public static Block fireStoneOre;

	public static void load(Configuration configuration) {
		pokemonHealerActiveId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PokemonHealerActive", 201).value);
		pokemonHealerIdleId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PokemonHealerIdle", 202).value);
		thunderStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ThunderStoneOre", 203).value);
		leafStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("LeafStoneOre", 204).value);
		fireStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("FireStoneOre", 207).value);
		pcId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PC", 205).value);
		waterStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("WaterStoneOre", 206).value);
		healer = new BlockHealer(pokemonHealerIdleId).setBlockName("PokeHealer");
		thunderStoneOre = new BlockEvolutionStoneOre(thunderStoneOreId, EnumEvolutionStone.Thunderstone, 3.0f).setBlockName("ThunderStoneOre");
		leafStoneOre = new BlockEvolutionStoneOre(leafStoneOreId, EnumEvolutionStone.Leafstone, 3.0f).setBlockName("LeafStoneOre");
		waterStoneOre = new BlockEvolutionStoneOre(waterStoneOreId, EnumEvolutionStone.Waterstone, 3.0f).setBlockName("WaterStoneOre");
		fireStoneOre = new BlockEvolutionStoneOre(fireStoneOreId, EnumEvolutionStone.Firestone, 3.0f).setBlockName("FireStoneOre");
		pc = new BlockPC(pcId, 0).setBlockName("pc");
		PixelmonBlocksApricornTrees.load(configuration);
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(healer);
		GameRegistry.registerBlock(thunderStoneOre);
		GameRegistry.registerBlock(leafStoneOre);
		GameRegistry.registerBlock(waterStoneOre);
		GameRegistry.registerBlock(fireStoneOre);
		GameRegistry.registerBlock(pc);
		
		GameRegistry.registerTileEntity(TileEntityPC.class, "Pokemon PC");
		GameRegistry.registerTileEntity(TileEntityHealer.class, "Healer");
		
		PixelmonBlocksApricornTrees.registerBlocks();
	}

	public static void addNames() {
		try
		{
			for(Field field : PixelmonBlocks.class.getFields())
			{
				if(field.isAnnotationPresent(Mod.Block.class))
				{
					Block block = (Block)field.get(null);
					LanguageRegistry.addName(block, field.getAnnotation(Mod.Block.class).name());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		PixelmonBlocksApricornTrees.addNames();
	}
	
}
