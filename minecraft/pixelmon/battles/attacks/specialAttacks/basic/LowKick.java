package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class LowKick extends SpecialAttackBase{

	public LowKick() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		
		int weight = (int) DatabaseStats.getWeight(target.getNickname());
		
		if(weight >= 0.1){
			a.baseAttack.basePower = 20;
		}
		if(weight >= 10.0){
			a.baseAttack.basePower = 40;
		}
		if(weight >= 25.0){
			a.baseAttack.basePower = 60;
		}
		if(weight >= 50.0){
			a.baseAttack.basePower = 80;
		}
		if(weight >= 100.0){
			a.baseAttack.basePower = 100;
		}
		if(weight >= 200.0){
			a.baseAttack.basePower = 120;
		}
		
		return false;
	}

}
