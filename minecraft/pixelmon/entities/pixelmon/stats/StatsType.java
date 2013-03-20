package pixelmon.entities.pixelmon.stats;

public enum StatsType {
	None, HP, Attack, Defence, SpecialAttack, SpecialDefence, Speed, Accuracy, Evasion;

	public static boolean isStatsEffect(String effectTypeString) {
		for (StatsType t : values()) {
			if (effectTypeString.equalsIgnoreCase(t.toString()))
				return true;
		}
		return false;
	}

	public static StatsType getStatsEffect(String effectTypeString) {
		for (StatsType t : values()) {
			if (effectTypeString.equalsIgnoreCase(t.toString()))
				return t;
		}
		return null;
	}
}
