package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.battles.status.Perish;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyPerish extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		if (checkChance()) {
			for (StatusBase a : target.status)
				if (a.type == StatusType.Perish) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has already heard the song!");
					return;
				}
			for (StatusBase b : user.status)
				if (b.type == StatusType.Perish) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getOwner() + " has already heard the song!");
					return;
				}

			user.status.add(new Perish());
			ChatHandler.sendBattleMessage(user.getOwner(), user.getName() + " heard the Perish Song!");
			target.status.add(new Perish());
			ChatHandler.sendBattleMessage(target.getOwner(), target.getName() + " heard the Perish Song!");
		}

	}
}
