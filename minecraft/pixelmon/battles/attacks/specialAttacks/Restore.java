package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Restore extends SpecialAttackBase {
		int HealIndex;
	public Restore(int HealIndex) {
		super(SpecialAttackType.Restore, ApplyStage.During, false);
		this.HealIndex = HealIndex;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) {
		ChatHandler.sendBattleMessage(user.getOwner(), "Before switch");
		switch(HealIndex){
		
		case 1 :
			user.heal((user.getMaxHealth()/2));
			System.out.println(user.getMaxHealth()/2);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " restored Health!");
			break;
		
		
		
		}
		

		return false;
	}

}
