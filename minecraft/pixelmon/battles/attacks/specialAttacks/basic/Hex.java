package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Hex extends SpecialAttackBase {

	public Hex() {
		super(ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			
		if(target.hasPrimaryStatus())
			a.movePower = 100;		
		else
			a.movePower = 50;
		
		return false;
	}
	
}