package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.status.Poison;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class ApplyPoison extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList)throws Exception {
		if (checkChance()) {
			if (target.type.contains(EnumType.Poison)){
				return;
			}
			for (StatusBase e : target.status)
				if (e.type == StatusType.Poison || e.type == StatusType.PoisonBadly) {
					return;
				}
			target.status.add(new Poison());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has been poisoned!");
		}
	}

}
