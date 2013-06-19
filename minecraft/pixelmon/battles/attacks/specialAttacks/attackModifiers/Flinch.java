package pixelmon.battles.attacks.specialAttacks.attackModifiers;


import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.Value;
import pixelmon.battles.controller.BattleController;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
public class Flinch extends AttackModifierBase {

	public Flinch() {
		super(ApplyStage.End, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception{
		if (checkChance()){
			return true;
		}
		return false;
	}

}
