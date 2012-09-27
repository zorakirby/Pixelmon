package pixelmon.config;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;


public class PixelmonRecipes {

	public static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.healerItem),
				new Object[] { "IRI", "RDR", "IRI", Character.valueOf('D'), new ItemStack(Item.diamond), Character.valueOf('I'), Item.ingotIron, Character.valueOf('R'), Block.stone });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.pokeBall, 3), new Object[] { "RRR", "CBC", "III", Character.valueOf('R'), new ItemStack(Item.dyePowder, 1, 1), Character.valueOf('B'), Block.button,
				Character.valueOf('I'), Item.ingotIron, Character.valueOf('C'), PixelmonItems.coalDust });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.greatBall, 2),new Object[] { "LLL", "CBC", "III", Character.valueOf('L'), new ItemStack(Item.dyePowder, 1, 4), Character.valueOf('C'), PixelmonItems.coalDust, Character.valueOf('B'), Block.button,
						Character.valueOf('I'), Item.ingotIron });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.ultraBall, 1), new Object[] { "GGG", "CBC", "III", Character.valueOf('G'), Item.ingotGold, Character.valueOf('C'), PixelmonItems.coalDust, Character.valueOf('B'),
				Block.button, Character.valueOf('I'), Item.ingotIron });
//		GameRegistry.addRecipe(new ItemStack(PixelmonItems.masterBall), new Object[] { "PPP", "OBO", "DDD", Character.valueOf('P'), new ItemStack(Item.dyePowder, 1, 5), Character.valueOf('O'), Block.obsidian,
//				Character.valueOf('B'), Block.button, Character.valueOf('D'), Item.diamond });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.pokeChecker), new Object[] { " GG", "IIG", "II ", Character.valueOf('G'), Block.glass, Character.valueOf('I'), Item.ingotIron });
		GameRegistry.addShapelessRecipe(new ItemStack(PixelmonItems.rareCandy), new Object[] { Item.lightStoneDust, Item.appleGold, Item.sugar });
		GameRegistry.addShapelessRecipe(new ItemStack(PixelmonItems.potion, 4), new Object[] { Item.glassBottle, Item.bucketMilk, Item.wheat });
		GameRegistry.addShapelessRecipe(new ItemStack(PixelmonItems.coalDust, 4), new Object[] { Item.coal });
		GameRegistry.addRecipe(new ItemStack(Item.coal, 1), new Object[] { "XX", "XX", Character.valueOf('X'), PixelmonItems.coalDust });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.pokeDex, 1), new Object[] { "IPI", "DGD", "IRI", Character.valueOf('I'), Item.ingotIron, Character.valueOf('P'), Block.thinGlass, Character.valueOf('D'),
				new ItemStack(Item.dyePowder, 1, 1), Character.valueOf('G'), Block.redstoneLampIdle, Character.valueOf('R'), Item.redstone });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.thunderStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), PixelmonItems.thunderStoneShard });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.leafStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), PixelmonItems.leafStoneShard });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.waterStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), PixelmonItems.waterStoneShard });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.fireStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), PixelmonItems.fireStoneShard });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.pcItem, 1), new Object[]{"DPD", "DGD", "DRD", Character.valueOf('D'), Item.diamond, Character.valueOf('P'), Block.thinGlass, Character.valueOf('G'), Block.redstoneLampIdle, Character.valueOf('R'), Item.redstone});
	}
}
