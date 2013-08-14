package pixelmon.enums;

public enum EnumTrainers {
	BugCatcher,
	BugManiac,
	FireBreather,
	Fisherman,
	Ornithologist,
	RichBoy,
	Swimmer,
	Youngster;
	

	
	public static boolean has(String creatureName) {
		for (EnumTrainers trainer : values())
			if (trainer.toString().equalsIgnoreCase(creatureName))
				return true;
		return false;
	}
}
