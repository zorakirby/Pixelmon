package pixelmon.entities.pokeballs.captures;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumPokeballs;

public class CaptureNestBall extends CaptureBase {

	public CaptureNestBall() {
		super(EnumPokeballs.NestBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		float lvl = p2.getLvl().getLevel();
		double bonus = (40f - lvl) / 10f;
		if (bonus < 1)
			bonus = 1;
		return bonus;
	}
}
