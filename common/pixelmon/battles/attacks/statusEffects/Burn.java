package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.*;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
public class Burn extends StatusEffectBase {

	public Burn() {
		super(StatusEffectType.Burn, true, false, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {

		if (checkChance()) {
			if (target.type.contains(EnumType.Fire)){
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Burn) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already burnt!");
					return;
				}
			target.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has been burnt!");
		}
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is hurt by its burn!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 8));
	}

	@Override
	public boolean ClearsOnBattleEnd() {
		return false;
	}
}
