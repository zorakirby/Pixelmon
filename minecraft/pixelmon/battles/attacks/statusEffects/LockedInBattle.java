package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class LockedInBattle extends StatusEffectBase {

	public LockedInBattle() {
		super(StatusEffectType.LockedInBattle, false, true, false);

	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			ArrayList<String> attackList) {
			
		if(target.isLockedInBattle)
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already trapped!");
			
			else{
				target.isLockedInBattle = true;
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " can no longer escape!");
				target.status.add(this);
				target.locker = user;
			}
		
	}
	
	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target){
		if(target.locker != user){
		   target.isLockedInBattle = false;
		   target.status.remove(this);
		}
			
		
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Turn end");
	}

}
