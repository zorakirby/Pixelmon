package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;



public class Paralysis extends StatusEffectBase {

	public Paralysis() {
		super(StatusEffectType.Paralysis, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Paralysis) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already paralyzed!");
					return;
				}

			target.status.add(this);
			target.battleStats.setIsParalyzed();
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is paralyzed!");

		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");

	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (RandomHelper.getRandomNumberBetween(0, 100) <= 25) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is paralyzed!");
			return false;
		} else {
			return true;
		}
	}
}
