package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ElectroBall extends SpecialAttackBase {
	int speedRelationship;
	public ElectroBall() {
		super(ApplyStage.During, false);

	}
 
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		int targetSpeed = target.stats.Speed, userSpeed = user.stats.Speed;
		if ((double)userSpeed*0.25 > targetSpeed)
			a.movePower = 150;
		else if ((double)(userSpeed*0.50) <= targetSpeed) 
			a.movePower = 60;
		else if ((double)(userSpeed*0.34) <= targetSpeed)
			a.movePower = 80;
		else if ((double)(userSpeed*0.25) < targetSpeed)
			a.movePower = 120;
		return false;
	}

}
