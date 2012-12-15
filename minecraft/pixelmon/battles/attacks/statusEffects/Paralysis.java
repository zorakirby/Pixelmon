package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Paralysis extends StatusEffectBase {

	public Paralysis() {
		super(StatusEffectType.Paralysis, false, true, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Paralysis) {
					return;
				}

			target.status.add(this);
			target.battleStats.setIsParalyzed();
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is paralyzed!");
		}

	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		if (RandomHelper.getRandomNumberBetween(0, 100) <= 25) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is paralyzed and cannot move!");
			return false;
		} else {
			return true;
		}
	}
}
