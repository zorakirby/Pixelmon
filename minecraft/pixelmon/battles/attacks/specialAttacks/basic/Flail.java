package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Flail extends SpecialAttackBase {

	public Flail() {
		super(ApplyStage.During, false);
	
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			
		double i = (double)(user.func_110143_aJ()/(double)user.getMaxHealth())*100 + 1;
		if(i >= 70)
			a.movePower = 20;
		else if (i >= 35 && i < 70)
			a.movePower = 40;
		else if (i >= 20 && i < 35)
			a.movePower = 80;
		else if (i >= 10 && i < 20)
			a.movePower = 100;
		else if (i >= 4 && i < 10)
			a.movePower = 150;
		else if (i > 0 && i < 4)
			a.movePower = 200;
		return false;
	}

}
