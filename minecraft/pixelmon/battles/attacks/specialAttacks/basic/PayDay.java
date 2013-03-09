package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PayDay extends SpecialAttackBase {

	public PayDay(ApplyStage a, boolean persists) {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		user.battleController.money += user.getLvl().getLevel() * 5;
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Coins are scattered all around");
		return false;
	}

}
