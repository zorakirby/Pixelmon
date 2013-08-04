package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.Sunny;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class ApplySunny extends StatusApplierBase {

	@Override
	public void ApplyEffect(Attack a, double crit, EntityPixelmon user,
			EntityPixelmon target, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {

			if (user.battleController.getWeather() instanceof Sunny)
			{
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The move failed!");
				return;
			}
			user.battleController.checkAndRemoveWeather();
			user.battleController.addGlobalStatus(new Sunny());
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The sunlight turned harsh!");
	}

}
