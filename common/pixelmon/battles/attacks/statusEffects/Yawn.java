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
		if(turnCount == 0){
			target.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " became drowsy!");
		}
		
		
		
		
		}
	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {

		turnCount++;
		if(turnCount == 1)
			turnCount++;
		
		else if (turnCount == 2)
			user.status.add(new Sleep());
			user.status.remove(this);
			
	}


}
