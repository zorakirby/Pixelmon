package pixelmon.battles.status;

import java.util.ArrayList;

public enum StatusType {
	Burn(0, true), Confusion(1), Cursed(2), Infatuated(3), Flee(4), Flying(5), Freeze(6, true), FireSpin(7), Leech(8), LightScreen(9), Mist(10), Paralysis(11,
			true), Poison(12, true), PoisonBadly(13, true), Protect(14), SafeGuard(15), Sleep(16, true), SmackedDown(17), Substitute(18), Sunny(19), Wait(20), TrickRoom(
			21), Perish(22), Yawn(24), Disable(25), Immobilize(26), Recharge(27), AquaRing(28), UnderGround(29), Transformed(30), MeanLook(31), Clamped(32);
	public boolean canStack = false;
	public int index;

	private StatusType(int index) {
		this.index = index;
	}

	private StatusType(int index, boolean canStack) {
		this.index = index;
		this.canStack = canStack;
	}

	public static StatusType getStatusEffect(String string) {
		for (StatusType t : values())
			if (t.toString().equalsIgnoreCase(string))
				return t;
		return null;
	}

	public static boolean isStatusEffect(String string) {
		for (StatusType t : values())
			if (t.toString().equalsIgnoreCase(string))
				return true;
		return false;
	}

	static ArrayList<StatusPersist> restoreStatusList;
	static {
		restoreStatusList = new ArrayList<StatusPersist>();
		restoreStatusList.add(new Burn());
		restoreStatusList.add(new Freeze());
		restoreStatusList.add(new Poison());
		restoreStatusList.add(new PoisonBadly());
		restoreStatusList.add(new Sleep());
	}

	public static StatusType getEffect(int integer) {
		for (StatusType t : values())
			if (t.index == integer)
				return t;
		return null;
	}

	public static StatusPersist getEffectInstance(int integer) {
		StatusType type = null;
		for (StatusType t : values())
			if (t.index == integer)
				type = t;
		if (type != null) {
			for (StatusPersist s : restoreStatusList)
				if (s.type == type)
					return s;
		}
		return null;
	}
}
