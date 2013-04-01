package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class DoSetDamage extends SpecialAttackBase {
	public DoSetDamage(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		String name = a.baseAttack.attackName.toLowerCase();			
		System.out.println(name);
		
		int healthDecrement = 0;					//Note: I cannot seem to get the parameter of the effect column
													//      passed in, and that is why I'm checking for the attack name	
		if(name.contains("sonicboom"))				//		here. In case you are wondering :)
			healthDecrement = 20;
		
		else if(name.contains("dragon rage"))
		{
			System.out.println("dahell");
			healthDecrement = 40;
		}

		target.setEntityHealth(target.getHealth()-healthDecrement);

		
		if(name.contains("super fang"))
			target.setEntityHealth(target.getHealth()/2);
		
		return false;
	}

}
