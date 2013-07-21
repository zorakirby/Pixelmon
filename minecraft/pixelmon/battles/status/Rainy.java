package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Rainy extends GlobalStatusBase {
	int turnsToGo = 5;
	boolean endsThisTurn = false;
	public Rainy() {
		super(false, true);
	}
	
	@Override
	public String endOfTurnMessage(ArrayList<GlobalStatusBase> list)
	{
		turnsToGo--;
		if (turnsToGo == 0)
		{
			list.remove(this);
			return "The rain subsided";
		}
		return "It is raining heavily!";
	}
	
	@Override
	public void applyInMoveEffect(EntityPixelmon user, EntityPixelmon target, Attack a)
	{
		if (a.baseAttack.basePower != -1)
		{
			if (a.baseAttack.attackType == EnumType.Water)
				a.baseAttack.basePower *= 1.5;
	   else if (a.baseAttack.attackType == EnumType.Fire)
				a.baseAttack.basePower *= 0.5;
		}
		return;	
	}

}
