package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Yawn extends StatusBase {

	int effectTurns = 0;

	public Yawn() {
		super(StatusType.Yawn, true, false, false);
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		effectTurns++;

		if (effectTurns == 2) {
			user.status.add(new Sleep());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " fell asleep!");
			user.status.remove(this);
		}
	}
}
