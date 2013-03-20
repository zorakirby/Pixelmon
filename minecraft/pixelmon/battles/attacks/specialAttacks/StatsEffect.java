package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.EffectBase;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.StatsType;

public class StatsEffect extends EffectBase {

	private StatsType type;
	private boolean isUser = false;

	public StatsEffect(StatsType type, int value, boolean isUser) {
		super(ApplyStage.End, false);
		this.type = type;
		this.value = value;
		this.isUser = isUser;
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		EntityPixelmon effected = target;
		if (isUser)
			effected = user;
		if (checkChance()) {
			if (type == StatsType.Accuracy) {
				if (value > 0) {
					if (effected.battleStats.IncreaseAccuracy(value))
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s accuracy was increased!");
				} else if (effected.battleStats.DecreaseAccuracy(Math.abs(value)))
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s accuracy was decreased!");
				else
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsType.Evasion) {
				if (value > 0) {
					if (effected.battleStats.IncreaseEvasion(value))
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s evasion was increased!");
				} else if (effected.battleStats.DecreaseEvasion(Math.abs(value)))
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s evasion was decreased!");
				else
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsType.Attack) {
				if (value > 0) {
					if (effected.battleStats.IncreaseAttack(value))
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s attack was increased!");
				} else if (effected.battleStats.DecreaseAttack(Math.abs(value)))
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s attack was decreased!");
				else
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsType.Defence) {
				if (value > 0) {
					if (effected.battleStats.IncreaseDefence(value))
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s defense was increased!");
				} else if (effected.battleStats.DecreaseDefence(Math.abs(value)))
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s defense was decreased!");
				else
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsType.SpecialAttack) {
				if (value > 0) {
					if (effected.battleStats.IncreaseSpecialAttack(value))
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s special attack was increased!");
				} else if (effected.battleStats.DecreaseSpecialAttack(Math.abs(value)))
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s special attack was decreased!");
				else
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsType.SpecialDefence) {
				if (value > 0) {
					if (effected.battleStats.IncreaseSpecialDefence(value))
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s special defense was increased!");
				} else if (effected.battleStats.DecreaseSpecialDefence(Math.abs(value)))
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s special defense was decreased!");
				else
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsType.Speed) {
				if (value > 0) {
					if (effected.battleStats.IncreaseSpeed(value))
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s speed was increased!");
				} else if (effected.battleStats.DecreaseSpeed(Math.abs(value)))
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), effected.getNickname() + "'s speed was decreased!");
				else
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It had no effect");

			}
		}
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {
		if (isUser)
			return true;
		return false;
	}

}
