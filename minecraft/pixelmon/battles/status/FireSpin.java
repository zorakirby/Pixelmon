package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class FireSpin extends StatusBase {

	int effectTurns;

	public FireSpin() {
		super(StatusType.FireSpin, true, false, false);
		effectTurns = RandomHelper.getRandomNumberBetween(4, 5);
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is trapped in a vortex and takes damage!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 16));
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (effectTurns == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " breaks free of the swirling vortex!");
			user.status.remove(this);
		}
		effectTurns--;
	}

	public boolean stopsSwitching() {
		return true;
	}

}
