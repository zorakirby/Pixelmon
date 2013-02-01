package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Curse extends SpecialAttackBase {

	public Curse() {
		super(SpecialAttackType.Curse, ApplyStage.During, false);

	}

	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception{
		if (user.type.contains(EnumType.Ghost)) {

			for (StatusEffectBase e : target.status) {

				if (e.type == StatusEffectType.Cursed) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already cursed!");
				}
			}
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " cut its own HP and laid a curse on the  " + target.getName() + "!");
			user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 2));
			target.status.add(new pixelmon.battles.attacks.statusEffects.Cursed());
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
