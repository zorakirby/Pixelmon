package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.specialAttacks.SpecialAttackType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;



public class WaitAfter extends StatusEffectBase {

	private int numTurns;
	private int turnCount;
	public WaitAfter(int value) {
		super(StatusEffectType.WaitAfter, false, true, false);
		numTurns = value;
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		turnCount = 0;
		user.status.add(this);
	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName()+ " is recharging!");
		return false;
	}

	@Override
	public double adjustDamage(Attack a, double damage, PixelmonEntityHelper user, PixelmonEntityHelper target, double crit) {
		return 0;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		turnCount++;
		if (turnCount == numTurns) user.status.remove(this);
	}

}
