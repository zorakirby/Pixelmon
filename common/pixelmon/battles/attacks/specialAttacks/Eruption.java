package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Eruption extends SpecialAttackBase {

	public Eruption() {
		super(SpecialAttackType.Eruption, ApplyStage.During, false);
		
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
							   Attack a, ArrayList<String> attackList,
							   ArrayList<String> targetAttackList)
							   {
		
		
		a.baseAttack.basePower = 150 * (user.getHealth()/user.getMaxHealth());
		

		return false;
							   }

}
