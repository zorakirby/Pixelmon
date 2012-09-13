package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.statusEffects.Confusion;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
public class PetalDance extends MultiTurnSpecialAttackBase {

	public PetalDance() {
		super(MultiTurnSpecialAttackType.PetalDance, (new Random()).nextInt(2)+2);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		if (!persists){
			persists = true;
			turnCounter=0;
		}
		turnCounter++;
		if (turnCounter == turnCount){
			persists = false;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " became confused!");
			user.status.add(new Confusion());
		}
		return false;
	}

	@Override
	public boolean cantMiss() {
		
		return false;
	}

}
