package pixelmon.entities.pokeballs.captures;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumPokeballs;

public class CaptureLevelBall extends CaptureBase {

	public CaptureLevelBall() {
		super(EnumPokeballs.LevelBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		double ballBonus = pokeball.getBallBonus();
		if (mode == Mode.battle) {
			BattleController bc = BattleRegistry.getBattle((EntityPlayer) thrower);
			int ownerPokemonLevel = 0;
			for (BattleParticipant p : bc.participants) {
				if (p instanceof PlayerParticipant && p.getEntity() == thrower)
					ownerPokemonLevel = p.currentPokemon().getLvl().getLevel();
			}
			if (ownerPokemonLevel > 1 * p2.getLvl().getLevel())
				ballBonus = 2;
			if (ownerPokemonLevel > 2 * p2.getLvl().getLevel())
				ballBonus = 4;
			if (ownerPokemonLevel > 4 * p2.getLvl().getLevel())
				ballBonus = 8;
		}
		return ballBonus;
	}

}
