package pixelmon.battles.status;

import pixelmon.RandomHelper;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Immobilize extends StatusBase {
	int effectTurns = 0;
	boolean hasTotalTurns = false;

	public Immobilize() {
		super(StatusType.Immobilize, true, true, false);
	}

	public Immobilize(int value1, int value2) {
		super(StatusType.Immobilize, true, true, false);
		effectTurns = RandomHelper.getRandomNumberBetween(value1, value2);
		hasTotalTurns = true;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (hasTotalTurns) {
			effectTurns--;
			if (effectTurns == 0)
				user.status.remove(this);
		}
	}

	@Override
	public boolean stopsSwitching() throws Exception {
		return true;
	}
}
