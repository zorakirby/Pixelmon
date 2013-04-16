package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.Recharge;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyRecharge extends StatusApplierBase {
	int turnWait = 0;
	public ApplyRecharge(Value...values){
		turnWait = values[0].value;
	}

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			
			user.status.add(new Recharge(turnWait));
		
	}

}
