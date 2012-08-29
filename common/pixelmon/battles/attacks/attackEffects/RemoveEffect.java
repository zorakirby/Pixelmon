package pixelmon.battles.attacks.attackEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

public class RemoveEffect extends EffectBase {
	StatusEffectType removeType;
	public RemoveEffect(StatusEffectType removeType) {
		super(EffectType.Remove, ApplyStage.End, false);
		this.removeType= removeType;
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		for (StatusEffectBase e:target.status){
			if (e.type == removeType) {
				target.status.remove(e);
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), user
						.getName() + " removed " + target.getName() + "'s "+ e.type.toString() +"!");
			}
			
		}
	}

	@Override
	public boolean cantMiss() {
		return false;
	}

}
