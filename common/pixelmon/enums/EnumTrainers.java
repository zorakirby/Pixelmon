package pixelmon.enums;

public enum EnumTrainers {
	Youngster, BugCatcher;

	public static boolean has(String creatureName) {
		for (EnumTrainers trainer : values())
			if (trainer.toString().equalsIgnoreCase(creatureName))
				return true;
		return false;
	}
}
