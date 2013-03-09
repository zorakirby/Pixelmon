package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Flee extends StatusBase {

	public Flee() {
		super(StatusType.Flee, false, false, true);
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		return false;
	}
}
