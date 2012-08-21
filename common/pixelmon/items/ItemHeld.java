package pixelmon.items;

import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumHeldItems;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * 
 * IF THE ITEM IS TO BE HELD BY A PIXELMON, USE THIS AND EDIT WHAT YOU NEED
 * 
 * @author Gerald -xkyouchoux-
 *
 */

public abstract class ItemHeld extends PixelmonItem 
{
	private EnumHeldItems heldItemType;
	private boolean effectsBattles;
	
	public ItemHeld(int id, EnumHeldItems heldItemType)
	{
		super(id);
		this.heldItemType = heldItemType;
		effectsBattles = heldItemType.getBattleModifier();
	}
	
	public EnumHeldItems getHeldItemType(){
		return heldItemType;
	}
	
	public boolean doesEffectBattles(){
		return effectsBattles;
	}
	
	public static void useItem(PixelmonEntityHelper helper, PixelmonEntityHelper helper1, EnumHeldItems item)
	{
		if(helper.getHeldItem() != null && helper.getHeldItem().getItem() != null && helper.getHeldItem().getItem() instanceof ItemHeld)
		{
			if(((ItemHeld)helper.getHeldItem().getItem()).heldItemType == item && ((ItemHeld)helper.getHeldItem().getItem()).effectEntity(helper))
			{
				helper.setHeldItem(null);
			}
		}
	}
	
	public static boolean isItemOfType(ItemStack item, EnumHeldItems type)
	{
		if(item != null && item.getItem() == null && !(item.getItem() instanceof ItemHeld))
		{
			return false;
		}
		else return ((ItemHeld)item.getItem()).heldItemType == type;
	}
	
	public boolean effectEntity(PixelmonEntityHelper helper1)
	{
		return false;
	}
	
	public static void useBattleItems(PixelmonEntityHelper helper1, PixelmonEntityHelper helper2)
	{
		for(EnumHeldItems item : EnumHeldItems.values())
		{
			if(!item.getBattleModifier())
			{
				continue;
			}
			useItem(helper1, helper2, item);
		}
	}
}
