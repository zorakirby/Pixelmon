package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Cursed extends StatusBase {

	public Cursed() {
		super(StatusType.Cursed, true, false, false);
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is afflicted by the curse!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 4));
	}
}