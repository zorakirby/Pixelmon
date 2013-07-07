package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Disable;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyDisable extends StatusApplierBase {
	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		System.out.println(target.getLastMoveUsed().baseAttack.attackName);
		if (target.getLastMoveUsed() == null) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(),
					target.getNickname() + " hasn't used a move yet!");
		}

		else {
			
			
			
			//		rand.nextInt(4) + 4;
				if (!target.getLastMoveUsed().getDisabled())
				{
					target.getLastMoveUsed().setDisabled(true, target);
					target.status.add(new Disable(target.getLastMoveUsed()));
				}
					
				else if (target.getLastMoveUsed().getDisabled())
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getLastMoveUsed().baseAttack.attackName + " is already disabled!");	
		}
		

	}

}
