package pixelmon.items;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * 
 * IF THE ITEM IS TO BE HELD BY A PIXELMON, USE THIS AND EDIT WHAT YOU NEED
 * 
 * @author Gerald -xkyouchoux-
 *
 */

public final class ItemHeld extends PixelmonItem {
	
	public ItemHeld(int id)
	{
		super(id);
	}
	
	public int getIconFromDamage(int damage)
	{
		return iconIndex;
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return getItemName() + "." + item.getItemDamage();
	}
}
