package pixelmon.entities.pokeballs.captures;

import net.minecraft.src.EntityPlayer;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumPokeballs;

public class CaptureLoveBall extends CaptureBase {

	public CaptureLoveBall() {
		super(EnumPokeballs.LoveBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		double ballBonus = type.getBallBonus();
		if (mode == Mode.battle) {
			BattleController bc = BattleRegistry.getBattle((EntityPlayer) thrower);
			boolean ownerPokemonIsMale = true;
			String ownerPokemonName = "";
			if (bc.participant1 instanceof PlayerParticipant && ((PlayerParticipant) bc.participant1).player == thrower) {
				ownerPokemonIsMale = ((PlayerParticipant) bc.participant1).currentPokemon().isMale;
				ownerPokemonName = ((PlayerParticipant) bc.participant1).currentPokemon().getName();
			} else if (bc.participant2 instanceof PlayerParticipant && ((PlayerParticipant) bc.participant2).player == thrower) {
				ownerPokemonIsMale = ((PlayerParticipant) bc.participant2).currentPokemon().isMale;
				ownerPokemonName = ((PlayerParticipant) bc.participant2).currentPokemon().getName();
			}
			if (ownerPokemonIsMale != p2.isMale && ownerPokemonName.equals(p2.getName()))
				return 8;
		}
		return ballBonus;
	}

}
