package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Poison;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class ApplyPoison extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList)throws Exception {
		if (checkChance()) {
			
			boolean hasAPrimary = target.hasPrimaryStatus();
			
			if (target.type.contains(EnumType.Poison) || hasAPrimary)
			{
				if(attack.baseAttack.basePower == -1)
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), attack.baseAttack.attackName + " failed!");
				return;
			}			
			if (target.hasStatus(StatusType.Poison) || target.hasStatus(StatusType.PoisonBadly)) {
				if (attack.baseAttack.basePower == -1)
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already poisoned!");
				return;
			}

			target.status.add(new Poison());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " has been poisoned!");
		}
	}

}
