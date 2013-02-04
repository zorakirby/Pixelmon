package pixelmon.enums.heldItems;

import pixelmon.battles.attacks.attackEffects.StatsEffectType;

public enum EnumChoiceItems {
	ChoiceBand(StatsEffectType.Attack),
	ChoiceScarf(StatsEffectType.Speed),
	ChoiceSpecs(StatsEffectType.SpecialAttack);
	
	public StatsEffectType effectType;
	private EnumChoiceItems(StatsEffectType type){
		this.effectType = type;
	}
}
