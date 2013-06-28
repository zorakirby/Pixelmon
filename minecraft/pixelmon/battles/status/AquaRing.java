package pixelmon.battles.status;

import net.minecraft.util.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class AquaRing extends StatusBase {

	public AquaRing() {
		super(StatusType.AquaRing, true, false, false);
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is healed by it's ring");
		int healamount = (int) (((float) user.getMaxHealth()) / 16f);
		user.healEntityBy(healamount);
	}
}
