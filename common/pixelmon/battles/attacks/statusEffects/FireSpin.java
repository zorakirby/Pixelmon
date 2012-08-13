package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class FireSpin extends StatusEffectBase {
	int effectTurns = -1;

	public FireSpin() {
		super(StatusEffectType.FireSpin, true, false, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			if (target.getType().contains(EnumType.Fire)){
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.FireSpin) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already spinning in fire!");
					return;
				}
			effectTurns = RandomHelper.getRandomNumberBetween(4, 5);
			target.status.add(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is trapped in a vortex!");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}


	@Override
	public void applyRepeatedEffect(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is trapped in a vortex and takes damage!");
		user.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), (int) (((float) user.getMaxHealth()) / 16));
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (effectTurns == 0) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " breaks free of the swirling vortex!");
			user.status.remove(this);
		}
		effectTurns--;
	}
	public boolean stopsSwitching() {
		return true;
	}

}
