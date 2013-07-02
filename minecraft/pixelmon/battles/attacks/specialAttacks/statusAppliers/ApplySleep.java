package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Sleep;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplySleep extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (checkChance()) {
			
			boolean hasAPrimary = target.hasPrimaryStatus();
			
			if (target.hasStatus(StatusType.Sleep))
			{
				if(attack.baseAttack.basePower == -1)
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already asleep!");
				return;
			}
			if (hasAPrimary)
			{
				if (attack.baseAttack.basePower == -1)
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), attack.baseAttack.attackName + " failed!");
				return;
			}
		
			target.status.add(new Sleep());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " has fallen asleep!");

		}
	}
}
