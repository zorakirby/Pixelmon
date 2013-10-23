package pixelmon.util;

import java.util.Random;

import pixelmon.RandomHelper;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ChancedStack extends ChancedWrapper<ItemStack>{

	public int minItems, maxItems;
	
	/**
	 * @see ChancedWrapper#ChancedWrapper(Object, float)
	 * @param stack - The associated ItemStack. The Stack's 
	 * {@link ItemStack#stackSize stackSize} is unimportant. Copies of this 
	 * stack made with {@link #newStack(Random)} will also copy this stack's
	 * {@link ItemStack#stackTagCompound stackTagCompound}.
	 * @param minItems - The minimum size of the ItemStack resulting from
	 * {@link #newStack(Random)}. If {@link #newStack(Random)} picks 0 for the
	 * size, the result will be null.
	 * @param maxItems - The maximum size of the ItemStack resulting from
	 * {@link #newStack(Random)}.
	 */
	public ChancedStack(ItemStack stack, float chance, int minItems, int maxItems){
		super(stack, chance);
		setMinMaxItems(minItems, maxItems);
	}
	
	/**
	 * Constructs a new {@code ChancedStack} in the same way as 
	 * {@link #ChancedStack(ItemStack, float, int, int)}, except the super-constructor
	 * {@link ChancedWrapper#ChancedWrapper(Object, Random, float)} is used instead.
	 */
	public ChancedStack(ItemStack stack, Random random, float chance, int minItems, int maxItems){
		super(stack, random, chance);
		setMinMaxItems(minItems, maxItems);
	}
	
	public void setMinMaxItems(int min, int max){
		this.minItems = min;
		this.maxItems = max;
	}
	
	/**
	 * @return a copy of {@link ChancedWrapper#object this ChancedStack's ItemStack},
	 * with a size ranging from {@code minItems} to {@code maxItems}. However, if
	 * the randomly chosen size is 0, returns {@code null} instead.
	 */
	public ItemStack newStack(Random random){
		int size = RandomHelper.useRandomForNumberBetween(random, minItems, maxItems);
		return size == 0 ? null : this.object.splitStack(size);
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName() + "(chance:" + (maxChance-minChance) + " item:" + object.getDisplayName() + " minItems:" + minItems + " maxItems:" + maxItems + ")";
	}
	
	@Override
	public String describeObject(){
		return object.getDisplayName();
	}

}
