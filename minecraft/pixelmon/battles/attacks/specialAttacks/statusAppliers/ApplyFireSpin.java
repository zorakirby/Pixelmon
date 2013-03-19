package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.RandomHelper;
import pixelmon.battles.status.FireSpin;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class ApplyFireSpin extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (checkChance()) {
			if (target.type.contains(EnumType.Fire)) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			for (StatusBase e : target.status)
				if (e.type == StatusType.FireSpin) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is already spinning in fire!");
					return;
				}
			target.status.add(new FireSpin());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " is trapped in a vortex!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " failed!");
	}
}
