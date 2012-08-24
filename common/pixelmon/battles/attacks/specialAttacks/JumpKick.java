package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
public class JumpKick extends SpecialAttackBase {

	public JumpKick() {
		super(SpecialAttackType.JumpKick, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		return false;
	}
	
	@Override
	public void ApplyMissEffect(PixelmonEntityHelper user, PixelmonEntityHelper target){
		user.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), user.getHealth()/2);
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() +" kept on going and hurt itself trying to land!");
	}
}
