package pixelmon.battles.status;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.DamageSource;
import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Confusion extends StatusBase {
	private int effectTurns = -1;

	public Confusion() {
		super(StatusType.Confusion, false, true, false);
		effectTurns = (new Random()).nextInt(4) + 1;
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is confused...");
		if ((new Random()).nextInt(100) <= 50) {
			user.attackEntityFrom(DamageSource.causeMobDamage(user), calculateConfusionDamage(user));
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " hurt itself in its confusion");
			return false;
		}
		return true;
	}

	private int calculateConfusionDamage(EntityPixelmon user) throws Exception {
		double stab = 1;
		double type = 1;
		double critical = 1;
		double rand = ((double) RandomHelper.getRandomNumberBetween(85, 100)) / 100;
		double modifier = stab * type * critical * rand;
		double attack = ((double) user.stats.Attack) * ((double) user.battleStats.getAttackModifier()) / 100;
		double defence = ((double) user.stats.Defence) * ((double) user.battleStats.getDefenceModifier()) / 100;
		double Damage = ((2 * user.getLvl().getLevel() + 10) / 250 * (attack / defence) * 40 + 2) * modifier;

		return (int) Math.floor(Damage);
		// (int) Math.round(Damage);
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (effectTurns == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " snaps out of confusion!");
			user.status.remove(this);
		}
		effectTurns--;
	}
}
