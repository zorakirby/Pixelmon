package pixelmon.battles.attacks.specialAttacks;

public enum SpecialAttackType {
	Acrobatics,
	Acupressure,
	Curse,
	EchoedVoice,
	Facade,
	Frustration,
	Guillotine,
	GyroBall,
	HiddenPower,
	HornDrill,
	JumpKick,
	Magnitude,
	NightShade,
	Payday,
	PetalDance,
	PsychUp,
	Psywave,
	Return,
	Reversal,
	SmackDown,
	Substitute,
	Venoshock;

	public static SpecialAttackType getSpecialAttackType(String string) {
		if (string.equalsIgnoreCase("Acrobatics")) return SpecialAttackType.Acrobatics;
		if (string.equalsIgnoreCase("Acupressure")) return SpecialAttackType.Acupressure;
		if (string.equalsIgnoreCase("Curse")) return SpecialAttackType.Curse;
		if (string.equalsIgnoreCase("EchoedVoice")) return SpecialAttackType.EchoedVoice;
		if (string.equalsIgnoreCase("Facade")) return SpecialAttackType.Facade;
		if (string.equalsIgnoreCase("Frustration")) return SpecialAttackType.Frustration;
		if (string.equalsIgnoreCase("Guillotine")) return SpecialAttackType.Guillotine;
		if (string.equalsIgnoreCase("GyroBall")) return SpecialAttackType.GyroBall;
		if (string.equalsIgnoreCase("HiddenPower")) return SpecialAttackType.HiddenPower;
		if (string.equalsIgnoreCase("HornDrill")) return SpecialAttackType.HornDrill;
		if (string.equalsIgnoreCase("JumpKick")) return SpecialAttackType.JumpKick;
		if (string.equalsIgnoreCase("Magnitude")) return SpecialAttackType.Magnitude;
		if (string.equalsIgnoreCase("NightShade")) return SpecialAttackType.NightShade;
		if (string.equalsIgnoreCase("Payday")) return SpecialAttackType.Payday;
		if (string.equalsIgnoreCase("PetalDance")) return SpecialAttackType.PetalDance;
		if (string.equalsIgnoreCase("PsychUp")) return SpecialAttackType.PsychUp;
		if (string.equalsIgnoreCase("Psywave")) return SpecialAttackType.Psywave;
		if (string.equalsIgnoreCase("Return")) return SpecialAttackType.Return;
		if (string.equalsIgnoreCase("Reversal")) return SpecialAttackType.Reversal;
		if (string.equalsIgnoreCase("SmackDown")) return SpecialAttackType.SmackDown;
		if (string.equalsIgnoreCase("Substitute")) return SpecialAttackType.Substitute;
		if (string.equalsIgnoreCase("Venoshock")) return SpecialAttackType.Venoshock;
		return null;
	}

	public static boolean isSpecialAttackType(String string) {
		if (string.equalsIgnoreCase("Acrobatics")) return true;
		if (string.equalsIgnoreCase("Acupressure")) return true;
		if (string.equalsIgnoreCase("Curse")) return true;
		if (string.equalsIgnoreCase("EchoedVoice")) return true;
		if (string.equalsIgnoreCase("Facade")) return true;
		if (string.equalsIgnoreCase("Frustration")) return true;
		if (string.equalsIgnoreCase("Guillotine")) return true;
		if (string.equalsIgnoreCase("GyroBall")) return true;
		if (string.equalsIgnoreCase("HiddenPower")) return true;
		if (string.equalsIgnoreCase("HornDrill")) return true;
		if (string.equalsIgnoreCase("JumpKick")) return true;
		if (string.equalsIgnoreCase("NightShade")) return true;
		if (string.equalsIgnoreCase("Payday")) return true;
		if (string.equalsIgnoreCase("PetalDance")) return true;
		if (string.equalsIgnoreCase("PsychUp")) return true;
		if (string.equalsIgnoreCase("Psywave")) return true;
		if (string.equalsIgnoreCase("Return")) return true;
		if (string.equalsIgnoreCase("Reversal")) return true;
		if (string.equalsIgnoreCase("SmackDown")) return true;
		if (string.equalsIgnoreCase("Substitute")) return true;
		if (string.equalsIgnoreCase("Venoshock")) return true;
		return false;
	}
}
