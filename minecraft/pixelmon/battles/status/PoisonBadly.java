package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class PoisonBadly extends StatusPersist {

	int poisonSeverity = 1;

	public PoisonBadly() {
		super(StatusType.PoisonBadly, true, false, false);
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is hurt by poison!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 16f * poisonSeverity));
		poisonSeverity++;
	}



	@Override
	public boolean clearsOnBattleEnd() {
		poisonSeverity = 1;
		return false;
	}

	@Override
	public StatusBase restoreFromNBT(NBTTagCompound nbt) {
		return new PoisonBadly();
	}
}
