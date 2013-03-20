package pixelmon.config;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import pixelmon.blocks.apricornTrees.BlockApricornTree;
import pixelmon.blocks.apricornTrees.TileEntityApricornTree;
import pixelmon.enums.EnumApricornTrees;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelmonBlocksApricornTrees {

	public static int apTreeBlackId;
	public static int apTreeWhiteId;
	public static int apTreeOrangeId;
	public static int apTreeGreenId;
	public static int apTreeBlueId;
	public static int apTreeYellowId;
	public static int apTreeRedId;
	
	@Mod.Block(name = "Black Apricorn Tree")
	public static Block apricornTreeBlack;
	@Mod.Block(name = "White Apricorn Tree")
	public static Block apricornTreeWhite;
	@Mod.Block(name = "Pink Apricorn Tree")
	public static Block apricornTreePink;
	@Mod.Block(name = "Green Apricorn Tree")
	public static Block apricornTreeGreen;
	@Mod.Block(name = "Blue Apricorn Tree")
	public static Block apricornTreeBlue;
	@Mod.Block(name = "Yellow Apricorn Tree")
	public static Block apricornTreeYellow;
	@Mod.Block(name = "Red Apricorn Tree")
	public static Block apricornTreeRed;
	
	public static void load(Configuration configuration) {
		apTreeBlackId = configuration.getBlock("ApricornTreeBlack", 208).getInt(208);
		apTreeWhiteId = configuration.getBlock("ApricornTreeWhite", 209).getInt(209);
		apTreeOrangeId = configuration.getBlock("ApricornTreeOrange", 210).getInt(210);
		apTreeGreenId = configuration.getBlock("ApricornTreeGreen", 211).getInt(211);
		apTreeBlueId = configuration.getBlock("ApricornTreeBlue", 212).getInt(212);
		apTreeYellowId = configuration.getBlock("ApricornTreeYellow", 213).getInt(213);
		apTreeRedId = configuration.getBlock("ApricornTreeRed", 214).getInt(214);
		apricornTreeBlack = new BlockApricornTree(apTreeBlackId, EnumApricornTrees.Black);
		apricornTreeWhite = new BlockApricornTree(apTreeWhiteId, EnumApricornTrees.White);
		apricornTreePink = new BlockApricornTree(apTreeOrangeId, EnumApricornTrees.Pink);
		apricornTreeGreen = new BlockApricornTree(apTreeGreenId, EnumApricornTrees.Green);
		apricornTreeBlue = new BlockApricornTree(apTreeBlueId, EnumApricornTrees.Blue);
		apricornTreeYellow = new BlockApricornTree(apTreeYellowId, EnumApricornTrees.Yellow);
		apricornTreeRed = new BlockApricornTree(apTreeRedId, EnumApricornTrees.Red);
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
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(apricornTreeBlack, "Black Apricorn Tree");
		GameRegistry.registerBlock(apricornTreeWhite, "White Apricorn Tree");
		GameRegistry.registerBlock(apricornTreePink, "Pink Apricorn Tree");
		GameRegistry.registerBlock(apricornTreeGreen, "Green Apricorn Tree");
		GameRegistry.registerBlock(apricornTreeBlue, "Blue Apricorn Tree");
		GameRegistry.registerBlock(apricornTreeYellow, "Yellow Apricorn Tree");
		GameRegistry.registerBlock(apricornTreeRed, "Red Apricorn Tree");
		
		GameRegistry.registerTileEntity(TileEntityApricornTree.class, "Apricorn Tree");
	}

}
