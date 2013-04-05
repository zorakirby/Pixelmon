package pixelmon.battles.status;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Disable extends StatusBase {
	Attack disabledMove;
	int effectiveTurns;
	int elapsedTurns;
	public Disable(Attack attack, int turns) {
		super(StatusType.Disable, true, false, false);
		disabledMove = attack;	
		effectiveTurns = turns;
	}
	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if(effectiveTurns > elapsedTurns){
			elapsedTurns++;
		}
		else if(effectiveTurns == elapsedTurns){
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), disabledMove.baseAttack.attackName +
																			  " on "  + target.getNickname() + 
																			  " was enabled again!");
			target.status.remove(this);
			target.moveset.add(disabledMove);
			
		}
	}
	
}
