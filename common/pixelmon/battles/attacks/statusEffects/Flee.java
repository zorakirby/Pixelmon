package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.BattleRegistry;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;

public class Flee extends StatusEffectBase {

	public Flee() {
		super(StatusEffectType.Flee, false, false, true);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName()+ " runs away!");

		target.aggression = Aggression.passive;
		user.aggression = Aggression.passive;

		target.battleController.endBattleWithoutXP();
		

	}

	@Override
	public boolean canAttackThisTurn(EntityPixelmon user, EntityPixelmon target) {
		return false;
	}
}
