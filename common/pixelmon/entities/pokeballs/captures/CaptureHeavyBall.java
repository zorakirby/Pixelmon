package pixelmon.entities.pokeballs.captures;

import net.minecraft.src.EntityPlayer;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
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
		double ballBonus = type.getBallBonus();
		float weight = DatabaseStats.getWeight(p2.getName());
			if (p2)
			
			
		return ballBonus;
	}

}
