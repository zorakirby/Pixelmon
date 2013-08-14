package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.MeanLook;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyMeanLook extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			if (target.hasStatus(StatusType.MeanLook))
			{
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The move failed!");
				return;
			}
			target.status.add(new MeanLook(user));
				
	}

}
