package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Sunny extends StatusEffectBase {

	public Sunny() {
		super(StatusEffectType.Sunny, false, false, true);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (checkChance()) {
			if (!user.getOwner().worldObj.isDaytime()) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "There's no sun at night!");
				return;
			}
			for (StatusEffectBase e : user.status)
				if (e.type == StatusEffectType.Sunny) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's already sunny!");
					return;
				}

			target.battleVariables.set(type, 5);
			user.battleVariables.set(type, 5);
			target.status.add(this);
			user.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " makes the sun shine more brightly!");

		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");

	}

	@Override
	public double adjustDamage(Attack a, double damage, EntityPixelmon user, EntityPixelmon target, double crit) throws Exception {
		if (a.baseAttack.attackType == EnumType.Fire)
			return damage *= 1.5;
		else if (a.baseAttack.attackType == EnumType.Water)
			return damage *= 0.5;
		else
			return damage;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (user.battleVariables.get(type) == 0) {
			user.status.remove(this);
		}
		user.battleVariables.decrement(type);
	}
}
