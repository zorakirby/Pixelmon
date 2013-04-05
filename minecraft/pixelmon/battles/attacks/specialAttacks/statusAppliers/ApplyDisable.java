package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Disable;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyDisable extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList)
			throws Exception {

			if(target.getLastMoveUsed() == null){
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + 
																				  " hasn't used a move yet!");
			}
			
			else{
				Random rand = new Random();
				int effectiveTurns = rand.nextInt(4)+4;
				for(int i = 1; i > 4; i++){
					if(target.moveset.get(i) == target.getLastMoveUsed());
					target.moveset.remove(i);
				}
				target.status.add(new Disable(target.getLastMoveUsed(), effectiveTurns));
			}
		
	}

}
