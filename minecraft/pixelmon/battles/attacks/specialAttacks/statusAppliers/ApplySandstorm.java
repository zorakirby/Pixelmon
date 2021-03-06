package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Sandstorm;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplySandstorm extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		if (user.battleController.globalStatusController.getWeather() instanceof Sandstorm)
		{
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
			return;
		}
		user.battleController.globalStatusController.addGlobalStatus(new Sandstorm("Sandstorm", RandomHelper.getRandomNumberBetween(5, 8)*2));
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "A sandstorm kicked up!");
	}

}
