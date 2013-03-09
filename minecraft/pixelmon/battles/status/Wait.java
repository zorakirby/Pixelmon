package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Wait extends StatusBase {

	private int effectTurns;
	private int totalTurns;

	public Wait(int value) {
		super(StatusType.Wait, false, true, false);
		totalTurns = value;
		effectTurns = 0;
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is recharging!");
		return false;
	}

	@Override
	public double adjustDamage(Attack a, double damage, EntityPixelmon user, EntityPixelmon target, double crit) {
		return 0;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		effectTurns++;
		if (effectTurns == totalTurns)
			user.status.remove(this);
	}

}
