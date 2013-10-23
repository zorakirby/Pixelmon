package pixelmon.worldGeneration.mysteryDungeon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
import pixelmon.util.ChancedCollection;
import pixelmon.util.ChancedStack;
import pixelmon.util.ChancedWrapper;
import pixelmon.util.CommonHelper;
import pixelmon.util.FunctionHelper;
import pixelmon.util.testing.Testomatic;


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
	protected static HashSet<ChancedWrapper>
	commonItems = new HashSet(),
	uncommonItems = new HashSet(),
	semirareItems = new HashSet(),
	rareItems = new HashSet(),
	veryrareItems = new HashSet(),
	superrareItems = new HashSet(),
	ultrarareItems = new HashSet(),
	allItems = new HashSet();


	private static HashSet<ChancedWrapper>[] itemSets = new HashSet[]{
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

	public final HashSet<ChancedWrapper> itemCollection = new HashSet();
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
		if(init)
			throw new IllegalStateException("DungeonExtraTreasure was already initialized!");
		
		Collection<ChancedStack> apris = new ArrayList(), cookApris = new ArrayList();
		for(EnumApricorns apricorn :  EnumApricorns.values()){
			ChancedStack stack = new ChancedStack(new ItemStack(apricorn.apricorn()), statRandom, COMMON.chance, 3, 7);
			apris.add(stack);
			stack = new ChancedStack(new ItemStack(apricorn.cookedApricorn()), statRandom, COMMON.chance, 3, 7);
			cookApris.add(stack);
		}


		//Evolution stones added here. If the stone has not yet been implemented, it's ignored.
		
		Collection<ChancedStack> stones = new ArrayList();
		for(EnumEvolutionStone stone : EnumEvolutionStone.values()){
			if(stone.getItem(0) != null){
				ChancedStack stack = new ChancedStack(new ItemStack(stone.getItem(0)), statRandom, ALWAYS.chance, 0, 1);
				stones.add(stack);
			}
		}

		Collection<ChancedStack> balls = new ArrayList(), lids = new ArrayList(), discs = new ArrayList();
		for(EnumPokeballs ballType : EnumPokeballs.values()){
			float baseRarity = EnumTreasureRarity.estimateRarity(ballType);
			int minSize = Math.max(1, ballType.quantityMade-2);
			int maxSize = ballType == MasterBall ? 1 : Math.max(1, ballType.quantityMade+5);
			ItemStack stack = new ItemStack(ballType.getItem());
			ChancedStack chancedStack = new ChancedStack(stack, statRandom, baseRarity, minSize, maxSize);
			balls.add(chancedStack);
			if(ballType.getDisc()!= null){
				stack = new ItemStack(ballType.getDisc());
				chancedStack = new ChancedStack(stack, statRandom, baseRarity, minSize, maxSize);
				discs.add(chancedStack);
				stack = new ItemStack(ballType.getLid());
				chancedStack = new ChancedStack(stack, statRandom, baseRarity, minSize, maxSize);
				lids.add(chancedStack);				
			}
		}
		//All armor made of diamond, including mod-added armor added here.
		Collection<ChancedStack> diamondArmors = new ArrayList(), modArmors = new ArrayList();
		for(EnumArmorMaterial armorMat : ArmorToolLibrary.ARMOR.keySet()){
			if(armorMat == DIAMOND){
				for(ItemArmor armor : ArmorToolLibrary.ARMOR.get(armorMat)){
					if(armor == null) continue;
					ArrayList<ItemStack> otherStacks = new ArrayList();
					armor.getSubItems(armor.itemID, armor.getCreativeTab(), otherStacks);
					for(ItemStack stack : otherStacks){
						ChancedStack cstack = new ChancedStack(stack, statRandom, COMMON.chance, 0, 1);
						diamondArmors.add(cstack);
					}
				}
			}
			
			//chainmail, as well as any other installed mods' armor pieces made of chainmail, OR that don't use a default armor material, and are not made of aluminum, or old running armor are added here.
			else if(armorMat != Pixelmon.ALUMINIUMARMOR && armorMat != Pixelmon.OLDRUNNINGARMOR && !ArmorToolLibrary.isStandard(armorMat)){
				for(ItemArmor armor : ArmorToolLibrary.ARMOR.get(armorMat)){
					if(armor == null) continue;
					ArrayList<ItemStack> otherStacks = new ArrayList();
					armor.getSubItems(armor.itemID, armor.getCreativeTab(), otherStacks);
					for(ItemStack stack : otherStacks){
						ChancedStack cstack = new ChancedStack(stack, statRandom, UNCOMMON.average(RARE), 0, 1);
						modArmors.add(cstack);
					}
				}
			}
		}

		Collection<ChancedStack> diamondTools = new ArrayList(), modTools = new ArrayList();
		for(EnumToolMaterial toolMat : ArmorToolLibrary.TOOLS.keySet()){
			// EMERALD is actually diamond. All tools including mod-added tools made of diamond added here.
			if(toolMat == EMERALD){
				for(ItemTool tool : ArmorToolLibrary.TOOLS.get(toolMat)){
					if(tool == null) continue;
					ArrayList<ItemStack> otherStacks = new ArrayList();
					tool.getSubItems(tool.itemID, tool.getCreativeTab(), otherStacks);
					for(ItemStack stack : otherStacks){
						ChancedStack cstack = new ChancedStack(stack, statRandom, UNCOMMON.chance, 0, 1);
						diamondTools.add(cstack);
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
						ChancedStack cstack = new ChancedStack(stack, statRandom, UNCOMMON.average(RARE), 0, 1);
						modTools.add(cstack);
					}
				}
			}
		}
		
		Collection<ChancedStack> tms = new ArrayList();
		for(Item tm : PixelmonItemsTMs.TMs){
			ChancedStack cstack = new ChancedStack(new ItemStack(tm), statRandom, UNCOMMON.average(COMMON), 1, 1);
			tms.add(cstack);
		}
		
		//the treasure that normally spawns in simple vanilla dungeons.
		Collection<ChancedStack> vanillaStuff = new ArrayList();
		for(WeightedRandomChestContent defaultTreasure : WorldGenDungeons.field_111189_a){
			float rarity =  (float) (FunctionHelper.slider(defaultTreasure.itemWeight, 0, 10)*COMMON.chance);
			ChancedStack cstack = new ChancedStack(defaultTreasure.theItemId, statRandom, rarity, defaultTreasure.theMinimumChanceToGenerateItem, defaultTreasure.theMaximumChanceToGenerateItem);
			vanillaStuff.add(cstack);
		}
		
		registerRareItem(new ItemStack(PixelmonBlocks.fossil), RARE.chance, 0, 2);
		registerRareItem(new ItemStack(PixelmonItems.superRod), RARE.chance, 0, 1);
		
		registerRareCategory(apris, COMMON.chance);
		registerRareCategory(cookApris, COMMON.average(COMMON.average(UNCOMMON)));
		registerRareCategory(discs, COMMON.average(UNCOMMON));
		registerRareCategory(lids, UNCOMMON.chance);
		registerRareCategory(balls, UNCOMMON.average(UNLIKELY));

		
		registerRareCategory(stones, UNLIKELY.chance);
		
		registerRareCategory(tms, RARE.average(SEMI_RARE));
		
		registerRareCategory(diamondArmors, RARE.average(VERY_RARE));
		registerRareCategory(diamondTools, RARE.average(VERY_RARE));
		
		registerRareCategory(vanillaStuff, COMMON.chance);
		
		if(!modTools.isEmpty())
			registerRareCategory(modTools, VERY_RARE.chance);
		if(!modArmors.isEmpty())
			registerRareCategory(modArmors, VERY_RARE.chance);
		
		init = true;
		System.out.println("SIZZEEEE 0 = " + allItems.size());
		Testomatic.debugRareTreasure();
	}

	public static ChancedStack registerRareItem(ItemStack stack, EnumTreasureRarity rarity, int minStack, int maxStack){
		ChancedStack content = new ChancedStack(stack, statRandom, rarity.chance, minStack, maxStack);
		HashSet<ChancedWrapper> set = itemSets[rarity.ordinal()];
		addRareItem(set, content);
		if(debugRegistration)
			System.out.println(String.format(sayRegistration, stack.getDisplayName(), rarity.chance, rarity));
		return content;
	}

	public static ChancedStack registerRareItem(ItemStack stack, float chance, int minStack, int maxStack){
		ChancedStack content = new ChancedStack(stack, statRandom, chance, minStack, maxStack);
		EnumTreasureRarity rarity = EnumTreasureRarity.round(chance);
		HashSet<ChancedWrapper> set = itemSets[rarity.ordinal()];
		addRareItem(set, content);
		if(debugRegistration)
			System.out.println(String.format(sayRegistration, stack.getDisplayName(), chance, rarity));
		return content;
	}
	
	
	public static ChancedWrapper registerRareCategory(Collection<ChancedStack> itemCategory, float chance){
		ChancedCollection<ChancedStack> content = new ChancedCollection(itemCategory, chance);
		EnumTreasureRarity rarity = EnumTreasureRarity.round(chance);
		HashSet<ChancedWrapper> set = itemSets[rarity.ordinal()];
		set.add(content);
		allItems.add(content);
		return content;
	}

	protected static void addRareItem(Set<ChancedWrapper> set, ChancedStack content){
		set.add(content);
		allItems.add(content);
	}
	
	public static TreeSet<ChancedWrapper> getEverything(){
		return new TreeSet(allItems);
	}



	@Override
	protected void genFloor(World world, MysteryDungeonFloor floor,
			Random random, int x, int y, int z, int floorIndex) {
		System.out.println(itemCollection);
		y++;
		int chestsAmt = RandomHelper.useRandomForNumberBetween(random, minChests, maxChests);
		for(int i = 0; i < chestsAmt; i++){
			int[] chestPoint = floor.randomPoint(random, true);
			ForgeDirection chestDir = WorldHelper.NWSE[random.nextInt(4)];
			int x0 = x+chestPoint[0];
			int z0 = z+chestPoint[1];
			world.setBlock(x0, y, z0, Block.chest.blockID, 0, 2);
			TileEntityChest chest = (TileEntityChest) world.getBlockTileEntity(x0, y, z0);
			float slotFillChance;
			if(this.dungeon == null)
				throw new IllegalStateException("WHERE THE FUCK IS MY MYSTERY DUNGEON OBJECT?!?!?!?");
			if(this.finalFloorSlotChance == this.firstFloorSlotChance){
				slotFillChance = this.firstFloorSlotChance;
			}
			else{
			float floorValue = (float) FunctionHelper.slider(floorIndex, 0, dungeon.numFloors-1);
			slotFillChance = (float) (dungeon.up ? FunctionHelper.inverseSlider(floorValue, this.firstFloorSlotChance, this.finalFloorSlotChance) : FunctionHelper.slider(floorValue, this.finalFloorSlotChance, this.firstFloorSlotChance));
			}
			for(int j = 0; j < chest.getSizeInventory(); j++){
				if(!ChancedWrapper.chosen(slotFillChance, myRandom))
					continue;
				//use separate Random for chest filling = same map seeds don't have the same chest contents
				ChancedWrapper stack = ChancedWrapper.weightedChoice(itemCollection, myRandom, true);
				//System.out.println();
				//System.out.println("Stack is " + stack);
				if(stack!=null){
					ItemStack newStack = null;
					if(stack instanceof ChancedStack){
					newStack = ((ChancedStack)stack).newStack(random);
					}
					else if (stack instanceof ChancedCollection){
						Collection<ChancedStack> stacks = (Collection<ChancedStack>) stack.object;
						ChancedStack cStack = ChancedWrapper.weightedChoice(stacks, myRandom, true);
						if(cStack!=null)
							newStack = cStack.newStack(random);
					}
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
