package pixelmon.enums;

import net.minecraft.item.Item;
import pixelmon.battles.status.StatusType;
import pixelmon.config.PixelmonItems;

public enum EnumStatusAilmentHealers {
	FullRestore(66, "all", true, "fullrestore", 64), Antidote(82, "Poison", false, "antidote", 80), ParlyzHeal(98, "Paralysis", false, "parlyzheal", 96), Awakening(114, "Sleep", false, "awakening",
			112), BurnHeal(130, "Burn", false, "burn", 128), IceHeal(146, "Freeze", false, "iceheal", 144), FullHeal(162, "all", false, "fullheal", 160);

	private EnumStatusAilmentHealers(int index, String status, boolean healsHP, String filenamePrefix, int iconIndex) {
		StatusType[] statuses = {};
		if (status.equalsIgnoreCase("all")) {
			statuses = new StatusType[] { StatusType.Burn, StatusType.Confusion, StatusType.Freeze, StatusType.Paralysis, StatusType.Poison,
					StatusType.PoisonBadly, StatusType.Sleep };
		} else if (status.equalsIgnoreCase("Poison")) {
			statuses = new StatusType[] { StatusType.Poison, StatusType.PoisonBadly };
		} else {
			statuses = new StatusType[] { StatusType.getStatusEffect(status) };
		}
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndex;
		this.statuses = statuses;
		this.healsHP = healsHP;
	}

	private StatusType[] statuses;
	private boolean healsHP;
	private int index;
	private String filenamePrefix;
	private int iconIndex;

	public StatusType[] statusesHealed() {
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