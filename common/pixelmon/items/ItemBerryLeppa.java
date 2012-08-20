package pixelmon.items;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumHeldItems;

public class ItemBerryLeppa extends ItemHeld {

	public ItemBerryLeppa(int id) {
		super(id, EnumHeldItems.leppa);
		// TODO Auto-generated constructor stub
	}
	
	public boolean effectEntity(PixelmonEntityHelper helper)
	{
		for(Attack move : helper.moveset)
		{
			if(move == null)
			{
				continue;
			}
			if(move.pp < move.ppmax - 10)
			{
				move.pp += 10;
				helper.setHeldItem(null);
				return true;
			}
		}
		return false;
	}

}
