package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Burn extends StatusPersist {

	public Burn() {
		super(StatusType.Burn, true, false, false);
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is hurt by its burn!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 8));
	}

	@Override
	public StatusBase restoreFromNBT(NBTTagCompound nbt) {
		return new Burn();
	}
}
