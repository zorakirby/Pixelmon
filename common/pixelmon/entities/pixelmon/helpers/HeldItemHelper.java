package pixelmon.entities.pixelmon.helpers;

import pixelmon.config.PixelmonItems;
import net.minecraft.src.ItemStack;

public class HeldItemHelper {
	
	public static final ItemStack expShare = new ItemStack(PixelmonItems.heldItem, 1, 0);
	public static final ItemStack luckyEgg = new ItemStack(PixelmonItems.heldItem, 1, 1);
	
	public boolean canHoldItem(ItemStack item)
	{
		if(item .itemID != PixelmonItems.heldItemsID)
		{
			return false;
		}
		else return true;
	}
	
	public static boolean isItemLuckyEgg(PixelmonEntityHelper p)
	{
		return p.getHeldItem() != null && p.getHeldItem().isItemEqual(luckyEgg);
	}
	
	public static boolean isItemXPShare(PixelmonEntityHelper p)
	{
		return p.getHeldItem() != null && p.getHeldItem().isItemEqual(expShare);
	}
	
	public static ItemStack getItemForPixelmon(PixelmonEntityHelper p)
	{
		return null;
	}
}
