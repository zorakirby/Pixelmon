package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Disable;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyDisable extends StatusApplierBase {
	Random rand = new Random();
	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		String lastUsedMove = targetAttackList.get(targetAttackList.size()-1);
		if (targetAttackList.size() - 1 == -1) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(),
					target.getNickname() + " hasn't used a move yet!");
		}

		else {
			
			
			int effectiveTurns = rand.nextInt(4) + 4;
			for(Attack atk: target.moveset){
				if (atk.baseAttack.attackName.equalsIgnoreCase(lastUsedMove) && !atk.getDisabled()) {
					
					atk.setDisabled(true, target);
					target.status.add(new Disable(atk, effectiveTurns));
				}
				
				else if(atk.baseAttack.attackName.equalsIgnoreCase(lastUsedMove)&& atk.getDisabled())
				{	
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), atk.baseAttack.attackName + " is already disabled!");	
				}
			}

		}
		

	}

}
