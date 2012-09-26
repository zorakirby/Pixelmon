package pixelmon.config;

import java.lang.reflect.Field;
import java.util.ArrayList;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import pixelmon.blocks.TileEntityPC;
import pixelmon.blocks.apricornTrees.BlockApricornTree;
import pixelmon.blocks.apricornTrees.TileEntityApricornTree;
import pixelmon.enums.EnumApricornTrees;

import net.minecraft.src.Block;
import net.minecraftforge.common.Configuration;

public class ApricornTreeBlocks {

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
		apTreeBlackId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ApricornTreeBlack", 208).value);
		apTreeWhiteId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ApricornTreeWhite", 209).value);
		apTreeOrangeId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ApricornTreeOrange", 210).value);
		apTreeGreenId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ApricornTreeGreen", 211).value);
		apTreeBlueId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ApricornTreeBlue", 212).value);
		apTreeYellowId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ApricornTreeYellow", 213).value);
		apTreeRedId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ApricornTreeRed", 214).value);
		apricornTreeBlack = new BlockApricornTree(apTreeBlackId, EnumApricornTrees.Black).setBlockName("Black Apricorn Tree");
		apricornTreeWhite = new BlockApricornTree(apTreeWhiteId, EnumApricornTrees.White).setBlockName("White Apricorn Tree");
		apricornTreePink = new BlockApricornTree(apTreeOrangeId, EnumApricornTrees.Pink).setBlockName("Pink Apricorn Tree");
		apricornTreeGreen = new BlockApricornTree(apTreeGreenId, EnumApricornTrees.Green).setBlockName("Green Apricorn Tree");
		apricornTreeBlue = new BlockApricornTree(apTreeBlueId, EnumApricornTrees.Blue).setBlockName("Blue Apricorn Tree");
		apricornTreeYellow = new BlockApricornTree(apTreeYellowId, EnumApricornTrees.Yellow).setBlockName("Yellow Apricorn Tree");
		apricornTreeRed = new BlockApricornTree(apTreeRedId, EnumApricornTrees.Red).setBlockName("Red Apricorn Tree");
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
		GameRegistry.registerBlock(apricornTreeBlack);
		GameRegistry.registerBlock(apricornTreeWhite);
		GameRegistry.registerBlock(apricornTreePink);
		GameRegistry.registerBlock(apricornTreeGreen);
		GameRegistry.registerBlock(apricornTreeBlue);
		GameRegistry.registerBlock(apricornTreeYellow);
		GameRegistry.registerBlock(apricornTreeRed);
		
		GameRegistry.registerTileEntity(TileEntityApricornTree.class, "Apricorn Tree");
	}

}
