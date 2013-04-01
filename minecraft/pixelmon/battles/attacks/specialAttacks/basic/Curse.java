package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.Cursed;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Curse extends SpecialAttackBase {

	public Curse(Value... values) {
		super(ApplyStage.During, false);

	}

	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (user.type.contains(EnumType.Ghost)) {

			for (StatusBase e : target.status) {

				if (e.type == StatusType.Cursed) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already cursed!");
				}
			}
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " cut its own HP and laid a curse on the  " + target.getNickname() + "!");
			user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 2));
			target.status.add(new Cursed());
		} else {
			user.battleStats.DecreaseSpeed(1);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Speed was lowered!");
			user.battleStats.IncreaseAttack(1);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Attack was raised!");
			user.battleStats.IncreaseDefence(1);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Defense was raised!");

		}
		return false;

	}
}
