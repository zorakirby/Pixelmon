package pixelmon.battles.attacks.specialAttacks.modifiers;

public enum ModifierType {
	Chance, User;

	public static ModifierType getModifierType(String string) {
		for (ModifierType t : values())
			if (t.toString().equalsIgnoreCase(string))
				return t;
		return null;
	}
}
