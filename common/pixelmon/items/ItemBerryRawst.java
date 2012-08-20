package pixelmon.items;

import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumHeldItems;

public class ItemBerryRawst extends ItemHeld {

	public ItemBerryRawst(int id) {
		super(id, EnumHeldItems.rawst);
		// TODO Auto-generated constructor stub
	}
	
	public boolean effectEntity(PixelmonEntityHelper helper)
	{
		for(int i = 0; i < helper.status.size(); i++)
		{
			StatusEffectBase base = helper.status.get(i);
			if(base.type == StatusEffectType.Burn)
			{
				helper.status.remove(i);
				return true;
			}
		}
		return false;
	}

}
