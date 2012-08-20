package pixelmon.enums;

import net.minecraft.src.ItemStack;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.items.ItemHeld;

public enum EnumHeldItems {
	expShare, luckyEgg,
	oran(true), rawst(true), leppa(true);
	
	private boolean consume;
	
	private EnumHeldItems()
	{
		this(false);
	}
	
	private EnumHeldItems(boolean var1)
	{
		consume = var1;
	}
	
	public void applyEffects(PixelmonEntityHelper helper)
	{
		if(this == oran && ItemHeld.isItemOfType(helper, oran))
		{
			if(helper.getHealth() < (int)((float)helper.getMaxHealth() / .3f))
			{
				helper.setHealth(helper.getHealth() + 10);
				helper.setHeldItem(null);
			}
		}
		if(this == rawst && ItemHeld.isItemOfType(helper, rawst))
		{
			for(int i = 0; i < helper.status.size(); i++)
			{
				StatusEffectBase base = helper.status.get(i);
				if(base.type == StatusEffectType.Burn)
				{
					helper.status.remove(i);
					helper.setHeldItem(null);
					break;
				}
			}
		}
		if(this == leppa && ItemHeld.isItemOfType(helper, leppa))
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
					break;
				}
			}
		}
	}
}
