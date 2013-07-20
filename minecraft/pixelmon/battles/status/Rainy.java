package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Rainy extends GlobalStatusBase {

	public Rainy() {
		super(false, true);
	}
	
	@Override
	public String endOfTurnMessage()
	{
		return "It is raining heavily!";
	}
	
	@Override
	public void applyInMoveEffect(EntityPixelmon user, EntityPixelmon target, Attack a)
	{
		System.out.println(a.baseAttack.basePower);
		if (a.baseAttack.basePower != -1)
		{
		if (a.baseAttack.attackType == EnumType.Water)
			a.baseAttack.basePower *= 2;
		else if (a.baseAttack.attackType == EnumType.Fire)
			a.baseAttack.basePower /= 2;
		}
		System.out.println(a.baseAttack.basePower);
		return;	
	}

}
