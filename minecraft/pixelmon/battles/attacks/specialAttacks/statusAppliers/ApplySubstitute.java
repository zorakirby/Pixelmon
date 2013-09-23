package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusType;
import pixelmon.battles.status.Substitute;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplySubstitute extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			if (user.hasStatus(StatusType.Substitute) || user.func_110143_aJ() <= (user.getMaxHealth()/4))
			{
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "But the move failed!");
				return;
			}
			int health = user.getMaxHealth()/4;
			user.doBattleDamage(user, user.getMaxHealth()/4);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " created a substitute!");
			user.status.add(new Substitute(health));
	}

}
