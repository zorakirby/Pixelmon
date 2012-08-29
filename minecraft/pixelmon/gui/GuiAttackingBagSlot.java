package pixelmon.gui;

import net.minecraft.src.ItemStack;

public class GuiAttackingBagSlot {
	
	private int count;
	private ItemStack item;
	private String name;
	
	public GuiAttackingBagSlot(ItemStack i){
		item = i;
		name = i.getItem().getItemDisplayName(i);
		count = 0;
	}
	
	public int getCount(){
		return count;
	}
	
	public String getName(){
		return name;
	}
	
	public ItemStack getItem(){
		return item;
	}
	
	public void addItem(){
		count++;
	}

}
