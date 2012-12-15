package pixelmon.battles.attacks.animations;

import net.minecraft.entity.EntityLiving;

public interface IAttackAnimation 
{
	public void doMove(EntityLiving user, EntityLiving target);
}
