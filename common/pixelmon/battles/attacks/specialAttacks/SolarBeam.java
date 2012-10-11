package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
public class SolarBeam extends MultiTurnSpecialAttackBase {

	public SolarBeam() {
		super(MultiTurnSpecialAttackType.SolarBeam, 2);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		if (!persists){
			persists = true;
			turnCounter=0;
		}
		turnCounter++;
		for(StatusEffectBase e: user.status) if (e.type == StatusEffectType.Sunny) turnCounter++;
		if (turnCounter == 1){
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is storing energy!");
			return true;
		}else{
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
