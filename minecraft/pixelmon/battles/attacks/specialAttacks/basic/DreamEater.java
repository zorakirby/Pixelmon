package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class DreamEater extends SpecialAttackBase {

	public DreamEater() {
		super(ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {

			boolean hasIt = false;
			float restoration = 0;
		
			for (StatusBase e : target.status){
				if (e.type == StatusType.Sleep) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " ate " + target.getNickname() + "'s dream!");
				hasIt = true;
				}
			}
		
			if(!hasIt){
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is not asleep!");
				return true;
			}
		
			else if (hasIt){
				restoration = a.doDamageCalc(user, target, crit)/2;
			}
		
			if(restoration >= target.func_110143_aJ()){
				restoration = target.func_110143_aJ()/2;
			}
		
		
		user.heal(restoration);
		return false;
		}
	}

