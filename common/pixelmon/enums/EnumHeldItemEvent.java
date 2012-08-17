package pixelmon.enums;

import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.ItemStack;

public enum EnumHeldItemEvent
{
	nearDeath(),
	levelUp(),
	gainedXP();
	
	private ItemStack[] itemsAccepted;
	
	private EnumHeldItemEvent(ItemStack... var1)
	{
		itemsAccepted = var1;
	}
	
	public boolean isItemAccepted(ItemStack var1)
	{
		if(var1 == null || var1.getItem() == null)
		{
			return false;
		}
		for(ItemStack var2 : itemsAccepted)
		{
			if(var2.itemID == var1.itemID && var2.getItemDamage() == var1.getItemDamage())
			{
				return true;
			}
		}
		return false;
	}
	
	private static boolean handleEvent(EnumHeldItemEvent var1, ItemStack var2, PixelmonEntityHelper var3, Object... var4)
	{
		return false;
	}
}
