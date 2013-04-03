package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class DoSetDamage extends SpecialAttackBase {
	int damage;
	public DoSetDamage(Value... values) {
		super(ApplyStage.During, false);
		damage = values[0].value;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		String name = a.baseAttack.attackName.toLowerCase();			
		
		
		System.out.println(damage);
		target.setEntityHealth(target.getHealth()-damage);

		
		if(name.contains("super fang"))
			target.setEntityHealth(target.getHealth()/2);
		
		return false;
	}

}
