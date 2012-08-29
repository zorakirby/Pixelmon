package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;



public class Sleep extends StatusEffectBase {

	int effectTurns = -1;

	public Sleep() {
		super(StatusEffectType.Sleep, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Sleep) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already asleep!");
					return;
				}

			target.status.add(this);

			effectTurns = RandomHelper.getRandomNumberBetween(1, 4);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has fallen asleep!");

		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is still sleeping!");
		return false;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (effectTurns == 0) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " wakes up!");
			user.status.remove(this);
		}
		effectTurns--;

	}
	@Override
	public boolean ClearsOnBattleEnd() {
		return false;
	}
}
