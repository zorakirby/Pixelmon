package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.statusEffects.Flying;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class Fly extends MultiTurnSpecialAttackBase {

	public Fly(MultiTurnSpecialAttackType type, int turnCount) {
		super(type, turnCount);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		if (!persists){
			persists = true;
			turnCounter=0;
		}
		turnCounter++;
		if (turnCounter == 1){
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " Flies up in the air!");
			user.status.add(new Flying());
			return true;
		}else{
			for (int i =0; i < user.status.size(); i++){
				StatusEffectBase e = user.status.get(i);
				if (e.type == StatusEffectType.Flying) user.status.remove(e);
			}
			persists = false;
			return false;
		}
	}

	@Override
	public boolean cantMiss() {
		if (turnCounter ==0) return true;
		return false;
	}
}
