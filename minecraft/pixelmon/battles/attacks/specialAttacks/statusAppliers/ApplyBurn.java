package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.status.Burn;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyBurn extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {

		if (checkChance()) {
			for (StatusBase e : target.status)
				if (e.type == StatusType.Burn) {
					return;
				}
			target.status.add(new Burn());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has been burnt!");
		}
	}

}
