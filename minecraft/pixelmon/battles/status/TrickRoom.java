package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class TrickRoom extends StatusBase {

	int effectTurns = 5;

	public TrickRoom() {
		super(StatusType.TrickRoom, false, false, false);
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (effectTurns == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The room returns to normal...");
			user.status.remove(this);
		}
		effectTurns--;
	}

	@Override
	public boolean participantMovesFirst(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (user.stats.Speed * user.battleStats.getSpeedModifier() > target.stats.Speed * target.battleStats.getSpeedModifier())
			return false;
		else if (target.stats.Speed * target.battleStats.getSpeedModifier() > user.stats.Speed * user.battleStats.getSpeedModifier())
			return true;
		else {
			if (RandomHelper.getRandomNumberBetween(1, 2) > 1)
				return false;
			else
				return true;
		}
	}

	@Override
	public boolean hasPriorityEffect() {
		return true;
	}
}
