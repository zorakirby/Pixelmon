package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class RaiseStats extends SpecialAttackBase {

	public RaiseStats() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		Random rand = new Random();
		if (rand.nextInt(10) + 1 == 1) {
			user.battleStats.IncreaseAttack(1);
			user.battleStats.IncreaseDefence(1);
			user.battleStats.IncreaseSpecialAttack(1);
			user.battleStats.IncreaseSpecialDefence(1);
			user.battleStats.IncreaseSpeed(1);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + "'s stats rose!");
		}
		return false;
	}

}
