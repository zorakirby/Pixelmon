package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class HealOther extends SpecialAttackBase {
	int increment;
	public HealOther(Value... values) {
		super(ApplyStage.During, false);
		increment = values[0].value;
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			
			double restoration = target.func_110143_aJ()*(double)(increment/100);
			target.heal((int)Math.ceil(restoration));
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " healed " +
																			  target.getNickname() + "!");
			return false;
			
	}

}
