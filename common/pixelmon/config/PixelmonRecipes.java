package pixelmon.config;

import pixelmon.enums.EnumApricorns;
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
		GameRegistry.addShapelessRecipe(new ItemStack(PixelmonItems.rareCandy), new Object[] { Item.lightStoneDust, Item.appleGold, Item.sugar });
		GameRegistry.addShapelessRecipe(new ItemStack(PixelmonItems.potion, 4), new Object[] { Item.glassBottle, Item.bucketMilk, Item.wheat });
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

		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerWood, 1),
				new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Block.planks, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerStone, 1),
				new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Block.cobblestone, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerIron, 1), new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Item.ingotIron,
				Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerGold, 1), new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Item.ingotGold,
				Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerDiamond, 1), new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Item.diamond,
				Character.valueOf('S'), Item.stick });

		GameRegistry.addRecipe(new ItemStack(PixelmonItems.anvilItem, 1), new Object[] { "XXX", "XX ", "XXX", Character.valueOf('X'), Item.ingotIron });

		for (EnumApricorns a : EnumApricorns.values()) {
			GameRegistry.addSmelting(PixelmonItemsApricorns.getApricorn(a).shiftedIndex, new ItemStack(PixelmonItemsApricorns.getCookedApricorn(a), 1), 1);
		}

		GameRegistry.addRecipe(new ItemStack(PixelmonItemsPokeballs.ironDisc, 5), new Object[] { "XXX", Character.valueOf('X'), Item.ingotIron });

		for (EnumPokeballs p : EnumPokeballs.values()) {
			if (p != EnumPokeballs.MasterBall) {
				GameRegistry.addRecipe(
						new ItemStack(p.getDisc(), p.quantityMade),
						new Object[] { "XYZ", Character.valueOf('X'), PixelmonItemsApricorns.getCookedApricorn(p.recipe[0]), Character.valueOf('Y'),
								PixelmonItemsApricorns.getCookedApricorn(p.recipe[1]), Character.valueOf('Z'),
								PixelmonItemsApricorns.getCookedApricorn(p.recipe[2]) });

				GameRegistry.addShapelessRecipe(new ItemStack(p.getItem(), 1), new Object[] { Block.stoneButton, PixelmonItemsPokeballs.ironBase, p.getLid() });
			}
		}
	}
}
