package pixelmon.entities.pokeballs.captures;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.database.DatabaseStats;
import pixelmon.database.EvolutionInfo;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumPokeballs;

public class CaptureMoonBall extends CaptureBase {

	public CaptureMoonBall() {
		super(EnumPokeballs.MoonBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		for (EvolutionInfo e : DatabaseStats.getEvolveList(p2.baseStats.id)) {
			if (e.evolutionStone == EnumEvolutionStone.Moonstone || p2.getName().equalsIgnoreCase("Nidoqueen") || p2.getName().equalsIgnoreCase("Nidoking")
					|| p2.getName().equalsIgnoreCase("Clefable") || p2.getName().equalsIgnoreCase("Wigglytuff") || p2.getName().equalsIgnoreCase("Delcatty")) {
				return 4;
			}
		}
		return 1;
	}
}
