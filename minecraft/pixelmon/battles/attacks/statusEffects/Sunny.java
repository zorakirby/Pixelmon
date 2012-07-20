package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;

import net.minecraft.src.ModLoader;

public class Sunny extends StatusEffectBase {

	private int turnCount = -1;
	public Sunny() {
		super(StatusEffectType.Sunny, false, false, true);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			if (!user.getOwner().worldObj.isDaytime()) {
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), "There's no sun at night!");
				return;
			}
			for (StatusEffectBase e : user.getStatus())
				if (e.type == StatusEffectType.Sunny) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It's already sunny!");
					return;
				}

			turnCount=5;
			target.getStatus().add(this);
			user.getStatus().add(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " makes the sun shine more brightly!");

		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");

	}

	@Override
	public double adjustDamage(Attack a, double damage, PixelmonEntityHelper user, PixelmonEntityHelper target, double crit) {
		if (a.attackType == EnumType.Fire)
			return damage *= 1.5;
		else if (a.attackType == EnumType.Water)
			return damage*=0.5;
		else
			return damage;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (turnCount == 0) {
			user.getStatus().remove(this);
		}
		turnCount--;
	}
}
