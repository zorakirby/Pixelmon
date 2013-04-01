package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Restore extends SpecialAttackBase {
	int HealIndex;

	public Restore(Value... values) {
		super(ApplyStage.During, false);
		this.HealIndex = values[0].value;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		ChatHandler.sendBattleMessage(user.getOwner(), "Before switch");
		switch (HealIndex) {
		case 1:
			user.heal((user.getMaxHealth() / 2));
			System.out.println(user.getMaxHealth() / 2);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " restored Health!");
			break;
		}
		return false;
	}

}
