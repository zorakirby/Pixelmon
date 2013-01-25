package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class FireSpin extends StatusEffectBase {

	public FireSpin() {
		super(StatusEffectType.FireSpin, true, false, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			if (target.type.contains(EnumType.Fire)) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.FireSpin) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already spinning in fire!");
					return;
				}
			target.battleVariables.set(type, RandomHelper.getRandomNumberBetween(4, 5));
			target.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is trapped in a vortex!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is trapped in a vortex and takes damage!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 16));
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) {
		if (user.battleVariables.get(type) == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " breaks free of the swirling vortex!");
			user.status.remove(this);
		}
		user.battleVariables.decrement(type);
	}

	public boolean stopsSwitching() {
		return true;
	}

}
