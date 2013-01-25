package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.Flying;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Fly extends MultiTurnSpecialAttackBase {

	public Fly(MultiTurnSpecialAttackType type, int turnCount) {
		super(type, turnCount);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		if (!doesPersist(user)){
			setPersists(user, true);
			initTurnCount(user);
		}
		incrementTurnCount(user);
		if (getTurnCount(user) == 1){
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " Flies up in the air!");
			user.status.add(new Flying());
			return true;
		}else{
			for (int i =0; i < user.status.size(); i++){
				StatusEffectBase e = user.status.get(i);
				if (e.type == StatusEffectType.Flying) user.status.remove(e);
			}
			setPersists(user, false);
			return false;
		}
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {
		if (getTurnCount(user) ==0) return true;
		return false;
	}
}
