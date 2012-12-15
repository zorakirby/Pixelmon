package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Frustration extends SpecialAttackBase {

	public Frustration() {
		super(SpecialAttackType.Frustration, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		a.baseAttack.basePower = (int) ((255 - user.friendship.friendship) / 2.5);
		return false;
	
	}

}
