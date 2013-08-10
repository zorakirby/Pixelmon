package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Magnitude extends SpecialAttackBase {

	public Magnitude() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		int i = RandomHelper.getRandomNumberBetween(1, 100);
		if (i <= 5) {
			a.movePower = 10;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 4!");
		} else if (i <= 15 && i > 5) {
			a.movePower = 30;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 5!");
		} else if (i <= 35 && i > 15) {
			a.movePower = 50;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 6!");
		} else if (i <= 65 && i > 35) {
			a.movePower = 70;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 7!");
		} else if (i <= 85 && i > 65) {
			a.movePower = 90;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 8!");
		} else if (i <= 95 && i > 85) {
			a.movePower = 110;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 9!");
		} else if (i <= 100 && i > 95) {
			a.movePower = 150;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 10!");
		}
		return false;

	}

}
