package pixelmon.battles.status;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.controller.BattleController;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Sunny extends GlobalStatusBase {
	int turnsToGo = 10;
	public Sunny() {
		super("Sunny");
	}

	public String endOfTurnMessage(BattleController bc)
	{
		turnsToGo--;
		if (turnsToGo == 0)
		{
			bc.globalStatusController.removeGlobalStatus(this);
			return "The sunlight faded.";
		}
		return "The sunlight is harsh!";
	}
	
	@Override
	public void applyInMoveEffect(EntityPixelmon user, EntityPixelmon target, Attack a)
	{
		if (a.baseAttack.accuracy != -1)
		{
			if (a.baseAttack.attackType == EnumType.Water)
				a.movePower *= 0.5;
			else if (a.baseAttack.attackType == EnumType.Fire)
				a.movePower *= 1.5;
		}
	}
}
