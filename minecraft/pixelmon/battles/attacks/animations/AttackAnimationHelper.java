package pixelmon.battles.attacks.animations;

import net.minecraft.src.EntityLiving;
import pixelmon.battles.attacks.animations.IAttackAnimation;

public class AttackAnimationHelper implements IAttackAnimation {

	public void doMove(EntityLiving user, EntityLiving target) {
		
	}
	public AttackAnimationHelper(String animationString){
		System.out.println(animationString);

	}

}
