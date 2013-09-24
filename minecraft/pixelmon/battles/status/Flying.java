package pixelmon.battles.status;

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
		String moveName = a.baseAttack.attackName;
		if (moveName.equalsIgnoreCase("Hurricane")|| moveName.equalsIgnoreCase("Whirlwind")
				|| moveName.equalsIgnoreCase("Smack Down") || moveName.equalsIgnoreCase("Thunder")
				|| moveName.equalsIgnoreCase("Sky Uppercut"))
			return false;			
		if (moveName.equalsIgnoreCase("Twister") || moveName.equalsIgnoreCase("Gust"))
		{
			a.movePower*=2;
			return false;
		}
		return true;
	}

	@Override
	public boolean stopsStatusChange(Attack a) {
		return true;
	}
}
