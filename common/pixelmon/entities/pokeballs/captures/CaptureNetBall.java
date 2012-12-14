package pixelmon.entities.pokeballs.captures;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import pixelmon.database.DatabaseStats;
import pixelmon.database.EvolutionInfo;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.enums.EnumPokeballs;
import pixelmon.enums.EnumType;

public class CaptureNetBall extends CaptureBase {

	public CaptureNetBall() {
		super(EnumPokeballs.DuskBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		if(p2.type.contains(EnumType.Bug) || p2.type.contains(EnumType.Water)){
			return 3;
		}
		return 1;
	}
}
