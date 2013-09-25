package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Hail;
import pixelmon.battles.status.Sandstorm;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyHail extends StatusApplierBase {
	
	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		if (user.battleController.globalStatusController.getWeather() instanceof Hail)
		{
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
			return;
		}
		user.battleController.globalStatusController.addGlobalStatus(new Hail("Hail", 10));
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It started to hail!");

	}
}