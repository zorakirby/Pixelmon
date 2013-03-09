package pixelmon.battles.status;

import net.minecraft.nbt.NBTTagCompound;

public abstract class StatusPersist extends StatusBase {

	public StatusPersist(StatusType type, boolean hasRepeatedEffect, boolean hasPreMoveEffect, boolean hasInMoveEffect) {
		super(type, hasRepeatedEffect, hasPreMoveEffect, hasInMoveEffect);
	}

	@Override
	public boolean clearsOnBattleEnd() throws Exception {
		return false;
	}

	public void writeToNBT(int i, NBTTagCompound nbt) throws Exception {
		nbt.setInteger("Status" + i, type.index);
	}

	public abstract StatusBase restoreFromNBT(NBTTagCompound nbt);
}
