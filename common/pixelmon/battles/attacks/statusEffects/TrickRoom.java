package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

public class TrickRoom extends StatusEffectBase {
	private int effectTurns = -1;
	public TrickRoom() {
		super(StatusEffectType.TrickRoom, false, false, false);
		this.applyStage = ApplyStage.Priority;
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		for (int i=0; i < user.bc.battleStatusList.size(); i++){
			if (user.bc.battleStatusList.get(i).type == type){
				user.bc.battleStatusList.remove(i);
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), "The room returns to normal....  Or is it!!!???");
				return;
			}
		}
		effectTurns = 5;
		user.bc.battleStatusList.add(this);
	}
	
	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (effectTurns == 0) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), "The room returns to normal....  Or is it!!!???");
			user.status.remove(this);
		}
		effectTurns--;
	}
	
	@Override
	public boolean pokemon1MovesFirst(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (user.stats.Speed * user.battleStats.SpeedModifier > target.stats.Speed
				* target.battleStats.SpeedModifier)
			return false;
		else if (target.stats.Speed * target.battleStats.SpeedModifier > user.stats.Speed
				* user.battleStats.SpeedModifier)
			return true;
		else {
			if (RandomHelper.getRandomNumberBetween(0, 2) >= 1)
				return false;
			else
				return true;
		}
	}

}
