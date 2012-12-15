package pixelmon.entities.pokeballs.captures;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumPokeballs;

public class CaptureHeavyBall extends CaptureBase {

	public CaptureHeavyBall() {
		super(EnumPokeballs.HeavyBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		return type.getBallBonus();
	}

	@Override
	public int modifyCaptureRate(String pokemonName, int captureRate) {
		float weight = DatabaseStats.getWeight(pokemonName);
		if (weight < 205) {
			captureRate -= 20;
		} else if (weight < 307) {
			captureRate += 20;
		} else if (weight < 409.5)
			captureRate += 30;
		else
			captureRate += 40;
		return captureRate;
	}

}
