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

public class ItemHeld extends PixelmonItem 
{
	private EnumHeldItems heldItemType;
	private boolean effectsBattles = false;
	
	public ItemHeld(int id, EnumHeldItems heldItemType)
	{
		super(id);
		this.heldItemType = heldItemType;
	}
	
	public EnumHeldItems getHeldItemType(){
		return heldItemType;
	}
	
	public boolean doesEffectBattles(){
		return effectsBattles;
	}
	
	public void ApplyEffect(PixelmonEntityHelper helper1)
	{
		heldItemType.applyEffects(helper1);
	}
	
	public static boolean isItemOfType(PixelmonEntityHelper helper, EnumHeldItems item)
	{
		if(helper.getHeldItem() == null || !(helper.getHeldItem().getItem() instanceof ItemHeld))
		{
			return false;
		}
		else return ((ItemHeld)helper.getHeldItem().getItem()).heldItemType == item;
	}
}
