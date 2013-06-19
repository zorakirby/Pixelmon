package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Digging extends StatusBase {
	public Digging() {
		super(StatusType.Digging, false, false, true);
	}

	@Override
	public boolean stopsSwitching() {
		return true;
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {
		String move = a.baseAttack.attackName;
		if (!move.equalsIgnoreCase("earthquake")&&!move.equalsIgnoreCase("fissure")&&!move.equalsIgnoreCase("magnitude")){
			//Need to do something regarding powering up those moves here. Will require a fair bit of effort I think
			return true;}
		return false;
	}

	@Override
	public boolean stopsStatusChange() {
		return true;
	}
}
