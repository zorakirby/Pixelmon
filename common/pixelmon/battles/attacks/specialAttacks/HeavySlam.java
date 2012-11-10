package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class HeavySlam extends SpecialAttackBase {

	public HeavySlam() {
		super(SpecialAttackType.HeavySlam, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) {
		
		int i = (int) (DatabaseStats.getWeight(target.getName()) / DatabaseStats.getWeight(user.getName()));

		if(i <=20)
			a.basePower = 120;
		else if(i > 20 && i <= 25)
			a.basePower = 100;
		else if(i > 25 && i <=(100/3))
			a.basePower = 80;
		else if(i > (100/3) && i >= 50)
			a.basePower = 60;
		else if(i > 50)
			a.basePower = 40;
			
			
		return false;
	}

}
