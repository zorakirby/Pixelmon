package pixelmon.entities.pokeballs.captures;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumPokeballs;

public class CaptureHealBall extends CaptureBase {

	public CaptureHealBall() {
		super(EnumPokeballs.HealBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		return type.getBallBonus();
	}

	@Override
	public void doAfterEffect(EnumPokeballs type, EntityPixelmon p) {
		p.setEntityHealth(p.getMaxHealth());
	}
}
