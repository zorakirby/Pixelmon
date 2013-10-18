package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Spite extends SpecialAttackBase {

	public Spite() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			
			if (target.getLastMoveUsed() != null)
			{
				for (Attack e : target.getMoveset())
				{
					if (e.baseAttack.attackName == target.getLastMoveUsed().baseAttack.attackName)
					{
						e.pp -= 4;
						if (e.pp < 0)
							e.pp = 0;
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + "'s " 
								+ target.getLastMoveUsed().baseAttack.attackName + "'s PP dropped!");
						return true;
					}
				}

			}
			else
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The move failed!");
		return true;
	}

}
