package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Protect extends StatusEffectBase {

	public Protect() {
		super(StatusEffectType.Protect, false, false, true);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (user.hasStatus(type))
			return;
		float chance = 1f;
		for (int i = attackList.size() - 2; i >= 0; i--) {
			if (attackList.get(i) == "Protect")
				chance *= 0.5f;
			else
				break;
		}
		if (chance < 0.125f)
			chance = 0.125f;

		if ((new Random()).nextInt(100) <= chance * 100) {
			user.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is readying itself!");

		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " protects itself!");
		return true;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		user.status.remove(this);
	}
}
