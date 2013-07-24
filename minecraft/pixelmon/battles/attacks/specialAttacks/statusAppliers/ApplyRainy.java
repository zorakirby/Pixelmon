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
		System.out.println("Size of statuses is " + user.battleController.globalStatuses.size());
		for (int i = 0; i < user.battleController.globalStatuses.size(); i++)
		{
			System.out.println(user.battleController.globalStatuses.get(i).getName());
			if (user.battleController.globalStatuses.get(i) instanceof Rainy)
			{
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
				return;
			}
				
		}
		user.battleController.checkAndRemoveWeather();
		user.battleController.globalStatuses.add(new Rainy());
		for (int i = 0; i < user.battleController.globalStatuses.size(); i++)
		{
			System.out.println(user.battleController.globalStatuses.get(i).getName());
		}
		
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {
		return true;
	}
}
