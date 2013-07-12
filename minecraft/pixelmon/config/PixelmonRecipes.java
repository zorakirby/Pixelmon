package pixelmon.config;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pixelmon.enums.EnumApricorns;
import pixelmon.enums.EnumPokeballs;
import cpw.mods.fml.common.registry.GameRegistry;

public class PixelmonRecipes {

	public static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.healerItem), new Object[] { "IRI", "RDR", "IRI", Character.valueOf('D'),
				new ItemStack(Item.diamond), Character.valueOf('I'), PixelmonItems.aluminiumPlate, Character.valueOf('R'), Item.ingotIron });

		GameRegistry.addRecipe(new ItemStack(PixelmonItemsFossils.fossilMachineBase), new Object[] { "   ", "III", "III", Character.valueOf('I'),
				PixelmonItems.aluminiumPlate });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsFossils.fossilMachineDisplay), new Object[] { " R ", "ROR", " R ", Character.valueOf('O'),
				Block.obsidian, Character.valueOf('R'), Item.redstone });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsFossils.fossilMachineTop), new Object[] { "IRI", "III", "   ", Character.valueOf('I'),
				PixelmonItems.aluminiumPlate, Character.valueOf('R'), Item.redstone });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsFossils.fossilMachineTank), new Object[] { "GWG", "GWG", "GWG", Character.valueOf('G'),
				Block.thinGlass, Character.valueOf('W'), Item.bucketWater });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsFossils.fossilMachineItem), new Object[] { " L ", " TD", " B ", Character.valueOf('L'),
				new ItemStack(PixelmonItemsFossils.fossilMachineTop), Character.valueOf('T'), PixelmonItemsFossils.fossilMachineTank, Character.valueOf('D'),
				PixelmonItemsFossils.fossilMachineDisplay, Character.valueOf('B'), PixelmonItemsFossils.fossilMachineBase });
		GameRegistry.addRecipe(new ItemStack(PixelmonItemsFossils.fossilCleanerItem), new Object[] { "AAA", "AAA", "RGR", Character.valueOf('A'),
				PixelmonItems.aluminiumPlate, Character.valueOf('R'), Item.redstone, Character.valueOf('G'), Block.thinGlass });
		// GameRegistry.addRecipe(new ItemStack(PixelmonItems.masterBall), new
		// Object[] { "PPP", "OBO", "DDD", Character.valueOf('P'), new
		// ItemStack(Item.dyePowder, 1, 5), Character.valueOf('O'),
		// Block.obsidian,
		// Character.valueOf('B'), Block.button, Character.valueOf('D'),
		// Item.diamond });
		if (PixelmonConfig.allowRareCandyCrafting)
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
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.pcItem, 1), new Object[] { "DPD", "DGD", "DRD", Character.valueOf('D'),
				PixelmonItems.aluminiumPlate, Character.valueOf('P'), Block.thinGlass, Character.valueOf('G'), Block.redstoneLampIdle, Character.valueOf('R'),
				Item.redstone });

		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerWood, 1),
				new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Block.planks, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerStone, 1), new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Block.cobblestone,
				Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerIron, 1), new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Item.ingotIron,
				Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerGold, 1), new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Item.ingotGold,
				Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerDiamond, 1), new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'), Item.diamond,
				Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hammerAluminium, 1), new Object[] { "WWW", "WSW", " S ", Character.valueOf('W'),
				PixelmonItems.aluminiumIngot, Character.valueOf('S'), Item.stick });

		GameRegistry.addRecipe(new ItemStack(PixelmonItems.swordAluminium, 1), new Object[] { "W", "W", "S", Character.valueOf('W'),
				PixelmonItems.aluminiumIngot, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.axeAluminium, 1), new Object[] { "WW ", "WS ", " S ", Character.valueOf('W'),
				PixelmonItems.aluminiumIngot, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.spadeAluminium, 1), new Object[] { "W", "S", "S", Character.valueOf('W'),
				PixelmonItems.aluminiumIngot, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.pickaxeAluminium, 1), new Object[] { "WWW", " S ", " S ", Character.valueOf('W'),
				PixelmonItems.aluminiumIngot, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.hoeAluminium, 1), new Object[] { "WW ", " S ", " S ", Character.valueOf('W'),
				PixelmonItems.aluminiumIngot, Character.valueOf('S'), Item.stick });

		GameRegistry.addRecipe(new ItemStack(PixelmonItems.helmetAluminium, 1), new Object[] { "SSS", "S S", Character.valueOf('S'),
				PixelmonItems.aluminiumIngot });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.torsoAluminium, 1), new Object[] { "S S", "SSS", "SSS", Character.valueOf('S'),
				PixelmonItems.aluminiumIngot });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.leggingsAluminium, 1), new Object[] { "SSS", "S S", "S S", Character.valueOf('S'),
				PixelmonItems.aluminiumIngot });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.bootsAluminium, 1), new Object[] { "S S", "S S", Character.valueOf('S'),
				PixelmonItems.aluminiumIngot });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.newRunningShoes, 1), new Object[] { "DOD", "I I", "F F", Character.valueOf('D'), Item.diamond,
				Character.valueOf('O'), PixelmonItems.oldRunningShoes, Character.valueOf('I'), Item.ingotIron, Character.valueOf('F'), Item.feather });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.oldRunningShoes, 1), new Object[] { "IBI", "I I", "F F", Character.valueOf('I'), Item.ingotIron,
				Character.valueOf('B'), Item.bootsLeather, Character.valueOf('F'), Item.feather });
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.wailmerPail, 1), new Object[] { "BBB", " W ", "III", Character.valueOf('I'), Item.ingotIron,
				Character.valueOf('B'), Item.bone, Character.valueOf('W'), Item.bucketWater });
		
		GameRegistry.addRecipe(new ItemStack(PixelmonItems.oldRod, 1), new Object[] { "  S", " ST", "S P", Character.valueOf('S'), Item.stick,
			Character.valueOf('T'), Item.silk, Character.valueOf('P'), PixelmonItemsPokeballs.pokeBall });

		

		GameRegistry.addRecipe(new ItemStack(PixelmonItems.anvilItem, 1), new Object[] { "XXX", "XX ", "XXX", Character.valueOf('X'), Item.ingotIron });

		GameRegistry.addSmelting(PixelmonBlocks.bauxite.blockID, new ItemStack(PixelmonItems.aluminiumIngot), new Random().nextInt(3));

		for (EnumApricorns a : EnumApricorns.values()) {
			GameRegistry.addSmelting(PixelmonItemsApricorns.getApricorn(a).itemID, new ItemStack(PixelmonItemsApricorns.getCookedApricorn(a), 1), 1);
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
