package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class JumpKick extends SpecialAttackBase {

	public JumpKick() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		return false;
	}

	@Override
	public void ApplyMissEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		user.attackEntityFrom(DamageSource.causeMobDamage(user), user.func_110143_aJ() / 2);
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " kept on going and hurt itself trying to land!");
	}
}
