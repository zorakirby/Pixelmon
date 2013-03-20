package pixelmon.enums.heldItems;

import pixelmon.entities.pixelmon.stats.StatsType;


public enum EnumChoiceItems {
	ChoiceBand(StatsType.Attack),
	ChoiceScarf(StatsType.Speed),
	ChoiceSpecs(StatsType.SpecialAttack);
	
	public StatsType effectType;
	private EnumChoiceItems(StatsType type){
		this.effectType = type;
	}
}
