package pixelmon.battles.status;

import java.util.ArrayList;

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
		return true;
	}

	@Override
	public boolean stopsStatusChange() {
		return true;
	}
}
