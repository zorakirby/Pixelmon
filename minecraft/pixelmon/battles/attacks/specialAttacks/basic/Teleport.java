package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.AI.AITeleportAway;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Teleport extends SpecialAttackBase {

	public Teleport() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		if ((user.getOwner() != null || user.getTrainer() != null) && (target.getOwner() != null || target.getTrainer() != null)) {
			ChatHandler.sendBattleMessage(user.getOwner(), "Can't teleport from a trainer battle!");
			return true;
		}

		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " teleported away!");
		user.battleController.endBattleWithoutXP();
		while (!AITeleportAway.teleportRandomly(user, new Random())){};
		return true;
	}
}
