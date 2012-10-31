package pixelmon.battles.attacks.statusEffects;

public enum StatusEffectType {
	Burn(0, true), Confusion(1), Cursed(2), Infatuated(3), Flee(4), Flying(5), Freeze(6, true), FireSpin(7), Leech(8), LightScreen(9), Mist(10), Paralysis(11,
			true), Poison(12, true), PoisonBadly(13, true), Protect(14), SafeGuard(15), Sleep(16, true), SmackedDown(17), Substitute(18), Sunny(19), WaitAfter(
			20), TrickRoom(21), Perish(22);

	public boolean canStack = false;
	public int index;

	private StatusEffectType(int index) {
		this.index = index;
	}

	private StatusEffectType(int index, boolean canStack) {
		this.index = index;
		this.canStack = canStack;
	}

	public static StatusEffectType getStatusEffect(String string) {
		for (StatusEffectType t : values())
			if (t.toString().equalsIgnoreCase(string))
				return t;
		return null;
	}

	public static boolean isStatusEffect(String string) {
		for (StatusEffectType t : values())
			if (t.toString().equalsIgnoreCase(string))
				return true;
		return false;
	}

	public static StatusEffectType getEffect(int integer) {
		for (StatusEffectType t : values())
			if (t.index == integer)
				return t;
		return null;
	}
}
