package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Paralysis extends StatusBase {

	public Paralysis(EntityPixelmon affected) {
		super(StatusType.Paralysis, false, true, false);
		affected.battleStats.setIsParalyzed();
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (RandomHelper.getRandomNumberBetween(0, 100) <= 25) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is paralyzed and cannot move!");
			return false;
		} else {
			return true;
		}
	}
}
