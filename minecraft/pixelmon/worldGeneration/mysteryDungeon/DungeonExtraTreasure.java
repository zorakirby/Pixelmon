package pixelmon.worldGeneration.mysteryDungeon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import pixelmon.Pixelmon;
import pixelmon.RandomHelper;
import pixelmon.WorldHelper;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.config.PixelmonItemsTMs;
import pixelmon.enums.EnumApricorns;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumPokeballs;
import static pixelmon.enums.EnumPokeballs.*;
import pixelmon.enums.EnumTreasureRarity;
import static pixelmon.enums.EnumTreasureRarity.*;
import pixelmon.items.ArmorToolLibrary;
import pixelmon.items.ItemTM;
import pixelmon.util.ChancedStack;
import pixelmon.util.ChancedWrapper;
import pixelmon.util.CommonHelper;
import pixelmon.util.FunctionHelper;


import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import static net.minecraft.item.EnumArmorMaterial.*;
import net.minecraft.item.EnumToolMaterial;
import static net.minecraft.item.EnumToolMaterial.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraftforge.common.ForgeDirection;


public class DungeonExtraTreasure extends AbstractDungeonExtra{


	private static boolean debugRegistration = false, init = false;
	private static final String sayRegistration = "Registering %s with chance '%s' as %s";
	public static HashSet<ChancedStack> 
	commonItems = new HashSet(),
	uncommonItems = new HashSet(),
	semirareItems = new HashSet(),
	rareItems = new HashSet(),
	veryrareItems = new HashSet(),
	superrareItems = new HashSet(),
	ultrarareItems = new HashSet(),
	tms = new HashSet(),
	allItems = new HashSet();


	private static HashSet<ChancedStack>[] itemSets = new HashSet[]{
		commonItems,
		uncommonItems,
		semirareItems,
		rareItems, 
		veryrareItems, 
		superrareItems,
		ultrarareItems,
	};

	public final int minChests, maxChests;
	
	protected final float firstFloorSlotChance, finalFloorSlotChance;

	public final HashSet<ChancedStack> itemCollection = new HashSet();
	/**
	 * Should only be used with ChancedStack's constructor and {@link ChancedStack#newStack(Random)}
	 */
	protected static Random statRandom = new Random();
	
	protected final Random myRandom;

	
	/**
	 * Constructor can only be used after Pixelmon 
	 * {@link Pixelmon#modsLoaded(cpw.mods.fml.common.event.FMLPostInitializationEvent) has completed 
	 * the post-init phase} or else an {@code IllegalStateException} will be thrown
	 * 
	 *
	 */
	public DungeonExtraTreasure(int minChests, int maxChests, float firstFloorSlotFillChance, float finalFloorSlotFillChance, EnumTreasureRarity... pickFrom){
		this.minChests = minChests;
		this.maxChests = maxChests;
		this.myRandom = new Random();
		this.firstFloorSlotChance = firstFloorSlotFillChance;
		this.finalFloorSlotChance = finalFloorSlotFillChance;
		for(EnumTreasureRarity rarity : pickFrom){
			int id = rarity.ordinal();
			if(id > 6)
				continue;
			itemCollection.addAll(itemSets[id]);
		}
	}
	
	
	/**
	 * Whether or not <code> a new DungeonExtraTreasure can be constructed with 
	 * {@link #DungeonExtraTreasure(int, int, EnumTreasureRarity...)}. That is to say, if ArmorToolLibrary
	 */
	public static boolean canInstantiate(){
		return Pixelmon.postInitialized();
	}



	public static void initRareItems(){

		for(EnumApricorns apricorn :  EnumApricorns.values()){
			registerRareItem(new ItemStack(apricorn.apricorn()), COMMON, 3, 7);
			registerRareItem(new ItemStack(apricorn.cookedApricorn()), COMMON.average(UNCOMMON), 3, 7);
		}


		//Evolution stones added here. If the stone has not yet been implemented, it's ignored.
		for(EnumEvolutionStone stone : EnumEvolutionStone.values()){
			if(stone.getItem(0) != null)
				registerRareItem(new ItemStack(stone.getItem(0)), UNCOMMON, 0, 1);
		}

		for(EnumPokeballs ballType : EnumPokeballs.values()){
			float baseRarity = EnumTreasureRarity.estimateRarity(ballType);
			int minSize = Math.max(1, ballType.quantityMade-2);
			int maxSize = ballType == MasterBall ? 1 : Math.max(1, ballType.quantityMade+5);
			registerRareItem(new ItemStack(ballType.getItem()), baseRarity *.5F, minSize, maxSize);
			if(ballType.getDisc() != null){ //Then it will also have a Lid.
				registerRareItem(new ItemStack(ballType.getDisc()), baseRarity, minSize, maxSize);
				registerRareItem(new ItemStack(ballType.getLid()), baseRarity*.8F, minSize, maxSize);
			}
		}
		//All armor made of diamond, including mod-added armor added here.
		for(EnumArmorMaterial armorMat : ArmorToolLibrary.ARMOR.keySet()){
			if(armorMat == DIAMOND){
				for(ItemArmor armor : ArmorToolLibrary.ARMOR.get(armorMat)){
					if(armor == null) continue;
					ArrayList<ItemStack> otherStacks = new ArrayList();
					armor.getSubItems(armor.itemID, armor.getCreativeTab(), otherStacks);
					for(ItemStack stack : otherStacks){
						registerRareItem(stack, RARE, 0, 1);
					}
				}
			}
			//chainmail, as well as any other installed mods' armor pieces made of chainmail, OR that don't use a default armor material, and are not made of aluminum, are added here.
			else if(armorMat != Pixelmon.ALUMINIUMARMOR && !ArmorToolLibrary.isStandard(armorMat)){
				for(ItemArmor armor : ArmorToolLibrary.ARMOR.get(armorMat)){
					if(armor == null) continue;
					ArrayList<ItemStack> otherStacks = new ArrayList();
					armor.getSubItems(armor.itemID, armor.getCreativeTab(), otherStacks);
					for(ItemStack stack : otherStacks){
						registerRareItem(stack, SUPER_RARE, 0, 1);
					}
				}
			}
		}

		for(EnumToolMaterial toolMat : ArmorToolLibrary.TOOLS.keySet()){
			// EMERALD is actually diamond. All tools including mod-added tools made of diamond added here.
			if(toolMat == EMERALD){
				for(ItemTool tool : ArmorToolLibrary.TOOLS.get(toolMat)){
					if(tool == null) continue;
					ArrayList<ItemStack> otherStacks = new ArrayList();
					tool.getSubItems(tool.itemID, tool.getCreativeTab(), otherStacks);
					for(ItemStack stack : otherStacks){
						registerRareItem(stack, RARE, 0, 1);
					}
				}
			}
			//any other installed mods' tools that don't use a default tool material, and are not made of aluminum
			else if(toolMat != Pixelmon.ALUMINIUM && !ArmorToolLibrary.isStandard(toolMat)){
				for(ItemTool tool : ArmorToolLibrary.TOOLS.get(toolMat)){
					if(tool == null) continue;
					ArrayList<ItemStack> otherStacks = new ArrayList();
					tool.getSubItems(tool.itemID, tool.getCreativeTab(), otherStacks);
					for(ItemStack stack : otherStacks){
						registerRareItem(stack, SUPER_RARE, 0, 1);
					}
				}
			}
		}
		for(Item tm : PixelmonItemsTMs.TMs){
			registerRareItem(new ItemStack(tm), RARE, 1, 1);
		}
		//the treasure that normally spawns in simple vanilla dungeons.
		for(WeightedRandomChestContent defaultTreasure : WorldGenDungeons.field_111189_a){
			float rarity =  (float) (FunctionHelper.slider(defaultTreasure.itemWeight, 0, 10)*COMMON.chance);
			registerRareItem(defaultTreasure.theItemId, rarity, defaultTreasure.theMinimumChanceToGenerateItem, defaultTreasure.theMaximumChanceToGenerateItem);//Those are 2 long-ass field names.
		}
		registerRareItem(new ItemStack(PixelmonBlocks.fossil), UNCOMMON, 1, 2);
		registerRareItem(new ItemStack(PixelmonItems.superRod), UNCOMMON, 1, 1);
		for(EnumTreasureRarity rar : EnumTreasureRarity.STANDARD_RARITIES){
			System.out.println(rar + "_ITEMS size = " + itemSets[rar.ordinal()].size());
		}
		init = true;
	}

	public static ChancedStack registerRareItem(ItemStack stack, EnumTreasureRarity rarity, int minStack, int maxStack){
		ChancedStack content = new ChancedStack(stack, statRandom, rarity.chance, minStack, maxStack);
		HashSet<ChancedStack> set = itemSets[rarity.ordinal()];
		addRareItem(set, content);
		if(debugRegistration)
			System.out.println(String.format(sayRegistration, stack.getDisplayName(), rarity.chance, rarity));
		return content;
	}

	public static ChancedStack registerRareItem(ItemStack stack, float chance, int minStack, int maxStack){
		ChancedStack content = new ChancedStack(stack, statRandom, chance, minStack, maxStack);
		EnumTreasureRarity rarity = EnumTreasureRarity.round(chance);
		HashSet<ChancedStack> set = itemSets[rarity.ordinal()];
		addRareItem(set, content);
		if(debugRegistration)
			System.out.println(String.format(sayRegistration, stack.getDisplayName(), chance, rarity));
		return content;
	}

	protected static void addRareItem(Set<ChancedStack> set, ChancedStack content){
		set.add(content);
		if(content.object.getItem() instanceof ItemTM)
			tms.add(content);
		allItems.add(content);
	}



	@Override
	protected void genFloor(World world, MysteryDungeonFloor floor,
			Random random, int x, int y, int z, int floorIndex) {
		System.out.println(itemCollection);
		int chestsAmt = RandomHelper.useRandomForNumberBetween(random, minChests, maxChests);
		for(int i = 0; i < chestsAmt; i++){
			int[] chestPoint = floor.randomPoint(random, true);
			ForgeDirection chestDir = WorldHelper.NWSE[random.nextInt(4)];
			int x0 = x+chestPoint[0];
			int z0 = z+chestPoint[1];
			world.setBlock(x0, y, z0, Block.chest.blockID, 0, 2);
			TileEntityChest chest = (TileEntityChest) world.getBlockTileEntity(x0, y, z0);
			float slotFillChance;
			if(this.finalFloorSlotChance == this.firstFloorSlotChance){
				slotFillChance = this.firstFloorSlotChance;
			}
			else{
			float floorValue = (float) FunctionHelper.slider(floorIndex, 0, dungeon.numFloors-1);
			slotFillChance = (float) (dungeon.up ? FunctionHelper.slider(floorValue, this.firstFloorSlotChance, this.finalFloorSlotChance) : FunctionHelper.slider(floorValue, this.finalFloorSlotChance, this.firstFloorSlotChance));
			}
			for(int j = 0; j < chest.getSizeInventory(); j++){
				if(!ChancedWrapper.chosen(slotFillChance, myRandom))
					continue;
				//use separate Random for chest filling = same map seeds don't have the same chest contents
				ChancedStack stack = ChancedWrapper.weightedChoice(itemCollection, myRandom, true);
				//System.out.println();
				//System.out.println("Stack is " + stack);
				if(stack!=null){
					ItemStack newStack = stack.newStack(random);
					//System.out.println("New stack is " + newStack);
					if(newStack != null && newStack.stackSize != 0)
						chest.setInventorySlotContents(j, newStack);
				}

			}
		}
	}

	@Override
	protected void genOthers(World world, Random random, int x, int y, int z) {
		//nuffin

	}

}
