package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Yawn extends StatusEffectBase {
	int turnCount = 0;
	public Yawn() {
		super(StatusEffectType.Yawn, true, false, false);

	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			ArrayList<String> attackList) {
		
			target.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " became drowsy!");
	
		}
	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {

		if(turnCount == 0)
			turnCount++;
		
		
		else if (turnCount == 1){
			user.status.add(new Sleep());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), 
					target.getName() + " fell asleep!");
			user.status.remove(this);
		}
	}


}
