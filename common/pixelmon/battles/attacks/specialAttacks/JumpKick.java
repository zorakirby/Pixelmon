package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
public class JumpKick extends SpecialAttackBase {

	public JumpKick() {
		super(SpecialAttackType.JumpKick, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		return false;
	}
	
	@Override
	public void ApplyMissEffect(EntityPixelmon user, EntityPixelmon target){
		user.attackEntityFrom(DamageSource.causeMobDamage(user), user.getHealth()/2);
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() +" kept on going and hurt itself trying to land!");
	}
}
