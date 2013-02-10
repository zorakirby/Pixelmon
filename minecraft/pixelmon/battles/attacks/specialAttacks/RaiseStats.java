package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class RaiseStats extends SpecialAttackBase {

	public RaiseStats() {
		super(SpecialAttackType.RaiseStats, ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		Random rand = new Random();
		if(rand.nextInt(10)+1 == 1){
			
		}
		
		return false;
	}

}
