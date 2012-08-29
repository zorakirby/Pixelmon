package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.*;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;


public class Confusion extends StatusEffectBase {
	private int effectTurns = -1;

	public Confusion() {
		super(StatusEffectType.Confusion, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Confusion) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already confused!");
					return;
				}
			target.status.add(this);
			effectTurns = (new Random()).nextInt(4) + 1;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has become confused!");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is confused...");
		if ((new Random()).nextInt(100) <= 50) {
			user.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), calculateConfusionDamage(user));
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " hurt itself in its confusion");
			return false;
		}
		return true;
	}

	private int calculateConfusionDamage(PixelmonEntityHelper user) {
		double stab = 1;
		double type = EnumType.getTotalEffectiveness(user.getType(), EnumType.Normal);
		double critical = 1;
		double rand = ((new Random()).nextDouble() + 85.0) * 15.0 / 100.0;
		double modifier = stab * type * critical * rand;
		double attack = ((double) user.stats.Attack) * ((double) user.battleStats.AttackModifier) / 100;
		double defence = ((double) user.stats.Defence) * ((double) user.battleStats.DefenceModifier) / 100;
		double Damage = ((2 * user.getLvl().getLevel() + 10) / 250 * (attack / defence) * 40 + 2) * modifier;

		return (int) Math.round(Damage);
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (effectTurns == 0) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " snaps out of confusion!");
			user.status.remove(this);
		}
		effectTurns--;
	}
}
