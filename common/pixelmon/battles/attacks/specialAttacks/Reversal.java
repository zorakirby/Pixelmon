package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Reversal extends SpecialAttackBase {

	public Reversal() {
		super(SpecialAttackType.Reversal, ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
	
		int percentage = (int) (user.getMaxHealth()/user.getHealth() * 100);
		
		if(percentage >=71)
			a.basePower = 20;
		
		else if(percentage <71 && percentage >=36)
			a.basePower = 40;
		
		else if(percentage <36 && percentage >=21)
			a.basePower = 80;
		
		else if(percentage <21 && percentage >=11)
			a.basePower = 100;
		
		else if(percentage <11 && percentage >=5)
			a.basePower = 150;
		
		else if(percentage <5 && percentage >=0)
			a.basePower = 200;
		
		
		return false;
	}

}
