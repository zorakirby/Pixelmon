package pixelmon.battles.status;

import net.minecraft.util.DamageSource;
import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Clamped extends StatusBase {
	int turnsLeft;
	public Clamped() {
		super(StatusType.Clamped, true, false, false);
		turnsLeft = RandomHelper.getRandomNumberBetween(4, 5);
	}
	public boolean stopsSwitching() throws Exception {
		return true;
	}
	@Override
	public void applyRepeatedEffect(EntityPixelmon entityPixelmon, EntityPixelmon entityPixelmon2) throws Exception {

		if (turnsLeft == 0)
		{
			entityPixelmon.status.remove(this);
			ChatHandler.sendBattleMessage(entityPixelmon.getOwner(), entityPixelmon2.getOwner(), entityPixelmon.getNickname() + " was freed from clamp!");
			return;
		}
		entityPixelmon.attackEntityFrom(DamageSource.causeMobDamage(entityPixelmon2), entityPixelmon.getMaxHealth()/16);
		ChatHandler.sendBattleMessage(entityPixelmon.getOwner(), entityPixelmon2.getOwner(), entityPixelmon.getNickname() + " was hurt by clamp!");
		turnsLeft--;
	}
}
