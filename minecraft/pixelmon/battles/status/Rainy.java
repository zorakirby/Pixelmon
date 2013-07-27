package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.controller.BattleController;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Rainy extends GlobalStatusBase {
	
	int turnsToGo = 10; // endOfTurnMessage is called twice at once, so turns require doubling (will make a fix at some point)
	public Rainy() {
		super("Rainy");
	}
	
	@Override
	public String endOfTurnMessage(BattleController bc)
	{
		turnsToGo--;
		if (turnsToGo == 0)
		{
			bc.removeGlobalStatus(this);
			return "The rain stopped";
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
	}
}
