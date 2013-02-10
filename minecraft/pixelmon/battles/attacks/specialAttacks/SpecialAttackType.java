package pixelmon.battles.attacks.specialAttacks;

public enum SpecialAttackType {
	Acrobatics,
	Acupressure,
	Curse,
	EchoedVoice,
	Eruption,
	Facade,
	Frustration,
	Guillotine,
	GyroBall,
	HeavySlam,
	HiddenPower,
	HornDrill,
	JumpKick,
	Magnitude,
	MilkDrink,
	NightShade,
	PainSplit,
	Payday,
	PetalDance,
	Punishment,
	PsychUp,
	Psywave,
	Restore,
	Return,
	Reversal,
	SeismicToss,
	SmackDown,
	Substitute,
	Venoshock;

	public static SpecialAttackType getSpecialAttackType(String string) {
		if (string.equalsIgnoreCase("Acrobatics")) return SpecialAttackType.Acrobatics;
		if (string.equalsIgnoreCase("Acupressure")) return SpecialAttackType.Acupressure;
		if (string.equalsIgnoreCase("Curse")) return SpecialAttackType.Curse;
		if (string.equalsIgnoreCase("EchoedVoice")) return SpecialAttackType.EchoedVoice;
		if (string.equalsIgnoreCase("Eruption")) return SpecialAttackType.Eruption;
		if (string.equalsIgnoreCase("Facade")) return SpecialAttackType.Facade;
		if (string.equalsIgnoreCase("Frustration")) return SpecialAttackType.Frustration;
		if (string.equalsIgnoreCase("Guillotine")) return SpecialAttackType.Guillotine;
		if (string.equalsIgnoreCase("GyroBall")) return SpecialAttackType.GyroBall;
		if (string.equalsIgnoreCase("HeavySlam")) return SpecialAttackType.HeavySlam;
		if (string.equalsIgnoreCase("HiddenPower")) return SpecialAttackType.HiddenPower;
		if (string.equalsIgnoreCase("HornDrill")) return SpecialAttackType.HornDrill;
		if (string.equalsIgnoreCase("JumpKick")) return SpecialAttackType.JumpKick;
		if (string.equalsIgnoreCase("Magnitude")) return SpecialAttackType.Magnitude;
		if (string.equalsIgnoreCase("MilkDrink")) return SpecialAttackType.MilkDrink;
		if (string.equalsIgnoreCase("NightShade")) return SpecialAttackType.NightShade;
		if (string.equalsIgnoreCase("PainSplit")) return SpecialAttackType.PainSplit;
		if (string.equalsIgnoreCase("Payday")) return SpecialAttackType.Payday;
		if (string.equalsIgnoreCase("PetalDance")) return SpecialAttackType.PetalDance;
		if (string.equalsIgnoreCase("Punishment")) return SpecialAttackType.Punishment;
		if (string.equalsIgnoreCase("PsychUp")) return SpecialAttackType.PsychUp;
		if (string.equalsIgnoreCase("Psywave")) return SpecialAttackType.Psywave;
		if (string.equalsIgnoreCase("Restore")) return SpecialAttackType.Restore;
		if (string.equalsIgnoreCase("Return")) return SpecialAttackType.Return;
		if (string.equalsIgnoreCase("Reversal")) return SpecialAttackType.Reversal;
		if (string.equalsIgnoreCase("SeismicToss")) return SpecialAttackType.SeismicToss;
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
		if (string.equalsIgnoreCase("Eruption")) return true;
		if (string.equalsIgnoreCase("Facade")) return true;
		if (string.equalsIgnoreCase("Frustration")) return true;
		if (string.equalsIgnoreCase("Guillotine")) return true;
		if (string.equalsIgnoreCase("GyroBall")) return true;
		if (string.equalsIgnoreCase("HeavySlam")) return true;
		if (string.equalsIgnoreCase("Payday")) return true;
		if (string.equalsIgnoreCase("HiddenPower")) return true;
		if (string.equalsIgnoreCase("HornDrill")) return true;
		if (string.equalsIgnoreCase("JumpKick")) return true;
		if (string.equalsIgnoreCase("MilkDrink")) return true;
		if (string.equalsIgnoreCase("Magnitude")) return true;
		if (string.equalsIgnoreCase("NightShade")) return true;
		if (string.equalsIgnoreCase("PainSplit")) return true;
		if (string.equalsIgnoreCase("Payday")) return true;
		if (string.equalsIgnoreCase("PetalDance")) return true;
		if (string.equalsIgnoreCase("Punishment")) return true;
		if (string.equalsIgnoreCase("PsychUp")) return true;
		if (string.equalsIgnoreCase("Psywave")) return true;
		if (string.equalsIgnoreCase("Restore")) return true;
		if (string.equalsIgnoreCase("Return")) return true;
		if (string.equalsIgnoreCase("Reversal")) return true;
		if (string.equalsIgnoreCase("SeismicToss")) return true;
		if (string.equalsIgnoreCase("SmackDown")) return true;
		if (string.equalsIgnoreCase("Substitute")) return true;
		if (string.equalsIgnoreCase("Venoshock")) return true;
		return false;
	}
}
