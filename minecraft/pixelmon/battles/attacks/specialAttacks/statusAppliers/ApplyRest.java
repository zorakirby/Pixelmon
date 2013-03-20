package

pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Sleep;
import pixelmon.battles.status.StatusType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyRest extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		user.status.clear();
		user.heal(user.getMaxHealth());
		user.status.add(new Sleep(3));
	}
}
