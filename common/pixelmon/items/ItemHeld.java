package pixelmon.items;

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

public abstract class ItemHeld extends PixelmonItem {
	private EnumHeldItems heldItemType;
	private boolean effectsBattles = false;
	
	public ItemHeld(EnumHeldItems heldItemType)
	{
		super(0);
		this.heldItemType = heldItemType;
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return getItemName() + "." + item.getItemDamage();
	}
	
	public EnumHeldItems getHeldItemType(){
		return heldItemType;
	}
	
	public boolean doesEffectBattles(){
		return effectsBattles;
	}
	
	public abstract void ApplyEffect();
}
