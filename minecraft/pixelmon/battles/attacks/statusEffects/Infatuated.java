package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Infatuated extends StatusEffectBase {

	EntityPixelmon originalTarget; 
	public Infatuated() {
		super(StatusEffectType.Infatuated, false, true, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Infatuated) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " is already in love!");
					return;
				}
			target.status.add(this);
			originalTarget = user;
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has fallen in love!");
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		if (originalTarget !=target){
			user.status.remove(this);
			return true;
		}
		
		if (RandomHelper.getRandomNumberBetween(0, 100) <= 50) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is in love!");
			return false;
		} else {
			return true;
		}
	}
}
