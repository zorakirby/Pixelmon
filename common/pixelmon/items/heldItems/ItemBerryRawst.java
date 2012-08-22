package pixelmon.items.heldItems;

import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemBerryRawst extends ItemHeld {

	public ItemBerryRawst(int id) {
		super(id, EnumHeldItems.rawst);
		SetUsableInBattle(true);
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
