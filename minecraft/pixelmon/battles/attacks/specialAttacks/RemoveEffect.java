package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectBase;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class RemoveEffect extends EffectBase {
	StatusType removeType;

	public RemoveEffect(StatusType removeType) {
		super(ApplyStage.End, false);
		this.removeType = removeType;
	}

	@Override
	public void ApplyEffect(Attack attack, EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		for (StatusBase e : target.status) {
			if (e.type == removeType) {
				target.status.remove(e);
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " removed " + target.getNickname() + "'s " + e.type.toString() + "!");
			}

		}
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {
		return false;
	}

}
