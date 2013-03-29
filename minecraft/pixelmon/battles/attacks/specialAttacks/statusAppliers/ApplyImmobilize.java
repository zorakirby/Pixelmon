package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.status.Immobilize;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyImmobilize extends StatusApplierBase {
	int value1, value2;
	boolean hasRange = false;

	public ApplyImmobilize(Value... values) {
		if (values != null && values[0] != null && values[0].value != -1) {
			hasRange = true;
			value1 = values[0].value;
			value2 = values[1].value;
		}
	}

	@Override
	public void ApplyEffect(Attack attack, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		for (StatusBase e : target.status)
			if (e.type == StatusType.Immobilize) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " already cannot escape!");
				return;
			}
		if (hasRange)
			target.status.add(new Immobilize(value1, value2));
		else
			target.status.add(new Immobilize());
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " cannot escape!");
	}

}
