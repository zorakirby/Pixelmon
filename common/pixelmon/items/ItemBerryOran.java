package pixelmon.items;

import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumHeldItems;

public class ItemBerryOran extends ItemHeld {

	public ItemBerryOran(int id) 
	{
		super(id, EnumHeldItems.oran);
	}
	
	public boolean effectEntity(PixelmonEntityHelper helper)
	{
		if(helper.getHealth() < (int)((float)helper.getMaxHealth() / .3f))
		{
			helper.setHealth(helper.getHealth() + 10);
			return true;
		}
		return false;
	}

}
