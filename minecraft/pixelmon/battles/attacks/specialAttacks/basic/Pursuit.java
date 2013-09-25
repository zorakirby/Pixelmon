package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Pursuit extends SpecialAttackBase {

	public Pursuit() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			// Will need to think about this one
			return false;
	}

}
