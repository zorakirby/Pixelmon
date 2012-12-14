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

public class CaptureDiveBall extends CaptureBase {

	public CaptureDiveBall() {
		super(EnumPokeballs.DiveBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		
		if(p2.isInWater()){
			return 3.5;
		}
		else return 1;
	}
}
