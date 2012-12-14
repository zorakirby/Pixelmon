package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class Leech extends StatusEffectBase {

	private int effectTurns;

	public Leech() {
		super(StatusEffectType.Leech, true, false, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Leech) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already seeded!");
					return;
				}
			target.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " has planted a seed!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " drains health from " + user.getName());
		int dmg = (int) (((float) user.getMaxHealth()) / 8);
		if (user.getHealth() < 16)
			dmg = 1;
		user.attackEntityFrom(DamageSource.causeMobDamage(user), dmg);
		target.setEntityHealth(target.getHealth() + dmg);
	}
}
