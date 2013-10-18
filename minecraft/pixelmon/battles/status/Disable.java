package pixelmon.battles.status;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Disable extends StatusBase {
	Attack disabledMove;
	int effectiveTurns;
	int elapsedTurns = -1;
	public Disable(Attack attack) {
		super(StatusType.Disable, true, false, false);
		disabledMove = attack;
		effectiveTurns = RandomHelper.getRandomNumberBetween(4, 7);
	}
	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		
			if(effectiveTurns == elapsedTurns){
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + "'s " + 
			disabledMove.baseAttack.attackName + " is no longer disabled!");
			for(int i = 0; i < 4; i++){
				if (target.getMoveset().get(i) == disabledMove){
					target.getMoveset().get(i).setDisabled(false, target);
				}
			}						
			
			disabledMove.setDisabled(false, target);
			target.status.remove(this);
			
		}
	}
	
	@Override
	public void turnTick(EntityPixelmon pixelmon1, EntityPixelmon target) throws Exception {
		elapsedTurns++;
	}
	
}
