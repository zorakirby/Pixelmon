package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Freeze extends StatusEffectBase {

	public Freeze() {
		super(StatusEffectType.Freeze, false, true, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Freeze) {
					return;
				}
			if (target.type.contains(EnumType.Ice)) {
				return;
			}
			target.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has been frozen solid");
		}
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		if (RandomHelper.getRandomNumberBetween(0, 100) <= 20) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " breaks free from the ice!");
			user.status.remove(this);
			return true;
		} else {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is frozen solid!");
			return false;
		}
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {
		return false;
	}

	public boolean clearsOnBattleEnd() {
		return false;
	}
}
