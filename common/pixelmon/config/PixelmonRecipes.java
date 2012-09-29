package pixelmon.config;

import pixelmon.enums.EnumPokeballs;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PixelmonRecipes {

	public static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.healerItem), new Object[] { "IRI", "RDR", "IRI", Character.valueOf('D'),
				new ItemStack(Item.diamond), Character.valueOf('I'), Item.ingotIron, Character.valueOf('R'), Block.stone });
		// GameRegistry.addRecipe(new ItemStack(PixelmonItems.masterBall), new
		// Object[] { "PPP", "OBO", "DDD", Character.valueOf('P'), new
		// ItemStack(Item.dyePowder, 1, 5), Character.valueOf('O'),
		// Block.obsidian,
		// Character.valueOf('B'), Block.button, Character.valueOf('D'),
		// Item.diamond });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.pokeChecker),
				new Object[] { " GG", "IIG", "II ", Character.valueOf('G'), Block.glass, Character.valueOf('I'), Item.ingotIron });
		GameRegistry.addShapelessRecipe(new ItemStack(PixelmonItems.rareCandy), new Object[] { Item.lightStoneDust, Item.appleGold, Item.sugar });
		GameRegistry.addShapelessRecipe(new ItemStack(PixelmonItems.potion, 4), new Object[] { Item.glassBottle, Item.bucketMilk, Item.wheat });
		GameRegistry.addShapelessRecipe(new ItemStack(PixelmonItems.coalDust, 4), new Object[] { Item.coal });
		GameRegistry.addRecipe(new ItemStack(Item.coal, 1), new Object[] { "XX", "XX", Character.valueOf('X'), PixelmonItems.coalDust });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.pokeDex, 1),
				new Object[] { "IPI", "DGD", "IRI", Character.valueOf('I'), Item.ingotIron, Character.valueOf('P'), Block.thinGlass, Character.valueOf('D'),
						new ItemStack(Item.dyePowder, 1, 1), Character.valueOf('G'), Block.redstoneLampIdle, Character.valueOf('R'), Item.redstone });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.thunderStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'),
				PixelmonItems.thunderStoneShard });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.leafStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'),
				PixelmonItems.leafStoneShard });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.waterStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'),
				PixelmonItems.waterStoneShard });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.fireStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'),
				PixelmonItems.fireStoneShard });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.pcItem, 1),
				new Object[] { "DPD", "DGD", "DRD", Character.valueOf('D'), Item.diamond, Character.valueOf('P'), Block.thinGlass, Character.valueOf('G'),
						Block.redstoneLampIdle, Character.valueOf('R'), Item.redstone });

		GameRegistry.addSmelting(PixelmonItems.apricornBlack.shiftedIndex, new ItemStack(PixelmonItems.apricornBlackCooked, 1), 1);
		GameRegistry.addSmelting(PixelmonItems.apricornWhite.shiftedIndex, new ItemStack(PixelmonItems.apricornWhiteCooked, 1), 1);
		GameRegistry.addSmelting(PixelmonItems.apricornGreen.shiftedIndex, new ItemStack(PixelmonItems.apricornGreenCooked, 1), 1);
		GameRegistry.addSmelting(PixelmonItems.apricornPink.shiftedIndex, new ItemStack(PixelmonItems.apricornPinkCooked, 1), 1);
		GameRegistry.addSmelting(PixelmonItems.apricornBlue.shiftedIndex, new ItemStack(PixelmonItems.apricornBlueCooked, 1), 1);
		GameRegistry.addSmelting(PixelmonItems.apricornYellow.shiftedIndex, new ItemStack(PixelmonItems.apricornYellowCooked, 1), 1);
		GameRegistry.addSmelting(PixelmonItems.apricornRed.shiftedIndex, new ItemStack(PixelmonItems.apricornRedCooked, 1), 1);

		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.ironDisc, 5), new Object[] { "XXX", Character.valueOf('X'), Item.ingotIron });

		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.pokeBallDisc, 5), new Object[] { "XXX", Character.valueOf('X'),
				PixelmonItems.apricornRedCooked });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.greatBallDisc, 2), new Object[] { "BRB", Character.valueOf('R'),
				PixelmonItems.apricornRedCooked, Character.valueOf('B'), PixelmonItems.apricornBlueCooked });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.ultraBallDisc, 1), new Object[] { "BYB", Character.valueOf('B'),
				PixelmonItems.apricornBlackCooked, Character.valueOf('X'), PixelmonItems.apricornYellowCooked });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.friendBallDisc, 3), new Object[] { "GYR", Character.valueOf('G'),
				PixelmonItems.apricornGreenCooked, Character.valueOf('Y'), PixelmonItems.apricornYellowCooked, Character.valueOf('R'),
				PixelmonItems.apricornRedCooked });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.moonBallDisc, 2), new Object[] { "BYX", Character.valueOf('B'),
				PixelmonItems.apricornBlueCooked, Character.valueOf('Y'), PixelmonItems.apricornYellowCooked, Character.valueOf('X'),
				PixelmonItems.apricornBlackCooked });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.loveBallDisc, 5), new Object[] { "XXX", Character.valueOf('X'),
				PixelmonItems.apricornPinkCooked });

		for (EnumPokeballs p: EnumPokeballs.values()){
			if (p != EnumPokeballs.MasterBall)
				GameRegistry.addShapelessRecipe(new ItemStack(p.getItem(), 1), new Object[] { Block.button, PixelmonItemsPokeballs.ironBase,
				p.getLid() });
		}
	}
}
