package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class GyroBall extends SpecialAttackBase {

	public GyroBall(SpecialAttackType type, ApplyStage a, boolean persists) {
		super(SpecialAttackType.GyroBall, ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		System.out.println(">>>");
		
	a.basePower = (25*(target.stats.Speed / user.stats.Speed));
			System.out.println(target.stats.Speed / user.stats.Speed);
			System.out.println(a.basePower);
		return true;
	}

	
	
}
