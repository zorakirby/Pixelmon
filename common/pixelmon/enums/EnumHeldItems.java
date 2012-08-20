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
	oran, rawst, leppa;
	
	private boolean effectsBattle;
	
	private EnumHeldItems()
	{
		this(true);
	}
	
	private EnumHeldItems(boolean var1)
	{
		effectsBattle = var1;
	}
	
	public boolean getBattleModifier()
	{
		return effectsBattle;
	}
}
