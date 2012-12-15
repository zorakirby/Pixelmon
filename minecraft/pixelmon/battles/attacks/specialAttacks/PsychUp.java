package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PsychUp extends SpecialAttackBase {

	public PsychUp() {
		super(SpecialAttackType.PsychUp, ApplyStage.During, false);

	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		
		
		
		
		user.battleStats.AttackModifier = target.battleStats.AttackModifier;
		user.battleStats.DefenceModifier = target.battleStats.DefenceModifier;
		user.battleStats.SpecialAttackModifier = target.battleStats.SpecialAttackModifier;
		user.battleStats.SpecialDefenceModifier = target.battleStats.SpecialDefenceModifier;
		user.battleStats.SpeedModifier = target.battleStats.SpeedModifier;
		ChatHandler.sendBattleMessage(user.getOwner(), user.getName() + " copied the foe's stat changes!");
		ChatHandler.sendBattleMessage(target.getOwner(), "The foe copied " + target.getName() + "'s stat changes");
		return true;
		
	}

}
