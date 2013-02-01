package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class TrickRoom extends StatusEffectBase {

	public TrickRoom() {
		super(StatusEffectType.TrickRoom, false, false, false);
		this.applyStage = ApplyStage.Priority;
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception {
		for (int i = 0; i < user.battleController.battleStatusList.size(); i++) {
			if (user.battleController.battleStatusList.get(i).type == type) {
				user.battleController.battleStatusList.remove(i);
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The room returns to normal....  Or is it!!!???");
				return;
			}
		}
		user.battleVariables.set(type, 5);
		user.battleController.battleStatusList.add(this);
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (user.battleVariables.get(type) == 0) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The room returns to normal....  Or is it!!!???");
			user.status.remove(this);
		}
		user.battleVariables.decrement(type);
	}

	@Override
	public boolean participantMovesFirst(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (user.stats.Speed * user.battleStats.SpeedModifier > target.stats.Speed * target.battleStats.SpeedModifier)
			return false;
		else if (target.stats.Speed * target.battleStats.SpeedModifier > user.stats.Speed * user.battleStats.SpeedModifier)
			return true;
		else {
			if (RandomHelper.getRandomNumberBetween(0, 2) >= 1)
				return false;
			else
				return true;
		}
	}

}
