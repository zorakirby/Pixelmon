package pixelmon.entities.pokeballs;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.entities.pokeballs.captures.CaptureBase;
import pixelmon.entities.pokeballs.captures.CaptureDiveBall;
import pixelmon.entities.pokeballs.captures.CaptureDuskBall;
import pixelmon.entities.pokeballs.captures.CaptureFriendBall;
import pixelmon.entities.pokeballs.captures.CaptureHealBall;
import pixelmon.entities.pokeballs.captures.CaptureHeavyBall;
import pixelmon.entities.pokeballs.captures.CaptureLevelBall;
import pixelmon.entities.pokeballs.captures.CaptureLoveBall;
import pixelmon.entities.pokeballs.captures.CaptureLuxuryBall;
import pixelmon.entities.pokeballs.captures.CaptureMoonBall;
import pixelmon.entities.pokeballs.captures.CaptureNestBall;
import pixelmon.entities.pokeballs.captures.CaptureNetBall;
import pixelmon.entities.pokeballs.captures.CaptureSafariBall;
import pixelmon.enums.EnumPokeballs;

public class PokeballTypeHelper {

	private static ArrayList<CaptureBase> captureList  = new ArrayList<CaptureBase>();
	
	static{
		captureList.add(new CaptureLoveBall());
		captureList.add(new CaptureLevelBall());
		captureList.add(new CaptureMoonBall());
		captureList.add(new CaptureFriendBall());
		captureList.add(new CaptureSafariBall());
		captureList.add(new CaptureDiveBall());
		captureList.add(new CaptureDuskBall());
		captureList.add(new CaptureHealBall());
		captureList.add(new CaptureLuxuryBall());
		captureList.add(new CaptureNetBall());
		captureList.add(new CaptureNestBall());
		captureList.add(new CaptureHeavyBall());
	}
	
	public static double getBallBonus(EnumPokeballs type, EntityLivingBase thrower, EntityPixelmon p2, Mode mode) {
		double ballBonus = type.getBallBonus();
		for (CaptureBase c: captureList)
			if (c.pokeball == type) return c.getBallBonus(type, (EntityPlayer)thrower, p2, mode);
		return ballBonus;
	}
	
	public static void doAfterEffect(EnumPokeballs type, EntityPixelmon p2){
		for (CaptureBase c: captureList)
			if (c.pokeball == type) c.doAfterEffect(type, p2);
	}

	public static int modifyCaptureRate(EnumPokeballs type, String pokemonName, int captureRate) {
		for (CaptureBase c: captureList)
			if (c.pokeball == type) return c.modifyCaptureRate(pokemonName, captureRate);
		return captureRate;
	}

	
}
