package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Leech extends StatusBase {

	public Leech() {
		super(StatusType.Leech, true, false, false);
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " drains health from " + user.getNickname());
		int dmg = (int) (((float) user.getMaxHealth()) / 8);
		if (user.getHealth() < 16)
			dmg = 1;
		user.attackEntityFrom(DamageSource.causeMobDamage(user), dmg);
		target.healEntityBy(dmg);
	}
}
