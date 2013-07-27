package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Sandstorm;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplySandstorm extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {

		if (user.battleController.getWeather().getName().equals("Sandstorm"))
		{
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
			return;
		}
		
		user.battleController.checkAndRemoveWeather();
		user.battleController.addGlobalStatus(new Sandstorm("Sandstorm"));
	}

}
