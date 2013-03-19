package pixelmon.battles.status;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectBase;
import pixelmon.battles.attacks.specialAttacks.ModifierBase;
import pixelmon.battles.attacks.specialAttacks.ModifierType;
import pixelmon.battles.attacks.specialAttacks.StatsEffect;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Mist extends StatusBase {
	int effectTurns = 0;

	public Mist() {
		super(StatusType.Mist, false, true, false);
		effectTurns = 0;
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception {
		if (a.baseAttack.attackCategory == Attack.ATTACK_STATUS) {
			for (EffectBase e : a.baseAttack.effects) {
				if (e instanceof StatsEffect) {
					for (ModifierBase m : (((StatsEffect) e).modifiers)) {
						if (m.type == ModifierType.User)
							return false;
					}
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " is protected by the mist!");
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) {
		effectTurns++;
		if (effectTurns == 5) {
			user.status.remove(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The mist wore off!");
		}
	}

}
