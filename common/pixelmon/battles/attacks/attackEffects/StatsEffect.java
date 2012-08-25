package pixelmon.battles.attacks.attackEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.EffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

public class StatsEffect extends EffectBase{
	
	private StatsEffectType type;
	private boolean isUser = false;
	
	public StatsEffect(StatsEffectType type, int value, boolean isUser) {
		super(EffectType.Stats, ApplyStage.End, false);
		this.type = type;
		this.value = value;
		this.isUser = isUser;
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		PixelmonEntityHelper effected = target;
		if (isUser) effected = user;
		if (checkChance()) {
			if (type == StatsEffectType.Accuracy) {
				if (value > 0) {if (effected.battleStats.IncreaseAccuracy(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName()+ "'s accuracy was increased!");}
				else if (effected.battleStats.DecreaseAccuracy(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s accuracy was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.Evasion) {
				if (value > 0) {if (effected.battleStats.IncreaseEvasion(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s evasion was increased!");}
				else if (effected.battleStats.DecreaseEvasion(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s evasion was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");
				
			} else if (type == StatsEffectType.Attack) {
				if (value > 0) {if (effected.battleStats.IncreaseAttack(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s attack was increased!");}
				else if (effected.battleStats.DecreaseAttack(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s attack was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.Defence) {
				if (value > 0) {if (effected.battleStats.IncreaseDefence(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s defense was increased!");}
				else if (effected.battleStats.DecreaseDefence(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s defense was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.SpecialAttack) {
				if (value > 0) {if (effected.battleStats.IncreaseSpecialAttack(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s special attack was increased!");}
				else if (effected.battleStats.DecreaseSpecialAttack(Math.abs(value)))ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s special attack was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.SpecialDefence) {
				if (value > 0) {if (effected.battleStats.IncreaseSpecialDefence(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s special defense was increased!");}
				else if (effected.battleStats.DecreaseSpecialDefence(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s special defense was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.Speed) {
				if (value > 0) {if (effected.battleStats.IncreaseSpeed(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s speed was increased!");}
				else if (effected.battleStats.DecreaseSpeed(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s speed was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			}
		}	
	}

	@Override
	public boolean cantMiss() {
		if (isUser) return true;
		return false;
	}
	
	

}
