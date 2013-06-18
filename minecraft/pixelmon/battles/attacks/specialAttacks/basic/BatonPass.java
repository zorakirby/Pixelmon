package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusBase;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.BattleStats;

public class BatonPass extends SpecialAttackBase {

	public BatonPass() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {

		if (user.getOwner() == null)
			return true;
		if (user.getStorage().countAblePokemon() > 1) {
			int id = user.getStorage().getNextPokemonId(user.getPokemonId());
			ChatHandler.sendBattleMessage(user.getOwner(), user.getNickname() + " passes a baton!");
			ArrayList<StatusBase> statusCopy = user.status;
			BattleStats battleStatsCopy = user.battleStats;
			EntityPixelmon newUser = user.battleController.SwitchPokemon(user, id);
			for (StatusBase s : statusCopy)
				newUser.status.add(s);
			newUser.battleStats.copyStats(battleStatsCopy);
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), "There are no pokemon left to switch to!");

		return true;
	}
}
