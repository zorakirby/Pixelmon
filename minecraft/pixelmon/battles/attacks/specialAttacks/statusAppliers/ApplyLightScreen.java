package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.LightScreen;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplyLightScreen extends StatusApplierBase {

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception{
		if (checkChance()) {
			if (user.status.contains(this)) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " already has a lightscreen!");
				return;
			}
			user.status.add(new LightScreen());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " has put up a screen of shimmering light!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " failed!");
	}
}
