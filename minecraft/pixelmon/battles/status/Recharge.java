package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectBase;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Recharge extends StatusBase {
	int turnWait;
	int turnsWaited = 0;
	
	public Recharge(int turnWait) {
		super(StatusType.Recharge, false, true, false);
		this.turnWait = turnWait;
	}
	
	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if(turnsWaited == turnWait){
			target.status.remove(this);
			return true;
		}
		else{
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is recharging!");
		turnsWaited++;
		return false;
		}
		
	}
}