package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class LockedInBattle extends StatusBase {
	EntityPixelmon locker;

	public LockedInBattle(EntityPixelmon locker) {
		super(StatusType.LockedInBattle, false, true, false);
		this.locker = locker;
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {
		if (locker != user) {
			target.isLockedInBattle = false;
			target.status.remove(this);
		}
	}

}
