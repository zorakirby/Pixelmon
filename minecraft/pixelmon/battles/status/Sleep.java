package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Sleep extends StatusPersist {

	int effectTurns = -1;
	boolean setTurns = false;

	public Sleep() {
		super(StatusType.Sleep, false, true, false);
		effectTurns = RandomHelper.getRandomNumberBetween(1, 4);
	}

	public Sleep(int i) {
		super(StatusType.Sleep, false, true, false);
		effectTurns = i;
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (effectTurns == 0)
		{
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " woke up!");
			user.status.remove(this);
			return true;
		}
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is still sleeping!");
		return false;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (effectTurns == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " wakes up!");
			user.status.remove(this);
			;
		}
		effectTurns--;

	}

	@Override
	public void writeToNBT(int i, NBTTagCompound nbt) throws Exception {
		super.writeToNBT(i, nbt);
		nbt.setInteger("StatusSleepTurns", effectTurns);
	}

	@Override
	public StatusBase restoreFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("StatusSleepTurns"))
			return new Sleep(nbt.getInteger("StatusSleepTurns"));
		else
			return new Sleep();
	}

}
