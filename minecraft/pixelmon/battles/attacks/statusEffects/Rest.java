package 

pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Rest extends StatusEffectBase {
	int effectTurns = 0;
	public Rest() {
		super(StatusEffectType.Rest, true, false, true);

	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			ArrayList<String> attackList) {
		
	if (effectTurns == 0)
		{
			user.status.clear();
			user.heal(user.getMaxHealth());
			user.status.add(new Sleep());
			effectTurns++;
		}
		
			
	}

	public boolean ApplyRepeatedEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		
		if(effectTurns == 1)
			effectTurns++;
		
		else if(effectTurns == 2)
			effectTurns++;
		
		else if(effectTurns == 3)
		user.status.remove(StatusEffectType.Sleep);
		user.status.remove(this);
		
		return false;
		
		
	}		
}
