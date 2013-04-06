package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Confusion;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyConfusion extends StatusApplierBase {
	private int effectTurns = -1;

	@Override
	public void ApplyEffect(Attack attack, double crit, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (checkChance()) {
			for (StatusBase e : target.status)
				if (e.type == StatusType.Confusion) {
					return;
				}
			target.status.add(new Confusion());
			effectTurns = (new Random()).nextInt(4) + 1;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " has become confused!");
		}
	}
}
