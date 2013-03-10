package pixelmon.enums.heldItems;

import pixelmon.entities.pixelmon.stats.StatsType;

public enum EnumEvAdjustingItems {
	MachoBrace(StatsType.None, 3), PowerWeight(StatsType.HP, 4), PowerBracer(StatsType.Attack, 5), PowerBelt(StatsType.Defence, 6), PowerLens(StatsType.SpecialAttack, 7),
	PowerBand(StatsType.SpecialDefence, 8), PowerAnklet(StatsType.Speed, 9);
	
	public StatsType statAffected;
	public int iconRow;
	private EnumEvAdjustingItems(StatsType statAffected, int iconRow){
		this.statAffected = statAffected;
		this.iconRow = iconRow;
	}
}

