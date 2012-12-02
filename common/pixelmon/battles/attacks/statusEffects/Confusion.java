package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.*;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class Confusion extends StatusEffectBase {
	private int effectTurns = -1;

	public Confusion() {
		super(StatusEffectType.Confusion, false, true, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Confusion) {
					return;
				}
			target.status.add(this);
			effectTurns = (new Random()).nextInt(4) + 1;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has become confused!");
		}
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is confused...");
		if ((new Random()).nextInt(100) <= 50) {
			user.attackEntityFrom(DamageSource.causeMobDamage(user), calculateConfusionDamage(user));
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " hurt itself in its confusion");
			return false;
		}
		return true;
	}

	private int calculateConfusionDamage(EntityPixelmon user) {
		double stab = 1;
		double type = EnumType.getTotalEffectiveness(user.type, EnumType.Normal);
		double critical = 1;
		double rand = ((new Random()).nextDouble() + 85.0) * 15.0 / 100.0;
		double modifier = stab * type * critical * rand;
		double attack = ((double) user.stats.Attack) * ((double) user.battleStats.AttackModifier) / 100;
		double defence = ((double) user.stats.Defence) * ((double) user.battleStats.DefenceModifier) / 100;
		double Damage = ((2 * user.getLvl().getLevel() + 10) / 250 * (attack / defence) * 40 + 2) * modifier;

		return (int) Math.floor(Damage);
				//(int) Math.round(Damage);
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) {
		if (effectTurns == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " snaps out of confusion!");
			user.status.remove(this);
		}
		effectTurns--;
	}
}
