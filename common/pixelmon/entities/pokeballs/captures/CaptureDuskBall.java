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

public class CaptureDuskBall extends CaptureBase {

	public CaptureDuskBall() {
		super(EnumPokeballs.DuskBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		if(p2.posY <= 64 && !p2.worldObj.canLightningStrikeAt(MathHelper.floor_double(p2.posX), MathHelper.floor_double(p2.posY), MathHelper.floor_double(p2.posZ)) && p2.worldObj.getLightBrightness(MathHelper.floor_double(p2.posX), MathHelper.floor_double(p2.posY), MathHelper.floor_double(p2.posZ)) <= 14|| !p2.worldObj.isDaytime()){
			return 3.5;
		}
		return 1;
	}
}
