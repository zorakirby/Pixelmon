package pixelmon.enums;

import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.config.PixelmonItems;
import net.minecraft.src.Item;

public enum EnumStatusAilmentHealers {
	FullRestore(66, "all", true, "fullrestore", 64), Antidote(82, "Poison", false, "antidote", 80), ParlyzHeal(98, "Paralysis", false, "parlyzheal", 96), Awakening(114, "Sleep", false, "awakening",
			112), BurnHeal(130, "Burn", false, "burn", 128), IceHeal(146, "Freeze", false, "iceheal", 144), FullHeal(162, "all", false, "fullheal", 160);

	private EnumStatusAilmentHealers(int index, String status, boolean healsHP, String filenamePrefix, int iconIndex) {
		StatusEffectType[] statuses = {};
		if (status.equalsIgnoreCase("all")) {
			statuses = new StatusEffectType[] { StatusEffectType.Burn, StatusEffectType.Confusion, StatusEffectType.Freeze, StatusEffectType.Paralysis, StatusEffectType.Poison,
					StatusEffectType.PoisonBadly, StatusEffectType.Sleep };
		} else if (status.equalsIgnoreCase("Poison")) {
			statuses = new StatusEffectType[] { StatusEffectType.Poison, StatusEffectType.PoisonBadly };
		} else {
			statuses = new StatusEffectType[] { StatusEffectType.getStatusEffect(status) };
		}
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndex;
		this.statuses = statuses;
		this.healsHP = healsHP;
	}

	private StatusEffectType[] statuses;
	private boolean healsHP;
	private int index;
	private String filenamePrefix;
	private int iconIndex;

	public StatusEffectType[] statusesHealed() {
		return statuses;
	}

	public boolean healsHP() {
		return healsHP;
	}

	public int getIndex() {
		return index;
	}

	public int getIconIndex() {
		return iconIndex;
	}

	public Item getItem() {
		if (index == 66)
			return PixelmonItems.fullRestore;
		if (index == 82)
			return PixelmonItems.antidote;
		if (index == 98)
			return PixelmonItems.parlyzHeal;
		if (index == 114)
			return PixelmonItems.awakening;
		if (index == 130)
			return PixelmonItems.burnHeal;
		if (index == 146)
			return PixelmonItems.iceHeal;
		if (index == 162)
			return PixelmonItems.fullHeal;
		return PixelmonItems.potion;
	}

	public String getTexture() {
		return filenamePrefix + ".png";
	}

	public static EnumStatusAilmentHealers getFromIndex(int index) {
		if (index == 66)
			return EnumStatusAilmentHealers.FullRestore;
		if (index == 82)
			return EnumStatusAilmentHealers.Antidote;
		if (index == 98)
			return EnumStatusAilmentHealers.ParlyzHeal;
		if (index == 114)
			return EnumStatusAilmentHealers.Awakening;
		if (index == 130)
			return EnumStatusAilmentHealers.BurnHeal;
		if (index == 146)
			return EnumStatusAilmentHealers.IceHeal;
		if (index == 162)
			return EnumStatusAilmentHealers.FullHeal;
		else
			return EnumStatusAilmentHealers.FullRestore;
	}
}