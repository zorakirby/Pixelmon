package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.SmackedDown;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

public class SmackDown extends SpecialAttackBase {

	public SmackDown() {
		super(SpecialAttackType.SmackDown, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		target.status.add(new SmackedDown());
		for (int i = 0; i < target.status.size(); i++) {
			StatusEffectBase s = target.status.get(i);
			if (s.type == StatusEffectType.Flying) {
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " got knocked out of the sky!");
				target.status.remove(s);
			}
		}
		return false;
	}

}
