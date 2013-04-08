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
		if (targetAttackList.size() - 1 == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(),
					target.getNickname() + " hasn't used a move yet!");
		}

		else {
			
			
			int effectiveTurns = rand.nextInt(4) + 4;
			int i = -1;
			for(Attack atk: target.moveset){
				i++;
				System.out.println(targetAttackList.size()-1);
				System.out.println(targetAttackList.get(targetAttackList.size()-1));
				System.out.println(target.moveset.get(i).baseAttack.attackName);
				if (target.moveset.get(i).baseAttack.attackName.equalsIgnoreCase(targetAttackList.get(targetAttackList.size()-1))/*&& !target.moveset.get(i).disabled*/) {

					
					target.moveset.get(i).disabled = true;
					
					ChatHandler.sendBattleMessage(user.getOwner(),target.getOwner(),target.moveset.get(i).baseAttack.attackName);
					target.status.add(new Disable(target.moveset.get(i),
							effectiveTurns));
					System.out.println("Applied disable");
				}
				else if(target.moveset.get(i).baseAttack.attackName.equalsIgnoreCase(targetAttackList.get(targetAttackList.size()))&& target.moveset.get(i).disabled){
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "That move is already disabled!");
				}
			}

		}
		

	}

}
