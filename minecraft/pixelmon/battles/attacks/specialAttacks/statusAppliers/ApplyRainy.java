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
		for (int i = 0; i < user.battleController.getGlobalStatusSize(); i++)
		{
			if (user.battleController.getGlobalStatus(i) instanceof Rainy)
			{
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
				return;
			}
				
		}
		user.battleController.checkAndRemoveWeather();
		user.battleController.addGlobalStatus(new Rainy());
		for (int i = 0; i < user.battleController.getGlobalStatusSize(); i++)
		{
			System.out.println(user.battleController.getGlobalStatus(i).getName());
		}
		
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {
		return true;
	}
}
