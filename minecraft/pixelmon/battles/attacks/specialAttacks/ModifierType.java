package pixelmon.battles.attacks.specialAttacks;

public enum ModifierType {
	Chance, User, Repeat;

	public static ModifierType getModifierType(String string) {
		for (ModifierType t : values())
			if (t.toString().equalsIgnoreCase(string))
				return t;
		return null;
	}
}
