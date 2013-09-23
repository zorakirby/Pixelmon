package pixelmon.battles.status;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class UnderGround extends StatusBase {
	public UnderGround() {
		super(StatusType.UnderGround, false, false, true);
	}

	@Override
	public boolean stopsSwitching() {
		return true;
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {
		if (a.baseAttack.attackName == "Earthquake" || a.baseAttack.attackName == "Magnitude")
		{
			a.baseAttack.basePower *=2;
			return false;
		}
		if (a.baseAttack.attackName == "Fissure")
		{
			a.cantMiss(user);
			return false;
		}
		return true;
	}

	@Override
	public boolean stopsStatusChange(Attack a) {
		return true;
	}
}
