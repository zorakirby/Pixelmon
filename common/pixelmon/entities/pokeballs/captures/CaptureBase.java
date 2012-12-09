package pixelmon.entities.pokeballs.captures;

import net.minecraft.src.EntityPlayer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumPokeballs;

public abstract class CaptureBase {

	public EnumPokeballs pokeball;

	public CaptureBase(EnumPokeballs pokeball) {
		this.pokeball = pokeball;
	}

	public abstract double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode);

	public void doAfterEffect(EnumPokeballs type, EntityPixelmon p) {
	}

	public int modifyCaptureRate(String pokemonName, int captureRate) {
		return captureRate;
	}
}
