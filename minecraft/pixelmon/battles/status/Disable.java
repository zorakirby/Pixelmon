package pixelmon.battles.status;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Disable extends StatusBase {
	Attack disabledMove;
	int effectiveTurns;
	int elapsedTurns = -1;
	public Disable(Attack attack, int turns) {
		super(StatusType.Disable, true, false, false);
		disabledMove = attack;	
		effectiveTurns = turns;
	}
	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		
			if(effectiveTurns == elapsedTurns){
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), disabledMove.baseAttack.attackName +
																			  " on "  + target.getNickname() + 
																			  " was enabled again!");
			for(int i = 0; i < 4; i++){
				if (target.moveset.get(i) == disabledMove){
					target.moveset.get(i).setDisabled(false, target);
				}
			}			
			target.status.remove(this);
			
		}
	}
	
	@Override
	public void turnTick(EntityPixelmon pixelmon1, EntityPixelmon target) throws Exception {
		elapsedTurns++;
	}
	
}
