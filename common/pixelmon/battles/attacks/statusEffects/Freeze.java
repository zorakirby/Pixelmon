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
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already frozen!");
					return;
				}
			if (target.type.contains(EnumType.Ice)){
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			target.status.add(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has been frozen solid");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");

	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		if (RandomHelper.getRandomNumberBetween(0, 100) <= 20) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " breaks free from the ice!");
			user.status.remove(this);
			return true;
		} else {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is frozen solid!");
			return false;
		}
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {
		return false;
	}

	public boolean ClearsOnBattleEnd() {
		return false;
	}
}
