package pixelmon.battles.status;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Protect extends StatusBase {

	public Protect() {
		super(StatusType.Protect, false, false, true);
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " protects itself!");
		return true;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		user.status.remove(this);
	}
}
