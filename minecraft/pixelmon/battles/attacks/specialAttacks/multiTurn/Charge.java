package pixelmon.battles.attacks.specialAttacks.multiTurn;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Digging;
import pixelmon.battles.status.Flying;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Charge extends MultiTurnSpecialAttackBase {

	public Charge(){
		
	}
	
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			String move = a.baseAttack.attackName;

			if(!doesPersist(user)){
				setPersists(user, true);
				setTurnCount(user, 2);
			}
			decrementTurnCount(user);
			
			if(getTurnCount(user)==1){
				if(move.equals("Fly")){
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " flew into the air!");
				user.status.add(new Flying());}
				else if (move.equals("SolarBeam"))
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " charged up energy!");
				else if (move.equals("Dig")){
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " dug underground!");
				user.status.add(new Digging());}
				else if (move.equals("Razor Wind"))
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " whipped up a whirlwind!");
				else if (move.equals("Skull Bash")){
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " tucked in its head!");
				user.battleStats.IncreaseDefence(1);}
				else if (move.equals("Sky Attack"))
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " became cloaked in a harsh light!");
				else if (move.equals("Freeze Shock"))
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " became cloaked in a freezing light!");
				return true;
			
			}
			else				
				return false;
			
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {
		if(getTurnCount(user)==1)
		return true;
		return false;
	}

}
