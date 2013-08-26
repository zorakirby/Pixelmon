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
		if(a.baseAttack.attackName != "Earthquake" && a.baseAttack.attackName != "Magnitude" 
				&& a.baseAttack.attackName != "Fissure")
		return true;
		
		//target.battleController.participants.get(target.battleController.).attack.baseAttack.basePower *=2;
		return false;
	}

	@Override
	public boolean stopsStatusChange() {
		return true;
	}
}
