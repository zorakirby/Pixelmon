package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Acupressure extends SpecialAttackBase {

	public Acupressure(Value... values) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception{
		boolean handled = false;
		while (!handled) {
			int selection = (new Random()).nextInt(7);
			switch (selection) {
			case 0:
				if (user.battleStats.getAttackModifier() < 400) {
					user.battleStats.IncreaseAttack(2);
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Attack was raised sharply!");
					handled = true;
				}
				break;
			case 1:
				if (user.battleStats.getDefenceModifier() < 400) {
					user.battleStats.IncreaseDefence(2);
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Defence was raised sharply!");
					handled = true;
				}
				break;
			case 2:
				if (user.battleStats.getSpeedModifier() < 400) {
					user.battleStats.IncreaseSpeed(2);
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Speed was raised sharply!");
					handled = true;
				}
				break;
			case 3:
				if (user.battleStats.getSpecialAttackModifier() < 400) {
					user.battleStats.IncreaseSpecialAttack(2);
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Special Attack was raised sharply!");
					handled = true;
				}
				break;
			case 4:
				if (user.battleStats.getSpecialDefenceModifier() < 400) {
					user.battleStats.IncreaseSpecialDefence(2);
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Special Defence was raised sharply!");
					handled = true;
				}
				break;
			case 5:
				if (user.battleStats.getAccuracy() < 300) {
					user.battleStats.IncreaseAccuracy(2);
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Accuracy was raised sharply!");
					handled = true;
				}
				break;
			case 6:
				if (user.battleStats.getEvasion() < 300) {
					user.battleStats.IncreaseEvasion(2);
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Evasion was raised sharply!");
					handled = true;
				}
				break;
			}
		}
		return false;
	}
}
