package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Revenge extends SpecialAttackBase {

	public Revenge() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			if (user.battleController.participants.get(1).currentPokemon() == user)
				if (user.battleController.participants.get(1).damageTakenThisTurn != 0)
					a.movePower *= 2;
			else 
				if (user.battleController.participants.get(0).damageTakenThisTurn != 0)
					a.movePower *=2;
		System.out.println(a.movePower);
		return false;
	}

}
