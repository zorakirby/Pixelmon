package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class PoisonBadly extends StatusEffectBase {

	double poisonAmount = 1 / 16;

	public PoisonBadly() {
		super(StatusEffectType.PoisonBadly, true, false, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			if (target.type.contains(EnumType.Poison)) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Poison || e.type == StatusEffectType.PoisonBadly) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already poisoned!");
					return;
				}
			poisonAmount = 1 / 16;
			target.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has been badly poisoned!");
		} else ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is hurt by poison!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) * poisonAmount));
		poisonAmount += 1 / 16;
	}

	@Override
	public boolean clearsOnBattleEnd() {
		return false;
	}
}
