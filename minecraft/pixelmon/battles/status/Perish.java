package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Perish extends StatusBase {

	int effectTurns = 0;

	public Perish() {
		super(StatusType.Perish, false, false, false);
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {

		if (effectTurns == 3) {
			if (!target.isDead) {
				target.attackEntityFrom(DamageSource.causeMobDamage(user), target.func_110143_aJ());
				ChatHandler.sendBattleMessage(target.getOwner(), user.getOwner(), "Perish Song struck " + target.getNickname() + "!");
			}
			if (!user.isDead) {
				user.attackEntityFrom(DamageSource.causeMobDamage(user), target.func_110143_aJ());
				ChatHandler.sendBattleMessage(target.getOwner(), user.getOwner(), "Perish Song struck " + user.getNickname() + "!");
			}
		}
		effectTurns++;

	}

}
