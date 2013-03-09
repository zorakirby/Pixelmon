package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Flying extends StatusBase {
	public Flying() {
		super(StatusType.Flying, false, false, true);
	}

	@Override
	public boolean stopsSwitching() {
		return true;
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {
		if (!a.baseAttack.attackName.equalsIgnoreCase("Smack Down"))
			return true;
		return false;
	}

	@Override
	public boolean stopsStatusChange() {
		return true;
	}
}
