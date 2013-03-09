package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Infatuated extends StatusBase {

	EntityPixelmon originalTarget;

	public Infatuated(EntityPixelmon originalTarget) {
		super(StatusType.Infatuated, false, true, false);
		this.originalTarget = originalTarget;
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (originalTarget != target) {
			user.status.remove(this);
			return true;
		}

		if (RandomHelper.getRandomNumberBetween(0, 100) <= 50) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is in love!");
			return false;
		} else {
			return true;
		}
	}
}
