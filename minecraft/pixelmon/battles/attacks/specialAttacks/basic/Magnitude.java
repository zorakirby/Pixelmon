package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Magnitude extends SpecialAttackBase {

	public Magnitude(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {

		RandomHelper random = new RandomHelper();
		int i = random.getRandomNumberBetween(1, 100);
		if (i <= 5) {
			a.baseAttack.basePower = 10;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 4!");
		} else if (i <= 15 && i > 5) {
			a.baseAttack.basePower = 30;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 5!");
		} else if (i <= 35 && i > 15) {
			a.baseAttack.basePower = 50;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 6!");
		} else if (i <= 65 && i > 35) {
			a.baseAttack.basePower = 70;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 7!");
		} else if (i <= 85 && i > 65) {
			a.baseAttack.basePower = 90;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 8!");
		} else if (i <= 95 && i > 85) {
			a.baseAttack.basePower = 110;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 9!");
		} else if (i <= 100 && i > 95) {
			a.baseAttack.basePower = 150;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Magnitude 10!");
		}
		return false;

	}

}
