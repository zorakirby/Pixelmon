package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Freeze extends StatusPersist {

	public Freeze() {
		super(StatusType.Freeze, false, true, false);
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (RandomHelper.getRandomNumberBetween(0, 100) <= 20) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " breaks free from the ice!");
			user.status.remove(this);
			return true;
		} else {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is frozen solid!");
			return false;
		}
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception {
		return false;
	}

	@Override
	public StatusBase restoreFromNBT(NBTTagCompound nbt) {
		return new Freeze();
	}

}
