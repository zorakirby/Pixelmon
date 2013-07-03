package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Haze extends SpecialAttackBase {

	public Haze() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		//Clears the attack, defense, etc. modifiers on the user and target.
		target.battleStats.clearBattleStats();
		user.battleStats.clearBattleStats();
		return true;
	}

}
