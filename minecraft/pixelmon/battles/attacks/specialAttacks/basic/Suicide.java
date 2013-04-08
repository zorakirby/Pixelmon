package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Suicide extends SpecialAttackBase {

	public Suicide() {
		super(ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
	
		user.setEntityHealth(0);
		ChatHandler.sendBattleMessage(user.getOwner(),target.getOwner(), user.getNickname() + " blew itself up!");
		
		return false;
	}

	

}
