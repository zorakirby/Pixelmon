package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.GlobalStatusBase;
import pixelmon.battles.status.Rainy;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyRainy extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> tsargetAttackList) throws Exception {
		if (user.battleController.getWeather() instanceof Rainy)
		{
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
				return;
		}
		user.battleController.checkAndRemoveWeather();
		user.battleController.addGlobalStatus(new Rainy());
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {
		return true;
	}
}
