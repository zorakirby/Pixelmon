package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Sleep extends StatusEffectBase {

	int setTurnsValue = -1;
	boolean setTurns = false;

	public Sleep() {
		super(StatusEffectType.Sleep, false, true, false);
	}

	public Sleep(int i) {
		super(StatusEffectType.Sleep, false, true, false);
		setTurnsValue = i;
		setTurns = true;
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Sleep) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already asleep!");
					return;
				}

			target.status.add(this);

			if (setTurns)
				target.battleVariables.set(type, setTurnsValue);
			else
				target.battleVariables.set(type, RandomHelper.getRandomNumberBetween(1, 4));
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has fallen asleep!");

		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " did not fall asleep!");
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is still sleeping!");
		return false;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) {
		if (user.battleVariables.get(type) == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " wakes up!");
			user.status.remove(this);
		}
		user.battleVariables.decrement(type);

	}

	@Override
	public boolean clearsOnBattleEnd() {
		return false;
	}

	public void init(EntityPixelmon target) {
		target.battleVariables.set(type, RandomHelper.getRandomNumberBetween(1, 4));
	}
}
