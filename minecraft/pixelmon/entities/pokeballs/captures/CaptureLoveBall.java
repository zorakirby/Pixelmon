package pixelmon.entities.pokeballs.captures;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
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
			if(bc == null)
				return ballBonus;
			boolean ownerPokemonIsMale = true;
			String ownerPokemonName = "";
			for (BattleParticipant p : bc.participants) {
				if (p instanceof PlayerParticipant && p.getEntity() == thrower) {
					ownerPokemonIsMale = p.currentPokemon().isMale;
					ownerPokemonName = p.currentPokemon().getName();
				}
			}
			if (ownerPokemonIsMale != p2.isMale && ownerPokemonName.equals(p2.getName()))
				return 8;
		}
		return ballBonus;
	}

}
