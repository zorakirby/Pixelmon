package pixelmon.battles.attacks.attackModifiers;


import pixelmon.battles.BattleController;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
public class FlinchAttackModifier extends AttackModifierBase {

	public FlinchAttackModifier() {
		super(AttackModifierType.Flinch, ApplyStage.End, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) {
		if (checkChance()){
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " flinched!");
			return true;
		}
		return false;
	}

}
