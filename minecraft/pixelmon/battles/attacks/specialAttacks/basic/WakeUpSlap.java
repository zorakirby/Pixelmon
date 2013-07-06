package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class WakeUpSlap extends SpecialAttackBase{

	public WakeUpSlap() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		
		if(target.hasStatus(StatusType.Sleep)){
			a.baseAttack.basePower = a.baseAttack.basePower * 2;
			target.removeStatus(StatusType.Sleep);
		}
		
		return false;
	}

}
