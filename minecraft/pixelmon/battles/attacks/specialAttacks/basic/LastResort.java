package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class LastResort extends SpecialAttackBase {

	public LastResort() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		boolean usedAllMoves = true;
		System.out.println(user.moveset.size());
		if (user.moveset.size() == 1)
		{
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
			return true;
		}
			
		for (Attack atk : user.moveset)
		{
			if(usedAllMoves == false)
			{
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
				return true;
			}
			if(!atk.baseAttack.attackName.equalsIgnoreCase("Last Resort")); // Last Resort.
			for (String s : attackList)
			{
				
				System.out.println(s);
				if (s == atk.baseAttack.attackName)
				{
					usedAllMoves = true;
					break;					
				}
				else
					usedAllMoves = false;
				}
			
		}
		if(!usedAllMoves)
		{
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), a.baseAttack.attackName + " failed!");
			return true;
		}
		return false;
	}

}
