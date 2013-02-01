package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class LightScreen extends StatusEffectBase {

	public LightScreen() {
		super(StatusEffectType.LightScreen, false, false, true);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception{

		if (checkChance()) {
			if (user.status.contains(this)) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " already has a lightscreen!");
				return;
			}
			user.status.add(this);
			user.battleVariables.set(type, 5);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " has put up a screen of shimmering light!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public double adjustDamage(Attack a, double damage, EntityPixelmon user, EntityPixelmon target, double crit) throws Exception{
		if (a.baseAttack.attackCategory == Attack.ATTACK_SPECIAL)
			return damage / 2;
		return damage;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception{
		if (user.battleVariables.get(type) == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + "'s Lightscreen wears off!");
			user.status.remove(this);
		}
		user.battleVariables.decrement(type);
	}
}
